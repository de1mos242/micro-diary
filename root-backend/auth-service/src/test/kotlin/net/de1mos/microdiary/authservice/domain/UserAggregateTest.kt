@file:Suppress("DEPRECATION")

package net.de1mos.microdiary.authservice.domain

import net.de1mos.microdiary.commondata.domain.user.InternalUserPrepareCreationEvent
import net.de1mos.microdiary.commondata.domain.user.UserType
import org.axonframework.test.aggregate.AggregateTestFixture
import org.axonframework.test.aggregate.FixtureConfiguration
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.springframework.security.crypto.password.DelegatingPasswordEncoder
import org.springframework.security.crypto.password.NoOpPasswordEncoder
import java.util.*

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
internal class UserAggregateTest {

    private var fixture: FixtureConfiguration<UserAggregate> = AggregateTestFixture(UserAggregate::class.java)

    init {
        fixture.registerInjectableResource(
                DelegatingPasswordEncoder("noop",
                        mapOf("noop" to NoOpPasswordEncoder.getInstance())))
    }

    @Test
    fun `Test create new internal user`() {
        val userUUID = UUID.randomUUID()
        val login = "fedor"
        val email = "fedor@gmail.com"
        val password = "qwerty"
        val encodedPassword = "{noop}qwerty"
        fixture.given()
                .`when`(PrepareInternalUserCreationCommand(userUUID, login, email, password))
                .expectSuccessfulHandlerExecution()
                .expectEvents(InternalUserPrepareCreationEvent(userUUID, login, email, encodedPassword))
                .expectState {
                    assertEquals(userUUID, it.userUUID)
                    assertEquals(UserType.INTERNAL, it.userType)
                    assertNotNull(it.internalUser)
                    assertEquals(login, it.internalUser!!.login)
                    assertEquals(email, it.internalUser!!.email)
                    assertEquals(encodedPassword, it.internalUser!!.password)
                }
    }
}