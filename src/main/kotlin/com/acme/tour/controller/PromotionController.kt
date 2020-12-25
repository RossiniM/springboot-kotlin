package com.acme.tour.controller

import com.acme.tour.model.Promotion
import com.acme.tour.services.PromotionServices
import org.springframework.beans.factory.annotation.Autowired
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
    fun getById(@PathVariable id: Long): Promotion? = promotionServices.getById(id)

    @GetMapping(value = ["/promotion"])
    fun getAll(@RequestParam(required = false, defaultValue = "") localFilter: String) =
        promotionServices.searchByLocal(localFilter)

    @PostMapping(value = ["/promotion"])
    fun create(@RequestBody promotion: Promotion) = promotionServices.create(promotion)

    @PutMapping(value = ["/promotion/{id}"])
    fun update(@PathVariable id: Long, @RequestBody promotion: Promotion) = promotionServices.update(id, promotion)

    @DeleteMapping(value = ["/promotion/{id}"])
    fun delete(@PathVariable id: Long) = promotionServices.delete(id)

}