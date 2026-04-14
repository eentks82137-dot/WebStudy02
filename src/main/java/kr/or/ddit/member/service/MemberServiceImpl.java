package kr.or.ddit.member.service;

import java.util.List;

import kr.or.ddit.auth.exception.AuthenticationException;
import kr.or.ddit.auth.service.AuthenticateService;
import kr.or.ddit.member.dto.MemberDTO;
import kr.or.ddit.member.mapper.MemberMapper;
import kr.or.ddit.mybatis.MapperProxyGenerator;

public class MemberServiceImpl implements MemberService {
    private MemberMapper dao = MapperProxyGenerator.generateMapperProxy(MemberMapper.class);
    private AuthenticateService authenticateService = new AuthenticateService();

    @Override
    public List<MemberDTO> readMemberList() {
        return dao.selectMemberList();
    }

    @Override
    public void changePassword(String username, String oldPassword, String newPassword) throws AuthenticationException {
        MemberDTO memberDTO = authenticateService.authenticate(username, oldPassword);

        // 비밀번호 수정
        // TODO (추후 암호화 해야함)
        memberDTO.setMemPass(newPassword);
        dao.updatePassword(memberDTO.getMemId(), memberDTO.getMemPass());
    }
}
