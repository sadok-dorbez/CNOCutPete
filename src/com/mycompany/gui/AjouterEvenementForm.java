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
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.RadioButton;
import com.codename1.ui.Tabs;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import com.mycompany.entities.Evenement;
import com.mycompany.entities.Reclamation;
import com.mycompany.services.ServiceEvenement;
import com.mycompany.services.ServiceReclamation;

/**
 *
 * @author ziedm
 */
public class AjouterEvenementForm extends BaseForm {
    
     
    Form current;
    public AjouterEvenementForm(Resources res){
        super("Newsfeed",BoxLayout.y());  //heritage men newsfeed w le formulaire vertical
        
        Toolbar tb = new Toolbar(true);
        current = this;
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("Ajout Evenement");
        getContentPane().setScrollVisible(false);
        
        //tziid l menu
         super.addSideMenu(res);
        
        tb.addSearchCommand(e -> {
            
        });
      
        
        Tabs swipe = new Tabs();
        
        Label s1 = new Label();
        Label s2 = new Label();
        
        addTab(swipe,s1, res.getImage("back-logo.jpeg"),"","",res);
        
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
       
            RadioButton aaa = RadioButton.createToggle("Listes Evenements", barGroup);
        aaa.setUIID("SelectBar");
       
        RadioButton partage = RadioButton.createToggle("Event", barGroup);
        partage.setUIID("SelectBar");
        Label arrow = new Label(res.getImage("news-tab-down-arrow.png"), "Container");

        
        
        aaa.addActionListener((e) -> {
               InfiniteProgress ip = new InfiniteProgress();
        final Dialog ipDlg = ip.showInifiniteBlocking();
        
        //  ListReclamationForm a = new ListReclamationForm(res);
          //  a.show();
            refreshTheme();
        });

        add(LayeredLayout.encloseIn(
                GridLayout.encloseIn(1,  partage),
                FlowLayout.encloseBottom(arrow)
        ));

        partage.setSelected(true);
        arrow.setVisible(false);
        
        
        
        addShowListener(e -> {
            arrow.setVisible(true);
            updateArrowPosition(partage, arrow);
        });
        
   
        
        
        
        
  
        bindButtonSelection(partage, arrow);
        // special case for rotation
        addOrientationListener(e -> {
            updateArrowPosition(barGroup.getRadioButton(barGroup.getSelectedIndex()), arrow);
        });

       
        //
        
        
        TextField nom =  new TextField("","Entrer le nom de Evenement");
        nom.setUIID("TextFieldBlack");
        addStringValue("nom",nom);
        
           
        TextField typeE =  new TextField("","Entrer le typeE");
        typeE.setUIID("TextFieldBlack");
        addStringValue("typeE",typeE);
        
        TextField description =  new TextField("","Entrer la description");
        description.setUIID("TextFieldBlack");
        addStringValue("description",description);
        
        TextField image =  new TextField("","Entrer l'image");
        image.setUIID("TextFieldBlack");
        addStringValue("image",image);
        
         TextField date_debut =  new TextField("","Entrer date_debut");
        date_debut.setUIID("TextFieldBlack");
        addStringValue("date_debut",date_debut);
        
         TextField date_fin =  new TextField("","Entrer date_fin");
        date_fin.setUIID("TextFieldBlack");
        addStringValue("date_fin",date_fin);
        
        
        Button btnAjouter=new Button ("Ajouter");
        addStringValue("",btnAjouter);
        
        
        //onclick button event
        
        btnAjouter.addActionListener((e) -> {
            
            
            
            
            try {
                if(nom.getText().equals("") || typeE.getText().equals("") || description.getText().equals("") || image.getText().equals("") || date_debut.getText().equals("") || date_fin.getText().equals("")){
                    Dialog.show("Veuillez verifier les donnees","","Annuler","ok");
                }
                else{
                    InfiniteProgress ip = new InfiniteProgress();; //w entytestana fi data tetsab
                    final Dialog iDialog = ip.showInfiniteBlocking();
                    
                    Evenement r = new Evenement(String.valueOf(nom.getText()
                              ).toString(),
                            String.valueOf(typeE.getText()).toString(),
                            String.valueOf(description.getText()).toString(),
                            String.valueOf(image.getText()).toString(),
                            String.valueOf(date_debut.getText()).toString(),
                            String.valueOf(date_fin.getText()).toString()
                
                    );
                    System.out.println("data  reclamation == "+r);
                    
                    
                      //appelle methode ajouterReclamation mt3 service Reclamation bch nzido données ta3na fi base
                        ServiceEvenement.getInstance().ajouterevenement(r);
                        
                      //na7iow loading bedajout
                      iDialog.dispose();
                      
                   
                      //ba3d mé naameel l ajout netaaada ll liste reclamation
                      new ListEvenementForm(res).show();
                      
                      
                      refreshTheme(); //refrech
       
                    
                }
                
            }catch(Exception ex){
                ex.printStackTrace();
            }
                
                
              
                
                
                
          });
        
       
        
        
    }

    private void addStringValue(String s, Component v ) {
        
        add(BorderLayout.west(new Label(s,"PaddedLabel"))
        .add(BorderLayout.CENTER,v));
        add(createLineSeparator(0xeeeeee));
        
        
        
       
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
    
    
    
    
}
