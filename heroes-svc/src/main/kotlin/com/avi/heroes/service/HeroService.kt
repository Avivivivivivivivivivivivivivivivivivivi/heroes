package com.avi.heroes.service

import com.avi.heroes.controller.dto.HeroRequest
import com.avi.heroes.controller.dto.extensions.toHeroEntity
import com.avi.heroes.dto.HeroDto
import com.avi.heroes.entity.HeroEntity
import com.avi.heroes.entity.extensions.toDto
import com.avi.heroes.repository.HeroJpaRepository
import org.springframework.stereotype.Service

@Service
class HeroService(val heroJpaRepository: HeroJpaRepository) {
  fun save(newHero: HeroRequest) {
    heroJpaRepository.save(newHero.toHeroEntity())
  }

  fun getHeroes(): List<HeroDto> {
    return heroJpaRepository.findAll().map(HeroEntity::toDto)
  }
}