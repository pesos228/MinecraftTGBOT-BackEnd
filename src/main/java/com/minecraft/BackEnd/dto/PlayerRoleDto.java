package com.minecraft.BackEnd.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PlayerRoleDto {
    private Long tgId;
    private String username;
    private String role_name;
}
