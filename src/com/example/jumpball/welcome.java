package com.example.jumpball;

import android.app.*;
import android.content.*;
import android.graphics.Typeface;
import android.os.*;
import android.text.format.Time;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.*;

public class welcome extends Activity implements AnimationListener{
	 static SharedPreferences share;
	 static SharedPreferences easy,hard,crazy,ghost,bluetooth,achievement,crow,roll;
	 private Animation mat1=null,mat2=null;
	 Context mcon=null;
	 static int screenwidth,screenheight;
	 
	 static int first=0;
	public void  onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		fullscreen(true);
		
		setContentView(R.layout.bif);
		 SharedPreferences setting = getSharedPreferences("game.ini", 0);
		 DisplayMetrics metrics=new DisplayMetrics();
	        getWindowManager().getDefaultDisplay().getMetrics(metrics);
	        screenwidth=metrics.widthPixels;
	        screenheight=metrics.heightPixels; 
	        hard = getSharedPreferences("hard.ini",0); 
	        crazy = getSharedPreferences("crazy.ini",0); 
	        crow = getSharedPreferences("crow.ini", 0); 
	        ghost = getSharedPreferences("ghost.ini", 0); 
	        roll = getSharedPreferences("roll.ini", 0); 
	        bluetooth = getSharedPreferences("bluetooth.ini",0); 
	        achievement = getSharedPreferences("achievement.ini",0); 
	        easy = getSharedPreferences("easy.ini", 0); 
        Boolean user_first = setting.getBoolean("FIRST",true);
        if(user_first){
        	first=1;
        	System.out.println("第一次启动");
        setting.edit().putBoolean("FIRST", false).commit();
        share = getSharedPreferences("Setting_himi", MODE_PRIVATE); 
       
        share.edit().putString("count", String.valueOf(0)).commit();
        Time t=new Time();
	    t.setToNow();
	    String s=t.year+"."+t.month+"."+t.monthDay+"."+t.hour+"."+t.minute+"."+t.second;
 	        share.edit()    
                 .putString("day",s)
                 .putString("score",String.valueOf(0)) 
                 .commit(); 
 	       share.edit()    
           .putString("day",s)
           .putString("score",String.valueOf(0)) 
           .commit();
 	      easy.edit()    
          .putString("day",s)
          .putString("score",String.valueOf(0)) 
          .commit();  
 	     hard.edit()    
         .putString("day",s)
         .putString("score",String.valueOf(0)) 
         .commit();  
 	    crazy.edit()    
        .putString("day",s)
        .putString("score",String.valueOf(0)) 
        .commit();  
 	   bluetooth.edit()    
       .putString("day",s)
       .putString("score",String.valueOf(0)) 
       .commit();  
 	  crow.edit()    
      .putString("day",s)
      .putString("score",String.valueOf(0)) 
      .commit();  
 	 ghost.edit()    
     .putString("day",s)
     .putString("score",String.valueOf(0)) 
     .commit();  
 	roll.edit()    
    .putString("day",s)
    .putString("score",String.valueOf(0)) 
    .commit();  
 	 // share.edit().putString("one","0").commit();
 	  achievement.edit().putString("one",String.valueOf(0)).commit();
 	 achievement.edit().putString("two",String.valueOf(0)).commit();
 	 achievement.edit().putString("three",String.valueOf(0)).commit();
 	 achievement.edit().putString("four",String.valueOf(0)).commit();
 	 achievement.edit().putString("five",String.valueOf(0)).commit();
 	 achievement.edit().putString("six",String.valueOf(0)).commit();
 	 achievement.edit().putString("seven",String.valueOf(0)).commit();
 	 achievement.edit().putString("eight",String.valueOf(0)).commit();
 	 achievement.edit().putString("nine",String.valueOf(0)).commit();
 	 achievement.edit().putString("ten",String.valueOf(0)).commit();
        }
        Typeface tf=Typeface.createFromAsset(getAssets(),"girl.TTF");
		//左边字符串动画
		TextView iv1=(TextView)findViewById(R.id.logo1);
		iv1.setTypeface(tf);
		mat1=AnimationUtils.loadAnimation(getApplicationContext(),R.anim.literal_in_left);
		iv1.startAnimation(mat1);
        //右边字符串动画
		TextView iv2=(TextView)findViewById(R.id.logo2);
		iv2.setTypeface(tf);
		mat2=AnimationUtils.loadAnimation(getApplicationContext(),R.anim.literal_in_right);
		iv2.startAnimation(mat2);
		mat2.setAnimationListener(this);
	}
	//全屏，去掉导航栏和状态栏
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
	@Override
	public void onAnimationEnd(Animation arg0) {
		// TODO Auto-generated method stub
		Intent i=new Intent(welcome.this,choose.class);
	    startActivity(i);
	    welcome.this.finish();
	
	}
	@Override
	public void onAnimationRepeat(Animation arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void onAnimationStart(Animation arg0) {
		// TODO Auto-generated method stub
		
	}
}
