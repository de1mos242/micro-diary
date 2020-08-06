package net.de1mos.microdiary.familyservice.services;

import net.de1mos.microdiary.familyservice.BaseTestApplication;
import net.de1mos.microdiary.familyservice.domain.commands.CreateNewFamilyCommand;
import net.de1mos.microdiary.familyservice.domain.projections.FamilyInfoProjection;
import net.de1mos.microdiary.familyservice.domain.queries.GetFamilyQuery;
import net.de1mos.microdiary.familyservice.security.AccessChecker;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.queryhandling.QueryGateway;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = BaseTestApplication.class)
class FamilyServiceTest {

    @MockBean
    QueryGateway queryGateway;
    @MockBean
    CommandGateway commandGateway;
    @Autowired
    FamilyService familyService;
    @MockBean
    AccessChecker accessChecker;

    @Test
    @WithMockUser
    void getFamilyById() {
        String familyId = UUID.randomUUID().toString();
        when(accessChecker.hasAccess(any(), any())).thenReturn(true);
        familyService.getFamilyById(familyId);
        verify(queryGateway).query(new GetFamilyQuery(familyId), FamilyInfoProjection.class);
    }

    @Test
    @WithMockUser
    void getFamilyByIdAccessDenied() {
        when(accessChecker.hasAccess(any(), any())).thenReturn(false);
        Assert.assertThrows(AccessDeniedException.class, () -> familyService.getFamilyById("any"));
    }

    @Test
    @WithMockUser
    void saveFamily() {
        when(accessChecker.hasAccess(any(), any())).thenReturn(true);
        String familyId = UUID.randomUUID().toString();
        String familyName = "super family";
        String userId = UUID.randomUUID().toString();
        familyService.saveFamily(familyId, familyName, userId);
        verify(commandGateway).send(argThat((CreateNewFamilyCommand c) ->
                familyId.equals(c.getFamilyId()) && familyName.equals(c.getFamilyName()) && userId.equals(c.getUserId())
        ));
    }

    @Test
    @WithMockUser
    void saveFamilyAccessDenied() {
        when(accessChecker.hasAccess(any(), any())).thenReturn(false);
        Assert.assertThrows(AccessDeniedException.class, () -> familyService.saveFamily("any", "any", "any"));
    }
}