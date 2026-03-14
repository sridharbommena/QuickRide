package com.quickride.controller;

import com.quickride.dto.TripRequestDto;
import com.quickride.dto.TripResponseDto;
import com.quickride.external.exception.DriverNotFoundException;
import com.quickride.external.exception.TripNotFoundException;
import com.quickride.model.TRIP_STATUS;
import com.quickride.service.TripService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/trips")
@AllArgsConstructor
public class TripController {

    private final TripService tripService;

//    POST /api/trips — create a new trip (riderId, pickupLocation, dropLocation)
    @PostMapping
    public TripResponseDto createTrip(@RequestBody TripRequestDto trip) throws DriverNotFoundException {
        return tripService.createTrip(trip);
    }

//    GET /api/trips/{id} — get trip by ID
    @GetMapping("{id}")
    public TripResponseDto getTripById(@PathVariable("id") Long id) throws TripNotFoundException {
        return tripService.getTripById(id);
    }

//    PATCH /api/trips/{id}/status/{status} - Change trip status
    @PatchMapping("{id}/status/{status}")
    public TripResponseDto updateTrip(@PathVariable("id") Long id, @PathVariable("status")TRIP_STATUS status) throws DriverNotFoundException, TripNotFoundException {
        return this.tripService.updateTripStatus(id, status);
    }

}
