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

public class bluefail implements Screen,InputProcessor
{
	blueGameSwitch gs;
	Stage stage;
	Image image;
	Image restart,menu;
	 Label l;
	//public static float LBorder=400*MainActivity.w;
	//public static float RBorder=(784-64)*MainActivity.w;
	//public static float DBorder=164*MainActivity.h;
	//public static float UBorder=(164+128*3-64)*MainActivity.h;
	private int LBoder=(int)crowball.LBorder;
	private int RBoder=(int)crowball.RBorder;
	private int UBoder=(int)crowball.UBorder;
	private int DBoder=(int)crowball.DBorder;
	private BitmapFont Font;
	 //SharedPreferences share;
	   private FreeTypeFontGenerator Generator;
       
	   FreeTypeBitmapFontData fontData;
	   int i=0;
	   int m,n;
	   LabelStyle labelStyle;
	   Label label;
	public bluefail(blueGameSwitch gs)
	{
		this.gs=gs;
		Generator=new FreeTypeFontGenerator(Gdx.files.internal("girl.TTF"));
		fontData=Generator.generateData(75, Generator.DEFAULT_CHARS+"亲当你看到这个页时说明刚才疏忽肯定不是实力按一下面的钮，会得多分再来次吧简直超神了试能更高", false);
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
	       label.setText(blueJump.score+"");
		      label.setPosition(1050*MainActivity.w, 400*MainActivity.h);
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
	    		  Gdx.gl.glClearColor(1, 1, 1, 1);
	   	          Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
	    		  if(blueJump.state==blueJump.STATE.Left)
	    			 blueJump.state=blueJump.STATE.Right;
	    		 if(blueJump.state==blueJump.STATE.Right)
	    			 blueJump.state=blueJump.STATE.Left;
	    		 if(blueJump.state==blueJump.STATE.Up)
	    				  
	    			 blueJump.state=blueJump.STATE.Down;
	    	    if(blueJump.state==blueJump.STATE.Down)
	    			  blueJump.state=blueJump.STATE.Up;
	    		 n=blueJump.n;
	    		 while(n==blueJump.n)
	    		 {
	    			 blueJump.n=new Random().nextInt(3);
	    		 }
	    		 m=blueJump.m;
	    		 while(m==blueJump.m)
	    		 {
	    			 blueJump.m=new Random().nextInt(3);
	    		 }
	    		    blueFirstGame.fling=-1;
	    		    blueFirstGame.area=0;
	    		    blueJump.fail=0;
	    		    blueJump.ifcontinue=0;
	    		   blueJump.playcount=0; 
	    		   bluetooth.i=0;
	    		   bluetooth.state="prepare";
	    		   bluetooth.receive="null";
	    		   blueFirstGame.replay=false;
	    		   blueJump.score=0;
	    		   blueFirstGame.playMusic=0;
	    		   blueFirstGame.state=0;
	    		   bluetooth.blockreceice="null";
	    		   crowball.down1.setPosition(LBoder, DBoder-64*blueMainActivity.h);
	    		   crowball.down2.setPosition(LBoder+160*blueMainActivity.w, DBoder-64*blueMainActivity.h);
	    		
	    		   crowball.left1.setPosition(LBoder-64*blueMainActivity.w, DBoder);
	    		   crowball.left2.setPosition(LBoder-64*blueMainActivity.w, DBoder+160*blueMainActivity.h);
	    			
	    		   crowball.up1.setPosition(LBoder, UBoder+64*blueMainActivity.h);
	    		   crowball.up2.setPosition(LBoder+160*blueMainActivity.w,UBoder+64*blueMainActivity.h);
	    		
	    		   crowball.right1.setPosition(RBoder+64*blueMainActivity.w, DBoder);
	    		   crowball.right2.setPosition(RBoder+64*blueMainActivity.w, DBoder+160*blueMainActivity.h);
	    	        gs.setScreen(gs.bfg);
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
		    String score=blueMainActivity.bluetootha.getString("score", "");
		    System.out.println("score="+score);
		    if(blueJump.score>Integer.parseInt(score))
		    {
		    	blueMainActivity.bluetootha.edit().putString("day",s).commit();
		    	blueMainActivity.bluetootha.edit().putString("score",String.valueOf(blueJump.score)).commit();
		    }
		    if(blueJump.score>=40)
		    {
		    	 blueMainActivity.achievement.edit().putString("nine",String.valueOf(1)).commit();
		    }
		    stage = new Stage(blueMainActivity.screenwidth,blueMainActivity.screenheight,false);
		       label = new Label("", labelStyle); 
		       image = new Image(new Texture(Gdx.files.internal("fail_background.png")));
		       image.setSize(blueMainActivity.screenwidth, blueMainActivity.screenheight);
		       menu=new Image(new Texture(Gdx.files.internal("easy/menu.png")));
		       menu.setSize(317*blueMainActivity.w, 103*blueMainActivity.h);
		       menu.setPosition(480*blueMainActivity.w, 310*blueMainActivity.h);
		       restart=new Image(new Texture(Gdx.files.internal("easy/restart.png")));
		       restart.setSize(317*blueMainActivity.w, 103*blueMainActivity.h);
		       restart.setPosition(480*blueMainActivity.w, 480*blueMainActivity.h);
		       image = new Image(new Texture(Gdx.files.internal("easy/easyback.png")));
		       image.setSize(blueMainActivity.screenwidth, blueMainActivity.screenheight);
		       
		       l= new Label("", labelStyle); 
		    
		    	   stage.addActor(image);
				   stage.addActor(label);
		           stage.addActor(menu);
		           stage.addActor(restart);
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
		gs.setScreen(gs.bfg);
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
