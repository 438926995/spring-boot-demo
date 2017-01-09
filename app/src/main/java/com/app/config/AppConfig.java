package com.app.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @author huwenwen
 * @since 16/12/26
 */
@Configuration
@ComponentScan(basePackages = {"com.wen.service"})
@Import({DateSourceConfig.class, SecurityConfig.class})
public class AppConfig {
}
