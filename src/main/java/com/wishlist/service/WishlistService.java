package com.wishlist.service;

import com.wishlist.dto.ProductWishlist;
import com.wishlist.dto.ShortsWishlist;
import com.wishlist.repository.ProductWishlistRepository;
import com.wishlist.repository.ShortsWishlistRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class WishlistService {

    private final ProductWishlistRepository productWishlistRepository;
    private final ShortsWishlistRepository shortsWishlistRepository;

    public WishlistService(ProductWishlistRepository productWishlistRepository, ShortsWishlistRepository shortsWishlistRepository) {
        this.productWishlistRepository = productWishlistRepository;
        this.shortsWishlistRepository = shortsWishlistRepository;
    }

    // 상품 위시리스트 조회
    public List<ProductWishlist> getProductWishlist(int userId) {
        return productWishlistRepository.findByUserId(userId);
    }

    // 숏츠 위시리스트 조회
    public List<ShortsWishlist> getShortsWishlist(int userId) {
        return shortsWishlistRepository.findByUserId(userId);
    }

    // 상품 위시리스트에 항목 추가
    public ProductWishlist addProductToWishlist(int userId, ProductWishlist productWishlist) {
        log.info("Adding productCode {} to wishlist for userId: {}", productWishlist.getProductCode(), userId);
        productWishlist.setUserId(userId);
        return productWishlistRepository.save(productWishlist);
    }

    // 숏츠 위시리스트에 항목 추가
    public ShortsWishlist addShortsToWishlist(int userId, ShortsWishlist shortsWishlist) {
        log.info("Adding shortsCode {} to wishlist for userId: {}", shortsWishlist.getShortsCode(), userId);
        shortsWishlist.setUserId(userId);
        return shortsWishlistRepository.save(shortsWishlist);
    }

    // 상품 위시리스트에서 항목 삭제
    public void removeProductFromWishlist(int userId, Long id) {
        log.info("Removing productCode {} from wishlist for userId: {}", id, userId);
        productWishlistRepository.deleteById(id);
    }

    // 숏츠 위시리스트에서 항목 삭제
    public void removeShortsFromWishlist(int userId, Long id) {
        log.info("Removing shortsCode {} from wishlist for userId: {}", id, userId);
        shortsWishlistRepository.deleteById(id);
    }
}
