package com.wishlist.repository;

import com.wishlist.dto.ShortsWishlist;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ShortsWishlistRepository extends JpaRepository<ShortsWishlist, Long> {
    List<ShortsWishlist> findByUserId(int userId);  // 사용자 ID로 필터링
}
