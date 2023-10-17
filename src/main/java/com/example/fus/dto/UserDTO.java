package com.example.fus.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private String memberId;
    private String passwd;
    private String name;
    private String birthDate;
    private String zipCode;
    private String address01;
    private String address02;
    private String phone;
    private String findQuestion;
    private String findAnswer;
    private String addDate;
}
