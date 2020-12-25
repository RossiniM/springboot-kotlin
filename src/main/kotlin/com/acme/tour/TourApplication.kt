package com.acme.tour

import com.acme.tour.model.Promotion
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import java.util.concurrent.ConcurrentHashMap

@SpringBootApplication
class TourApplication {
    companion object {
        val initialPromotion = arrayListOf(
            Promotion(1, "notal", "loja do mecanico", true, 5, 500),
            Promotion(2, "notal", "loja do mecanico", true, 10, 1000),
            Promotion(3, "notal", "loja do mecanico", true, 6, 500),
            Promotion(4, "notal", "loja do mecanico", true, 7, 800)
        )
    }
	@Bean
	fun promotion() = ConcurrentHashMap(initialPromotion.associateBy(Promotion::id))

}

fun main(args: Array<String>) {
    runApplication<TourApplication>(*args)
}
