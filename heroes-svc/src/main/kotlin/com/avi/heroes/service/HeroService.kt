package com.avi.heroes.service

import com.avi.heroes.controller.dto.HeroRequest
import com.avi.heroes.controller.dto.extensions.toHeroEntity
import com.avi.heroes.controller.exception.EntityNotFoundException
import com.avi.heroes.entity.HeroEntity
import com.avi.heroes.entity.extensions.toDto
import com.avi.heroes.repository.HeroJpaRepository
import org.springframework.stereotype.Service

@Service
class HeroService(val heroJpaRepository: HeroJpaRepository) {
  fun save(newHero: HeroRequest) = heroJpaRepository.save(newHero.toHeroEntity())

  fun getHeroes() = heroJpaRepository.findAll().map(HeroEntity::toDto)

  fun getHeroById(id: Long): HeroEntity =
    heroJpaRepository.findById(id).orElseThrow { EntityNotFoundException("Hero with id = $id could not be found") }

  fun deleteHero(id: Long) = heroJpaRepository.delete(heroJpaRepository.findById(id)
    .orElseThrow { EntityNotFoundException("Hero with id = $id could not be found") })
}