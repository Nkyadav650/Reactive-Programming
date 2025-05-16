package com.reactive.webflux.serviceimpl;

import com.reactive.webflux.entity.Users;
import com.reactive.webflux.jwt.utils.JwtUtil;
import com.reactive.webflux.model.AuthRequest;
import com.reactive.webflux.repository.UserRepository;
import com.reactive.webflux.service.AuthService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.Map;
import java.util.UUID;

@Slf4j
@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final PasswordEncoder encoder;
    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;

    @Override
    public Mono<Users> register(Users user) {
        if (user.getUserId() == null || user.getUserId().isBlank()) {
            user.setUserId(UUID.randomUUID().toString());
        }

        user.setPassword(encoder.encode(user.getPassword()));

        return userRepository.existsById(user.getUserId())
                .flatMap(exists -> {
                    if (exists) {
                        // Optional: throw error or update
                        return Mono.error(new IllegalArgumentException("User already exists"));
                    } else {

                        Mono<Users> save = userRepository.saveUsers(user);
                        System.out.println(save);
                        return save;

                    }
                });
    }


    @Override
    public Mono<Map<String, String>> login(AuthRequest request) {
        return userRepository.findByUsername(request.getUsername())
                .doOnNext(users -> log.info("Stored (hashed) password: {}", users.getPassword()))
                .doOnNext(users -> log.info("Request (raw) password: {}", request.getPassword()))
                .filter(users -> encoder.matches(request.getPassword(), users.getPassword()))
                .map(users -> {
                    String token = jwtUtil.generateToken(users.getUsername(), users.getRole());
                    return Map.of("token", token);
                })
                .switchIfEmpty(Mono.error(new RuntimeException("Invalid username or password"))); // optional: for custom error
    }


}
