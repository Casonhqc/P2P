package Cason.p2p.base.domain;

import Cason.p2p.base.util.BidConst;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * 用户对应的账户信息
 * @author Cason
 * @date 2022-11-01 19:43
 */
@Getter
@Setter
public class Account extends BaseDomain {
    private int version;
    private String tradePassword;
    private BigDecimal usableAmount = BidConst.ZERO;
    private BigDecimal freezedAmount = BidConst.ZERO;
    private BigDecimal unReceiveInterest = BidConst.ZERO;
    private BigDecimal unReceivePrincipal = BidConst.ZERO;
    private BigDecimal unReturnAmount = BidConst.ZERO;
    private BigDecimal remainBorrowLimit = BidConst.INIT_BORRWO_LIMIT;
    private BigDecimal borrowLimit = BidConst.INIT_BORRWO_LIMIT;

    public BigDecimal getTotalAmount(){
        return usableAmount.add(this.freezedAmount).add(this.unReceivePrincipal);
    }
}
