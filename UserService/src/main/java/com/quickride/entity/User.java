package com.quickride.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data @AllArgsConstructor @NoArgsConstructor @Builder
@Table(name = "USERS")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private String email;
    private String phone;

    @Enumerated(EnumType.STRING)
    private UserType userType;
    private LocalDateTime createdAt;

    @PrePersist
    private void beforeCreate(){
        this.createdAt = LocalDateTime.now();
    }

}
