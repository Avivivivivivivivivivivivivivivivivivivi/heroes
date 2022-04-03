package com.avi.heroes

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class HeroesApplication

fun main(args: Array<String>) {
	runApplication<HeroesApplication>(*args)
}
