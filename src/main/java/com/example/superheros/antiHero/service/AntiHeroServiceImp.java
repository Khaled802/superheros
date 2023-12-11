package com.example.superheros.antiHero.service;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.UUID;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.example.superheros.antiHero.entity.AntiHero;
import com.example.superheros.antiHero.repository.AntiHeroRepository;
import com.example.superheros.exception.NotFoundException;
import com.example.superheros.util.Merger;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@AllArgsConstructor
@Slf4j
public class AntiHeroServiceImp implements AntiHeroService {

    private AntiHeroRepository antiHeroRepository;

    @Override
    @Cacheable(value = "AntiHero")
    public List<AntiHero> findAll() {
        return antiHeroRepository.findAll();
    }

    @Override
    @Cacheable(value = "AntiHero", key = "#uuid")
    public AntiHero findById(UUID uuid) {
        return antiHeroRepository.findById(uuid)
                .orElseThrow(() -> new NotFoundException("antiHero is not found with id: " + uuid));
    }

    @Override
    @CacheEvict(value = "AntiHero", key = "#uuid", allEntries = true)
    public void removeById(UUID uuid) {
        antiHeroRepository.deleteById(uuid);
    }

    @Override
    @Transactional
    public AntiHero insert(AntiHero antiHero) {
        return antiHeroRepository.save(antiHero);
    }

    @Override
    @CachePut(value = "AntiHero", key = "#uuid")
    public AntiHero update(UUID uuid, AntiHero antiHero) throws InstantiationException, IllegalAccessException,
            IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
        AntiHero antiHeroOriginal = antiHeroRepository.findById(uuid).orElse(null);
        if (antiHeroOriginal == null)
            throw new NotFoundException("antiHero is not found with id: " + uuid);

        AntiHero updatedAntiHero = new Merger<AntiHero>().merge(antiHeroOriginal, antiHero);
        log.info("updated: {}", updatedAntiHero);
        return antiHeroRepository.save(updatedAntiHero);
    }

    public boolean isFound(UUID uuid) {
        return antiHeroRepository.existsById(uuid);
    }

}
