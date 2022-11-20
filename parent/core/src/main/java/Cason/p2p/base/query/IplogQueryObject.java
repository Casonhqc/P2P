package Cason.p2p.base.query;

import Cason.p2p.base.util.DateUtil;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang.StringUtils;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * 日志查询对象
 * @author Cason
 * @date 2022-11-04 22:28
 */
@Setter
@Getter
public class IplogQueryObject extends QueryObject{
    private Date beginDate;
    private Date endDate;
    private String username;
    private int state = -1;
    private int userType = -1;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    public void setBeginDate(Date beginDate){
        this.beginDate = beginDate;
    }

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    public void setEndDate(Date endDate){
        this.endDate = endDate;
    }

    public Date getEndDate(){
        return this.endDate == null? null: DateUtil.endOfDay(endDate);
    }

    public String getUsername(){
        return StringUtils.isNotEmpty(username) ? username:null;
    }
}
