package com.acme.tour.services

import com.acme.tour.model.Promotion
import org.springframework.stereotype.Component
import java.util.concurrent.ConcurrentHashMap

@Component
class PromotionServicesImpl : PromotionServices {

    companion object {
        val initialPromotion = arrayListOf(
            Promotion(1, "notal", "loja do mecanico", true, 5, 500),
            Promotion(2, "notal", "loja do mecanico", true, 10, 1000),
            Promotion(3, "notal", "loja do mecanico", true, 6, 500),
            Promotion(4, "notal", "loja do mecanico", true, 7, 800)
        )
    }

    var promotionMap = ConcurrentHashMap(initialPromotion.associateBy(Promotion::id))

    override fun getById(id: Long): Promotion? = promotionMap[id]

    override fun create(promotion: Promotion) {
        promotionMap[promotion.id] = promotion
    }

    override fun update(id: Long, promotion: Promotion) {
        promotionMap.remove(id)
        promotionMap[id] = promotion
    }

    override fun delete(id: Long) = promotionMap.remove(id)

    override fun searchByLocal(localFilter: String): Collection<Promotion> =
        localFilter
            .takeIf(String::isNullOrBlank)
            ?.let { promotionMap.values }
            ?: promotionMap
                .filterValues { promotion -> promotion.local.contains(localFilter, true) }
                .values

}