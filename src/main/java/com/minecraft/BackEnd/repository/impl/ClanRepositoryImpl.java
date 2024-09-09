package com.minecraft.BackEnd.repository.impl;

import com.minecraft.BackEnd.entities.Clan;
import com.minecraft.BackEnd.exceptions.ClanNotFoundException;
import com.minecraft.BackEnd.repository.AbstractBaseRepository;
import com.minecraft.BackEnd.repository.ClanRepository;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;

@Repository
public class ClanRepositoryImpl extends AbstractBaseRepository<Clan, Integer> implements ClanRepository {
    protected ClanRepositoryImpl() {
        super(Clan.class);
    }
    @Override
    public Clan findByName(String name) {
        try {
            TypedQuery<Clan> query = entityManager.createQuery("SELECT c FROM Clan c WHERE c.name = :name", Clan.class);
            query.setParameter("name", name);
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public Clan findClanByPlayerTgId(Long id) {
        try {
            TypedQuery<Clan> query = entityManager.createQuery(
                    "SELECT c FROM Clan c JOIN c.clanMembers.player p WHERE p.tgId = :id", Clan.class);
            query.setParameter("id", id);
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public void deleteByName(String name) {
        try {
            TypedQuery<Clan> query = entityManager.createQuery(
                    "SELECT c FROM Clan c WHERE c.name = :name", Clan.class);
            query.setParameter("name", name);
            Clan clan = query.getSingleResult();
            if (clan != null) {
                entityManager.remove(clan);
            }
        } catch (NoResultException e) {
            throw new ClanNotFoundException("Clan with name " + name + " not found");
        }
    }
}
