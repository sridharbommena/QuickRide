package com.quickride.dto;

import com.quickride.entity.UserType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor @NoArgsConstructor @Builder
public class UserDto {
    private Long id;
    private String name;
    private String email;
    private String phone;
    private UserType userType;
    private LocalDateTime createdAt;
}
