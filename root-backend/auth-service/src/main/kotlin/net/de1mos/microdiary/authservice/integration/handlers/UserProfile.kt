package net.de1mos.microdiary.authservice.integration.handlers

import net.de1mos.microdiary.commondata.saga.user.AnotherObject
import net.de1mos.microdiary.commondata.saga.user.CreateUserProfileCommand
import net.de1mos.microdiary.commondata.saga.user.SomeObject
import org.axonframework.commandhandling.CommandHandler
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.kafka.support.SendResult
import org.springframework.stereotype.Component
import org.springframework.util.concurrent.ListenableFutureCallback
import java.time.Instant
import java.util.*



@Component
class UserProfile(private val kafkaTemplate: KafkaTemplate<String, AnotherObject>,
                  private val kafkaTemplate2: KafkaTemplate<String, SomeObject>) {

    @CommandHandler
    fun handle(cmd: CreateUserProfileCommand) {
        val result = kafkaTemplate.send("super-puper-topic", "my-key-what?", AnotherObject("my-mega-data"))
        result.addCallback(object : ListenableFutureCallback<SendResult<String, AnotherObject>> {
            override fun onSuccess(result: SendResult<String, AnotherObject>?) {
                println("kafka get success result: $result")
            }

            override fun onFailure(ex: Throwable) {
                println("kafka get failure result: $ex")
            }

        })
        val result2 = kafkaTemplate2.send("super-puper-topic", "my-key-what?", SomeObject("Garry", Instant.now()))
        result2.addCallback(object : ListenableFutureCallback<SendResult<String, SomeObject>> {
            override fun onSuccess(result: SendResult<String, SomeObject>?) {
                println("kafka 2 get success result: $result")
            }

            override fun onFailure(ex: Throwable) {
                println("kafka 2 get failure result: $ex")
            }

        })
        val r = result2.get()
        println("Here is result: $r")
    }
}