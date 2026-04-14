package kr.or.ddit.member.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import kr.or.ddit.member.dto.MemberDTO;

/**
 * 사용자 관리와 인증에 사용할 Persistence Layer
 */

public interface MemberMapper {
    /**
     * 사용자 아이디를 이용하여 사용자 정보를 조회하는 메서드
     * 
     * @param username
     * @return
     */
    MemberDTO selectMember(@Param("memId") String username);

    List<MemberDTO> selectMemberList();

    void updateMemberPassword(MemberDTO memberDTO);

    int updatePassword(@Param("username") String username, @Param("password") String password);
}
