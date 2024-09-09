package com.minecraft.BackEnd.exceptions;

public class ClanAlreadyHaveOwnerException extends RuntimeException{
    public ClanAlreadyHaveOwnerException(String message) {
        super(message);
    }
}
