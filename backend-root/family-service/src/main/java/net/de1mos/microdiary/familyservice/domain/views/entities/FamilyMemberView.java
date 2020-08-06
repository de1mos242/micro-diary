package net.de1mos.microdiary.familyservice.domain.views.entities;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Data
@Entity
@Table(name = "family_member_view")
public class FamilyMemberView {

    @Id
    @Column
    private UUID id;

    @Column
    private UUID userId;

    @Column
    private UUID familyId;
}
