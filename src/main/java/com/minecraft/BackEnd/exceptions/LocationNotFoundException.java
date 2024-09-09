package com.minecraft.BackEnd.exceptions;

public class LocationNotFoundException extends RuntimeException{
    public LocationNotFoundException(Long id) {
        super("User's location with "+ id +" id not found");
    }
}
