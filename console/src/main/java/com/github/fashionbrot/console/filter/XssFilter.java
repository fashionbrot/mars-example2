package com.github.fashionbrot.console.filter;

import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * SpringBootApplication 上使用@ServletComponentScan 注解后
 * Servlet可以直接通过@WebServlet注解自动注册
 * Filter可以直接通过@WebFilter注解自动注册
 * Listener可以直接通过@WebListener 注解自动注册
 */

/*@WebFilter(filterName="MyFilter",urlPatterns= {"/*"})
@Order(FilterRegistrationBean.HIGHEST_PRECEDENCE)*/
public class XssFilter extends OncePerRequestFilter {

    /**
     * 是否过滤富文本内容
     */
    private static boolean IS_INCLUDE_RICH_TEXT = true;

    /**
     * 跳过的路径
     */
    public List<String> excludes = new ArrayList<>();

    @Override
    protected void initFilterBean() {
        FilterConfig filterConfig = Objects.requireNonNull(getFilterConfig());
        String isIncludeRichText = filterConfig.getInitParameter("isIncludeRichText");
        if (StringUtils.isNotBlank(isIncludeRichText)) {
            IS_INCLUDE_RICH_TEXT = BooleanUtils.toBoolean(isIncludeRichText);
        }
        String temp = filterConfig.getInitParameter("excludes");
        if (temp != null) {
            String[] url = temp.split(",");
            excludes.addAll(Arrays.asList(url));
        }
    }

    @Override
    public void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        response.setHeader("Set-Cookie", "name=value; HttpOnly"); //向所有会话cookie中添加“HttpOnly”属性。
        if (handleExcludeUrl(request, response)) {
            filterChain.doFilter(request, response);
        }else {
            XssHttpServletRequestWrapper xssRequest = new XssHttpServletRequestWrapper(request, IS_INCLUDE_RICH_TEXT);
            filterChain.doFilter(xssRequest, response);
        }
    }

    private boolean handleExcludeUrl(HttpServletRequest request, HttpServletResponse response) {
        if (excludes == null || excludes.isEmpty()) {
            return false;
        }
        String url = request.getServletPath();
        for (String pattern : excludes) {
            Pattern p = Pattern.compile("^" + pattern);
            Matcher m = p.matcher(url);
            if (m.find()) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void destroy() {
    }
}