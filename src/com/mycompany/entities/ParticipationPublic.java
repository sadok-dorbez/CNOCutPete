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
public class ParticipationPublic {
    
    
        private int id;
        private String donation ;

        private int user_id;
        
        
    public ParticipationPublic() {
    }
    
    

    public ParticipationPublic(int id, String donation , int user_id) {
        this.id = id;
        this.donation = donation;
        this.user_id = user_id;
    }

    
    public ParticipationPublic(String donation) {
        this.donation = donation;
    }
    
      public ParticipationPublic(String donation, int user_id) {
        this.donation = donation;
         this.user_id = user_id;
    }
    
    
    

    
    
    
    
    
    public int getId() {
        return id;
    }
    
    

    public void setId(int id) {
        this.id = id;
    }

    public String getDonation() {
        return donation;
    }

    public void setDonation(String donation) {
        this.donation = donation;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }
    
    
     
 
}
