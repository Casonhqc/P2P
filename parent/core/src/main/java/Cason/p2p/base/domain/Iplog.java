package Cason.p2p.base.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * 登录日志
 * @author Cason
 * @date 2022-11-03 22:11
 */
@Getter
@Setter
public class Iplog extends BaseDomain{

    public static final int STATE_SUCCESS = 1;
    public static final int STATE_FAILED = 0;

    private String ip;
    private Date loginTime;
    private String userName;
    private int state;
    private int userType;

    public String getStateDisplay(){
        return state==STATE_SUCCESS? "登录成功":"登录失败";
    }

    public String getUserTypeDisplay(){
        return userType == Logininfo.USER_CLEINT ? "前端用户":"后台用户";
    }
}
