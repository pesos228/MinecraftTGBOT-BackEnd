package com.minecraft.BackEnd.exceptions;

public class PlayerNotMemberOfClanException extends RuntimeException {
    public PlayerNotMemberOfClanException(String message) {
        super(message);
    }
}
