/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import Web.Web;
import com.codename1.components.InfiniteProgress;
import com.codename1.components.ScaleImageLabel;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.ButtonGroup;
import com.codename1.ui.Command;
import com.codename1.ui.Component;
import static com.codename1.ui.Component.BOTTOM;
import static com.codename1.ui.Component.CENTER;
import static com.codename1.ui.Component.LEFT;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Form;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.RadioButton;
import com.codename1.ui.Tabs;
import com.codename1.ui.Toolbar;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.entities.Reclamation;
import com.mycompany.myapp.entities.services.ServiceReclamation;
import java.util.ArrayList;

/**
 *
 * @author ziedm
 */
public class HomeForm extends BaseForm {
    
        
           
    Form current ;
    public HomeForm (Resources res ) {
        
        super("Newsfeed",BoxLayout.y());  //heritage men newsfeed w le formulaire vertical
        
        Toolbar tb = new Toolbar(true);
        current = this;
        setToolbar(tb);
        getTitleArea().setUIID("Container");
      
        getContentPane().setScrollVisible(false);
        
        super.addSideMenu(res);
        
        tb.addSearchCommand(e -> {
            
        });
      
        
        
        Tabs swipe = new Tabs();
        
        Label s1 = new Label();
        Label s2 = new Label();
        
        addTab(swipe,s1, res.getImage("bien11.jpg"),"","",res);
        
        //
          swipe.setUIID("Container");
        swipe.getContentPane().setUIID("Container");
        swipe.hideTabs();

        ButtonGroup bg = new ButtonGroup();
        int size = Display.getInstance().convertToPixels(1);
        Image unselectedWalkthru = Image.createImage(size, size, 0);
        Graphics g = unselectedWalkthru.getGraphics();
        g.setColor(0xffffff);
        g.setAlpha(100);
        g.setAntiAliased(true);
        g.fillArc(0, 0, size, size, 0, 360);
        Image selectedWalkthru = Image.createImage(size, size, 0);
        g = selectedWalkthru.getGraphics();
        g.setColor(0xffffff);
        g.setAntiAliased(true);
        g.fillArc(0, 0, size, size, 0, 360);
        RadioButton[] rbs = new RadioButton[swipe.getTabCount()];
        FlowLayout flow = new FlowLayout(CENTER);
        flow.setValign(BOTTOM);
        Container radioContainer = new Container(flow);
        for (int iter = 0; iter < rbs.length; iter++) {
            rbs[iter] = RadioButton.createToggle(unselectedWalkthru, bg);
            rbs[iter].setPressedIcon(selectedWalkthru);
            rbs[iter].setUIID("Label");
            radioContainer.add(rbs[iter]);
        }

        rbs[0].setSelected(true);
        swipe.addSelectionListener((i, ii) -> {
            if (!rbs[ii].isSelected()) {
                rbs[ii].setSelected(true);
            }
        });

        Component.setSameSize(radioContainer, s1, s2);
        add(LayeredLayout.encloseIn(swipe, radioContainer));

        ButtonGroup barGroup = new ButtonGroup();
        RadioButton aaaa = RadioButton.createToggle("aaaa", barGroup);
        aaaa.setUIID("SelectBar");
        
        
           RadioButton aaa = RadioButton.createToggle("aaa", barGroup);
        aaa.setUIID("SelectBar");
        
        
     
        Label arrow = new Label(res.getImage("news-tab-down-arrow.png"), "Container");


        
        
         aaaa.addActionListener((e)->{
         new ListReclamationForm(res).show(); 
          });
        
         
          Button reserver = new Button("Visiter notre site web");
        reserver.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                new Web().show();
            }

        });
        add(reserver);
        
           Container ct = new Container(BoxLayout.y());
            
            Button pdf = new Button("PDF");
            Button Partager = new Button("Partager");
                Partager.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent evt) {
                                         
                                    Display.getInstance().execute(" https://www.facebook.com/sharer/sharer.php?u=http%3A%2F%2F127.0.0.1%3A8000%2F");

                                }

                });
                            ct.add(Partager);

            pdf.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {

                    if (Dialog.show("Confirmation", "Voulez vous imprimer les reclamations  en pdf ?", "Oui", "Annuler")) {
                        if(ServiceReclamation.getInstance().Exporterpdf()){
                        Dialog.show("Success", "imprimer", new Command("OK"));
                        new PDF(res).show();
                        // new AllReclamation(res).show();

                    }
                }}
            });
            ct.add(pdf);
            
            Label separator = new Label("", "Separator");
            ct.add(separator);
            add(ct);
         
              Label title = new Label("CUTEPETE") ; 
           add(title);
    
        title.getAllStyles().setAlignment(CENTER) ; 
        title.getAllStyles().setMargin(30,30,0,0) ; 
        title.getAllStyles().setFgColor(0xD13866);
        title.getAllStyles().setBgColor(BOTTOM);
               
         
         
         
         
         
        
        
        
         
        
        
        aaa.addActionListener((e) -> {
               InfiniteProgress ip = new InfiniteProgress();
        final Dialog ipDlg = ip.showInifiniteBlocking();
        
        //  ListReclamationForm a = new ListReclamationForm(res);
          //  a.show();
            refreshTheme();
        });

    
    
       
    }
    
    
    
    
    private void addTab(Tabs swipe, Label spacer , Image image, String string, String text, Resources res) {
        int size = Math.min(Display.getInstance().getDisplayWidth(), Display.getInstance().getDisplayHeight());
        
        if (image.getHeight() < size) {
            image = image.scaledHeight(size);
        }
        
        if (image.getHeight() > Display.getInstance().getDisplayHeight() / 2){
            image = image.scaledHeight(Display.getInstance().getDisplayHeight() / 2);
        }
        
        ScaleImageLabel imageScale = new ScaleImageLabel(image);
        imageScale.setUIID("Container");
        imageScale.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);
      
        Label overLay = new Label("","ImageOverlay");
        
        Container page1 = 
                LayeredLayout.encloseIn(
                imageScale,
                        overLay,
                        BorderLayout.south(
                        BoxLayout.encloseY(
                        new SpanLabel (text, "LargeWhiteText"),
                                spacer
                        )
                        
                     )
                );
        
        swipe.addTab("",res.getImage("back-logo.jpeg"), page1);
       
        
     }
    
    public void bindButtonSelection(Button btn , Label l) {
        
        btn.addActionListener(e -> {
            if (btn.isSelected()) {
                updateArrowPosition(btn,l);
            }
            
        });
    }

    private void updateArrowPosition(Button btn, Label l) {
        l.getUnselectedStyle().setMargin(LEFT, btn.getX() + btn.getWidth() / 2 - l.getWidth() / 2 );
        l.getParent().repaint();
        
    }

    private void addButton(Image img ,String titre, String contenu ,Reclamation rec) {
        
        
        int height = Display.getInstance().convertToPixels(11.5f);
        int width = Display.getInstance().convertToPixels(14f);
        
        Button image = new Button (img.fill(width , height));
        image.setUIID("Label");
        
        Container cnt = BorderLayout.west(image);
        
        
        Label titreTxt = new Label("titre : "+titre, "NewsTopLine2"   );
        Label contenuTxt = new Label("contenu : "+contenu, "NewsTopLine2");
        //TextArea ta = new TextArea (titre) ;
        //ta.setUIID("NewsTopLine");
        //ta.setEditable(false);
        
        cnt.add(BorderLayout.CENTER, BoxLayout.encloseY(BoxLayout.encloseX(titreTxt),BoxLayout.encloseX(contenuTxt)));
        
        add(cnt);
        
    }
    

    
}
