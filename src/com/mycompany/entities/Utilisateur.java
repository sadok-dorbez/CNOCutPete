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
public class Utilisateur {
       
    private int id;
    private String username ;
    private String email ;
    private String password ;
    private String nom ;
    private String prenom ;

    public Utilisateur(String username, String email, String password, String nom, String prenom) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.nom = nom;
        this.prenom = prenom;
    }

    public Utilisateur() {
    }

    public Utilisateur(int id) {
        this.id = id;
    }

    public Utilisateur(String username, String email) {
        this.username = username;
        this.email = email;
    }

    
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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


    
    
    
}
