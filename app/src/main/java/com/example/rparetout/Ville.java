package com.example.rparetout;

public class Ville {
    String nomville , idV,nomgov;
public Ville(){

}
    public Ville(String nomV, String govV) {
        this.nomville = nomV;
        this.nomgov = govV;
    }

    public String getNomV() {
        return nomville;
    }

    public void setNomV(String nomV) {
        this.nomville = nomV;
    }

    public String getIdV() {
        return idV;
    }

    public void setIdV(String idV) {
        this.idV = idV;
    }

    public String getGovV() {
        return nomgov;
    }

    public void setGovV(String govV) {
        nomgov = govV;
    }
}
