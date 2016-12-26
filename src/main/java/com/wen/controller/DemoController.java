package com.wen.controller;

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
public class DemoController {

  @RequestMapping(value = "/hello1")
  @ResponseBody
  public String hello(){
    return "hello";
  }

}
