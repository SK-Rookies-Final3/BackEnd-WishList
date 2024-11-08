package com.wishlist.controller;


import com.wishlist.dto.WishlistResponse;
import com.wishlist.service.WishlistService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestHeader;

@RestController
@RequiredArgsConstructor
@RequestMapping("/wishlist-service")
public class WishlistController {

    private final CartService CartService;

    @Autowired
    public CartController(CartService CartService) {
        this.CartService = CartService;
    }

    // 장바구니에 상품 추가
    @PostMapping("/add")
    public String addProductToCart(@RequestParam String memberId,
                                   @RequestParam String productCode,
                                   @RequestParam int quantity) {
        return CartService.addProductToCart(memberId, productCode, quantity);
    }

    // 장바구니 상품 목록 조회
    @GetMapping("/items")
    public List<Map<String, Object>> getCartItems(@RequestParam String memberId) {
        return CartService.getCartItems(memberId);
    }

    // 장바구니에서 상품 삭제
    @PostMapping("/remove")
    public String removeProductFromCart(@RequestParam String memberId,
                                        @RequestParam String productCode) {
        return CartService.removeProductFromCart(memberId, productCode);
    }

    // 커스텀 장바구니 생성
    @PostMapping("/custom")
    public String createCustomCart(@RequestParam String memberId,
                                   @RequestParam String cartName,
                                   @RequestParam List<String> productCodes) {
        return CartService.createCustomCart(memberId, cartName, productCodes);
    }

    // 커스텀 장바구니 수정
    @PostMapping("/custom/update")
    public String updateCustomCart(@RequestParam String memberId,
                                   @RequestParam String cartName,
                                   @RequestParam List<String> productCodes) {
        return CartService.updateCustomCart(memberId, cartName, productCodes);
    }
}
