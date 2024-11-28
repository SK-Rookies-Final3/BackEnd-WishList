package com.wishlist.db;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Getter
@Entity
public class WishlistEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    @Column(name = "user_id")
    private Long userId;

    @Column(name = "product_code")
    private String productCode;

    private Boolean deleted;

    // 기본 생성자는 JPA 엔티티 클래스를 위해 반드시 필요
    public WishlistEntity() {
    }

    // 생성자
    public WishlistEntity(Long userId, String productCode, Boolean deleted) {
        this.userId = userId;
        this.productCode = productCode;
        this.deleted = deleted;
    }

    // 정적 팩토리 메서드
    public static WishlistEntity of(Long userId, String productCode) {
        return new WishlistEntity(userId, productCode, false);
    }

    // 삭제 상태를 토글하는 메서드
    public void toggleDeleted() {
        this.deleted = !this.deleted;
    }
}
