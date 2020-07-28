package net.de1mos.microdiary.familyservice.security;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@Component
public class AccessChecker {

    public boolean hasAccess(Authentication auth, String familyId) {
        return true;
    }
}
