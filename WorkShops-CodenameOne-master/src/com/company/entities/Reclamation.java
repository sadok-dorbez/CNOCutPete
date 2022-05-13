/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.entities;

import java.util.Date;

/**
 *
 * @author ziedm
 */
public class Reclamation {

    public static void getConteu() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
    private int id ;
    private String titre,contenu ;

    
    

    public Reclamation (int id, String titre ,String contenu){
        
        
  this.id = id ;
  this.titre = titre ;
  this.contenu = contenu ;
      
    }
    
        public Reclamation ( float donation , Date date){
        
  this.titre = titre ;
  this.contenu = contenu ;
      
    }

    public Reclamation() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public int getId(){
        return id ;
    }
    
    public void setId(){
        this.id = id ;
    }
    
        public String getTitre(){
        return titre ;
    }
    
    public void setTitre(){
        this.titre = titre ;
    }
    
      public String getContenu(){
        return contenu ;
    }
    
    public void setContenu(){
        this.contenu = contenu ;
    }

    public void setId(int i) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void setTitre(String titre) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
