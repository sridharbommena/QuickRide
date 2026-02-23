package com.quickride.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data @AllArgsConstructor @NoArgsConstructor @Builder
@Table(name = "DRIVERS")
public class Driver {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String licenseNumber;
    private String vehicleNumber;
    private boolean availability;
    private LocalDateTime createdAt;

    @PrePersist
    private void beforeCreate(){
        this.availability = true;
        this.createdAt = LocalDateTime.now();
    }

}
