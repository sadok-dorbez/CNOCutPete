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
public class Paiement {
    
        private int id;
        private String NomSurCarte ;
        private String NumCarte ;
        private String MoisExp ;
        private String AnneeExp ;
        private String CVV ;

    public Paiement() {
    }

    public Paiement(int id, String NomSurCarte, String NumCarte, String MoisExp, String AnneeExp, String CVV) {
        this.id = id;
        this.NomSurCarte = NomSurCarte;
        this.NumCarte = NumCarte;
        this.MoisExp = MoisExp;
        this.AnneeExp = AnneeExp;
        this.CVV = CVV;
    }

    public Paiement(String NomSurCarte, String NumCarte, String MoisExp, String AnneeExp, String CVV) {
        this.NomSurCarte = NomSurCarte;
        this.NumCarte = NumCarte;
        this.MoisExp = MoisExp;
        this.AnneeExp = AnneeExp;
        this.CVV = CVV;
    }

 

    
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomSurCarte() {
        return NomSurCarte;
    }

    public void setNomSurCarte(String NomSurCarte) {
        this.NomSurCarte = NomSurCarte;
    }

    public String getNumCarte() {
        return NumCarte;
    }

    public void setNumCarte(String NumCarte) {
        this.NumCarte = NumCarte;
    }

    public String getMoisExp() {
        return MoisExp;
    }

    public void setMoisExp(String MoisExp) {
        this.MoisExp = MoisExp;
    }

    public String getAnneeExp() {
        return AnneeExp;
    }

    public void setAnneeExp(String AnneeExp) {
        this.AnneeExp = AnneeExp;
    }

    public String getCVV() {
        return CVV;
    }

    public void setCVV(String CVV) {
        this.CVV = CVV;
    }

    
}
