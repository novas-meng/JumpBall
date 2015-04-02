package com.example.jumpball;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Set;
import java.util.UUID;

import com.badlogic.gdx.ApplicationListener;
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
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Menu;

public class rollMainActivity extends AndroidApplication {

    public static int screenwidth,screenheight;
    rollGameSwitch gs;
    static SharedPreferences roll,achievement;
    static float h;
    static float w;
    float width,height;
    Vibrator vibrator;
    ArrayList<String > addlist=new ArrayList<String>();
   static int first=0;
	@Override
    public void onCreate(Bundle savedInstanceState) { 
        super.onCreate(savedInstanceState); 
        roll=getSharedPreferences("hard.ini", 0); 
        achievement = getSharedPreferences("achievement.ini",0); 
        vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);  
        DisplayMetrics metrics=new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        screenwidth=metrics.widthPixels;
        screenheight=metrics.heightPixels; 
        width=screenwidth;
        height=screenheight;
        w=width/1280;
       h=height/720;
       SharedPreferences setting = getSharedPreferences("rollgame.ini", 0);
       Boolean user_first = setting.getBoolean("FIRST",true);
       if(user_first){
       	first=1;
       	setting.edit().putBoolean("FIRST", false).commit();
       }
       else
       {
       	first=0;
       }
       System.out.println("w="+w+"h="+h+"screenWidth"+screenwidth);
       gs=new rollGameSwitch();
        //FirstGameForRoll fg=new FirstGameForRoll(this);
       // initialize(new FirstGame(), false); 
        initialize(gs,false);
    } 
   
    public void onBackPressed() {
    	// TODO Auto-generated method stub
    	//super.onBackPressed();
    	this.finish();
    	
    	System.out.println("MainActivity按下了返回键，退出") ;
    	Intent intent=new Intent(rollMainActivity.this,ModeSelected.class);
        startActivity(intent);
    	System.exit(0);
    	
    	}
   

}
