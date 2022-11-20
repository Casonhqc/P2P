package Cason.mgrsite.base;

import Cason.p2p.base.query.IIplogService;
import Cason.p2p.base.query.IplogQueryObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 查看登录日志
 * @author Cason
 * @date 2022-11-11 16:52
 */
@Controller
public class IplogController {
    @Autowired
    private IIplogService iplogService;

    @RequestMapping("ipLog")
    public String ipLog(@ModelAttribute("qo")IplogQueryObject qo, Model model){
        model.addAttribute("pageResult",iplogService.query(qo));
        return "ipLog/list";
    }
}
