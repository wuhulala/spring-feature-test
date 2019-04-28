package com.wuhulala.springboot.mybatis.threadsafe;

import org.apache.ibatis.cursor.Cursor;
import org.apache.ibatis.executor.BatchResult;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.defaults.DefaultSqlSessionFactory;

import java.sql.Connection;
import java.util.List;
import java.util.Map;


public class ThreadSafeSqlSession implements SqlSession {

    private static ThreadLocal<SqlSession> threadLocal = new ThreadLocal<>();


    private SqlSessionFactory factory = null;

    public ThreadSafeSqlSession(DefaultSqlSessionFactory sqlSessionFactory) {
        this.factory = sqlSessionFactory;
    }

    @Override
    public <T> T selectOne(String statement) {
        return getSqlSession().selectOne(statement);
    }

    @Override
    public <T> T selectOne(String statement, Object parameter) {
        return getSqlSession().selectOne(statement, parameter);
    }

    @Override
    public <E> List<E> selectList(String statement) {
        return getSqlSession().selectList(statement);
    }

    @Override
    public <E> List<E> selectList(String statement, Object parameter) {
        return getSqlSession().selectList(statement, parameter);
    }

    @Override
    public <E> List<E> selectList(String statement, Object parameter, RowBounds rowBounds) {
        return getSqlSession().selectList(statement, parameter, rowBounds);
    }

    @Override
    public <K, V> Map<K, V> selectMap(String statement, String mapKey) {
        return getSqlSession().selectMap(statement, mapKey);
    }

    @Override
    public <K, V> Map<K, V> selectMap(String statement, Object parameter, String mapKey) {
        return getSqlSession().selectMap(statement, parameter, mapKey);

    }

    @Override
    public <K, V> Map<K, V> selectMap(String statement, Object parameter, String mapKey, RowBounds rowBounds) {
        return getSqlSession().selectMap(statement, parameter, mapKey, rowBounds);
    }


    @Override
    public <T> Cursor<T> selectCursor(String statement) {
        return getSqlSession().selectCursor(statement);
    }

    @Override
    public <T> Cursor<T> selectCursor(String statement, Object parameter) {
        return getSqlSession().selectCursor(statement, parameter);

    }

    @Override
    public <T> Cursor<T> selectCursor(String statement, Object parameter, RowBounds rowBounds) {
        return getSqlSession().selectCursor(statement, parameter, rowBounds);
    }

    @Override
    public void select(String statement, Object parameter, ResultHandler handler) {
        getSqlSession().select(statement, parameter, handler);
    }

    @Override
    public void select(String statement, ResultHandler handler) {
        getSqlSession().select(statement, handler);

    }

    @Override
    public void select(String statement, Object parameter, RowBounds rowBounds, ResultHandler handler) {
        getSqlSession().select(statement, parameter, rowBounds, handler);

    }

    @Override
    public int insert(String statement) {
        return getSqlSession().insert(statement);
    }

    @Override
    public int insert(String statement, Object parameter) {
        return getSqlSession().insert(statement, parameter);
    }

    @Override
    public int update(String statement) {
        return getSqlSession().update(statement);
    }

    @Override
    public int update(String statement, Object parameter) {
        return getSqlSession().update(statement, parameter);
    }

    @Override
    public int delete(String statement) {
        return getSqlSession().delete(statement);
    }

    @Override
    public int delete(String statement, Object parameter) {
        return getSqlSession().delete(statement, parameter);
    }

    @Override
    public void commit() {
        getSqlSession().commit();
    }

    @Override
    public void commit(boolean force) {
        getSqlSession().commit(force);

    }

    @Override
    public void rollback() {
        getSqlSession().rollback();
    }

    @Override
    public void rollback(boolean force) {
        getSqlSession().rollback(force);
    }

    @Override
    public List<BatchResult> flushStatements() {
        return getSqlSession().flushStatements();
    }

    @Override
    public void clearCache() {
        getSqlSession().clearCache();
    }

    @Override
    public <T> T getMapper(Class<T> type) {
        return getSqlSession().getMapper(type);
    }

    @Override
    public Connection getConnection() {
        return getSqlSession().getConnection();
    }

    @Override
    public Configuration getConfiguration() {
        return factory.getConfiguration();
    }

    /**
     * 关闭SqlSession
     */
    @Override
    public void close() {
        SqlSession sqlSession = threadLocal.get();
        if (sqlSession != null) {
            sqlSession.close();
            threadLocal.remove();
        }
    }

    /**
     * 获取SqlSession 代理方法
     *
     * <p> 应该可以使用代理模式实现 </p>
     * @return SqlSession
     */
    private SqlSession getSqlSession() {
        SqlSession sqlSession = threadLocal.get();  // 从当前线程获取
        if (sqlSession == null) {
            sqlSession = factory.openSession();
            threadLocal.set(sqlSession);  // 将sqlSession与当前线程绑定
        }
        return sqlSession;
    }




}