package com.cfyusacapraz.agiletool.api.constants;

import java.util.List;

public final class ParamConstants {

    public static final int DEFAULT_PAGE_NUMBER = 0;
    public static final int DEFAULT_PAGE_SIZE = 20;
    public static final String DEFAULT_SORT_RULE = "id-";

    public static final List<String> DEFAULT_SORT_RULES = List.of(DEFAULT_SORT_RULE);

    private ParamConstants() {
        throw new IllegalStateException("Utility class");
    }
}
