package com.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author huwenwen
 * @since 16/12/23
 */
@Controller
public class HelloController {

  @RequestMapping(value = "/hello", method = RequestMethod.GET)
  public String hello(){
    return "hello";
  }
}
