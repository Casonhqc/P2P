package Cason.p2p.base.domain;

import com.alibaba.fastjson.JSONObject;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

/**
 * 数据字典分类
 * @author Cason
 * @date 2022-11-01 19:41
 */
@Setter
@Getter
public class SystemDictionary extends BaseDomain {
    private String sn;
    private String title;

    /**
     * 返回当前的json字符串
     * @return
     */
    public String getJsonString(){
        Map<String,Object> json=new HashMap<>();
        json.put("id",id);
        json.put("sn", sn);
        json.put("title", title);
        return JSONObject.toJSONString(json);
    }
}
