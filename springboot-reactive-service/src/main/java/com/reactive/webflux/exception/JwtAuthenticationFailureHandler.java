package com.reactive.webflux.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.buffer.DataBufferFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.web.server.WebFilterExchange;
import org.springframework.security.web.server.authentication.ServerAuthenticationFailureHandler;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.core.AuthenticationException;

@Slf4j
public class JwtAuthenticationFailureHandler implements ServerAuthenticationFailureHandler {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public Mono<Void> onAuthenticationFailure(WebFilterExchange webFilterExchange, AuthenticationException exception) {
        log.error("Authentication failed: {}", exception.getMessage());

        Map<String, Object> response = new HashMap<>();
        response.put("error", exception.getMessage());
        response.put("status", HttpStatus.UNAUTHORIZED.value());

        var exchange = webFilterExchange.getExchange();
        var responseWriter = exchange.getResponse();
        responseWriter.setStatusCode(HttpStatus.UNAUTHORIZED);
        responseWriter.getHeaders().setContentType(MediaType.APPLICATION_JSON);

        try {
            String json = objectMapper.writeValueAsString(response);
            DataBufferFactory bufferFactory = responseWriter.bufferFactory();
            return responseWriter.writeWith(Mono.just(bufferFactory.wrap(json.getBytes(StandardCharsets.UTF_8))));
        } catch (Exception e) {
            log.error("Error writing failure response", e);
            return responseWriter.setComplete();
        }
    }


}
