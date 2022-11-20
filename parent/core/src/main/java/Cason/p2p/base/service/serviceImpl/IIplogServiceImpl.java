package Cason.p2p.base.service.serviceImpl;

import Cason.p2p.base.domain.Iplog;
import Cason.p2p.base.mapper.IplogMapper;
import Cason.p2p.base.query.IIplogService;
import Cason.p2p.base.query.IplogQueryObject;
import Cason.p2p.base.query.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Cason
 * @date 2022-11-04 22:44
 */
@Service
public class IIplogServiceImpl implements IIplogService {
    @Autowired
    private IplogMapper iplogMapper;

    @Override
    public PageResult query(IplogQueryObject qo) {
        int count = iplogMapper.queryForCount(qo);
        if(count > 0){
            List<Iplog> list = iplogMapper.query(qo);
            return new PageResult(list,count, qo.getCurrentPage(), qo.getPageSize());

        }
        return PageResult.empty(qo.getPageSize());
    }
}
