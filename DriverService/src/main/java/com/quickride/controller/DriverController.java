package com.quickride.controller;

import com.quickride.dto.DriverProfileCreateRequestDto;
import com.quickride.dto.DriverDto;
import com.quickride.exception.DriverNotFoundException;
import com.quickride.service.DriverService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/drivers")
@AllArgsConstructor
public class DriverController {

    private final DriverService driverService;

//    POST /api/users/register-driver — registers a new driver (name, email, phone)
    @PostMapping("register-driver")
    public ResponseEntity<DriverDto> registerDriver(@RequestBody DriverProfileCreateRequestDto createRequestDto){
        DriverDto driverDto = driverService.registerDriver(createRequestDto);
        return new ResponseEntity<>(driverDto, HttpStatus.CREATED);
    }

//    GET /api/drivers/{id} — fetch driver by ID
    @GetMapping("/{id}")
    public ResponseEntity<DriverDto> findDriverById(@PathVariable("id") Long id){
        DriverDto userById = driverService.findDriverById(id);
        return new ResponseEntity<>(userById, HttpStatus.OK);
    }

//    PATCH /api/drivers/{id}/availability — toggle a driver's availability (AVAILABLE / BUSY)
    @PatchMapping("{id}/availability")
    public ResponseEntity<DriverDto> toggleDriverAvailability(@PathVariable("id") Long id) throws DriverNotFoundException {
        DriverDto driverDto = driverService.toggleDriverAvailability(id);
        return new ResponseEntity<>(driverDto, HttpStatus.ACCEPTED);
    }

//    GET /api/drivers/available — list all currently available drivers
    @GetMapping("available")
    public ResponseEntity<List<DriverDto>> getAllDrivers(){
        List<DriverDto> availableDrivers = driverService.getAvailableDrivers();
        return new ResponseEntity<>(availableDrivers, HttpStatus.OK);
    }

}
