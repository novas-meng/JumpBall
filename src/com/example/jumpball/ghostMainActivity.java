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
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Menu;

public class ghostMainActivity extends AndroidApplication {

    public static int screenwidth,screenheight;
    ghostGameSwitch gs;
    static SharedPreferences ghost,achievement;
    static float h;
    static float w;
    float width,height;
   static  Vibrator vibrator ;
   static int bouns1;
	@Override
    public void onCreate(Bundle savedInstanceState) { 
        super.onCreate(savedInstanceState); 
        ghost=getSharedPreferences("ghost.ini", 0);
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
       MainActivity.w=w;
       MainActivity.h=h;
       System.out.println("w="+w+"h="+h+"screenWidth"+screenwidth);
       String s1= achievement.getString("seven","");
       if(s1.equals("1"))
       {
    	   bouns1=1;
       }
        gs=new ghostGameSwitch();
      
        ghostFirstGame fg=new ghostFirstGame(this);
       // initialize(new FirstGame(), false); 
        initialize(gs,false);
    } 
    public void onBackPressed() {
    	// TODO Auto-generated method stub
    	//super.onBackPressed();
    	this.finish();
    	
    	System.out.println("MainActivity按下了返回键，退出") ;
    	Intent intent=new Intent(ghostMainActivity.this,ModeSelected.class);
        startActivity(intent);
    	System.exit(0);
    	
    	}
   

}
