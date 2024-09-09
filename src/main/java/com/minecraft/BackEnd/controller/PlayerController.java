package com.minecraft.BackEnd.controller;

import com.minecraft.BackEnd.dto.PlayerDto;
import com.minecraft.BackEnd.dto.PlayerDtoRegister;
import com.minecraft.BackEnd.dto.PlayerRoleDto;
import com.minecraft.BackEnd.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/player")
public class PlayerController {

    private final PlayerService playerService;

    @Autowired
    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createPlayer(@RequestBody PlayerDtoRegister playerDto){
        playerService.save(playerDto);
    }

    @GetMapping("/{username}")
    public PlayerDto getPlayerByUsername(@PathVariable String username) {
        return playerService.findByUsername(username);
    }

    @GetMapping("/tgId/{tgId}")
    public PlayerDto getPlayerByTgId(@PathVariable Long tgId) {
        return playerService.findByTgId(tgId);
    }

    @GetMapping
    public List<PlayerDto> getPlayerByTgId(){
        return playerService.findAll();
    }

    @PutMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void updatePlayer(@RequestBody PlayerDto playerDto) {
        playerService.update(playerDto);
    }

}
