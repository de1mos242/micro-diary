package net.de1mos.microdiary.familyservice.messaging.commands;

import lombok.Data;

@Data
public class CreateFamilyCommand {
    private final String familyId;
    private final String familyName;

    private final String memberId;
    private final String userId;
}
