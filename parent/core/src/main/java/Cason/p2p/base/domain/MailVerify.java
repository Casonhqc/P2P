package Cason.p2p.base.domain;


import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**邮箱验证对象
 * @author Cason
 * @date 2022-11-14 19:51
 */
@Getter
@Setter
public class MailVerify extends BaseDomain{
    private Long userinfoId;
    private String email;
    private String uuid;
    private Date sendDate;
}
