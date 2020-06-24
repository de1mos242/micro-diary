package net.de1mos.microdiary.authservice.query

import net.de1mos.microdiary.authservice.repositories.UserProjectionRepository
import net.de1mos.microdiary.authservice.views.InternalUserView
import net.de1mos.microdiary.commondata.domain.user.InternalUserPrepareCreationEvent
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
    fun on(event: InternalUserPrepareCreationEvent) {
        repository.save(InternalUserView(event.userUUID, event.login, event.email, event.password))
    }
}