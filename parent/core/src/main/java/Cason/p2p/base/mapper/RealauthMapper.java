package Cason.p2p.base.mapper;

import Cason.p2p.base.domain.Realauth;
import java.util.List;

public interface RealauthMapper {

    int insert(Realauth record);

    Realauth selectByPrimaryKey(Long id);

    List<Realauth> selectAll();

}