package net.de1mos.microdiary.familyservice.messaging;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

public interface FamilyMessageProcessor {
    String INPUT = "family.command";

    @Input(INPUT)
    SubscribableChannel commandChannel();

    @Output("family.command.response")
    MessageChannel successChannel();

    @Output("family.command.error")
    MessageChannel errorChannel();
}
