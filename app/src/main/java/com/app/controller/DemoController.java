package com.app.controller;

import com.alibaba.fastjson.JSON;
import com.app.model.User;
import com.app.user.UserService;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author huwenwen
 * @since 16/12/23
 */
@Controller
@EnableAutoConfiguration
@Log
public class DemoController {

  @Autowired
  private UserService userService;

  @RequestMapping(value = "/hello1")
  @ResponseBody
  public String hello() {
    User userById = userService.getUserById(2l);
    log.info("--------user:" + JSON.toJSONString(userById));
    return "hello:" + JSON.toJSONString(userById);
  }

}
