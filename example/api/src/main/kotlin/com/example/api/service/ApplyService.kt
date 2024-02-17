package com.example.api.service

import com.example.api.domain.Coupon
import com.example.api.repository.CouponCountRepository
import com.example.api.repository.CouponRepository
import org.springframework.stereotype.Service

@Service
class ApplyService(
    private val couponRepository: CouponRepository,
    private val couponCountRepository: CouponCountRepository,
) {

    // 쿠폰 발급 로직
    fun apply(userId: Long) {
//        val count = couponRepository.count()
        // mysql말고 redis활용
        val count = couponCountRepository.increment() ?: throw RuntimeException("Null 발생")

        if (count > 100) {
            return;
        }

        couponRepository.save(Coupon(userId))
    }

}