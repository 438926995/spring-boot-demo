package com.app.security;

import com.alibaba.fastjson.JSON;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * @author huwenwen
 * @since 16/12/26
 */
@Log
public class CustomDaoAuthenticationProvider extends DaoAuthenticationProvider {

  @Autowired
  private Md5PasswordEncoder passwordEncoder;

  @Override
  protected void additionalAuthenticationChecks(UserDetails userDetails,
      UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
    log.info("additionalAuthenticationChecks:" + JSON.toJSONString(userDetails));
    super.additionalAuthenticationChecks(userDetails, authentication);
  }

  @Override
  public Md5PasswordEncoder getPasswordEncoder() {
    return passwordEncoder;
  }

  public void setPasswordEncoder(Md5PasswordEncoder passwordEncoder) {
    this.passwordEncoder = passwordEncoder;
  }
}
