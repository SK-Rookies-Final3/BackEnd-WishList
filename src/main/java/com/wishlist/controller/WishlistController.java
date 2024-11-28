package com.wishlist.controller;

import com.wishlist.dto.Product;
import com.wishlist.dto.ProductRequest;
import com.wishlist.dto.ShortsRequest;
import com.wishlist.service.WishlistService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/wishlist")
public class WishlistController {

    private final WishlistService wishlistService;

    public WishlistController(WishlistService wishlistService) {
        this.wishlistService = wishlistService;
    }

    @PostMapping("/products")
    public String addProductToWishlist(@PathVariable String userId, @RequestBody ProductRequest productRequest) {
        return wishlistService.addProductToWishlist(userId, productRequest.getProductCode());
    }

    @PostMapping("/shorts")
    public String addShortToWishlist(@PathVariable String userId, @RequestBody ShortsRequest shortsRequest) {
        return wishlistService.addShortToWishlist(userId, shortsRequest.getShortsCode());
    }

    @GetMapping("/products")
    public List<Product> getProductsFromWishlist(@PathVariable String userId) {
        return wishlistService.getProductsFromWishlist(userId);
    }

    @GetMapping("/shorts")
    public List<Short> getShortsFromWishlist(@PathVariable String userId) {
        return wishlistService.getShortsFromWishlist(userId);
    }

    @DeleteMapping("/products/{productCode}")
    public String removeProductFromWishlist(@PathVariable String userId, @PathVariable String productCode) {
        return wishlistService.removeProductFromWishlist(userId, productCode);
    }

    @DeleteMapping("/shorts/{shortsCode}")
    public String removeShortFromWishlist(@PathVariable String userId, @PathVariable String shortsCode) {
        return wishlistService.removeShortFromWishlist(userId, shortsCode);
    }
}
