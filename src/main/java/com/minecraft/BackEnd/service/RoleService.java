package com.minecraft.BackEnd.service;

import com.minecraft.BackEnd.dto.RoleDto;
import com.minecraft.BackEnd.entities.Role;
import com.minecraft.BackEnd.exceptions.RoleAlreadyExistsException;
import com.minecraft.BackEnd.repository.impl.RoleRepositoryImpl;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RoleService extends AbstractService {

    private final RoleRepositoryImpl roleRepository;

    @Autowired
    public RoleService(RoleRepositoryImpl roleRepository, ModelMapper modelMapper) {
        super(modelMapper);
        this.roleRepository = roleRepository;
    }

    @Transactional
    public void save(RoleDto roleDto){
        Role roleExists = roleRepository.findByName(roleDto.getName());
        if (roleExists != null){
            throw new RoleAlreadyExistsException("Role with name "+ roleDto.getName() + " already exists");
        }
        Role role = convertToEntity(roleDto, Role.class);
        roleRepository.save(role);
    }
}
