package Cason.p2p.base.service.serviceImpl;

import Cason.p2p.base.service.IVerifyCodeService;
import Cason.p2p.base.util.BidConst;
import Cason.p2p.base.util.DateUtil;
import Cason.p2p.base.util.UserContext;
import Cason.p2p.base.vo.VerifyCodeVo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StreamUtils;

import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.UUID;

/**
 * @author Cason
 * @date 2022-11-12 21:17
 */
@Service
public class VerifyCodeServiceImpl implements IVerifyCodeService {
    @Value("${sms.username}")
    private String username;

    @Value("${sms.password}")
    private String password;

    @Value("${sms.apikey}")
    private String apiKey;

    @Value("${sms.url}")
    private String url;

    @Override
    public void sendVerifyCode(String phoneNumber) {
        System.out.println("用户名"+username+"  密码"+password +"  密钥" +apiKey+"  url:"+ url);
//        从session中取验证码，判断是否发送过验证码
        VerifyCodeVo vo = UserContext.getCurrentVerifyCode();
        if(vo == null || DateUtil.secondsBetween(new Date(),vo.getLastSendTime()) > 90){
            //正常发送信息

            //生成一个验证码
            String verifyCode = UUID.randomUUID().toString().substring(0,4);
            //发送信息
            try {
                //创建一个url对象
                URL url = new URL(this.url);
                //通过url对象获得一个httpUrlconnection对象
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                //拼接post请求对象
                StringBuilder content = new StringBuilder(100)
                        .append("username=").append(username)
                        .append("&password=").append(password)
                        .append("&apikey=").append(apiKey).append("&mobile=")
                        .append(phoneNumber).append("&content=")
                        .append("验证码是:").append(verifyCode).append(",请在5分钟内使用");
                //发送post请求，要大写
                connection.setRequestMethod("POST");
                //设置Post请求室友请求体的
                connection.setDoOutput(true);
                //写入post请求体
                connection.getOutputStream().write(content.toString().getBytes());
                //得到响应流(已经发送了）
                String response = StreamUtils.copyToString(connection.getInputStream(), StandardCharsets.UTF_8);

                if(response.startsWith("success:")){
                    //发送成功
                    //把手机号码，发送时间，验证码装配到VO中并保存到session里
                    vo = new VerifyCodeVo();
                    vo.setVerifyCode(verifyCode);
                    vo.setLastSendTime(new Date());
                    vo.setPhoneNumber(phoneNumber);

                    UserContext.putCurrentVerifyCode(vo);
                }else{
                    //发送失败
                    throw new RuntimeException("转发给短信网关失败");

                }
            } catch (Exception e) {
                throw new RuntimeException("短信发送失败");
            }

        }
        else{
            throw new RuntimeException("请求过于频繁");
        }

    }

    @Override
    public boolean verify(String phoneNumber, String verifyCode) {
        VerifyCodeVo vo = UserContext.getCurrentVerifyCode();
        if(vo != null //发送了验证码
            && vo.getVerifyCode().equalsIgnoreCase(verifyCode)//验证码
            && vo.getPhoneNumber().equals(phoneNumber) //手机号
            && DateUtil.secondsBetween(new Date(),vo.getLastSendTime()) < BidConst.VERIFYCODE_VALIDATE_SECONDS){
        return true;
        }
        return false;
    }
}
