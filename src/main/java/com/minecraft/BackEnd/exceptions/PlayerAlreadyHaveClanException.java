package com.minecraft.BackEnd.exceptions;

public class PlayerAlreadyHaveClanException extends RuntimeException{
    public PlayerAlreadyHaveClanException(String message) {
        super(message);
    }
}
