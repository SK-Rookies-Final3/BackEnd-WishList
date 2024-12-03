package com.wishlist.db;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductCodeRepository extends JpaRepository<ProductCode, String> {

    // 특정 상품 코드로 상품 정보를 조회하는 쿼리 메소드
    Optional<ProductCode> findByProductCode(String productCode);

   }
