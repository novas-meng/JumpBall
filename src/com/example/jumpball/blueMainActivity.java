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

public class blueMainActivity extends AndroidApplication {

	public static final String PROTOCOL_SCHEME_L2CAP = "btl2cap";
	public static final String PROTOCOL_SCHEME_RFCOMM = "btspp";
	public static final String PROTOCOL_SCHEME_BT_OBEX = "btgoep";
	public static final String PROTOCOL_SCHEME_TCP_OBEX = "tcpobex";
    public static int screenwidth,screenheight;
    blueGameSwitch gs;
    static SharedPreferences share;
    static SharedPreferences bluetootha,achievement;
    static float h;
    static float w;
    float width,height;
    ArrayList<String > addlist=new ArrayList<String>();
 	static  BluetoothAdapter mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
    BluetoothAdapter btadapter=BluetoothAdapter.getDefaultAdapter();
    @Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		 if (!btadapter.isEnabled()) {
	            Intent enableIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
	            startActivityForResult(enableIntent, 3);
	        }
	}
    public synchronized void onResume() {
        super.onResume();
      
    }

	@Override
    public void onCreate(Bundle savedInstanceState) { 
        super.onCreate(savedInstanceState); 
        share = getSharedPreferences("Setting_himi", MODE_PRIVATE);   
        bluetootha = getSharedPreferences("bluetooth.ini",0); 
        achievement = getSharedPreferences("achievement.ini",0); 
        DisplayMetrics metrics=new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        screenwidth=metrics.widthPixels;
        screenheight=metrics.heightPixels; 
        width=screenwidth;
        height=screenheight;
        w=width/1280;
       h=height/720;
       System.out.println("w="+w+"h="+h+"screenWidth"+screenwidth);
       MainActivity.screenwidth=screenwidth;
       MainActivity.screenheight=screenheight;
       MainActivity.h=h;
       MainActivity.w=w;
       System.out.println("w"+" "+w);
       System.out.println("向另外一个机器传送分辨率");
       bluetooth.trans("w"+" "+w+" "+"h"+" "+h);
       
      // bluetooth.trans("h"+" "+h);
        gs=new blueGameSwitch();
      
        blueFirstGame fg=new blueFirstGame(this);
       // initialize(new FirstGame(), false); 
        initialize(gs,false);
    } 
    public void onBackPressed() {
    	// TODO Auto-generated method stub
    	//super.onBackPressed();
    	System.exit(0);
    	this.finish();
    	gs.dispose();
    	}
}
