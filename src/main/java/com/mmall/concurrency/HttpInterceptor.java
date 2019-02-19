package com.mmall.concurrency;

import com.mmall.concurrency.example.threadLocal.RequestHolder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author panghu
 * @Title: HttpInterceptor
 * @ProjectName cuncurrency_demo
 * @Description: TODO
 * @date 19-2-19 下午9:55
 */
@Slf4j
public class HttpInterceptor  extends HandlerInterceptorAdapter {
    /**
     * 接口处理之前
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("preHandle");
        return true;
    }

    /**
     * 接口处理之后
     * @param request
     * @param response
     * @param handler
     * @param ex
     * @throws Exception
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        /*一定要记得移除数据，否则会造成内存泄露*/
        RequestHolder.remove();
        log.info("afterCompletion");
        return;
    }
}
