package com.avi.heroes.repository

import com.avi.heroes.entity.HeroEntity
import org.springframework.data.repository.CrudRepository

interface HeroJpaRepository : CrudRepository<HeroEntity, Long>