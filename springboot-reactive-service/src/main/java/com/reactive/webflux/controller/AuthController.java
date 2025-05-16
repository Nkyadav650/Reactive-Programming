package com.reactive.webflux.controller;

import com.reactive.webflux.entity.Users;
import com.reactive.webflux.model.AuthRequest;
import com.reactive.webflux.service.AuthService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/auth")
@AllArgsConstructor
@Slf4j
public class AuthController {

    private final AuthService authService;

    private final PasswordEncoder encoder;

    @PostMapping("/register")
    public Mono<Users> register(@RequestBody Users users) {
//        if (users.getUserId() == null || users.getUserId().isBlank()) {
//            String newId = UUID.randomUUID().toString();
//            System.out.println("Generated UUID: " + newId);  // Add log
//            users.setUserId(newId);
//        }
        Mono<Users> register = authService.register(users);
        return register;
    }

    @PostMapping("/login")
    public Mono<ResponseEntity<Map<String, String>>> login(@RequestBody AuthRequest request) {
        return authService.login(request)
                .map(ResponseEntity::ok)
                .onErrorResume(e -> {
                    log.error("Login failed: {}", e.getMessage());
                    return Mono.just(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
                })
                .defaultIfEmpty(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
    }

}
