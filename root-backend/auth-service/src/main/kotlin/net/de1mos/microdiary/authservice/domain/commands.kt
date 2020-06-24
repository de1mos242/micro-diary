package net.de1mos.microdiary.authservice.domain

import java.util.*

data class PrepareInternalUserCreationCommand(val userUUID: UUID, val login: String, val email: String, val password: String)

data class UserConfirmCreationCommand(val userUUID: UUID)

data class UserRejectCreationCommand(val userUUID: UUID)