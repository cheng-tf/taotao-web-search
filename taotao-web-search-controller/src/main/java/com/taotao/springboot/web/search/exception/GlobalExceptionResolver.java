package com.taotao.springboot.web.search.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <p>Title: GlobalExceptionResolver</p>
 * <p>Description: 全局异常处理器</p>
 * <p>Company: bupt.edu.cn</p>
 * <p>Created: 2018-05-06 15:15</p>
 * @author ChengTengfei
 * @version 1.0
 */
@Component
public class GlobalExceptionResolver implements HandlerExceptionResolver {

    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionResolver.class);

    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response,
                                         Object handler, Exception e) {
        log.info("进入全局异常处理器。");
        log.debug("测试handler的类型：" + handler.getClass());
        e.printStackTrace();// 控制台打印异常
        log.error("系统发生异常, error={}", e);// 日志文件写入异常
        // 发邮件(JavaMail)
        // 发短信
        // 错误页面
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("message", "您的电脑有问题，请稍后重试。");
        modelAndView.setViewName("error/exception");
        return modelAndView;
    }

}
