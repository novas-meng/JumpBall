package com.example.jumpball;

import java.util.HashMap;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.NinePatch;
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


public class crazyFirstGame implements Screen,InputProcessor,GestureListener
{

	Image ach1;
	crazyball ball;
	RepeatAction r;
	 Image helpR,helpL,help,tips;
	 boolean begin=false;
	 int display=0;
	 int second=0;
	Image image;
	int count=0;
	 Image game_board;
	 Image score_board;
	static Stage stage;
	InputMultiplexer input;
	  FreeTypeFontGenerator Generator;
	   FreeTypeBitmapFontData fontData,helpData;
	crazyGameSwitch gs;
	BitmapFont bf;
	BitmapFont Font,helpFont;
	Activity main;
	TextureRegion texture;
	static int start=0;
	static int playMusic=0;
	//area代表将屏幕分为四块,fling代表滑动方向,0->  1<-  2 上 3 下 
	static int fling=0;
	static int area=0;
	static int[] ifexist=new int[12];
	boolean hasdialog=false;
	Sound ready,go;
	 LabelStyle labelStyle,helpStyle;
	 Label label;
	 Label readyLabel,goLabel,bounsLabel,tipsLabel;
	 static Label helpLabel;
	//存在方块值为1，不存在值为0
	public crazyFirstGame(crazyGameSwitch gs)
	{
		this.gs=gs;
	}
	
