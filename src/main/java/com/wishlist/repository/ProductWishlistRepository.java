package com.wishlist.repository;

import com.wishlist.dto.ProductWishlist;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductWishlistRepository extends JpaRepository<ProductWishlist, Long> {
    List<ProductWishlist> findByUserId(String userId);  // 사용자 ID로 필터링
}
