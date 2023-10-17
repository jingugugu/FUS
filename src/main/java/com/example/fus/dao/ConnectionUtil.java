package com.example.fus.dao;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public enum ConnectionUtil { // enum은 자동으로 싱글톤 타입으로 만들어줘서 enum 사용함
    INSTANCE;

    private final HikariDataSource dataSource;

    ConnectionUtil() {
        HikariConfig config = new HikariConfig();
        config.setDriverClassName("org.mariadb.jdbc.Driver");
        config.setJdbcUrl("jdbc:mariadb://localhost:3308/fus");
        config.setUsername("root");
        config.setPassword("9979"); // 이 부분 각자 비밀번호에 맞춰서 변경
        config.addDataSourceProperty("cachePrepStmts", "true");
        config.addDataSourceProperty("prepStmtCacheSize", "250");
        config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");

        dataSource = new HikariDataSource(config);
    }

    public Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }
}

// 사용하기 위해서는 ConnectionUtil.INSTANCE.getConnection()을 이용해서 접근해야 함. ( static 처럼 )
