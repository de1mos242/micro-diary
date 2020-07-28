package net.de1mos.microdiary.apigateway.messaging;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

public interface FamilyMessageProcessor {
    String SUCCESS_INPUT = "family.command.response";
    String FAILURE_INPUT = "family.command.error";

    @Input(SUCCESS_INPUT)
    SubscribableChannel successChannel();

    @Input(FAILURE_INPUT)
    SubscribableChannel failureChannel();

    @Output("family.command")
    MessageChannel commandChannel();
}
