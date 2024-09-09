package com.minecraft.BackEnd.repository;

import com.minecraft.BackEnd.entities.Role;

public interface RoleRepository {

    Role findByName(String name);

}
