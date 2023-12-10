package com.example.superheros.antiHero.service;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.UUID;

import com.example.superheros.antiHero.entity.AntiHero;

public interface AntiHeroService {
    List<AntiHero> findAll();
    AntiHero findById(UUID uuid);
    void removeById(UUID uuid);
    AntiHero insert(AntiHero antiHero);
    AntiHero update(UUID uuid, AntiHero antiHero) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException;
}
