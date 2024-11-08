package com.wishlist.db;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

/**
 * WishlistEntity 클래스는 사용자가 선택한 제품을 즐겨찾기 형태로 저장하는 엔티티 클래스입니다.
 * 이 클래스는 데이터베이스의 'wishlist' 테이블에 매핑됩니다.
 */
@Getter
@Entity // 이 클래스는 JPA 엔티티임을 나타냅니다.
public class WishlistEntity {

    @Id // 이 필드는 기본 키로 사용됩니다.
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 기본 키의 값을 자동으로 생성하도록 설정
    private Long id; // 각 즐겨찾기의 고유 ID

    @Setter
    @Column(name = "user_id") // 'user_id' 컬럼과 매핑
    private Long userId; // 해당 즐겨찾기를 만든 사용자의 ID

    @Column(name = "product_code") // 'product_code' 컬럼과 매핑
    private String productCode; // 사용자가 즐겨찾기한 제품의 고유 코드

    private Boolean deleted; // 즐겨찾기가 삭제되었는지 여부를 나타내는 필드 (삭제 상태를 트래킹)

    // 기본 생성자: JPA의 엔티티 클래스에서는 반드시 필요
    public void Wishlist() {
    }

    /**
     * 생성자: 사용자 ID, 제품 코드, 삭제 상태를 인자로 받아서 객체를 초기화합니다.
     *
     * @param userId 사용자의 ID
     * @param productCode 제품 코드
     * @param deleted 삭제 상태
     */
    private void Wishlist(Long userId, String productCode, Boolean deleted) {
        this.userId = userId;
        this.productCode = productCode;
        this.deleted = deleted;
    }

    /**
     * 사용자가 즐겨찾기를 추가할 때 사용할 정적 팩토리 메서드입니다.
     * 초기 삭제 상태는 false로 설정됩니다.
     *
     * @param userId 사용자의 ID
     * @param productCode 제품 코드
     * @return 새로운 Wishlist 객체
     */
    public static Wishlist of(Long userId, String productCode) {
        return new Wishlist(userId, productCode, false);
    }

    /**
     * 즐겨찾기 항목의 삭제 상태를 토글합니다. (삭제 상태를 반전시킴)
     * 예를 들어, 삭제되지 않은 항목을 삭제된 상태로 변경하거나 그 반대를 수행합니다.
     */
    public void toggleDeleted() {
        this.deleted = !this.deleted; // 'deleted' 값을 반전시킴
    }
}
