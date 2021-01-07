package com.example.rparetout;

public class Reclamation {
    private String nomRec , msgRec , telRec ;

    public Reclamation() {
    }

    public Reclamation(String nomRec, String msgRec,String telRec) {
        this.nomRec = nomRec;
        this.msgRec = msgRec;
        this.telRec = telRec;

    }

    public String getTelRec() {
        return telRec;
    }

    public void setTelRec(String telRec) {
        this.telRec = telRec;
    }

    public String getNomRec() {
        return nomRec;
    }

    public void setNomRec(String nomRec) {
        this.nomRec = nomRec;
    }

    public String getMsgRec() {
        return msgRec;
    }

    public void setMsgRec(String msgRec) {
        this.msgRec = msgRec;
    }
}
