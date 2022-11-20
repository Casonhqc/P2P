package Cason.p2p.base.mapper;

import Cason.p2p.base.domain.Iplog;
import Cason.p2p.base.query.IplogQueryObject;

import java.util.List;

public interface IplogMapper {

    int insert(Iplog record);



    /**
     * 查询数据总数
     * @param qo
     * @return
     */
    int queryForCount(IplogQueryObject qo);

    /**
     * 查询当前页的数据集合
     * @param qo
     * @return
     */
    List<Iplog> query(IplogQueryObject qo);
}