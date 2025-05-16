package com.reactive.webflux.repository;

import com.reactive.webflux.entity.Users;
import org.springframework.data.r2dbc.repository.Modifying;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface UserRepository extends ReactiveCrudRepository<Users,String> {
    public Mono<Users> findByUsername(String username);
    @Modifying
    @Query("INSERT INTO users (user_id, username,password, email,contact, role) VALUES (:#{#user.userId}, :#{#user.username}, :#{#user.password}, :#{#user.email}, :#{#user.contact}, :#{#user.role})")
   public Mono<Users> saveUsers(Users user);
}
