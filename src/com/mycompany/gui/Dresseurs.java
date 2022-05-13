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
import com.codename1.ui.ComboBox;
import com.codename1.ui.Command;
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
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.entities.Dresseur;
import com.mycompany.myapp.entities.Reclamation;
import com.mycompany.myapp.entities.services.ServiceDresseur;
import com.mycompany.myapp.entities.services.ServiceReclamation;

/**
 *
 * @author sadok
 */
public class Dresseurs extends BaseForm {
    
    
    Form current;
    public Dresseurs(Resources res){
        super("Newsfeed",BoxLayout.y());  //heritage men newsfeed w le formulaire vertical
        
        Toolbar tb = new Toolbar(true);
        current = this;
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("Ajout Dresseur");
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
       
            RadioButton aaa = RadioButton.createToggle("Listes Dresseur", barGroup);
        aaa.setUIID("SelectBar");
       
        RadioButton partage = RadioButton.createToggle("Reserver dresseur", barGroup);
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
        
        
        TextField nom =  new TextField("","Entrer nom de dresseur");
        nom.setUIID("TextFieldBlack");
        addStringValue("nom",nom);
        
        TextField prenom =  new TextField("","Entrer prenom de dresseur");
        prenom.setUIID("TextFieldBlack");
        addStringValue("prenom",prenom);
        
        
        
        ComboBox<String> combo = new ComboBox<>();
        combo.addItem("chats");
        combo.addItem("chiens");
        Dresseur d = new Dresseur();
        combo.setSelectedItem(d.getSpecialite());
        add(combo);
        TextField email =  new TextField("","Entrer email de dresseur");
        email.setUIID("TextFieldBlack");
                addStringValue("email",email);

        TextField address =  new TextField("","Entrer address de dresseur");
        address.setUIID("TextFieldBlack");
        addStringValue("address",address);

        Label lbdate = new Label("Date:");
        add(lbdate);
        Picker datePicker = new Picker();
        
        datePicker.setType(Display.PICKER_TYPE_DATE);
        datePicker.setDate(d.getDate());
        add(datePicker);
          TextField num =  new TextField("","Entrer num de dresseur");
        prenom.setUIID("TextFieldBlack");
        addStringValue("num",num);
        
          TextField picture =  new TextField("","Entrer image de dresseur");
        picture.setUIID("TextFieldBlack");
        addStringValue("picture",picture);
        
        Button btnAjouter=new Button ("Ajouter");
        addStringValue("",btnAjouter);
        
       
        //onclick button event
        
        btnAjouter.addActionListener((e) -> {
            
         
            try {
                if(nom.getText().equals("") ||  prenom.getText().equals("")){
                    Dialog.show("Veuillez verifier les donnees","","Annuler","ok");
                }
                else{
                    InfiniteProgress ip = new InfiniteProgress();; //w entytestana fi data tetsab
                    final Dialog iDialog = ip.showInfiniteBlocking();
                    
                    Dresseur r = new Dresseur(String.valueOf(nom.getText()
                    ),
                            String.valueOf(prenom.getText()),
                    String.valueOf(combo.getSelectedItem()),
                            String.valueOf(email.getText()),
                            String.valueOf(address.getText()),
                            datePicker.getDate(),Integer.parseInt(num.getText()),String.valueOf(picture.getText()).toString());
                    
                    System.out.println("data  dresseur == "+r);
                    
                    
                      //appelle methode ajouterReclamation mt3 service Reclamation bch nzido données ta3na fi base
                        ServiceDresseur.getInstance().ajouterDresseur(d);
                        
                      //na7iow loading bedajout
                      iDialog.dispose();
                      
                   
                      //ba3d mé naameel l ajout netaaada ll liste reclamation
                      new ListReclamationForm(res).show();
                      
                      
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
