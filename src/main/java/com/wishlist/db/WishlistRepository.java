package com.wishlist.db;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface WishlistRepository extends JpaRepository<Wishlist, Long> {
    Optional<Wishlist> findByUserId(String userId);  // 사용자 ID로 Wishlist 조회
    void deleteByUserIdAndProductCode(String userId, String productCode);  // 사용자 ID와 상품 코드로 Wishlist에서 상품 제거
    boolean existsByUserIdAndProductCode(String userId, String productCode);  // Wishlist에 특정 상품이 존재하는지 확인
}
