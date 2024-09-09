package com.minecraft.BackEnd.repository;

import com.minecraft.BackEnd.entities.Location;

public interface LocationRepository {

    Location findByTgId(Long id);

}
