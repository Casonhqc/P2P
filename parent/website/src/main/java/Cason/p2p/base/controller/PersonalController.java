package Cason.p2p.base.controller;

import Cason.p2p.base.domain.Logininfo;
import Cason.p2p.base.domain.Userinfo;
import Cason.p2p.base.service.IAccountService;
import Cason.p2p.base.service.IUserinfoService;
import Cason.p2p.base.util.JSONResult;
import Cason.p2p.base.util.RequireLogin;
import Cason.p2p.base.util.UserContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 用户的个人中心
 * @author Cason
 * @date 2022-11-03 19:52
 */
@Controller
public class PersonalController {
    @Autowired
    private IUserinfoService userinfoService;
    @Autowired
    private IAccountService accountService;

    @RequireLogin
    @RequestMapping("/personal")
    public String personalCenter(Model model) {
        Logininfo current = UserContext.getCurrent();
        model.addAttribute("userinfo",userinfoService.get(current.getId()));
        model.addAttribute("account",accountService.get(current.getId()));
        return "personal";
    }

    /**
     * 绑定手机
     * @param phoneNumber
     * @param verifyCode
     * @return
     */
    @RequireLogin
    @RequestMapping("bindPhone")
    @ResponseBody
    public JSONResult bindPhone(String phoneNumber,String verifyCode){
        JSONResult result = new JSONResult();
        try{
            this.userinfoService.bindPhone(phoneNumber,verifyCode);

        }catch (RuntimeException e){
            result.setSuccess(false);
            result.setMsg(e.getMessage());

        }
        return result;
    }

    /**
     * 发送邮箱
     * @param email
     * @return
     */
    @RequireLogin
    @RequestMapping("sendEmail")
    @ResponseBody
    public JSONResult sendEmail(String email){
        JSONResult result = new JSONResult();
        try{
            this.userinfoService.sendVerifyEmail(email);

        }catch (RuntimeException e){
            result.setSuccess(false);
            result.setMsg(e.getMessage());

        }
        return result;
    }

    /**
     * 绑定邮箱，页面对应绑定结果
     * @param key
     * @param model
     * @return
     */
    @RequestMapping("/bindEmail")
    public String bindEmail(String key,Model model){
        try{
            this.userinfoService.bindEmail(key);
            model.addAttribute("success",true);
        }catch (RuntimeException e){
            model.addAttribute("success",false);
            model.addAttribute("msg",e.getMessage());
        }
        return "checkmail_result";
    }
}
