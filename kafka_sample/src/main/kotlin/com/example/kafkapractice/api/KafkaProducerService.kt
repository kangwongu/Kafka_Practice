package com.example.kafkapractice.api

import com.example.kafkapractice.MyMessage
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Service

@Service
class KafkaProducerService(
    private val kafkaTemplate: KafkaTemplate<String, String>,
    private val newKafkaTemplate: KafkaTemplate<String, MyMessage>,
) {

    fun sendJson(message: MyMessage) {
        newKafkaTemplate.send(TOPIC_NAME, message)
    }

    fun send(message: String) {
        kafkaTemplate.send(TOPIC_NAME, message)
    }

    companion object {
        const val TOPIC_NAME = "topic"
    }

}