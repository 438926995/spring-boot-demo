package com.app.config;

import com.alibaba.druid.pool.DruidDataSource;
import lombok.extern.java.Log;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author huwenwen
 * @since 16/12/26
 */
@Configuration
// 启用注解事务管理，使用CGLib代理
@EnableTransactionManagement(proxyTargetClass = true)
@PropertySource({"classpath:jdbc.properties"})
@MapperScan(basePackages = {"com.app.dao"})
@Log
public class DateSourceConfig {

  /**
   * 数据库连接配置
   *
   * @param driverClassName
   * @param username
   * @param password
   * @param jdbcUrl
   * @return
   */
  @Bean
  public DruidDataSource dataSource(@Value("${jdbc.driver.className}") String driverClassName,
      @Value("${jdbc.username}") String username, @Value("${jdbc.password}") String password,
      @Value("${jdbc.url}") String jdbcUrl) {
    log.info("加载数据库....url:" + jdbcUrl);
    DruidDataSource dataSource = new DruidDataSource();
    dataSource.setDriverClassName(driverClassName);
    dataSource.setUsername(username);
    dataSource.setPassword(password);
    dataSource.setUrl(jdbcUrl);
    // 配置初始化大小、最小、最大
    dataSource.setInitialSize(3);
    dataSource.setMinIdle(10);
    dataSource.setMaxActive(35);
    // 配置获取连接等待超时的时间
    dataSource.setMaxWait(60000);
    // 配置间隔多久才进行一次检测，检测需要关闭的空闲连接
    dataSource.setMinEvictableIdleTimeMillis(300000);
    // 连接测试
    dataSource.setValidationQuery("SELECT 'x'");
    dataSource.setTestWhileIdle(true);
    dataSource.setTestOnBorrow(true);
    dataSource.setTestOnReturn(false);
    return dataSource;
  }

  @Bean
  public JdbcTemplate jdbcTemplate(DruidDataSource dataSource){
    return new JdbcTemplate(dataSource);
  }

  @Bean(name = "sqlSessionFactory")
  public SqlSessionFactory sqlSessionFactoryBean(DruidDataSource dataSource){
    log.info("加载数据库sqlSessionFactory......");
    SqlSessionFactoryBean sqlsession = new SqlSessionFactoryBean();
    sqlsession.setDataSource(dataSource);
    sqlsession.setTypeAliasesPackage("");//扫描entity包 使用别名
    org.apache.ibatis.session.Configuration configuration=new org.apache.ibatis.session.Configuration();
    configuration.setUseGeneratedKeys(true);//使用jdbc的getGeneratedKeys获取数据库自增主键值
    configuration.setUseColumnLabel(true);//使用列别名替换列名 select user as User
    configuration.setMapUnderscoreToCamelCase(true);//-自动使用驼峰命名属性映射字段   userId    user_id
    sqlsession.setConfiguration(configuration);
    sqlsession.setFailFast(true);
    //添加XML目录
//    ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
    try {
//      sqlsession.setMapperLocations(resolver.getResources("classpath:mybatis-mapper/*.xml"));
      return sqlsession.getObject();
    } catch (Exception e) {
      e.printStackTrace();
      throw new RuntimeException(e);
    }
  }

  /**
   * 事务配置
   *
   * @param dataSource
   * @return
   */
  @Bean
  public DataSourceTransactionManager dataSourceTransactionManager(DruidDataSource dataSource) {
    log.info("加载事务配置.......");
    return new DataSourceTransactionManager(dataSource);
  }
}
