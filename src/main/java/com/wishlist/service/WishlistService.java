package com.wishlist.service;

import com.wishlist.db.Wishlist;
import com.wishlist.db.WishlistRepository;
import com.wishlist.dto.Product;
import com.wishlist.dto.Short;
import com.wishlist.dto.ProductRequest;
import com.wishlist.dto.ShortsRequest;
import com.wishlist.util.HeaderUtils;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Service
public class WishlistService {

    private final WishlistRepository wishlistRepository;
    private final RestTemplate restTemplate;
    private final String BRAND_SERVICE_URL = "/open-api/brand/product/";  // 브랜드 서비스 URL
    private final String SHORTS_SERVICE_URL = "http://shorts-service/api/shorts/";  // 숏츠 서비스 URL

    @Autowired
    public WishlistService(WishlistRepository wishlistRepository, RestTemplate restTemplate) {
        this.wishlistRepository = wishlistRepository;
        this.restTemplate = restTemplate;
    }

    // 사용자 Wishlist 조회
    private Wishlist getWishlist(HttpServletRequest request) {
        String userId = HeaderUtils.getUserIdFromHeaders(request);
        return wishlistRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("Wishlist not found for user: " + userId));
    }

    // 상품 코드로 상품 정보 가져오기 (브랜드 서비스)
    private Product getProductInfo(String productCode) {
        return restTemplate.getForObject(BRAND_SERVICE_URL + productCode, Product.class);
    }

    // 숏츠 코드로 숏츠 정보 가져오기 (숏츠 서비스)
    private Short getShortInfo(String shortsCode) {
        return restTemplate.getForObject(SHORTS_SERVICE_URL + shortsCode, Short.class);
    }

    // 사용자 Wishlist에서 상품 목록 조회 (상품 정보 포함)
    public List<Product> getProductsFromWishlist(String userId) {
        Wishlist wishlist = wishlistRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("Wishlist not found for user: " + userId));

        List<Product> products = new ArrayList<>();
        for (String productCode : wishlist.getProductCodes()) { // 상품 코드 리스트로 접근
            Product product = getProductInfo(productCode); // 상품 정보 조회
            products.add(product); // 상품 목록에 추가
        }
        return products;
    }

    // 사용자 Wishlist에서 숏츠 목록 조회 (숏츠 정보 포함)
    public List<Short> getShortsFromWishlist(String userId) {
        Wishlist wishlist = wishlistRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("Wishlist not found for user: " + userId));

        List<Short> shorts = new ArrayList<>();
        for (String shortsCode : wishlist.getShortsCodes()) { // 숏츠 코드 리스트로 접근
            Short shortsInfo = getShortInfo(shortsCode); // 숏츠 정보 조회
            shorts.add(shortsInfo); // 숏츠 목록에 추가
        }
        return shorts;
    }

    // 상품을 Wishlist에 추가
    public String addProductToWishlist(String userId, String productCode) {
        Wishlist wishlist = wishlistRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("Wishlist not found for user: " + userId));

        // 이미 Wishlist에 해당 상품이 있는지 확인
        if (!wishlist.getProductCodes().contains(productCode)) {
            wishlist.getProductCodes().add(productCode);
            wishlistRepository.save(wishlist);
            return "Product added to wishlist";
        } else {
            return "Product already exists in wishlist";
        }
    }

    // 숏츠를 Wishlist에 추가
    public String addShortToWishlist(String userId, String shortsCode) {
        Wishlist wishlist = wishlistRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("Wishlist not found for user: " + userId));

        // 이미 Wishlist에 해당 숏츠가 있는지 확인
        if (!wishlist.getShortsCodes().contains(shortsCode)) {
            wishlist.getShortsCodes().add(shortsCode);
            wishlistRepository.save(wishlist);
            return "Short added to wishlist";
        } else {
            return "Short already exists in wishlist";
        }
    }

    // 상품을 Wishlist에서 제거
    public String removeProductFromWishlist(String userId, String productCode) {
        Wishlist wishlist = wishlistRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("Wishlist not found for user: " + userId));

        if (wishlist.getProductCodes().remove(productCode)) {
            wishlistRepository.save(wishlist);
            return "Product removed from wishlist";
        } else {
            return "Product not found in wishlist";
        }
    }

    // 숏츠를 Wishlist에서 제거
    public String removeShortFromWishlist(String userId, String shortsCode) {
        Wishlist wishlist = wishlistRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("Wishlist not found for user: " + userId));

        if (wishlist.getShortsCodes().remove(shortsCode)) {
            wishlistRepository.save(wishlist);
            return "Short removed from wishlist";
        } else {
            return "Short not found in wishlist";
        }
    }
}
