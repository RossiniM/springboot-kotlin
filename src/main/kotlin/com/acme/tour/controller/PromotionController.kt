package com.acme.tour.controller

import com.acme.tour.model.JsonResponse
import com.acme.tour.model.JsonResponse.*
import com.acme.tour.model.Promotion
import com.acme.tour.services.PromotionServices
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class PromotionController {

    @Autowired
    lateinit var promotionServices: PromotionServices

    @GetMapping(value = ["/promotion/{id}"])
    fun getById(@PathVariable id: Long): ResponseEntity<Promotion?> {
        val promotion = promotionServices.getById(id)
        val status = promotion?.run { HttpStatus.OK } ?: HttpStatus.NOT_FOUND
        return ResponseEntity(promotion, status)
    }

    @GetMapping(value = ["/promotion"])
    fun getAll(
        @RequestParam(required = false,
            defaultValue = "") localFilter: String,
    ): ResponseEntity<Collection<Promotion>> {
        val promotionCollection = promotionServices.searchByLocal(localFilter)
        return ResponseEntity<Collection<Promotion>>(promotionCollection, HttpStatus.OK)
    }

    @PostMapping(value = ["/promotion"])
    fun create(@RequestBody promotion: Promotion): ResponseEntity<JsonResponse> {
        promotionServices.create(promotion)
        return ResponseEntity(CREATED, CREATED.httpStatus)

    }

    @PutMapping(value = ["/promotion/{id}"])
    fun update(@PathVariable id: Long, @RequestBody promotion: Promotion): ResponseEntity<JsonResponse> {
        val response = promotionServices.getById(id)?.let { ACCEPTED } ?: NOT_FOUND
        promotionServices.update(id, promotion)
        return ResponseEntity(response, response.httpStatus)
    }

    @DeleteMapping(value = ["/promotion/{id}"])
    fun delete(@PathVariable id: Long): ResponseEntity<JsonResponse> {
        val response = promotionServices.delete(id)?.let { DELETED } ?: NOT_FOUND
        return ResponseEntity(response, response.httpStatus)
    }
}
