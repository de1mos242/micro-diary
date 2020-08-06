package net.de1mos.microdiary.familyservice.web;

import lombok.RequiredArgsConstructor;
import net.de1mos.microdiary.familyservice.services.FamilyService;
import net.de1mos.microdiary.familyservice.web.dto.FamilyInfoDto;
import net.de1mos.microdiary.familyservice.web.dto.SaveFamilyDto;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/family")
@RequiredArgsConstructor
public class FamilyController {

    private final FamilyService familyService;

    @PutMapping(value = "/{family_uuid}")
    public CompletableFuture<ResponseEntity<Void>> saveFamily(@RequestBody SaveFamilyDto dto,
                                                              @PathVariable("family_uuid") UUID familyUUID) {
        String userId = SecurityContextHolder.getContext().getAuthentication().getName();
        var future = familyService.saveFamily(familyUUID.toString(), dto.getFamilyName(), userId);
        return future.thenApplyAsync(o -> ResponseEntity.noContent().build());
    }

    @GetMapping(value = "/{family_uuid}")
    public CompletableFuture<FamilyInfoDto> getFamily(@PathVariable("family_uuid") UUID familyUUID) {
        var future = familyService.getFamilyById(familyUUID.toString());
        return future.thenApplyAsync(FamilyInfoDto::fromProjection);
    }

}
