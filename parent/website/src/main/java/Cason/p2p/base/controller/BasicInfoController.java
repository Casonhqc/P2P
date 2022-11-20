package Cason.p2p.base.controller;

import Cason.p2p.base.domain.Userinfo;
import Cason.p2p.base.query.PageResult;
import Cason.p2p.base.service.ISystemDictionaryService;
import Cason.p2p.base.service.IUserinfoService;
import Cason.p2p.base.util.JSONResult;
import Cason.p2p.base.util.RequireLogin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 个人资料页面的控制器
 * @author Cason
 * @date 2022-11-18 15:38
 */
@Controller
public class BasicInfoController {
    @Autowired
    private IUserinfoService userinfoService;

    @Autowired
    private ISystemDictionaryService systemDictionaryService;

    /**
     * 个人资料页面展示
     * @param model
     * @return
     */
    @RequestMapping("basicInfo")
    @RequireLogin
    public String basicInfo(Model model){
        //添加:当前用户;
        model.addAttribute("userinfo",this.userinfoService.getCurrent());
        //添加:所有下拉列表相关内容
        model.addAttribute("educationBackgrounds",this.systemDictionaryService.listByParentSn("educationBackground"));
        model.addAttribute("incomeGrades",this.systemDictionaryService.listByParentSn("incomeGrade"));
        model.addAttribute("marriages",this.systemDictionaryService.listByParentSn("marriage"));
        model.addAttribute("kidCounts",this.systemDictionaryService.listByParentSn("kidCount"));
        model.addAttribute("houseConditions",this.systemDictionaryService.listByParentSn("houseCondition"));
        return "userInfo";
    }

    @RequestMapping("basicInfo_save")
    @ResponseBody
    public JSONResult save(Userinfo userinfo){
        this.userinfoService.updateBasicInfo(userinfo);
        return new JSONResult();
    }
}
