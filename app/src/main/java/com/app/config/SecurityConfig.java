package com.app.config;

import com.app.security.CustomDaoAuthenticationProvider;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.ReflectionSaltSource;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@ComponentScan(basePackages = {"com.app.security"})
@Log
// 初始化soa client,扫描TokenService
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  private static final String LOGOUT_URL = "/auth/logout";
  private static final String LOGOUT_SUCCESS_URL = "/auth/logout/validate?status=success";

  @Autowired
  private UserDetailsService customUserDetailsService;

  // ========= Overrides ===========

  @Override
  public void configure(WebSecurity web) throws Exception {
    // 设置不拦截规则
    log.info("设置不拦截规则........");
    web.ignoring().antMatchers("/test/**");
    web.ignoring().antMatchers("/captcha/*");
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {

  }

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.authenticationProvider(getCustomDaoAuthenticationProvider());
  }

  @Bean
  public AuthenticationManager authenticationManager() throws Exception {
    return super.authenticationManagerBean();
  }

  /**
   * 密码MD5加密.
   * 
   * @return
   */
  @Bean
  public Md5PasswordEncoder getPasswordEncoder() {
    return new Md5PasswordEncoder();
  }

  @Bean
  public ReflectionSaltSource getReflectionSaltSource() {
    ReflectionSaltSource rs = new ReflectionSaltSource();
    rs.setUserPropertyToUse("username");
    return rs;
  }

  @Bean
  public CustomDaoAuthenticationProvider getCustomDaoAuthenticationProvider() {
    CustomDaoAuthenticationProvider authenticationProvider = new CustomDaoAuthenticationProvider();
    authenticationProvider.setUserDetailsService(customUserDetailsService);
    authenticationProvider.setPasswordEncoder(getPasswordEncoder());
    return authenticationProvider;
  }

}
