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

public class ghostfail implements Screen,InputProcessor
{
	ghostGameSwitch gs;
	Stage stage;
	Image image;
	LabelStyle labelStyle;
	Label label;
	 Image image_ach;
	 Image image_bonus;
	 ImageButton restart,back;
	 TextureRegionDrawable trd,tru;
	 Label l;
	 public static float LBorder=430*MainActivity.w;
		public static float RBorder=(430*MainActivity.w+160*MainActivity.w*3-64*MainActivity.w);
		public static float DBorder=150*MainActivity.h;
		public static float UBorder=(150*MainActivity.h+160*MainActivity.h*3-64*MainActivity.h);
	private BitmapFont Font;
	 Label loading;
		int fail=1;
		int failcount=0;
	
	
	 //SharedPreferences share;
	   private FreeTypeFontGenerator Generator;
       
	   FreeTypeBitmapFontData fontData;
	   int i=0;
	   int m,n;
	public ghostfail(ghostGameSwitch ghostGameSwitch)
	{
		this.gs=ghostGameSwitch;
		Generator=new FreeTypeFontGenerator(Gdx.files.internal("girl.TTF"));
		fontData=Generator.generateData(75, Generator.DEFAULT_CHARS, false);
	    Font=new BitmapFont(fontData,fontData.getTextureRegion(),false);
	    Font.setColor(Color.BLUE);
	    labelStyle = new LabelStyle(Font, Color.WHITE); 
	    Texture.setEnforcePotImages(false);
	    Texture txtup=new Texture(Gdx.files.internal("ghost/ghost_restart_btn.png"));
		Texture txtdown=new Texture(Gdx.files.internal("ghost/ghost_restart_pressed_btn.png"));
		 trd=new TextureRegionDrawable(new TextureRegion(txtdown));
		tru=new TextureRegionDrawable(new TextureRegion(txtup));
		 restart=new ImageButton(tru,trd);
		 txtup=new Texture(Gdx.files.internal("ghost/ghost_back_btn.png"));
		 txtdown=new Texture(Gdx.files.internal("ghost/ghost_back_pressed_btn.png"));
		 trd=new TextureRegionDrawable(new TextureRegion(txtdown));
			tru=new TextureRegionDrawable(new TextureRegion(txtup));
			 back=new ImageButton(tru,trd);
		back.setPosition(530*ghostMainActivity.w, 250*ghostMainActivity.h);
		restart.setPosition(530*ghostMainActivity.w, 450*ghostMainActivity.h);
		//back.isPressed() = true;
	}
	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		//this.dispose();
		Font.dispose();
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}
	
	public boolean keyDown(int arg0) {
		// TODO Auto-generated method stub
		
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
		// Gdx.gl.glClearColor(1, 1, 1, 1);
	      // Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
	      
	       label.setText(ghostJump.score+"");
		     
		      label.setFontScale(0.75f);
	          label.setPosition(160*MainActivity.w, 155*MainActivity.h);
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
			{
	    	  if(Gdx.input.getX()>500*ghostMainActivity.w&&Gdx.input.getX()<870*ghostMainActivity.w)
	    	  {
	    		  if(Gdx.input.getY()<350*ghostMainActivity.h)
	    		 {
	    	  		
	    		  Gdx.gl.glClearColor(1, 1, 1, 1);
	    		  Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
	    		  fail=0;
	    		  if(ghostJump.state==ghostJump.STATE.Left)
	    			   ghostJump.state=ghostJump.STATE.Right;
	    			    if(ghostJump.state==ghostJump.STATE.Right)
	    			    	  ghostJump.state=ghostJump.STATE.Left;
	    			    	//Jump.d.setPosition(Jump.right[1].x, Jump.right[1].y);
	    			    if(ghostJump.state==ghostJump.STATE.Up)
	    				   // Jump.d.setPosition(Jump.up[1].x, Jump.up[1].y);
	    			    	  ghostJump.state=ghostJump.STATE.Down;
	    			    if(ghostJump.state==ghostJump.STATE.Down)
	    				    //Jump.d.setPosition(Jump.down[1].x, Jump.down[1].y);
	    			    	  ghostJump.state=ghostJump.STATE.Up;
	    		 n=ghostJump.n;
	    		 while(n==ghostJump.n)
	    		 {
	    			 ghostJump.n=new Random().nextInt(3);
	    		 }
	    		 m=ghostJump.m;
	    		 while(m==ghostJump.m)
	    		 {
	    			 ghostJump.m=new Random().nextInt(3);
	    		 }
	    		 ghostFirstGame.begin=false;
	    		    ghostFirstGame.help1exist=false;
	    		    ghostFirstGame.fling=-1;
	    		    ghostFirstGame.area=0;
	    		    ghostJump.fail=0;
	    		    ghostJump.ifcontinue=0;
	    		    ghostJump.playcount=0; 
	    		    for(int j=0;j<10;j++)
	    		   ghostFirstGame.index[j]=0;
	    		    ghostFirstGame.speed=1;
	    		    ghostFirstGame.gap=1;
	    		    ghostFirstGame.pumpexist=false;
	    		   ghostJump.score=0;
	    		   ghostFirstGame.playMusic=0;
	    		   Ballforghost.down1.setPosition(LBorder, DBorder-64*ghostMainActivity.h);
	    			Ballforghost.down2.setPosition(LBorder+160*ghostMainActivity.w, DBorder-64*ghostMainActivity.h);
	    			Ballforghost.left1.setPosition(LBorder-160*ghostMainActivity.w, DBorder);
	    			Ballforghost.left2.setPosition(LBorder-64*ghostMainActivity.w, DBorder+160*ghostMainActivity.h);	    			
	    			Ballforghost.up1.setPosition(LBorder, UBorder+64*ghostMainActivity.h);
	    			Ballforghost.up2.setPosition(LBorder+160*ghostMainActivity.w,UBorder+64*ghostMainActivity.h);
	    			Ballforghost.right1.setPosition(RBorder+64*ghostMainActivity.w, DBorder);
	    			Ballforghost.right2.setPosition(RBorder+64*ghostMainActivity.w, DBorder+160*ghostMainActivity.h);
	    			
	    	       // gs.setScreen(gs.fg);
	    		  }
	    		  if(Gdx.input.getY() > 400*ghostMainActivity.h)
	    		  {
	    			  this.dispose();
	  				Gdx.app.exit();
	    		  }
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
		    SharedPreferences sp=welcome.crazy;
		    String score=ghostMainActivity.ghost.getString("score", "");
		    System.out.println("score="+score);
		    if(ghostJump.score>Integer.parseInt(score))
		    {
		    	ghostMainActivity.ghost.edit().putString("day",s).commit();
		    	ghostMainActivity.ghost.edit().putString("score",String.valueOf(ghostJump.score)).commit();
		    }
		/*
		 Time t=new Time();
		    t.setToNow();
		    String count=MainActivity.share.getString("count", "");
		    System.out.println("count="+count);
		    String s=t.year+"."+t.month+"."+t.monthDay+"."+t.hour+"."+t.minute+"."+t.second;
		    System.out.println("fail="+s);
		    //int sc=Integer.parseInt(MainActivity.share.getString("score"+Integer.parseInt(count), ""));
		    int n=Integer.parseInt(count);
		   // if(Jump.score>sc)
		  //  {
		    	 MainActivity.share.edit()    
                .putString("day"+Integer.parseInt(count),s)
                .putString("score"+Integer.parseInt(count),String.valueOf(crazyJump.score))    
                .commit();  
		    	 n++;
		 //   }
		    System.out.println("n="+n);
		    if(n==10)
		    	n=0;
		    	*/
		   
		    
		    //ghostMainActivity.share.edit().putString("count",String.valueOf(n)).commit();
		    stage = new Stage(ghostMainActivity.screenwidth,ghostMainActivity.screenheight,false);
		       label = new Label("", labelStyle); 
		       image = new Image(new Texture(Gdx.files.internal("ghost/ghost_fail_background.png")));
		       image.setSize(ghostMainActivity.screenwidth, ghostMainActivity.screenheight);
		       image_ach=new Image(new Texture(Gdx.files.internal("fail_bonus.png")));
		       image_ach.setPosition(1035*ghostMainActivity.w, 530*ghostMainActivity.h);
		       image_ach.setSize(215*ghostMainActivity.w, 130*ghostMainActivity.h);
		       loading = new Label("",labelStyle);
		       loading.setText("");
		       loading.setSize(100*ghostMainActivity.w, 100*ghostMainActivity.h);
		       loading.setPosition(480*ghostMainActivity.w, 200*ghostMainActivity.h);
		       l= new Label("", labelStyle); 
		       if(ghostJump.score>=30)
		       {  
		    	   ghostMainActivity.achievement.edit().putString("seven",String.valueOf(1)).commit();
		    	  // ghostMainActivity.first=0;
		       l.setText("·è¿ñË«ÊÖ");
		       l.setFontScale(0.7f);
		       l.setPosition(1040*ghostMainActivity.w, 600*ghostMainActivity.h);
		      
		       stage.addActor(image);
			   stage.addActor(label);
			   stage.addActor(image_ach);
			  
			   stage.addActor(back);
			   stage.addActor(restart);
			   
			   stage.addActor(l);
			   stage.addActor(loading);
		       }
		       else
		       {
		    	   stage.addActor(image);
				   stage.addActor(label);
				   stage.addActor(back);
				   stage.addActor(restart);
				   stage.addActor(loading);
		       }
			  
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
