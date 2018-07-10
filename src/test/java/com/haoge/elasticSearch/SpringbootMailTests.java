package com.haoge.elasticSearch;

import java.io.File;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootMailTests {
	@Autowired
	JavaMailSenderImpl mailSender;

	@Test
	public void test01() {
		//测试发送简单邮件
		SimpleMailMessage message = new SimpleMailMessage();
		message.setSubject("测试邮件");//设置主题
		message.setText("这是一份测试邮件。。");//设置邮件内容
		message.setTo("15810673938@163.com");//设置发送目标
		message.setFrom("861914994@qq.com");//设置发送账号
		mailSender.send(message);
	}
	
	@Test
	public void test02() throws MessagingException {
		//测试发送复杂邮件
		MimeMessage mineMessage = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(mineMessage,true);//true表示允许上传文件
		
		helper.setSubject("测试邮件");//设置主题
		helper.setText("<b style='color:red'>这是一份测试邮件。。</b>",true);//设置邮件内容,允许使用html
		helper.setTo("15810673938@163.com");//设置发送目标
		helper.setFrom("861914994@qq.com");//设置发送账号
		helper.addAttachment("Chrysanthemum.jpg", new File("C:\\\\Users\\\\Public\\\\Pictures\\\\Sample Pictures\\\\Chrysanthemum.jpg"));
		helper.addAttachment("Desert.jpg", new File("C:\\\\Users\\\\Public\\\\Pictures\\\\Sample Pictures\\\\Desert.jpg"));
		mailSender.send(mineMessage);
	}
}
