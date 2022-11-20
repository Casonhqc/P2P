package Cason.p2p.base.service;

import Cason.p2p.base.domain.Account;

/**
 * 账户相关
 * @author Cason
 * @date 2022-11-01 21:22
 */
public interface IAccountService {
    /**
     * 更新信息
     * @param account
     */
    void update(Account account);

    /**
     * 新增
     * @param account
     */
    void add(Account account);

    /**
     * 通过logininfo的id获取account实体
     * @param id
     * @return
     */
    Account get(Long id);

    /**
     * 相当于get的加强版，将调用session获得logininfo的id的步骤封装一下
     * @return
     */
    Account getCurrent();

}
