package com.minecraft.BackEnd.repository.impl;

import com.minecraft.BackEnd.entities.Player;
import com.minecraft.BackEnd.entities.Role;
import com.minecraft.BackEnd.repository.AbstractBaseRepository;
import com.minecraft.BackEnd.repository.RoleRepository;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;

@Repository
public class RoleRepositoryImpl extends AbstractBaseRepository<Role, Integer> implements RoleRepository {
    protected RoleRepositoryImpl() {
        super(Role.class);
    }

    @Override
    public Role findByName(String name) {
        try {
            TypedQuery<Role> query = entityManager.createQuery("SELECT r FROM Role r WHERE r.name = :name", Role.class);
            query.setParameter("name", name);
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
}
