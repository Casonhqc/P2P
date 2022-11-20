package Cason.p2p.base.util;

import Cason.p2p.base.domain.Logininfo;
import Cason.p2p.base.vo.VerifyCodeVo;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpSession;

/**
 * 用于存放当前用户上下文
 * @author Cason
 * @date 2022-10-31 13:31
 */
public class UserContext {
    public static final String USER_IN_SESSION = "logininfo";
    public static final String VERYFICODE_IN_SESSION = "verifycode";

    /**
     * 实现在Service中获取session
     * @return
     */
    private static HttpSession getSession(){
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getSession();
    }

    /**
     * 登录信息的存取
     * @param current
     */
    public static void putCurrent(Logininfo current){
        //得到session，放入current
        getSession().setAttribute(USER_IN_SESSION,current);
    }

    public static Logininfo getCurrent(){
        //得到session中的current
        return (Logininfo) getSession().getAttribute(USER_IN_SESSION);
    }

    /**
     * 验证码的存取
     * @param vo
     */
    public static void putCurrentVerifyCode(VerifyCodeVo vo){
        getSession().setAttribute(VERYFICODE_IN_SESSION,vo);
    }

    public static VerifyCodeVo getCurrentVerifyCode(){
        return (VerifyCodeVo) getSession().getAttribute(VERYFICODE_IN_SESSION);
    }

}
