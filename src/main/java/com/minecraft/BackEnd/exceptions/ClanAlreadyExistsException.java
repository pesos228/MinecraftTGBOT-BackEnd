package com.minecraft.BackEnd.exceptions;

public class ClanAlreadyExistsException extends RuntimeException{
    public ClanAlreadyExistsException(String message) {
        super(message);
    }
}
