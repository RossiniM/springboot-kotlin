package com.acme.tour.repository

import com.acme.tour.model.Promotion
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface PromotionRepository : JpaRepository<Promotion, Long>