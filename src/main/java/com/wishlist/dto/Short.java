package com.wishlist.dto;

public class Short {
    private String shortsCode;
    private String shortsThumbnail; // 숏츠 썸네일 URL
    private String shortsUrl; // 숏츠 URL

    // getter, setter
    public String getShortsCode() {
        return shortsCode;
    }

    public void setShortsCode(String shortsCode) {
        this.shortsCode = shortsCode;
    }

    public String getShortsThumbnail() {
        return shortsThumbnail;
    }

    public void setShortsThumbnail(String shortsThumbnail) {
        this.shortsThumbnail = shortsThumbnail;
    }

    public String getShortsUrl() {
        return shortsUrl;
    }

    public void setShortsUrl(String shortsUrl) {
        this.shortsUrl = shortsUrl;
    }
}
