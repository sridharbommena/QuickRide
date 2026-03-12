package com.quickride.service.impl;

import com.quickride.dto.TripRequestDto;
import com.quickride.dto.TripResponseDto;
import com.quickride.external.DriverServiceClient;
import com.quickride.external.dto.DriverDto;
import com.quickride.external.exception.DriverNotFoundException;
import com.quickride.model.TRIP_STATUS;
import com.quickride.model.Trip;
import com.quickride.repo.TripRepository;
import com.quickride.service.TripMapper;
import com.quickride.service.TripService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class TripServiceImpl implements TripService {

    private final TripRepository tripRepository;
    private final TripMapper tripMapper;
    private final DriverServiceClient driverServiceClient;

    @Override
    @Transactional
    public TripResponseDto createTrip(TripRequestDto tripRequestDto) throws DriverNotFoundException {
        Trip trip = tripMapper.toEntity(tripRequestDto);
        Trip savedTrip = tripRepository.save(trip);

        //call driver service to get available list of drivers
        ResponseEntity<List<DriverDto>> allDrivers = driverServiceClient.getAllDrivers();
        assert allDrivers.getBody() != null;
        Optional<DriverDto> firstDriver = allDrivers.getBody().stream().findFirst();


        //assign driver id
        if(firstDriver.isPresent()){
            savedTrip.setDriverId(firstDriver.get().getId());
            savedTrip.setStatus(TRIP_STATUS.ASSIGNED);

            //call driver service to update the availability of the driver
            driverServiceClient.toggleDriverAvailability(firstDriver.get().getId());

            //save
            return tripMapper.toDto(tripRepository.save(savedTrip));
        }

        return tripMapper.toDto(savedTrip);
    }

    @Override
    public TripResponseDto getTripById(Long id) {
        Optional<Trip> tripOptional = tripRepository.findById(id);

        if(tripOptional.isPresent()){
            Trip trip = tripOptional.get();
            return tripMapper.toDto(trip);
        }

        return null;
    }

    @Override
    @Transactional
    public TripResponseDto updateTripStatus(Long id, TRIP_STATUS tripStatus) throws DriverNotFoundException {
        Optional<Trip> tripOptional = tripRepository.findById(id);

        if(tripOptional.isPresent()){
            Trip trip = tripOptional.get();
            trip.setStatus(tripStatus);

            if(tripStatus.equals(TRIP_STATUS.COMPLETED)){
                //make the driver available again
                ResponseEntity<DriverDto> driverDtoResponseEntity = driverServiceClient.toggleDriverAvailability(trip.getDriverId());
            }

            Trip saved = tripRepository.save(trip);
            return tripMapper.toDto(saved);
        }

        return null;
    }
}
