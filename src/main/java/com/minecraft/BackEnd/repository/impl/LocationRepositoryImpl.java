package com.minecraft.BackEnd.repository.impl;

import com.minecraft.BackEnd.entities.Location;
import com.minecraft.BackEnd.repository.AbstractBaseRepository;
import com.minecraft.BackEnd.repository.LocationRepository;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;

@Repository
public class LocationRepositoryImpl extends AbstractBaseRepository<Location, Integer> implements LocationRepository {
    protected LocationRepositoryImpl() {
        super(Location.class);
    }

    @Override
    public Location findByTgId(Long id) {
        try {
            TypedQuery<Location> query = entityManager.createQuery("SELECT l FROM Location l JOIN l.player p WHERE p.tgId = :id", Location.class);
            query.setParameter("id", id);
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

}
