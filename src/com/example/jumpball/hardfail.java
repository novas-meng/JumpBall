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

public class hardfail implements Screen,InputProcessor
{
	hardGameSwitch gs;
	Stage stage;
	Image image;
	Image menu;
	 Image restart;
		 public static float LBorder=395*MainActivity.w;
			public static float RBorder=(395*MainActivity.w+160*MainActivity.w*3-64*MainActivity.w);
			public static float DBorder=165*MainActivity.h;
			public static float UBorder=(165*MainActivity.h+160*MainActivity.h*3-64*MainActivity.h);
	private BitmapFont Font;
	 //SharedPreferences share;
	   private FreeTypeFontGenerator Generator;
       
	   FreeTypeBitmapFontData fontData;
	   int i=0;
	   int m,n;
	   LabelStyle labelStyle;
	   Label label;
	   Label loading;
		int fail=1;
		int failcount=0;
	public hardfail(hardGameSwitch gs)
	{
		this.gs=gs;
		Generator=new FreeTypeFontGenerator(Gdx.files.internal("girl.TTF"));
		fontData=Generator.generateData(75, Generator.DEFAULT_CHARS+"", false);
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
	       label.setText(hardJump.score+"");
		      label.setPosition(1050*MainActivity.w, 400*MainActivity.h);
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
		    	  gs.setScreen(gs.hfg);
		      }
	       stage.act();
	       stage.draw();
	      
	      if(Gdx.input.justTouched())
			{
	    	  if(Gdx.input.getX()>470*MainActivity.w&&Gdx.input.getX()<788*MainActivity.w&&Gdx.input.getY()>310*MainActivity.h&&Gdx.input.getY()<407*MainActivity.h)
	    	  {
	    		  Gdx.app.exit();
	    	  }
	    	  if(Gdx.input.getX()>470*MainActivity.w&&Gdx.input.getX()<788*MainActivity.w&&Gdx.input.getY()>145*MainActivity.h&&Gdx.input.getY()<250*MainActivity.h)
	    	  {
	    		//  Gdx.gl.glClearColor(1, 1, 1, 1);
	   	        //  Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
	   	          fail=0;
	    		  if(hardJump.state==hardJump.STATE.Left)
	    			   hardJump.state=hardJump.STATE.Right;
	    			    if(hardJump.state==hardJump.STATE.Right)
	    			    	  hardJump.state=hardJump.STATE.Left;
	    			    	//Jump.d.setPosition(Jump.right[1].x, Jump.right[1].y);
	    			    if(hardJump.state==hardJump.STATE.Up)
	    				   // Jump.d.setPosition(Jump.up[1].x, Jump.up[1].y);
	    			    	  hardJump.state=hardJump.STATE.Down;
	    			    if(hardJump.state==hardJump.STATE.Down)
	    				    //Jump.d.setPosition(Jump.down[1].x, Jump.down[1].y);
	    			    	  hardJump.state=hardJump.STATE.Up;
	    		 n=hardJump.n;
	    		 while(n==hardJump.n)
	    		 {
	    			 hardJump.n=new Random().nextInt(3);
	    		 }
	    		 m=hardJump.m;
	    		 while(m==hardJump.m)
	    		 {
	    			 hardJump.m=new Random().nextInt(3);
	    		 }
	    		 hardFirstGame.gap=0;
	    		    hardFirstGame.fling=-1;
	    		    hardFirstGame.area=0;
	    		    hardFirstGame.fireexist=false;
	    		    hardFirstGame.speed=1;
	    		    hardFirstGame.hasreset1=false;
	    		    hardFirstGame.hasreset2=false;
	    		    hardFirstGame.hasreset3=false;
	    		    hardFirstGame.hasreset4=false;
	    		    hardFirstGame.hasreset5=false;
	    		    hardFirstGame.hasreset6=false;
	    		    hardFirstGame.hasreset7=false;
	    		    hardFirstGame.hasreset8=false;
	    		     hardJump.fail=0;
	    		    hardJump.ifcontinue=0;
	    		    hardJump.playcount=0; 
	    		   
	    		   hardJump.score=0;
	    		   hardFirstGame.playMusic=0;
                    hardball.down1.setPosition(LBorder, DBorder-64*MainActivity.h);
                    hardball.down2.setPosition(LBorder+160*MainActivity.w, DBorder-64*MainActivity.h);
	    			//Ball.down3.setPosition(LBorder+256, DBorder-64);
                    hardball.left1.setPosition(LBorder-64*MainActivity.w, DBorder);
                    hardball.left2.setPosition(LBorder-64*MainActivity.w, DBorder+160*MainActivity.h);
	    			//Ball.left3.setPosition(LBorder-64, DBorder+128*2);
                    hardball.up1.setPosition(LBorder, UBorder+64*MainActivity.h);
                    hardball.up2.setPosition(LBorder+160*MainActivity.w,UBorder+64*MainActivity.h);
	    			//Ball.up3.setPosition(LBorder+128*2, UBorder+64);
                    hardball.right1.setPosition(RBorder+64*MainActivity.w, DBorder);
                    hardball.right2.setPosition(RBorder+64*MainActivity.w, DBorder+160*MainActivity.h);
	    			//Ball.right3.setPosition(RBorder+64, DBorder+128*2);
	    		
	    				//gs.setScreen(gs.hfg);
	    		
	    	  }
	    	 
				//System.out.println("受到法律卡上飞机快乐撒地方；萨度");
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
		Texture.setEnforcePotImages(false);
		 Time t=new Time();
		    t.setToNow();
		    String s=t.year+"."+t.month+"."+t.monthDay+"."+t.hour+"."+t.minute+"."+t.second;
		    SharedPreferences sp=welcome.hard;
		    String score=hardMainActivity.hard.getString("score","");
		    System.out.println("score="+score);
		    if(hardJump.score>Integer.parseInt(score))
		    {
		    	hardMainActivity.hard.edit().putString("day",s).commit();
		    	hardMainActivity.hard.edit().putString("score",String.valueOf(hardJump.score)).commit();
		    }
		    stage = new Stage(MainActivity.screenwidth,MainActivity.screenheight,false);
		       label = new Label("", labelStyle); 
		    menu=new Image(new Texture(Gdx.files.internal("easy/menu.png")));
		       menu.setSize(317*MainActivity.w, 103*MainActivity.h);
		       menu.setPosition(480*MainActivity.w, 310*MainActivity.h);
		       restart=new Image(new Texture(Gdx.files.internal("easy/restart.png")));
		       restart.setSize(317*MainActivity.w, 103*MainActivity.h);
		       restart.setPosition(480*MainActivity.w, 480*MainActivity.h);
		       image = new Image(new Texture(Gdx.files.internal("hard/hardback.png")));
		       image.setSize(MainActivity.screenwidth, MainActivity.screenheight);
		       loading = new Label("",labelStyle);
		       loading.setText("");
		       loading.setSize(100*MainActivity.w, 100*MainActivity.h);
		       loading.setPosition(480*MainActivity.w, 200*MainActivity.h);
		       if(hardJump.score>=50)
		       {  
		    	   hardMainActivity.achievement.edit().putString("four",String.valueOf(1)).commit();
		  
		    
		       }
		       else if(hardJump.score>=30)
		       {
		    	   hardMainActivity.achievement.edit().putString("three",String.valueOf(1)).commit();
				  
		       } 
		       stage.addActor(image);
		       stage.addActor(menu);
		       stage.addActor(restart);
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
		// TODO Auto-generated method stu
	
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
