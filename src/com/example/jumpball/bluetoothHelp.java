package com.example.jumpball;  
  
import java.util.ArrayList;  
import java.util.List;  
import android.os.Bundle;  
import android.os.Parcelable;  
import android.support.v4.view.PagerAdapter;  
import android.support.v4.view.ViewPager;  
import android.support.v4.view.ViewPager.OnPageChangeListener;  
import android.util.DisplayMetrics;  
import android.util.Log;  
import android.view.LayoutInflater;  
import android.view.View;  
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;  
import android.view.animation.Animation;  
import android.view.animation.TranslateAnimation;  
import android.widget.Button;  
import android.widget.ImageView;  
import android.widget.RelativeLayout;  
import android.widget.TextView;  
import android.app.Activity;  
import android.app.AlertDialog;  
import android.content.DialogInterface;  
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.BitmapFactory;  
import android.graphics.Typeface;
  
public class bluetoothHelp extends Activity {  
    private ViewPager mPager;// 页卡内容  
    private List<View> listViews; // Tab页面列表  
    private ImageView cursor;// 动画图片  
    private int offset = 0;// 动画图片偏移量  
    private int currIndex = 0;// 当前页卡编号  
    private int bmpW;// 动画图片宽度  
    MyPagerAdapter adapter;  
    LayoutInflater mInflater;  
    ImageView ret;
    RelativeLayout rel;  
  
    /** Called when the activity is first created. */  
    @Override  
    public void onCreate(Bundle savedInstanceState) {  
        super.onCreate(savedInstanceState);  
        fullscreen(true);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.bluetoothhelp);  
        SharedPreferences setting = getSharedPreferences("CitGamessshadsw.ini", 0);
        Boolean user_first = setting.getBoolean("blueFIRST",true);
        if(user_first){//第一次
        	System.out.println("第一次玩");
        setting.edit().putBoolean("blueFIRST", false).commit();
        }else{
        	this.finish();
           System.out.println("不是第一次玩");
           Intent intent=new Intent(bluetoothHelp.this,bluetooth.class);
           startActivity(intent);
        }
        Log.i("Viewpage", "--onCreate--");
        initImageView();   
        initPageView();  
        Typeface typeFace = Typeface.createFromAsset(getAssets(),"girl.TTF");
        ret=(ImageView) listViews.get(2).findViewById(R.id.bluetooth_continue);
        ret.setOnClickListener(new OnClickListener() {
 		
 		@Override
 		public void onClick(View arg0) {
 			// TODO 自动生成的方法存根
 			bluetoothHelp.this.finish();
 			Intent i=new Intent(bluetoothHelp.this,bluetooth.class);
 			startActivity(i);
 		}
 	});
    }  
  
    private void initPageView() {  
        mInflater = getLayoutInflater();  
        listViews = new ArrayList<View>();  
        listViews.add(mInflater.inflate(R.layout.bluetooth1, null));  
        listViews.add(mInflater.inflate(R.layout.bluetooth2, null));  
        listViews.add(mInflater.inflate(R.layout.bluetooth3, null));  
        adapter = new MyPagerAdapter(listViews);  
        mPager = (ViewPager) findViewById(R.id.page);  
        mPager.setAdapter(adapter);  
        mPager.setCurrentItem(0);  
        mPager.setOnPageChangeListener(new MyOnPageChangeListener());  
    }  
    
  
    private void initImageView() {  
        //cursor = (ImageView) findViewById(R.id.cursor);  
        rel = (RelativeLayout) findViewById(R.id.layout);  
        bmpW = BitmapFactory.decodeResource(getResources(),R.drawable.down)  
                .getWidth();  
        DisplayMetrics dm = new DisplayMetrics();  
        getWindowManager().getDefaultDisplay().getMetrics(dm);  
        int screenW = dm.widthPixels;  
        offset = (screenW*10 / 33 - bmpW) / 2;  
        // Matrix matrix = new Matrix();  
        // matrix.postTranslate(offset, 0);  
        //cursor.setBackgroundResource(R.drawable.down);  
        // cursor.setScaleType(ScaleType.MATRIX);  
        // cursor.setImageMatrix(matrix);  
        rel.setPadding(offset, 0, 0, 0);  
  
    }  
  
    public class MyOnClickListener implements View.OnClickListener {  
        private int index = 0;  
  
        public MyOnClickListener(int i) {  
            index = i;  
        }  
  
        @Override  
        public void onClick(View v) {  
            // TODO Auto-generated method stub  
            mPager.setCurrentItem(index);  
        }  
    }  
  
    public class MyPagerAdapter extends PagerAdapter implements OnClickListener {  
        public List<View> mListViews;  
        public View v1;  
        public View v2;  
        public View v3;  
  
        public MyPagerAdapter(List<View> mListViews) {  
            this.mListViews = mListViews;  
            getViewClickListener(mListViews);  
        }  
  
        public void getViewClickListener(List<View> listview) {  
            v1 = listview.get(0);  
            v2 = listview.get(1);  
            v3 = listview.get(2);  
        }  
  
        public void destroyItem(View arg0, int arg1, Object arg2) {  
            ((ViewPager) arg0).removeView(mListViews.get(arg1));  
        }  
  
        public void finishUpdate(View arg0) {  
        }  
  
        @Override  
        public int getCount() {  
            return mListViews.size();  
        }  
  
        @Override  
        public Object instantiateItem(View arg0, int arg1) {  
            ((ViewPager) arg0).addView(mListViews.get(arg1), 0);  
            return mListViews.get(arg1);  
        }  
  
        @Override  
        public boolean isViewFromObject(View arg0, Object arg1) {  
            return arg0 == (arg1);  
        }  
  
        @Override  
        public void restoreState(Parcelable arg0, ClassLoader arg1) {  
        }  
  
        @Override  
        public Parcelable saveState() {  
            return null;  
        }  
  
        @Override  
        public void startUpdate(View arg0) {  
        }

		@Override
		public void onClick(View v) {
			// TODO 自动生成的方法存根
			
		}  

        
    }  
  
    public class MyOnPageChangeListener implements OnPageChangeListener {  
  
        int one = offset * 2 + bmpW;// 页卡1 -> 页卡2 偏移量  
        int two = one * 2;// 页卡1 -> 页卡3 偏移量  
  
        @Override  
        public void onPageSelected(int arg0) {  
            Animation animation = null;  
            switch (arg0) {  
            case 0:  
                if (currIndex == 1) {  
                    animation = new TranslateAnimation(one, 0, 0, 0);  
                } else if (currIndex == 2) {  
                    animation = new TranslateAnimation(two, 0, 0, 0);  
                }  
                break;  
            case 1:  
                if (currIndex == 0) {  
                    animation = new TranslateAnimation(offset, one, 0, 0);  
                } else if (currIndex == 2) {  
                    animation = new TranslateAnimation(two, one, 0, 0);  
                }  
                break;  
            case 2:  
                if (currIndex == 0) {  
                    animation = new TranslateAnimation(offset, two, 0, 0);  
                } else if (currIndex == 1) {  
                    animation = new TranslateAnimation(one, two, 0, 0);  
                }  
                break;  
            }  
            currIndex = arg0;  
            animation.setFillAfter(true);// True:图片停在动画结束位置  
            animation.setDuration(300);  
            rel.startAnimation(animation);  
        }  
  
        @Override  
        public void onPageScrolled(int arg0, float arg1, int arg2) {  
              
        }  
  
        @Override  
        public void onPageScrollStateChanged(int arg0) {  
              
        }
        
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