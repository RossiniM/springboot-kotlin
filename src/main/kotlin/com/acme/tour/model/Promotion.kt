package com.acme.tour.model

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "TBL_PROMOTION")
data class Promotion(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 1,
    val description: String = "",
    val local: String = "",
    val isAllInclusive: Boolean = true,
    val amountDays: Int = 1,
    val price: Double = 0.0,
)
