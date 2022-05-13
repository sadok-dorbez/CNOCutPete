/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;


//CODE TEMPLATE :



import com.codename1.charts.views.BarChart;
import com.codename1.charts.views.BarChart.Type;
import com.codename1.charts.ChartComponent;
import com.codename1.charts.models.CategorySeries;
import com.codename1.charts.models.XYMultipleSeriesDataset;
import com.codename1.charts.renderers.DefaultRenderer;
import com.codename1.charts.renderers.SimpleSeriesRenderer;
import com.codename1.charts.renderers.XYMultipleSeriesRenderer;
import com.codename1.charts.renderers.XYMultipleSeriesRenderer.Orientation;
import com.codename1.charts.renderers.XYSeriesRenderer;
import com.codename1.charts.util.ColorUtil;
import com.codename1.charts.views.PieChart;
import com.codename1.components.ScaleImageLabel;
import com.codename1.components.SpanLabel;
import com.codename1.components.ToastBar;
import com.codename1.ui.Button;
import com.codename1.ui.ButtonGroup;
import com.codename1.ui.Command;
import com.codename1.ui.Component;
import static com.codename1.ui.Component.BOTTOM;
import static com.codename1.ui.Component.CENTER;
import static com.codename1.ui.Component.LEFT;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.Font;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.RadioButton;
import com.codename1.ui.Tabs;
import com.codename1.ui.TextArea;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;

import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author Chédy
 */

public class BackAdminForm extends BaseForm {
  
        
    private boolean drawOnMutableImage;
   
    
    private double nbr_feedback = 45 ;
    private double nbr_reclamation = 29 ;
    
    Form current;
BaseForm form;
        public BackAdminForm(Resources res)  {
        super("Newsfeed", BoxLayout.y());
            current= this;

        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("Acceuill");
        getContentPane().setScrollVisible(false);
        
        super.addSideMenu(res);
        tb.addSearchCommand(e -> {});
        
        Tabs swipe = new Tabs();

        Label spacer1 = new Label();
        Label spacer2 = new Label();
        addTab(swipe, res.getImage("back-logo.jpeg"), spacer1, "Bienvenue");
                
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
        for(int iter = 0 ; iter < rbs.length ; iter++) {
            rbs[iter] = RadioButton.createToggle(unselectedWalkthru, bg);
            rbs[iter].setPressedIcon(selectedWalkthru);
            rbs[iter].setUIID("Label");
            radioContainer.add(rbs[iter]);
        }
                
        rbs[0].setSelected(true);
        swipe.addSelectionListener((i, ii) -> {
            if(!rbs[ii].isSelected()) {
                rbs[ii].setSelected(true);
            }
        });
        refreshTheme();
        Component.setSameSize(radioContainer, spacer1, spacer2);
        add(LayeredLayout.encloseIn(swipe, radioContainer));
        
        ButtonGroup barGroup = new ButtonGroup();
        
        RadioButton all = RadioButton.createToggle("Feedback", barGroup);
        
        
        
        RadioButton mesListes = RadioButton.createToggle("Liste public", barGroup);
        mesListes.setUIID("SelectBar");
         
        RadioButton mesListesForum = RadioButton.createToggle("Liste Forum", barGroup);
        mesListesForum.setUIID("SelectBar");
        
         RadioButton mesListesReclamation = RadioButton.createToggle("Liste rec ", barGroup);
        mesListesReclamation.setUIID("SelectBar");
        
           RadioButton mesListesParticipationsPrive = RadioButton.createToggle("Liste prive ", barGroup);
        mesListesParticipationsPrive.setUIID("SelectBar");
        
            RadioButton ajouterevent = RadioButton.createToggle("Evenement ", barGroup);
        ajouterevent.setUIID("SelectBar");
        
        
        RadioButton profile = RadioButton.createToggle("Statistique", barGroup);
        profile.setUIID("SelectBar");
        Label arrow = new Label(res.getImage("news-tab-down-arrow.png"), "Container");
        
        add(LayeredLayout.encloseIn(
                GridLayout.encloseIn(6,mesListes,mesListesForum,mesListesReclamation,mesListesParticipationsPrive,ajouterevent,profile),
                FlowLayout.encloseBottom(arrow)
        ));
        all.setSelected(true);
        arrow.setVisible(false);
        addShowListener(e -> {
            arrow.setVisible(true);
            updateArrowPosition(all, arrow);
           
        });
        
        
        
           
             mesListes.addActionListener((e)->{
         new ListParticipationPublicForm(res).show(); 
          });
              mesListesReclamation.addActionListener((e)->{
         new ListReclamationForm(res).show(); 
          });
                  mesListesParticipationsPrive.addActionListener((e)->{
         new ListParticipationPriveForm(res).show(); 
          });
         mesListesForum.addActionListener((e)->{
         new ListMessageForm(res).show(); 
          });
             ajouterevent.addActionListener((e)->{
         new AjouterEvenementForm(res).show(); 
          });
    
    
        
        
        bindButtonSelection(profile, arrow);
                profile.addActionListener((e)->{
                  new BackAdminForm(res).show();
                  
        });
        // special case for rotation
        addOrientationListener(e -> {
            updateArrowPosition(barGroup.getRadioButton(barGroup.getSelectedIndex()), arrow);
        });
    
        
      //app
      createPieChartForm();
        
        }
    
    
    
    
    
