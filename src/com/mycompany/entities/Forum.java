/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.entities;

/**
 *
 * @author ziedm
 */
public class Forum {
        
    
    private int id;
    private String titre,contenu ;
    
    private int user_id;

    public Forum() {
    }

  
    public Forum(int id, String titre, String contenu,int user_id ) {
        this.id = id;
        this.titre = titre;
        this.contenu = contenu;
        this.user_id = user_id;
    }
    

    public Forum(String titre, String contenu) {
        this.titre = titre;
        this.contenu = contenu;
    }
    
    
    public Forum(String titre, String contenu ,int user_id ) {
        this.titre = titre;
        this.contenu = contenu;
           this.user_id = user_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getContenu() {
        return contenu;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }
    
    
    
    
}
