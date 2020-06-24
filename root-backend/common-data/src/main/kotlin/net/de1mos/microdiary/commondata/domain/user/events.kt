package net.de1mos.microdiary.commondata.domain.user

import java.util.*

data class InternalUserPrepareCreationEvent(val userUUID: UUID, val login: String, val email: String, val password: String)