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
import com.mycompany.entities.ParticipationPublic;
import com.mycompany.utils.Statics;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author ziedm
 */
public class ServiceParticipationPublic {
    
    
    
     
    //singleton 
     public static ServiceParticipationPublic instance=null ;
     
      public static boolean resultOk =true;
     private ConnectionRequest req;
     
     
     public static ServiceParticipationPublic getInstance(){
     
         if( instance == null )
             instance = new ServiceParticipationPublic ();
         return instance ;
     }
     
         
     
     public ServiceParticipationPublic (){
     
     req= new ConnectionRequest ();
     }
    
     
     
     //ajout
     public void ajouterParticipationPublic(ParticipationPublic ParticipationPublic ){
     
     
       String url =Statics.BASE_URL+"/addparticipationpublicJson/new?donation="+ParticipationPublic.getDonation()+"&user="+ParticipationPublic.getUser_id();
       
       
       req.setUrl(url);
       req.addResponseListener((e)  ->  {
       
       String str = new String (req.getResponseData()); //Response json hethi il ritha fi navigateur kblia 
       System.out.println("data =="+str);
       
       });
       
         NetworkManager.getInstance().addToQueueAndWait(req); //execution ta3 request sinon yet3ada chy dima nal9awhaa
         
     }
      
     
     
     
     
       //affichage
     public ArrayList<ParticipationPublic>affichageParticipationPublics(){
     
     ArrayList<ParticipationPublic> result = new ArrayList<>();
     String url = Statics.BASE_URL+"/ParticipationPublicJson" ;
     req.setUrl(url);
     
     
     
     req.addResponseListener(new ActionListener<NetworkEvent>() {
         @Override
         public void actionPerformed(NetworkEvent evt) {
                
             JSONParser jsonp;
             jsonp = new JSONParser ();
             
                    
             try{
                Map<String,Object>mapParticipationPublics = jsonp.parseJSON(new CharArrayReader(new String (req.getResponseData()) .toCharArray())); 
                List<Map<String,Object>> listOfMaps = (List<Map<String,Object>> ) mapParticipationPublics.get("root");
                  for (Map<String, Object> obj : listOfMaps) {
                    ParticipationPublic re = new ParticipationPublic ();
                      
                   //dima id fi codename one float nhothaaa
                   
                  // float id = Float.parseFloat(obj.get("id").toString());
                   String donation = obj.get("donation").toString();
            
                   
                 //  re.setId((int)id);
                   re.setDonation(donation);
            
                   
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
   
     
       //Delete 
    public boolean deleteParticipationPublic(int id ) {
        String url = Statics.BASE_URL +"/deleteReclamation1?id="+id;
        
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
