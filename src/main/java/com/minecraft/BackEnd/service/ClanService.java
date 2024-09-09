package com.minecraft.BackEnd.service;

import com.minecraft.BackEnd.dto.ClanDto;
import com.minecraft.BackEnd.dto.ClanMemberDto;
import com.minecraft.BackEnd.entities.Clan;
import com.minecraft.BackEnd.entities.Player;
import com.minecraft.BackEnd.exceptions.*;
import com.minecraft.BackEnd.repository.impl.ClanRepositoryImpl;
import com.minecraft.BackEnd.repository.impl.PlayerRepositoryImpl;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClanService extends AbstractService{

    private final ClanRepositoryImpl clanRepository;
    private final PlayerRepositoryImpl playerRepository;

    @Autowired
    protected ClanService(ModelMapper modelMapper, ClanRepositoryImpl clanRepository, PlayerRepositoryImpl playerRepository) {
        super(modelMapper);
        this.clanRepository = clanRepository;
        this.playerRepository = playerRepository;
    }

    @Transactional
    public void save(ClanDto clanDto){
        Clan clan = clanRepository.findByName(clanDto.getName());
        if (clan != null){
            throw new ClanAlreadyExistsException("Clan with name " + clanDto.getName() + " already exists");
        }
        Clan newClan = convertToEntity(clanDto, Clan.class);
        System.out.println(newClan.getName());
        clanRepository.save(newClan);
    }

    @Transactional
    public void deleteClan(String name){
        clanRepository.deleteByName(name);
    }

    public List<ClanDto> findAll(){
        return clanRepository.findAll().stream()
                .map(clan -> convertToDto(clan, ClanDto.class))
                .collect(Collectors.toList());
    }

}
