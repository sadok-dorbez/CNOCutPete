/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.components.FloatingHint;
import com.codename1.components.InfiniteProgress;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.util.Resources;
import com.mycompany.services.ServiceUtilisateur;
import com.sun.mail.smtp.SMTPTransport;
import java.util.Date;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author ziedm
 */
public class VerifPaiementForm extends BaseForm{
    
     TextField email;
    public VerifPaiementForm(Resources res) {
        super(new BorderLayout());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        tb.setUIID("IMGLogin");
        getTitleArea().setUIID("Container");
        Form previous = Display.getInstance().getCurrent();
        tb.setBackCommand("", e -> previous.showBack());
        setUIID("Activate");
        
        add(BorderLayout.NORTH, 
                BoxLayout.encloseY(
                        new Label(res.getImage("smily.png"), "LogoLabel"),
                        new Label("Vérification !!!", "LogoLabel")
                )
        );
        
        
        
        
        
        
        
        
        email = new TextField("","saisir votre email",20,TextField.ANY);
        email.setSingleLineTextArea(false);
        
        
        
        Button valider = new Button("Valider");
        Button home = new Button("");
        home.addActionListener( e-> previous.showBack());//yarja3 lel window ely9ablha
        home.setUIID("CenterLink");
     
        
        Container content = BoxLayout.encloseY(
                
        
                new FloatingHint(email),
                createLineSeparator(),
                valider,
                 FlowLayout.encloseCenter(),
                home
           
        );
        
        content.setScrollableY(true);
        
        add(BorderLayout.CENTER,content);
        
        valider.requestFocus();
        
        valider.addActionListener(e -> {
            
            InfiniteProgress ip = new InfiniteProgress();
            
            final Dialog ipDialog =  ip.showInfiniteBlocking();
            
           //houni bch nzido API SEND MAIL autrement bch n3ayto lel function ta3o mais 9bal njibo image oublier.png
            
            sendMail(res);
            ipDialog.dispose();
            Dialog.show("Vérification Paiement","Nous avons envoyé la vérification a votre e-mail. Veuillez vérifier votre boite de réception",new Command("OK"));
            new HomeForm(res).show();
            refreshTheme();
            
        });
        
        
         }
        
   
     
         //sendMail
    
    public void sendMail(Resources res) {
        try {
            
            Properties props = new Properties();
                props.put("mail.transport.protocol", "smtp"); //SMTP protocol
		props.put("mail.smtps.host", "smtp.gmail.com"); //SMTP Host
		props.put("mail.smtps.auth", "true"); //enable authentication
             
            Session session = Session.getInstance(props,null); // aleh 9rahach 5ater mazlna masabinach javax.mail .jar
            
            
            MimeMessage msg = new MimeMessage(session);
            
            msg.setFrom(new InternetAddress("Vérification Paiement <monEmail@domaine.com>"));
            msg.setRecipients(Message.RecipientType.TO, email.getText().toString());
            msg.setSubject("Save Me  ");
            msg.setSentDate(new Date(System.currentTimeMillis()));
            
           String mp = ServiceUtilisateur.getInstance().getPasswordByEmail(email.getText().toString(), res);//mp taw narj3lo
           String txt = "Votre Participation à été éffectuer avec Succés";
           
           
           msg.setText(txt);
           
          SMTPTransport  st = (SMTPTransport)session.getTransport("smtps") ;
            
          st.connect("smtp.gmail.com",465,"save.me.pidev@gmail.com","SAVEsave2022");
           
          st.sendMessage(msg, msg.getAllRecipients());
            
          System.out.println("server response : "+st.getLastServerResponse());
          
         
             
        }catch(Exception e ) {
            e.printStackTrace();
        }
        
   
    
                
                
                
                
                
    }          
    
}
