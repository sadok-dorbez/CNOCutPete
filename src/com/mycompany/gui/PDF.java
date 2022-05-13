/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.util.Resources;
import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.mycompany.gui.BaseForm;
import com.mycompany.myapp.entities.Reclamation;
import com.mycompany.myapp.entities.services.ServiceReclamation;
import java.util.ArrayList;

/**
 *
 * @author sadok
 */
public class PDF extends BaseForm {

    public PDF(Resources res) {
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        tb.setTitle("Liste des reclamations");
        getContentPane().setScrollVisible(true);
        super.addSideMenu(res);

       
   

        //this.theme=theme;
        SpanLabel sp = new SpanLabel();

        sp.setText(ServiceReclamation.getInstance().affichageReclamations().toString());
        add(sp);
        //// f twig 
        Button pdf = new Button("pdf");
        add(pdf);
        pdf.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent evt) {


                    String path = "";
                    Document document = new Document();
                    try {

                    //    PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(path + "reclamation.pdf"));

                        document.open();
                        PdfPTable tb1 = new PdfPTable(12);
                        tb1.addCell("iduser");
                        tb1.addCell("titre");
                        tb1.addCell("contenu");

                   

                        ServiceReclamation es = new ServiceReclamation();
                        ArrayList<Reclamation> list = es.affichageReclamations();
                        for (Reclamation m : list) {

                            /*   String id_user = String.valueOf(m.getIdUser());
                            String prix = String.valueOf(m.getPrix());
                            String description = String.valueOf(m.getDescription());

                         

                            tb1.addCell(id_user);
                            tb1.addCell(prix);
                            tb1.addCell(description);
                            //tb1.addCell(image);
                            tb1.addCell(quantity);
                            tb1.addCell(confirmed);
*/
                        }
                        document.add(new Paragraph("Reclamation"));

                        document.add(tb1);
                        document.close();
         //writer.close();
                        com.codename1.io.File file = new com.codename1.io.File("rec.pdf");
                        new ListReclamationForm(res).show();

 //   desktop.open(file);
                    } 
                    catch (Exception e){
                        e.printStackTrace();
                    
                  
                   
      
              }}
                    //Logger.getLogger(ListFormation.class.getName()).log(Level.SEVERE, null, ex);

                    //Logger.getLogger(ListFormation.class.getName()).log(Level.SEVERE, null, ex);
                    //getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());

          //  @Override
     //       public void actionPerformed(ActionEvent evt) {
            //    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
           // }

             


        });}
}
