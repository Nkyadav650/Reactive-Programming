package com.reactive.webflux.jwt.config;

import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.web.server.authentication.AuthenticationWebFilter;
import org.springframework.security.web.server.context.WebSessionServerSecurityContextRepository;
import org.springframework.stereotype.Component;

import com.reactive.webflux.exception.JwtAuthenticationFailureHandler;
import com.reactive.webflux.exception.JwtTokenExpiredException;
import com.reactive.webflux.jwt.utils.JwtUtil;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Slf4j
@Component
public class JwtAuthenticationFilter extends AuthenticationWebFilter {
	

    public JwtAuthenticationFilter(JwtUtil jwtUtil, ReactiveUserDetailsService userDetailsService) {
        super(new JwtReactiveAuthenticationManager(jwtUtil, userDetailsService));

        this.setServerAuthenticationConverter(exchange -> {
            String authHeader = exchange.getRequest().getHeaders().getFirst(HttpHeaders.AUTHORIZATION);
            log.info("Received Authorization header: {}", authHeader);

            if (authHeader != null && authHeader.startsWith("Bearer ")) {
                String authToken = authHeader.substring(7);
                log.info("Extracted JWT token: {}", authToken);

                try {
                    String username = jwtUtil.getUsername(authToken);
                    log.info("Extracted username from token: {}", username);

                    if (username != null && jwtUtil.isTokenValid(authToken)) {
                        log.info("Token is valid for user: {}", username);
                        return Mono.just(new UsernamePasswordAuthenticationToken(username, null));
                    } else {
                        log.warn("JWT token is invalid or username is null");
                        return Mono.empty();
                    }

                } catch (ExpiredJwtException e) {
                    log.warn("JWT token expired: {}", e.getMessage());
                    return Mono.error(new JwtTokenExpiredException("JWT token has expired"));
                } catch (SignatureException e) {
                    log.warn("JWT signature is invalid: {}", e.getMessage());
                    return Mono.error(new JwtTokenExpiredException("Invalid JWT signature"));
                } catch (MalformedJwtException e) {
                    log.warn("JWT token is malformed: {}", e.getMessage());
                    return Mono.error(new JwtTokenExpiredException("Malformed JWT token"));
                } catch (JwtException e) {
                    log.warn("General JWT exception: {}", e.getMessage());
                    return Mono.error(new JwtTokenExpiredException("Invalid JWT token"));
                } catch (Exception e) {
                    log.error("Unexpected error while processing token", e);
                    return Mono.error(new JwtTokenExpiredException("Unexpected token processing error"));
                }
            }

            return Mono.empty();
        });

        this.setSecurityContextRepository(new WebSessionServerSecurityContextRepository());
        this.setAuthenticationFailureHandler(new JwtAuthenticationFailureHandler());
    }
}
