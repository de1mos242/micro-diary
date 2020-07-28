package net.de1mos.microdiary.familyservice.messaging;

import lombok.RequiredArgsConstructor;
import net.de1mos.microdiary.familyservice.domain.commands.CreateNewFamilyCommand;
import net.de1mos.microdiary.familyservice.messaging.commands.CreateFamilyCommand;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class FamilyCommandListener {
    private final FamilyMessageProcessor processor;
    private final CommandGateway commandGateway;

    @StreamListener(value = FamilyMessageProcessor.INPUT, condition = "headers['type']=='CreateFamilyCommandMessage'")
    public void CreateFamilyCommandHandler(CreateFamilyCommand cmd) {
        var command = CreateNewFamilyCommand.builder()
                .familyId(cmd.getFamilyId())
                .familyName(cmd.getFamilyName())
                .memberId(cmd.getMemberId())
                .userId(cmd.getUserId())
                .build();
        commandGateway.send(command, (message, resultMessage) -> {
            if (resultMessage.isExceptional()) {
                processor.errorChannel().send(MessageBuilder.withPayload(resultMessage.exceptionResult().getMessage()).build());
            } else {
                processor.successChannel().send(MessageBuilder.withPayload("ok").build());
            }
        });
    }

}
