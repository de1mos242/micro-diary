package net.de1mos.microdiary.authservice.query

import net.de1mos.microdiary.authservice.views.UserView
import org.springframework.data.domain.Page

data class GetUsersListPageResponse(val pageInfo: Page<UserView>)