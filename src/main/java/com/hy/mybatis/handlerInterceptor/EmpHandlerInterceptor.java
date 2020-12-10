package com.hy.mybatis.handlerInterceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class EmpHandlerInterceptor implements HandlerInterceptor{

     /*Controller执行前调用此方法
     返回true表示继续执行，返回false中止执行
     这里可以加入登录校验、权限拦截等*/
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object obj) throws Exception {
        String username = (String)request.getSession().getAttribute("username");
        System.out.println("拦截器");
        if(username==null || "".equals(username)){
            System.out.println("拦截器无username");
            request.getRequestDispatcher("/view/load.jsp").forward(request,response);
            //response.getWriter().write(JSONObject.toJSONString(404));
            return false;
        }
        // 设置为true，放行
        return true;
    }


}
