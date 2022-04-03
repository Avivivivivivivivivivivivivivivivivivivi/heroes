package com.avi.heroes.controller.dto.extensions

import com.avi.heroes.controller.dto.HeroRequest
import com.avi.heroes.entity.HeroEntity

fun HeroRequest.toHeroEntity() = HeroEntity(
  name = name,
  lastName = lastName,
  age = age,
  heroClass = heroClass,
)