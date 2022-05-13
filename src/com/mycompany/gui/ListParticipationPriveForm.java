/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.components.InfiniteProgress;
import com.codename1.components.ScaleImageLabel;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.ButtonGroup;
import com.codename1.ui.Component;
import static com.codename1.ui.Component.BOTTOM;
import static com.codename1.ui.Component.CENTER;
import static com.codename1.ui.Component.LEFT;
import static com.codename1.ui.Component.RIGHT;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.RadioButton;
import com.codename1.ui.Tabs;
import com.codename1.ui.Toolbar;
import com.codename1.ui.URLImage;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import com.mycompany.entities.ParticipationPrive;
import com.mycompany.entities.ParticipationPublic;
import com.mycompany.services.ServiceParticipationPrive;
import com.mycompany.services.ServiceParticipationPublic;
import com.mycompany.services.ServiceReclamation;
import java.util.ArrayList;

/**
 *
 * @author ziedm
 */
public class ListParticipationPriveForm extends BaseForm {
    
    Form current ;
    public ListParticipationPriveForm (Resources res ) {
        
        super("Newsfeed",BoxLayout.y());  //heritage men newsfeed w le formulaire vertical
        
        Toolbar tb = new Toolbar(true);
        current = this;
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("List de participation prive");
        getContentPane().setScrollVisible(false);
        
        super.addSideMenu(res);
        
        tb.addSearchCommand(e -> {
            
        });
      
        
        
        Tabs swipe = new Tabs();
        
        Label s1 = new Label();
        Label s2 = new Label();
        
        addTab(swipe,s1, res.getImage("participer.png"),"","",res);
        
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
        RadioButton mesListes = RadioButton.createToggle(" participations prives ", barGroup);
        mesListes.setUIID("SelectBar");
        
        
           RadioButton aaa = RadioButton.createToggle("aaa", barGroup);
           mesListes.setUIID("SelectBar");
        
        
     
        Label arrow = new Label(res.getImage("news-tab-down-arrow.png"), "Container");


        
        
         mesListes.addActionListener((e)->{
         new ListParticipationPublicForm(res).show(); 
          });
        
        
        
        aaa.addActionListener((e) -> {
               InfiniteProgress ip = new InfiniteProgress();
        final Dialog ipDlg = ip.showInifiniteBlocking();
        
      
            refreshTheme();
        });

        add(LayeredLayout.encloseIn(
                GridLayout.encloseIn(1, mesListes),
                FlowLayout.encloseBottom(arrow)
        ));

   
        arrow.setVisible(false);
        addShowListener(e -> {
            arrow.setVisible(true);
           
        });
        bindButtonSelection(mesListes, arrow);
     
   
        // special case for rotation
        addOrientationListener(e -> {
            updateArrowPosition(barGroup.getRadioButton(barGroup.getSelectedIndex()), arrow);
        });
        
        //appel ll affichage methode
        ArrayList<ParticipationPrive>list = ServiceParticipationPrive.getInstance().affichageParticipationPrives();
        
        for (ParticipationPrive rec : list ) {
           String  urlImage = "participer.png";    // image statique pour le moment 
            
           Image placeHolder = Image.createImage(120, 90) ;
           EncodedImage enc = EncodedImage.createFromImage(placeHolder, false) ;
           URLImage urlim = URLImage.createToStorage(enc, urlImage, urlImage, URLImage.RESIZE_SCALE);
            
            addButton(urlim,rec,res);
            
            
            ScaleImageLabel image = new ScaleImageLabel(urlim);
            
            Container containerImg = new Container();
            
            image.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);
            
            
        }
        
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
        
        swipe.addTab("",res.getImage("participer.png"), page1);
       
        
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

    private void addButton(Image img ,ParticipationPrive rec, Resources res) {
        
        
        int height = Display.getInstance().convertToPixels(11.5f);
        int width = Display.getInstance().convertToPixels(14f);
        
        Button image = new Button (img.fill(width , height));
        image.setUIID("Label");
        
        Container cnt = BorderLayout.west(image);
        
        
        Label numeroTelTxt = new Label("Numero téléphone : "+rec.getNumeroTel(), "NewsTopLine2"   );
        Label nbrPrisechargeTxt = new Label("Nb prise en charge : "+rec.getNbrPrisecharge(), "NewsTopLine2"   );

        //TextArea ta = new TextArea (titre) ;
        //ta.setUIID("NewsTopLine");
        //ta.setEditable(false);
        
          
          
          
          
          
           
        //supprimer button
        Label lSupprimer = new Label(" ");
        lSupprimer.setUIID("NewsTopLine");
        Style supprmierStyle = new Style(lSupprimer.getUnselectedStyle());
        supprmierStyle.setFgColor(0xf21f1f);
        
        FontImage suprrimerImage = FontImage.createMaterial(FontImage.MATERIAL_DELETE, supprmierStyle);
        lSupprimer.setIcon(suprrimerImage);
        lSupprimer.setTextPosition(RIGHT);
        
        //click delete icon
        lSupprimer.addPointerPressedListener(l -> {
            
            Dialog dig = new Dialog("Suppression");
            
            if(dig.show("Suppression","Vous voulez supprimer ce reclamation ?","Annuler","Oui")) {
                dig.dispose();
            }
            else {
                dig.dispose();
                 }
                //n3ayto l suuprimer men service Reclamation
                if(ServiceParticipationPrive.getInstance().deleteParticipation(rec.getId())) {
                    new ListParticipationPriveForm(res).show();
                }
           
        });
        
        
  
       
        
       cnt.add(BorderLayout.CENTER , BoxLayout.encloseY(
                
       BoxLayout.encloseX(numeroTelTxt),
       BoxLayout.encloseX(nbrPrisechargeTxt,lSupprimer)));
        

        add(cnt);
        
    }
    

    
}