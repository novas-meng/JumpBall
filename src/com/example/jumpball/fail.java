package com.example.jumpball;

import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeBitmapFontData;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.format.Time;

public class fail implements Screen,InputProcessor
{
	GameSwitch gs;
	Stage stage;
	Image image;
	Image image_ach;
	 Image image_bonus;
	 Label l;
	 Label loading;
		int fail=1;
		int failcount=0;
	 public static float LBorder=430*MainActivity.w;
		public static float RBorder=(430*MainActivity.w+160*MainActivity.w*3-64*MainActivity.w);
		public static float DBorder=150*MainActivity.h;
		public static float UBorder=(150*MainActivity.h+160*MainActivity.h*3-64*MainActivity.h);
	//public static float LBorder=400*MainActivity.w;
	//public static float RBorder=(784-64)*MainActivity.w;
	//public static float DBorder=164*MainActivity.h;
	//public static float UBorder=(164+128*3-64)*MainActivity.h;
	private BitmapFont Font;
	 SharedPreferences share;
	   private FreeTypeFontGenerator Generator;
	   LabelStyle labelStyle;
	   Label label;
	   
	   FreeTypeBitmapFontData fontData;
	   int i=0;
	   int m,n;
	public fail(GameSwitch gs)
	{
		this.gs=gs;
		Generator=new FreeTypeFontGenerator(Gdx.files.internal("girl.TTF"));
		fontData=Generator.generateData(75, Generator.DEFAULT_CHARS+"有所小成反映达人新手上路亲当你看到这个页太厉害时说明刚才疏忽肯定不是实力按一下面的钮，会得多分再来次吧简直超神了试能更高", false);
	    Font=new BitmapFont(fontData,fontData.getTextureRegion(),false);
	    Font.setColor(Color.BLUE);
	    labelStyle = new LabelStyle(Font, Color.WHITE); 
	}
	@Override
	public void dispose() {
		// TODO Auto-generated method stub
	
		Font.dispose();
	
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}
	
