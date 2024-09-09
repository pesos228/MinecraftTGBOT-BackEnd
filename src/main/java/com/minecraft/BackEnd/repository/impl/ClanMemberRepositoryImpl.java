package com.minecraft.BackEnd.repository.impl;

import com.minecraft.BackEnd.entities.Clan;
import com.minecraft.BackEnd.entities.ClanMember;
import com.minecraft.BackEnd.exceptions.PlayerNotMemberOfClanException;
import com.minecraft.BackEnd.repository.AbstractBaseRepository;
import com.minecraft.BackEnd.repository.ClanMemberRepository;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ClanMemberRepositoryImpl extends AbstractBaseRepository<ClanMember, Integer> implements ClanMemberRepository {
    protected ClanMemberRepositoryImpl() {
        super(ClanMember.class);
    }

    @Override
    public void deleteByTgId(Long tgId) {
        try {
            ClanMember clanMember = findByTgId(tgId);
            if (clanMember != null) {
                entityManager.remove(clanMember);
            }
        } catch (NoResultException e) {
            throw new PlayerNotMemberOfClanException("Player is not a member of any clan");
        } catch (Exception e) {
            throw new RuntimeException("Error deleting clan member", e);
        }
    }

    @Override
    public List<ClanMember> getMembersByClanName(String name) {
        TypedQuery<ClanMember> query = entityManager.createQuery("SELECT c FROM ClanMember c WHERE c.clan.name = :name", ClanMember.class);
        query.setParameter("name", name);
        return query.getResultList();
    }

    @Override
    public ClanMember findByTgId(Long tgId) {
        try{
            TypedQuery<ClanMember> query = entityManager.createQuery(
                    "SELECT c FROM ClanMember c WHERE c.player.tgId = :tgId", ClanMember.class);
            query.setParameter("tgId", tgId);
            return query.getSingleResult();
        }catch (NoResultException e){
            return null;
        }
    }

}
