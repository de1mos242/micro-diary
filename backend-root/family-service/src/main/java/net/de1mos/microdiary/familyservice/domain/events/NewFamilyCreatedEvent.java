package net.de1mos.microdiary.familyservice.domain.events;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class NewFamilyCreatedEvent {
    private final String familyId;
    private final String familyName;
    private final String memberId;
    private final String userId;
}
