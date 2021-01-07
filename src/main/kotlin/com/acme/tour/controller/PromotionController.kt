package com.acme.tour.controller

import com.acme.tour.model.JsonResponse
import com.acme.tour.model.JsonResponse.*
import com.acme.tour.model.Promotion
import com.acme.tour.model.PromotionPriceUpdateDTO
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
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(value = ["/promotion"])
class PromotionController {

    @Autowired
    lateinit var promotionServices: PromotionServices

    @GetMapping(value = ["/{id}"])
    fun getById(@PathVariable id: Long): ResponseEntity<Promotion?> {
        val promotion = promotionServices.getById(id)
        val status = promotion?.run { HttpStatus.OK } ?: HttpStatus.NOT_FOUND
        return ResponseEntity(promotion, status)
    }

    @GetMapping()
    fun getAll(
        @RequestParam(required = false, defaultValue = "0") start: Int,
        @RequestParam(required = false, defaultValue = "3") size: Int,
    ): ResponseEntity<Collection<Promotion>> {
        val promotionCollection = promotionServices.getAll(start, size)
        return ResponseEntity<Collection<Promotion>>(promotionCollection, HttpStatus.OK)
    }

    @GetMapping(value = ["/sortedByLocal"])
    fun getAllSortedByLocal(
        @RequestParam(required = false, defaultValue = "0") start: Int,
        @RequestParam(required = false, defaultValue = "3") size: Int,
    ): ResponseEntity<Collection<Promotion>> {
        val promotionCollection = promotionServices.getAllSortedByLocal(start, size)
        return ResponseEntity<Collection<Promotion>>(promotionCollection, HttpStatus.OK)
    }

    @GetMapping(value = ["/lessThan"])
    fun getAllLessThan9000(
        @RequestParam(required = false, defaultValue = "0") price: Double,
    ): ResponseEntity<Collection<Promotion>> {
        val promotionCollection = promotionServices.findByPriceLessThan(price)
        return ResponseEntity<Collection<Promotion>>(promotionCollection, HttpStatus.OK)
    }

    @PostMapping
    fun create(@RequestBody promotion: Promotion): ResponseEntity<JsonResponse> {
        promotionServices.create(promotion)
        return ResponseEntity(CREATED, CREATED.httpStatus)

    }

    @PutMapping(value = ["/{id}"])
    fun update(@PathVariable id: Long, @RequestBody promotion: Promotion): ResponseEntity<JsonResponse> {
        val response = promotionServices.getById(id)?.let { ACCEPTED } ?: NOT_FOUND
        promotionServices.update(id, promotion)
        return ResponseEntity(response, response.httpStatus)
    }

    @DeleteMapping(value = ["/{id}"])
    fun delete(@PathVariable id: Long): ResponseEntity<JsonResponse> {
        val response = promotionServices.delete(id).let { DELETED }
        return ResponseEntity(response, response.httpStatus)
    }

    @GetMapping(value = ["/count"])
    fun count(): ResponseEntity<Map<String, Long>> = ResponseEntity.status(HttpStatus.OK).body(mapOf("count" to promotionServices.count()))

    @PutMapping(value = ["/updatePriceByLocal"])
    fun updatePriceByLocal(@RequestBody dto:PromotionPriceUpdateDTO): ResponseEntity<JsonResponse> {
        val (price, local) = dto
        promotionServices.updatePriceByLocal(price,local)
        return ResponseEntity(ACCEPTED, ACCEPTED.httpStatus)

    }

}
