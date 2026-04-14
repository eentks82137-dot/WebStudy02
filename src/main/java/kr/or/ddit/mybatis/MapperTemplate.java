package kr.or.ddit.mybatis;

import java.util.function.Function;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class MapperTemplate<S> {
    private final Class<S> mapperType;

    private SqlSessionFactory sqlSessionFactory = CustomSqlSessionFactoryBuilder.getSqlSessionFactory();

    public <T> T executeAround(Function<S, T> callback) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession(true)) {
            S mapperProxy = sqlSession.getMapper(mapperType);
            return callback.apply(mapperProxy);
        }
    }
}
