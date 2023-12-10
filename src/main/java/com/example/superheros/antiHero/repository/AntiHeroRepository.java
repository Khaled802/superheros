package com.example.superheros.antiHero.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.superheros.antiHero.entity.AntiHero;

@Repository
public interface AntiHeroRepository  extends JpaRepository<AntiHero, UUID> {
    
}
