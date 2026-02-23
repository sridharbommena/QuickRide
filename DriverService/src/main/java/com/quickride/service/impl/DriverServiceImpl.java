package com.quickride.service.impl;

import com.quickride.dto.DriverProfileCreateRequestDto;
import com.quickride.dto.DriverDto;
import com.quickride.entity.Driver;
import com.quickride.exception.DriverNotFoundException;
import com.quickride.repo.DriverRepository;
import com.quickride.service.DriverModelMapper;
import com.quickride.service.DriverService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class DriverServiceImpl implements DriverService {

    private final DriverRepository driverRepository;
    private final DriverModelMapper driverModelMapper;

    @Override
    @Transactional
    public DriverDto registerDriver(DriverProfileCreateRequestDto createRequestDto) {
        Driver driver = new Driver();

        driver.setName(createRequestDto.getName());
        driver.setLicenseNumber(createRequestDto.getLicenseNumber());
        driver.setVehicleNumber(createRequestDto.getVehicleNumber());

        Driver savedDriver = driverRepository.save(driver);

        return driverModelMapper.toDto(savedDriver);
    }

    @Override
    public DriverDto findDriverById(Long id) {
        Optional<Driver> optionalUser = driverRepository.findById(id);
        return optionalUser.map(driverModelMapper::toDto).orElse(null);
    }

    @Transactional
    @Override
    public DriverDto toggleDriverAvailability(Long id) throws DriverNotFoundException {
        Optional<Driver> driverOptional = driverRepository.findById(id);

        if(driverOptional.isPresent()){
            Driver driver = driverOptional.get();
            driver.setAvailability(!driver.isAvailability());
            Driver savedDriver = driverRepository.save(driver);

            return driverModelMapper.toDto(savedDriver);
        } else
            throw new DriverNotFoundException("Driver with id: " + id + " is not found!");

    }

    @Override
    public List<DriverDto> getAvailableDrivers() {
        List<Driver> availableDriversList = driverRepository.findDriversByAvailabilityTrue();
        return toDriverDto(availableDriversList);
    }

    private List<DriverDto> toDriverDto(List<Driver> availableDriversList) {
        return availableDriversList.stream()
                .map(driverModelMapper::toDto)
                .collect(Collectors.toList());
    }
}
