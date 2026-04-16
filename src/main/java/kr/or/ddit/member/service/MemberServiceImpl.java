package kr.or.ddit.member.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import kr.or.ddit.auth.exception.AuthenticationException;
import kr.or.ddit.auth.service.AuthenticateService;
import kr.or.ddit.common.exception.EntityNotFoundException;
import kr.or.ddit.member.dto.MemberDTO;
import kr.or.ddit.member.mapper.MemberMapper;
import kr.or.ddit.mybatis.CustomSqlSessionFactoryBuilder;
import kr.or.ddit.mybatis.MapperProxyGenerator;
import kr.or.ddit.mybatis.SqlSessionContext;

public class MemberServiceImpl implements MemberService {
    private MemberMapper mapper = MapperProxyGenerator.generateMapperProxy(MemberMapper.class);
    private AuthenticateService authenticateService = new AuthenticateService();
    private SqlSessionFactory sqlSessionFactory = CustomSqlSessionFactoryBuilder.getSqlSessionFactory();

    @Override
    public List<MemberDTO> readMemberList() {
        return mapper.selectMemberList();
    }

    @Override
    public void changePassword(String username, String oldPassword, String newPassword) throws AuthenticationException {
        MemberDTO memberDTO = authenticateService.authenticate(username, oldPassword);

        // try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
        // SqlSessionContext.setSqlSession(sqlSession);
        memberDTO.setMemPass(newPassword);
        mapper.updatePassword(memberDTO.getMemId(), memberDTO.getMemPass());
        // sqlSession.commit();
        // } catch (Exception e) {
        // } finally {
        // SqlSessionContext.removeSqlSession();
        // }

    }

    @Override
    public void registerMember(MemberDTO memberDTO) {
        int cnt = mapper.insertMember(memberDTO);
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            SqlSessionContext.setSqlSession(sqlSession);
            cnt = mapper.insertMember(memberDTO);
            sqlSession.commit();
            if (cnt > 0) {
                mapper.insertMemberRole(memberDTO.getMemId());
                sqlSession.commit();
            } else {
                throw new RuntimeException("회원 등록 실패");
            }
        } finally {
            SqlSessionContext.removeSqlSession();
        }
    }

    @Override
    public void modifyMember(MemberDTO memberDTO) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'modifyMember'");
    }

    @Override
    public MemberDTO readMember(String memId) throws EntityNotFoundException {
        MemberDTO memberDTO = mapper.selectMember(memId);
        if (memberDTO == null) {
            throw new EntityNotFoundException("%s 사용자 없음".formatted(memId));
        }
        return memberDTO;
    }

    @Override
    public void removeMember(MemberDTO memberDTO) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'removeMember'");
    }
}
