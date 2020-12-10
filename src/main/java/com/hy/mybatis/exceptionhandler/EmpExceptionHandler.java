package com.hy.mybatis.exceptionhandler;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//@Component
public class EmpExceptionHandler implements HandlerExceptionResolver {
    @Override
    public ModelAndView resolveException(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) {
        System.out.println("+++++++++++++");
        ModelAndView modelAndView = new ModelAndView();//new MappingJackson2JsonView()
        //1、判断是哪一种异常
        String message = "";
        message = e.getMessage();

        //2、发送邮件和短信通知到相关人员
        System.out.println("异常" + message);

        //3、跳转到友好的页面，并展示描述信息
        modelAndView.setViewName("/view/500.jsp");
        modelAndView.addObject("error", message);

            /*httpServletRequest.getSession().setAttribute("error",message);
            modelAndView.addObject(JSONObject.toJSONString("500"));*/
        System.out.println("放行");
        return modelAndView;
    }
}
