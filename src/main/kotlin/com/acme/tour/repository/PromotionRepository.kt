package com.acme.tour.repository

import com.acme.tour.model.Promotion
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface PromotionRepository : PagingAndSortingRepository<Promotion, Long> {

    @Query(value = "select  p from Promotion p  where p.price <=:price")
    fun findByPriceLessThan(@Param(value = "price") price: Double): Collection<Promotion>
}
