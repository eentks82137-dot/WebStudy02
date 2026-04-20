package kr.or.ddit.member.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import kr.or.ddit.auth.Authentication;
import kr.or.ddit.auth.SecurityContextHolder;
import kr.or.ddit.auth.exception.AuthenticationException;
import kr.or.ddit.auth.service.AuthenticateService;
import kr.or.ddit.common.exception.EntityNotFoundException;
import kr.or.ddit.member.dto.MemberDTO;
import kr.or.ddit.member.mapper.MemberMapper;
import kr.or.ddit.mybatis.CustomSqlSessionFactoryBuilder;
import kr.or.ddit.mybatis.MapperProxyGenerator;
import kr.or.ddit.mybatis.SqlSessionContext;
import kr.or.ddit.validate.ValidateUtils;
import kr.or.ddit.validate.groups.DeleteGroup;
import lombok.extern.slf4j.Slf4j;

@Slf4j
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
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        log.info("인증된 사용자 : {}", authentication.getPrincipal());
        MemberDTO memberDTO = authenticateService.authenticate(authentication.getName(), oldPassword);

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
        int cnt;
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
    public void modifyMember(MemberDTO memberDTO) throws RuntimeException {

        authenticateService.authenticate(memberDTO.getMemId(), memberDTO.getMemPass());

        int cnt = mapper.updateMember(memberDTO);
        if (cnt < 1) {
            throw new RuntimeException("회원 수정 실패");
        }

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
    public void removeMember(String memId, String memPass) {
        // 인증
        authenticateService.authenticate(memId, memPass);

        try (SqlSession session = sqlSessionFactory.openSession();) {
            SqlSessionContext.setSqlSession(session);
            // delete member role
            int cntRole = session.getMapper(MemberMapper.class).deleteMemberRole(memId);
            // delete member
            int cntMem = session.getMapper(MemberMapper.class).deleteMember(memId);
            if (cntMem == 0 || cntRole == 0) {
                // session.commit(); // 테스트용으로 막아둠
                log.info("회원 탈퇴 처리 성공. {}", memId);
            } else {
                throw new RuntimeException("회원 삭제 중 오류 발생");
            }
        } finally {
            SqlSessionContext.removeSqlSession();
        }

    }
}
