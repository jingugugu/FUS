package com.example.fus.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShippingDTO {
    private String memberId; //아이디
    private int productId; //제품번호
    private String productName; //제품명
    private int count; //제품 갯수
    private String phone; //전화번호
    private String receiverName; //받는사람
    private String receiverZipCode; //받는 우편번호
    private String receiverAddress01; //받는 주소1
    private String receiverAddress02; //받는 주소2
    private String addDate; //등록 날짜
}
