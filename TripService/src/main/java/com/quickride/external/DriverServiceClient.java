package com.quickride.external;

import com.quickride.external.dto.DriverDto;
import com.quickride.external.exception.DriverNotFoundException;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "DRIVER-SERVICE", path = "api/drivers")
public interface DriverServiceClient {
    //    PATCH /api/drivers/{id}/availability — toggle a driver's availability (AVAILABLE / BUSY)
    @PatchMapping("{id}/availability")
    public ResponseEntity<DriverDto> toggleDriverAvailability(@PathVariable("id") Long id) throws DriverNotFoundException;

    //    GET /api/drivers/available — list all currently available drivers
    @GetMapping("available")
    public ResponseEntity<List<DriverDto>> getAllDrivers();
}
