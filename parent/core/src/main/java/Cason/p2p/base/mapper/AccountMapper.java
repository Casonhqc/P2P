package Cason.p2p.base.mapper;

import Cason.p2p.base.domain.Account;
import java.util.List;

public interface AccountMapper {

    int insert(Account record);

    Account selectByPrimaryKey(Long id);


    int updateByPrimaryKey(Account record);
}