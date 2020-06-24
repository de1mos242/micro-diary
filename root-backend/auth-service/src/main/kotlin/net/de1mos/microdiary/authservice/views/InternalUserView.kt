package net.de1mos.microdiary.authservice.views

import net.de1mos.microdiary.commondata.domain.user.UserType
import java.util.*
import javax.persistence.Column
import javax.persistence.DiscriminatorValue
import javax.persistence.Entity

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