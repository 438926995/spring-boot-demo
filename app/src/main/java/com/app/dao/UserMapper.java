package com.app.dao;

import com.app.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * @author huwenwen
 * @since 16/12/27
 */
@Mapper
public interface UserMapper {

  @Select("select * from t_big_table where id = #{id}")
  User getUserById(Long id);

}
