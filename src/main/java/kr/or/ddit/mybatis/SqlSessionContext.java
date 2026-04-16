package kr.or.ddit.mybatis;

import org.apache.ibatis.session.SqlSession;

public class SqlSessionContext {
    /**
     * ThreadLocal을 사용하여 현재 스레드에 SqlSession을 저장하고 관리하는 유틸리티 클래스
     * 
     * ThreadLocal을 사용하여 각 스레드마다 독립적인 SqlSession을 저장할 수 있도록 구현되어 있다.
     */
    private static final ThreadLocal<SqlSession> threadLocal = new ThreadLocal<>();

    public static void setSqlSession(SqlSession sqlSession) {
        threadLocal.set(sqlSession);
    }

    public static SqlSession getSqlSession() {
        return threadLocal.get();
    }

    public static void removeSqlSession() {
        threadLocal.remove();
    }

    public static boolean hasSqlSession() {
        return getSqlSession() != null;
    }
}
