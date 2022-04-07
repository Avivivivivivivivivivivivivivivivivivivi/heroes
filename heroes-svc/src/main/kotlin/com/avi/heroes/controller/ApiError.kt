package com.avi.heroes.controller

import org.springframework.http.HttpStatus


class ApiError (
  val status: HttpStatus,
  val message: String,
  val errors: List<String> = arrayListOf(),
){
  constructor(status: HttpStatus, message: String, error: String): this (status, message, arrayListOf(error))
}