package com.quickride.dto;

import com.quickride.model.TRIP_STATUS;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data @AllArgsConstructor @NoArgsConstructor
public class TripResponseDto {
    private Long id;
    private Long riderId;
    private Long driverId;
    private TRIP_STATUS status;
    private String pickupLocation;
    private String dropLocation;
    private LocalDateTime createdAt;
}
