package Cason.p2p.base.controller;

import Cason.p2p.base.domain.Logininfo;
import Cason.p2p.base.service.ILogininfoService;
import Cason.p2p.base.util.JSONResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * 用于注册，登录相关
 * @author Cason
 * @date 2022-10-29 22:32
 */
@Controller
public class RegisterController {
    @Autowired
    private ILogininfoService logininfoService;

    @RequestMapping("register")
    @ResponseBody
    public JSONResult register(String username, String password){
        JSONResult result = new JSONResult();
        try{
            logininfoService.register(username,password);

        }
        catch (RuntimeException e){
            result.setSuccess(false);
            result.setMsg(e.getMessage());
        }
        return result;
    }

    @RequestMapping("checkUsername")
    @ResponseBody
    public boolean checkUsername(String username){
        return !this.logininfoService.checkUsername(username);
    }

    @RequestMapping("login")
    @ResponseBody
    public JSONResult login(String username, String password, HttpServletRequest request){
        JSONResult result = new JSONResult();

        Logininfo current = logininfoService.login(username,password,request.getRemoteAddr(),Logininfo.USER_CLEINT);
        if(current == null){
            result.setSuccess(false);
            result.setMsg("用户名或密码错误，请重试");
        }

        return result;
    }

}
