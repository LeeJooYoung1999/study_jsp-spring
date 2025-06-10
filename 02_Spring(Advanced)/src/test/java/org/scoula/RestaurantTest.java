package org.scoula;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.scoula.config.RootConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@Log4j2
@ContextConfiguration(classes = RootConfig.class)
@ExtendWith(SpringExtension.class)
class RestaurantTest {

    @Autowired
    Restaurant restaurant; //RestaurantTest <-- Restaurant <-- Chef

    @Test
    void getChef() {
        log.info(restaurant); //null 또는 주소
        log.info(restaurant.getChef()); //null 또는 주소
        //assertNotNull(restaurant); //null이 아님을 주장하다!
        //내 주장이 맞으면 콘솔에 아무것도 안찍힘.
        //내 중장이 틀리면 콘솔에 틀린정보가 찍힘.

        //또는
        assertNull(restaurant); //null임을 주장하다.

    }
}