package net.de1mos.microdiary.authservice.console

import net.de1mos.microdiary.authservice.domain.PrepareInternalUserCreationCommand
import net.de1mos.microdiary.authservice.query.GetAllUsersQuery
import net.de1mos.microdiary.authservice.query.GetUsersListPageResponse
import net.de1mos.microdiary.commondata.saga.user.CreateUserProfileCommand
import org.axonframework.commandhandling.gateway.CommandGateway
import org.axonframework.messaging.responsetypes.ResponseTypes
import org.axonframework.queryhandling.QueryGateway
import org.springframework.boot.CommandLineRunner
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import java.util.*
import java.util.concurrent.CompletableFuture

@Service
class Runner(private var commandGateway: CommandGateway, private val queryGateway: QueryGateway) : CommandLineRunner {


    override fun run(vararg args: String?) {
        println("Start sending command")
        val result: CompletableFuture<UUID> = commandGateway.send(PrepareInternalUserCreationCommand(UUID.randomUUID(), "fedor2", "fedor2@gmail.com", "qwerty"))
        println("Finish sending command")
        println(result.get())

        println("Start sending command 2")
        val result2: CompletableFuture<UUID> = commandGateway.send(PrepareInternalUserCreationCommand(UUID.randomUUID(), "fedor3", "fedor3@gmail.com", "qwerty"))
        println("Finish sending command 2")
        val uuid = result2.get()
        println(uuid)


        val query = queryGateway.query(GetAllUsersQuery(Pageable.unpaged()), ResponseTypes.instanceOf(GetUsersListPageResponse::class.java))
        val page = query.get()
        page.pageInfo.content.forEach(::println)

        println("send kafka")
        commandGateway.send<Void>(CreateUserProfileCommand(UUID.randomUUID(), "supername"))
        println("sent kafka")

    }
}