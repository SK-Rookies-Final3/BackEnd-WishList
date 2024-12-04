package com.wishlist.db;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShortsCode {

    @Id
    private String shortsCode;  // 숏츠 코드

    private String shortsThumbnail;  // 숏츠 썸네일 URL

    private String shortsLink;  // 숏츠 링크

    // Getter and Setter
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

    public String getShortsLink() {
        return shortsLink;
    }

    public void setShortsLink(String shortsLink) {
        this.shortsLink = shortsLink;
    }
}
