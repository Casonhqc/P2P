package Cason.p2p.base.service;

import Cason.p2p.base.domain.SystemDictionary;
import Cason.p2p.base.domain.SystemDictionaryItem;
import Cason.p2p.base.query.PageResult;
import Cason.p2p.base.query.SystemDictionaryQueryObject;

import java.util.List;

/**
 * 数据字典服务
 * @author Cason
 * @date 2022-11-15 21:56
 */
public interface ISystemDictionaryService {
    /**
     * 数据字典分类的分页
     * @param qo
     * @return
     */
    PageResult queryDics(SystemDictionaryQueryObject qo);

    /**
     * 数据字典分类的保存（插入）或更新
     * @param dictionary
     */
    void saveORUpdateDic(SystemDictionary dictionary);

    /**
     * 列出所有的数据字典分类
     * @return
     */
    List<SystemDictionary> listAllDics();

    /**
     * 数据字典明细的分页功能
     * @param qo
     * @return
     */
    PageResult queryItem(SystemDictionaryQueryObject qo);

    /**
     * 保存或修改数据字典明细
     * @param item
     */
    void saveOrUpdateItem(SystemDictionaryItem item);

    /**
     * 根据父节点的索引返回数据字典明细的集合
     * @param
     * @return
     */
    List<SystemDictionaryItem> listByParentSn(String sn);
}
