package com.test.melnyk.internetshop.util;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

public final class ParamUtil {

    public static Map<String, String[]> getParamMap(HttpServletRequest httpServletRequest) {
        Map<String, String[]> origin = httpServletRequest.getParameterMap();
        Map<String, String[]> params = new HashMap<>();

        if (origin.get("numberOfItems") != null) {
            params.put("numberOfItems", origin.get("numberOfItems"));
        }
        return params;
    }
}