	public boolean keyDown(int arg0) {
		// TODO Auto-generated method stub
	//	gs.setScreen(gs.fg);
		
		if(arg0==Input.Keys.BACK){
			//gs.setScreen(gs.fg);
			this.dispose();
			Gdx.app.exit();
		
		}
		
		return false;
	}
	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(float arg0) {
		// TODO Auto-generated method stub
		 Gdx.gl.glClearColor(1, 1, 1, 1);
	       Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
	       if(fail==0)
		      {
		    	  failcount++;
		      }
		      if(failcount==1)
		      {
		    	  loading.setText("Loading...");
		      }
		      if(failcount==10)
		      {
		    	  fail=1;
		    	  failcount=0;
		    	  gs.setScreen(gs.fg);
		      }

	       stage.act();
	       stage.draw();
	      if(Gdx.input.justTouched())
			{ if(Gdx.input.getX()>500*MainActivity.w&&Gdx.input.getX()<820*MainActivity.w&&Gdx.input.getY()>320*MainActivity.h&&Gdx.input.getY()<410*MainActivity.h)
	    	  {
				  System.out.println("退出");
	    		  Gdx.app.exit();
	    	  }
			System.out.println("触摸电"+Gdx.input.getY());
	    	  if(Gdx.input.getX()>500*MainActivity.w&&Gdx.input.getX()<820*MainActivity.w&&Gdx.input.getY()<279*MainActivity.h&&Gdx.input.getY()>130*MainActivity.h)
	    	  {			
	    		  //Gdx.gl.glClearColor(1, 1, 1, 1);
	   	         // Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
	    		  fail=0;
	   	          System.out.println("再来一次") ;
	    		  if(Jump.state==Jump.STATE.Left)
	    			   Jump.state=Jump.STATE.Right;
	    		if(Jump.state==Jump.STATE.Right)
	    			    	  Jump.state=Jump.STATE.Left;
	    		if(Jump.state==Jump.STATE.Up)	    				 
	    			    	  Jump.state=Jump.STATE.Down;
	    		if(Jump.state==Jump.STATE.Down)
	    			    	  Jump.state=Jump.STATE.Up;
	    		 n=Jump.n;
	    		 while(n==Jump.n)
	    		 {
	    			 Jump.n=new Random().nextInt(3);
	    		 }
	    		 m=Jump.m;
	    		 while(m==Jump.m)
	    		 {
	    			 Jump.m=new Random().nextInt(3);
	    		 }
	    		    FirstGame.fling=-1;
	    		    FirstGame.area=0;
	    		    FirstGame.begin=false;
	    		    FirstGame.help1exist=false;
	    		    FirstGame.help2exist=false;
	    		    FirstGame.help3exist=false;
	    		    Jump.fail=0;
	    		    Jump.ifcontinue=0;
	    		    Jump.playcount=0; 
	    		   Jump.hasBreaken=false;
	    		   Jump.CrowHitBall=0;
	    		   Jump.hasGoToRight=0;
	    		   Jump.hasCrow=false;
	    		   Jump.time=0;
	    		   Jump.setscore1=false;
	    		   Jump.setscore2=false;
	    		   Jump.setscore3=false;
	    		   Jump.setscore4=false;
	    		   Jump.setscore5=false;
	    		   Jump.setscore6=false;
	    		   Jump.setscore7=false;
	    		   Jump.score=0;
	    		   FirstGame.playMusic=0;
	    		   FirstGame.query=true;
	    		   FirstGame.playAnimation_split=0;
	    		   Jump.ifCrowExist=true;
	    		   Jump.crowTo=-1;
	    		   Jump.first=0;
	    		   Ball.down1.setPosition(LBorder, DBorder-64*MainActivity.h);
	    			Ball.down2.setPosition(LBorder+160*MainActivity.w, DBorder-64*MainActivity.h);
	    			Ball.left1.setPosition(LBorder-64*MainActivity.w, DBorder);
	    			Ball.left2.setPosition(LBorder-64*MainActivity.w, DBorder+160*MainActivity.h);
	    			Ball.up1.setPosition(LBorder, UBorder+64*MainActivity.h);
	    			Ball.up2.setPosition(LBorder+160*MainActivity.w,UBorder+64*MainActivity.h);
	    			Ball.right1.setPosition(RBorder+64*MainActivity.w, DBorder);
	    			Ball.right2.setPosition(RBorder+64*MainActivity.w, DBorder+160*MainActivity.h);
	    			//if(Modechoice.hardChoose==1)
	    				//gs.setScreen(gs.hfg);
	    			//else
	    	       // gs.setScreen(gs.fg);
	    	  }
			}
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
	public void show() {
		// TODO Auto-generated method stub
		//gs.fg.dispose();
		
		 Time t=new Time();
		    t.setToNow();
		    String s=t.year+"."+t.month+"."+t.monthDay+"."+t.hour+"."+t.minute+"."+t.second;
		    SharedPreferences sp=welcome.easy;
		    String score=MainActivity.crow.getString("score", "");
		    System.out.println("score="+score);
		    if(Jump.score>Integer.parseInt(score))
		    {
		    	MainActivity.crow.edit().putString("day",s).commit();
		    	MainActivity.crow.edit().putString("score",String.valueOf(Jump.score)).commit();
		    }
		   
		    
		 //   MainActivity.share.edit().putString("count",String.valueOf(n)).commit();
	       stage = new Stage(MainActivity.screenwidth,MainActivity.screenheight,false);
	       label = new Label("", labelStyle); 
	       image = new Image(new Texture(Gdx.files.internal("crow/fail_background.png")));
	       image.setSize(MainActivity.screenwidth, MainActivity.screenheight);
	     /*
	        image_ach=new Image(new Texture(Gdx.files.internal("fail_bonus.png")));
	       image_ach.setPosition(1035*MainActivity.w, 530*MainActivity.h);
	       image_ach.setSize(215*MainActivity.w, 130*MainActivity.h);
	       image_bonus=new Image(new Texture(Gdx.files.internal("medal.png")));
	       image_bonus.setSize(140*MainActivity.w,140*MainActivity.h);
	       image_bonus.setPosition(1080*MainActivity.w, 430*MainActivity.h);
	       */
	       l= new Label("", labelStyle); 
	       loading = new Label("",labelStyle);
	       loading.setText("");
	       loading.setSize(100*MainActivity.w, 100*MainActivity.h);
	       loading.setPosition(480*MainActivity.w, 200*MainActivity.h);
	         if(Jump.score>30)
	       {
	    	 
	    	   //System.out.println("大于10分");
	    	   MainActivity.achievement.edit().putString("six",String.valueOf(1)).commit();
	    	
	       }
	    
	    	   stage.addActor(image);
			   stage.addActor(label);
			   stage.addActor(loading);
	      
	      
	      
	  
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
		gs.setScreen(gs.fg);
		return true;
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
}
