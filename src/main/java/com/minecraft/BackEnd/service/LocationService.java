package com.minecraft.BackEnd.service;

import com.minecraft.BackEnd.dto.LocationDto;
import com.minecraft.BackEnd.entities.Location;
import com.minecraft.BackEnd.exceptions.LocationNotFoundException;
import com.minecraft.BackEnd.repository.impl.LocationRepositoryImpl;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class LocationService extends AbstractService{

    private final LocationRepositoryImpl locationRepository;
    @Autowired
    protected LocationService(ModelMapper modelMapper, LocationRepositoryImpl locationRepository) {
        super(modelMapper);
        this.locationRepository = locationRepository;
    }

    @Transactional
    public void save(LocationDto locationDto){
        Location locationTest = locationRepository.findByTgId(locationDto.getTgId());
        if (locationTest != null){
            locationTest.setX(locationDto.getX());
            locationTest.setY(locationDto.getY());
            locationTest.setZ(locationDto.getZ());
            locationRepository.save(locationTest);
        }
        else {
            Location location = convertToEntity(locationDto, Location.class);
            locationRepository.save(location);
        }

    }

    public LocationDto findByTgId(Long id){
        Location location = locationRepository.findByTgId(id);
        if (location == null){
            throw new LocationNotFoundException(id);
        }
        return convertToDto(location,LocationDto.class);
    }

}
