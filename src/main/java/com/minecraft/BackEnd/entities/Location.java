package com.minecraft.BackEnd.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "location")
public class Location extends BaseEntity{
    private float x;
    private float y;
    private float z;
    private Player player;

    protected Location() {
    }

    public Location(float x, float y, float z, Player player) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.player = player;
    }

    @Column(name = "location_x", nullable = false)
    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    @Column(name = "location_y", nullable = false)
    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    @Column(name = "location_z", nullable = false)
    public float getZ() {
        return z;
    }

    public void setZ(float z) {
        this.z = z;
    }

    @OneToOne
    @JoinColumn(name = "player_id")
    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }
}
