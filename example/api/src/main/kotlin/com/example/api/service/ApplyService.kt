package com.example.api.service

import com.example.api.domain.Coupon
import com.example.api.repository.CouponRepository
import org.springframework.stereotype.Service

@Service
class ApplyService(
    private val couponRepository: CouponRepository,
) {

    // 쿠폰 발급 로직
    fun apply(userId: Long) {
        val count = couponRepository.count()

        if (count > 100) {
            return;
        }

        couponRepository.save(Coupon(userId))
    }

}