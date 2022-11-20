package Cason.p2p.base.util;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 登陆拦截
 * @author Cason
 * @date 2022-11-12 14:34
 */
public class CheckLoginInterceptor extends HandlerInterceptorAdapter {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if(handler instanceof HandlerMethod){
            HandlerMethod hm = (HandlerMethod) handler;
            RequireLogin rl = hm.getMethodAnnotation(RequireLogin.class);

            if(rl != null && UserContext.getCurrent() == null){
                response.sendRedirect("/login.html");
                return false;
            }

        }
        return super.preHandle(request,response,handler);
    }
}
