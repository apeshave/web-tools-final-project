/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.healthcare.data;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author ADi
 *  
 *  
 *
 */
public class Mail {
    
    private String username = "your email : crazypatlaudu :D";
    private String password = "your password";

    
	// @Param message - message to send
	// @Param email - to whom you wanna send
    public void sendMail(String message,String email){
    
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.from", "apeshave@gmail.com");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        props.setProperty("mail.debug", "true");
        
        Session session = Session.getInstance(props, null);
    
    
          
        try {
            MimeMessage msg = new MimeMessage(session);
            msg.setFrom();
            msg.setRecipients(Message.RecipientType.TO, email);
            msg.setSubject("iHealthCare Solutions - Email Validation");
            msg.setContent(message, "text/html; charset=utf-8");
            Transport transport = session.getTransport("smtp");
            transport.connect(username, password);

            transport.sendMessage(msg, msg.getAllRecipients());
            transport.close();
                    
        } catch (MessagingException mex) {
            System.out.println("send failed, exception: " + mex);
        }
    }
}
