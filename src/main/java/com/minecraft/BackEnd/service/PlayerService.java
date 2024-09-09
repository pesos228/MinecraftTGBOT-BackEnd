package com.minecraft.BackEnd.service;

import com.minecraft.BackEnd.dto.PlayerDto;
import com.minecraft.BackEnd.dto.PlayerDtoRegister;
import com.minecraft.BackEnd.dto.PlayerRoleDto;
import com.minecraft.BackEnd.entities.Clan;
import com.minecraft.BackEnd.entities.Player;
import com.minecraft.BackEnd.entities.Role;
import com.minecraft.BackEnd.exceptions.PlayerAlreadyExistsException;
import com.minecraft.BackEnd.exceptions.PlayerNotFoundException;
import com.minecraft.BackEnd.exceptions.PlayerNotMemberOfClanException;
import com.minecraft.BackEnd.exceptions.RoleNotFoundException;
import com.minecraft.BackEnd.repository.impl.ClanRepositoryImpl;
import com.minecraft.BackEnd.repository.impl.PlayerRepositoryImpl;
import com.minecraft.BackEnd.repository.impl.RoleRepositoryImpl;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PlayerService extends AbstractService {

    private final PlayerRepositoryImpl playerRepository;

    @Autowired
    public PlayerService(PlayerRepositoryImpl playerRepository, ModelMapper modelMapper) {
        super(modelMapper);
        this.playerRepository = playerRepository;
    }

    @Transactional
    public void save(PlayerDtoRegister playerDto){
        Player player = convertToEntity(playerDto, Player.class);
        Player playerTest = playerRepository.findByTgId(playerDto.getTgId());
        if (playerTest == null){
            playerRepository.save(player);
        }
        else{
            throw new PlayerAlreadyExistsException(playerDto.getTgId());
        }
    }

    @Transactional
    public void update(PlayerDto playerDto){
        Player playerTest = playerRepository.findByTgId(playerDto.getTgId());
        if (playerTest == null){
            throw new PlayerNotFoundException("User with "+ playerDto.getTgId() +" id not found");
        }
        playerTest.setBalance(playerDto.getBalance());
        playerTest.setUsername(playerDto.getUsername());
        playerTest.setStatus(playerDto.isStatus());
        playerRepository.save(playerTest);

    }

    public PlayerDto findByTgId(Long id){
        Player player = playerRepository.findByTgId(id);
        if (player == null){
            throw new PlayerNotFoundException("User with "+ id +" id not found");
        }
        return convertToDto(player, PlayerDto.class);
    }

    public PlayerDto findByUsername(String username){
        Player player = playerRepository.findByUsername(username);
        if (player == null){
            throw new PlayerNotFoundException("User with "+ username +" name not found");
        }
        return convertToDto(player,PlayerDto.class);
    }

    public List<PlayerDto> findAll(){
        return playerRepository.findAll().stream()
                .map(player -> convertToDto(player,PlayerDto.class))
                .collect(Collectors.toList());
    }

}
