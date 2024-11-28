package com.wishlist.dto;

public class ProductRequest {
    private String productCode;

    // 생성자
    public ProductRequest(String productCode) {
        this.productCode = productCode;
    }

    // getter, setter
    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }
}
