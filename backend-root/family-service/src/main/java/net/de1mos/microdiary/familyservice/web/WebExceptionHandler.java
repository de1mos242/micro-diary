package net.de1mos.microdiary.familyservice.web;

import net.de1mos.microdiary.familyservice.web.dto.CommonExceptionDto;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.*;

@RestControllerAdvice
public class WebExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public CommonExceptionDto handle(Exception e) {
        return new CommonExceptionDto(CommonExceptionDto.ErrorCodes.INTERNAL);
    }

    @ExceptionHandler(AccessDeniedException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public CommonExceptionDto handle(AccessDeniedException e) {
        return new CommonExceptionDto(CommonExceptionDto.ErrorCodes.ACCESS_DENIED);
    }
}
