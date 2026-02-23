package com.quickride.service;

import com.quickride.dto.DriverDto;
import com.quickride.entity.Driver;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DriverModelMapper {

    private final ModelMapper modelMapper;

    public Driver toEntity(DriverDto driverDto){
        return modelMapper.map(driverDto, Driver.class);
    }

    public DriverDto toDto(Driver driver){
        return modelMapper.map(driver, DriverDto.class);
    }

}
