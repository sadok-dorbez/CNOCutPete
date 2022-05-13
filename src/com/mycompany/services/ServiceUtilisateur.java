/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.MultipartRequest;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Dialog;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.util.Resources;
import com.mycompany.entities.Reclamation;
import com.mycompany.entities.Utilisateur;
import com.mycompany.gui.AjoutReclamationForm;
import com.mycompany.gui.ListReclamationForm;
import com.mycompany.gui.SessionManager;
import com.mycompany.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Vector;

/**
 *
 * @author ziedm
 */
public class ServiceUtilisateur {
    
    //singleton 
     public static ServiceUtilisateur instance=null ;
         
    public static boolean resultOk = true;
    String json;
     
     private ConnectionRequest req;
     
  
     
     public static ServiceUtilisateur getInstance(){
     
         if( instance == null )
             instance = new ServiceUtilisateur ();
         return instance ;
     }
     
       
     
     public ServiceUtilisateur (){
     
     req= new ConnectionRequest ();
     }
    
     
    //signup
     
     public void signup (TextField username ,TextField email ,TextField password ,TextField confirmPassword , ComboBox<String> roles , Resources res ){
         
         
   
         String url = Statics.BASE_URL+"/user/signup?username="+username.getText().toString()+"&email="+email.getText().toString()+"&password="+password.getText().toString()+"&roles="+roles.getSelectedItem().toString();
         
         req.setUrl(url);
         
         
         //controll saisir
         if(username.getText().equals(" ") && password.getText().equals(" ")&& email.getText().equals(" ")) {
             Dialog.show ("Erreur","Veuillez remplir les champs SVP ","OK",null);
         }
         
         //hath w9et tsir le excution url
         req.addResponseListener((e) -> {
             
             //njib data il 7atithom fi form
             byte[]data = (byte []) e.getMetaData(); //lazem awel haja nhadhraha ke data ya3ni nakho id t3 kol text filed
             String responseData = new String (data); //b3dika na5o content
             
             System.out.println("data   === >"+responseData); 
         }
         );
         NetworkManager.getInstance().addToQueueAndWait(req);
         
  }
     
      //signup
     public void signin (TextField email,TextField password, Resources rs){
         
         
         String url = Statics.BASE_URL+"/user/signin?email="+email.getText().toString()+"&password="+password.getText().toString();
         
         req = new ConnectionRequest(url, false);  //false ya"ni url mazelt maweslitich le serveur
         req.setUrl(url);
         
            req.addResponseListener((e) -> {
             
            JSONParser j = new JSONParser();
            String json = new String(req.getResponseData())+ "";
            
            try{
            if(json.equals("failed")) {
                Dialog.show("Echec d'authentification","email ou mot de passe invalide","OK",null);
            }
            else{
                System.out.println("data =="+json);
                Map<String,Object> user = j.parseJSON(new CharArrayReader(json.toCharArray()));
                
                
                
                
                           
                //Session 
                float id = Float.parseFloat(user.get("id").toString());
                SessionManager.setId((int)id);//jibt id ta3 user ly3ml login w sajltha fi session ta3i  
                SessionManager.setPassowrd(user.get("password").toString());
                SessionManager.setUserName(user.get("username").toString());
                SessionManager.setEmail(user.get("email").toString());
                
                
                
               
                System.out.println("current user ==>"+SessionManager.getEmail()+", "+SessionManager.getPassowrd()+", "+SessionManager.getPassowrd());
                
                
                
                
                if(user.size() >0 ) // l9a user
                   // new ListReclamationForm(rs).show();//yemchi lel list reclamation
                    new AjoutReclamationForm(rs).show();
                    
                    }
            
            }catch(Exception ex) {
                ex.printStackTrace();
            }
            
            
            
        });
    
         //ba3d execution ta3 requete ely heya url nestanaw response ta3 server.
        NetworkManager.getInstance().addToQueueAndWait(req);
    }
                    
      
      //heki 5dmtha taw nhabtha ala description
      public String getPasswordByEmail(String email, Resources rs ) {
        
        
        String url = Statics.BASE_URL+"/user/getPasswordByEmail?email="+email;
        req = new ConnectionRequest(url, false); //false ya3ni url mazlt matba3thtich lel server
        req.setUrl(url);
        
        req.addResponseListener((e) ->{
            
            JSONParser j = new JSONParser();
            
             json = new String(req.getResponseData()) + "";
            
            
            try {
            
          
                System.out.println("data =="+json);
                
                Map<String,Object> password = j.parseJSON(new CharArrayReader(json.toCharArray()));
                
                
            
            
            }catch(Exception ex) {
                ex.printStackTrace();
            }
            
            
            
        });
    
         //ba3d execution ta3 requete ely heya url nestanaw response ta3 server.
        NetworkManager.getInstance().addToQueueAndWait(req);
    return json;
    }              
      
      //edit utilisateur
      
      public static void EditUser(String username ,String password ,String email){
          
          String url = Statics.BASE_URL +"/user/edituser?username="+username+"&password="+password+"&email="+email;
                   MultipartRequest req = new MultipartRequest();
        req.setUrl(url);
        req.setPost(true);
        req.addArgument("id", String.valueOf(SessionManager.getId()));
        req.addArgument("username", username);
        req.addArgument("email", email);
        req.addArgument("password", password);
        System.out.println(email);
        req.addResponseListener((response) -> {
            byte[] data = (byte[]) response.getMetaData();
            String s = new String(data);
            System.out.println(s);
            if (s.equals("success")) {
                
                Dialog.show("SUCCESS","Changed was saved","OK",null);

            } else {
               // Dialog.show("Erreur", "Echec de modification", "OK", null);

            }

        });
        NetworkManager.getInstance().addToQueueAndWait(req);

    }
    
      
      
     
     
}
