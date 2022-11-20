package Cason.p2p.base.controller;

import Cason.p2p.base.service.IVerifyCodeService;
import Cason.p2p.base.service.serviceImpl.VerifyCodeServiceImpl;
import Cason.p2p.base.util.JSONResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 控制发送验证码
 * @author Cason
 * @date 2022-11-12 20:02
 */
@Controller
public class VerifyCodeController {
    @Autowired
    private IVerifyCodeService verifyCodeService;

    @RequestMapping("sendVerifyCode")
    @ResponseBody
    public JSONResult sendVerifyCode(String phoneNumber){
       JSONResult result = new JSONResult();
       try {
           verifyCodeService.sendVerifyCode(phoneNumber);
       } catch (RuntimeException e){
           result.setMsg(e.getMessage());
           result.setSuccess(false);
       }
       return result;
    }
}
