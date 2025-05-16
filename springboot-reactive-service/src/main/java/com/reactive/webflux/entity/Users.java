package com.reactive.webflux.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Table("users")
public class Users {
    @Id
    private String userId;
    private String username;
    private String password;
    private String email;
    private long contact;
    private String role;
}

