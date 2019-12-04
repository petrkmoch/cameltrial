package com.example.demo2;

import org.apache.camel.component.servlet.CamelHttpTransportServlet;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Demo2Application {

    public static void main(String[] args) {
        SpringApplication.run(Demo2Application.class, args);
    }

    @Bean
    public ServletRegistrationBean camelServletRegistration() {
        ServletRegistrationBean registrationBean = new ServletRegistrationBean(new CamelHttpTransportServlet(), "/camel/*");
        registrationBean.setName("CamelServlet");
        return registrationBean;
    }


}
