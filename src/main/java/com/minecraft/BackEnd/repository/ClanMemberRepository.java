package com.minecraft.BackEnd.repository;


import com.minecraft.BackEnd.entities.ClanMember;

import java.util.List;

public interface ClanMemberRepository {
    void deleteByTgId(Long tgId);
    List<ClanMember> getMembersByClanName(String name);
    ClanMember findByTgId(Long tgId);
}
