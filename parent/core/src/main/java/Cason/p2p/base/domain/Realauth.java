package Cason.p2p.base.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * @author Cason
 * @date 2022-11-18 17:27
 */
@Getter
@Setter
public class Realauth extends BaseDomain{
    public static final int SEX_MALE = 0;
    public static final int SEX_FEMALE = 1;

    public static final int STATE_NORMAL = 0;// 正常
    public static final int STATE_AUDIT = 1;// 审核通过
    public static final int STATE_REJECT = 2;// 审核拒绝

    private String realName;
    private int sex;
    private String idNumber;// 证件号码
    private String bornDate;// 出生日期
    private String address;// 证件地址
    private String image1;// 身份证正面图片地址
    private String image2;// 身份证反面图片地址

    protected String remark;// 审核备注
    protected int state;// 状态
    protected Logininfo applier;// 申请人;
    protected Logininfo auditor;// 审核人
    protected Date applyTime;// 申请时间
    protected Date auditTime;// 审核时间

}
