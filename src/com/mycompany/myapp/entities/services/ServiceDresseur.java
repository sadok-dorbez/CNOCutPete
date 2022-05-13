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
import com.codename1.l10n.ParseException;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.events.ActionListener;
import com.mycompany.myapp.entities.Dresseur;
import com.mycompany.myapp.utils.Statics;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *
 * @author sadok
 */
public class ServiceDresseur{
    
    
    //singleton 
     public static ServiceDresseur instance=null ;
     
     
     
     public static boolean resultOk =true;
     private ConnectionRequest req;
     
     
     
     public static ServiceDresseur getInstance(){
     
         if( instance == null )
             instance = new ServiceDresseur ();
         return instance ;
     }
     
         
      
   
     
     
     
     public ServiceDresseur (){
     
     req= new ConnectionRequest ();
     }
    
     
     
     //ajout
     public void ajouterDresseur(Dresseur d ){
     
      int a = 1 ; 
       String url = Statics.BASE_URL+"/addDresseurJson/new?nom="+d.getNom()+"&prenom="+d.getPrenom()+"&specialite="+d.getSpecialite()+"&email="+d.getEmail()+"&address="+d.getAddress()+"&date="+d.getDate()+"&num="+d.getNum()+"&picture="+d.getPicture();
       
         System.out.println(url);
       req.setUrl(url);
       req.addResponseListener((e)  ->  {
       
       String str = new String (req.getResponseData()); //Response json hethi il ritha fi navigateur kblia 
       System.out.println("data =="+str);
       
       });
       
         NetworkManager.getInstance().addToQueueAndWait(req); //execution ta3 request sinon yet3ada chy dima nal9awhaa
         
     }
             
     
    
//public void EditDresseur(Dresseur d) {
//        String url = Statics.BASE_URL + "facture/updateFacture?id="+f.getIdFacture()+"&date=" + facture.getDateFacture()
//                + "&remise=" + facture.getRemiseFacture() + "&total=" + facture.getTotalFacture()+ "&type=" + facture.getTypeFature();
//        System.out.println(url);
//        req.setUrl(url);
//        req.addResponseListener((e) -> {
//
//            String str = new String(req.getResponseData());
//            //reponse json hedhi elli rynaha fil naviguateur 
//
//            System.out.println("data == " + str);
//        });
//
//        NetworkManager.getInstance().addToQueueAndWait(req);//execution mtaie request sinon yitada chay dima nalkawha 
//
//    }
     
     //affichage
     public ArrayList<Dresseur>affichageDresseurs(){
     
     ArrayList<Dresseur> result = new ArrayList<>();
     String url = Statics.BASE_URL+"/DresseurJson" ;
     req.setUrl(url);
     
     
     
     req.addResponseListener(new ActionListener<NetworkEvent>() {
         @Override
         public void actionPerformed(NetworkEvent evt) {
                
             JSONParser jsonp;
             jsonp = new JSONParser ();
             
                    
             try{
                Map<String,Object>mapDresseurs = jsonp.parseJSON(new CharArrayReader(new String (req.getResponseData()) .toCharArray())); 
                List<Map<String,Object>> listOfMaps = (List<Map<String,Object>> ) mapDresseurs.get("root");
                  for (Map<String, Object> obj : listOfMaps) {
                      System.out.println(obj);
                    Dresseur re = new Dresseur ();
                      
                   //dima id fi codename one float nhothaaa
                      System.out.println(obj);
                   float id = Float.parseFloat(obj.get("id").toString());
                   String nom = obj.get("nom").toString();
                   String prenom = obj.get("prenom").toString();
                   String specialite = obj.get("specialite").toString();
                   String email = obj.get("email").toString();
                   String address = obj.get("address").toString();
                   int num = 55448209;
                   String picture = obj.get("picture").toString();
                   
                   try {  
                            Date date1=new SimpleDateFormat("yyyy-MM-dd").parse(obj.get("date").toString());
                            re.setDate(date1);

                        } catch (ParseException ex) {
                            System.out.println(ex);
                        }
                          re.setId((int)id);
                   re.setNom(nom);
                   re.setPrenom(prenom);
                   re.setEmail(email);
                   re.setAddress(address);
                   re.setSpecialite(specialite);
                   re.setNum(num);
                   re.setPicture(picture);
                   
                   
                   result.add(re);
                 
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
              
                 
                
         
         }
     });
       
                  NetworkManager.getInstance().addToQueueAndWait(req);      
                  return result;
     } 
     
     //Delete 
    public boolean deleteDresseur(int id ) {
        String url = Statics.BASE_URL +"/deleteDresseur?id="+id;
        
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
