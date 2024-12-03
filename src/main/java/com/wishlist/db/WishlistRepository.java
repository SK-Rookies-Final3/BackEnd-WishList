package com.wishlist.db;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WishlistRepository extends JpaRepository<Wishlist, Long> {
    // userId를 기준으로 Wishlist를 찾는 메소드
    Optional<Wishlist> findByUserId(String userId);
}
