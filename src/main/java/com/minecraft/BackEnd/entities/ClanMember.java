package com.minecraft.BackEnd.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "clan_member")
public class ClanMember extends BaseEntity{
    private Player player;
    private Clan clan;
    private Role role;

    protected ClanMember() {
    }

    @ManyToOne
    @JoinColumn(name = "player_id", nullable = false)
    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    @ManyToOne
    @JoinColumn(name = "clan_id", nullable = false)
    public Clan getClan() {
        return clan;
    }

    public void setClan(Clan clan) {
        this.clan = clan;
    }

    @ManyToOne
    @JoinColumn(name = "role_id", nullable = false)
    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
