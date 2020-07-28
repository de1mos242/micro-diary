package net.de1mos.microdiary.familyservice.web.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class SaveFamilyDto {
    @NotBlank(message = "Family name is mandatory")
    String familyName;
}
