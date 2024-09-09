package com.minecraft.BackEnd.controller;

import com.minecraft.BackEnd.dto.ClanDto;
import com.minecraft.BackEnd.dto.ClanMemberDto;
import com.minecraft.BackEnd.service.ClanMemberService;
import com.minecraft.BackEnd.service.ClanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clan")
public class ClanController {

    private final ClanService clanService;
    private final ClanMemberService clanMemberService;

    @Autowired
    public ClanController(ClanService clanService, ClanMemberService clanMemberService) {
        this.clanService = clanService;
        this.clanMemberService = clanMemberService;
    }

    @PostMapping
    public void save(@RequestBody ClanDto clanDto){
        clanService.save(clanDto);
    }

    @GetMapping
    public List<ClanDto> clanDtoList(){
        return clanService.findAll();
    }

    @DeleteMapping
    public void deleteClan(@RequestBody ClanDto clanDto){
        clanService.deleteClan(clanDto.getName());
    }

    @PostMapping("/addMember")
    public void addMember(@RequestBody ClanMemberDto clanMemberDto){
        clanMemberService.addMember(clanMemberDto);
    }

    @PostMapping("/removeMember")
    public void removeMember(@RequestBody ClanMemberDto clanMemberDto){
        clanMemberService.removeMember(clanMemberDto);
    }

    @GetMapping("/{name}/members")
    public List<ClanMemberDto> getMembersClan(@PathVariable String name){
        return clanMemberService.getMembersByClanName(name);
    }

    @GetMapping("/player/{tgId}")
    public ClanMemberDto getClanByPlayerTgId(@PathVariable Long tgId) {
        return clanMemberService.findClanByTgId(tgId);
    }
}
