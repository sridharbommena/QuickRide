package com.quickride.service;

import com.quickride.dto.TripRequestDto;
import com.quickride.dto.TripResponseDto;
import com.quickride.external.exception.DriverNotFoundException;
import com.quickride.external.exception.TripNotFoundException;
import com.quickride.model.TRIP_STATUS;

public interface TripService {
    TripResponseDto createTrip(TripRequestDto tripRequestDto) throws DriverNotFoundException;
    TripResponseDto getTripById(Long id) throws TripNotFoundException;
    TripResponseDto updateTripStatus(Long id, TRIP_STATUS tripStatus) throws DriverNotFoundException, TripNotFoundException;
}
