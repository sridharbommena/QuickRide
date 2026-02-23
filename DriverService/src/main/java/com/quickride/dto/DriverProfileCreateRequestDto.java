package com.quickride.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor @NoArgsConstructor @Builder
public class DriverProfileCreateRequestDto {
    private String name;
    private String licenseNumber;
    private String vehicleNumber;
}
