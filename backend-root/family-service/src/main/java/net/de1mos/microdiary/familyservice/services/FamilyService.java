package net.de1mos.microdiary.familyservice.services;

import lombok.RequiredArgsConstructor;
import net.de1mos.microdiary.familyservice.domain.projections.FamilyInfoProjection;
import net.de1mos.microdiary.familyservice.domain.queries.GetFamilyQuery;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
@RequiredArgsConstructor
public class FamilyService {

    private final CommandGateway commandGateway;
    private final QueryGateway queryGateway;

    @PreAuthorize("@accessChecker.hasAccess(authentication, #familyId)")
    public CompletableFuture<FamilyInfoProjection> getFamilyById(String familyId) {
        var query = new GetFamilyQuery(familyId);
        return queryGateway.query(query, FamilyInfoProjection.class);
    }
}
