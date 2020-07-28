package net.de1mos.microdiary.familyservice.web;

import lombok.RequiredArgsConstructor;
import net.de1mos.microdiary.familyservice.domain.commands.CreateNewFamilyCommand;
import net.de1mos.microdiary.familyservice.domain.projections.FamilyInfoProjection;
import net.de1mos.microdiary.familyservice.domain.queries.GetFamilyQuery;
import net.de1mos.microdiary.familyservice.services.FamilyService;
import net.de1mos.microdiary.familyservice.web.dto.FamilyInfoDto;
import net.de1mos.microdiary.familyservice.web.dto.SaveFamilyDto;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/family")
@RequiredArgsConstructor
public class FamilyController {

    private final CommandGateway commandGateway;
    private final FamilyService familyService;

    @PutMapping(value = "/{family_uuid}")
    public CompletableFuture<ResponseEntity<Void>> saveFamily(@RequestBody SaveFamilyDto dto,
                                                              @PathVariable("family_uuid") UUID familyUUID,
                                                              Authentication auth) {
        var cmd = CreateNewFamilyCommand.builder()
                .familyId(familyUUID.toString())
                .familyName(dto.getFamilyName())
                .memberId(UUID.randomUUID().toString())
                .userId(auth.getName())
                .build();
        var future = commandGateway.send(cmd);
        return future.thenApplyAsync(o -> ResponseEntity.noContent().build());
    }

    @GetMapping(value = "/{family_uuid}")
    public CompletableFuture<FamilyInfoDto> getFamily(@PathVariable("family_uuid") UUID familyUUID) {
        var future = familyService.getFamilyById(familyUUID.toString());
        return future.thenApplyAsync(FamilyInfoDto::fromProjection);
    }

}
