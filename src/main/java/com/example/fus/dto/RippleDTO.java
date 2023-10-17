package com.example.fus.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RippleDTO {
    private Integer rippleId;
    private Integer boardNum;
    private String memberId;
    private String name;
    private String content;
    private String insertDate;
    private String ip;
    private boolean isLogin; //댓글 작성자가 로그인인지
}