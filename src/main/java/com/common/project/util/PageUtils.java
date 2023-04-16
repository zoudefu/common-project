package com.common.project.util;

import java.util.Map;

public class PageUtils {

    private static final String PAGE = "page";
    private static final String LIMIT = "limit";
    private static final String ORDER_BY_FIELD = "orderByField";
    private static final String IS_ASC = "isAsc";

    public static int getSize(Map<String, Object> params) {
        return Integer.parseInt(params.getOrDefault(LIMIT, 10).toString());
    }

    public static int getCurrent(Map<String, Object> params) {
        return Integer.parseInt(params.getOrDefault(PAGE, 1).toString());
    }

    public static boolean isAsc(Map<String, Object> params) {
        return Boolean.parseBoolean(params.getOrDefault(IS_ASC, Boolean.TRUE).toString());
    }

    public static String getOrderByField(Map<String, Object> params) {
        return params.getOrDefault(ORDER_BY_FIELD, "").toString();
    }

}
