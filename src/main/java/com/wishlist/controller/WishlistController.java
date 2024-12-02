package com.wishlist.controller;

import com.wishlist.dto.Product;
import com.wishlist.dto.ProductRequest;
import com.wishlist.dto.Short;
import com.wishlist.dto.ShortsRequest;
import com.wishlist.service.WishlistService;
import com.wishlist.util.HeaderUtils;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpServletRequest;
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
    public String addProductToWishlist(HttpServletRequest request, @RequestBody ProductRequest productRequest) {
        String userId = HeaderUtils.getUserIdFromHeaders(request); // HttpServletRequest에서 사용자 ID를 추출
        return wishlistService.addProductToWishlist(userId, productRequest.getProductCode());
    }

    // 특정 사용자의 Wishlist에 숏츠 추가
    @PostMapping("/shorts")
    public String addShortToWishlist(HttpServletRequest request, @RequestBody ShortsRequest shortsRequest) {
        String userId = HeaderUtils.getUserIdFromHeaders(request); // HttpServletRequest에서 사용자 ID를 추출
        return wishlistService.addShortToWishlist(userId, shortsRequest.getShortsCode());
    }

    // 특정 사용자의 Wishlist에서 상품 목록 조회
    @GetMapping("/products")
    public List<Product> getProductsFromWishlist(HttpServletRequest request) {
        String userId = HeaderUtils.getUserIdFromHeaders(request); // HttpServletRequest에서 사용자 ID를 추출
        return wishlistService.getProductsFromWishlist(userId);
    }

    // 특정 사용자의 Wishlist에서 숏츠 목록 조회
    @GetMapping("/shorts")
    public List<Short> getShortsFromWishlist(HttpServletRequest request) {  // com.wishlist.dto.Short 사용
        String userId = HeaderUtils.getUserIdFromHeaders(request); // HttpServletRequest에서 사용자 ID를 추출
        return wishlistService.getShortsFromWishlist(userId);
    }

    // 특정 사용자의 Wishlist에서 상품 제거
    @DeleteMapping("/products/{productCode}")
    public String removeProductFromWishlist(HttpServletRequest request, @PathVariable String productCode) {
        String userId = HeaderUtils.getUserIdFromHeaders(request); // HttpServletRequest에서 사용자 ID를 추출
        return wishlistService.removeProductFromWishlist(userId, productCode);
    }

    // 특정 사용자의 Wishlist에서 숏츠 제거
    @DeleteMapping("/shorts/{shortsCode}")
    public String removeShortFromWishlist(HttpServletRequest request, @PathVariable String shortsCode) {
        String userId = HeaderUtils.getUserIdFromHeaders(request); // HttpServletRequest에서 사용자 ID를 추출
        return wishlistService.removeShortFromWishlist(userId, shortsCode);
    }
}
