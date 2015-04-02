package com.example.jumpball;

import java.util.HashMap;

import android.app.Activity;
import android.content.Context;
import android.text.format.Time;
import android.util.Log;
import android.widget.Toast;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeBitmapFontData;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.input.GestureDetector.GestureListener;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction;
import com.badlogic.gdx.scenes.scene2d.actions.RepeatAction;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.ui.Window.WindowStyle;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;


public class FirstGameForRoll implements Screen,InputProcessor,GestureListener
{
	 public static float LBorder=410*rollMainActivity.w;
		public static float RBorder=(410*rollMainActivity.w+160*rollMainActivity.w*3-64*rollMainActivity.w);
		public static float DBorder=150*rollMainActivity.h;
		public static float UBorder=(150*rollMainActivity.h+160*rollMainActivity.h*3-64*rollMainActivity.h);
	RepeatAction r;
	boolean begin=false;
	Activity main;
	Image background;
	Music back_music;
	Sound nextStage;
	 Image helpR,helpL,help;
	 int display=0;
	 int second=0;
	static int success=0;
	static boolean ignore=false;
	static int fail=0;
	static int task=1;
	Time t=new Time();
	static boolean stageOver=false;
	static int stageCount=0;
	static int playMusic=0;
	static int current_min,current_sec,next_min,next_sec;
   static  boolean setTime=false;
	static Image zhuanpan;
	Stage stage;
	BitmapFont Font;
	 FreeTypeFontGenerator Generator;
	   FreeTypeBitmapFontData fontData;
	int fling;
	int area;
	static float degree=0;
	InputMultiplexer input;
	 LabelStyle labelStyle;
	 Image board,life,taskboard;
	 Label label,timelabel;
	 Label readyLabel,goLabel,taskLabel,stageLabel;
	 static Sound ready,go;
	 rollGameSwitch rgs;
	 static Label helpLabel;
	public FirstGameForRoll(rollGameSwitch rgs)
	{
		this.rgs=rgs;
	}
	public FirstGameForRoll(Activity main) {
		// TODO Auto-generated constructor stub
		
		this.main=main;
	}
	
