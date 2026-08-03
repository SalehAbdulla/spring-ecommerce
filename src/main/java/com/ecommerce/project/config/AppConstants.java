package com.ecommerce.project.config;

import java.util.Set;

public class AppConstants {
    public static final String PAGE_NUMBER = "0";
    public static final String PAGE_SIZE = "10";
    public static final String SORT_BY = "id";
    public static final String SORT_ORDER = "asc";

    public static final int MAX_IMAGE_SIZE = 5 *1024*1024;
    public static final Set<String> ALLOWED_TYPES = Set.of("image/jpeg", "image/png", "image/gif", "image/webp");
}
