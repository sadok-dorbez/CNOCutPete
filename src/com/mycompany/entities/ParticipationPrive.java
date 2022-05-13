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
public class ParticipationPrive {
    
     private int id;
     private String numeroTel   ;
     private String nbrPrisecharge ;
     private int user_id ;

    public ParticipationPrive() {
    }

    public ParticipationPrive(int id, String numeroTel, String nbrPrisecharge,int user_id) {
        this.id = id;
        this.numeroTel = numeroTel;
        this.nbrPrisecharge = nbrPrisecharge;
        this.user_id = user_id;
    }

    public ParticipationPrive(String numeroTel, String nbrPrisecharge) {
        this.numeroTel = numeroTel;
        this.nbrPrisecharge = nbrPrisecharge;
    }
        public ParticipationPrive(String numeroTel, String nbrPrisecharge,int user_id) {
        this.numeroTel = numeroTel;
        this.nbrPrisecharge = nbrPrisecharge;
        this.user_id = user_id;
    }


    
    
    
    
    
    
    
    
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNumeroTel() {
        return numeroTel;
    }

    public void setNumeroTel(String numeroTel) {
        this.numeroTel = numeroTel;
    }

    public String getNbrPrisecharge() {
        return nbrPrisecharge;
    }

    public void setNbrPrisecharge(String nbrPrisecharge) {
        this.nbrPrisecharge = nbrPrisecharge;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }
    
     
     
}
