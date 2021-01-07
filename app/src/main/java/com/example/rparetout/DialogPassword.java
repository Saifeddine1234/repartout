package com.example.rparetout;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;


public class DialogPassword  {
    Activity activity;
    public DialogPassword(Activity activity) {
        this.activity = activity;
    }
    void startActivityDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder( activity);
        LayoutInflater inflater = activity.getLayoutInflater();
        builder.setView(inflater.inflate(R.layout.activity_dialog_password ,null));
        builder.setCancelable(true);
        builder.create().show();
    }
}

