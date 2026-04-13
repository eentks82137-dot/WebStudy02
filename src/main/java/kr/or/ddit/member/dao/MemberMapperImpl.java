package kr.or.ddit.member.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import kr.or.ddit.member.dto.MemberDTO;
import kr.or.ddit.mybatis.CustomSqlSessionFactoryBuilder;

public class MemberMapperImpl implements MemberDAO {
    SqlSessionFactory sqlSessionFactory = CustomSqlSessionFactoryBuilder.getSqlSessionFactory();

    @Override
    public MemberDTO selectMember(String username) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            return sqlSession.selectOne("Member.selectMember", username);
        }
    }

    @Override
    public List<MemberDTO> selectMemberList() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'selectMemberList'");
    }

    @Override
    public void updateMemberPassword(MemberDTO memberDTO) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateMemberPassword'");
    }

    @Override
    public int updatePassword(String username, String password) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updatePassword'");
    }

}
