package com.cfyusacapraz.agiletool.properties;

public final class PropertiesPrefixConstants {

    public static final String BASE_PREFIX = "agiletool.";
    public static final String SEED_ADMIN_PREFIX = BASE_PREFIX + "seed-admin";
    public static final String JWT_PREFIX = BASE_PREFIX + "jwt";

    private PropertiesPrefixConstants() {
        throw new IllegalStateException("Utility class");
    }
}
