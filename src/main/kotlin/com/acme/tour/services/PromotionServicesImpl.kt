package com.acme.tour.services

import com.acme.tour.model.Promotion
import com.acme.tour.repository.PromotionRepository
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Component

@Component
class PromotionServicesImpl(val promotionRepository: PromotionRepository) : PromotionServices {

    override fun getById(id: Long): Promotion? = promotionRepository.findById(id).orElse(null)

    override fun create(promotion: Promotion) {
        promotionRepository.save(promotion)
    }

    override fun update(id: Long, promotion: Promotion) = create(promotion)

    override fun delete(id: Long) = promotionRepository.delete(Promotion(id = id))

    override fun getAll(start: Int, size: Int): Collection<Promotion> = promotionRepository.findAll(PageRequest.of(start, size)).toList()

    override fun count(): Long = promotionRepository.count()

}