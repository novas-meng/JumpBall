package com.example.jumpball;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Set;
import java.util.UUID;

import com.badlogic.gdx.backends.android.AndroidApplication;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Vibrator;
import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothServerSocket;
import android.bluetooth.BluetoothSocket;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.hardware.SensorListener;
import android.hardware.SensorManager;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Menu;

public class crazyMainActivity extends AndroidApplication {

    public static int screenwidth,screenheight;
    crazyGameSwitch gs;
    static SharedPreferences crazy,achievement;
    static float h;
    static float w;
    float width,height;
   static  Vibrator vibrator ;
   static int first=0;
   static int bouns1=0;
	@Override
    public void onCreate(Bundle savedInstanceState) { 
        super.onCreate(savedInstanceState); 
        SharedPreferences setting = getSharedPreferences("crazygame.ini", 0);
        Boolean user_first = setting.getBoolean("FIRST",true);
        if(user_first){
        	first=1;
        	setting.edit().putBoolean("FIRST", false).commit();
        }
        else
        {
        	first=0;
        }
        crazy = getSharedPreferences("crazy.ini",0); 
        achievement = getSharedPreferences("achievement.ini",0); 
        vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);  
        DisplayMetrics metrics=new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        String s1= achievement.getString("five","");
        if(s1.equals("1"))
        {
     	   bouns1=1;
        }
        screenwidth=metrics.widthPixels;
        screenheight=metrics.heightPixels; 
        width=screenwidth;
        height=screenheight;
        w=width/1280;
       h=height/720;
       MainActivity.h=h;
       MainActivity.w=w;
       MainActivity.screenheight=screenheight;
       MainActivity.screenwidth=screenwidth;
       System.out.println("w="+w+"h="+h+"screenWidth"+screenwidth);
        gs=new crazyGameSwitch();
      
        crazyFirstGame fg=new crazyFirstGame(this);
       // initialize(new FirstGame(), false); 
        initialize(gs,false);
    } 
  
    public void onBackPressed() {
    	// TODO Auto-generated method stub
    	//super.onBackPressed();
    	this.finish();
    	
    	System.out.println("MainActivity按下了返回键，退出") ;
    	Intent intent=new Intent(crazyMainActivity.this,ModeSelected.class);
        startActivity(intent);
    	System.exit(0);
    	
    	}
	

}
