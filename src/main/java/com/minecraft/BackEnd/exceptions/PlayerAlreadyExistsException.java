package com.minecraft.BackEnd.exceptions;

public class PlayerAlreadyExistsException extends RuntimeException {
    public PlayerAlreadyExistsException(Long id) {
        super("User with "+ id +" id already exists");
    }
}
