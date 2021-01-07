package com.example.rparetout;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import java.util.HashMap;
public class UserSessionManager {
	SharedPreferences pref;
	Editor editor;
	Context _context;
	int PRIVATE_MODE = 0;
	private static final String PREFER_NAME = "AndroidExamplePref";
	private static final String IS_USER_LOGIN = "IsUserLoggedIn";
	public static final String KEY_NAME = "name";
	public static final String KEY_PRENOM = "prenom";
	public static final String KEY_EMAIL = "email";
	public static final String KEY_TELEPHONE = "telephone";
	public static final String KEY_MDP = "mdp";

	public UserSessionManager(Context context){
		this._context = context;
		pref = _context.getSharedPreferences(PREFER_NAME, PRIVATE_MODE);
		editor = pref.edit();
	}
	public void createUserLoginSession(String name,String prenom , String email,String telephone,String mdp){
		editor.putBoolean(IS_USER_LOGIN, true);
		editor.putString(KEY_NAME, name);
		editor.putString(KEY_PRENOM, prenom);
		editor.putString(KEY_EMAIL, email);
		editor.putString(KEY_TELEPHONE, telephone);
		editor.putString(KEY_MDP, mdp);

		editor.commit();
	}
	public boolean checkLogin(){
		if(!this.isUserLoggedIn()){
			
			Intent i = new Intent(_context, MainActivity.class);
			i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			_context.startActivity(i);
			
			return true;
		}
		return false;
	}
	public HashMap<String, String> getUserDetails(){
		HashMap<String, String> user = new HashMap<String, String>();
		user.put(KEY_NAME, pref.getString(KEY_NAME, null));
		user.put(KEY_PRENOM, pref.getString(KEY_PRENOM, null));
		user.put(KEY_EMAIL, pref.getString(KEY_EMAIL, null));
		user.put(KEY_TELEPHONE, pref.getString(KEY_TELEPHONE, null));
		user.put(KEY_MDP, pref.getString(KEY_MDP, null));
		
		// return user
		return user;
	}
	public void logoutUser(){
		editor.clear();
		editor.commit();
		Intent i = new Intent(_context, Login.class);
		i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		_context.startActivity(i);
	}
	public boolean isUserLoggedIn(){
		return pref.getBoolean(IS_USER_LOGIN, false);
	}
}
