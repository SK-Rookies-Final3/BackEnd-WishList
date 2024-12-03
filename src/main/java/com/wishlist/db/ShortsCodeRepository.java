package com.wishlist.db;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ShortsCodeRepository extends JpaRepository<ShortsCode, String> {

    // 특정 숏츠 코드로 숏츠 정보를 조회하는 쿼리 메소드
    Optional<ShortsCode> findByShortsCode(String shortsCode);
}
