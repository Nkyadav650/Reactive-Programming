package com.reactive.webflux.jwt.config;

import com.reactive.webflux.jwt.utils.JwtUtil;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import reactor.core.publisher.Mono;

import java.util.List;

public class JwtReactiveAuthenticationManager implements ReactiveAuthenticationManager {

    private final JwtUtil jwtUtil;
    private final ReactiveUserDetailsService userDetailsService;

    public JwtReactiveAuthenticationManager(JwtUtil jwtUtil, ReactiveUserDetailsService userDetailsService) {
        this.jwtUtil = jwtUtil;
        this.userDetailsService = userDetailsService;
    }

    @Override
    public Mono<Authentication> authenticate(Authentication authentication) {
        String username = authentication.getName();

        return userDetailsService.findByUsername(username)
                .map(userDetails -> {
                    List<SimpleGrantedAuthority> authorities = userDetails.getAuthorities().stream()
                            .map(authority -> new SimpleGrantedAuthority(authority.getAuthority().toUpperCase()))
                            .toList();

                    return new UsernamePasswordAuthenticationToken(userDetails, null, authorities);
                });
    }


//    @Override
//    public Mono<Authentication> authenticate(Authentication authentication) {
//        String username = authentication.getName();
//
//        return userDetailsService.findByUsername(username)
//                .map(userDetails -> new UsernamePasswordAuthenticationToken(
//                        userDetails,
//                        null,
//                        userDetails.getAuthorities()
//                ));
//    }
}

