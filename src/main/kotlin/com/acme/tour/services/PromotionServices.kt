package com.acme.tour.services

import com.acme.tour.model.Promotion

interface PromotionServices {
    fun getById(id: Long): Promotion?
    fun create(promotion: Promotion)
    fun update(id: Long, promotion: Promotion)
    fun delete(id: Long)
    fun getAll(start: Int, size: Int): Collection<Promotion>
    fun getAllSortedByLocal(start: Int, size: Int): Collection<Promotion>
    fun count(): Long
    fun findByPriceLessThan(price: Double): Collection<Promotion>
    fun updatePriceByLocal(newPrice:Double, local:String)
}