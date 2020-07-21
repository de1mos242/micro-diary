package net.de1mos.microdiary.familyservice.domain.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.axonframework.modelling.command.EntityId;

@Data
@AllArgsConstructor
public class FamilyMember {
    @EntityId
    private String memberId;

    private String userId;
}
