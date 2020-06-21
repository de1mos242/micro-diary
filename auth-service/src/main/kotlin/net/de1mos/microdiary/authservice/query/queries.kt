package net.de1mos.microdiary.authservice.query

import org.springframework.data.domain.Pageable
import java.util.*

data class GetAllUsersQuery(val pageable: Pageable)

data class GetUserInfo(val userUUID: UUID)