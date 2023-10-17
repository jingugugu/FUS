package com.example.fus.util;

import org.modelmapper.ModelMapper;
import org.modelmapper.config.Configuration;
import org.modelmapper.convention.MatchingStrategies;

public enum MapperUtil {
    INSTANCE; // 싱글톤

    private ModelMapper modelMapper;

    // A 객체의 정보를 B 객체로 복사하는 기능을 제공하는 modelMapper 라이브러리 객체의 초기 설정
    MapperUtil() {
        this.modelMapper = new ModelMapper();
        this.modelMapper.getConfiguration()
                .setFieldMatchingEnabled(true)                          // 매칭상태 ON
                .setFieldAccessLevel(Configuration.AccessLevel.PRIVATE) // 접근제어자는 private
                .setMatchingStrategy(MatchingStrategies.STRICT);        // STRICT : 엄격모드. 이름과 데이터 타입 모두 확인. ( 대소문자 까지 확인 )
    }

    public ModelMapper getInstance() {
        return modelMapper;
    }

}
