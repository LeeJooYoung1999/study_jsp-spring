package org.scoula.config;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.sql.DataSource;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;
@Log4j2
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {RootConfig.class})
class RootConfigTest {

    @Autowired
    private DataSource dataSource;

    @Test
    @DisplayName("dbcp 연결 정보 확인")
    void dataSrouce() throws SQLException {

        try ( Connection con = dataSource.getConnection()){
            log.info("dbcp로 부터 연결 하나 가지고 왔음.");
            log.info(con);
        }
    }
}