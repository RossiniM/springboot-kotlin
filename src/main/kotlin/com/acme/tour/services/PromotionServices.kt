package com.acme.tour.services

import com.acme.tour.model.Promotion

interface PromotionServices {
    fun getById(id: Long): Promotion?
    fun create(promotion: Promotion)
    fun update(id: Long, promotion: Promotion)
    fun delete(id: Long): Promotion?
    fun searchByLocal(local: String): Collection<Promotion>
}