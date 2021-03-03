package com.github.fashionbrot.console.config;

import com.github.fashionbrot.console.filter.XssFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class XssFilterConfig {

    @Bean
    public FilterRegistrationBean<XssFilter> myFilterFilterRegistrationBean(){
        FilterRegistrationBean<XssFilter> bean = new FilterRegistrationBean<>();
        bean.setFilter(new XssFilter());
        bean.setName("XssFilter");
        bean.setEnabled(true);
        bean.addUrlPatterns("/*");
        bean.setOrder(FilterRegistrationBean.HIGHEST_PRECEDENCE);
        Map<String, String> initParameters = new HashMap<>(4);
        initParameters.put("excludes", "/favicon.ico,/img/*,/js/*,/css/*,/fonts/*,/i18n/*,/ruoyi/*,/image/*,/ajax/*");
        initParameters.put("isIncludeRichText", "true");
        bean.setInitParameters(initParameters);
        return bean;
    }

}
