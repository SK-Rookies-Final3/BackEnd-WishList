package com.wishlist.dto;

public class ShortsRequest {
    private String shortsCode;

    // 생성자
    public ShortsRequest(String shortsCode) {
        this.shortsCode = shortsCode;
    }

    // getter, setter
    public String getShortsCode() {
        return shortsCode;
    }

    public void setShortsCode(String shortsCode) {
        this.shortsCode = shortsCode;
    }
}
