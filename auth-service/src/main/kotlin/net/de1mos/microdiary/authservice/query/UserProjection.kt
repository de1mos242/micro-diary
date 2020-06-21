package net.de1mos.microdiary.authservice.query

import net.de1mos.microdiary.authservice.domain.InternalUserCreatedEvent
import net.de1mos.microdiary.authservice.repositories.UserProjectionRepository
import net.de1mos.microdiary.authservice.views.InternalUserView
import org.axonframework.eventhandling.EventHandler
import org.axonframework.queryhandling.QueryHandler
import org.springframework.stereotype.Service

@Service
class UserProjection(private val repository: UserProjectionRepository) {

    @QueryHandler
    fun getAllUsers(query: GetAllUsersQuery): GetUsersListPageResponse {
        return GetUsersListPageResponse(repository.findAll(query.pageable))
    }

    @EventHandler
    fun on(event: InternalUserCreatedEvent) {
        repository.save(InternalUserView(event.userUUID, event.login, event.email, event.password))
    }
}