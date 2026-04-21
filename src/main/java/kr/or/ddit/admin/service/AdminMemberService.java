package kr.or.ddit.admin.service;

import java.util.List;
import kr.or.ddit.dto.MemberDTO;
import kr.or.ddit.member.mapper.MemberMapper;
import kr.or.ddit.mybatis.MapperProxyGenerator;

public class AdminMemberService {
    private MemberMapper memberDAO = MapperProxyGenerator.generateMapperProxy(MemberMapper.class);

    public List<MemberDTO> getAllMembers() {
        return memberDAO.selectMemberList();
    }

}
