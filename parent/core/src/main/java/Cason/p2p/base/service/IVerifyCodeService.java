package Cason.p2p.base.service;

/**
 * 验证码相关
 * @author Cason
 * @date 2022-11-12 21:17
 */
public interface IVerifyCodeService {
    /**
     * 发送验证码
     * @param phoneNumber
     */
    public void sendVerifyCode(String phoneNumber);

    /**
     * 检验验证码的合法性
     * @param phoneNumber
     * @param verifyCode
     * @return
     */
    boolean verify(String phoneNumber,String verifyCode);
}


