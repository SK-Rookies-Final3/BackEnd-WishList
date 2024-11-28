package com.wishlist.db;

import org.springframework.data.jpa.repository.JpaRepository;

public interface WishlistRepository extends JpaRepository<WishlistEntity, Long> {
    WishlistEntity findByUserIdAndProductCode(Long userId, String productCode);
}
