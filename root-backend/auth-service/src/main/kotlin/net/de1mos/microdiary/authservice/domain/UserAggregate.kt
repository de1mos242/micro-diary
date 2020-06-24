package net.de1mos.microdiary.authservice.domain

import net.de1mos.microdiary.commondata.domain.user.InternalUserPrepareCreationEvent
import net.de1mos.microdiary.commondata.domain.user.UserState
import net.de1mos.microdiary.commondata.domain.user.UserType
import org.axonframework.commandhandling.CommandHandler
import org.axonframework.eventsourcing.EventSourcingHandler
import org.axonframework.modelling.command.AggregateIdentifier
import org.axonframework.modelling.command.AggregateLifecycle
import org.axonframework.modelling.command.AggregateMember
import org.axonframework.spring.stereotype.Aggregate
import org.springframework.security.crypto.password.PasswordEncoder
import java.util.*


@Aggregate
class UserAggregate {
    @AggregateIdentifier
    lateinit var userUUID: UUID

    lateinit var userType: UserType

    lateinit var userState: UserState

    @AggregateMember
    var internalUser: InternalUser? = null

    protected constructor()

    @CommandHandler
    constructor(cmd: PrepareInternalUserCreationCommand, passwordEncoder: PasswordEncoder) {
        val password = passwordEncoder.encode(cmd.password)
        AggregateLifecycle.apply(InternalUserPrepareCreationEvent(cmd.userUUID, cmd.login, cmd.email, password))
    }

    @EventSourcingHandler
    fun on(event: InternalUserPrepareCreationEvent) {
        userUUID = event.userUUID
        userType = UserType.INTERNAL
        userState = UserState.CREATE_PENDING

        internalUser = InternalUser(event.login, event.email, event.password)
    }

    @CommandHandler
    fun handle(cmd: UserRejectCreationCommand) {

    }

}

open class InternalUser(val login: String, val email: String, val password: String) {

}