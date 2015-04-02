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

public class crazyfail implements Screen,InputProcessor
{
	crazyGameSwitch gs;
	Stage stage;
	Image image;
	Image menu;
	 Image restart;
	 LabelStyle labelStyle;
	Label label;
	 Label loading;
		int fail=1;
		int failcount=0;
	/*
	
	 Image image_ach;
	 Image image_bonus;
	 Label l;
	 */
	 public static float LBorder=340*MainActivity.w;
		public static float RBorder=(340*MainActivity.w+180*MainActivity.w*3-64*MainActivity.w);
		public static float DBorder=100*MainActivity.h;
		public static float UBorder=(100*MainActivity.h+180*MainActivity.h*3-64*MainActivity.h);
	private BitmapFont Font;
	 //SharedPreferences share;
	   private FreeTypeFontGenerator Generator;
       
	   FreeTypeBitmapFontData fontData;
	   int i=0;
	   int m,n;
	public crazyfail(crazyGameSwitch gs)
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
	       label.setText(crazyJump.score+"");
		      label.setPosition(1050*MainActivity.w, 400*MainActivity.h);
	       /*
	       if(crazyJump.score>50)
	       {
	    	    label.setText( "          Score="+crazyJump.score+"\n 亲,你简直太厉害了\n你会得更多分得\n 再来一次吧");
	    	    label.setFontScale(0.75f*MainActivity.w);
	 	        label.setPosition(400*MainActivity.w, 380*MainActivity.h);
	       }
	       else
	       {
	    	  
	       label.setText( "      Score="+crazyJump.score+"\n 亲,你疏忽了\n 这肯定不是你的实力\n 再来一次吧");
		     
		      label.setFontScale(0.75f*MainActivity.w);
	          label.setPosition(400*MainActivity.w, 375*MainActivity.h);
	       }
	       */
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
		    	  gs.setScreen(gs.cfg);
		      }
	       stage.act();
	       stage.draw();
	      // stage.act();
	      // stage.draw();
	       
	      if(Gdx.input.justTouched())
			{ if(Gdx.input.getX()>470*crazyMainActivity.w&&Gdx.input.getX()<788*crazyMainActivity.w&&Gdx.input.getY()>310*crazyMainActivity.h&&Gdx.input.getY()<407*crazyMainActivity.h)
	    	  {
	    		  Gdx.app.exit();
	    	  }
	    	  if(Gdx.input.getX()>470*crazyMainActivity.w&&Gdx.input.getX()<788*crazyMainActivity.w&&Gdx.input.getY()>145*crazyMainActivity.h&&Gdx.input.getY()<250*crazyMainActivity.h)
	    	  {
	    		  //Gdx.gl.glClearColor(1, 1, 1, 1);
	   	      // Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
	   	       fail=0;
	    		  if(crazyJump.state==crazyJump.STATE.Left)
	    			   crazyJump.state=crazyJump.STATE.Right;
	    			    if(crazyJump.state==crazyJump.STATE.Right)
	    			    	  crazyJump.state=crazyJump.STATE.Left;
	    			    	//Jump.d.setPosition(Jump.right[1].x, Jump.right[1].y);
	    			    if(crazyJump.state==crazyJump.STATE.Up)
	    				   // Jump.d.setPosition(Jump.up[1].x, Jump.up[1].y);
	    			    	  crazyJump.state=crazyJump.STATE.Down;
	    			    if(crazyJump.state==crazyJump.STATE.Down)
	    				    //Jump.d.setPosition(Jump.down[1].x, Jump.down[1].y);
	    			    	  crazyJump.state=crazyJump.STATE.Up;
	    		 n=crazyJump.n;
	    		 while(n==crazyJump.n)
	    		 {
	    			 crazyJump.n=new Random().nextInt(3);
	    		 }
	    		 m=crazyJump.m;
	    		 while(m==crazyJump.m)
	    		 {
	    			 crazyJump.m=new Random().nextInt(3);
	    		 }
	    		    crazyFirstGame.fling=-1;
	    		    crazyFirstGame.area=0;
	    		    crazyJump.fail=0;
	    		    crazyJump.ifcontinue=0;
	    		    crazyJump.ifcontinue2=0;
	    		    crazyJump.playcount=0; 
	    		   
	    		   crazyJump.score=0;
	    		   crazyFirstGame.playMusic=0;
	    		   crazyball.down1.setPosition(LBorder, DBorder-64*MainActivity.h);
                   crazyball.down2.setPosition(LBorder+160*MainActivity.w, DBorder-64*MainActivity.h);
	    			//Ball.down3.setPosition(LBorder+256, DBorder-64);
                   crazyball.left1.setPosition(LBorder-64*MainActivity.w, DBorder);
                   crazyball.left2.setPosition(LBorder-64*MainActivity.w, DBorder+160*MainActivity.h);
	    			//Ball.left3.setPosition(LBorder-64, DBorder+128*2);
                   crazyball.up1.setPosition(LBorder, UBorder+64*MainActivity.h);
                   crazyball.up2.setPosition(LBorder+160*MainActivity.w,UBorder+64*MainActivity.h);
	    			//Ball.up3.setPosition(LBorder+128*2, UBorder+64);
                   crazyball.right1.setPosition(RBorder+64*MainActivity.w, DBorder);
                   crazyball.right2.setPosition(RBorder+64*MainActivity.w, DBorder+160*MainActivity.h);
	    			//Ball.right3.setPosition(RBorder+64, DBorder+128*2);
	    	       // gs.setScreen(gs.cfg);
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
		    String score=crazyMainActivity.crazy.getString("score", "");
		    System.out.println("score="+score);
		    if(crazyJump.score>Integer.parseInt(score))
		    {
		    	crazyMainActivity.crazy.edit().putString("day",s).commit();
		    	crazyMainActivity.crazy.edit().putString("score",String.valueOf(crazyJump.score)).commit();
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
		  //  crazyMainActivity.share.edit().putString("count",String.valueOf(n)).commit();
		    stage = new Stage(MainActivity.screenwidth,MainActivity.screenheight,false);
		       label = new Label("", labelStyle); 
		       image = new Image(new Texture(Gdx.files.internal("crazy/crazyback.png")));
		       image.setSize(MainActivity.screenwidth, MainActivity.screenheight);
		       menu=new Image(new Texture(Gdx.files.internal("easy/menu.png")));
		       menu.setSize(317*crazyMainActivity.w, 103*crazyMainActivity.h);
		       menu.setPosition(480*crazyMainActivity.w, 310*crazyMainActivity.h);
		       restart=new Image(new Texture(Gdx.files.internal("easy/restart.png")));
		       restart.setSize(317*crazyMainActivity.w, 103*crazyMainActivity.h);
		       restart.setPosition(480*crazyMainActivity.w, 480*crazyMainActivity.h);
		       loading = new Label("",labelStyle);
		       loading.setText("");
		       loading.setSize(100*crazyMainActivity.w, 100*crazyMainActivity.h);
		       loading.setPosition(480*crazyMainActivity.w, 200*crazyMainActivity.h);
		       if(crazyJump.score>30)
		       {  
		    	   crazyMainActivity.achievement.edit().putString("five",String.valueOf(1)).commit();
		    	
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
