package net.de1mos.microdiary.authservice.views

import java.util.*
import javax.persistence.*

@Entity
@Table(name = "users_view")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "user_type")
abstract class UserView (
        @Id
        @Column(name = "user_uuid")
        open val userUUID: UUID
)