     private void updateArrowPosition(Button b, Label arrow) {
        arrow.getUnselectedStyle().setMargin(LEFT, b.getX() + b.getWidth() / 2 - arrow.getWidth() / 2);
        arrow.getParent().repaint();
        
        
    }
    
    private void addTab(Tabs swipe, Image img, Label spacer, String text) {
        int size = Math.min(Display.getInstance().getDisplayWidth(), Display.getInstance().getDisplayHeight());
        if(img.getHeight() < size) {
            img = img.scaledHeight(size);
        }

        if(img.getHeight() > Display.getInstance().getDisplayHeight() / 2) {
            img = img.scaledHeight(Display.getInstance().getDisplayHeight() / 2);
        }
        ScaleImageLabel image = new ScaleImageLabel(img);
        image.setUIID("Container");
        image.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);
        Label overlay = new Label(" ", "ImageOverlay");
        
        Container page1 = 
            LayeredLayout.encloseIn(
                image,
                overlay,
                BorderLayout.south(
                    BoxLayout.encloseY(
                            new SpanLabel(text, "LargeWhiteText"),
                            spacer
                        )
                )
            );

        swipe.addTab("", page1);
    }
    
   private void addButton(Image img,String title) {
          int height = Display.getInstance().convertToPixels(11.5f);
        int width = Display.getInstance().convertToPixels(14f);
        Button image = new Button(img.fill(width, height));
        image.setUIID("Label");
        Container cnt = BorderLayout.west(image);
        cnt.setLeadComponent(image);
        TextArea ta = new TextArea(title);
        ta.setUIID("NewsTopLine");
        ta.setEditable(false);

  ;       
      
       cnt.add(BorderLayout.CENTER, 
               BoxLayout.encloseY(
                       ta
               ));
       
       image.addActionListener(e -> {
           try{
           new AjoutReclamationForm(Resources.getGlobalResources()).show();
           }catch(Exception exx) {
               
           }
               });
        add(cnt);
        image.addActionListener(e -> ToastBar.showMessage(title, FontImage.MATERIAL_INFO));
   }
    private void bindButtonSelection(Button b, Label arrow) {
        b.addActionListener(e -> {
            if(b.isSelected()) {
                updateArrowPosition(b, arrow);
            }
        });
    }
    // Statistique  :
    //fonction : bech n7adhro size t3 stat w margin w colors ba3d chn3aytoulha methode hedhi.
    public DefaultRenderer buiDefaultRenderer(int []colors){
        
        DefaultRenderer renderer = new DefaultRenderer();
        renderer.setLegendTextSize(15);
        renderer.setLegendTextSize(15);
        renderer.setMargins(new int[] {20, 30, 15, 0});
        
        for(int color : colors){
            SimpleSeriesRenderer simpleSeriesRenderer = new SimpleSeriesRenderer();
          
            simpleSeriesRenderer.setColor(color);
            renderer.addSeriesRenderer(simpleSeriesRenderer);
        }
        return renderer;
    }

    
    public void createPieChartForm() {
        
        //chna3ml stat feedback rapport reclamation
        double total = nbr_feedback + nbr_reclamation ;
        
        //values
        double prcntFeed = (nbr_feedback *100)/total;
        
        double prcntRec = (nbr_reclamation *100)/total;

        //colors set :
        int[]colors = new int[]{0xf4b342,0x52b29a};
        
        DefaultRenderer renderer = buiDefaultRenderer(colors);
        renderer.setLabelsColor(0x000000); //black color for labels.
        
        renderer.setZoomButtonsVisible(true); //zoom
        renderer.setLabelsTextSize(20);
        renderer.setZoomEnabled(true);
        renderer.setChartTitleTextSize(20);
        renderer.setDisplayValues(true);
        renderer.setShowLabels(true);
       
        
        
        SimpleSeriesRenderer r = renderer.getSeriesRendererAt(0);
        r.setHighlighted(true);
        //create the chart ...
        PieChart chart = new PieChart (buildDataset("title",Math.round(prcntFeed),Math.round(prcntRec)), renderer);
        
        // n7oto chart fi component
        ChartComponent c = new ChartComponent(chart);
        
        String []messages = {
            "Statistique Participation % reclamations"
        };
        
        SpanLabel message = new SpanLabel(messages[0], "WelcomeMessage");
        
        Container cnt = BorderLayout.center(message);
        cnt.setUIID("Container");
        add(cnt);
        add(c);
    }

    private CategorySeries buildDataset(String title, double prcntFeed, double prcntRec) {
      
        CategorySeries series = new CategorySeries(title);
        
        series.add("Reclamation",prcntRec);
        series.add("Participation",prcntRec);
        
        return series;
    }
    
}