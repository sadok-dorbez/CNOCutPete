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
public class Evenement {
    
      
        private int id;
        private String  nom ;
        private String typeE  ;
        private String description  ;
        private String image  ;
        private String date_debut  ;
        private String date_fin  ;

    public Evenement() {
    }

    public Evenement(int id, String nom, String typeE, String description, String image, String date_debut, String date_fin) {
        this.id = id;
        this.nom = nom;
        this.typeE = typeE;
        this.description = description;
        this.image = image;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
    }

    public Evenement(String nom, String typeE, String description, String image, String date_debut, String date_fin) {
        this.nom = nom;
        this.typeE = typeE;
        this.description = description;
        this.image = image;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getTypeE() {
        return typeE;
    }

    public void setTypeE(String typeE) {
        this.typeE = typeE;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDate_debut() {
        return date_debut;
    }

    public void setDate_debut(String date_debut) {
        this.date_debut = date_debut;
    }

    public String getDate_fin() {
        return date_fin;
    }

    public void setDate_fin(String date_fin) {
        this.date_fin = date_fin;
    }

    
}
