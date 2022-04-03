package com.avi.heroes.entity

import javax.persistence.*

@Entity
@Table(name = "hero")
class HeroEntity(
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "hero_generator")
  @SequenceGenerator(name = "hero_generator", sequenceName = "hero_seq")
  val id: Long? = null,

  @Column(name = "hero_name")
  val name: String,

  val lastName: String,

  val age: Int,

  val heroClass: String,
)