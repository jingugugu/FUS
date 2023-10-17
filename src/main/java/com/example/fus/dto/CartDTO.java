package com.example.fus.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartDTO {
    private int num;    // 인덱스
    private int productId; // 상품번호
    private String memberId; // 멤버 아이디
    private String productName; //상품 이름
    private int count; // 수량
    private int price; //상품 가격
    private String fileName; //상품 이미지 파일
    private String addDate; //등록 날짜
}
