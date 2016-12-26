package com.wen;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.web.support.SpringBootServletInitializer;


/**
 * @author huwenwen
 * @since 16/12/23
 */
@SpringBootApplication
public class Application extends SpringBootServletInitializer implements
    EmbeddedServletContainerCustomizer {

  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }

  @Override
  public void customize(ConfigurableEmbeddedServletContainer configurableEmbeddedServletContainer) {
    configurableEmbeddedServletContainer.setPort(8080);
  }
}
