package Cason.p2p.base.controller;

import Cason.p2p.base.domain.Logininfo;
import Cason.p2p.base.domain.Userinfo;
import Cason.p2p.base.service.IAccountService;
import Cason.p2p.base.service.IUserinfoService;
import Cason.p2p.base.util.BidConst;
import Cason.p2p.base.util.UserContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 借款相关的控制器
 * @author Cason
 * @date 2022-11-15 20:36
 */
@Controller
public class BorrowController {
    @Autowired
    private IUserinfoService userinfoService;

    @Autowired
    private IAccountService accountService;

    /**
     * 导向到我要借款的的页面（点击我要借款时的请求）
     * @param model
     * @return
     */
    @RequestMapping("borrow")
    public String borrowIndex(Model model){
        Logininfo current = UserContext.getCurrent();
        //如果没有登录直接到静态页面
        if(current == null){
            return "redirect:borrow.html";
        }else {
            model.addAttribute("account",accountService.getCurrent());
            model.addAttribute("userinfo",userinfoService.getCurrent());
            model.addAttribute("creditBorrowScore", BidConst.BASE_BORROW_SCORE);
        }
        return "borrow";
    }
}
