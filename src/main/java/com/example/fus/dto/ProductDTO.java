package com.example.fus.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {
    private int productId; //상품 아이디
    private String productName; //상품 이름
    private String category; //카테고리
    private String description; //상품 설명
    private int price; //상품 가격
    private int unitsInStock; //상품 재고
    private String fileName; //상품 이미지 파일
    private String addDate; //등록 날짜
    private int reviewCount; //리뷰 수
    private int orderCount; //판매 수
}
