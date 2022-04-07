package com.avi.heroes.configuration

import com.avi.heroes.controller.ApiError
import com.avi.heroes.controller.exception.EntityNotFoundException
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.MissingServletRequestParameterException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.context.request.WebRequest
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler


@ControllerAdvice
class CustomRestExceptionHandler : ResponseEntityExceptionHandler() {

  val logger: Logger = LoggerFactory.getLogger(this.javaClass)

  override fun handleMethodArgumentNotValid(
    ex: MethodArgumentNotValidException,
    headers: HttpHeaders,
    status: HttpStatus,
    request: WebRequest,
  ): ResponseEntity<Any> {
    val errors: MutableList<String> = ArrayList()
    for (error in ex.bindingResult.fieldErrors) {
      errors.add("${error.field}: ${error.defaultMessage}")
    }
    for (error in ex.bindingResult.globalErrors) {
      errors.add("${error.objectName}: ${error.defaultMessage}")
    }

    val apiError = ApiError(HttpStatus.BAD_REQUEST, ex.localizedMessage, errors)
    return handleExceptionInternal(
      ex, apiError, headers, apiError.status, request)
  }

  override fun handleMissingServletRequestParameter(
    ex: MissingServletRequestParameterException,
    headers: HttpHeaders,
    status: HttpStatus,
    request: WebRequest,
  ): ResponseEntity<Any> {
    val error = "${ex.parameterName} parameter is missing"

    val apiError = ApiError(HttpStatus.BAD_REQUEST, ex.localizedMessage, error)
    return ResponseEntity(
      apiError, HttpHeaders(), apiError.status)
  }

  @ExceptionHandler(EntityNotFoundException::class)
  fun handleConstraintViolation(
    ex: EntityNotFoundException, request: WebRequest?,
  ): ResponseEntity<Any?>? {
    val apiError = ApiError(HttpStatus.NOT_FOUND, ex.localizedMessage)
    logger.error("Requested entity was not found", ex)
    return ResponseEntity(
      apiError, HttpHeaders(), apiError.status)
  }

  override fun handleHttpMessageNotReadable(
    ex: HttpMessageNotReadableException,
    headers: HttpHeaders,
    status: HttpStatus,
    request: WebRequest,
  ): ResponseEntity<Any> {
    val apiError = ApiError(HttpStatus.BAD_REQUEST, ex.localizedMessage)
    return ResponseEntity(
      apiError, HttpHeaders(), apiError.status)
  }
}