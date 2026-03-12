package com.quickride.external.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DriverDto {
    private Long id;
    private String name;
    private String licenseNumber;
    private String vehicleNumber;
    private boolean availability;
    private LocalDateTime createdAt;
}