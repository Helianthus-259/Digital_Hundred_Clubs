package com.szbt.authserver.util;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class EmailUtil {
    @Value("${spring.mail.username}")
    private String from;

    @Autowired
    private JavaMailSender sender;

    public boolean sendMail(String to, String subject, String body) throws MessagingException {
        if (to.equals("") || to == null) {
            return false;
        }
        //构建标准的简单邮件信息
        //发送人和xml保持一致
        MimeMessage mimeMessage = sender.createMimeMessage();
        MimeMessageHelper m = new MimeMessageHelper(mimeMessage, true);
        //发送人
        m.setFrom(from);
        //接收人
        m.setTo(to);
        //邮件标题
        m.setSubject(subject);
        //内容
        m.setText(startHtml+body+endHtml,true);
        sender.send(mimeMessage);//发送邮件
        System.out.println("发送成功！");
        return true;
    }


    public String generateCode(int length) {
        StringBuilder s = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            int n = random.nextInt(10);
            s.append(n);
        }
        return s.toString();
    }

    public static String startHtml = "<html >\n" +
            "<meta charset=\"utf-8\">\n" +
            "<body><div id=\"contentDiv\" onmouseover=\"getTop().stopPropagation(event);\" onclick=\"getTop().preSwapLink(event, 'html', 'ZC0004_vDfNJayMtMUuKGIAzzsWvc8');\" style=\"position:relative;font-size:14px;height:auto;padding:15px 15px 10px 15px;z-index:1;zoom:1;line-height:1.7;\" class=\"body\">\n" +
            "  <div id=\"qm_con_body\">\n" +
            "    <div id=\"mailContentContainer\" class=\"qmbox qm_con_body_content qqmail_webmail_only\" style=\"opacity: 1;\">\n" +
            "      <style type=\"text/css\">\n" +
            "        .qmbox h1,.qmbox \t\t\th2,.qmbox \t\t\th3 {\t\t\t\tcolor: #e11e1e;\t\t\t}\t\t\t.qmbox p {\t\t\t\tpadding: 0;\t\t\t\tmargin: 0;\t\t\t\tcolor: #333;\t\t\t\tfont-size: 16px;\t\t\t}\t\t\t.qmbox hr {\t\t\t\tbackground-color: #d9d9d9;\t\t\t\tborder: none;\t\t\t\theight: 1px;\t\t\t}\t\t\t.qmbox .eo-link {\t\t\t\tcolor: #0576b9;\t\t\t\ttext-decoration: none;\t\t\t\tcursor: pointer;\t\t\t}\t\t\t.qmbox .eo-link:hover {\t\t\t\tcolor: #3498db;\t\t\t}\t\t\t.qmbox .eo-link:hover {\t\t\t\ttext-decoration: underline;\t\t\t}\t\t\t.qmbox .eo-p-link {\t\t\t\tdisplay: block;\t\t\t\tmargin-top: 20px;\t\t\t\tcolor: #009cff;\t\t\t\ttext-decoration: underline;\t\t\t}\t\t\t.qmbox .p-intro {\t\t\t\tpadding: 30px;\t\t\t}\t\t\t.qmbox .p-code {\t\t\t\tpadding: 0 30px 0 30px;\t\t\t}\t\t\t.qmbox .p-news {\t\t\t\tpadding: 0px 30px 30px 30px;\t\t\t}\n" +
            "      </style>\n" +
            "      <div style=\"max-width:800px;padding-bottom:10px;margin:20px auto 0 auto;\">\n" +
            "        <table cellpadding=\"0\" cellspacing=\"0\" style=\"background-color: #fff;border-collapse: collapse; border:1px solid #e5e5e5;box-shadow: 0 10px 15px rgba(0, 0, 0, 0.05);text-align: left;width: 100%;font-size: 14px;border-spacing: 0;\">\n" +
            "          <tbody>\n" +
            "          <tr style=\"background-color: #056608;\">\n" +
            "            <td>\n" +
            "              <img style=\"padding: 15px 0 15px 30px;width:758px;height:158px;\" src=\"https://sse.sysu.edu.cn/sites/sse.prod.dpcms4.sysu.edu.cn/files/sselogo20220915_0.png\">                <span >  </span>\n" +
            "            </td>\n" +
            "          </tr>\n" +
            "          <tr>\n" +
            "            <td class=\"p-intro\">\n" +
            "              <h1 style=\"font-size: 26px; font-weight: bold;\">验证您的邮箱地址</h1>\n" +
            "              <p style=\"line-height:1.75em;\">感谢您使用  数字百团. </p>\n" +
            "              <p style=\"line-height:1.75em;\">若为别人操作请忽视此信息</p>\n" +
            "              <p style=\"line-height:1.75em;\">以下是您的邮箱验证码，请在五分钟内将它输入到  数字百团 的邮箱验证码输入框中:</p>\n" +
            "\n" +
            "            </td>\n" +
            "          </tr>\n" +
            "          <tr>\n" +
            "            <td class=\"p-code\">\n" +
            "              <p style=\"color: #253858;text-align:center;line-height:1.75em;background-color: #f2f2f2;min-width: 200px;margin: 0 auto;font-size: 28px;border-radius: 5px;border: 1px solid #d9d9d9;font-weight: bold;\">";

    public static String endHtml = " </p>\n" +
            "            </td>\n" +
            "          </tr>\n" +
            "          <tr>\n" +
            "            <td class=\"p-intro\">\n" +
            "              <p style=\"line-height:1.75em;\">这一封邮件包括一些您的 数字百团 账号信息，请不要回复或转发它，以免带来不必要的信息泄露风险。 </p>\n" +
            "            </td>\n" +
            "          </tr>\n" +
            "          <tr>\n" +
            "            <td class=\"p-intro\">\n" +
            "              <hr>\n" +
            "              <p style=\"text-align: center;line-height:1.75em;\"> 数字百团</p>\n" +
            "            </td>\n" +
            "          </tr>\n" +
            "          </tbody>\n" +
            "        </table>\n" +
            "      </div>\n" +
            "      <style type=\"text/css\">\n" +
            "        .qmbox style, .qmbox script, .qmbox head, .qmbox link, .qmbox meta {display: none !important;}\n" +
            "      </style>\n" +
            "    </div>\n" +
            "  </div><!-- -->\n" +
            "  <style>\n" +
            "    #mailContentContainer .txt {height:auto;}\n" +
            "  </style>\n" +
            "</div></body>\n" +
            "</html>\n" +
            "\n" +
            "\n";
}