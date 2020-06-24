package net.de1mos.microdiary.commondata.saga.user

import java.util.*

data class InternalUserCreationRequestedEvent(
        val userUUID: UUID,
        val login: String,
        val email: String,
        val password: String,
        val name: String
)

data class InternalUserCreatedEvent(val userUUID: UUID)

data class InternalUserCreationFailedEvent(val userUUID: UUID, val reason: String)

data class UserProfileCreatedEvent(val userUUID: UUID)

data class UserProfileCreationFailedEvent(val userUUID: UUID, val reason: String)