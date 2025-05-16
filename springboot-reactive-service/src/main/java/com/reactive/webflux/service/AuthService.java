package com.reactive.webflux.service;

import com.reactive.webflux.entity.Users;
import com.reactive.webflux.model.AuthRequest;
import reactor.core.publisher.Mono;

import java.util.Map;

public interface AuthService {

    public Mono<Users> register(Users users);
    public Mono<Map<String, String>> login(AuthRequest request);
}
