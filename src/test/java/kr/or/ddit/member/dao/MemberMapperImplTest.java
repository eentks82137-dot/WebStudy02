package kr.or.ddit.member.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

import kr.or.ddit.member.dto.MemberDTO;
import kr.or.ddit.member.mapper.MemberMapper;
import kr.or.ddit.mybatis.MapperProxyGenerator;

public class MemberMapperImplTest {
    MemberMapper mapper = MapperProxyGenerator.generateMapperProxy(MemberMapper.class);

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
}
