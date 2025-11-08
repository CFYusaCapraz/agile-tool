package com.cfyusacapraz.agiletool.api.constants;

public final class ApiEndpoints {

    public static final String RESPONSE_CONTENT_TYPE = "application/json";

    public static final String BASE_API_URL = "/api";
    public static final String AUTH_BASE_URL = BASE_API_URL + "/auth";
    public static final String USER_BASE_URL = BASE_API_URL + "/users";

    private ApiEndpoints() {
        throw new IllegalStateException("Utility class");
    }
}
