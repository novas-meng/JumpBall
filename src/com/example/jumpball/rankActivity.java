package com.example.jumpball;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;

import com.tencent.mm.sdk.modelmsg.SendMessageToWX;
import com.tencent.mm.sdk.modelmsg.WXMediaMessage;
import com.tencent.mm.sdk.modelmsg.WXTextObject;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.WXAPIFactory;


public class rankActivity extends Activity {
	 static SharedPreferences easy,hard,crazy,bluetooth,crow,ghost,roll;
	 private static final String APP_ID ="wx02ac3d9203508916";
	 private IWXAPI api;
	 private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd.hh.mm.ss");

	//设置全屏，去掉状态栏
	 private void fullscreen(boolean enable) {
	        if (enable) {
	            WindowManager.LayoutParams lp = getWindow().getAttributes();
	            lp.flags |= WindowManager.LayoutParams.FLAG_FULLSCREEN;
	            getWindow().setAttributes(lp);
	            getWindow().addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
	        } else {
	            WindowManager.LayoutParams attr = getWindow().getAttributes();
	            attr.flags &= (~WindowManager.LayoutParams.FLAG_FULLSCREEN);
	            getWindow().setAttributes(attr);
	            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
	        }
	 }
	 
	@SuppressLint("CommitPrefEdits")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		fullscreen(true);
	       // 去除标题栏 
	    requestWindowFeature(Window.FEATURE_NO_TITLE);
	    
	    easy = getSharedPreferences("easy.ini", MODE_PRIVATE);
	    hard = getSharedPreferences("hard.ini", MODE_PRIVATE);
	    crazy = getSharedPreferences("crazy.ini", MODE_PRIVATE);
	    bluetooth = getSharedPreferences("bluetooth.ini", MODE_PRIVATE);
	    crow = getSharedPreferences("crow.ini", MODE_PRIVATE);
	    ghost = getSharedPreferences("ghost.ini", MODE_PRIVATE);
	    roll = getSharedPreferences("roll.ini", MODE_PRIVATE);
	    
	    DisplayMetrics metric=new DisplayMetrics();
	    getWindowManager().getDefaultDisplay().getMetrics(metric);	
	    if(metric.widthPixels>1400)
	    	setContentView(R.layout.rank1920x1080);	
	    else if(metric.widthPixels>1000)
	    	 setContentView(R.layout.rank1280x720);
	    else if(metric.widthPixels>900)
	    	 setContentView(R.layout.rank960x540);
	    else setContentView(R.layout.rank800x480);
	    
	    easy.edit().putString("score", String.valueOf(1))
	         .putString("day", "2014.7.1.12.09.60").commit();
	    hard.edit().putString("score", String.valueOf(17))
	         .putString("day", "2014.7.1.12.09.51").commit();
	    crazy.edit().putString("score", String.valueOf(2))
	         .putString("day", "2014.7.1.12.09.23").commit();
	    bluetooth.edit().putString("score",String.valueOf(3))
	         .putString("day", "2014.7.1.12.09.56").commit();
	    crow.edit().putString("score",String.valueOf(22))
	         .putString("day", "2014.8.1.12.09.41").commit();
	    ghost.edit().putString("score",String.valueOf(31))
	         .putString("day", "2014.8.1.12.09.42").commit();
	    roll.edit().putString("score", String.valueOf(7))
	         .putString("day", "2014.8.1.12.09.43").commit();
	 
	    final Button easybtn = (Button) findViewById(R.id.easy);
	    final Button hardbtn = (Button) findViewById(R.id.hard);
	    final Button crazybtn = (Button) findViewById(R.id.crazy);
	    final Button bluetoothbtn = (Button) findViewById(R.id.bluetooth);
	    final Button birdbtn = (Button) findViewById(R.id.bird);
	    final Button ghostbtn = (Button) findViewById(R.id.ghost);
	    final Button turnplatebtn = (Button) findViewById(R.id.turnplate);
	    
	    Typeface typeFace = Typeface.createFromAsset(getAssets(),"girl.TTF");

	    easybtn.setTypeface(typeFace);
	    hardbtn.setTypeface(typeFace);
	    crazybtn.setTypeface(typeFace);
	    bluetoothbtn.setTypeface(typeFace);
	    birdbtn.setTypeface(typeFace);
	    ghostbtn.setTypeface(typeFace);
	    turnplatebtn.setTypeface(typeFace);
	    
	    String curdate = sdf.format(new Date());
	    
	    easybtn.setText(String.format("%3s",easy.getString("score", ""))+"          "+ 
	    		String.format("%-3s",getDifferDays(easy.getString("day", ""),curdate))+"天之内"); 
	    hardbtn.setText(String.format("%3s",hard.getString("score", ""))+"          "+
	    		String.format("%-3s",getDifferDays(hard.getString("day", ""),curdate))+"天之内"); 
	    crazybtn.setText(String.format("%3s",crazy.getString("score", ""))+"          "+
	    		String.format("%-3s",getDifferDays(crazy.getString("day", ""),curdate))+"天之内"); 
	    bluetoothbtn.setText(String.format("%3s",bluetooth.getString("score", ""))+"          "+
	    		String.format("%-3s",getDifferDays(bluetooth.getString("day", ""),curdate))+"天之内");   
	    birdbtn.setText(String.format("%3s",crow.getString("score", ""))+"          "+
	    		String.format("%-3s",getDifferDays(crow.getString("day", ""),curdate))+"天之内");
	    ghostbtn.setText(String.format("%3s",ghost.getString("score", ""))+"          "+
	    		String.format("%-3s",getDifferDays(ghost.getString("day", ""),curdate))+"天之内");
	    turnplatebtn.setText(String.format("%3s",roll.getString("score", ""))+"          "+
	    		String.format("%-3s",getDifferDays(roll.getString("day", ""),curdate))+"天之内");
	    
	    
	    api = WXAPIFactory.createWXAPI(this, APP_ID, true);
        api.registerApp(APP_ID);
        final Button back=(Button)findViewById(R.id.back);
	    back.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				rankActivity.this.finish();
	        	Intent i=new Intent(rankActivity.this,choose.class);
	    	    startActivity(i); 
			}
        });
	    final Button share=(Button)findViewById(R.id.share);
	    share.setOnClickListener(new View.OnClickListener() {
	    	@Override
			public void onClick(View v) {
	    		System.out.println("432545");
                // 初始化一个WXTextObject对象  
                String text = "【最高分】"
                		      + "简单："+easy.getString("score", "")
                		      + "困难："+hard.getString("score", "")
                		      + "分裂："+crazy.getString("score", "")
                		      + "乌鸦："+crow.getString("score", "")
                		      + "幽灵："+ghost.getString("score", "")
                		      + "转盘："+roll.getString("score", "");  
                WXTextObject textObj = new WXTextObject();  
                textObj.text = text;  
  
                WXMediaMessage msg = new WXMediaMessage(textObj);  
                msg.mediaObject = textObj;  
                msg.description = text;  
                  
                SendMessageToWX.Req req = new SendMessageToWX.Req();  
                req.transaction = String.valueOf(System.currentTimeMillis());  
                req.message = msg;  
                  
                api.sendReq(req);  
			}
		});       
	}
	
    public int getDifferDays(String strBegin, String strEnd)  {
        int num;
        Date begin = null;
        try {
            begin = sdf.parse(strBegin);
        } catch (java.text.ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        Date end = null; 
        try {
            end = sdf.parse(strEnd);
        } catch (java.text.ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        num = (int) ((end.getTime() - begin.getTime()) / (24 * 60 * 60 * 1000));
        num = num-30;
        num++;
        return num;
    }
}
