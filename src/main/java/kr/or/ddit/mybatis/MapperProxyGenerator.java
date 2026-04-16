package kr.or.ddit.mybatis;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

public class MapperProxyGenerator {

    /**
     * MyBatis Mapper 인터페이스의 프록시 객체를 생성하는 유틸리티 메서드
     * MyBatis의 SqlSessionFactory를 사용하여 Mapper 인터페이스의 프록시 객체를 생성합니다.
     * Mapper 인터페이스의 메서드가 호출될 때마다 SqlSession을 열고, 해당 Mapper 인터페이스의 실제 구현체를 가져와서 메서드를
     * 실행합니다.
     * 
     * @param <T>        - Mapper 인터페이스 타입
     * @param mapperType - Mapper 인터페이스 클래스 객체
     * @return - Mapper 인터페이스의 프록시 객체
     */
    public static <T> T generateMapperProxy(Class<T> mapperType) {
        SqlSessionFactory sqlSessionFactory = CustomSqlSessionFactoryBuilder.getSqlSessionFactory();
        InvocationHandler handler = (noOp, method, args) -> {

            if (SqlSessionContext.hasSqlSession()) {
                SqlSession sqlSession = SqlSessionContext.getSqlSession();
                Object mapperProxy = sqlSession.getMapper(mapperType);
                return method.invoke(mapperProxy, args);
            }

            try (SqlSession sqlSession = sqlSessionFactory.openSession(true)) {
                Object mapperProxy = sqlSession.getMapper(mapperType);
                return method.invoke(mapperProxy, args);
            }
        };
        T proxy = mapperType.cast(Proxy.newProxyInstance(mapperType.getClassLoader(),
                new Class<?>[] { mapperType }, handler));

        return proxy;
    }
}
