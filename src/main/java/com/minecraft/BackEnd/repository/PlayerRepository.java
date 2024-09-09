package com.minecraft.BackEnd.repository;

import com.minecraft.BackEnd.entities.Player;

public interface PlayerRepository {

    Player findByTgId(Long id);
    Player findByUsername(String username);
}
