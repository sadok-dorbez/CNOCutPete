/*
 * Copyright (c) 2016, Codename One
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated 
 * documentation files (the "Software"), to deal in the Software without restriction, including without limitation 
 * the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, 
 * and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions 
 * of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, 
 * INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A 
 * PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT 
 * HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF 
 * CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE 
 * OR THE USE OR OTHER DEALINGS IN THE SOFTWARE. 
 */

package com.mycompany.gui;

import com.codename1.components.FloatingActionButton;
import com.codename1.components.InfiniteProgress;
import com.codename1.components.MultiButton;
import com.codename1.components.ScaleImageLabel;
import com.codename1.ui.Button;
import com.codename1.ui.CheckBox;
import com.codename1.ui.Component;
import static com.codename1.ui.Component.BOTTOM;
import static com.codename1.ui.Component.CENTER;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import com.mycompany.services.ServiceUtilisateur;

/**
 * The user profile form
 *
 * @author Shai Almog
 */
public class ProfileForm extends BaseForm {
    
    private static String i ;
    Form previous = Display.getInstance().getCurrent();
    public ProfileForm(Resources res) {
        super(BoxLayout.y());
        Toolbar tb = getToolbar();
        tb.setTitleCentered(false);
        Image profilePic = res.getImage("bien11.jpg");
        Image mask = res.getImage("bien11.jpg");
        profilePic = profilePic.fill(mask.getWidth(), mask.getHeight());
       Label profilePicLabel = new Label(profilePic, "ProfilePicTitle");
        profilePicLabel.setMask(mask.createMask());

        Button menuButton = new Button("");
        menuButton.setUIID("Title");
        FontImage.setMaterialIcon(menuButton, FontImage.MATERIAL_MENU);
        menuButton.addActionListener(e -> getToolbar().openSideMenu());


    
        
        
      
        String name = SessionManager.getUserName();
        String emailUser = SessionManager.getEmail();

        Container titleCmp = BoxLayout.encloseY(
                FlowLayout.encloseIn(menuButton),
                BorderLayout.centerAbsolute(
                        BoxLayout.encloseY(
                                new Label("Title"),
                            
                                new Label(emailUser, "SubTitle")
                        )
                ).add(BorderLayout.WEST, profilePicLabel),
                GridLayout.encloseIn()
        );

        FloatingActionButton fab = FloatingActionButton.createFAB(FontImage.MATERIAL_ADD);
        fab.getAllStyles().setMarginUnit(Style.UNIT_TYPE_PIXELS);
    
        tb.setTitleComponent(fab.bindFabToContainer(titleCmp, CENTER, BOTTOM));
        Button modify = new Button("Save Changes");
        
    
        
        
        
        Button back = new Button("back");
  
     
        
        
        
        back.addActionListener(e -> previous.showBack());

      
        
        
        //Button picture=new Button("Photo");
           Label title = new Label("Modfier your account") ; 
           add(title);
    
        title.getAllStyles().setAlignment(CENTER) ; 
        title.getAllStyles().setMargin(30,30,0,0) ; 
        title.getAllStyles().setFgColor(0xD13866); 
               
                
        FontImage arrowDown = FontImage.createMaterial(FontImage.MATERIAL_KEYBOARD_ARROW_DOWN, "Label", 3);
        /* add(LayeredLayout.encloseIN(
        s1,
         BorderLayout.south(
                     GridLayout.encloseIn(3,
                             FlowLayout.encloseCenter( )
                     ))
        ));*/
        String us = SessionManager.getUserName();
        System.out.println(us);
        TextField username = new TextField(us);
        username.setUIID("TextFieldBlack");
        addStringValue("username", username);
        
        TextField password = new TextField(SessionManager.getPassowrd(), "password", 20, TextField.PASSWORD);
        password.setUIID("TextFieldBlack");
        addStringValue("password", password);
        TextField email = new TextField(SessionManager.getEmail(), "email", 20, TextField.EMAILADDR);
        email.setUIID("TextFieldBlack");
        addStringValue("email", email);
        modify.setUIID("Edit");
        back.setUIID("back");
          //addStringValue("",picture)
        addStringValue("", modify);
        addStringValue("", back);
   
        
        
        
        
     
        /* TextField path= new TextField("");
       picture.addActionListener(e->{
       i=Capture.capturePhoto(Display.getInstance().getDisplayWidth(),-1);
       if(i != null){
           Image im;
           try{
           im=Image.createImage(i);
           im=im.scaled(res.getImage("photo-profile.jpg").getWidth(),res.gaetImage("photo-profile.jpg").getHeight());
           System.out.println(i);
           path.setText(i);
           
           }
           catch(IOException ex) {
               System.out.println("Could not load image");
           
           }
       
       }
   
       });*/

        modify.addActionListener((edit) -> {
            InfiniteProgress ip = new InfiniteProgress();
            final Dialog ipDlg = ip.showInifiniteBlocking();
            ServiceUtilisateur.EditUser(username.getText(), password.getText(), email.getText());
            SessionManager.setUserName(username.getText());
            SessionManager.setPassowrd(password.getText());
            SessionManager.setEmail(email.getText());
            //SessionManager.setPhoto(username.getText()+".jpg");
            //Dialog.show("SUCCESS","Changed was saved","OK",null);
            ipDlg.dispose();
            refreshTheme();

        });
   
    }
    
      private void addButtonBottom(Image arrowDown, String text, int color, boolean first) {
        MultiButton finishLandingPage = new MultiButton(text);
        finishLandingPage.setEmblem(arrowDown);
        finishLandingPage.setUIID("Container");
        finishLandingPage.setUIIDLine1("TodayEntry");
        finishLandingPage.setIcon(createCircleLine(color, finishLandingPage.getPreferredH(), first));
        finishLandingPage.setIconUIID("Container");
        add(FlowLayout.encloseIn(finishLandingPage));
    }
      
       private Image createCircleLine(int color, int height, boolean first) {
        Image img = Image.createImage(height, height, 0);
        Graphics g = img.getGraphics();
        g.setAntiAliased(true);
        g.setColor(0xcccccc);
        int y = 0;
        if (first) {
            y = height / 6 + 1;
        }
        g.drawLine(height / 2, y, height / 2, height);
        g.drawLine(height / 2 - 1, y, height / 2 - 1, height);
        g.setColor(color);
        g.fillArc(height / 2 - height / 4, height / 6, height / 2, height / 2, 0, 360);
        return img;
    }

    

    private void addStringValue(String s, Component v) {
              add(BorderLayout.west(new Label(s, "PaddedLabel")).add(BorderLayout.CENTER, v));
        //  add(createLineSeparator(0xeeeeee));
    }
}
