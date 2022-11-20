package Cason.p2p.base.service;

import Cason.p2p.base.domain.Logininfo;

/**
 * 登录相关服务
 * @author Cason
 * @date 2022-10-29 21:34
 */
public interface ILogininfoService {
    /**
     * 用户注册
     * @param username
     * @param password
     */
    void register(String username, String password);

    /**
     * 检查用户名是否存在
     * 如果存在返回true
     * 不存在返回false
     * @param username
     * @return
     */
    boolean checkUsername(String username);

    /**
     * 登录操作
     * @param username
     * @param password
     */
    Logininfo login(String username, String password, String ip,int userType);

    /**
     * 创建第一个管理员
     */
    void initAdmin();
}
