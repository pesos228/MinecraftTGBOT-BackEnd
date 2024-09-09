package com.minecraft.BackEnd.config;

import com.minecraft.BackEnd.dto.*;
import com.minecraft.BackEnd.entities.*;
import com.minecraft.BackEnd.repository.PlayerRepository;
import com.minecraft.BackEnd.repository.impl.ClanRepositoryImpl;
import com.minecraft.BackEnd.repository.impl.RoleRepositoryImpl;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper(PlayerRepository playerRepository,
                                   ClanRepositoryImpl clanRepository,
                                   RoleRepositoryImpl roleRepository) {
        ModelMapper modelMapper = new ModelMapper();

        // Маппинг для PlayerDtoRegister в Player
        modelMapper.addMappings(new PropertyMap<PlayerDtoRegister, Player>() {
            @Override
            protected void configure() {
                skip().setId(null);
                map().setUsername(source.getUsername());
                map().setTgId(source.getTgId());
                map().setBalance(0L); // Значения по умолчанию
                map().setStatus(false);
            }
        });

        // Маппинг для LocationDto в Location
        modelMapper.addMappings(new PropertyMap<LocationDto, Location>() {
            @Override
            protected void configure() {
                skip().setId(null);
                map().setX(source.getX());
                map().setY(source.getY());
                map().setZ(source.getZ());
                using(ctx -> playerRepository.findByTgId((Long) ctx.getSource())).map(source.getTgId(), destination.getPlayer());
            }
        });

        // Маппинг для Location в LocationDto
        modelMapper.addMappings(new PropertyMap<Location, LocationDto>() {
            @Override
            protected void configure() {
                map().setX(source.getX());
                map().setY(source.getY());
                map().setZ(source.getZ());
                map().setTgId(source.getPlayer().getTgId());
            }
        });

        // Маппинг для PlayerRoleDto в Role
        modelMapper.addMappings(new PropertyMap<PlayerRoleDto, Role>() {
            @Override
            protected void configure() {
                skip().setId(null);
                map().setName(source.getRole_name());
            }
        });

        // Маппинг для ClanMemberDto в ClanMember
        modelMapper.addMappings(new PropertyMap<ClanMemberDto, ClanMember>() {
            @Override
            protected void configure() {
                skip().setId(null);
                using(ctx -> playerRepository.findByTgId((Long) ctx.getSource()))
                        .map(source.getTgId(), destination.getPlayer());
                using(ctx -> clanRepository.findByName((String) ctx.getSource()))
                        .map(source.getClan_name(), destination.getClan());
                using(ctx -> roleRepository.findByName((String) ctx.getSource()))
                        .map(source.getRole_name(), destination.getRole());
            }
        });

        // Маппинг для ClanMember в ClanMemberDto
        modelMapper.addMappings(new PropertyMap<ClanMember, ClanMemberDto>() {
            @Override
            protected void configure() {
                map().setTgId(source.getPlayer().getTgId());
                map().setClan_name(source.getClan().getName());
                map().setRole_name(source.getRole().getName());
            }
        });

        return modelMapper;
    }
}

