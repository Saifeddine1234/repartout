package com.example.rparetout;

public class Types {
  String  idType,nomType;

    public Types(String nomType) {
        this.nomType = nomType;
    }
    public Types() {
    }

    public String getIdType() {
        return idType;
    }

    public void setIdType(String idType) {
        this.idType = idType;
    }

    public String getNomType() {
        return nomType;
    }

    public void setNomType(String nomType) {
        this.nomType = nomType;
    }

    public Types(String idType, String nomType) {
        this.idType = idType;
        this.nomType = nomType;
    }
}
