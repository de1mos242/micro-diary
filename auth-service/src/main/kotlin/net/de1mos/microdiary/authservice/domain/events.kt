package net.de1mos.microdiary.authservice.domain

import java.util.*

data class InternalUserCreatedEvent(val userUUID: UUID, val login: String, val email: String, val password: String)