package net.de1mos.microdiary.familyservice.domain.commands;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class CreateNewFamilyCommand {
    private final String familyId;
    private final String familyName;

    private final String memberId;
    private final String userId;
}
