package kr.or.ddit.member.service;

import java.util.List;

import kr.or.ddit.auth.exception.AuthenticationException;
import kr.or.ddit.common.exception.EntityNotFoundException;
import kr.or.ddit.member.dto.MemberDTO;

/**
 * 사용자 관리를 위한 Business Logic Layer 인터페이스
 */
public interface MemberService {

    /**
     * 회원 등록
     * 
     * @param memberDTO 등록할 회원 정보가 담긴 MemberDTO 객체
     */
    void registerMember(MemberDTO memberDTO);

    /**
     * 회원 정보 수정
     * 
     * @param memberDTO 수정할 회원 정보가 담긴 MemberDTO 객체
     * @throws AuthenticationException 인증 실패 시 발생하는 예외
     */
    void modifyMember(MemberDTO memberDTO) throws AuthenticationException;

    /**
     * 회원 상세 조회
     * 
     * @param memId 조회할 회원의 아이디
     * @return 조회된 회원 정보가 담긴 MemberDTO 객체
     * @throws EntityNotFoundException 조회 대상이 존재하지 않을 때 발생하는 예외
     */
    MemberDTO readMember(String memId) throws EntityNotFoundException;

    /**
     * 회원 삭제
     * 
     * @param memberDTO 삭제할 회원 정보가 담긴 MemberDTO 객체
     */
    void removeMember(String memId, String memPass);

    /**
     * (관리자용) 모든 사용자 정보를 조회하는 메서드
     * 
     * @return 사용자 정보 리스트
     */

    public List<MemberDTO> readMemberList();

    /**
     * 비밀번호 변경
     * 
     * @param username    사용자 이름
     * @param oldPassword 이전 비밀번호
     * @param newPassword 새로운 비밀번호
     * @throws AuthenticationException 인증 실패 시 발생하는 예외
     */
    public void changePassword(String username, String oldPassword, String newPassword) throws AuthenticationException;
}
