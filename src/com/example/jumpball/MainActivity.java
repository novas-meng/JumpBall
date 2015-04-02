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

public class MainActivity extends AndroidApplication implements SensorListener {

	public static final String PROTOCOL_SCHEME_L2CAP = "btl2cap";
	public static final String PROTOCOL_SCHEME_RFCOMM = "btspp";
	public static final String PROTOCOL_SCHEME_BT_OBEX = "btgoep";
	public static final String PROTOCOL_SCHEME_TCP_OBEX = "tcpobex";
    public static int screenwidth,screenheight;
    GameSwitch gs;
    static SharedPreferences crow,achievement;
    static float h;
    static float w;
    float width,height;
   static  Vibrator vibrator ;
  static  SensorManager manager;
  static enum Direcation
  {
	  Up,Down,Flat
  };
  static int bouns1;
  static Direcation direcation;
  long lasttime,current;
    ArrayList<String > addlist=new ArrayList<String>();
   
	@Override
    public void onCreate(Bundle savedInstanceState) { 
        super.onCreate(savedInstanceState); 
        manager=(SensorManager)getSystemService(SENSOR_SERVICE);
        manager.registerListener(this,SensorManager.SENSOR_ACCELEROMETER,SensorManager.SENSOR_DELAY_UI);
        crow = getSharedPreferences("crow.ini", 0); 
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
        
       String s1= achievement.getString("six","");
       if(s1.equals("1"))
       {
    	   bouns1=1;
       }
       System.out.println("w="+w+"h="+h+"screenWidth"+screenwidth);
        gs=new GameSwitch();
      
        FirstGame fg=new FirstGame(this);
       // initialize(new FirstGame(), false); 
        initialize(gs,false);
    } 
    public void onBackPressed() {
    	// TODO Auto-generated method stub
    	//super.onBackPressed();
    	this.finish();
    	
    	System.out.println("MainActivity按下了返回键，退出") ;
    	Intent intent=new Intent(MainActivity.this,ModeSelected.class);
        startActivity(intent);
    	System.exit(0);
    	
    	}
	@Override
	public void onAccuracyChanged(int arg0, int arg1) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void onSensorChanged(int arg0, float[] arg1) {
		// TODO Auto-generated method stub
		current=System.currentTimeMillis();
	     if(current-lasttime<100)
	     {
	    	 return ;
	     }
	    lasttime=current;
	   // System.out.println("arg[0]="+arg1[0]);
	   // System.out.println("arg[1]="+arg1[1]);
	   // System.out.println("arg[2]="+arg1[2]);
	  if(arg1[1]<-5)
	  {
		  direcation=Direcation.Up;
		  arg1[1]=0;
		//System.out.println("手机向下倾斜了");  
	  }
	  else
	  {
		  direcation=Direcation.Flat;
	  }
	}
   

}
