package com.minecraft.BackEnd.service;

import com.minecraft.BackEnd.dto.ClanMemberDto;
import com.minecraft.BackEnd.entities.Clan;
import com.minecraft.BackEnd.entities.ClanMember;
import com.minecraft.BackEnd.entities.Player;
import com.minecraft.BackEnd.entities.Role;
import com.minecraft.BackEnd.exceptions.*;
import com.minecraft.BackEnd.repository.impl.ClanMemberRepositoryImpl;
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
public class ClanMemberService extends AbstractService {
    private final ClanMemberRepositoryImpl clanMemberRepository;
    private final ClanRepositoryImpl clanRepository;
    private final PlayerRepositoryImpl playerRepository;
    private final RoleRepositoryImpl roleRepository;

    @Autowired
    public ClanMemberService(ClanMemberRepositoryImpl clanMemberRepository, ClanRepositoryImpl clanRepository, PlayerRepositoryImpl playerRepository, RoleRepositoryImpl roleRepository, ModelMapper modelMapper) {
        super(modelMapper);
        this.clanMemberRepository = clanMemberRepository;
        this.clanRepository = clanRepository;
        this.playerRepository = playerRepository;
        this.roleRepository = roleRepository;
    }

    @Transactional
    public void addMember(ClanMemberDto clanMemberDto){
        Player player = playerRepository.findByTgId(clanMemberDto.getTgId());
        if (player == null){
            throw new PlayerNotFoundException("Player with ID "+ clanMemberDto.getTgId() + " not found");
        }
        Clan clan = clanRepository.findByName(clanMemberDto.getClan_name());
        if (clan == null){
            throw new ClanNotFoundException("Clan with name "+ clanMemberDto.getClan_name() + " not found");
        }
        Clan clanMemberCheck = clanRepository.findClanByPlayerTgId(clanMemberDto.getTgId());
        if (clanMemberCheck == clan){
            throw new PlayerAlreadyHaveClanException("Player is already a member of a clan "+ clanMemberDto.getClan_name());
        }
        if (clanMemberCheck != null){
            throw new PlayerAlreadyHaveClanException("Player is already a member of a clan "+ clanMemberCheck.getName());
        }
        Role role = roleRepository.findByName(clanMemberDto.getRole_name());
        if (role == null){
            throw new RoleNotFoundException("Role with name "+ clanMemberDto.getRole_name() + " not found");
        }
        if ("OWNER".equals(clanMemberDto.getRole_name())) {
            List<ClanMemberDto> clanMembers = getMembersByClanName(clanMemberDto.getClan_name());
            for (ClanMemberDto clanMember : clanMembers) {
                if ("OWNER".equals(clanMember.getRole_name())) {
                    throw new ClanAlreadyExistsException("Clan " + clanMemberDto.getClan_name() + " already has an owner");
                }
            }
        }
        ClanMember clanMember = convertToEntity(clanMemberDto, ClanMember.class);
        clanMemberRepository.save(clanMember);
    }

    @Transactional
    public void removeMember(ClanMemberDto clanMemberDto){
        Player player = playerRepository.findByTgId(clanMemberDto.getTgId());
        if (player == null){
            throw new PlayerNotFoundException("Player with ID "+ clanMemberDto.getTgId() + " not found");
        }
        Clan clan = clanRepository.findByName(clanMemberDto.getClan_name());
        if (clan == null){
            throw new ClanNotFoundException("Clan with name "+ clanMemberDto.getClan_name() + " not found");
        }
        Clan clanMemberCheck = clanRepository.findClanByPlayerTgId(clanMemberDto.getTgId());
        if (clanMemberCheck != clan){
            throw new PlayerNotMemberOfClanException("Player not member of clan "+ clanMemberDto.getClan_name());
        }

        ClanMember clanMember = clanMemberRepository.findByTgId(clanMemberDto.getTgId());
        Role roleCheck = clanMember.getRole();
        if (!roleCheck.getName().equals(clanMemberDto.getRole_name())){
            throw new RoleNotFoundException("Player doesnt have "+ clanMemberDto.getRole_name() + " role");
        }
        clanMemberRepository.deleteByTgId(clanMemberDto.getTgId());
    }

    public List<ClanMemberDto> getMembersByClanName(String name){
        List<ClanMember> clanMembers = clanMemberRepository.getMembersByClanName(name);
        return clanMembers.stream()
                .map(clanMember -> convertToDto(clanMember, ClanMemberDto.class))
                .collect(Collectors.toList());
    }

    public ClanMemberDto findClanByTgId(Long id){
        Player player = playerRepository.findByTgId(id);
        if (player == null){
            throw new PlayerNotFoundException("Player with ID "+ id + " not found");
        }
        Clan clan = clanRepository.findClanByPlayerTgId(id);
        if (clan == null){
            throw new PlayerNotMemberOfClanException("Player "+ player.getUsername() +" not member of clan");
        }
        return convertToDto(clanMemberRepository.findByTgId(id), ClanMemberDto.class);
    }
}
