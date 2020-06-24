package net.de1mos.microdiary.commondata.domain.user

enum class UserType(val type: String) {
    INTERNAL(VALUES.Internal),
    GOOGLE(VALUES.Google);

    object VALUES {
        const val Internal: String = "INTERNAL"
        const val Google: String = "GOOGLE"
    }
}

enum class UserState(val state: String) {
    CREATE_PENDING("CREATE_PENDING"),
    ACTIVE("ACTIVE"),
    INACTIVE("INACTIVE"),
    BLOCKED("BLOCKED");
}