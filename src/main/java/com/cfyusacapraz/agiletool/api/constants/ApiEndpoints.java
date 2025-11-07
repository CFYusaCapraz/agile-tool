package com.cfyusacapraz.agiletool.api.constants;

public final class ApiEndpoints {

    public static final String BASE_API_URL = "/api";
    public static final String AUTH_BASE_URL = BASE_API_URL + "/auth";

    private ApiEndpoints() {
        throw new IllegalStateException("Utility class");
    }
}
