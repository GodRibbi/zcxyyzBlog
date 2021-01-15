package com.zrq.controller;

import java.util.Date;
import java.util.Properties;
import java.util.Random;

import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendEmail {
    private static String senderAddress = "zcxyyz@126.com";
    private static String recipientAddress;
    public String senderAccount = "zcxyyz@126.com";
    public String auth = "KOSGXSSYBZHMBTJC";
    public static String subject = "Here is your identifying code";
    public static String content = "Your identifying code is: ";
    private int code;

    public SendEmail(String recipientAddress) {
        SendEmail.recipientAddress = recipientAddress;
        Random random=new Random();
        code=random.nextInt(8999)+1000;
        content="Your identifying code is: ";
        content+=code;
        Properties properties = new Properties();
        properties.setProperty("mail.smtp.auth", "true");
        properties.setProperty("mail.transport.protocol", "smtp");
        properties.setProperty("mail.smtp.host", "smtp.126.com");
        Session session = Session.getInstance(properties);
        session.setDebug(true);
        MimeMessage msg;
        try {
            msg = getMimeMessage(session);
            Transport transport = session.getTransport("smtp");
            transport.connect("smtp.126.com", senderAccount, auth);
            transport.sendMessage(msg, msg.getAllRecipients());
            transport.close();
        } catch (MessagingException e) {
            e.printStackTrace();
        }

    }
    private MimeMessage getMimeMessage(Session session) throws MessagingException {
        MimeMessage msg = new MimeMessage(session);
        msg.addHeader("X-Mailer","Microsoft Outlook Express 6.00.2900.2869");
        msg.setFrom(new InternetAddress(senderAddress));
        msg.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress(recipientAddress));
        msg.setSubject(subject, "UTF-8");
        msg.setContent(content, "text/html;charset=UTF-8");
        // 发送 HTML 消息, 可以插入html标签
        msg.setContent("<h1>"+content+"</h1>",
        "text/html;charset=UTF-8" );
        msg.setSentDate(new Date());
        return msg;
    }
    public int getCode(){
        return code;
    }
}
