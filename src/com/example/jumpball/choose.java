package com.example.jumpball;

import java.io.IOException;

import android.annotation.SuppressLint;
import android.app.*;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.*;
import android.media.MediaPlayer;
import android.os.*;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.View.OnTouchListener;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.*;

public class choose extends Activity{

	  MediaPlayer mpMediaPlayer;
     private ImageView mode=null;
	 private ImageView score=null;
	 private ImageView help=null;
     private ImageView exit=null;
	 public void  onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		fullscreen(true);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		DisplayMetrics metric=new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(metric);
		if(metric.widthPixels>1400)
			setContentView(R.layout.choosea);
		else if(metric.widthPixels>1200)
		   setContentView(R.layout.choose);
		else if(metric.widthPixels>900)
			 setContentView(R.layout.choose1);
		else setContentView(R.layout.choose2);
	   // mpMediaPlayer = new MediaPlayer();
		mpMediaPlayer=MediaPlayer.create(getApplication(), R.raw.bgsound);
		/*
	    try {
			mpMediaPlayer.setDataSource("bgsound.wav");
		} catch (IllegalArgumentException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (SecurityException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IllegalStateException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		*/
	    /*
        AssetManager am = getAssets();
      
            try {
				mpMediaPlayer.setDataSource(am.openFd("bgsound.wav").getFileDescriptor());
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalStateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			*/
            try {
				mpMediaPlayer.prepare();
			} catch (IllegalStateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            mpMediaPlayer.start();
          //  mpMediaPlayer.setVolume(TRIM_MEMORY_UI_HIDDEN, TRIM_MEMORY_BACKGROUND);
           System.out.println("播放背景音乐");
        //DisplayMetrics metric=new DisplayMetrics();
        //getWindowManager().getDefaultDisplay().getMetrics(metric);
        int width=metric.widthPixels;
        int height=metric.heightPixels;
        int dendpi=metric.densityDpi;
        float density=metric.density;
        //px = dip * density / 160
        
        System.out.println("main screen "+height+"  "+width);
      //  Log.e("dpi",""+dendpi+" "+density);
    	mode=(ImageView) findViewById(R.id.mode);
		score=(ImageView) findViewById(R.id.score);
		help=(ImageView) findViewById(R.id.help);
	    exit=(ImageView) findViewById(R.id.exit);
	    mode.post(new Runnable(){   
	    	  
            @Override  
            public void run() {  
                // TODO Auto-generated method stub  
                  
                //ImageView的宽和高  
                Log.d("lxy", "iv_W = " + mode.getWidth() + ", iv_H = " + mode.getHeight());  
  
                //获得ImageView中Image的真实宽高，  
                int dw = mode.getDrawable().getBounds().width();  
                int dh = mode.getDrawable().getBounds().height();  
                Log.d("lxy", "drawable_X = " + dw + ", drawable_Y = " + dh);  
                  
                //获得ImageView中Image的变换矩阵  
                Matrix m = mode.getImageMatrix();  
                float[] values = new float[10];  
                m.getValues(values);  
                  
                //Image在绘制过程中的变换矩阵，从中获得x和y方向的缩放系数  
                float sx = values[0];  
                float sy = values[4];  
                Log.d("lxy", "scale_X = " + sx + ", scale_Y = " + sy);  
                  
                //计算Image在屏幕上实际绘制的宽高  
                int cw = (int)(dw * sx);  
                int ch = (int)(dh * sy);   
                Log.d("lxy", "caculate_W = " + cw + ", caculate_H = " + ch);  
            }});
	 //  System.out.println("button  "+mode.getHeight()+"  "+mode.getWidth());
	    //菜单的监听事件
		((View) mode).setOnTouchListener(new OnTouchListener()
		{
			@SuppressLint("NewApi") public boolean onTouch(View arg0, MotionEvent arg1) {
				// TODO Auto-generated method stub
				switch(arg1.getAction())
				{
				case MotionEvent.ACTION_UP:
					mpMediaPlayer.stop();
					//choose.this.finish();
					Intent intent=new Intent();
					intent.setClass(choose.this,ModeSelected.class);
					startActivity(intent);
					overridePendingTransition(R.anim.fade, R.anim.hold);  
					break;
				case MotionEvent.ACTION_DOWN:
					break;
					
				}
				return false;
			}

		});
		((View) score).setOnTouchListener(new OnTouchListener( )
		{
			public boolean onTouch(View arg0, MotionEvent arg1) {
				switch(arg1.getAction())
				{
				case MotionEvent.ACTION_UP:
					mpMediaPlayer.stop();
					Intent intent=new Intent();
					//choose.this.finish();
					intent.setClass(choose.this, rankActivity.class);
					startActivity(intent);
					
					overridePendingTransition(R.anim.fade, R.anim.hold);  
					break;
				case MotionEvent.ACTION_DOWN:
					break;
					
				}
				return false;
			}
		});
		((View) help).setOnTouchListener(new OnTouchListener( )
		{
			@SuppressLint("NewApi") public boolean onTouch(View arg0, MotionEvent arg1) {
				switch(arg1.getAction())
				{
				case MotionEvent.ACTION_UP:
					mpMediaPlayer.stop();
					Intent intent=new Intent();
				//	choose.this.finish();
					intent.setClass(choose.this, Achievement.class);
					startActivity(intent);
					overridePendingTransition(R.anim.fade, R.anim.hold);  
                    break;
				case MotionEvent.ACTION_DOWN:
					break;
				}
				return false;
			}
		});
		((View) exit).setOnTouchListener(new OnTouchListener( )
		{
			@SuppressLint("NewApi") public boolean onTouch(View arg0, MotionEvent arg1) {
				switch(arg1.getAction())
				{
				case MotionEvent.ACTION_UP:
					mpMediaPlayer.stop();
					System.exit(0);
					break;
				case MotionEvent.ACTION_DOWN:
					break;
					
				}
				return false;
			}
		});
	}
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
	@Override
	protected void onPause()
	{
		super.onPause();
				//mpMediaPlayer.release();
	//	mpMediaPlayer.stop();
	}
	protected void onResume()
	{
		super.onResume();
		//清除动画
	}
	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		//mpMediaPlayer.stop();
	}
	public void onBackPressed() {
    	// TODO Auto-generated method stub
    	//super.onBackPressed();
         this.finish();
    	System.exit(0);
    	
    	}
	
    protected void onRestart()
    {
    	super.onRestart();
    	//清除动画
    	System.out.println("onRestatr");
    	try {
			mpMediaPlayer.prepare();
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	mpMediaPlayer.start();
    }

}
