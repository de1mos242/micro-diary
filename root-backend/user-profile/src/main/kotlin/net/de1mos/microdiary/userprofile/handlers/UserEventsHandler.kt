package net.de1mos.microdiary.userprofile.handlers

import net.de1mos.microdiary.commondata.domain.user.InternalUserPrepareCreationEvent
import org.axonframework.config.ProcessingGroup
import org.axonframework.eventhandling.EventHandler
import org.springframework.stereotype.Service

@Service
@ProcessingGroup("kafka-group")
class UserEventsHandler {

    @EventHandler
    fun on(event: InternalUserPrepareCreationEvent) {
        println("Caught event $event")

    }
}