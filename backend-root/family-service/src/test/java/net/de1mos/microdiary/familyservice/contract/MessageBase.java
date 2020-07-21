package net.de1mos.microdiary.familyservice.contract;

import net.de1mos.microdiary.familyservice.ContractTestApplication;
import net.de1mos.microdiary.familyservice.domain.commands.CreateNewFamilyCommand;
import org.axonframework.commandhandling.CommandCallback;
import org.axonframework.commandhandling.GenericCommandMessage;
import org.axonframework.commandhandling.GenericCommandResultMessage;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.cloud.contract.verifier.messaging.boot.AutoConfigureMessageVerifier;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ContractTestApplication.class, webEnvironment = SpringBootTest.WebEnvironment.NONE)
@AutoConfigureMessageVerifier
@ActiveProfiles(value = "test")
public abstract class MessageBase {

    @MockBean
    CommandGateway commandGateway;

    @Before
    public void setUp() {
        CreateNewFamilyCommand cmd = new CreateNewFamilyCommand("123", "super family", "456", "789");
        Mockito.doAnswer(answer -> {
            CommandCallback<? super CreateNewFamilyCommand, ? super Object> arg = answer.getArgument(1);
            arg.onResult(GenericCommandMessage.asCommandMessage(cmd), GenericCommandResultMessage.asCommandResultMessage("OK"));
            return null;
        }).when(commandGateway).send(
                Mockito.eq(cmd),
                Mockito.any());
    }

}
