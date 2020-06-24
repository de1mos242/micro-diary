package net.de1mos.microdiary.userprofile

import com.fasterxml.jackson.annotation.JsonAutoDetect
import com.fasterxml.jackson.annotation.PropertyAccessor
import com.fasterxml.jackson.databind.ObjectMapper
import org.axonframework.config.EventProcessingConfigurer
import org.axonframework.extensions.kafka.eventhandling.consumer.streamable.StreamableKafkaMessageSource
import org.axonframework.serialization.Serializer
import org.axonframework.serialization.json.JacksonSerializer
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration


@SpringBootApplication
class UserProfileApplication

fun main(args: Array<String>) {
    runApplication<UserProfileApplication>(*args)
}

@Configuration
class TrackingConfiguration {
    @Autowired
    fun configureStreamableKafkaSource(configurer: EventProcessingConfigurer,
                                       streamableKafkaMessageSource: StreamableKafkaMessageSource<String, ByteArray>) {
        configurer.registerTrackingEventProcessor("kafka-group") { streamableKafkaMessageSource }
    }
}

@Qualifier("mapper")
@Bean
fun objectMapper(): ObjectMapper? {
    return ObjectMapper()
            .setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY)
}

@Qualifier("messageSerializer")
@Bean
fun messageSerializer(@Qualifier("mapper") objectMapper: ObjectMapper?): Serializer? {
    return JacksonSerializer.builder().objectMapper(objectMapper).build()
}
