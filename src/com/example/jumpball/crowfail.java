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

public class crowfail implements Screen,InputProcessor
{
	crowGameSwitch gs;
	Stage stage;
	Image image;
	Image menu,restart;
	 Label l;
	 public static float LBorder=410*MainActivity.w;
		public static float RBorder=(410*MainActivity.w+160*MainActivity.w*3-64*MainActivity.w);
		public static float DBorder=150*MainActivity.h;
		public static float UBorder=(150*MainActivity.h+160*MainActivity.h*3-64*MainActivity.h);
	//public static float LBorder=400*MainActivity.w;
	//public static float RBorder=(784-64)*MainActivity.w;
	//public static float DBorder=164*MainActivity.h;
	//public static float UBorder=(164+128*3-64)*MainActivity.h;
	private BitmapFont Font;
	 //SharedPreferences share;
	   private FreeTypeFontGenerator Generator;
	   LabelStyle labelStyle;
	   Label label;
	   
	   FreeTypeBitmapFontData fontData;
	   int i=0;
	   int m,n;
	   Label loading;
		int fail=1;
		int failcount=0;
	public crowfail(crowGameSwitch gs)
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
		System.out.println("资源已经彼清楚了");
	
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
	       
	       label.setText(crowJump.score+"");
		      label.setPosition(1050*MainActivity.w, 400*MainActivity.h);
		      if(fail==0)
		      {
		    	  loading.setText("Loading...");
		    	  failcount++;
		      }
		      if(failcount==1)
		      {
		    	  
		      }
		      if(failcount==10)
		      {
		    	  fail=1;
		    	  failcount=0;
		    	  gs.setScreen(gs.fg);
		      }
		      else
		      {
		    	   stage.act();
	       stage.draw();
		      }
	      
	      if(Gdx.input.justTouched())
			{
	    	  if(Gdx.input.getX()>470*crowMainActivity.w&&Gdx.input.getX()<788*crowMainActivity.w&&Gdx.input.getY()>310*crowMainActivity.h&&Gdx.input.getY()<407*crowMainActivity.h)
	    	  {
	    		  Gdx.app.exit();
	    	  }
	    	  if(Gdx.input.getX()>470*crowMainActivity.w&&Gdx.input.getX()<788*crowMainActivity.w&&Gdx.input.getY()>145*crowMainActivity.h&&Gdx.input.getY()<250*crowMainActivity.h)
	    	  {			
	    		  //Gdx.gl.glClearColor(1, 1, 1, 1);
	   	        //  Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
	   	       fail=0;
	    		  if(crowJump.state==crowJump.STATE.Left)
	    			   crowJump.state=crowJump.STATE.Right;
	    		if(crowJump.state==crowJump.STATE.Right)
	    			    	  crowJump.state=crowJump.STATE.Left;
	    		if(crowJump.state==crowJump.STATE.Up)	    				 
	    			    	  crowJump.state=crowJump.STATE.Down;
	    		if(crowJump.state==crowJump.STATE.Down)
	    			    	  crowJump.state=crowJump.STATE.Up;
	    		 n=crowJump.n;
	    		 while(n==crowJump.n)
	    		 {
	    			 crowJump.n=new Random().nextInt(3);
	    		 }
	    		 m=crowJump.m;
	    		 while(m==crowJump.m)
	    		 {
	    			 crowJump.m=new Random().nextInt(3);
	    		 }
	    		    crowFirstGame.fling=-1;
	    		    crowFirstGame.area=0;
	    		    crowJump.fail=0;
	    		    crowJump.ifcontinue=0;
	    		    crowJump.playcount=0; 
	    		   crowJump.hasBreaken=false;
	    		   crowJump.CrowHitBall=0;
	    		   crowJump.hasGoToRight=0;
	    		   crowJump.score=0;
	    		   crowFirstGame.playMusic=0;
	    	
	    		   crowJump.ifCrowExist=true;
	    		   crowJump.crowTo=-1;
	    		   crowJump.first=0;
	    		   crowball.down1.setPosition(LBorder, DBorder-64*crowMainActivity.h);
	    		   crowball.down2.setPosition(LBorder+160*crowMainActivity.w, DBorder-64*crowMainActivity.h);
	    		   crowball.left1.setPosition(LBorder-64*crowMainActivity.w, DBorder);
	    		   crowball.left2.setPosition(LBorder-64*crowMainActivity.w, DBorder+160*crowMainActivity.h);
	    		   crowball.up1.setPosition(LBorder, UBorder+64*crowMainActivity.h);
	    		   crowball.up2.setPosition(LBorder+160*crowMainActivity.w,UBorder+64*crowMainActivity.h);
	    		   crowball.right1.setPosition(RBorder+64*crowMainActivity.w, DBorder);
	    		   crowball.right2.setPosition(RBorder+64*crowMainActivity.w, DBorder+160*crowMainActivity.h);
	    			
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
		    String score=crowMainActivity.easy.getString("score", "");
		    System.out.println("score="+score);
		    if(crowJump.score>Integer.parseInt(score))
		    {
		    	crowMainActivity.easy.edit().putString("day",s).commit();
		    	crowMainActivity.easy.edit().putString("score",String.valueOf(crowJump.score)).commit();
		    }
		   
		   // crowMainActivity.share.edit().putString("count",String.valueOf(n)).commit();
	       stage = new Stage(crowMainActivity.screenwidth,crowMainActivity.screenheight,false);
	       label = new Label("", labelStyle); 
	       image = new Image(new Texture(Gdx.files.internal("easy/easyback.png")));
	       image.setSize(crowMainActivity.screenwidth, crowMainActivity.screenheight);
	       menu=new Image(new Texture(Gdx.files.internal("easy/menu.png")));
	       menu.setSize(317*crowMainActivity.w, 103*crowMainActivity.h);
	       menu.setPosition(480*crowMainActivity.w, 310*crowMainActivity.h);
	       restart=new Image(new Texture(Gdx.files.internal("easy/restart.png")));
	       restart.setSize(317*crowMainActivity.w, 103*crowMainActivity.h);
	       restart.setPosition(480*crowMainActivity.w, 480*crowMainActivity.h);
	       loading = new Label("",labelStyle);
	       loading.setText("");
	       loading.setSize(100*crowMainActivity.w, 100*crowMainActivity.h);
	       loading.setPosition(480*crowMainActivity.w, 200*crowMainActivity.h);
	       if(crowJump.score>=80)
	       {
	    	   crowMainActivity.achievement.edit().putString("two",String.valueOf(1)).commit();
	    	 	  	       
	       }
	      
	       else if(crowJump.score>=30)
	       {  
	    	   crowMainActivity.achievement.edit().putString("one",String.valueOf(1)).commit();
	    	  	 
	       }
	       else
	       {
	    	   
	    	  
	       }
	      
	       stage.addActor(image);
		   stage.addActor(label);
		   stage.addActor(menu);
		   stage.addActor(restart);
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
