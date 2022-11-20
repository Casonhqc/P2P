package Cason.p2p.base.domain;

import Cason.p2p.base.util.BitStatesUtils;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * 用户相关信息
 * @author Cason
 * @date 2022-11-01 19:32
 */
@Setter
@Getter
public class Userinfo extends BaseDomain {
    private int version; //版本号
    private long bitState = 0; // 用户状态码
    private String realName;
    private String idNumber;
    private String phoneNumber;
    private String email;
    private int score;
    private Long realAuthId;// 该用户对应的实名认证对象id
    private SystemDictionaryItem incomeGrade;// 收入
    private SystemDictionaryItem marriage;//
    private SystemDictionaryItem kidCount;//
    private SystemDictionaryItem educationBackground;//
    private SystemDictionaryItem houseCondition;//

    /**
     * 判断是否绑定手机
     * @return
     */
    public boolean getisBindPhone(){
        return BitStatesUtils.hasState(this.bitState,BitStatesUtils.OP_BIND_PHONE);
    }

    public void addState(long state){
        this.setBitState(BitStatesUtils.addState(this.bitState,state));
    }

    /**
     * 判断是否绑定邮箱
     * @return
     */
    public boolean getisBindEmail(){
        return  BitStatesUtils.hasState(this.bitState,BitStatesUtils.OP_BIND_EMAIL);
    }

    /**
     * 返回用户是否已经填写了基本资料
     *
     * @return
     */
    public boolean getIsBasicInfo() {
        return BitStatesUtils.hasState(this.bitState,
                BitStatesUtils.OP_BASIC_INFO);
    }

    /**
     * 返回用户是否已经实名认证
     *
     * @return
     */
    public boolean getIsRealAuth() {
        return BitStatesUtils.hasState(this.bitState,
                BitStatesUtils.OP_REAL_AUTH);
    }

    /**
     * 返回用户是否视频认证
     *
     * @return
     */
    public boolean getIsVedioAuth() {
        return BitStatesUtils.hasState(this.bitState,
                BitStatesUtils.OP_VEDIO_AUTH);
    }


}
