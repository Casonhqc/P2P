package Cason.mgrsite.base;

import Cason.p2p.base.domain.Logininfo;
import Cason.p2p.base.service.ILogininfoService;
import Cason.p2p.base.util.JSONResult;
import com.mysql.cj.log.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * 后台登录
 * @author Cason
 * @date 2022-11-08 20:19
 */
@Controller
public class LoginController {
    @Autowired
    private ILogininfoService logininfoService;

    @RequestMapping("/login")
    @ResponseBody
    public JSONResult login(String username, String password, HttpServletRequest request){
        Logininfo current = logininfoService.login(username,password,request.getRemoteAddr(), Logininfo.USER_MANAGER);
        JSONResult result = new JSONResult();
        if(current == null){
            result.setSuccess(false);
            result.setMsg("用户名或密码错我");
        }
        return result;
    }

    @RequestMapping("index")
    public String index(){
        return "main";
    }
}
