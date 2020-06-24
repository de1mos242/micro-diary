package net.de1mos.microdiary.userprofile.domain

import org.axonframework.modelling.command.AggregateIdentifier
import org.axonframework.spring.stereotype.Aggregate
import java.util.*

@Aggregate
class UserProfile {
    @AggregateIdentifier
    lateinit var userUUID: UUID

    lateinit var name: String

    var avatarUrl: String? = null


}