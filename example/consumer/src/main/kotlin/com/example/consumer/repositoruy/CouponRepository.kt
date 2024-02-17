package com.example.consumer.repositoruy

import com.example.consumer.domain.Coupon
import org.springframework.data.jpa.repository.JpaRepository

interface CouponRepository : JpaRepository<Coupon, Long> {

}