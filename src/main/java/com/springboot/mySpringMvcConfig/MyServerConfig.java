package com.springboot.mySpringMvcConfig;

import com.springboot.filter.MyFilter;
import com.springboot.listener.MyListener;
import com.springboot.servlet.MyServlet;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.ServletContextListener;
import java.util.Arrays;

/**
 * @author Wtq
 * @date 2019/8/14 - 10:01
 */
@Configuration
public class MyServerConfig {
    //注册三大组件
    @Bean//注册servlet
    public ServletRegistrationBean myServletRegistrationBean(){
        ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean(new MyServlet(),"/myServlet");
        return servletRegistrationBean;
    }

    @Bean//注册Filter
    public FilterRegistrationBean filterRegistrationBean(){
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(new MyFilter());
        filterRegistrationBean.setUrlPatterns(Arrays.asList("/hello","/myServlet"));
        return filterRegistrationBean;
    }
    @Bean//注册listener
    public ServletListenerRegistrationBean myListener(){
        ServletListenerRegistrationBean<MyListener> registrationBean = new ServletListenerRegistrationBean<MyListener>(new MyListener());
        return registrationBean;
    }

    @Bean
    public WebServerFactoryCustomizer myWebServerFactoryCustomizer() {
        return new WebServerFactoryCustomizer <ConfigurableServletWebServerFactory> (){
            @Override
            public void customize (ConfigurableServletWebServerFactory factory){
                factory.setPort(8090);
            }
        } ;
    }
}
