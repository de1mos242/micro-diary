package net.de1mos.microdiary.familyservice.contract;

import io.restassured.module.mockmvc.RestAssuredMockMvc;
import net.de1mos.microdiary.familyservice.ContractRestTestApplication;
import net.de1mos.microdiary.familyservice.domain.commands.CreateNewFamilyCommand;
import net.de1mos.microdiary.familyservice.domain.projections.FamilyInfoProjection;
import net.de1mos.microdiary.familyservice.services.FamilyService;
import net.de1mos.microdiary.familyservice.web.FamilyController;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
import org.springframework.security.authentication.TestingAuthenticationToken;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.context.WebApplicationContext;

import java.security.Principal;
import java.util.Collections;
import java.util.concurrent.CompletableFuture;

import static org.mockito.ArgumentMatchers.argThat;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ContractRestTestApplication.class)
@AutoConfigureWireMock(port = 0)
@WebAppConfiguration
public abstract class RestBase {

    @MockBean
    CommandGateway commandGateway;

    @MockBean
    FamilyService familyService;

    @Autowired
    private FamilyController familyController;

    @Autowired
    private WebApplicationContext context;

    @Before
    public void setUp() {
        RestAssuredMockMvc.authentication = RestAssuredMockMvc.authentication(
                new TestingAuthenticationToken((Principal) () -> "3f0d8412-e15a-4d6b-bf90-7e83c1e4193e",
                        null));
//        RestAssuredMockMvc.standaloneSetup(familyController);
        RestAssuredMockMvc.webAppContextSetup(context);


        Mockito.when(commandGateway.send(argThat((CreateNewFamilyCommand c) -> c.getFamilyId().equals("0dc796e6-e86a-4eea-ae52-a8c748ef57f3"))))
                .thenReturn(CompletableFuture.completedFuture("OK"));

        Mockito.doReturn(CompletableFuture.completedFuture(
                FamilyInfoProjection.builder()
                        .familyId("0dc796e6-e86a-4eea-ae52-a8c748ef57f3")
                        .familyName("super family")
                        .babies(Collections.singletonList(
                                new FamilyInfoProjection.BabyProjection(
                                        "be9b4211-a97e-4481-9829-e1e48101056c",
                                        "kovalsky")))
                        .members(Collections.singletonList(
                                new FamilyInfoProjection.MemberProjection("3f0d8412-e15a-4d6b-bf90-7e83c1e4193e")))
                        .build()
        )).when(familyService).getFamilyById("0dc796e6-e86a-4eea-ae52-a8c748ef57f3");
    }


}
