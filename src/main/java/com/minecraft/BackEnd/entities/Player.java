package com.minecraft.BackEnd.entities;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "player")
public class Player extends BaseEntity {

    private String username;
    private Long tgId;
    private Long balance;
    private Location location;
    private boolean status;
    private Set<ClanMember> clanMembers = new HashSet<>();

    protected Player() {
    }

    public Player(String username, Long tgId, Long balance, boolean status) {
        this.username = username;
        this.tgId = tgId;
        this.balance = balance;
        this.status = status;
    }

    @Column(name = "username", nullable = false, unique = true)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Column(name = "balance")
    public Long getBalance() {
        return balance;
    }

    public void setBalance(Long balance) {
        this.balance = balance;
    }

    @Column(name = "tg_id", nullable = false, unique = true)
    public Long getTgId() {
        return tgId;
    }

    public void setTgId(Long tgId) {
        this.tgId = tgId;
    }

    @OneToOne(mappedBy = "player")
    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    @Column(name = "is_online")
    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @OneToMany(mappedBy = "player", cascade = CascadeType.ALL, orphanRemoval = true)
    public Set<ClanMember> getClanMembers() {
        return clanMembers;
    }

    public void setClanMembers(Set<ClanMember> clanMembers) {
        this.clanMembers = clanMembers;
    }
}
