/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.service;


import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;

import com.codename1.ui.events.ActionListener;
import com.company.entities.Reclamation;
import com.company.utilis.Statics;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 *
 * @author ziedm
 */
public class ServiceReclamation {
    
    
    //singleton 
    public static ServiceReclamation instance = null ;
    
    
    //initialisation connection req
    
    private ConnectionRequest req ;
    
    
    public static ServiceReclamation getInstance(){
        
        if (instance == null )
            
            instance = new ServiceReclamation();
        return instance ;
            
    }
    
    
    public ServiceReclamation(){
        
        req = new ConnectionRequest();
    }
    
    //ajout
    public void ajoutReclamation (Reclamation reclamation){
        
        
        String url = Statics.BASE_URL+"addReclamationJson/new?titre="+reclamation.getTitre()+"&contenu="+reclamation.getContenu();
        
        req.setUrl(url);
        req.addResponseListener((e) -> {
            
          String str = new String (req.getResponseData())  ;
          System.out.println("data == "+str);
            
        });
        
        NetworkManager.getInstance().addToQueueAndWait(req);
    }
    
    //affichage 
    
    public ArrayList<Reclamation>affichageReclamations(){
        ArrayList<Reclamation> result = new ArrayList<>();
        
        String url = Statics.BASE_URL+"/reclamationJson";
        req.setUrl(url);
        
        req.addResponseListener(new ActionListener<NetworkEvent>(){
            @Override
            
            public void actionPerformed(NetworkEvent evt){
                JSONParser jsonp ;
                jsonp =new JSONParser();
                
                try {
                    Map<String,Object>mapReclamations = jsonp.parseJSON(new CharArrayReader (new String (req.getResponseData()).toCharArray()));
                    
                    List<Map<String,Object>>listOfMaps = (List<Map<String,Object>>) mapReclamations.get("root");
                    
                    for (Map<String, Object> obj : listOfMaps){
                         Reclamation re = new Reclamation();
                         
                         // dima id float fel codenameone
                         float id  =  Float.parseFloat((String) obj.get("id"));
                         
                         String titre = obj.get("titre").toString();
                         String contenu = obj.get("contenu").toString();
                         
                         
                         re.setId((int)id);
                         re.setTitre(titre);
                         
                         
                         
                         result.add(re);
                    }
                    
                    
                    
                  }catch (Exception ex){
                      
                      ex.printStackTrace();
                  }
                
            }
        });
        
        NetworkManager.getInstance().addToQueueAndWait(req);
        
      return result ;  
      
    }
    
    
    
}
