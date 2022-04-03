package com.avi.heroes.controller

import com.avi.heroes.controller.dto.HeroRequest
import com.avi.heroes.service.HeroService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/hero")
class HeroController (val heroService: HeroService){

  @PostMapping
  fun createHero(@RequestBody newHero: HeroRequest) = heroService.save(newHero)

  @GetMapping
  fun getHeroes() = heroService.getHeroes()

}