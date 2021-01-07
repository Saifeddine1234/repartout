package com.example.rparetout;

public class ContactM {
    String telephoneadmin , emailadmin ;

    public ContactM(String telephoneadmin, String emailadmin) {
        this.telephoneadmin = telephoneadmin;
        this.emailadmin = emailadmin;
    }

    public String getTelephoneadmin() {
        return telephoneadmin;
    }

    public void setTelephoneadmin(String telephoneadmin) {
        this.telephoneadmin = telephoneadmin;
    }

    public String getEmailadmin() {
        return emailadmin;
    }

    public void setEmailadmin(String emailadmin) {
        this.emailadmin = emailadmin;
    }
}
