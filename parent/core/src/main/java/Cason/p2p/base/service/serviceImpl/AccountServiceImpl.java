package Cason.p2p.base.service.serviceImpl;

import Cason.p2p.base.domain.Account;
import Cason.p2p.base.mapper.AccountMapper;
import Cason.p2p.base.service.IAccountService;
import Cason.p2p.base.util.UserContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Cason
 * @date 2022-11-01 21:23
 */
@Service
public class AccountServiceImpl implements IAccountService {
    @Autowired
    private AccountMapper accountMapper;
    @Override
    public void update(Account account) {
        int ret = this.accountMapper.updateByPrimaryKey(account);
        if(ret == 0){
            throw new RuntimeException("乐观锁失败:"+account.getId());
        }
    }

    @Override
    public void add(Account account) {
        this.accountMapper.insert(account);
    }

    @Override
    public Account get(Long id) {
        return this.accountMapper.selectByPrimaryKey(id);
    }

    @Override
    public Account getCurrent() {
        return this.get(UserContext.getCurrent().getId());
    }
}
