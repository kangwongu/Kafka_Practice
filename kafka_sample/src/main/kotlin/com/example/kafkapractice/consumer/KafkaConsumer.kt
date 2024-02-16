package com.example.kafkapractice.consumer

import com.example.kafkapractice.MyMessage
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Component

@Component
class KafkaConsumer(
    val objectMapper: ObjectMapper = ObjectMapper()
) {

    @KafkaListener(topics = [TOPIC_NAME])
    fun listenMessage(jsonMessage: String) {
        try {
            val message = objectMapper.readValue(jsonMessage, MyMessage::class.java)
            println(">>> ${message.name}, ${message.message} <<<")
        } catch (e: Exception) {
            e.stackTrace
        }
    }

    companion object {
        const val TOPIC_NAME = "topic"
    }
}