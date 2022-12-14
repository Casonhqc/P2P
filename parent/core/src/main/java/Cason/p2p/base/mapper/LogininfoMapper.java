package Cason.p2p.base.mapper;

import Cason.p2p.base.domain.Logininfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface LogininfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Logininfo record);

    Logininfo selectByPrimaryKey(Integer id);

    List<Logininfo> selectAll();

    int updateByPrimaryKey(Logininfo record);

    /**
     * 根据用户名查询用户数量
     * @param username
     * @return
     */
    int getCountByUsername(String username);

    /**
     * 登录
     * @param username
     * @param encode
     * @return
     */
    Logininfo login(@Param("username") String username, @Param("password") String encode,@Param("userType") int userType);

    int getCountByUserType(int userManager);
}