	public crazyFirstGame(Activity main) {
		// TODO Auto-generated constructor stub
		super();
		this.main=main;
	}
	@Override
	public void show() {
		// TODO Auto-generated method stub
		 for(int i=0;i<12;i++)
		 {
				 ifexist[i]=1;
		 }
		 ifexist[2]=0;
		 ifexist[3]=0;
		 ifexist[6]=0;
		 ifexist[11]=0;
		 Texture.setEnforcePotImages(false);
		 ach1=new Image(new Texture(Gdx.files.internal("ach/ach_5.png")));
		
		   ach1.setVisible(false);
	     
	       ach1.setSize(300*MainActivity.w, 130*MainActivity.h);
	     
	       ach1.setPosition(925*MainActivity.w, 530*MainActivity.h);
	    
		 Generator=new FreeTypeFontGenerator(Gdx.files.internal("girl.TTF"));
			fontData=Generator.generateData(75, Generator.DEFAULT_CHARS+"得心应手", false);
		    Font=new BitmapFont(fontData,fontData.getTextureRegion(),false);
		    Font.setColor(Color.BLUE);
		    labelStyle = new LabelStyle(Font, Color.BLACK); 
			helpData=Generator.generateData(45, Generator.DEFAULT_CHARS+"游戏规则在屏幕上方滑动手指控制侧块下左右小球会分裂哦", false);
		     helpFont=new BitmapFont(helpData,helpData.getTextureRegion(),false);
			    helpFont.setColor(Color.BLUE);
			     helpStyle = new LabelStyle(helpFont, Color.BLACK); 
		 ready=Gdx.audio.newSound(Gdx.files.internal("ready.wav"));
		 go=Gdx.audio.newSound(Gdx.files.internal("go.wav"));
		 image = new Image(new Texture(Gdx.files.internal("crazy/crazyback.png")));
		    //  image.setPosition(0, 0);
	       image.setSize(MainActivity.screenwidth, MainActivity.screenheight);
	       game_board=new Image(new Texture(Gdx.files.internal("game_board.png")));
	       game_board.setSize(700*MainActivity.w, 700*MainActivity.h);
	       game_board.setPosition(260*MainActivity.w, 20*MainActivity.h);
	       score_board=new Image(new Texture(Gdx.files.internal("score_board.png")));
	       score_board.setSize(200*MainActivity.w, 170*MainActivity.h);
	       score_board.setPosition(1000*MainActivity.w, 530*MainActivity.h);
	       help=new Image(new Texture(Gdx.files.internal("finger.png")));
	       helpL=new Image(new Texture(Gdx.files.internal("Lfinger.png")));
	       helpR=new Image(new Texture(Gdx.files.internal("Rfinger.png")));
	       tips=new Image(new Texture(Gdx.files.internal("tips.png")));
	       tips.setSize(240*crazyMainActivity.w, 120*crazyMainActivity.h);
	       stage = new Stage(MainActivity.screenwidth,MainActivity.screenheight,false);
	       //Gdx.input.setInputProcessor(stage);
           ball=new crazyball();
           stage.addActor(image);
         //  stage.addActor(game_board);
        //   stage.addActor(score_board);
           stage.addActor(tips);
           help.setSize(0, 0);
            helpL.setSize(0, 0);
            helpR.setSize(0, 0);
            stage.addActor(help);
            stage.addActor(helpL);
            stage.addActor(helpR);
           bf=new BitmapFont();
           bf.setScale(1.5f);
           label = new Label("", labelStyle); 
           readyLabel=new Label("",labelStyle);
           goLabel=new Label("",labelStyle);
           bounsLabel=new Label("",labelStyle);
           helpLabel=new Label("",helpStyle);
           tipsLabel=new Label("",helpStyle);
           crazyJump jump=new crazyJump();
	     //  stage.addActor(image);
	       stage.addActor(ball);
	       stage.addActor(ball.down1);
	       stage.addActor(ball.down2);
	      // stage.addActor(ball.down3);
	       stage.addActor(ball.left1);
	       stage.addActor(ball.left2);
	       //stage.addActor(ball.left3);
	       stage.addActor(ball.up1);
	       stage.addActor(ball.up2);
	      // stage.addActor(ball.up3);
	      
		       stage.addActor(ball.right1);
		       stage.addActor(ball.right2);
		      // stage.addActor(ball.right3);
	       stage.addActor(crazyJump.d);
	       
	       stage.addActor(jump);
	       stage.addActor(label);
	       stage.addActor(readyLabel);
	       stage.addActor(goLabel);
	       stage.addActor(helpLabel);
	       stage.addActor(bounsLabel);
	       stage.addActor(tipsLabel);
	       stage.addActor(ach1);
	   //    stage.addActor(new blockcolor());
	      //stage.addActor(l);
	       input=new InputMultiplexer();
	       input.addProcessor(this);
	       input.addProcessor(stage);
	       input.addProcessor(new GestureDetector(this));
	       Gdx.input.setInputProcessor(input);
	     
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		bf.dispose();
		ready.dispose();
		go.dispose();
		stage.dispose();
		for(int i=0;i<25;i++)
		crazyJump.sound[i].dispose();
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
	       second++;
	       label.setText(crazyJump.score+"");
		      label.setPosition(1050*MainActivity.w, 400*MainActivity.h);
		      readyLabel.setText("");
		      tipsLabel.setText("小球会分裂哦");
		      tipsLabel.setFontScale(0.7f);
		      tipsLabel.setPosition(30*crazyMainActivity.w, 600*crazyMainActivity.h);
		      tips.setPosition(30*crazyMainActivity.w, 545*crazyMainActivity.h);
		      if(crazyMainActivity.first==1)
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
	    	   readyLabel.setPosition(500*MainActivity.w, 500*MainActivity.h);
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
	    	   readyLabel.setPosition(550*MainActivity.w, 500*MainActivity.h);
	       }
	       if(playMusic==100)
	       {
	    	   playMusic=-1;
	    	   
	       }
		      }
	     if(crazyJump.fail==1)
	     {
	    	 System.out.println("碰撞错误");
	    	
	    	 gs.setScreen(gs.f);
	     }
	     /*
	     if(crazyJump.score>=30&&count<60&&crazyMainActivity.bouns1==0)
	     {
	    	 count++;
	    	 bounsLabel.setText("得心应手");
	    	 bounsLabel.setPosition(1000*crazyMainActivity.w, 600*crazyMainActivity.h);
	     }
	     if(count==60)
	     {
	    	 bounsLabel.setText("");
	    	 count=0;
	     }
	     */
	     if(crazyJump.score==30&&crazyMainActivity.bouns1==0)
	     {
	    	ach1.setVisible(true);
	     }
	     if(crazyJump.score>=31)
	    	 ach1.setVisible(false);
	    	  stage.act();
	      stage.draw();
	   
	}
	 public void help()
	    {
	       if(display==0)
	       {
	    	   helpLabel.setText("游戏规则");
	       }
	    	if(display==1)
	    	{
	    		helpLabel.setText("屏幕右侧上下滑动\n控制右侧滑块");
	    	}
	    	if(display==2)
	    	{
	    		helpLabel.setText("屏幕左侧上下滑动\n控制左侧滑块");
	    	}
	    	if(display==3)
	    	{
	    		helpLabel.setText("屏幕上侧左右滑动\n控制上侧滑块");
	    	}
	    	if(display==4)
	    	{
	    		helpLabel.setText("屏幕下侧左右滑动\n控制下侧滑块");
	    	}
	    	if(display==5)
	    	{
	    		helpLabel.setText("");
	    	}
	    	helpLabel.setPosition(400*crazyMainActivity.w, 400*crazyMainActivity.h);
	    	if(second==100)
	    	{
	    	    display=1;	
	    		helpR.setSize(128*crazyMainActivity.w,64*crazyMainActivity.h);
	    		helpR.setPosition(crowball.RBorder+300*crazyMainActivity.w, 100*crazyMainActivity.h);
	    		 MoveToAction moveto = Actions.moveTo(crowball.RBorder+300*crazyMainActivity.w, 600*crazyMainActivity.h,2f);
	    		 MoveToAction moveto1=Actions.moveTo(crowball.RBorder+300*crazyMainActivity.w, 100*crazyMainActivity.h);
	    		 SequenceAction alpha = Actions.sequence(moveto,moveto1);
	    		// RepeatAction r=Actions.repeat(5,alpha);
	    		 r=Actions.repeat(5,alpha);
	    		 helpR.addAction(r);
	    	}
	    	if(second==300)
	    	{
	    		helpR.setSize(0, 0);
	    		 display=2;	
	    		helpL.setSize(128*crazyMainActivity.w,64*crazyMainActivity.h);
	    		helpL.setPosition(50*crazyMainActivity.w, 100*crazyMainActivity.h);
	    		 MoveToAction moveto = Actions.moveTo(50*crazyMainActivity.w, 600*crazyMainActivity.h,2f);
	    		 MoveToAction moveto1=Actions.moveTo(50*crazyMainActivity.w, 100*crazyMainActivity.h);
	    		 SequenceAction alpha = Actions.sequence(moveto,moveto1);
	    		// RepeatAction r=Actions.repeat(5,alpha);
	    		 r=Actions.repeat(5,alpha);
	    		 helpL.addAction(r);
	    	}
	    	if(second==500)
	    	{
	    		helpL.setSize(0, 0);
	    		 display=3;	
	    		help.setSize(64*crazyMainActivity.w,128*crazyMainActivity.h);
	    		help.setPosition(1000*crazyMainActivity.w, 500*crazyMainActivity.h);
	    		 MoveToAction moveto = Actions.moveTo(1200*crazyMainActivity.w, 500*crazyMainActivity.h,1.5f);
	    		 MoveToAction moveto1=Actions.moveTo(1000*crazyMainActivity.w, 500*crazyMainActivity.h);
	    		 SequenceAction alpha = Actions.sequence(moveto,moveto1);
	    		// RepeatAction r=Actions.repeat(5,alpha);
	    		 r=Actions.repeat(5,alpha);
	    		 help.addAction(r);
	    	}
	    	if(second==700)
	    	{
	    		help.removeAction(r);
	    		help.setSize(0, 0);
	    		 display=4;	
	    		help.setSize(64*crazyMainActivity.w,128*crazyMainActivity.h);
	    		help.setPosition(1000*crazyMainActivity.w, 100*crazyMainActivity.h);
	    		 MoveToAction moveto = Actions.moveTo(1200*crazyMainActivity.w, 100*crazyMainActivity.h,1.5f);
	    		 MoveToAction moveto1=Actions.moveTo(1000*crazyMainActivity.w, 100*crazyMainActivity.h);
	    		 SequenceAction alpha = Actions.sequence(moveto,moveto1);
	    		 RepeatAction r=Actions.repeat(5,alpha);
	    		 help.addAction(r);
	    	}
	    	if(second==900)
	    	{
	    		display=5;
	    		helpLabel.setText("");
	    		help.setSize(0, 0);
	    		begin=true;
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
	public boolean fling(float arg0, float arg1, int arg2) {
		// TODO Auto-generated method stub
		System.out.println(arg0+" "+arg1+arg2+"");
		System.out.println(area);
		System.out.println(MainActivity.screenwidth);
		//System.out.println("花瓶");
		if(arg0>0&&Math.abs(arg0)>1000)
		{
			System.out.println("向右");
			fling=0;
		}
			
		if(arg0<0&&Math.abs(arg0)>1000)
		{
			System.out.println("向左");
			fling=1;
		}
			
		if(arg1>0&&Math.abs(arg1)>1000)
		{
			System.out.println("向下");
			fling=3;
		}
			
		if(arg1<0&&Math.abs(arg1)>1000)
		{
			System.out.println("向上");
			fling=2;
		}
			changePosition();
		return false;
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
	public boolean touchDown(float arg0, float arg1, int arg2, int arg3) //arg0代表x坐标，arg1代表y坐标
	{
		// TODO Auto-generated method stub
		System.out.println("被按下了");
		System.out.println("arg0="+arg0+"arg1="+arg1+"arg2="+arg2+"arg3="+arg3);
		if(arg0<MainActivity.screenwidth/2&&arg1>MainActivity.screenheight/2)
			area=0;
		else if(arg0>MainActivity.screenwidth/2&&arg1>MainActivity.screenheight/2)
			area=1;
		else if(arg0>MainActivity.screenwidth/2&&arg1<MainActivity.screenheight/2)
			area=2;
		else if(arg0<MainActivity.screenwidth/2&&arg1<MainActivity.screenheight/2)
			area=3;
		return true;
	}
 public void changePosition()//滑动时改变方块位置
 {
	 System.out.println("area="+area+"fling="+fling);
	 if(area==1||area==2)
	 {
		 if(fling==2)
		 {
			if(ifexist[3]==0&&ifexist[4]==1)
			{
				ifexist[3]=1;
				ifexist[4]=0;
				  MoveToAction moveto = Actions.moveTo(crazyJump.right[2].x+64*MainActivity.w, crazyJump.right[2].y-48*MainActivity.h, 0.1f);
				  crazyball.right2.addAction(moveto);
			}
			else if(ifexist[3]==1&&ifexist[4]==0&&ifexist[5]==1)
			{
				ifexist[4]=1;
				ifexist[5]=0;
				  MoveToAction moveto = Actions.moveTo(crazyJump.right[1].x+64*MainActivity.w, crazyJump.right[1].y-48*MainActivity.h, 0.1f);
				  crazyball.right1.addAction(moveto);
			}
		 }
		 if(fling==3)
		 {
			 if(ifexist[3]==1&&ifexist[4]==0&&ifexist[5]==1)
			 {
				 ifexist[3]=0;
					ifexist[4]=1;
					  MoveToAction moveto = Actions.moveTo(crazyJump.right[1].x+64*MainActivity.w, crazyJump.right[1].y-48*MainActivity.h, 0.05f);
					  crazyball.right2.addAction(moveto);
			 }
			 else if(ifexist[3]==1&&ifexist[4]==1&&ifexist[5]==0)
			 {
				 ifexist[4]=0;
					ifexist[5]=1;
					  MoveToAction moveto = Actions.moveTo(crazyJump.right[0].x+64*MainActivity.w, crazyJump.right[0].y-48*MainActivity.h, 0.05f);
					  crazyball.right1.addAction(moveto);
			 }
		 }
	 }
	 
	 if(area==3||area==0)
	 {
		 if(fling==2)
		 {
			if(ifexist[11]==0&&ifexist[10]==1)
			{
				ifexist[11]=1;
				ifexist[10]=0;
				  MoveToAction moveto = Actions.moveTo(crazyJump.left[2].x-64*MainActivity.w, crazyJump.left[2].y-48*MainActivity.h, 0.05f);
				  crazyball.left2.addAction(moveto);
			}
			else if(ifexist[11]==1&&ifexist[10]==0&&ifexist[9]==1)
			{
				ifexist[10]=1;
				ifexist[9]=0;
				  MoveToAction moveto = Actions.moveTo(crazyJump.left[1].x-64*MainActivity.w, crazyJump.left[1].y-48*MainActivity.h, 0.05f);
				  crazyball.left1.addAction(moveto);
			}
		 }
		 if(fling==3)
		 {
			 if(ifexist[11]==1&&ifexist[10]==0&&ifexist[9]==1)
			 {
				 ifexist[11]=0;
					ifexist[10]=1;
					  MoveToAction moveto = Actions.moveTo(crazyJump.left[1].x-64*MainActivity.w, crazyJump.left[1].y-48*MainActivity.h, 0.05f);
					  crazyball.left2.addAction(moveto);
			 }
			 else if(ifexist[11]==1&&ifexist[10]==1&&ifexist[9]==0)
			 {
				 ifexist[10]=0;
					ifexist[9]=1;
					  MoveToAction moveto = Actions.moveTo(crazyJump.left[0].x-64*MainActivity.w, crazyJump.left[0].y-48*MainActivity.h, 0.05f);
					  crazyball.left1.addAction(moveto);
			 }
		 }
	 }
	 
	 if(area==3||area==2)
	 {
		 if(fling==0)
		 {
			if(ifexist[2]==0&&ifexist[1]==1)
			{
				ifexist[2]=1;
				ifexist[1]=0;
				  MoveToAction moveto = Actions.moveTo(crazyJump.up[2].x-48*MainActivity.w, crazyJump.up[2].y+64*MainActivity.h, 0.05f);
				  crazyball.up2.addAction(moveto);
			}
			else if(ifexist[0]==1&&ifexist[1]==0&&ifexist[2]==1)
			{
				ifexist[1]=1;
				ifexist[0]=0;
				  MoveToAction moveto = Actions.moveTo(crazyJump.up[1].x-48*MainActivity.w, crazyJump.up[1].y+64*MainActivity.h, 0.05f);
				  crazyball.up1.addAction(moveto);
			}
		 }
		 if(fling==1)
		 {
			 if(ifexist[0]==0&&ifexist[1]==1&&ifexist[2]==1)
			 {
				 ifexist[1]=0;
					ifexist[0]=1;
					  MoveToAction moveto = Actions.moveTo(crazyJump.up[0].x-48*MainActivity.w, crazyJump.up[0].y+64*MainActivity.h, 0.05f);
					  crazyball.up1.addAction(moveto);
			 }
			 else if(ifexist[0]==1&&ifexist[1]==0&&ifexist[2]==1)
			 {
				 ifexist[1]=1;
					ifexist[2]=0;
					  MoveToAction moveto = Actions.moveTo(crazyJump.up[1].x-48*MainActivity.w, crazyJump.up[1].y+64*MainActivity.h, 0.05f);
					  crazyball.up2.addAction(moveto);
			 }
		 }
	 }
	 
	 if(area==0||area==1)
	 {
		 if(fling==0)
		 {
			if(ifexist[6]==0&&ifexist[7]==1)
			{
				ifexist[6]=1;
				ifexist[7]=0;
				  MoveToAction moveto = Actions.moveTo(crazyJump.down[2].x-48*MainActivity.w, crazyJump.down[2].y-64*MainActivity.h,0.05f);
				  crazyball.down2.addAction(moveto);
			}
			else if(ifexist[8]==1&&ifexist[7]==0&&ifexist[6]==1)
			{
				ifexist[7]=1;
				ifexist[8]=0;
				  MoveToAction moveto = Actions.moveTo(crazyJump.down[1].x-48*MainActivity.w, crazyJump.down[1].y-64*MainActivity.h, 0.05f);
				  crazyball.down1.addAction(moveto);
			}
		 }
		 if(fling==1)
		 {
			 if(ifexist[8]==0&&ifexist[7]==1&&ifexist[6]==1)
			 {
				 ifexist[7]=0;
					ifexist[8]=1;
					  MoveToAction moveto = Actions.moveTo(crazyJump.down[0].x-48*MainActivity.w, crazyJump.down[0].y-64*MainActivity.h, 0.05f);
					  crazyball.down1.addAction(moveto);
			 }
			 else if(ifexist[8]==1&&ifexist[7]==0&&ifexist[6]==1)
			 {
				 ifexist[7]=1;
					ifexist[6]=0;
					  MoveToAction moveto = Actions.moveTo(crazyJump.down[1].x-48*MainActivity.w, crazyJump.down[1].y-64*MainActivity.h, 0.05f);
					  crazyball.down2.addAction(moveto);
			 }
		 }
	 }
 }
	@Override
	public boolean zoom(float arg0, float arg1) {
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
	public void hide() {
		// TODO Auto-generated method stub
		
	}
}
