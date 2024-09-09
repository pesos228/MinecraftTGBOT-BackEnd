package com.minecraft.BackEnd.repository.impl;

import com.minecraft.BackEnd.entities.Player;
import com.minecraft.BackEnd.repository.AbstractBaseRepository;
import com.minecraft.BackEnd.repository.PlayerRepository;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;


@Repository
public class PlayerRepositoryImpl extends AbstractBaseRepository<Player, Integer> implements PlayerRepository {
    protected PlayerRepositoryImpl() {
        super(Player.class);
    }

    @Override
    public Player findByTgId(Long id) {
        try {
            TypedQuery<Player> query = entityManager.createQuery("SELECT p FROM Player p WHERE p.tgId = :id", Player.class);
            query.setParameter("id", id);
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public Player findByUsername(String username) {
        try {
            TypedQuery<Player> query = entityManager.createQuery("SELECT p FROM Player p WHERE p.username = :username", Player.class);
            query.setParameter("username", username);
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }


}
