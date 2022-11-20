package Cason.p2p.base.service.serviceImpl;

import Cason.p2p.base.domain.Account;
import Cason.p2p.base.domain.Iplog;
import Cason.p2p.base.domain.Logininfo;
import Cason.p2p.base.domain.Userinfo;
import Cason.p2p.base.mapper.AccountMapper;
import Cason.p2p.base.mapper.IplogMapper;
import Cason.p2p.base.mapper.LogininfoMapper;
import Cason.p2p.base.mapper.UserinfoMapper;
import Cason.p2p.base.service.IAccountService;
import Cason.p2p.base.service.ILogininfoService;
import Cason.p2p.base.service.IUserinfoService;
import Cason.p2p.base.util.BidConst;
import Cason.p2p.base.util.MD5;
import Cason.p2p.base.util.UserContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author Cason
 * @date 2022-10-29 21:36
 */
@Service
public class LogininforServiceImpl implements ILogininfoService {
    @Autowired
    private LogininfoMapper logininfoMapper;
    @Autowired
    private IAccountService accountService;
    @Autowired
    private IUserinfoService userinfoService;
    @Autowired
    private IplogMapper iplogMapper;
    @Override
    public void register(String username, String password) {
        //判断用户是否存在
        int count = this.logininfoMapper.getCountByUsername(username);
        //不存在就设置值并保存
        if(count <=0){
            Logininfo newInfo = new Logininfo();
            newInfo.setUsername(username);
            newInfo.setPassword(MD5.encode(password));
            newInfo.setState(Logininfo.STATE_NORMAL);
            newInfo.setUserType(Logininfo.USER_CLEINT);
            logininfoMapper.insert(newInfo);

            //初始化userinfo和account
            Account account = new Account();
            account.setId(newInfo.getId());
            this.accountService.add(account);

            Userinfo userinfo = new Userinfo();
            userinfo.setId(newInfo.getId());
            this.userinfoService.add(userinfo);
        }
        //存在就直接抛出错误
        else{
            throw new RuntimeException("用户名已存在");
        }
    }

    @Override
    public boolean checkUsername(String username) {
        return this.logininfoMapper.getCountByUsername(username) > 0;
    }

    @Override
    public Logininfo login(String username, String password,String ip,int userType) {
        Logininfo current = this.logininfoMapper.login(username,MD5.encode(password),userType);
        Iplog iplog = new Iplog();
        iplog.setIp(ip);
        iplog.setLoginTime(new Date());
        iplog.setUserName(username);
        iplog.setUserType(userType);
        if(current != null){
            //放到UserContext；
            UserContext.putCurrent(current);
            iplog.setState(Iplog.STATE_SUCCESS);
        }
        else{
            iplog.setState(Iplog.STATE_FAILED);
        }
        iplogMapper.insert(iplog);
        return current;
    }

    @Override
    public void initAdmin() {
        //查询管理员的数量;
        int count = logininfoMapper.getCountByUserType(Logininfo.USER_MANAGER);
        //如果不存在管理员就新建
        if(count == 0){
            Logininfo admin = new Logininfo();
            admin.setUsername(BidConst.DEFAULT_ADMIN_NAME);
            admin.setPassword(MD5.encode(BidConst.DEFAULT_AMMIN_PASSWORD));
            admin.setUserType(Logininfo.USER_MANAGER);
            admin.setState(Logininfo.STATE_NORMAL);
            this.logininfoMapper.insert(admin);
        }
    }
}