	@Override
	public void show() {
		// TODO Auto-generated method stub
		nextStage=Gdx.audio.newSound(Gdx.files.internal("music/roll_nextstage.wav"));
		back_music=Gdx.audio.newMusic(Gdx.files.internal("music/roll_back.mp3"));
		back_music.setVolume(0.6f);
		back_music.setLooping(true);
		back_music.play();
				ready=Gdx.audio.newSound(Gdx.files.internal("ready.wav"));
		 go=Gdx.audio.newSound(Gdx.files.internal("go.wav"));
		 Generator=new FreeTypeFontGenerator(Gdx.files.internal("girl.TTF"));
			fontData=Generator.generateData(45, Generator.DEFAULT_CHARS+"落在右手左秒内得到分一直使用黄色区域屏幕向上下滑动转盘顺逆时针", false);
		    Font=new BitmapFont(fontData,fontData.getTextureRegion(),false);
		    Font.setColor(Color.BLUE);
		 labelStyle = new LabelStyle(Font, Color.BLACK); 
		  label = new Label("", labelStyle); 
		  readyLabel=new Label("",labelStyle);
          goLabel=new Label("",labelStyle);
		  timelabel=new Label("",labelStyle);
		  taskLabel=new Label("",labelStyle);
		  //failLabel=new Label("",labelStyle);
		  stageLabel=new Label("",labelStyle);
		  helpLabel=new Label("",labelStyle);
		stage=new Stage(rollMainActivity.screenwidth, rollMainActivity.screenheight,false);
		Texture.setEnforcePotImages(false);
		  help=new Image(new Texture(Gdx.files.internal("finger.png")));
	       helpL=new Image(new Texture(Gdx.files.internal("Lfinger.png")));
	       helpR=new Image(new Texture(Gdx.files.internal("Rfinger.png")));
	background=new Image(new Texture(Gdx.files.internal("zhuanpan/back.jpg")));
	board=new Image(new Texture(Gdx.files.internal("zhuanpan/board.png")));
	board.setSize(260*rollMainActivity.w, 178*rollMainActivity.h);
	life=new Image(new Texture(Gdx.files.internal("zhuanpan/life.png")));
	life.setSize(261*rollMainActivity.w, 33*rollMainActivity.h);
	taskboard=new Image(new Texture(Gdx.files.internal("zhuanpan/board.png")));
	taskboard.setSize(260*rollMainActivity.w, 178*rollMainActivity.h);
	taskboard.setPosition(30*rollMainActivity.w, 545*rollMainActivity.h);
	board.setPosition(30*rollMainActivity.w, 345*rollMainActivity.h);
	life.setPosition(1012*rollMainActivity.w, 662*rollMainActivity.h);
	
	background.setSize(rollMainActivity.screenwidth, rollMainActivity.screenheight);
	zhuanpan=new Image(new Texture(Gdx.files.internal("zhuanpan/zhuanpan.png")));
	zhuanpan.setSize(600*rollMainActivity.w,600*rollMainActivity.h);
	zhuanpan.setPosition(340*rollMainActivity.w, 60*rollMainActivity.h);
	Jumpforroll jump=new Jumpforroll();
	stage.addActor(background);
	 
	stage.addActor(zhuanpan);
	stage.addActor(jump);
	stage.addActor(jump.ball);
	stage.addActor(board);
	stage.addActor(taskboard);
	stage.addActor(life);
	stage.addActor(label);
	stage.addActor(timelabel);
	stage.addActor(readyLabel);
	stage.addActor(goLabel);
	stage.addActor(taskLabel);
	//stage.addActor(failLabel);
	stage.addActor(stageLabel);
	  stage.addActor(helpLabel);
	  help.setSize(0, 0);
      helpL.setSize(0, 0);
      helpR.setSize(0, 0);
      stage.addActor(help);
      stage.addActor(helpL);
      stage.addActor(helpR);
	 input=new InputMultiplexer();
     input.addProcessor(this);
     input.addProcessor(stage);
     input.addProcessor(new GestureDetector(this));
     Gdx.input.setInputProcessor(input);
	}
	@SuppressWarnings("deprecation")
	
	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
	}

	@Override
	public void resize(int arg0, int arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean keyDown(int arg0) {
		// TODO Auto-generated method stub
		
		return false;
	}
	
	public void onBackPressed() {
		// TODO Auto-generated method stub
		}

	@Override
	public boolean keyTyped(char arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyUp(int arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean mouseMoved(int arg0, int arg1) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean scrolled(int arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	

	@Override
	public boolean touchDown(int arg0, int arg1, int arg2, int arg3) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDragged(int arg0, int arg1, int arg2) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchUp(int arg0, int arg1, int arg2, int arg3) {
		// TODO Auto-generated method stub
		return false;
	}

	
	
	@Override
	public void render(float arg0) {
		// TODO Auto-generated method stub
		Gdx.gl.glClearColor(1, 1, 1, 1);
	       Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
	       second++;
	       if(Jumpforroll.rollfail==1)
	       {
	    	   rgs.setScreen(rgs.rf);
	       }
	       if(rollMainActivity.first==1)
		    	  help();
		      else
		    	  begin=true;
		if(begin==true){
	       if(playMusic==0)
	       {
	    	   ready.play();
	       }
	       if(playMusic>=0&&playMusic<60)
	       {
	    	   readyLabel.setText("ready");
	    	   readyLabel.setPosition(500*rollMainActivity.w, 500*rollMainActivity.h);
	    	  
	       }
	       if(playMusic!=-1)
	       playMusic++;
	       if(playMusic==60)
	       {
	    	   go.play();
	       }
	       if(playMusic>=60)
	       {
	    	   readyLabel.setText("go");
	    	   readyLabel.setPosition(550*rollMainActivity.w, 500*rollMainActivity.h);
	    	  
	       }
	       if(playMusic==100)
	       {
	    	   playMusic=-1;
	    	   readyLabel.setText("");
	       }
		      }
	       if(playMusic==-1)
	       {
	    	   if(stageOver==false)
	    	   {
	    		   stageLabel.setText("stage "+task+" start");
	    		   stageLabel.setPosition(300+stageCount*5, 500);
	    		   stageCount++;
	    	   }
	    	  
	    	   if(stageCount==80)
	    	   {
	    		   stageLabel.setText("");
	    		   stageOver=true;
	    		  // failLabel.setText(fail+"");
		    	   //failLabel.setPosition(100*rollMainActivity.w, 500*rollMainActivity.h);
		    	   life.setSize((10-fail)*26,33);
		    	   if(setTime==false)
			       {
			    	     t.setToNow();
			    	     current_min=t.minute;
			    	     current_sec=t.second;
			    	     setTime=true;
			       }
			       else
			       {
			    	   t.setToNow();
			    	    next_min=t.minute;
			    	    next_sec=t.second;
			    	    int sum=(next_min-current_min)*60+next_sec-current_sec;
			    	   //task1,得分
			    	   if(task==1)
			    	   {
			    		  
			    	    if(sum==60)
			    	    {
			    	    	System.out.println("第一次结束");
			    	    	nextStage.play(1);
			    	    	stageOver=false;
			    	    	stageCount=0;
			    	    	ignore=true;
			    	      	setTime=false;
			    	    	task=2;
			    	      }
			    	       timelabel.setText("Time :"+(60-sum)+"");
			    	       
			    	   }
			    	   //task2 ,task3,task4,task5落在指定区域
			    	   else if(task==2||task==3||task==4||task==5)
			    	   {
				    	    if(sum==30)
				    	    {
				    	    	nextStage.play(1);
				    	    	stageOver=false;
				    	    	stageCount=0;
				    	      	setTime=false;
				    	      	ignore=true;
				    	    	task=task+1;
				    	      }
				    	       timelabel.setText(30-sum+"");
			    	   }
			    	   else if(task==6||task==7||task==8||task==9||task==10||task==11)
			    	   {
			    		   if(sum==20)
				    	    {
			    				nextStage.play(1);
			    			    stageOver=false;
				    	    	stageCount=0;
				    	      	setTime=false;
				    	      	ignore=true;
				    	    	task=task+1;
				    	      }
				    	       timelabel.setText(20-sum+"");
				    	       if(task==12)
				    	       {
				    	    	   success=1;
				    	    	   rgs.setScreen(rgs.rf);
				    	       }
			    	   }
			    	  
			    	   
			       }
		    	   
		    	   
		    	   
		    	   
		    	   
	    	   }
	    	
		      
		       timelabel.setPosition(50*rollMainActivity.w, 650*rollMainActivity.h);
		     //  label.setSize(width, height)
		       label.setText("Score : "+Jumpforroll.sumscore);
			    label.setPosition(1020*rollMainActivity.w, 580*rollMainActivity.h);
			    if(task==1)
			    {
			    	 taskLabel.setText("60s内\n取得100分");
	    	         taskLabel.setPosition(60*rollMainActivity.w, 480*rollMainActivity.h);
			    }
			    if(task==2)
			    {
			    	 if(Jumpforroll.sumscore<100)
			    	 {
			    		 //一个失败的界面
			    	 }
			    	 else
			    	 {
			    		 taskLabel.setText("落在2\n分区域");
	    	             taskLabel.setPosition(60*rollMainActivity.w, 480*rollMainActivity.h);
			    	 }
			    	 
			    }
			    if(task==3)
			    {
			    	 taskLabel.setText("落在3分\n的区域");
    	             taskLabel.setPosition(60*rollMainActivity.w, 480*rollMainActivity.h);
			    }
			    if(task==4)
			    {
			    	 taskLabel.setText("落在4分\n的区域");
    	             taskLabel.setPosition(60*rollMainActivity.w, 480*rollMainActivity.h);
			    }
			    if(task==5)
			    {
			    	 taskLabel.setText("落在5分\n的区域");
    	             taskLabel.setPosition(60*rollMainActivity.w, 480*rollMainActivity.h);
			    }
			    if(task==6)
			    {
			    	 taskLabel.setText("只用左手，落在\n3分的区域");
    	             taskLabel.setPosition(60*rollMainActivity.w, 480*rollMainActivity.h);
			    }
			    if(task==7)
			    {
			    	 taskLabel.setText("只用左手，落在\n4分的区域");
    	             taskLabel.setPosition(60*rollMainActivity.w, 420*rollMainActivity.h);
			    }
			    if(task==8)
			    {
			    	 taskLabel.setText("只用左手，落在\n5分的区域");
    	             taskLabel.setPosition(60*rollMainActivity.w, 480*rollMainActivity.h);
			    }
			    if(task==9)
			    {
			    	 taskLabel.setText("只用右手，落在\n3分的区域");
    	             taskLabel.setPosition(60*rollMainActivity.w, 480*rollMainActivity.h);
			    }
			    if(task==10)
			    {
			    	 taskLabel.setText("只用左手，落在\n4分的区域");
    	             taskLabel.setPosition(60*rollMainActivity.w, 480*rollMainActivity.h);
			    }
			    if(task==11)
			    {
			    	 taskLabel.setText("只用右手，落在\n5分的区域");
    	             taskLabel.setPosition(60*rollMainActivity.w, 480*rollMainActivity.h);
			    }
			    
			    if(fail==-1)
			    {
			    	 readyLabel.setText("wrong");
			    	 readyLabel.setPosition(550*rollMainActivity.w, 500*rollMainActivity.h);
			    }
	    	  
	       }
	      
	      // System.out.println("zhuanpan的位置x="+zhuanpan.getX()+"y="+zhuanpan.getY());
	       stage.draw();
	       stage.act();
	}
	 public void help()
	    {
	       if(display==0)
	       {
	    	   helpLabel.setText("游戏规则");
	       }
	    	if(display==1)
	    	{
	    		helpLabel.setText("屏幕右侧向下滑动\n转盘顺时针转动");
	    	}
	    	if(display==2)
	    	{
	    		helpLabel.setText("屏幕右侧向上滑动\n转盘逆时针转动");
	    	}
	    	if(display==3)
	    	{
	    		helpLabel.setText("屏幕左侧向上滑动\n转盘顺时针转动");
	    	}
	    	if(display==4)
	    	{
	    		helpLabel.setText("屏幕左侧向下滑动\n转盘逆时针转动");
	    	}
	    	if(display==5)
	    	{
	    		helpLabel.setText("");
	    	}
	    	helpLabel.setPosition(400*rollMainActivity.w, 400*rollMainActivity.h);
	    	if(second==100)
	    	{
	    	    display=1;	
	    		helpR.setSize(128*rollMainActivity.w,64*rollMainActivity.h);
	    		helpR.setPosition(RBorder+300*rollMainActivity.w, 600*rollMainActivity.h);
	    		 MoveToAction moveto = Actions.moveTo(RBorder+300*rollMainActivity.w, 100*rollMainActivity.h,2f);
	    		 MoveToAction moveto1=Actions.moveTo(RBorder+300*rollMainActivity.w, 600*rollMainActivity.h);
	    		 SequenceAction alpha = Actions.sequence(moveto,moveto1);
	    		// RepeatAction r=Actions.repeat(5,alpha);
	    		 r=Actions.repeat(5,alpha);
	    		 helpR.addAction(r);
	    	}
	    	if(second==300)
	    	{
	    		/*
	    		helpR.setSize(0, 0);
	    		 display=2;	
	    		helpL.setSize(128,64);
	    		helpL.setPosition(50, 100);
	    		 MoveToAction moveto = Actions.moveTo(50, 600,2f);
	    		 MoveToAction moveto1=Actions.moveTo(50, 100);
	    		 SequenceAction alpha = Actions.sequence(moveto,moveto1);
	    		// RepeatAction r=Actions.repeat(5,alpha);
	    		 r=Actions.repeat(5,alpha);
	    		 helpL.addAction(r);
	    		 */
	    		helpR.setSize(0, 0);
	    		  display=2;	
		    		helpR.setSize(128*rollMainActivity.w,64*rollMainActivity.h);
		    		helpR.setPosition(RBorder+300*rollMainActivity.w, 100*rollMainActivity.h);
		    		 MoveToAction moveto = Actions.moveTo(RBorder+300*rollMainActivity.w, 600*rollMainActivity.h,2f);
		    		 MoveToAction moveto1=Actions.moveTo(RBorder+300*rollMainActivity.w, 100*rollMainActivity.h);
		    		 SequenceAction alpha = Actions.sequence(moveto,moveto1);
		    		// RepeatAction r=Actions.repeat(5,alpha);
		    		 r=Actions.repeat(5,alpha);
		    		 helpR.addAction(r);
	    	}
	    	if(second==500)
	    	{
	    		/*
	    		helpL.setSize(0, 0);
	    		 display=3;	
	    		help.setSize(64,128);
	    		help.setPosition(1000, 500);
	    		 MoveToAction moveto = Actions.moveTo(1200, 500,1.5f);
	    		 MoveToAction moveto1=Actions.moveTo(1000, 500);
	    		 SequenceAction alpha = Actions.sequence(moveto,moveto1);
	    		// RepeatAction r=Actions.repeat(5,alpha);
	    		 r=Actions.repeat(5,alpha);
	    		 help.addAction(r);
	    		 */
	    		helpR.setSize(0, 0);
	    		 display=3;	
	    		helpL.setSize(128*rollMainActivity.w,64*rollMainActivity.h);
	    		helpL.setPosition(50*rollMainActivity.w, 100*rollMainActivity.h);
	    		 MoveToAction moveto = Actions.moveTo(50*rollMainActivity.w, 600*rollMainActivity.h,2f);
	    		 MoveToAction moveto1=Actions.moveTo(50*rollMainActivity.w, 100*rollMainActivity.h);
	    		 SequenceAction alpha = Actions.sequence(moveto,moveto1);
	    		// RepeatAction r=Actions.repeat(5,alpha);
	    		 r=Actions.repeat(5,alpha);
	    		 helpL.addAction(r);
	    	}
	    	if(second==700)
	    	{
	    		/*
	    		help.removeAction(r);
	    		help.setSize(0, 0);
	    		 display=4;	
	    		help.setSize(64,128);
	    		help.setPosition(1000, 100);
	    		 MoveToAction moveto = Actions.moveTo(1200, 100,1.5f);
	    		 MoveToAction moveto1=Actions.moveTo(1000, 100);
	    		 SequenceAction alpha = Actions.sequence(moveto,moveto1);
	    		 RepeatAction r=Actions.repeat(5,alpha);
	    		 help.addAction(r);
	    		 */
	    		helpL.setSize(0, 0);
	    		 display=4;	
	    		helpL.setSize(128*rollMainActivity.w,64*rollMainActivity.h);
	    		helpL.setPosition(50*rollMainActivity.w, 600*rollMainActivity.h);
	    		 MoveToAction moveto = Actions.moveTo(50*rollMainActivity.w, 100*rollMainActivity.h,2f);
	    		 MoveToAction moveto1=Actions.moveTo(50*rollMainActivity.w, 600*rollMainActivity.h);
	    		 SequenceAction alpha = Actions.sequence(moveto,moveto1);
	    		// RepeatAction r=Actions.repeat(5,alpha);
	    		 r=Actions.repeat(5,alpha);
	    		 helpL.addAction(r);
	    	}
	    	if(second==900)
	    	{
	    		display=5;
	    		helpLabel.setText("");
	    		helpL.setSize(0, 0);
	    		begin=true;
	    	}
	    	
	    }
	@Override
	public boolean fling(float arg0, float arg1, int arg2) {
		// TODO Auto-generated method stub
		//arg1代表y方向的速度
		if(arg0>0&&Math.abs(arg0)>800)
		{
			System.out.println("向右");
			fling=0;
		}
			
		if(arg0<0&&Math.abs(arg0)>800)
		{
			System.out.println("向左");
			fling=1;
		}
			
		if(arg1>0&&Math.abs(arg1)>800)
		{
			System.out.println("向下");
			fling=3;
		}
			
		if(arg1<0&&Math.abs(arg1)>800)
		{
			System.out.println("向上");
			fling=2;
		}
		roll(arg1);
		return false;
	}
	public void roll( float speed)
	{
		
		System.out.println("fling="+fling+"speed="+speed+"area"+area);
		zhuanpan.setOrigin(300*rollMainActivity.w, 300*rollMainActivity.h);
		if(area==1||area==2)
		{
			if(task==6||task==7||task==8||task==10)
			{
				fail++;
			}
			else
			{
				if(fling==3)
		        {
				System.out.println("speed/100="+speed/100);
				degree=(degree+speed/100);
			    zhuanpan.addAction(Actions.rotateBy(-speed/100, speed/10000f));
		        }
		        if(fling==2)
		        {
		     	  System.out.println("speed/100="+speed/100);
			     degree=(degree+speed/100);
			     zhuanpan.addAction(Actions.rotateBy(-speed/100, speed/10000f));
		        }
			}
			
		}
		if(area==0||area==3)
		{
			if(task==9||task==11)
			{
				fail++;
			}
			else
			{
				
				  if(fling==2)
		       {
				System.out.println("speed/100="+speed/100);
				degree= (degree-speed/100);
			     zhuanpan.addAction(Actions.rotateBy(speed/100, speed/10000f));
		      }
		      if(fling==3)
		     {
			  System.out.println("speed/100="+speed/100);
			   degree= (degree-speed/100);
			  zhuanpan.addAction(Actions.rotateBy(speed/100, speed/10000f));
		      }
	     }
			
		}
	}
	@Override
	public boolean longPress(float arg0, float arg1) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean pan(float arg0, float arg1, float arg2, float arg3) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean panStop(float arg0, float arg1, int arg2, int arg3) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean pinch(Vector2 arg0, Vector2 arg1, Vector2 arg2, Vector2 arg3) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean tap(float arg0, float arg1, int arg2, int arg3) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean touchDown(float arg0, float arg1, int arg2, int arg3) {
		// TODO Auto-generated method stub
		if(arg0<rollMainActivity.screenwidth/2&&arg1>rollMainActivity.screenheight/2)
			area=0;
		else if(arg0>rollMainActivity.screenwidth/2&&arg1>rollMainActivity.screenheight/2)
			area=1;
		else if(arg0>rollMainActivity.screenwidth/2&&arg1<rollMainActivity.screenheight/2)
			area=2;
		else if(arg0<rollMainActivity.screenwidth/2&&arg1<rollMainActivity.screenheight/2)
			area=3;
		return true;
	}
	@Override
	public boolean zoom(float arg0, float arg1) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

}
