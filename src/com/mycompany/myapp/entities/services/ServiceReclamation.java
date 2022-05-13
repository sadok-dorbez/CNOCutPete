/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.entities.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.io.Storage;
import com.codename1.io.Util;
import com.codename1.ui.events.ActionListener;
import com.mycompany.myapp.entities.Reclamation;
import com.mycompany.myapp.utils.Statics;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
/**
 *
 * @author pfeis
 */
public class ServiceReclamation {
    
    
    //singleton 
     public static ServiceReclamation instance=null ;
     
     
     
     public static boolean resultOk =true;
     private ConnectionRequest req;
     
     
     
     public static ServiceReclamation getInstance(){
     
         if( instance == null )
             instance = new ServiceReclamation ();
         return instance ;
     }
     
         
      
   
     
     
     
     public ServiceReclamation (){
     
     req= new ConnectionRequest ();
     }
    
     
     
     //ajout
     public void ajouterReclamation(Reclamation reclamation ){
     
      int a = 1 ; 
       String url = Statics.BASE_URL+"/addReclamationJson/new?titre="+reclamation.getTitre()+"&contenu="+reclamation.getContenu()+"&user="+a;
       
         System.out.println(url);
       req.setUrl(url);
       req.addResponseListener((e)  ->  {
       
       String str = new String (req.getResponseData()); //Response json hethi il ritha fi navigateur kblia 
       System.out.println("data =="+str);
       
       });
       
         NetworkManager.getInstance().addToQueueAndWait(req); //execution ta3 request sinon yet3ada chy dima nal9awhaa
         
     }
             
     
     //affichage
     public ArrayList<Reclamation>affichageReclamations(){
     
     ArrayList<Reclamation> result = new ArrayList<>();
     String url = Statics.BASE_URL+"/reclamationJson" ;
     req.setUrl(url);
     
     
     
     req.addResponseListener(new ActionListener<NetworkEvent>() {
         @Override
         public void actionPerformed(NetworkEvent evt) {
                
             JSONParser jsonp;
             jsonp = new JSONParser ();
             
                    
             try{
                Map<String,Object>mapReclamtions = jsonp.parseJSON(new CharArrayReader(new String (req.getResponseData()) .toCharArray())); 
                List<Map<String,Object>> listOfMaps = (List<Map<String,Object>> ) mapReclamtions.get("root");
                  for (Map<String, Object> obj : listOfMaps) {
                      System.out.println(obj);
                    Reclamation re = new Reclamation ();
                      
                   //dima id fi codename one float nhothaaa
                   
                   float id = Float.parseFloat(obj.get("id").toString());
                   String titre = obj.get("titre").toString();
                   String contenu = obj.get("contenu").toString();
                   
                 re.setId((int)id);
                   re.setTitre(titre);
                   re.setContenu(contenu);
                   
                   result.add(re);
                 
                } 
                
             }catch (Exception ex){
                ex.printStackTrace();
             }
                           
         }
     });
       
                  NetworkManager.getInstance().addToQueueAndWait(req);      
                  return result;
     } 
     
     //Delete 
    public boolean deleteReclamation(int id ) {
        String url = Statics.BASE_URL +"/deleteReclamation2?id="+id;
        
        req.setUrl(url);
        
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                    
                    req.removeResponseCodeListener(this);
            }
        });
        
        NetworkManager.getInstance().addToQueueAndWait(req);
        return  resultOk;
    }
     
     public boolean Exporterpdf() {
            // System.out.println("Hello Update !");
            String file = "commande.pdf";
            Util.downloadUrlToStorage("http://127.0.0.1:8000/pdf", file, true);
            try {
                InputStream is = Storage.getInstance().createInputStream(file);
                return true;
             //   ToastBar.showMessage("Pdf ete Exporter", FontImage.MATERIAL_ACCESS_TIME);
            } catch (IOException ex) {
                ex.getStackTrace();
                return false;
               // ToastBar.showMessage("Verifier Le server Run !!", FontImage.MATERIAL_ACCESS_TIME);
            }
    }
     

   

 } 