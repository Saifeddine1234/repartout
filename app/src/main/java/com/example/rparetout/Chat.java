package com.example.rparetout;

public class Chat {
    String sender , reciver ,dateC , message ,client , reparateur ,desc , date , type , id , image;

    public Chat() {
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDateC() {
        return dateC;
    }

    public void setDateC(String dateC) {
        this.dateC = dateC;
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public String getReparateur() {
        return reparateur;
    }

    public void setReparateur(String reparateur) {
        this.reparateur = reparateur;
    }

    public String getReciver() {
        return reciver;
    }

    public void setReciver(String reciver) {
        this.reciver = reciver;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getDesc() {
        return desc;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Chat(String sender, String reciver, String message , String desc , String date , String dateC) {
        this.sender = sender;
        this.reciver = reciver;
        this.message = message;
        this.desc = desc ;
        this.dateC = dateC;
        this.date = date;

    }

    public Chat(String sender, String reciver,String message,String image,String desc,  String date, String dateC,  String type) {
        this.sender = sender;
        this.reciver = reciver;
        this.dateC = dateC;
        this.message = message;
        this.desc = desc;
        this.date = date;
        this.image = image;
        this.type = type;
    }
}
