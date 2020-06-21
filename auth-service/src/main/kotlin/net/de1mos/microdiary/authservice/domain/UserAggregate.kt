package net.de1mos.microdiary.authservice.domain

import org.axonframework.commandhandling.CommandHandler
import org.axonframework.eventsourcing.EventSourcingHandler
import org.axonframework.modelling.command.AggregateIdentifier
import org.axonframework.modelling.command.AggregateLifecycle
import org.axonframework.modelling.command.AggregateMember
import org.axonframework.spring.stereotype.Aggregate
import org.springframework.security.crypto.password.PasswordEncoder
import java.util.*

enum class UserType(val type: String) {
    INTERNAL(VALUES.Internal),
    GOOGLE(VALUES.Google);

    object VALUES {
        const val Internal : String = "Internal"
        const val Google : String = "Google"
    }
}

@Aggregate
class UserAggregate {
    @AggregateIdentifier
    lateinit var userUUID: UUID

    lateinit var userType: UserType

    @AggregateMember
    var internalUser: InternalUser? = null

    protected constructor()

    @CommandHandler
    constructor(cmd: CreateInternalUserCommand, passwordEncoder: PasswordEncoder) {
        val password = passwordEncoder.encode(cmd.password)
        AggregateLifecycle.apply(InternalUserCreatedEvent(cmd.userUUID, cmd.login, cmd.email, password))
    }

    @EventSourcingHandler
    fun on(event: InternalUserCreatedEvent) {
        userUUID = event.userUUID
        userType = UserType.INTERNAL

        internalUser = InternalUser(event.login, event.email, event.password)
    }

}

open class InternalUser(val login: String, val email: String, val password: String) {

}