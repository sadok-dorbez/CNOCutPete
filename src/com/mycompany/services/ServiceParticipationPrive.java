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
import com.mycompany.entities.ParticipationPrive;
import com.mycompany.entities.ParticipationPublic;
import static com.mycompany.services.ServiceReclamation.resultOk;
import com.mycompany.utils.Statics;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author ziedm
 */
public class ServiceParticipationPrive {
     
    //singleton 
     public static ServiceParticipationPrive instance=null ;
     
     public static boolean resultOk =true;
     private ConnectionRequest req;
     
     
     public static ServiceParticipationPrive getInstance(){
     
         if( instance == null )
             instance = new ServiceParticipationPrive ();
         return instance ;
     }
     
         
     
     public ServiceParticipationPrive (){
     
     req= new ConnectionRequest ();
     }
    
     
     
     //ajout
     public void ajouterParticipationPrive(ParticipationPrive ParticipationPrive ){
     
     
       String url =Statics.BASE_URL+"/addparticipationpriveJson/new?numeroTel="+ParticipationPrive.getNumeroTel()+"&nbrPrisecharge="+ParticipationPrive.getNbrPrisecharge()+"&user="+ParticipationPrive.getUser_id();
       
       
       req.setUrl(url);
       req.addResponseListener((e)  ->  {
       
       String str = new String (req.getResponseData()); //Response json hethi il ritha fi navigateur kblia 
       System.out.println("data =="+str);
       
       });
       
         NetworkManager.getInstance().addToQueueAndWait(req); //execution ta3 request sinon yet3ada chy dima nal9awhaa
         
     }
      
     
     
       //affichage
     public ArrayList<ParticipationPrive>affichageParticipationPrives(){
     
     ArrayList<ParticipationPrive> result = new ArrayList<>();
     String url = Statics.BASE_URL+"/ParticipationPublicJson1" ;
     req.setUrl(url);
     
     
     
     req.addResponseListener(new ActionListener<NetworkEvent>() {
         @Override
         public void actionPerformed(NetworkEvent evt) {
                
             JSONParser jsonp;
             jsonp = new JSONParser ();
             
                    
             try{
                Map<String,Object>mapParticipationPrives = jsonp.parseJSON(new CharArrayReader(new String (req.getResponseData()) .toCharArray())); 
                List<Map<String,Object>> listOfMaps = (List<Map<String,Object>> ) mapParticipationPrives.get("root");
                  for (Map<String, Object> obj : listOfMaps) {
                    ParticipationPrive re = new ParticipationPrive ();
                      
                   //dima id fi codename one float nhothaaa
                   
                  // float id = Float.parseFloat(obj.get("id").toString());
                   String numeroTel = obj.get("numeroTel").toString();
                   String nbrPrisecharge = obj.get("nbrPrisecharge").toString();
                   
            
                   
                 //  re.setId((int)id);
                   re.setNumeroTel(numeroTel);
                       re.setNbrPrisecharge(nbrPrisecharge);
            
                   
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
    public boolean deleteParticipation(int id ) {
        String url = Statics.BASE_URL +"/deleteReclamation?id="+id;
        
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
     