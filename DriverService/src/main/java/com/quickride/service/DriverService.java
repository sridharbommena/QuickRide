package com.quickride.service;

import com.quickride.dto.DriverProfileCreateRequestDto;
import com.quickride.dto.DriverDto;
import com.quickride.entity.Driver;
import com.quickride.exception.DriverNotFoundException;

import java.util.List;

public interface DriverService {
    DriverDto registerDriver(DriverProfileCreateRequestDto createRequestDto);
    DriverDto findDriverById(Long id);
    DriverDto toggleDriverAvailability(Long id) throws DriverNotFoundException;
    List<DriverDto> getAvailableDrivers();
}
