package com.example.fus.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BoardDTO {
    private int boardNum; //게시글 번호
    private String memberId; //멤버 아이디
    private String title; //게시글 제목
    private String name; //사용자 이름
    private String content; //게시글 내용
    private int count;      // 조회수
    private String addDate; //게시글 날짜
    private String fileName; //파일
}
