package net.de1mos.microdiary.familyservice.web.dto;

import lombok.Data;

@Data
public class CommonExceptionDto {
    private final ErrorCodes code;

    public enum ErrorCodes {
        INTERNAL,
        ACCESS_DENIED
    }
}
