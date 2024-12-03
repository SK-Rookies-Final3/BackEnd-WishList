package com.wishlist.db;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Wishlist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id")
    private String userId;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "wishlist_id")
    private List<ProductCode> productCodes;  // 상품 코드 리스트 (상품 코드와 썸네일 포함)

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "wishlist_id")
    private List<ShortsCode> shortsCodes;  // 숏츠 코드 리스트 (숏츠 코드, 썸네일, 링크 포함)

    // Getter and Setter
    public List<ProductCode> getProductCodes() {
        return productCodes;
    }

    public void setProductCodes(List<ProductCode> productCodes) {
        this.productCodes = productCodes;
    }

    public List<ShortsCode> getShortsCodes() {
        return shortsCodes;
    }

    public void setShortsCodes(List<ShortsCode> shortsCodes) {
        this.shortsCodes = shortsCodes;
    }
}
