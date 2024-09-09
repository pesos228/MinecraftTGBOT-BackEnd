package com.minecraft.BackEnd.exceptions.advice;

import com.minecraft.BackEnd.exceptions.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ResponseBody
    @ExceptionHandler(PlayerAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    String playerAlreadyExistsHandler(PlayerAlreadyExistsException e){
        return e.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(PlayerNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String playerNotFoundHandler(PlayerNotFoundException e){
        return e.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(LocationNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String locationNotFoundHandler(LocationNotFoundException e){
        return e.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(ClanAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    String clanAlreadyExistsHandler(ClanAlreadyExistsException e){
        return e.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(ClanNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String ClanNotFoundHandler(ClanNotFoundException e){
        return e.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(PlayerAlreadyHaveClanException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    String PlayerAlreadyHaveClanHandler(PlayerAlreadyHaveClanException e){
        return e.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(PlayerNotMemberOfClanException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    String UserNotMemberOfClanHandler(PlayerNotMemberOfClanException e){
        return e.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(RoleAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    String RoleAlreadyExistsHandler(RoleAlreadyExistsException e){
        return e.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(RoleNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String RoleNotFoundHandler(RoleNotFoundException e){
        return e.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(ClanAlreadyHaveOwnerException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    String lanAlreadyHaveOwnerHandler(ClanAlreadyHaveOwnerException e){
        return e.getMessage();
    }

}
