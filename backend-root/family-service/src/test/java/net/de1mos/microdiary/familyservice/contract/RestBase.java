package net.de1mos.microdiary.familyservice.contract;

import io.restassured.module.mockmvc.RestAssuredMockMvc;
import net.de1mos.microdiary.familyservice.ContractRestTestApplication;
import net.de1mos.microdiary.familyservice.domain.projections.FamilyInfoProjection;
import net.de1mos.microdiary.familyservice.services.FamilyService;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.TestingAuthenticationToken;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.test.web.servlet.setup.MockMvcConfigurer;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.Filter;
import java.security.Principal;
import java.util.Collections;
import java.util.concurrent.CompletableFuture;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ContractRestTestApplication.class)
@AutoConfigureWireMock(port = 0)
@WebAppConfiguration
@WithMockUser(username = "admin")
public abstract class RestBase {

    @MockBean
    FamilyService familyService;

    @MockBean
    CommandGateway commandGateway;

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private Filter springSecurityFilterChain;

    @Mock
    private Principal mockedPrincipal;


    @Before
    public void setUp() {
        RestAssuredMockMvc.authentication = RestAssuredMockMvc.principal(mockedPrincipal);

        MockMvc mockMvc = MockMvcBuilders.webAppContextSetup(context)
//                .apply(SecurityMockMvcConfigurers.springSecurity(springSecurityFilterChain))
                .build();
        RestAssuredMockMvc.mockMvc(mockMvc);

        Mockito.when(familyService.saveFamily(
                eq("0dc796e6-e86a-4eea-ae52-a8c748ef57f3"),
                eq("super family"),
                eq("admin")))
                .thenReturn(CompletableFuture.completedFuture(null));

        Mockito.doThrow(new AccessDeniedException("Access denied"))
                .when(familyService).getFamilyById("45e8d220-fae8-4e43-bb9b-218ba024a469");

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
