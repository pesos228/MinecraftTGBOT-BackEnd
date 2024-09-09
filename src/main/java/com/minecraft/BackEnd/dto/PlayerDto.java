package com.minecraft.BackEnd.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PlayerDto {
    private String username;
    private Long tgId;
    private Long balance;
    private boolean status;
}
