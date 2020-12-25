package com.acme.tour.controller

import com.acme.tour.model.Promotion
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.util.concurrent.ConcurrentHashMap

@RestController
class PromotionController {

    @Autowired
    lateinit var promotionMap: ConcurrentHashMap<Long, Promotion>

    @GetMapping(value = ["/promotion/{id}"])
    fun getById(@PathVariable id: Long): Promotion? = promotionMap[id]


    @GetMapping(value = ["/promotion"])
    fun getAll(@RequestParam(required = false, defaultValue = "") localFilter: String)  =
        localFilter
            .takeIf(String::isNullOrBlank)
            ?.let { promotionMap.values }
            ?: promotionMap.filterValues { promotion -> promotion.local.contains(localFilter, true) }


    @PostMapping(value = ["/promotion"])
    fun create(@RequestBody promotion: Promotion) {
        promotionMap[promotion.id] = promotion
    }

    @PutMapping(value = ["/promotion/{id}"])
    fun update(@PathVariable id: Long, @RequestBody promotion: Promotion) {
        promotionMap.remove(id)
        promotionMap[id] = promotion
    }

    @DeleteMapping(value = ["/promotion/{id}"])
    fun delete(@PathVariable id: Long) = promotionMap.remove(id)

}