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

public class crowMainActivity extends AndroidApplication {

    public static int screenwidth,screenheight;
    crowGameSwitch gs;
    static SharedPreferences share;
    static SharedPreferences easy,hard,crazy,music,bluetooth,achievement;
    static float h;
    static float w;
    float width,height;
   static  Vibrator vibrator ;
   static int bouns1=0,bouns2=0;
	@Override
    public void onCreate(Bundle savedInstanceState) { 
        super.onCreate(savedInstanceState); 
      
        easy=getSharedPreferences("easy.ini", 0);
        achievement = getSharedPreferences("achievement.ini",0);
       String s1= achievement.getString("one","");
       if(s1.equals("1"))
       {
    	   bouns1=1;
       }
       String s2=achievement.getString("two", "");
       if(s2.equals("1"))
       {
    	   bouns2=1;
       }
        vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);  
        DisplayMetrics metrics=new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        screenwidth=metrics.widthPixels;
        screenheight=metrics.heightPixels; 
        width=screenwidth;
        height=screenheight;
        w=width/1280;
       h=height/720;
      // System.out.println("w="+w+"h="+h+"screenWidth"+screenwidth);
        gs=new crowGameSwitch();
      
        crowFirstGame fg=new crowFirstGame(this);
       // initialize(new FirstGame(), false); 
        initialize(gs,false);
    } 
  
  
    public void onBackPressed() {
    	// TODO Auto-generated method stub
    	//super.onBackPressed();
    	this.finish();
    	
    	//System.out.println("MainActivity�����˷��ؼ����˳�") ;
    	Intent intent=new Intent(crowMainActivity.this,ModeSelected.class);
        startActivity(intent);
    	System.exit(0);
    	
    	}
   

}