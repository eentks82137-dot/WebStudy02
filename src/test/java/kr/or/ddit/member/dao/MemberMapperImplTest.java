package kr.or.ddit.member.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Arrays;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.jupiter.api.Test;

import kr.or.ddit.member.dto.MemberDTO;
import kr.or.ddit.member.mapper.MemberMapper;
import kr.or.ddit.mybatis.CustomSqlSessionFactoryBuilder;
import kr.or.ddit.mybatis.MapperProxyGenerator;
import kr.or.ddit.mybatis.SqlSessionContext;

public class MemberMapperImplTest {
    MemberMapper mapper = MapperProxyGenerator.generateMapperProxy(MemberMapper.class);
    private SqlSessionFactory sqlSessionFactory = CustomSqlSessionFactoryBuilder.getSqlSessionFactory();

    @Test
    void testSelectMember() {
        MemberDTO memberDTO = mapper.selectMember("b001");
        assertNotNull(memberDTO);
        System.out.println(memberDTO);
    }

    @Test
    void testSelectMemberList() {
        assertNotNull(mapper.selectMemberList());
        mapper.selectMemberList().forEach(System.out::println);
    }

    @Test
    void updateMemberPassword() {
        String username = "b001";
        String password = "java";
        int rowCount = mapper.updatePassword(username, password);

        MemberDTO memberDTO = mapper.selectMember(username);
        assertEquals(1, rowCount);
        assertEquals(password, memberDTO.getMemPass());
    }

    @Test
    void testInsertMember() {
        MemberDTO memberDTO = MemberDTO.builder()
                .memId("z009")
                .memPass("java")
                .memName("dummy")
                .memZip("12345")
                .memAdd1("add1")
                .memAdd2("add2")
                .memMail("foo@bar.com")
                .build();

        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            SqlSessionContext.setSqlSession(sqlSession);
            sqlSession.getMapper(MemberMapper.class).insertMember(memberDTO);
        } finally {
            SqlSessionContext.removeSqlSession();
        }

    }
}
