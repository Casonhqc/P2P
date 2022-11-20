package Cason.mgrsite.base;

import Cason.p2p.base.domain.Iplog;
import Cason.p2p.base.domain.SystemDictionary;
import Cason.p2p.base.domain.SystemDictionaryItem;
import Cason.p2p.base.query.SystemDictionaryQueryObject;
import Cason.p2p.base.service.ISystemDictionaryService;
import Cason.p2p.base.util.JSONResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 数据字典控制器
 * @author Cason
 * @date 2022-11-15 22:03
 */
@Controller
public class SystemDictionaryController {
    /**
     * 数据字典分类的分页列表
     */
    @Autowired
    private ISystemDictionaryService systemDictionaryService;


    @RequestMapping("systemDictionary_list")
    public String systemDictionaryList(@ModelAttribute("qo")SystemDictionaryQueryObject qo, Model model){
        model.addAttribute("pageResult",this.systemDictionaryService.queryDics(qo));
        return "systemdic/systemDictionary_list";
    }

    /**
     *数据字典分类的保存和修改
     * @param dictionary 字典分类
     * @return JSON模板
     */
    @RequestMapping("systemDictionary_update")
    @ResponseBody
    public JSONResult systemDictionaryUpdate(SystemDictionary dictionary){
       this.systemDictionaryService.saveORUpdateDic(dictionary);
       return new JSONResult();

    }

    /**
     * 数据字典明细的分页列表
     * @param qo
     * @param model
     * @return
     */
    @RequestMapping("systemDictionaryItem_list")
    public String systemDictionaryItemList(@ModelAttribute("qo")SystemDictionaryQueryObject qo, Model model){
        model.addAttribute("pageResult",this.systemDictionaryService.queryItem(qo));
        model.addAttribute("systemDictionaryGroups",this.systemDictionaryService.listAllDics());
        return "systemdic/systemDictionaryItem_list";
    }

    /**
     * 添加修改字典明细
     */
    @RequestMapping("systemDictionaryItem_update")
    @ResponseBody
    public JSONResult systemDictionaryItemUpdate(SystemDictionaryItem item) {
        this.systemDictionaryService.saveOrUpdateItem(item);
        return new JSONResult();
    }
}
