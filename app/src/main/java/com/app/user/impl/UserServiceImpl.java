package com.app.user.impl;

import com.app.dao.UserMapper;
import com.app.model.User;
import com.app.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author huwenwen
 * @since 16/12/27
 */
@Service
public class UserServiceImpl implements UserService {

  @Autowired
  private UserMapper userMapper;

  @Override
  public User getUserById(Long id) {
    return userMapper.getUserById(id);
  }
}
