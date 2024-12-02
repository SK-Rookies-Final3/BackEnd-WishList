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

    @ElementCollection
    private List<String> productCodes;  // 상품 코드 리스트

    @ElementCollection
    private List<String> shortsCodes;  // 숏츠 코드 리스트

    // Getter and Setter
    public List<String> getProductCodes() {
        return productCodes;
    }

    public void setProductCodes(List<String> productCodes) {
        this.productCodes = productCodes;
    }

    public List<String> getShortsCodes() {
        return shortsCodes;
    }

    public void setShortsCodes(List<String> shortsCodes) {
        this.shortsCodes = shortsCodes;
    }
}
