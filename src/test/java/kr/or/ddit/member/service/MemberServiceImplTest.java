package kr.or.ddit.member.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import kr.or.ddit.member.dao.MemberMapperImpl;
import kr.or.ddit.member.dto.MemberDTO;

public class MemberServiceImplTest {
    MemberMapperImpl dao = new MemberMapperImpl();
    MemberServiceImpl memberServiceImpl = new MemberServiceImpl();

    @Test
    void testUpdatePassword() {
        String username = "x001";
        String oldPassword = "java";
        String newPassword = "java1";

        memberServiceImpl.changePassword(username, oldPassword, newPassword);

        MemberDTO memberDTO = dao.selectMember(username);
        String updatedPass = memberDTO.getMemPass();
        assertEquals(newPassword, updatedPass);

        // 테스트를 통과하면 다시 원래대로 돌리기

        memberServiceImpl.changePassword(username, newPassword, oldPassword);

        memberDTO = dao.selectMember(username);
        updatedPass = memberDTO.getMemPass();
        assertEquals(oldPassword, updatedPass);
    }
}
