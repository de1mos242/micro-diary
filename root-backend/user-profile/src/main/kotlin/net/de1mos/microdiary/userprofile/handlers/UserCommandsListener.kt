package net.de1mos.microdiary.userprofile.handlers

import net.de1mos.microdiary.commondata.saga.user.AnotherObject
import net.de1mos.microdiary.commondata.saga.user.SomeObject
import org.springframework.kafka.annotation.KafkaHandler
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.messaging.handler.annotation.Payload
import org.springframework.stereotype.Service

@KafkaListener(topics = ["super-puper-topic"])
@Service
class UserCommandsListener {

    @KafkaHandler
    fun handleCommands(@Payload message: AnotherObject) {
        println("========================caught command==========================")
        println(message)
    }

    @KafkaHandler
    fun handleCommands(@Payload message: SomeObject) {
        println("========================caught command 2==========================")
        println(message)
    }
}