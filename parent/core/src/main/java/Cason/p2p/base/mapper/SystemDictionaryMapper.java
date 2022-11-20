package Cason.p2p.base.mapper;

import Cason.p2p.base.domain.SystemDictionary;
import Cason.p2p.base.query.SystemDictionaryQueryObject;

import java.util.List;

public interface SystemDictionaryMapper {

    int insert(SystemDictionary record);

    SystemDictionary selectByPrimaryKey(Long id);


    int updateByPrimaryKey(SystemDictionary record);

    /**
     * 查询数据总数
     * @param qo
     * @return
     */
    int queryForCount(SystemDictionaryQueryObject qo);

    /**
     * 查询返回数据集合
     * @return
     */
    List<SystemDictionary> query(SystemDictionaryQueryObject qo);

    List<SystemDictionary> selectAll();
}