package com.wishlist.util;

import jakarta.servlet.http.HttpServletRequest;

public class HeaderUtils {

    // HTTP 요청 헤더에서 userId를 추출하는 유틸리티 메서드
    public static String getUserIdFromHeaders(HttpServletRequest request) {
        return request.getHeader("X-User-Id");
    }
}
