package com.minecraft.BackEnd.repository;

import com.minecraft.BackEnd.entities.Clan;

public interface ClanRepository {

    Clan findByName(String name);
    Clan findClanByPlayerTgId(Long tgId);
    void deleteByName(String name);
}
