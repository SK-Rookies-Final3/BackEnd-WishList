package com.wishlist.dto;

public class Product {
    private String productCode;
    private String productImage; // 상품 이미지 URL

    // getter, setter
    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getProductImage() {
        return productImage;
    }

    public void setProductImage(String productImage) {
        this.productImage = productImage;
    }
}
