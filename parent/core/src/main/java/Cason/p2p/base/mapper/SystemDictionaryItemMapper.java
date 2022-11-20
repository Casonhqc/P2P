package Cason.p2p.base.mapper;

import Cason.p2p.base.domain.SystemDictionaryItem;
import Cason.p2p.base.query.SystemDictionaryQueryObject;

import java.util.List;

public interface SystemDictionaryItemMapper {

    int insert(SystemDictionaryItem record);

    SystemDictionaryItem selectByPrimaryKey(Long id);


    int updateByPrimaryKey(SystemDictionaryItem record);

    int queryForCount(SystemDictionaryQueryObject qo);

    List<SystemDictionaryItem> query(SystemDictionaryQueryObject qo);

    /**
     * 根据父节点查数据字典明细
     * @param sn
     * @return
     */
    List<SystemDictionaryItem> selectByPsn(String sn);
}