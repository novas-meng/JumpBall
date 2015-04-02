package com.example.jumpball;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

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
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Pixmap.Format;
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
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction;
import com.badlogic.gdx.scenes.scene2d.actions.ParallelAction;
import com.badlogic.gdx.scenes.scene2d.actions.RepeatAction;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;
import com.badlogic.gdx.scenes.scene2d.actions.SizeToAction;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.ui.Window.WindowStyle;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Array;


public class crowFirstGame implements Screen,InputProcessor,GestureListener
{

	crowball ball;
	int flag1=-1,flag2=-1,flag3=-1,flag4=-1,flag5=-1,flag6=-1;
	int count=0;
	Actor move1,move2;
	MoveToAction moveto;
	boolean temp=false,temp1=false,temp2=false,temp3=false,temp4=false,temp5=false;
	RepeatAction r;
	Image ach1,ach2;
	Image image;
	 Image game_board;
	 Image score_board;
	 Image helpR,helpL,help,paodanL1,paodanL2,paodanL3,paodanR1,paodanR2,paodanR3,tips;
	 boolean begin=false;
	 Sound fly;
	 int display=0;
	Stage stage;
	int second=0;
	InputMultiplexer input;
	  FreeTypeFontGenerator Generator;
	   FreeTypeBitmapFontData fontData,helpData;
	crowGameSwitch gs;
	static BitmapFont bf;
	BitmapFont Font,helpFont;
	Activity main;
	TextureRegion texture;
	static int start=0;
	BitmapFont windowbf;
	static int playMusic=0;
	//area代表将屏幕分为四块,fling代表滑动方向,0->  1<-  2 上 3 下 
	static int fling=0;
	static int area=0;
	static int[] ifexist=new int[12];
	boolean hasdialog=false;
	static Sound ready;
	static Sound go;
	 LabelStyle labelStyle,helpStyle;
	 Label label,tipsLabel;
	 Label readyLabel,goLabel,bounsLabel;
	 static Label helpLabel;
	//存在方块值为1，不存在值为0
	public crowFirstGame(crowGameSwitch gs)
	{
		this.gs=gs;
	}
	
