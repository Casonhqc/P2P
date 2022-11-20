package Cason.p2p.base.controller;

import Cason.p2p.base.domain.Realauth;
import Cason.p2p.base.domain.Userinfo;
import Cason.p2p.base.service.IRealAuthService;
import Cason.p2p.base.service.IUserinfoService;
import Cason.p2p.base.util.JSONResult;
import Cason.p2p.base.util.RequireLogin;
import Cason.p2p.base.util.UploadUtil;
import Cason.p2p.base.util.UserContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;

@Controller
/**
 * 管理实名认证的页面
 * @author Cason
 * @date 2022-11-18 20:03
 */
public class RealAuthController {
    @Autowired
    private IUserinfoService userinfoService;

    @Autowired
    private IRealAuthService realAuthService;

    @Autowired
    private ServletContext servletContext;

    @RequestMapping("/realAuth")
    @RequireLogin
    public String realAuth(Model model){
        //获取当前用户
        Userinfo current = userinfoService.getCurrent();

        //如果用户已经实名认证了
        if(current.getIsRealAuth()){
            //根据userinfo上的realAuthId找到实名认证对象
            model.addAttribute(this.realAuthService.get(current.getRealAuthId()));
            //auditing为false，表示已实名
            model.addAttribute("auditing",false);
            return "realAuth_result";
        }
        else{
            if(current.getRealAuthId()!=null){
                //有实名对象id
                model.addAttribute("auditing",true);
                return "realAuth_result";
            }
            else{
                //去到实名认证填写页面
                return "realAuth";
            }
        }

    }

    /**
     * 将上传的图片存到本地并返回存放路径
     * @param file
     * @return
     */
    @RequestMapping("/realAuthUpload")
    @ResponseBody
    public String realAuthUpdate(MultipartFile file ){
        String basePath = servletContext.getRealPath("/upload");
        String fileName = UploadUtil.upload(file,basePath);
        return "/upload/"+fileName;
    }

    @RequestMapping("realAuth_save")
    @RequireLogin
    @ResponseBody
    public JSONResult realAuthSave(Realauth realauth){
        this.realAuthService.apply(realauth);
        return new JSONResult();
    }
}
