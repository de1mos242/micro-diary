package net.de1mos.microdiary.authservice.views

import net.de1mos.microdiary.authservice.domain.UserType
import java.util.*
import javax.persistence.*

@Entity
@DiscriminatorValue(UserType.VALUES.Internal)
data class InternalUserView(
        override val userUUID: UUID,

        @Column(name = "login")
        val login: String,

        @Column(name = "email")
        val email: String,

        @Column(name = "password")
        val password: String
) : UserView(userUUID)