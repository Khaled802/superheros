package com.example.superheros.antiHero.dto;

import com.example.superheros.antiHero.entity.AntiHero;

import lombok.Value;

@Value
public class patchAntiHeroDto {
    private String firstName;
    private String lastName;
    private String house;
    private String knownAs;

    public AntiHero getAntiHeroEntity() {
        return new AntiHero(null, firstName, lastName, house, knownAs, null);
    }
}
