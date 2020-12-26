package com.acme.tour.advice

import com.acme.tour.model.JsonResponse
import com.fasterxml.jackson.core.JsonParseException
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@ControllerAdvice
class ErrorHandler {
    @ExceptionHandler(JsonParseException::class)
    fun jsonBadRequestHandler(servletRequest: HttpServletRequest, servletResponse: HttpServletResponse, exception: Exception)
            : ResponseEntity<JsonResponse> {
        return ResponseEntity(JsonResponse.BAD_REQUEST.apply { description = exception.message }, JsonResponse.BAD_REQUEST.httpStatus)
    }

}