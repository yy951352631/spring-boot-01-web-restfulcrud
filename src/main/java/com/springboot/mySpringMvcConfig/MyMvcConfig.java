package com.springboot.mySpringMvcConfig;

import com.springboot.component.LoginHandlerInterceptor;
import org.springframework.boot.web.server.WebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory;
import org.springframework.web.servlet.LocaleResolver;
import com.springboot.component.MyLocaleResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.annotation.WebServlet;

/**
 * @author Wtq
 * @date 2019/8/6 - 15:38
 */
/*
1.使用WebMvcAutoConfigurationAdapter可以扩展SpringMVC的功能
2.使用@EnableWebMvc,则完全接管springMVC的配置，（SpringBoot 的自动配置不再生效）
 2.1:原因：SpringBoot的SpringMVCAutoConfig中会检测DelegatingWebMvcConfiguration类是否存在，如果存在则不进行自动配置，而
 @EnableWebMvc注解会将类DelegatingWebMvcConfiguration 自动注入到容器中
 (WebMvcAutoConfiguration上的注解)@ConditionalOnClass({ Servlet.class, DispatcherServlet.class,
WebMvcConfigurerAdapter.class })        
//容器中没有这个组件的时候，这个自动配置类才生效
 */
//@EnableWebMvc
@Configuration
public class MyMvcConfig implements WebMvcConfigurer {

    //自定义我们的视图解析器
    //将组件注册在我们的容器中
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        //urlPath:我们的RequestMapping  setViewName 值就是我们的返回值
        registry.addViewController("/").setViewName("login");
        registry.addViewController("/index.html").setViewName("login");
        registry.addViewController("/main.html").setViewName("dashboard");
    }

    //注册拦截器
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //静态资源？ *.css   *.js
        //SpringBoot 已经完成了静态资源的映射，我们无需管理，页面也能正常访问
        registry.addInterceptor(new LoginHandlerInterceptor())
                .addPathPatterns("/*main*")// /**  :任意多层路径下的任意请求都进行拦截
                .excludePathPatterns("/**.css");//excludePathPatterns除了 "/index.html","/","user/login"
    }

    @Bean
    public LocaleResolver localeResolver() {
        return new MyLocaleResolver();
    }
}


/*


//注册拦截器
@Override
public void addInterceptors(InterceptorRegistry registry) {
    registry.addInterceptor(new LoginHandlerInterceptor()).addPathPatterns("/**").excludePathPatterns("/",
            "/index.html","/user/login","/static/asserts/css/**");
}
 */