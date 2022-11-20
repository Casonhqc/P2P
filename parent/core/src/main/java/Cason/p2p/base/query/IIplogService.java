package Cason.p2p.base.query;

/**
 * @author Cason
 * @date 2022-11-04 22:43
 */
public interface IIplogService {
    /**
     * 分页查询
     * @param qo
     * @return
     */
    PageResult query(IplogQueryObject qo);
}
