package com.example.fus.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReviewDTO {
    private int index;
    private int productId; //상품 아이디
    private String memberId; //아이디
    private String productName; //상품명
    private int rate; //평점
    private String title; //제목
    private String content; //내용
    private String fileName; //파일이름
    private String addDate; //등록 날짜
}
