package com.wishlist.service;

import com.wishlist.db.WishlistEntity;
import com.wishlist.db.WishlistRepository;
import com.wishlist.dto.WishlistRequest;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * WishlistService 클래스는 사용자의 즐겨찾기 목록을 관리하는 서비스입니다.
 * 주로 사용자가 특정 제품을 즐겨찾기 목록에 추가하거나, 기존에 추가된 제품의 상태를 토글하는 작업을 수행합니다.
 */
@Service // Spring의 서비스 컴포넌트로 등록되어 비즈니스 로직을 담당하는 클래스임을 나타냅니다.
@RequiredArgsConstructor // Lombok을 사용하여 필드에 대한 생성자를 자동 생성
public class WishlistService {

    private final WishlistRepository wishlistRepository; // Wishlist 엔티티의 데이터베이스 작업을 위한 레포지토리
    private final UserServiceClient userServiceClient; // 사용자 관련 정보를 조회하기 위한 외부 서비스 클라이언트

    /**
     * 특정 사용자의 즐겨찾기 목록에서 제품을 토글하는 메서드입니다.
     * - 즐겨찾기 목록에 제품이 있으면 삭제 상태를 토글(반전)합니다.
     * - 즐겨찾기 목록에 제품이 없으면 새로 추가합니다.
     *
     * @param productCode 즐겨찾기 할 제품의 고유 코드
     * @param headers HTTP 요청 헤더. 사용자 인증에 사용되는 정보가 포함됩니다.
     * @throws RuntimeException 사용자 ID를 찾을 수 없을 경우 예외를 던집니다.
     */
    @Transactional // 이 메서드는 데이터베이스 작업을 포함하고 있으므로 트랜잭션을 관리합니다.
    public void toggleWishlist(String productCode, HttpHeaders headers) {
        // 1. HTTP 헤더에서 사용자 이메일을 추출합니다.
        String userEmail = userServiceClient.getMyEmail(headers);

        // 2. 이메일을 통해 사용자 ID를 조회합니다.
        Optional<Long> userId = userServiceClient.getUserId(userEmail);

        // 3. 사용자 ID가 존재하지 않으면 예외를 발생시킵니다.
        if (userId.isEmpty()) {
            throw new RuntimeException("사용자 ID를 찾을 수 없습니다.");
        }

        // 4. 사용자 ID와 제품 코드로 즐겨찾기 목록을 조회합니다.
        Optional<WishlistEntity> optionalWishlist = wishlistRepository.findByUserIdAndProductCode(userId.get(), productCode);

        // 5. 즐겨찾기 목록에 해당 제품이 이미 있으면, 삭제 상태를 반전시킵니다.
        if (optionalWishlist.isPresent()) {
            WishlistEntity wishlist = optionalWishlist.get();
            wishlist.toggleDeleted(); // 'deleted' 상태를 토글합니다.
            wishlistRepository.save(wishlist); // 변경된 상태를 저장합니다.
        } else {
            // 6. 즐겨찾기 목록에 해당 제품이 없다면, 새로 추가합니다.
            WishlistEntity wishlist = WishlistEntity.of(userId.get(), productCode); // 새로운 WishlistEntity 객체 생성
            wishlistRepository.save(wishlist); // 새로 생성된 엔티티를 데이터베이스에 저장합니다.
        }
    }
}
