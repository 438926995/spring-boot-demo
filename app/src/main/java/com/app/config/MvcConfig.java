package com.app.config;

import com.app.exception.CustomSimpleMappingExceptionResolver;
import com.app.security.SubmissionInterceptor;
import lombok.extern.java.Log;
import org.hibernate.validator.HibernateValidator;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Configuration
@EnableWebMvc
@EnableAspectJAutoProxy(proxyTargetClass = true)
@ComponentScan(basePackages = {"com.app.controller"}, useDefaultFilters = false, includeFilters = {
    @ComponentScan.Filter(type = FilterType.ANNOTATION, value = {Controller.class})})
@Log
public class MvcConfig extends WebMvcConfigurerAdapter {

  /**
   * 拦截器.
   */
  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    log.info("-----进入拦截器-----");
    registry.addInterceptor(new LocaleChangeInterceptor());
    registry.addInterceptor(getSubmissionInterceptor())
        .excludePathPatterns("/test/**", "/captcha/*", "/auth/login");
  }

  @Override
  public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
    converters.add(httpMessConverter());
    converters.add(jsonConverter());
  }

  /**
   * 验证.
   *
   * @return
   */
  @Override
  public Validator getValidator() {
    return validator();
  }

  /**
   * 基于cookie的本地化资源处理器
   *
   * @return
   */
  @Bean
  public LocaleResolver getLocaleResolver() {
    return new CookieLocaleResolver();
  }

  /**
   * 消息资源处理器.
   *
   * @return
   */
  @Bean
  public MessageSource messageSource() {
    ReloadableResourceBundleMessageSource messageSource =
        new ReloadableResourceBundleMessageSource();
    messageSource
        .setBasenames("classpath:validate/messages", "classpath:validate/errorCodeMessages");
    messageSource.setCacheSeconds(0);
    return messageSource;
  }

  @Bean
  public LocaleResolver localeResolver() {
    CookieLocaleResolver localeResolver = new CookieLocaleResolver();
    localeResolver.setCookieMaxAge(604800);
    localeResolver.setDefaultLocale(Locale.CHINA);
    localeResolver.setCookieName("locale");
    return localeResolver;
  }

  @Bean
  public LocalValidatorFactoryBean validator() {
    LocalValidatorFactoryBean validatorFactoryBean = new LocalValidatorFactoryBean();
    validatorFactoryBean.setValidationMessageSource(messageSource());
    validatorFactoryBean.setProviderClass(HibernateValidator.class);
    return validatorFactoryBean;
  }

  @Bean
  public MappingJackson2HttpMessageConverter jsonConverter() {
    MappingJackson2HttpMessageConverter jsonMessageConverter =
        new MappingJackson2HttpMessageConverter();
    List<MediaType> supportedMediaTypes = new ArrayList<>();
    supportedMediaTypes
        .add(new MediaType("application", "x-www-form-urlencoded", Charset.forName("UTF-8")));
    supportedMediaTypes.add(new MediaType("application", "json", Charset.forName("UTF-8")));
    jsonMessageConverter.setSupportedMediaTypes(supportedMediaTypes);
    return jsonMessageConverter;
  }

  @Bean
  public StringHttpMessageConverter httpMessConverter() {
    StringHttpMessageConverter httpMessConverter = new StringHttpMessageConverter();
    List<MediaType> supportedMediaTypes = new ArrayList<>();
    supportedMediaTypes.add(new MediaType("text", "plain", Charset.forName("UTF-8")));
    supportedMediaTypes.add(new MediaType("text", "html", Charset.forName("UTF-8")));
    httpMessConverter.setSupportedMediaTypes(supportedMediaTypes);
    return httpMessConverter;
  }

  /**
   * 异常处理器.
   *
   * @return
   */
  @Bean
  public CustomSimpleMappingExceptionResolver simpleMappingExceptionResolver() {
    CustomSimpleMappingExceptionResolver simpleMappingExceptionResolver =
        new CustomSimpleMappingExceptionResolver();
    simpleMappingExceptionResolver.setDefaultErrorView("/account/errorpage");
    simpleMappingExceptionResolver.setExceptionAttribute("ex");
    return simpleMappingExceptionResolver;
  }

  @Override
  public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
    configurer.enable();
  }

  @Bean
  public SubmissionInterceptor getSubmissionInterceptor() {
    return new SubmissionInterceptor();
  }

}
