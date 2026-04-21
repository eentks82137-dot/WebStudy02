package kr.or.ddit.member.service;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import kr.or.ddit.dto.MemberDTO;
import kr.or.ddit.member.mapper.MemberMapper;
import kr.or.ddit.mybatis.MapperProxyGenerator;

public class MemberServiceImplTest {
    MemberMapper dao = MapperProxyGenerator.generateMapperProxy(MemberMapper.class);
    MemberServiceImpl memberServiceImpl = new MemberServiceImpl();

    @Test
    void testUpdatePassword() {
        String username = "x001";
        String oldPassword = "java";
        String newPassword = "java1";

        assertDoesNotThrow(
                () -> memberServiceImpl.changePassword(username, oldPassword, newPassword));
        MemberDTO memberDTO = dao.selectMember(username);
        String updatedPass = memberDTO.getMemPass();
        assertEquals(newPassword, updatedPass);

        // 테스트를 통과하면 다시 원래대로 돌리면서 정상적으로 변경 되었는지 확인

        assertDoesNotThrow(
                () -> memberServiceImpl.changePassword(username, newPassword, oldPassword));

        memberDTO = dao.selectMember(username);
        updatedPass = memberDTO.getMemPass();
        assertEquals(oldPassword, updatedPass);
    }

    @Test
    void testReadMemberList() {
        memberServiceImpl.readMemberList().forEach(System.out::println);
    }
}
