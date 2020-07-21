package net.de1mos.microdiary.familyservice.domain.models;

import lombok.Data;
import lombok.NoArgsConstructor;
import net.de1mos.microdiary.familyservice.domain.commands.CreateNewFamilyCommand;
import net.de1mos.microdiary.familyservice.domain.events.NewFamilyCreatedEvent;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.modelling.command.AggregateMember;
import org.axonframework.spring.stereotype.Aggregate;

import java.util.ArrayList;
import java.util.List;

@Aggregate
@Data
@NoArgsConstructor
public class Family {

    @AggregateIdentifier
    private String familyId;
    private String familyName;

    @AggregateMember
    private List<FamilyMember> members = new ArrayList<>();

    @CommandHandler
    public Family(CreateNewFamilyCommand cmd) {
        NewFamilyCreatedEvent event = new NewFamilyCreatedEvent(cmd.getFamilyId(),
                cmd.getFamilyName(),
                cmd.getMemberId(),
                cmd.getUserId());
        AggregateLifecycle.apply(event);
    }

    @EventSourcingHandler
    public void on(NewFamilyCreatedEvent event) {
        familyId = event.getFamilyId();
        familyName = event.getFamilyName();

        members.add(new FamilyMember(event.getMemberId(), event.getUserId()));
    }

}
