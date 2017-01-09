package com.app.user;

import com.app.model.User;

/**
 * @author huwenwen
 * @since 16/12/27
 */
public interface UserService {

  User getUserById(Long id);
}
