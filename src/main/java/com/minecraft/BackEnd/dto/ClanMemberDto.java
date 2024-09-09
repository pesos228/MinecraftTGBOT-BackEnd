package com.minecraft.BackEnd.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClanMemberDto {
    private Long tgId;
    private String clan_name;
    private String role_name;
}
