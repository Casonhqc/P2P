package Cason.p2p.base.controller;

import Cason.p2p.base.query.IIplogService;
import Cason.p2p.base.query.IplogQueryObject;
import Cason.p2p.base.util.RequireLogin;
import Cason.p2p.base.util.UserContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.PublicKey;

/**
 * 登录日志
 * @author Cason
 * @date 2022-11-04 23:12
 */
@Controller
public class IplogController {
    @Autowired
    private IIplogService iIplogService;

    /**
     * 个人用户登录记录列表
     * @param qo
     * @param model
     * @return
     */
    @RequireLogin
    @RequestMapping("ipLog")
    public String iPlogString(@ModelAttribute("qo") IplogQueryObject qo, Model model){
        qo.setUsername(UserContext.getCurrent().getUsername());
        model.addAttribute("pageResult",this.iIplogService.query(qo));
        return "ipLog_list";
    }
}
