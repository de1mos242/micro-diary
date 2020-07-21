package net.de1mos.microdiary.familyservice.domain.commands;

import lombok.Data;

@Data
public class CreateNewFamilyCommand {
    private final String familyId;
    private final String familyName;

    private final String memberId;
    private final String userId;
}
