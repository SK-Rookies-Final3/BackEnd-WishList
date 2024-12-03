package com.wishlist.db;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class ProductCode {

    @Id
    private String productCode;  // 상품 코드

    private String productThumbnail;  // 상품 썸네일 URL

    // Getter and Setter
    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getProductThumbnail() {
        return productThumbnail;
    }

    public void setProductThumbnail(String productThumbnail) {
        this.productThumbnail = productThumbnail;
    }
}
