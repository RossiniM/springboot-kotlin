package com.acme.tour.model

import com.fasterxml.jackson.annotation.JsonFormat
import org.springframework.http.HttpStatus
import java.util.Date

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
enum class JsonResponse(val message: String = "", val date: Date = Date(), val httpStatus: HttpStatus, var description: String? = null) {
    OK("OK", httpStatus = HttpStatus.OK),
    ACCEPTED("ACCEPTED", httpStatus = HttpStatus.ACCEPTED),
    DELETED("The Object was deleted", httpStatus = HttpStatus.OK),
    CREATED("The Object was created", httpStatus = HttpStatus.CREATED),
    UPDATE("The object was updated", httpStatus = HttpStatus.ACCEPTED),
    NOT_FOUND("The object was not found", httpStatus = HttpStatus.NOT_FOUND),
    BAD_REQUEST("Bad request ", httpStatus = HttpStatus.BAD_REQUEST);


}
