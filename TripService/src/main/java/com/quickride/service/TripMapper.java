package com.quickride.service;

import com.quickride.dto.TripRequestDto;
import com.quickride.dto.TripResponseDto;
import com.quickride.model.Trip;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TripMapper {
    private final ModelMapper modelMapper;

    public TripResponseDto toDto(Trip trip){
        return this.modelMapper.map(trip, TripResponseDto.class);
    }

    public TripRequestDto toRequestDto(Trip trip){
        return this.modelMapper.map(trip, TripRequestDto.class);
    }

    public Trip toEntity(TripRequestDto tripRequestDto){
        return this.modelMapper.map(tripRequestDto, Trip.class);
    }

    public Trip toEntity(TripResponseDto tripResponseDto){
        return this.modelMapper.map(tripResponseDto, Trip.class);
    }

}
