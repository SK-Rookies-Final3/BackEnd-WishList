package com.wishlist.db;

public class WishlistRepository extends JpaRepository<Wishlist, Long> {
    Wishlist findByUserIdAndProductCode(Long userId, String productCode);
}
