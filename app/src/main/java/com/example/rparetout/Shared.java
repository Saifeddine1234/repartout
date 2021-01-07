package com.example.rparetout;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

public class Shared {
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    Context context;
    int mode = 0;
    String FileName = "sdfile";
    String Data ="b";
    public Shared(Context context){
        this.context = context;
        sharedPreferences = context.getSharedPreferences(FileName,mode);
        editor=sharedPreferences.edit();
    }
    public void secondTime(){
        editor.putBoolean(Data,true);
        editor.commit();
    }
    public void firstTime(){
        if(!this.login()){
            Intent intent=new Intent(context,Login.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            context.startActivity(intent);
        }
    }

    private boolean login() {
        return sharedPreferences.getBoolean(Data,false);
    }


}
