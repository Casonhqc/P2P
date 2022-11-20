package Cason.p2p.base.vo;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * 存放验证码相关的内容，这个对象放到session中
 * @author Cason
 * @date 2022-11-12 21:22
 */
@Getter
@Setter
public class VerifyCodeVo {
    private String verifyCode;
    private String phoneNumber;
    private Date lastSendTime;
}
