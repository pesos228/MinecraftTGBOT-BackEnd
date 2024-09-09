package com.minecraft.BackEnd.exceptions;

public class ClanNotFoundException extends RuntimeException{
    public ClanNotFoundException(String message) {
        super(message);
    }
}
