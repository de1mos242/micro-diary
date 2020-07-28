package net.de1mos.microdiary.familyservice.web.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Builder;
import lombok.Data;
import net.de1mos.microdiary.familyservice.domain.projections.FamilyInfoProjection;

import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
//@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class FamilyInfoDto {

    private final String familyId;
    private final String familyName;
    private final List<MemberInfoDto> members;
    private final List<BabyInfoDto> babies;

    @Data
//    @JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
    static class MemberInfoDto {
        private final String userId;
    }

    @Data
//    @JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
    static class BabyInfoDto {
        private final String babyId;
        private final String babyName;
    }

    public static FamilyInfoDto fromProjection(FamilyInfoProjection projection) {
        return FamilyInfoDto.builder()
                .familyId(projection.getFamilyId())
                .familyName(projection.getFamilyName())
                .members(projection.getMembers().stream()
                        .map(m -> new MemberInfoDto(m.getUserId()))
                        .collect(Collectors.toList()))
                .babies(projection.getBabies().stream()
                        .map(b -> new BabyInfoDto(b.getBabyId(), b.getBabyName()))
                        .collect(Collectors.toList()))
                .build();
    }
}
