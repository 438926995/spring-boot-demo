package com.wen.controller;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author huwenwen
 * @since 16/12/23
 */
@Controller
@EnableAutoConfiguration
public class HelloController {

  @RequestMapping(value = "/hello", method = RequestMethod.GET)
  @ResponseBody
  public String hello(String name){
    return "hello:" + name;
  }
}
