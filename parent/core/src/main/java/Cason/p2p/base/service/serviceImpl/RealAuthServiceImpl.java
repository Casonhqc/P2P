package Cason.p2p.base.service.serviceImpl;

import Cason.p2p.base.domain.Realauth;
import Cason.p2p.base.domain.Userinfo;
import Cason.p2p.base.mapper.RealauthMapper;
import Cason.p2p.base.service.IRealAuthService;
import Cason.p2p.base.service.IUserinfoService;
import Cason.p2p.base.util.UserContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author Cason
 * @date 2022-11-18 20:27
 */
@Service
public class RealAuthServiceImpl implements IRealAuthService {

    @Autowired
    private RealauthMapper realauthMapper;

    @Autowired
    private IUserinfoService userinfoService;

    @Override
    public Realauth get(Long id) {
        return this.realauthMapper.selectByPrimaryKey(id);
    }

    @Override
    public void apply(Realauth realauth) {
        Userinfo current = this.userinfoService.getCurrent();

        //如果当前用户没有实名过且没有提交过
        if(!current.getIsRealAuth() && current.getRealAuthId() == null){
            //给当前的实名认证对象补充信息
            realauth.setApplier(UserContext.getCurrent());
            realauth.setApplyTime(new Date());
            realauth.setState(Realauth.STATE_NORMAL);

            //保存实名对象
            this.realauthMapper.insert(realauth);

            current.setRealAuthId(realauth.getId());
            this.userinfoService.update(current);
        }
    }
}
