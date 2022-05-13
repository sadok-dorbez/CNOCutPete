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
import com.mycompany.entities.Paiement;
import com.mycompany.services.ServicePaiement;


/**
 *
 * @author ziedm
 */
public class PaiementForm  extends BaseForm{
    
    
       Form current;
    public PaiementForm (Resources res){
        super("Newsfeed",BoxLayout.y());  //heritage men newsfeed w le formulaire vertical
        
        Toolbar tb = new Toolbar(true);
        current = this;
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("Ajouter paiement ");
        getContentPane().setScrollVisible(false);
        
        //tziid l menu
         super.addSideMenu(res);
        
        tb.addSearchCommand(e -> {
            
        });
      
        
        Tabs swipe = new Tabs();
        
        Label s1 = new Label();
        Label s2 = new Label();
        
        addTab(swipe,s1, res.getImage("carte.png"),"","",res);
        
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
        RadioButton aaaa = RadioButton.createToggle("Listes participatons publics", barGroup);
        aaaa.setUIID("SelectBar");
        
            RadioButton aaa = RadioButton.createToggle("Listes Reclamations", barGroup);
        aaaa.setUIID("SelectBar");
       
        RadioButton partage = RadioButton.createToggle(" payer ", barGroup);
        partage.setUIID("SelectBar");
        Label arrow = new Label(res.getImage("news-tab-down-arrow.png"), "Container");

         aaa.addActionListener((e)->{
         new ListParticipationPublicForm(res).show(); 
          });
        
        aaa.addActionListener((e) -> {
               InfiniteProgress ip = new InfiniteProgress();
        final Dialog ipDlg = ip.showInifiniteBlocking();
        
        //  ListReclamationForm a = new ListReclamationForm(res);
          //  a.show();
            refreshTheme();
        });

           add(LayeredLayout.encloseIn(
                GridLayout.encloseIn(),
                FlowLayout.encloseBottom(arrow)
        ));

        partage.setSelected(true);
        arrow.setVisible(false);
        
        
 
       
        //
        
    
        TextField NomSurCarte =  new TextField("","Saisir Votre Nom Sur Carte");
        NomSurCarte.setUIID("TextFieldBlack");
        addStringValue(" Nom Sur Carte",NomSurCarte);
        
        TextField NumCarte =  new TextField("","Saisir Votre Numero Carte");
        NumCarte.setUIID("TextFieldBlack");
        addStringValue("Numero Carte",NumCarte);
        
        TextField MoisExp =  new TextField("","Saisir Mois deExp");
        MoisExp.setUIID("TextFieldBlack");
        addStringValue("Mois deExp",MoisExp);
        
        TextField AnneeExp =  new TextField("","Saisir Annee deExp");
        AnneeExp.setUIID("TextFieldBlack");
        addStringValue("Annee deExp",AnneeExp);
        
        TextField CVV =  new TextField("","Saisir Votre CVV");
        CVV.setUIID("TextFieldBlack");
        addStringValue("CVV",CVV);
        
        
        
        Button btnAjouter=new Button ("payer");
        addStringValue("",btnAjouter);
        
        
        //onclick button event
        
        btnAjouter.addActionListener((e) -> {
            
            
            
            
            try {
                if( NomSurCarte.getText().equals("")|| NumCarte.getText().equals("")|| MoisExp.getText().equals("")|| AnneeExp.getText().equals("")|| CVV.getText().equals("")){
                    Dialog.show("Veuillez verifier les donnees","","Annuler","ok");
                }
                else{
                    InfiniteProgress ip = new InfiniteProgress();; //w entytestana fi data tetsab
                    final Dialog iDialog = ip.showInfiniteBlocking();
                    
                    Paiement r = new Paiement(
                        String.valueOf(NomSurCarte.getText()).toString(),
                        String.valueOf(NumCarte.getText()).toString(),
                        String.valueOf(MoisExp.getText()).toString(),
                        String.valueOf(AnneeExp.getText()).toString(),
                        String.valueOf(CVV.getText()).toString());
                       
                 
                    
                     
                    
                     
                    
                    System.out.println("data  paiement == "+r);
                    
                    
                      //appelle methode ajouterReclamation mt3 service Reclamation bch nzido données ta3na fi base
                        ServicePaiement.getInstance().ajouterPaiement(r);
                        
                      //na7iow loading bedajout
                      iDialog.dispose();
                      
                   
                      //ba3d mé naameel l ajout netaaada ll liste reclamation
                      new VerifPaiementForm(res).show();
                      
                      
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
    
   
    
}
