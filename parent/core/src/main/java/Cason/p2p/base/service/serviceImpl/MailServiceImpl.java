package Cason.p2p.base.service.serviceImpl;

import Cason.p2p.base.service.IMailService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMailMessage;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 * @author Cason
 * @date 2022-11-15 19:19
 */
@Service
public class MailServiceImpl implements IMailService {
    @Value("${mail.host}")
    private String host;

    @Value("${mail.username}")
    private String username;

    @Value("${mail.password}")
    private String password;
    @Override
    public void sendMail(String target, String title, String content) {
        JavaMailSenderImpl sender = new JavaMailSenderImpl();
        try {
            //设置SMTP服务器的地址
            sender.setHost(host);
            //创建一个邮箱对象
            MimeMessage message = sender.createMimeMessage();
            //创建一个邮箱助手
            MimeMessageHelper messageHelper = new MimeMessageHelper(message);
            //通过helper设置发送的邮件内容
            //设置目标邮箱
            messageHelper.setTo(target);
            //设置发送方
            messageHelper.setFrom("1125193113@qq.com","系统管理员");
            //设置邮件猪蹄
            messageHelper.setSubject(title);
            //设置邮件
            messageHelper.setText(content);

            //设置发送邮件方的账号和密码进行验证
            sender.setUsername(username);
            sender.setPassword(password);

            //设置发送服务的一些参数
            Properties prop = new Properties();
            prop.put("mail.smtp.auth",true);
            prop.put("mail.smtp.timeout",25000);

            sender.setJavaMailProperties(prop);
            sender.send(message);
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException("借用SMTP服务器发送邮件失败");
        }

    }
}
