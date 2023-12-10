package com.example.superheros.antiHero.dto;

import java.util.UUID;

import com.example.superheros.antiHero.entity.AntiHero;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Value;

@Value
public class AntiHeroDto {
    @NotNull(message = "should provide id")
    private UUID id;

    @NotNull(message = "should provide firstName")
    @NotBlank
    private String firstName;
    
    private String lastName;
    private String house;
    private String knownAs;

    public AntiHeroDto(AntiHero antiHero) {
        id = antiHero.getId();
        firstName = antiHero.getFirstName();
        lastName = antiHero.getLastName();
        house = antiHero.getHouse();
        knownAs = antiHero.getKnownAs();
    }

}
