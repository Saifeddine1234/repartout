package com.example.rparetout;

public class Reparateur {
    String nomrep,prenomrep,cinrep,typerep,telephonerep,mdprep , imagerep;

    public Reparateur(String nomrep, String prenomrep, String cinrep, String typerep, String telephonerep, String mdprep) {
        this.nomrep = nomrep;
        this.prenomrep = prenomrep;
        this.cinrep = cinrep;
        this.typerep = typerep;
        this.telephonerep = telephonerep;
        this.mdprep = mdprep;
    }
    public Reparateur(String nomrep, String prenomrep, String cinrep, String typerep, String telephonerep, String mdprep,String imagerep) {
        this.nomrep = nomrep;
        this.prenomrep = prenomrep;
        this.cinrep = cinrep;
        this.typerep = typerep;
        this.telephonerep = telephonerep;
        this.mdprep = mdprep;
        this.imagerep = imagerep;
    }

    public Reparateur() {
    }

    public String getNomrep() {
        return nomrep;
    }

    public void setNomrep(String nomrep) {
        this.nomrep = nomrep;
    }

    public String getPrenomrep() {
        return prenomrep;
    }

    public void setPrenomrep(String prenomrep) {
        this.prenomrep = prenomrep;
    }

    public String getCinrep() {
        return cinrep;
    }

    public void setCinrep(String cinrep) {
        this.cinrep = cinrep;
    }

    public String getTyperep() {
        return typerep;
    }

    public void setTyperep(String typerep) {
        this.typerep = typerep;
    }

    public String getTelephonerep() {
        return telephonerep;
    }

    public void setTelephonerep(String telephonerep) {
        this.telephonerep = telephonerep;
    }

    public String getMdprep() {
        return mdprep;
    }

    public void setMdprep(String mdprep) {
        this.mdprep = mdprep;
    }

    public String getImagerep() {
        return imagerep;
    }

    public void setImagerep(String imagerep) {
        this.imagerep = imagerep;
    }
}
