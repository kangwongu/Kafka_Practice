package com.example.api.service

import com.example.api.repository.CouponRepository
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class ApplyServiceTest @Autowired constructor(
    private val applyService: ApplyService,
    private val couponRepository: CouponRepository,
) {

    @DisplayName("한번만 응모")
    @Test
    fun applyOne() {
        // given
        applyService.apply(1L);

        // when
        val result = couponRepository.count()

        // then
        Assertions.assertThat(result).isEqualTo(1)
    }
}