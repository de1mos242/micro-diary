package net.de1mos.microdiary.authservice.sagas

import net.de1mos.microdiary.authservice.domain.PrepareInternalUserCreationCommand
import net.de1mos.microdiary.authservice.domain.UserRejectCreationCommand
import net.de1mos.microdiary.commondata.saga.user.CreateUserProfileCommand
import net.de1mos.microdiary.commondata.saga.user.InternalUserCreatedEvent
import net.de1mos.microdiary.commondata.saga.user.InternalUserCreationFailedEvent
import org.axonframework.commandhandling.gateway.CommandGateway
import org.axonframework.modelling.saga.SagaEventHandler
import org.axonframework.modelling.saga.StartSaga
import org.axonframework.spring.stereotype.Saga
import org.springframework.beans.factory.annotation.Autowired
import java.util.*

data class InternalUserCreationRequestedEvent(
        val userUUID: UUID,
        val login: String,
        val email: String,
        val password: String,
        val name: String
)

@Saga
class CreateInternalUserSaga {
    private lateinit var userUUID: UUID
    private lateinit var login: String
    private lateinit var email: String
    private lateinit var password: String
    private lateinit var name: String

    @Autowired
    private lateinit var commandGateway: CommandGateway

    @StartSaga
    @SagaEventHandler(associationProperty = "userUUID")
    fun handle(event: InternalUserCreationRequestedEvent) {
        userUUID = event.userUUID
        login = event.login
        email = event.email
        password = event.password
        name = event.name

        commandGateway.send<UUID>(PrepareInternalUserCreationCommand(userUUID, login, email, password))
    }

    @SagaEventHandler(associationProperty = "userUUID")
    fun handle(event: InternalUserCreatedEvent) {
        commandGateway.send<Void>(CreateUserProfileCommand(userUUID, name))
    }

    @SagaEventHandler(associationProperty = "userUUID")
    fun handle(event: InternalUserCreationFailedEvent) {
        commandGateway.send<Void>(UserRejectCreationCommand(userUUID))
    }


}