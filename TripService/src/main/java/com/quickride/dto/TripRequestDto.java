package com.quickride.dto;

import lombok.*;

@Setter
@Getter
public class TripRequestDto {
    private Long riderId;
    private String pickupLocation;
    private String dropLocation;
}
