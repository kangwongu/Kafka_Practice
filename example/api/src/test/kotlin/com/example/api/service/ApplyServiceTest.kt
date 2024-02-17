package com.example.api.service

import com.example.api.repository.CouponRepository
import org.assertj.core.api.Assertions
import org.assertj.core.api.Assertions.*
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import java.util.concurrent.CountDownLatch
import java.util.concurrent.Executors

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
        assertThat(result).isEqualTo(1)
    }

    @DisplayName("여러명 응모")
    @Test
    fun applyMulti() {
        // given
        val threadCount = 1000;     // 1000개의 요청
        val executorService = Executors.newFixedThreadPool(32)
        val latch = CountDownLatch(threadCount)

        // when, 동시에 1000개의 쿠폰발급요청
        for (i in 1 .. threadCount) {
            val userId = i.toLong()
            executorService.submit {
                try {
                applyService.apply(userId)
                } finally {
                    latch.countDown()
                }
            }
        }
        latch.await()

        Thread.sleep(5000)

        // then
        val result = couponRepository.count()
        assertThat(result).isEqualTo(100)
    }
}