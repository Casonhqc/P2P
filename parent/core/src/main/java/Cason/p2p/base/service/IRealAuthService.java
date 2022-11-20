package Cason.p2p.base.service;

import Cason.p2p.base.domain.Realauth;

/**
 * 实名认证对象服务
 * @author Cason
 * @date 2022-11-18 20:24
 */
public interface IRealAuthService {

    /**
     * 取出实名认证对象
     * @param id
     * @return
     */
    Realauth get(Long id);

    /**
     * 申请实名认证
     * @param realauth
     */
    void apply(Realauth realauth);
}
