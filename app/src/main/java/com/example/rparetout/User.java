package com.example.rparetout;

public class User {
    String idU, codeuser , mdpuser;

    public User(String codeuser, String mdpuser) {
        this.codeuser = codeuser;
        this.mdpuser = mdpuser;
    }

    public String getIdU() {
        return idU;
    }

    public void setIdU(String idU) {
        this.idU = idU;
    }

    public String getCodeU() {
        return codeuser;
    }

    public void setCodeU(String codeU) {
        this.codeuser = codeU;
    }

    public String getMdpU() {
        return mdpuser;
    }

    public void setMdpU(String mdpU) {
        this.mdpuser = mdpU;
    }
}
