package kr.or.ddit.mybatis;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.jupiter.api.Test;

public class CustomSqlSessionFactoryBuilderTest {

    @Test
    void testGetSqlSessionFactory() {
        SqlSessionFactory sqlSessionFactory = CustomSqlSessionFactoryBuilder.getSqlSessionFactory();
        System.out.println(sqlSessionFactory);
        assertNotNull(sqlSessionFactory);
    }
}
