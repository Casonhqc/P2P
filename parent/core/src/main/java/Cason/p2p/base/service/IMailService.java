package Cason.p2p.base.service;

/**
 * 邮件发送业务
 * @author Cason
 * @date 2022-11-15 19:17
 */
public interface IMailService {
    void sendMail(String target, String title,String content);
}
