package net.de1mos.microdiary.authservice.repositories

import net.de1mos.microdiary.authservice.views.UserView
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface UserProjectionRepository : JpaRepository<UserView, UUID> {
    override fun findAll(pageable: Pageable): Page<UserView>
}