package com.quickride.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "TRIP")
@Data @AllArgsConstructor @NoArgsConstructor @Builder
public class Trip {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long riderId;
    private Long driverId;

    @Enumerated(EnumType.STRING)
    private TRIP_STATUS status;
    private String pickupLocation;
    private String dropLocation;
    private LocalDateTime createdAt;

    @PrePersist
    void setUp(){
        this.createdAt = LocalDateTime.now();
        this.status = TRIP_STATUS.REQUESTED;
    }

}

