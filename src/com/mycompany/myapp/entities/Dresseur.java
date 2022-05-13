/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.entities;

import java.util.Date;

/**
 *
 * @author sadok
 */
public class Dresseur {
   private int  id ; 
   private String nom ; 
   private String prenom  ; 
   private String specialite ; 
   private String email ; 
   private String address ; 
   private Date date ; 
   private int num ; 
   private String picture ; 

    public Dresseur() {
    }

    public Dresseur(int id, String nom, String prenom, String specialite, String email, String address, Date date, int num, String picture) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.specialite = specialite;
        this.email = email;
        this.address = address;
        this.date = date;
        this.num = num;
        this.picture = picture;
    }

    public Dresseur(String nom, String prenom, String specialite, String email, String address, Date date, int num, String picture) {
        this.nom = nom;
        this.prenom = prenom;
        this.specialite = specialite;
        this.email = email;
        this.address = address;
        this.date = date;
        this.num = num;
        this.picture = picture;
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

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getSpecialite() {
        return specialite;
    }

    public void setSpecialite(String specialite) {
        this.specialite = specialite;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    @Override
    public String toString() {
        return "Dresseur{" + "id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", specialite=" + specialite + ", email=" + email + ", address=" + address + ", date=" + date + ", num=" + num + ", picture=" + picture + '}';
    }
    
    
}
