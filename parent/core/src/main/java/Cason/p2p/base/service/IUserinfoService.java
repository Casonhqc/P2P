package Cason.p2p.base.service;

import Cason.p2p.base.domain.Account;
import Cason.p2p.base.domain.Userinfo;

/**
 * 用户相关服务
 * @author Cason
 * @date 2022-11-01 21:26
 */
public interface IUserinfoService {
    /**
     * 更新
     * @param userinfo
     */
    void update(Userinfo userinfo);

    /**
     * 新增
     * @param userinfo
     */
    void add(Userinfo userinfo);

    /**
     * 通过logininfo的id获取userinfo实体
     * @param id
     * @return
     */
    Userinfo get(Long id);

    /**
     * 绑定手机号
     * @param phoneNumber
     * @param verifyCode
     */
    void bindPhone(String phoneNumber, String verifyCode);

    /**
     * 获得当前的用户信息
     * @return
     */
    Userinfo getCurrent();

    /**
     * 发送验证邮箱
     * @param email
     */
    void sendVerifyEmail(String email);

    /**绑定邮箱
     *
     * @param key
     */
    void bindEmail(String key);

    /**
     * 用于个人资料页面的保存
     * @param userinfo
     */
    void updateBasicInfo(Userinfo userinfo);
}
