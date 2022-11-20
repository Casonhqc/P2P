package Cason.p2p.base.service.serviceImpl;

import Cason.p2p.base.domain.SystemDictionary;
import Cason.p2p.base.domain.SystemDictionaryItem;
import Cason.p2p.base.mapper.SystemDictionaryItemMapper;
import Cason.p2p.base.mapper.SystemDictionaryMapper;
import Cason.p2p.base.query.PageResult;
import Cason.p2p.base.query.SystemDictionaryQueryObject;
import Cason.p2p.base.service.ISystemDictionaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
/**
 * @author Cason
 * @date 2022-11-15 21:59
 */
public class SystemDictionaryImpl implements ISystemDictionaryService {
    @Autowired
    private SystemDictionaryMapper systemDictionaryMapper;

    @Autowired
    private SystemDictionaryItemMapper systemDictionaryItemMapper;

    @Override
    public PageResult queryDics(SystemDictionaryQueryObject qo) {
        int count = this.systemDictionaryMapper.queryForCount(qo);

        if (count != 0) {
            List<SystemDictionary> list = this.systemDictionaryMapper.query(qo);
            return new PageResult(list, count, qo.getCurrentPage(), qo.getPageSize());
        }
        return PageResult.empty(qo.getPageSize());
    }

    @Override
    public void saveORUpdateDic(SystemDictionary dictionary) {
        //获取dictio的id判断是否insert过
        if (dictionary.getId() != null) {
            this.systemDictionaryMapper.updateByPrimaryKey(dictionary);
        } else {
            this.systemDictionaryMapper.insert(dictionary);
        }

    }

    @Override
    public List<SystemDictionary> listAllDics() {
        return this.systemDictionaryMapper.selectAll();
    }

    @Override
    public PageResult queryItem(SystemDictionaryQueryObject qo) {
        int count = this.systemDictionaryItemMapper.queryForCount(qo);
        if (count != 0) {
            List<SystemDictionaryItem> list = this.systemDictionaryItemMapper.query(qo);
            return new PageResult(list, count, qo.getCurrentPage(), qo.getPageSize());


        }
        return PageResult.empty(qo.getPageSize());
    }

    @Override
    public void saveOrUpdateItem(SystemDictionaryItem item) {
        if (item.getId() == null) {
            this.systemDictionaryItemMapper.insert(item);
        } else {
            this.systemDictionaryItemMapper.updateByPrimaryKey(item);
        }
    }

    @Override
    public List <SystemDictionaryItem> listByParentSn(String sn) {
        return this.systemDictionaryItemMapper.selectByPsn(sn);
    }
}