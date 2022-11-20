package Cason.p2p.base.util;

import java.math.BigDecimal;

/**
 * 系统得到常量
 * @author Cason
 * @date 2022-11-01 19:46
 */
public class BidConst {
    /**
     * 定义存储精度
     */
    public static final int STORE_SCALE = 4;
    /**
     * 定义运算精度
     */
    public static final int CAL_SCALE = 8;
    /**
     * 定义显示精度
     */
    public static final int DISPLAY_SCALE = 2;

    /**
     * 定义系统级别的0
     */
    public static final BigDecimal ZERO = new BigDecimal("0.0000");

    /**
     * 定义初始授信额度
     */
    public static final BigDecimal INIT_BORRWO_LIMIT = new BigDecimal("5000.0000");
    /**
     * 默认的管理员用户名和密码
     */
    public static final String DEFAULT_ADMIN_NAME = "admin";
    public static final String DEFAULT_AMMIN_PASSWORD = "admin";

    /**
     * 设置验证码有效期
     */
    public static final int VERIFYCODE_VALIDATE_SECONDS = 300;

    /**
     * 设置邮件有效期
     */
    public static final int VERIFYCODE_VALIDATE_DAYS = 5;

    /**
     * 设置最低借款分数
     */
    public static final int BASE_BORROW_SCORE = 30;
}
