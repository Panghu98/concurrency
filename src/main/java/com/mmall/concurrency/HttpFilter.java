package com.mmall.concurrency;


import com.mmall.concurrency.example.threadLocal.RequestHolder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @author panghu
 * @Title: HttpFilter
 * @ProjectName cuncurrency_demo
 * @Description: TODO
 * @date 19-2-19 下午9:02
 */
@Slf4j
public class HttpFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        /*       request.getSession().getAttribute("attribute"); */
        log.info("do filter,{},{}", Thread.currentThread().getId(), request.getServletPath());
        RequestHolder.add(Thread.currentThread().getId());
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
