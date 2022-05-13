/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.mycompany.entities.Evenement;

import com.mycompany.utils.Statics;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author ziedm
 */
public class ServiceEvenement {
     
    //singleton 
     public static ServiceEvenement instance=null ;
     
     
     
     public static boolean resultOk =true;
     private ConnectionRequest req;
     
     
     
     public static ServiceEvenement getInstance(){
     
         if( instance == null )
             instance = new ServiceEvenement ();
         return instance ;
     }
     
         
      
   
     
     
     
     public ServiceEvenement (){
     
     req= new ConnectionRequest ();
     }
    
     
     
     
     
     
     
     
     
     
     
     //ajout
     public void ajouterevenement(Evenement Evenement ){
     
     
       String url =Statics.BASE_URL+"/addEvenementJson/new?nom="+Evenement.getNom()+"&typeE="+Evenement.getTypeE()+"&description="+Evenement.getDescription()+"&image="+Evenement.getImage()+"&date_debut="+Evenement.getDate_debut()+"&date_fin="+Evenement.getDate_fin();
       
       
       req.setUrl(url);
       req.addResponseListener((e)  ->  {
       
       String str = new String (req.getResponseData()); //Response json hethi il ritha fi navigateur kblia 
       System.out.println("data =="+str);
       
       });
       
         NetworkManager.getInstance().addToQueueAndWait(req); //execution ta3 request sinon yet3ada chy dima nal9awhaa
         
     }
             
     
     //affichage
     public ArrayList<Evenement>affichageEvenements(){
     
     ArrayList<Evenement> result = new ArrayList<>();
     String url = Statics.BASE_URL+"/EvenementJson" ;
     req.setUrl(url);
     
     
     
     req.addResponseListener(new ActionListener<NetworkEvent>() {
         @Override
         public void actionPerformed(NetworkEvent evt) {
                
             JSONParser jsonp;
             jsonp = new JSONParser ();
             
                    
             try{
                Map<String,Object>mapEvenements = jsonp.parseJSON(new CharArrayReader(new String (req.getResponseData()) .toCharArray())); 
                List<Map<String,Object>> listOfMaps = (List<Map<String,Object>> ) mapEvenements.get("root");
                  for (Map<String, Object> obj : listOfMaps) {
                    Evenement re = new Evenement ();
                      
                   //dima id fi codename one float nhothaaa
                   
                  // float id = Float.parseFloat(obj.get("id").toString());
                   String nom = obj.get("nom").toString();
                   String typeE = obj.get("typeE").toString();
                   String description = obj.get("description").toString();
                   String image = obj.get("image").toString();
                   String date_debut = obj.get("date_debut").toString();
                   String date_fin = obj.get("date_fin").toString();
                   
                 // re.setId((int)id);
                   re.setNom(nom);
                   re.setTypeE(typeE);
                    re.setDescription(description);
                   re.setImage(image);
                    re.setDate_debut(date_debut);
                   re.setDate_fin(date_fin);
                   
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
    public boolean deleteEvenement(int id ) {
        String url = Statics.BASE_URL +"/deleteReclamation5?id="+id;
        
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
     
   

 } 