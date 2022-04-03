package com.avi.heroes.entity.extensions

import com.avi.heroes.dto.HeroDto
import com.avi.heroes.entity.HeroEntity

fun HeroEntity.toDto() = HeroDto(
  id = id!!,
  name = name,
  lastName = lastName,
  age = age,
  heroClass = heroClass,
)