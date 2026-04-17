package kr.or.ddit.mybatis;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

import kr.or.ddit.member.mapper.MemberMapper;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.jupiter.api.Test;

public class ProxyTest {

    @Test
    void test() {
        SqlSessionFactory sqlSessionFactory = CustomSqlSessionFactoryBuilder.getSqlSessionFactory();
        Class<?> mapperType = MemberMapper.class;
        InvocationHandler handler = (noOp, method, args) -> {
            try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
                Object mapperProxy = sqlSession.getMapper(mapperType);
                return method.invoke(mapperProxy, args);
            }
        };
        MemberMapper proxy = (MemberMapper) Proxy.newProxyInstance(mapperType.getClassLoader(),
                new Class[] { mapperType }, handler);

        System.out.println(proxy.selectMemberList());
    }

    @SuppressWarnings("unchecked")
    <T> T generateMapperProxy(Class<T> mapperType) {
        SqlSessionFactory sqlSessionFactory = CustomSqlSessionFactoryBuilder.getSqlSessionFactory();
        InvocationHandler handler = (noOp, method, args) -> {
            try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
                Object mapperProxy = sqlSession.getMapper(mapperType);
                return method.invoke(mapperProxy, args);
            }
        };
        T proxy = (T) Proxy.newProxyInstance(mapperType.getClassLoader(),
                new Class[] { mapperType }, handler);

        return proxy;
    }
}
