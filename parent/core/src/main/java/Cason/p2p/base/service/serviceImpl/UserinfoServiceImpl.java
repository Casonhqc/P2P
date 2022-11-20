package Cason.p2p.base.service.serviceImpl;

import Cason.p2p.base.domain.Account;
import Cason.p2p.base.domain.MailVerify;
import Cason.p2p.base.domain.Userinfo;
import Cason.p2p.base.mapper.MailVerifyMapper;
import Cason.p2p.base.mapper.UserinfoMapper;
import Cason.p2p.base.service.IMailService;
import Cason.p2p.base.service.IUserinfoService;
import Cason.p2p.base.service.IVerifyCodeService;
import Cason.p2p.base.util.BidConst;
import Cason.p2p.base.util.BitStatesUtils;
import Cason.p2p.base.util.DateUtil;
import Cason.p2p.base.util.UserContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;

/**
 * @author Cason
 * @date 2022-11-01 21:26
 */
@Service
public class UserinfoServiceImpl implements IUserinfoService {
    @Value("${mail.hostUrl}")
    private String hostUrl;
    @Autowired
    private MailVerifyMapper mailVerifyMapper;
    @Autowired
    private UserinfoMapper userinfoMapper;
    @Autowired
    private IVerifyCodeService verifyCodeService;
    @Autowired
    private IMailService mailService;
    @Override
    public void update(Userinfo userinfo) {
        int ret = this.userinfoMapper.updateByPrimaryKey(userinfo);
        if(ret == 0){
            throw new RuntimeException("乐观锁失败：Userinfo"+userinfo.getId());
        }

    }

    @Override
    public void add(Userinfo userinfo) {
        this.userinfoMapper.insert(userinfo);
    }

    @Override
    public Userinfo get(Long id) {
        return this.userinfoMapper.selectByPrimaryKey(id);
    }

    @Override
    public void bindPhone(String phoneNumber, String verifyCode) {
        //获得当前的用户信息
        Userinfo current = this.get(UserContext.getCurrent().getId());
        if(!current.getisBindPhone()){
            //检查验证码的合法性
            boolean ret = this.verifyCodeService.verify(phoneNumber,verifyCode);
            if(ret){
                //如果合法,绑定手机
                current.addState(BitStatesUtils.OP_BIND_PHONE);
                current.setPhoneNumber(phoneNumber);
                this.update(current);
            }else{
                throw new RuntimeException("绑定手机失败");
            }
        }

    }

    @Override
    public Userinfo getCurrent() {
        /**
         * UserContext.getCurrent().getId()得到的是Logininfo的id
         */
        return  this.get(UserContext.getCurrent().getId());
    }

    @Override
    public void sendVerifyEmail(String email) {
        //获得当前的用户信息
        Userinfo current = this.getCurrent();
        if (!current.getisBindEmail()) {
            String uuid = UUID.randomUUID().toString();
            // 构造一份要发送的邮件
            StringBuilder content = new StringBuilder(100)
                    .append("点击<a href='").append(this.hostUrl)
                    .append("bindEmail.do?key=").append(uuid)
                    .append("'>这里</a>完成邮箱绑定,有效期为")
                    .append(BidConst.VERIFYCODE_VALIDATE_DAYS).append("天");

            try {
//                 System.out.println("发送邮件:" + email + "邮件内容:" + content);
                mailService.sendMail(email, "邮箱验证邮件", content.toString());
                // 构造一个MailVerify对象;
                MailVerify mv = new MailVerify();
                mv.setEmail(email);
                mv.setSendDate(new Date());
                mv.setUserinfoId(current.getId());
                mv.setUuid(uuid);
                this.mailVerifyMapper.insert(mv);

            } catch (Exception e) {
                e.printStackTrace();
                throw new RuntimeException("验证邮件发送失败!");
            }
        }

    }

    @Override
    public void bindEmail(String key) {
        /**
         * 通过uuid获得MailVerify对象，再获得Userinfo对象
         * 如果是通过current的id
         * 不能应对不同浏览器访问该网址，新的浏览器没有cookie
         */
        MailVerify mv = this.mailVerifyMapper.selectByUUID(key);
        if(mv != null){
            //判断当前用户有没有绑定邮箱
            Userinfo current = this.get(mv.getUserinfoId());
            if(!current.getisBindEmail()){
                //判断有效期
                if(mv != null && DateUtil.secondsBetween(mv.getSendDate(), new Date()) <= BidConst.VERIFYCODE_VALIDATE_DAYS * 24 * 3600){
                    //存入邮箱，，修改状态码，更新表
                    current.addState(BitStatesUtils.OP_BIND_EMAIL);
                    current.setEmail(mv.getEmail());
                    this.update(current);
                    return;
                }

            }
        }
        throw new RuntimeException("绑定邮箱失败");

    }

    @Override
    public void updateBasicInfo(Userinfo userinfo) {
        Userinfo old = this.getCurrent();
        //拷贝要更新的内容
        old.setEducationBackground(userinfo.getEducationBackground());
        old.setHouseCondition(userinfo.getHouseCondition());
        old.setIncomeGrade(userinfo.getIncomeGrade());
        old.setKidCount(userinfo.getKidCount());
        old.setMarriage(userinfo.getMarriage());

        //更新状态码
        if(!userinfo.getIsBasicInfo()){
            old.addState(BitStatesUtils.OP_BASIC_INFO);
        }

        this.update(old);

    }
}
