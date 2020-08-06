package net.de1mos.microdiary.familyservice.domain.views.entities;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Data
@Entity
@Table(name = "family_view")
public class FamilyView {

    @Id
    @Column
    private UUID id;

    @Column
    private String name;
}
