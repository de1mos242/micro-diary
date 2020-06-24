package net.de1mos.microdiary.commondata.saga.user

import java.time.Instant
import java.util.*

data class CreateUserProfileCommand(val userUUID: UUID, val name: String)

data class SomeObject(val name: String, val date: Instant)
data class AnotherObject(val data: String)