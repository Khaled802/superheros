package com.example.superheros.antiHero.controller;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.superheros.antiHero.dto.AntiHeroDto;
import com.example.superheros.antiHero.dto.AntiHeroInfoDto;
import com.example.superheros.antiHero.dto.patchAntiHeroDto;
import com.example.superheros.antiHero.service.AntiHeroService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/anti_heros")
@AllArgsConstructor
public class AntiHeroController {
    private AntiHeroService antiHeroService;

    @GetMapping
    public List<AntiHeroDto> getAllAntiHero() {
        return antiHeroService.findAll().stream().map((antiHero) -> new AntiHeroDto(antiHero)).toList();
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public AntiHeroDto createAntiHero(@Valid @RequestBody AntiHeroInfoDto antiHeroInfoDto) {
        return new AntiHeroDto(antiHeroService.insert(antiHeroInfoDto.getAntiHeroEntity()));
    }

    @GetMapping("/{uuid}")
    public AntiHeroDto getAntiHeroById(@PathVariable("uuid") UUID uuid) {
        return new AntiHeroDto(antiHeroService.findById(uuid));
    }

    @PutMapping("/{uuid}")
    public AntiHeroDto updateAntiHero(@PathVariable UUID uuid, @Valid @RequestBody patchAntiHeroDto patchAntiHeroDto)
            throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException,
            NoSuchMethodException, SecurityException {
        return new AntiHeroDto(antiHeroService.update(uuid, patchAntiHeroDto.getAntiHeroEntity()));
    }

    @DeleteMapping("/{uuid}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAntiHero(@PathVariable UUID uuid) {
        antiHeroService.removeById(uuid);
    }
}