	public crowFirstGame(Activity main) {
		// TODO Auto-generated constructor stub
		super();
		this.main=main;
	}
	@Override
	public void show() {
		// TODO Auto-generated method stub
		MainActivity.w=crowMainActivity.w;
		MainActivity.h=crowMainActivity.h;
		 for(int i=0;i<12;i++)
		 {
				 ifexist[i]=1;
		 }
		 ifexist[2]=0;
		 ifexist[3]=0;
		 ifexist[6]=0;
		 ifexist[11]=0;
		 Texture.setEnforcePotImages(false);
		 ach1=new Image(new Texture(Gdx.files.internal("ach/ach_1.png")));
		 ach2=new Image(new Texture(Gdx.files.internal("ach/ach_2.png")));
		 fly=Gdx.audio.newSound(Gdx.files.internal("inter/paodanfly.wav"));
		 Generator=new FreeTypeFontGenerator(Gdx.files.internal("girl.TTF"));
			fontData=Generator.generateData(75, Generator.DEFAULT_CHARS+"游戏规则在屏幕上方滑动手指控制侧块下左右初出茅庐反应达人", false);
			helpData=Generator.generateData(45, Generator.DEFAULT_CHARS+"游戏规则在屏幕上方滑动手指控制侧块下左右点击飞来的炮弹就会消失哦", false);
		    Font=new BitmapFont(fontData,fontData.getTextureRegion(),false);
		    Font.setColor(Color.BLUE);
		     labelStyle = new LabelStyle(Font, Color.BLACK); 
		     helpFont=new BitmapFont(helpData,helpData.getTextureRegion(),false);
			    helpFont.setColor(Color.BLUE);
			     helpStyle = new LabelStyle(helpFont, Color.BLACK); 
		 ready=Gdx.audio.newSound(Gdx.files.internal("ready.wav"));
		 go=Gdx.audio.newSound(Gdx.files.internal("go.wav"));
		 windowbf=new BitmapFont();
		 image = new Image(new Texture(Gdx.files.internal("easy/easyback.png")));
	    //  image.setPosition(0, 0);
       image.setSize(crowMainActivity.screenwidth, crowMainActivity.screenheight);
       help=new Image(new Texture(Gdx.files.internal("finger.png")));
       helpL=new Image(new Texture(Gdx.files.internal("Lfinger.png")));
       helpR=new Image(new Texture(Gdx.files.internal("Rfinger.png")));
       tips=new Image(new Texture(Gdx.files.internal("tips.png")));
       tips.setSize(240*crowMainActivity.w, 120*crowMainActivity.h);
     
       paodanL1=new Image(new Texture(Gdx.files.internal("inter/paodanL.png")));
       paodanR1=new Image(new Texture(Gdx.files.internal("inter/paodanR.png")));
       paodanL2=new Image(new Texture(Gdx.files.internal("inter/paodanL.png")));
       paodanR2=new Image(new Texture(Gdx.files.internal("inter/paodanR.png")));
       game_board=new Image(new Texture(Gdx.files.internal("game_board.png")));
       game_board.setSize(700*crowMainActivity.w, 700*crowMainActivity.h);
       game_board.setPosition(260*crowMainActivity.w, 20*crowMainActivity.h);
       score_board=new Image(new Texture(Gdx.files.internal("score_board.png")));
       score_board.setSize(250*crowMainActivity.w, 220*crowMainActivity.h);
       score_board.setPosition(970*crowMainActivity.w, 330*crowMainActivity.h);
	       stage = new Stage(crowMainActivity.screenwidth,crowMainActivity.screenheight,false);
	       //Gdx.input.setInputProcessor(stage);
	       ach1.setVisible(false);
	       ach2.setVisible(false);
	       ach1.setSize(300*crowMainActivity.w, 130*crowMainActivity.h);
	       ach2.setSize(300*crowMainActivity.w, 130*crowMainActivity.h);
	       ach1.setPosition(925*crowMainActivity.w, 530*crowMainActivity.h);
	       ach2.setPosition(925*crowMainActivity.w, 530*crowMainActivity.h);
           ball=new crowball();
           stage.addActor(image);
          // stage.addActor(game_board);
          // stage.addActor(score_board);
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
           crowJump jump=new crowJump();
	       stage.addActor(ball);
	       stage.addActor(ball.down1);
	       stage.addActor(ball.down2);  
	       stage.addActor(ball.left1);
	       stage.addActor(ball.left2);
	       //stage.addActor(ball.left3);
	       stage.addActor(ball.up1);
	       stage.addActor(ball.up2);
	  
		       stage.addActor(ball.right1);
		       stage.addActor(ball.right2);
		      // stage.addActor(ball.right3);
	       stage.addActor(crowJump.d);
	       stage.addActor(jump);
	   
	       stage.addActor(label);
	       stage.addActor(readyLabel);
	       stage.addActor(goLabel);
	       stage.addActor(helpLabel);
	       stage.addActor(bounsLabel);
	       stage.addActor(tipsLabel);
	       paodanL2.setSize(0, 0);
	       paodanR2.setSize(0, 0);
	       paodanL1.setSize(0, 0);
	       paodanR1.setSize(0, 0);
	       stage.addActor(paodanL1);
	       stage.addActor(paodanR1);
	       stage.addActor(paodanL2);
	       stage.addActor(paodanR2);
	       stage.addActor(ach1);
	       stage.addActor(ach2);
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
		System.out.println("在firstGame中资源被释放了");
		bf.dispose();
		ready.dispose();
		go.dispose();
		stage.dispose();
		for(int i=0;i<25;i++)
		crowJump.sound[i].dispose();
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
	  
	      label.setText(crowJump.score+"");
	      label.setPosition(1050*crowMainActivity.w, 400*crowMainActivity.h);
	      readyLabel.setText("");
	      tipsLabel.setText("点击飞来的炮弹\n就会消失哦");
	      tipsLabel.setFontScale(0.7f);
	      tipsLabel.setPosition(30*crowMainActivity.w, 600*crowMainActivity.h);
	      tips.setPosition(30*crowMainActivity.w, 545*crowMainActivity.h);
	      if(welcome.first==1)
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
	    	   readyLabel.setPosition(500*crowMainActivity.w, 500*crowMainActivity.h);
	    	  
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
	    	   readyLabel.setPosition(550*crowMainActivity.w, 500*crowMainActivity.h);
	    	  
	       }
	       if(playMusic==100)
	       {
	    	   playMusic=-1;
	    	   
	       }
	      }
	     if(crowJump.fail==1)
	     {
	    	
	    	 System.out.println("碰撞错误");
	    	 gs.setScreen(gs.cf);
	     }
	     /*
	     if(crowJump.score>=30&&count<60&&crowMainActivity.bouns1==0)
	     {
	    	 count++;
	    	 bounsLabel.setText("初出茅庐");
	    	 bounsLabel.setPosition(1000, 600);
	     }
	     if(count==60)
	     {
	    	 bounsLabel.setText("");
	    	 count=0;
	     }
	     if(crowJump.score>=80&&count<60&&crowMainActivity.bouns2==0)
	     {
	    	 count++;
	    	 bounsLabel.setText("反应达人");
	    	 bounsLabel.setPosition(1000, 600);
	     }
	     */
	    
	     if(crowJump.score==30&&crowMainActivity.bouns1==0)
	     {
	    	ach1.setVisible(true);
	     }
	     if(crowJump.score>=31)
	    	 ach1.setVisible(false);
	     if(crowJump.score==80&&crowMainActivity.bouns2==0)
	     {
	    	ach2.setVisible(true);
	     }
	     if(crowJump.score>=81)
	    	 ach2.setVisible(false);
	     paodan();
	     score();
	     stage.act();
	      stage.draw();    
	}
	public void score()
	{
	//	System.out.println("flag="+flag1);
		if(paodanL1.getX()+100>=crowball.LBorder-64&&temp2==false&&flag1==1)
		{
			temp=false;
			System.out.println(crowJump.score);
			System.out.println("碰上了L1");
			temp2=true;
			crowJump.score=crowJump.score-1;
			System.out.println(crowJump.score);
			paodanL1.removeAction(moveto);
			//paodanL1.setPosition(-200, -100);
			System.out.println("碰上了L1");
			float x=paodanL1.getX()-20;
			float y=paodanL1.getY();
			
			SizeToAction sta=Actions.sizeTo(0, 0,0f);
			 MoveToAction moveto=Actions.moveTo(x,y);
			 ParallelAction alpha = Actions.sequence(sta,moveto);
			paodanL1.addAction(alpha);
			
		}
		if(paodanL2.getX()+100>=crowball.LBorder-64&&temp3==false&&flag2==1)
		{
			temp1=false;
			System.out.println(crowJump.score);
			System.out.println("碰上了L2");
			temp3=true;
			crowJump.score=crowJump.score-1;
			System.out.println(crowJump.score);
			paodanL2.removeAction(moveto);
		//	paodanL2.setPosition(-200, -100);
			System.out.println("碰上了L2");
			float x=paodanL2.getX()-20;
			float y=paodanL2.getY();
			SizeToAction sta=Actions.sizeTo(0, 0,0f);
			 MoveToAction moveto=Actions.moveTo(x-20,y);
			 ParallelAction alpha = Actions.sequence(sta,moveto);
			paodanL2.addAction(alpha);
			
		}
		if(paodanR1.getX()<=crowball.RBorder+64+64&&temp4==false&&flag1==2)
		{
			temp=false;
			System.out.println(crowJump.score);
			System.out.println("碰上了R1");
			temp4=true;
			crowJump.score=crowJump.score-1;
			System.out.println(crowJump.score);
			paodanR1.removeAction(moveto);
			//paodanR1.setPosition(-200, -100);
			System.out.println("碰上了R1");
			float x=paodanR1.getX()-20;
			float y=paodanR1.getY();
			SizeToAction sta=Actions.sizeTo(0, 0,0f);
			 MoveToAction moveto=Actions.moveTo(x+20, y);
			 ParallelAction alpha = Actions.sequence(sta,moveto);
			paodanR1.addAction(alpha);
			
		}
		if(paodanR2.getX()<=crowball.RBorder+64+64&&temp5==false&&flag2==2)
		{
			temp1=false;
			System.out.println(crowJump.score);
			System.out.println("碰上了R2");
			temp5=true;
			crowJump.score=crowJump.score-1;
			System.out.println(crowJump.score);
			paodanR2.removeAction(moveto);
		//	paodanR2.setPosition(-200, -100);
			System.out.println("碰上了R2");
			float x=paodanR2.getX()-20;
			float y=paodanR2.getY();
			SizeToAction sta=Actions.sizeTo(0, 0,0f);
			 MoveToAction moveto=Actions.moveTo(x+20, y);
    		 ParallelAction alpha = Actions.sequence(sta,moveto);
			paodanR2.addAction(alpha);
			
		}
	}
	public void paodan()
	{
		if(crowJump.score%6==0||crowJump.score%4==0){
			System.out.println("score="+crowJump.score);
		}
		Random ran=new Random();
	    if(crowJump.score%4==1&&crowJump.score>=5)
	    {
	    	temp=false;
	    }
	    if(crowJump.score%6==1&&crowJump.score>=5)
	    {
	    	temp1=false;
	    }
	    if(crowJump.score%6==0&&temp1==false&&crowJump.score!=0)
		{	
			int r=ran.nextInt(2);
			System.out.println("六分r="+r);
			if(r==0)
			{
				fly.play();
				flag2=1;
				move2=paodanL2;
				temp1=true;
				temp3=false;
				Array<Action> list=paodanL2.getActions();
				for(Action a:list)
					paodanL2.removeAction(a);
				paodanL2.setSize(100,64);
				paodanR2.setSize(0, 0);
				flag3=ran.nextInt(3);
				while(flag3==flag5)
				{
					flag3=ran.nextInt(3);
				}
		       float y=crowball.DBorder+90*(2*flag3+1)-32;
		       
			   paodanL2.setPosition(-100, y);
		         moveto=Actions.moveTo(crowball.LBorder-95-64, y,2f);
		        paodanL2.addAction(moveto);
		        System.out.println("炮弹L2");
			}
			if(r==1)
			{
				fly.play();
				flag2=2;
				move2=paodanR2;
				temp1=true;
				temp5=false;
				Array<Action> list=paodanR2.getActions();
				for(Action a:list)
					paodanR2.removeAction(a);
				paodanR2.setSize(100,64);
				paodanL2.setSize(0, 0);
				flag4=ran.nextInt(3);
				while(flag4==flag6)
				{
					flag4=ran.nextInt(3);
				}
		       float y=crowball.DBorder+90*(2*flag4+1)-32;
			   paodanR2.setPosition(1300, y);
		         moveto=Actions.moveTo(crowball.RBorder+64-5+64, y,2f);
		        paodanR2.addAction(moveto);
		        System.out.println("炮弹R2");
			}
		}
		if(crowJump.score%4==0&&temp==false&&crowJump.score!=0)
		{	
			int r=ran.nextInt(2);
			System.out.println("四分r="+r);
			if(r==0)
			{
				fly.play();
				flag1=1;
				move1=paodanL1;
				temp=true;
				temp2=false;
				Array<Action> list=paodanL1.getActions();
				for(Action a:list)
					paodanL1.removeAction(a);
				paodanL1.setSize(100,64);
				paodanR1.setSize(0, 0);
				flag5=ran.nextInt(3);
				while(flag3==flag5)
				{
					flag5=ran.nextInt(3);
				}
		       float y=crowball.DBorder+90*(2*flag5+1)-32;
			   paodanL1.setPosition(-100, y);
		         moveto=Actions.moveTo(crowball.LBorder-95-64, y,2f);
		        paodanL1.addAction(moveto);
		        System.out.println("炮弹L1");
			}
			if(r==1)
			{
				fly.play();
				flag1=2;
				move1=paodanR1;
				temp=true;
				temp4=false;
				Array<Action> list=paodanR1.getActions();
				for(Action a:list)
					paodanR1.removeAction(a);
				paodanR1.setSize(100,64);
				paodanL1.setSize(0, 0);
				flag6=ran.nextInt(3);
				while(flag6==flag4)
				{
					flag6=ran.nextInt(3);
				}
		       float y=crowball.DBorder+90*(2*flag6+1)-32;
			   paodanR1.setPosition(1300, y);
		         moveto=Actions.moveTo(crowball.RBorder+64-5+64, y,2f);
		        paodanR1.addAction(moveto);
		        System.out.println("炮弹R1");
			}
		}
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
    	helpLabel.setPosition(400, 400);
    	if(second==100)
    	{
    	    display=1;	
    		helpR.setSize(128,64);
    		helpR.setPosition(crowball.RBorder+300, 100);
    		 MoveToAction moveto = Actions.moveTo(crowball.RBorder+300, 600,2f);
    		 MoveToAction moveto1=Actions.moveTo(crowball.RBorder+300, 100);
    		 SequenceAction alpha = Actions.sequence(moveto,moveto1);
    		// RepeatAction r=Actions.repeat(5,alpha);
    		 r=Actions.repeat(5,alpha);
    		 helpR.addAction(r);
    	}
    	if(second==300)
    	{
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
    	}
    	if(second==500)
    	{
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
    	}
    	if(second==700)
    	{
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
		System.out.println("在firstGame中资源被释放了");
		bf.dispose();
		ready.dispose();
		go.dispose();
		stage.dispose();
	
		for(int i=0;i<25;i++)
		crowJump.sound[i].dispose();
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
			changePosition();
		//Toast.makeText(null, "you", Toast.LENGTH_LONG).show();
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
		arg1=crowMainActivity.screenheight-arg1;
		if(move1==paodanL1)
		{
			System.out.println("是左侧");
			if(arg0>move1.getX()&&arg0<move1.getX()+100&&arg1>move1.getY()&&arg1<move1.getY()+64)
		{
			System.out.println("按到了");
			//paodanL1.setSize(0, 0);
			Array<Action> list=paodanL1.getActions();
			for(Action a:list)
				paodanL1.removeAction(a);
			paodanL1.removeAction(moveto);
			SizeToAction sta=Actions.sizeTo(0, 0,1f);
			paodanL1.addAction(sta);
			//paodanL1.setPosition(-200, -100);
		}
		}
		if(move1==paodanR1)
		{
			System.out.println("是右侧");
			if(arg0>move1.getX()&&arg0<move1.getX()+100&&arg1>move1.getY()&&arg1<move1.getY()+64)
		{
			System.out.println("按到了");
			//paodanR1.setSize(0, 0);
			Array<Action> list=paodanR1.getActions();
			for(Action a:list)
				paodanR1.removeAction(a);
			paodanR1.removeAction(moveto);
			SizeToAction sta=Actions.sizeTo(0, 0,1f);
			paodanR1.addAction(sta);
			//paodanR1.setPosition(-200, -100);
		}
		}
		if(move2==paodanL2)
		{
			System.out.println("是左侧");
			if(arg0>move2.getX()&&arg0<move2.getX()+100&&arg1>move2.getY()&&arg1<move2.getY()+64)
		{
			System.out.println("按到了");
			//paodanL1.setSize(0, 0);
			Array<Action> list=paodanL2.getActions();
			for(Action a:list)
				paodanL2.removeAction(a);
			//paodanL2.removeAction(moveto);
			SizeToAction sta=Actions.sizeTo(0, 0,1f);
			paodanL2.addAction(sta);
			//paodanL2.setPosition(-200, -100);
		}
		}
		if(move2==paodanR2)
		{
			System.out.println("是右侧");
			if(arg0>move2.getX()&&arg0<move2.getX()+100&&arg1>move2.getY()&&arg1<move2.getY()+64)
		{
			System.out.println("按到了");
			//paodanR1.setSize(0, 0);
			Array<Action> list=paodanR2.getActions();
			for(Action a:list)
				paodanR2.removeAction(a);
			//paodanR2.removeAction(moveto);
			SizeToAction sta=Actions.sizeTo(0, 0,1f);
			paodanR2.addAction(sta);
			//paodanR2.setPosition(-200, -100);
		}
		}
		return true;
	}

	@Override
	public boolean touchDown(float arg0, float arg1, int arg2, int arg3) //arg0代表x坐标，arg1代表y坐标
	{
		// TODO Auto-generated method stub
		System.out.println("被按下了");
		System.out.println("arg0="+arg0+"arg1="+arg1+"arg2="+arg2+"arg3="+arg3);
		if(arg0<crowMainActivity.screenwidth/2&&arg1>crowMainActivity.screenheight/2)
			area=0;
		else if(arg0>crowMainActivity.screenwidth/2&&arg1>crowMainActivity.screenheight/2)
			area=1;
		else if(arg0>crowMainActivity.screenwidth/2&&arg1<crowMainActivity.screenheight/2)
			area=2;
		else if(arg0<crowMainActivity.screenwidth/2&&arg1<crowMainActivity.screenheight/2)
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
				  MoveToAction moveto = Actions.moveTo(crowJump.right[2].x+64*crowMainActivity.w, crowJump.right[2].y-48*crowMainActivity.h, 0.1f);
				  crowball.right2.addAction(moveto);
			}
			else if(ifexist[3]==1&&ifexist[4]==0&&ifexist[5]==1)
			{
				ifexist[4]=1;
				ifexist[5]=0;
				  MoveToAction moveto = Actions.moveTo(crowJump.right[1].x+64*crowMainActivity.w, crowJump.right[1].y-48*crowMainActivity.h, 0.1f);
				  crowball.right1.addAction(moveto);
			}
		 }
		 if(fling==3)
		 {
			 if(ifexist[3]==1&&ifexist[4]==0&&ifexist[5]==1)
			 {
				 ifexist[3]=0;
					ifexist[4]=1;
					  MoveToAction moveto = Actions.moveTo(crowJump.right[1].x+64*crowMainActivity.w, crowJump.right[1].y-48*crowMainActivity.h, 0.05f);
					  crowball.right2.addAction(moveto);
			 }
			 else if(ifexist[3]==1&&ifexist[4]==1&&ifexist[5]==0)
			 {
				 ifexist[4]=0;
					ifexist[5]=1;
					  MoveToAction moveto = Actions.moveTo(crowJump.right[0].x+64*crowMainActivity.w, crowJump.right[0].y-48*crowMainActivity.h, 0.05f);
					crowball.right1.addAction(moveto);
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
				  MoveToAction moveto = Actions.moveTo(crowJump.left[2].x-64*crowMainActivity.w, crowJump.left[2].y-48*crowMainActivity.h, 0.05f);
				  crowball.left2.addAction(moveto);
			}
			else if(ifexist[11]==1&&ifexist[10]==0&&ifexist[9]==1)
			{
				ifexist[10]=1;
				ifexist[9]=0;
				  MoveToAction moveto = Actions.moveTo(crowJump.left[1].x-64*crowMainActivity.w, crowJump.left[1].y-48*crowMainActivity.h, 0.05f);
				  crowball.left1.addAction(moveto);
			}
		 }
		 if(fling==3)
		 {
			 if(ifexist[11]==1&&ifexist[10]==0&&ifexist[9]==1)
			 {
				 ifexist[11]=0;
					ifexist[10]=1;
					  MoveToAction moveto = Actions.moveTo(crowJump.left[1].x-64*crowMainActivity.w, crowJump.left[1].y-48*crowMainActivity.h, 0.05f);
					  crowball.left2.addAction(moveto);
			 }
			 else if(ifexist[11]==1&&ifexist[10]==1&&ifexist[9]==0)
			 {
				 ifexist[10]=0;
					ifexist[9]=1;
					  MoveToAction moveto = Actions.moveTo(crowJump.left[0].x-64*crowMainActivity.w, crowJump.left[0].y-48*crowMainActivity.h, 0.05f);
					  crowball.left1.addAction(moveto);
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
				  MoveToAction moveto = Actions.moveTo(crowJump.up[2].x-48*crowMainActivity.w, crowJump.up[2].y+64*crowMainActivity.h, 0.05f);
				  crowball.up2.addAction(moveto);
			}
			else if(ifexist[0]==1&&ifexist[1]==0&&ifexist[2]==1)
			{
				ifexist[1]=1;
				ifexist[0]=0;
				  MoveToAction moveto = Actions.moveTo(crowJump.up[1].x-48*crowMainActivity.w, crowJump.up[1].y+64*crowMainActivity.h, 0.05f);
				  crowball.up1.addAction(moveto);
			}
		 }
		 if(fling==1)
		 {
			 if(ifexist[0]==0&&ifexist[1]==1&&ifexist[2]==1)
			 {
				 ifexist[1]=0;
					ifexist[0]=1;
					  MoveToAction moveto = Actions.moveTo(crowJump.up[0].x-48*crowMainActivity.w, crowJump.up[0].y+64*crowMainActivity.h, 0.05f);
					  crowball.up1.addAction(moveto);
			 }
			 else if(ifexist[0]==1&&ifexist[1]==0&&ifexist[2]==1)
			 {
				 ifexist[1]=1;
					ifexist[2]=0;
					  MoveToAction moveto = Actions.moveTo(crowJump.up[1].x-48*crowMainActivity.w, crowJump.up[1].y+64*crowMainActivity.h, 0.05f);
					  crowball.up2.addAction(moveto);
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
				  MoveToAction moveto = Actions.moveTo(crowJump.down[2].x-48*crowMainActivity.w, crowJump.down[2].y-64*crowMainActivity.h,0.05f);
				  crowball.down2.addAction(moveto);
			}
			else if(ifexist[8]==1&&ifexist[7]==0&&ifexist[6]==1)
			{
				ifexist[7]=1;
				ifexist[8]=0;
				  MoveToAction moveto = Actions.moveTo(crowJump.down[1].x-48*crowMainActivity.w, crowJump.down[1].y-64*crowMainActivity.h, 0.05f);
				  crowball.down1.addAction(moveto);
			}
		 }
		 if(fling==1)
		 {
			 if(ifexist[8]==0&&ifexist[7]==1&&ifexist[6]==1)
			 {
				 ifexist[7]=0;
					ifexist[8]=1;
					  MoveToAction moveto = Actions.moveTo(crowJump.down[0].x-48*crowMainActivity.w, crowJump.down[0].y-64*crowMainActivity.h, 0.05f);
					  crowball.down1.addAction(moveto);
			 }
			 else if(ifexist[8]==1&&ifexist[7]==0&&ifexist[6]==1)
			 {
				 ifexist[7]=1;
					ifexist[6]=0;
					MoveToAction moveto = Actions.moveTo(crowJump.down[1].x-48*crowMainActivity.w, crowJump.down[1].y-64*crowMainActivity.h, 0.05f);
					crowball.down2.addAction(moveto);
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
		System.out.println("拖动");
		System.out.println("arg0="+arg0+"arg1="+arg1+"arg2="+arg2);
		//changePosition();
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
