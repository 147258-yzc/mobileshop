package com.yzc.myfragment.activity;

import android.content.Context;
import android.content.SharedPreferences;

import com.yzc.myfragment.MyApp;

public class SpTools {
    private final static String SPNAME="my_sp";

    public static void setString( String key, String value){
        SharedPreferences my_sp =getSharedPreferences();
        SharedPreferences.Editor edit = my_sp.edit();
        edit.putString(key,value);
        edit.apply();
    }
    public static String getString(String key,String defValue){
        SharedPreferences my_sp = getSharedPreferences();
        return my_sp.getString(key,defValue);
    }

    public static void setLong(String key,long defValue){
        SharedPreferences.Editor edit = getEdit();
        edit.putLong(key,defValue);
        edit.apply();
    }
    public static long getLong(String key,long defValue){
        SharedPreferences sharedPreferences = getSharedPreferences();
        return sharedPreferences.getLong(key,defValue);
    }
    public static void setBoolean(String key,boolean defValue){
        SharedPreferences.Editor edit = getEdit();
        edit.putBoolean(key,defValue);
        edit.apply();
    }
    public static boolean getBoolean(String key,boolean defValue){
        SharedPreferences sharedPreferences =getSharedPreferences();
        return sharedPreferences.getBoolean(key,defValue);
    }

    public static void setInt(String key,int defValue){
        SharedPreferences.Editor edit =getEdit();
        edit.putInt(key,defValue);
        edit.apply();
    }

    public static int getInt(String key,int defValue){
        SharedPreferences sharedPreferences =getSharedPreferences();
        return sharedPreferences.getInt(key,defValue);
    }

    public static void setFloat(String key,float defValue){
        SharedPreferences.Editor edit =getEdit();
        edit.putFloat(key,defValue);
        edit.apply();
    }

    public static float getFloat(String key,float defValue){
        SharedPreferences sharedPreferences = getSharedPreferences();
        return sharedPreferences.getFloat(key,defValue);
    }
    public static SharedPreferences getSharedPreferences(){
        SharedPreferences my_sp =MyApp.getContext().getSharedPreferences(SPNAME, Context.MODE_PRIVATE);
        return  my_sp;
    }



    public static SharedPreferences.Editor getEdit(){
        SharedPreferences my_sp = getSharedPreferences();
        SharedPreferences.Editor edit = my_sp.edit();
        return edit;
    }


}
