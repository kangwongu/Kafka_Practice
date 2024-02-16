package com.example.kafkapractice.api

import com.example.kafkapractice.MyMessage
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class ProducerController(
    private val kafkaProducerService: KafkaProducerService
) {

    @RequestMapping("/publish")
    fun publish(message: String): String {
        kafkaProducerService.send(message)
        return "published a message : $message"
    }

    @RequestMapping("/publish/json")
    fun publishJson(message: MyMessage): String {
        kafkaProducerService.sendJson(message)
        return "published a message : ${message.name}, ${message.message}"
    }
}