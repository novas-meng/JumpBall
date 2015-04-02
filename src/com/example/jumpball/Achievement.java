package com.example.jumpball;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

public class Achievement extends Activity
{
	
	ImageView imageview1;
	ImageView imageview2;
	ImageView imageview3;
	ImageView imageview4;
	ImageView imageview5;
	ImageView imageview6;
	ImageView imageview7;
	ImageView imageview8;
	ImageView imageview9;
	ImageView imageview10;
	Typeface typeface;
	static SharedPreferences achievement;
	public void  onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		fullscreen(true);
		if(welcome.screenwidth  <= 900)
			setContentView(R.layout.achievement800);
		else if(welcome.screenwidth <= 1000)
			setContentView(R.layout.achievement960);
		else if(welcome.screenwidth<=1400)
			setContentView(R.layout.achievement720);
		else
			setContentView(R.layout.achievement1080);
		typeface=Typeface.createFromAsset(getAssets(), "girl.TTF");
		initTextView();
		 achievement = getSharedPreferences("achievement.ini", MODE_PRIVATE); 
		initBouns();
		/*
		ImageView image=(ImageView)findViewById(R.id.ach_back);
		image.setOnClickListener(new OnClickListener()
		{

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Achievement.this.finish();
				Intent intent=new Intent(Achievement.this,choose.class);
				startActivity(intent);
			}
			
		});
			*/	
	}
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
	public void initBouns()
	{
		 String s1=achievement.getString("one","");
		 System.out.println("在成就中s="+s1);
		 if(s1.equals("1"))
		 {
			 System.out.println("dfsadfadsfadsf");
			 imageview1.setImageDrawable(getResources().getDrawable(R.drawable.ach_1));
		 }
		 String s2=achievement.getString("two","");
		 System.out.println("在成就中s="+s2);
		 if(s2.equals("1"))
		 {
			 System.out.println("dfsadfadsfadsf");
			 imageview2.setImageDrawable(getResources().getDrawable(R.drawable.ach_2));
		 }
		 String s3=achievement.getString("three","");
		 System.out.println("在成就中s="+s3);
		 if(s3.equals("1"))
		 {
			 System.out.println("dfsadfadsfadsf");
			 imageview3.setImageDrawable(getResources().getDrawable(R.drawable.ach_3));
		 }
		 String s4=achievement.getString("four","");
		 System.out.println("在成就中s="+s4);
		 if(s4.equals("1"))
		 {
			 System.out.println("dfsadfadsfadsf");
			 imageview4.setImageDrawable(getResources().getDrawable(R.drawable.ach_4));
		 }
		 String s5=achievement.getString("five","");
		 System.out.println("在成就中s="+s5);
		 if(s5.equals("1"))
		 {
			 System.out.println("dfsadfadsfadsf");
			 imageview5.setImageDrawable(getResources().getDrawable(R.drawable.ach_5));
		 }
		 String s6=achievement.getString("six","");
		 System.out.println("在成就中s="+s6);
		 if(s6.equals("1"))
		 {
			 System.out.println("dfsadfadsfadsf");
			 imageview6.setImageDrawable(getResources().getDrawable(R.drawable.ach_6));
		 }
		 String s7=achievement.getString("seven","");
		 System.out.println("在成就中s="+s7);
		 if(s7.equals("1"))
		 {
			 System.out.println("dfsadfadsfadsf");
			 imageview7.setImageDrawable(getResources().getDrawable(R.drawable.ach_7));
		 }
		 String s8=achievement.getString("eight","");
		 System.out.println("在成就中s="+s8);
		 if(s8.equals("1"))
		 {
			 System.out.println("dfsadfadsfadsf");
			 imageview8.setImageDrawable(getResources().getDrawable(R.drawable.ach_8));
		 }
		 String s9=achievement.getString("nine","");
		 System.out.println("在成就中s="+s9);
		 if(s9.equals("1"))
		 {
			 System.out.println("dfsadfadsfadsf");
			 imageview9.setImageDrawable(getResources().getDrawable(R.drawable.ach_9));
		 }
		 String s10=achievement.getString("nine","");
		 System.out.println("在成就中s="+s10);
		 if(s10.equals("1"))
		 {
			 System.out.println("dfsadfadsfadsf");
			 imageview10.setImageDrawable(getResources().getDrawable(R.drawable.ach10));
		 }
	}
	public void initTextView()
	{
	
		imageview1=(ImageView)findViewById(R.id.ach1);
		imageview2=(ImageView)findViewById(R.id.ach2);
		imageview3=(ImageView)findViewById(R.id.ach3);
		imageview4=(ImageView)findViewById(R.id.ach4);
		imageview5=(ImageView)findViewById(R.id.ach5);
		imageview6=(ImageView)findViewById(R.id.ach6);
		imageview7=(ImageView)findViewById(R.id.ach7);
		imageview8=(ImageView)findViewById(R.id.ach8);
		imageview9=(ImageView)findViewById(R.id.ach9);
		imageview10=(ImageView)findViewById(R.id.ach10);
	}
}
