package com.wishlist.service;

import com.wishlist.dto.Product;
import com.wishlist.dto.ProductRequest;
import com.wishlist.dto.ShortsRequest;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class WishlistService {

    private final RestTemplate restTemplate;
    private final String baseUrl = "http://api.example.com"; // 실제 API Gateway 주소로 변경

    public WishlistService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    // 상품을 위시리스트에 등록
    public String addProductToWishlist(String userId, String productCode) {
        try {
            String url = baseUrl + "/wishlist/" + userId + "/products";
            ProductRequest productRequest = new ProductRequest(productCode);

            ResponseEntity<String> response = restTemplate.postForEntity(url, productRequest, String.class);
            if (response.getStatusCode().is2xxSuccessful()) {
                return "Product added to wishlist successfully.";
            } else {
                return "Failed to add product to wishlist. Status: " + response.getStatusCode();
            }
        } catch (Exception e) {
            // 예외 처리 및 로깅 추가
            return "Error occurred: " + e.getMessage();
        }
    }


    // 숏츠를 위시리스트에 등록
    public String addShortToWishlist(String userId, String shortsCode) {
        String url = baseUrl + "/wishlist/" + userId + "/shorts";
        ShortsRequest shortsRequest = new ShortsRequest(shortsCode);

        ResponseEntity<String> response = restTemplate.postForEntity(url, shortsRequest, String.class);
        if (response.getStatusCode().is2xxSuccessful()) {
            return "Short added to wishlist successfully.";
        } else {
            return "Failed to add short to wishlist.";
        }
    }

    // 위시리스트에 포함된 모든 상품 조회
    public List<Product> getProductsFromWishlist(String userId) {
        String url = baseUrl + "/wishlist/" + userId + "/products";
        ResponseEntity<List> response = restTemplate.exchange(url, HttpMethod.GET, null, List.class);
        return response.getBody();
    }

    // 위시리스트에 포함된 모든 숏츠 조회
    public List<Short> getShortsFromWishlist(String userId) {
        String url = baseUrl + "/wishlist/" + userId + "/shorts";
        ResponseEntity<List> response = restTemplate.exchange(url, HttpMethod.GET, null, List.class);
        return response.getBody();
    }

    // 상품 삭제
    public String removeProductFromWishlist(String userId, String productCode) {
        String url = baseUrl + "/wishlist/" + userId + "/products/" + productCode;
        restTemplate.delete(url);
        return "Product removed from wishlist.";
    }

    // 숏츠 삭제
    public String removeShortFromWishlist(String userId, String shortsCode) {
        String url = baseUrl + "/wishlist/" + userId + "/shorts/" + shortsCode;
        restTemplate.delete(url);
        return "Short removed from wishlist.";
    }
}
