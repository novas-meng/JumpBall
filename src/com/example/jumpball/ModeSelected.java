package com.example.jumpball;


import java.io.IOException;

import android.annotation.TargetApi;
import android.app.*;
import android.content.*;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.os.*;
import android.text.format.Time;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.*;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
/*
public class ModeSelected extends Activity {
   
	
	public void  onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.modeselected);
		Gallery g=(Gallery) findViewById(R.id.gallery);
		g.setAdapter(new GalleryAdapter(this));
		g.setBackgroundResource(R.drawable.gallery_back);
		g.setOnItemClickListener(new OnItemClickListener()
		{

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				
			}
			
		});
	
	}
	public void onBackPressed() {
    	// TODO Auto-generated method stub
    	//super.onBackPressed();
    	this.finish();
    	
    	System.out.println("按下了返回键，退出") ;
    	Intent intent=new Intent(ModeSelected.this,choose.class);
        startActivity(intent);
    	System.exit(0);
    	
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
}
*/

/*
public class Modechoice extends Activity {
    static int hardChoose=0;
    TextView cho=null;
    private Intent i=null;
	public void  onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.modechoice);
		cho=(TextView)findViewById(R.id.choice);
		Gallery gallery=(Gallery)findViewById(R.id.gallery);
		Typeface tf=Typeface.createFromAsset(getAssets(),"girl.TTF");
		cho.setTypeface(tf);
		gallery.setCallbackDuringFling(false);
		gallery.setAdapter(new ImageAdapter(this));
		gallery.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {  
			
		    @Override  
		    public void onNothingSelected(AdapterView<?> arg0) {  
		    //这里不做响应 
		    	
		    	
		    	
		    }
			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
			// TODO Auto-generated method stub
			switch(arg2)
			{
			case 0:
				cho.setText("Easy");
				i=new Intent(Modechoice.this,MainActivity.class);
				break;
			case 1:
				cho.setText("Hard");
				hardChoose=1;
				i=new Intent(Modechoice.this,MainActivity.class);
				break;
			case 2:
				cho.setText("Crazy");
				hardChoose=2;
				i=new Intent(Modechoice.this,MainActivity.class);
				break;
			case 3:
				cho.setText("Bluetooth");
				i=new Intent(Modechoice.this,bluetooth.class);
				break;
				default:
			}
			arg1.setOnClickListener(new OnClickListener()
			{

				@Override
				public void onClick(View arg0) {
					// TODO Auto-generated method stub
					startActivity(i);
					
				}	
			});			
			}  
		}); 
	}
	public void onBackPressed() {
    	// TODO Auto-generated method stub
    	//super.onBackPressed();
    	this.finish();
    	
    	System.out.println("按下了返回键，退出") ;
    	Intent intent=new Intent(Modechoice.this,choose.class);
        startActivity(intent);
    	System.exit(0);
    	
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
}
*/

public class ModeSelected extends Activity {
	static int hardChoose=0;
	private TextView tvTitle; 	
	private GalleryView gallery; 
	  MediaPlayer mpMediaPlayer;
	private ImageAdapter adapter;
	 TextView cho=null;
	    private Intent i=null;
	    Typeface tf;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		fullscreen(true);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.modechoice);
		
		
		initRes();
		mpMediaPlayer=MediaPlayer.create(getApplication(), R.raw.bgsound);
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
	public void onBackPressed() {
    	// TODO Auto-generated method stub
    	//super.onBackPressed();
		this.finish();
       Intent intent=new Intent(ModeSelected.this,choose.class);
       startActivity(intent);
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
	private void initRes(){
		cho = (TextView) findViewById(R.id.modetext);
		gallery = (GalleryView) findViewById(R.id.mygallery);
		 tf=Typeface.createFromAsset(getAssets(),"girl.TTF");
		cho.setTypeface(tf);
		adapter = new ImageAdapter(this); 	
		adapter.createReflectedImages();
		gallery.setAdapter(adapter);
	//	gallery.setBackgroundResource(R.drawable.gallery_back);
		gallery.setOnItemSelectedListener(new OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
				cho.setText(adapter.titles[position]);
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
			}
		});

		gallery.setOnItemClickListener(new OnItemClickListener() {			// 设置点击事件监听
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				//Toast.makeText(Modechoice.this, "img " + (position+1) + " selected", Toast.LENGTH_SHORT).show();
				/*
				LayoutInflater inflater = getLayoutInflater();  
				   View layout = inflater.inflate(R.layout.custom,  
				     (ViewGroup) findViewById(R.id.llToast));  
				   ImageView image = (ImageView) layout  
				     .findViewById(R.id.tvImageToast);  
				   image.setImageResource(R.drawable.toast_loading);  
				   TextView title = (TextView) layout.findViewById(R.id.tvTitleToast);  
				   title.setTypeface(tf);
				   title.setText("Loading");  
				   TextView text = (TextView) layout.findViewById(R.id.tvTextToast); 
				   text.setTypeface(tf);
				   text.setText("亲，我们正在努力加载");  
				  Toast toast = new Toast(getApplicationContext());  
				   //toast.setGravity(Gravity.RIGHT | Gravity.TOP, 12, 40); 
				   toast.setDuration(Toast.LENGTH_LONG);  
				   toast.setView(layout);  
				   toast.show();  
				   */
				LayoutInflater inflater = getLayoutInflater();  
				   View layout = inflater.inflate(R.layout.loading,  
				     (ViewGroup) findViewById(R.id.loadingtoast));  
				   ImageView image = (ImageView) layout  
				     .findViewById(R.id.loadingimage);  
				   image.setImageResource(R.drawable.loading);  
				 
				  Toast toast = new Toast(getApplicationContext());  
				   //toast.setGravity(Gravity.RIGHT | Gravity.TOP, 12, 40); 
				   toast.setDuration(Toast.LENGTH_SHORT);  
				   toast.setView(layout);  
				   toast.show();  
				switch(position)
				{
				case 0:
					
				//	mpMediaPlayer.release();
					mpMediaPlayer.stop();
					i=new Intent(ModeSelected.this,crowMainActivity.class);
					startActivity(i);
					break;
				case 1:
				//	mpMediaPlayer.release();
					mpMediaPlayer.stop();
					i=new Intent(ModeSelected.this,hardMainActivity.class);
					startActivity(i);
					break;
				case 2:
				//	mpMediaPlayer.release();
					mpMediaPlayer.stop();
					i=new Intent(ModeSelected.this,crazyMainActivity.class);
					startActivity(i);
					break;
				case 3:
					//mpMediaPlayer.release();
					mpMediaPlayer.stop();
					i=new Intent(ModeSelected.this,MainActivity.class);
					startActivity(i);
					break;
				case 4:
				//	mpMediaPlayer.release();
					mpMediaPlayer.stop();
					i=new Intent(ModeSelected.this,ghostMainActivity.class);
					startActivity(i);
					break;
				case 5:
				//	mpMediaPlayer.release();
					mpMediaPlayer.stop();
					i=new Intent(ModeSelected.this,rollMainActivity.class);
					startActivity(i);
					break;
				case 6:
				//	mpMediaPlayer.release();
					mpMediaPlayer.stop();
					i=new Intent(ModeSelected.this,bluetoothHelp.class);
					startActivity(i);
					break;
				}
			}
		});
	}
}


