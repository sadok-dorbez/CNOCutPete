/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.services;

import com.codename1.io.ConnectionRequest;
import com.codename1.io.NetworkManager;
import com.mycompany.entities.Paiement;
import com.mycompany.entities.ParticipationPrive;
import com.mycompany.utils.Statics;

/**
 *
 * @author ziedm
 */
public class ServicePaiement {
    
    
     //singleton 
     public static ServicePaiement instance=null ;
     
     
     private ConnectionRequest req;
     
     
     public static ServicePaiement getInstance(){
     
         if( instance == null )
             instance = new ServicePaiement ();
         return instance ;
     }
     
         
     
     public ServicePaiement (){
     
     req= new ConnectionRequest ();
     }
    
     
     
     //ajout
     public void ajouterPaiement(Paiement Paiement ){
     
     
       String url =Statics.BASE_URL+"/addpaiementJson/new?NomSurCarte="+Paiement.getNomSurCarte()+"&NumCarte="+Paiement.getNumCarte()+"&MoisExp="+Paiement.getMoisExp()+"&AnneeExp="+Paiement.getAnneeExp()+"&CVV="+Paiement.getCVV()      ;
       
       
       req.setUrl(url);
       req.addResponseListener((e)  ->  {
       
       String str = new String (req.getResponseData()); //Response json hethi il ritha fi navigateur kblia 
       System.out.println("data =="+str);
       
       });
       
         NetworkManager.getInstance().addToQueueAndWait(req); //execution ta3 request sinon yet3ada chy dima nal9awhaa
         
     }
      
     
    
}
