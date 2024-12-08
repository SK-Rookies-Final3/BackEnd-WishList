package com.wishlist.controller;

import com.wishlist.dto.ProductWishlist;
import com.wishlist.dto.ShortsWishlist;
import com.wishlist.service.WishlistService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/wishlist")
public class WishlistController {

    private final WishlistService wishlistService;

    public WishlistController(WishlistService wishlistService) {
        this.wishlistService = wishlistService;
    }

    // 상품 위시리스트 조회
    @GetMapping("/products")
    public ResponseEntity<List<ProductWishlist>> getProductWishlist(@RequestHeader("X-User-Id") String userId) {
        log.info("Fetching product wishlist for userId: {}", userId);
        List<ProductWishlist> productWishlist = wishlistService.getProductWishlist(userId);
        return ResponseEntity.ok(productWishlist);
    }

    // 숏츠 위시리스트 조회
    @GetMapping("/shorts")
    public ResponseEntity<List<ShortsWishlist>> getShortsWishlist(@RequestHeader("X-User-Id") String userId) {
        log.info("Fetching shorts wishlist for userId: {}", userId);
        List<ShortsWishlist> shortsWishlist = wishlistService.getShortsWishlist(userId);
        return ResponseEntity.ok(shortsWishlist);
    }

    // 상품 위시리스트에 항목 추가하기
    @PostMapping("/products")
    public ResponseEntity<ProductWishlist> addProductToWishlist(@RequestHeader("X-User-Id") String userId, @RequestBody ProductWishlist productWishlist) {
        log.info("Adding product to wishlist for userId: {}", userId);
        ProductWishlist addedProduct = wishlistService.addProductToWishlist(userId, productWishlist);
        return ResponseEntity.status(201).body(addedProduct);
    }

    // 숏츠 위시리스트에 항목 추가하기
    @PostMapping("/shorts")
    public ResponseEntity<ShortsWishlist> addShortsToWishlist(@RequestHeader("X-User-Id") String userId, @RequestBody ShortsWishlist shortsWishlist) {
        log.info("Adding shorts to wishlist for userId: {}", userId);
        ShortsWishlist addedShorts = wishlistService.addShortsToWishlist(userId, shortsWishlist);
        return ResponseEntity.status(201).body(addedShorts);
    }

    // 상품 위시리스트에서 항목 삭제하기
    @DeleteMapping("/products/{id}")
    public ResponseEntity<Void> removeProductFromWishlist(@RequestHeader("X-User-Id") String userId, @PathVariable Long id) {
        log.info("Removing product from wishlist for userId: {}", userId);
        wishlistService.removeProductFromWishlist(userId, id);
        return ResponseEntity.noContent().build();
    }

    // 숏츠 위시리스트에서 항목 삭제하기
    @DeleteMapping("/shorts/{id}")
    public ResponseEntity<Void> removeShortsFromWishlist(@RequestHeader("X-User-Id") String userId, @PathVariable Long id) {
        log.info("Removing shorts from wishlist for userId: {}", userId);
        wishlistService.removeShortsFromWishlist(userId, id);
        return ResponseEntity.noContent().build();
    }
}
