package com.minecraft.BackEnd.entities;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "clan")
public class Clan extends BaseEntity {
    private String name;
    private Set<ClanMember> clanMembers = new HashSet<>();

    protected Clan() {
    }

    public Clan(String name) {
        this.name = name;
    }

    @Column(name = "name", nullable = false, unique = true)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @OneToMany(mappedBy = "clan", cascade = CascadeType.ALL, orphanRemoval = true)
    public Set<ClanMember> getClanMembers() {
        return clanMembers;
    }

    public void setClanMembers(Set<ClanMember> clanMembers) {
        this.clanMembers = clanMembers;
    }
}
