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

public class rollFail implements Screen,InputProcessor
{
	rollGameSwitch gs;
	Stage stage;
	Image image;
	LabelStyle labelStyle;
	Label label;
	
	 Label l;
	 Label loading;
		int fail=1;
		int failcount=0;
	private BitmapFont Font;
	
	
	
	 //SharedPreferences share;
	   private FreeTypeFontGenerator Generator;
       
	   FreeTypeBitmapFontData fontData;
	   int i=0;
	   int m,n;
	public rollFail(rollGameSwitch rollGameSwitch)
	{
		this.gs=rollGameSwitch;
		Generator=new FreeTypeFontGenerator(Gdx.files.internal("girl.TTF"));
		fontData=Generator.generateData(45, Generator.DEFAULT_CHARS, false);
	    Font=new BitmapFont(fontData,fontData.getTextureRegion(),false);
	    Font.setColor(Color.BLUE);
	    labelStyle = new LabelStyle(Font, Color.WHITE); 
	    Texture.setEnforcePotImages(false);
	  
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
	      
	       label.setText(Jumpforroll.sumscore+"");
		     
		      label.setFontScale(0.75f);
	          label.setPosition(1115*rollMainActivity.w, 565*rollMainActivity.h);
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
	    	  
	    		  if(Gdx.input.getX()>=490*rollMainActivity.w&&Gdx.input.getX()<=860*rollMainActivity.w&&Gdx.input.getY()>160*rollMainActivity.h&&Gdx.input.getY()<290*rollMainActivity.h)
	    		  {
	    			  fail=0;
	    			  Jumpforroll.score=0;
	    			  Jumpforroll.sumscore=0;
	    			  Jumpforroll.ignore=false;
	    			  Jumpforroll.rollfail=-1;
	    			  Jumpforroll.degree=new int[6];
	    			  FirstGameForRoll.success=0;
	    			  FirstGameForRoll.ignore=false;
	    			  FirstGameForRoll.fail=0;
	    			  FirstGameForRoll.task=1;
	    			  FirstGameForRoll.stageOver=false;
	    			  FirstGameForRoll.stageCount=0;
	    			  FirstGameForRoll.playMusic=0;
	    			  FirstGameForRoll.setTime=false;
	    			  FirstGameForRoll.degree=0;
	    			  FirstGameForRoll.zhuanpan=new Image(new Texture(Gdx.files.internal("zhuanpan/zhuanpan.png")));
	    				FirstGameForRoll.zhuanpan.setPosition(340*rollMainActivity.w, 60*rollMainActivity.h);
	    		   // gs.setScreen(gs.fg);
	    		
	    		  }
	    		  if(Gdx.input.getX()>=490*rollMainActivity.w&&Gdx.input.getX()<=860*rollMainActivity.w&&Gdx.input.getY()>350*rollMainActivity.h&&Gdx.input.getY()<480*rollMainActivity.h)
	    		  {
	    			  this.dispose();
	  				Gdx.app.exit();
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
		    String score=rollMainActivity.roll.getString("score", "");
		    System.out.println("score="+score);
		    if(Jumpforroll.score>Integer.parseInt(score))
		    {
		    	rollMainActivity.roll.edit().putString("day",s).commit();
		    	rollMainActivity.roll.edit().putString("score",String.valueOf(Jumpforroll.score)).commit();
		    }
		
		    stage = new Stage(rollMainActivity.screenwidth,rollMainActivity.screenheight,false);
		       label = new Label("", labelStyle); 
		       image = new Image(new Texture(Gdx.files.internal("zhuanpan/failback.png")));
		       image.setSize(rollMainActivity.screenwidth, rollMainActivity.screenheight);
		       loading = new Label("",labelStyle);
		       loading.setText("");
		       loading.setSize(100*rollMainActivity.w, 100*rollMainActivity.h);
		       loading.setPosition(480*rollMainActivity.w, 200*rollMainActivity.h);
		       l= new Label("", labelStyle); 
		       if(FirstGameForRoll.success==1)
		       {  
		    	   rollMainActivity.achievement.edit().putString("eight",String.valueOf(1)).commit();
		    	  // ghostMainActivity.first=0;
		     
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
