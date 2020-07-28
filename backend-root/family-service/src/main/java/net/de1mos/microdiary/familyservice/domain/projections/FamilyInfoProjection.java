package net.de1mos.microdiary.familyservice.domain.projections;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class FamilyInfoProjection {

    private final String familyId;
    private final String familyName;
    private final List<MemberProjection> members;
    private final List<BabyProjection> babies;

    @Data
    public static class MemberProjection {
        private final String userId;
    }

    @Data
    public static class BabyProjection {
        private final String babyId;
        private final String babyName;
    }
}
