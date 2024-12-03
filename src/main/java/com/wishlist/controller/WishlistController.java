package com.wishlist.controller;

import com.wishlist.dto.Product;
import com.wishlist.dto.ProductRequest;
import com.wishlist.dto.Short;
import com.wishlist.dto.ShortsRequest;
import com.wishlist.service.WishlistService;
import com.wishlist.util.HeaderUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/wishlist")
public class WishlistController {

    private final WishlistService wishlistService;

    public WishlistController(WishlistService wishlistService) {
        this.wishlistService = wishlistService;
    }

    // 특정 사용자의 Wishlist에 상품 추가
    @PostMapping("/products")
    public String addProductToWishlist(@RequestHeader("X-User-Id") String userId, @RequestBody ProductRequest productRequest) {
        return wishlistService.addProductToWishlist(userId, productRequest.getProductCode());
    }

    // 특정 사용자의 Wishlist에 숏츠 추가
    @PostMapping("/shorts")
    public String addShortToWishlist(@RequestHeader("X-User-Id") String userId, @RequestBody ShortsRequest shortsRequest) {
        return wishlistService.addShortToWishlist(userId, shortsRequest.getShortsCode());
    }

    // 특정 사용자의 Wishlist에서 상품 목록 조회
    @GetMapping("/products")
    public List<Product> getProductsFromWishlist(@RequestHeader("X-User-Id") String userId) {
        return wishlistService.getProductsFromWishlist(userId);
    }

    // 특정 사용자의 Wishlist에서 숏츠 목록 조회
    @GetMapping("/shorts")
    public List<Short> getShortsFromWishlist(@RequestHeader("X-User-Id") String userId) {
        return wishlistService.getShortsFromWishlist(userId);
    }

    // 특정 사용자의 Wishlist에서 상품 제거
    @DeleteMapping("/products/{productCode}")
    public String removeProductFromWishlist(@RequestHeader("X-User-Id") String userId, @PathVariable String productCode) {
        return wishlistService.removeProductFromWishlist(userId, productCode);
    }

    // 특정 사용자의 Wishlist에서 숏츠 제거
    @DeleteMapping("/shorts/{shortsCode}")
    public String removeShortFromWishlist(@RequestHeader("X-User-Id") String userId, @PathVariable String shortsCode) {
        return wishlistService.removeShortFromWishlist(userId, shortsCode);
    }
}
