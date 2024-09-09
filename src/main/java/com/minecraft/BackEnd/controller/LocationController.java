package com.minecraft.BackEnd.controller;

import com.minecraft.BackEnd.dto.LocationDto;
import com.minecraft.BackEnd.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/location")
public class LocationController {

    private final LocationService locationService;


    @Autowired
    public LocationController(LocationService locationService) {
        this.locationService = locationService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createLocation(@RequestBody LocationDto locationDto){
        locationService.save(locationDto);
    }

    @GetMapping("/{tgId}")
    public LocationDto getLocationByTgId(@PathVariable Long tgId){
        return locationService.findByTgId(tgId);
    }
}
