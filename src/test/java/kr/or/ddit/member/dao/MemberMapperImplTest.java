package kr.or.ddit.member.dao;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

import kr.or.ddit.member.dto.MemberDTO;

public class MemberMapperImplTest {
    MemberDAO mapper = new MemberMapperImpl();

    @Test
    void testSelectMember() {
        MemberDTO memberDTO = mapper.selectMember("a001");
        assertNotNull(memberDTO);
        System.out.println(memberDTO);
    }
}
