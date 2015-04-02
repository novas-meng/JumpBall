package com.example.jumpball;

import java.util.HashMap;
import java.util.Random;

import android.app.Activity;
import android.content.Context;
import android.graphics.PointF;
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
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.ui.Window.WindowStyle;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;


public class ghostFirstGame implements Screen,InputProcessor,GestureListener
{

	Image ach1;
	Ballforghost Ballforghost;
	Image image;
	Image pump;
	int count=0;
	Image help1;
	static boolean help1exist=true,begin=false;
	boolean hasreset1=false;
	boolean hasreset2=false;
	boolean hasreset3=false;
	boolean hasreset4=false;
	boolean hasreset5=false;
	boolean hasreset6=false;
	boolean hasreset7=false;
	boolean hasreset8=false;
	 Image game_board;
	 Image score_board;
	 int i=0;
	 static int[] index=new int[10];
	 static int speed=1;
	 PointF base=new PointF();
	static  int gap=1;
	static Stage stage;
	boolean hasPump=false;
	InputMultiplexer input;
	  FreeTypeFontGenerator Generator;
	   FreeTypeBitmapFontData fontData;
	ghostGameSwitch gs;
	BitmapFont bf;
	BitmapFont Font;
	Activity main;
	TextureRegion texture;
	static int start=0;
	static int playMusic=0;
	//area代表将屏幕分为四块,fling代表滑动方向,0->  1<-  2 上 3 下 
	static int fling=0;
	static int area=0;
	static int[] ifexist=new int[12];
	boolean hasdialog=false;
	static boolean pumpexist=false;
	Sound ready,go;
	 LabelStyle labelStyle;
	 Label label;
	 Label readyLabel,goLabel,bounsLabel;
	//存在方块值为1，不存在值为0
	public ghostFirstGame(ghostGameSwitch ghostGameSwitch)
	{
		this.gs=ghostGameSwitch;
	}
	
	public ghostFirstGame(Activity main) {
		// TODO Auto-generated constructor stub
		super();
		this.main=main;
	}
	@Override
	public void show() {
		// TODO Auto-generated method stub
		Texture.setEnforcePotImages(false);
		
		 for(int i=0;i<12;i++)
		 {
				 ifexist[i]=1;
		 }
		 ifexist[2]=0;
		 ifexist[3]=0;
		 ifexist[6]=0;
		 ifexist[11]=0;
		 Texture.setEnforcePotImages(false);
		 ach1=new Image(new Texture(Gdx.files.internal("ach/ach_7.png")));
			
		   ach1.setVisible(false);
	     
	       ach1.setSize(300*ghostMainActivity.w, 130*ghostMainActivity.h);
	     
	       ach1.setPosition(925*ghostMainActivity.w, 530*ghostMainActivity.w);
		 help1=new Image(new Texture(Gdx.files.internal("crow/help.png")));
	
		// help1.setSize(960*ghostMainActivity.w,540*ghostMainActivity.h);
		// help1.setPosition(160*ghostMainActivity.w, 90*ghostMainActivity.h);
	help1.setSize(ghostMainActivity.screenwidth,ghostMainActivity.screenheight);
		 Generator=new FreeTypeFontGenerator(Gdx.files.internal("girl.TTF"));
			fontData=Generator.generateData(75, Generator.DEFAULT_CHARS+"亲当你看到这个页时说明刚才疏忽了肯定不是实力按一下面的钮，再来次吧", false);
		    Font=new BitmapFont(fontData,fontData.getTextureRegion(),false);
		    Font.setColor(Color.BLUE);
		    labelStyle = new LabelStyle(Font, Color.BLACK); 
		 ready=Gdx.audio.newSound(Gdx.files.internal("ready.wav"));
		 go=Gdx.audio.newSound(Gdx.files.internal("go.wav"));
		 pump=new Image(new Texture(Gdx.files.internal("ghost/ghost_pump.png")));
		 pump.setSize(0,0);
		 image = new Image(new Texture(Gdx.files.internal("ghost/ghost_game_background.png")));
		    //  image.setPosition(0, 0);
	       image.setSize(ghostMainActivity.screenwidth, ghostMainActivity.screenheight);
	       game_board=new Image(new Texture(Gdx.files.internal("ghost/ghost_game_board.png")));
	       game_board.setSize(944*MainActivity.w, 720*MainActivity.h);
	       game_board.setPosition(120*MainActivity.w, 10*MainActivity.h);
	       score_board=new Image(new Texture(Gdx.files.internal("ghost/ghost_score_board.png")));
	       score_board.setSize(220*ghostMainActivity.w, 370*ghostMainActivity.h);
	       score_board.setPosition(4*ghostMainActivity.w, 10*ghostMainActivity.h);
	       stage = new Stage(ghostMainActivity.screenwidth,ghostMainActivity.screenheight,false);
	       //Gdx.input.setInputProcessor(stage);
           Ballforghost=new Ballforghost();
           stage.addActor(image);
           stage.addActor(game_board);
           stage.addActor(score_board);
           bf=new BitmapFont();
           bf.setScale(1.5f);
           label = new Label("", labelStyle); 
           readyLabel=new Label("",labelStyle);
           goLabel=new Label("",labelStyle);
           bounsLabel=new Label("",labelStyle);
           ghostJump jump=new ghostJump();
	     //  stage.addActor(image);
	       stage.addActor(Ballforghost);
	       stage.addActor(Ballforghost.down1);
	       stage.addActor(Ballforghost.down2);
	      // stage.addActor(Ballforghost.down3);
	       stage.addActor(Ballforghost.left1);
	       stage.addActor(Ballforghost.left2);
	      // stage.addActor(Ballforghost.left3);
	       stage.addActor(Ballforghost.up1);
	       stage.addActor(Ballforghost.up2);
	       stage.addActor(pump);
	      // stage.addActor(Ballforghost.up3);
	      
		       stage.addActor(Ballforghost.right1);
		       stage.addActor(Ballforghost.right2);
		      // stage.addActor(Ballforghost.right3);
	       stage.addActor(ghostJump.d);
	       
	       stage.addActor(jump);
	       stage.addActor(label);
	       stage.addActor(readyLabel);
	       stage.addActor(goLabel);
	       stage.addActor(jump.mask);
	       stage.addActor(help1);
	       stage.addActor(ach1);
	      // stage.addActor(jump.mask_down);
	      // stage.addActor(jump.mask_left);
	      // stage.addActor(jump.mask_right);
	     //  stage.addActor(jump.mask_up);
	     //  stage.addActor(new blockcolor());
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
		ghostJump.sound[i].dispose();
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
	       if(ghostJump.score==30&&ghostMainActivity.bouns1==0)
		     {
		    	ach1.setVisible(true);
		     }
		     if(ghostJump.score>=31)
		    	 ach1.setVisible(false);
	         label.setText(ghostJump.score+"");
		      label.setPosition(90*ghostMainActivity.w, 120*ghostMainActivity.h);
		      readyLabel.setText("");
		      if(begin==false)
		      {
		    	   help();
		      }
		     
		      if(begin==true){
	       if(playMusic==0)
	       {
	    	   ready.play();
	       }
	       if(playMusic>=0&&playMusic<60)
	       {
	    	   readyLabel.setText("ready");
	    	   readyLabel.setPosition(500*ghostMainActivity.w, 500*ghostMainActivity.h);
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
	    	   readyLabel.setPosition(550*ghostMainActivity.w, 500*ghostMainActivity.h);
	       }
	       if(playMusic==100)
	       {
	    	   playMusic=-1;
	    	   
	       }
		      }
	       if(ghostJump.score>=30&&count<60&&ghostMainActivity.bouns1==0)
		     {
		    	 count++;
		    	 bounsLabel.setText("初出茅庐");
		    	 bounsLabel.setPosition(1000*ghostMainActivity.w, 600*ghostMainActivity.h);
		     }
		     if(count==60)
		     {
		    	 bounsLabel.setText("");
		    	 count=0;
		     }
	     if(ghostJump.fail==1||pump.getWidth()>400)
	     {
	    	 System.out.println("碰撞错误");
	    	 ghostJump.background.pause();
	    	 gs.setScreen(gs.cf);
	     }
	    
	     if(ghostJump.score==10||ghostJump.score==15||ghostJump.score==20||ghostJump.score==25||ghostJump.score==30||ghostJump.score==40||ghostJump.score==50||ghostJump.score==60)
	     {
	    	 if(ghostJump.score==10&&hasreset1==false)
	    	 {
	    		 i=0;
	    		 hasreset1=true;
	    		 pumpexist=true;
	    		 speed=1;
	    	 }
	    	 if(ghostJump.score==15&&hasreset2==false)
	    	 {
	    		 i=1;
	    		 hasreset2=true;
	    		 pumpexist=true;
	    		 speed=1;
	    	 }
	    	 if(ghostJump.score==20&&hasreset3==false)
	    	 {
	    		 i=2;
	    		 hasreset3=true;
	    		 pumpexist=true;
	    		 speed=1;
	    	 }
	    	 if(ghostJump.score==25&&hasreset4==false)
	    	 {
	    		 i=3;
	    		 hasreset4=true;
	    		 pumpexist=true;
	    		 speed=1;
	    	 }
	    	 if(ghostJump.score==30&&hasreset5==false)
	    	 {
	    		 i=4;
	    		 hasreset5=true;
	    		 pumpexist=true;
	    		 speed=1;
	    	 }
	    	 if(ghostJump.score==40&&hasreset6==false)
	    	 {
	    		 i=5;
	    		 hasreset6=true;
	    		 pumpexist=true;
	    		 speed=1;
	    	 }
	    	 if(ghostJump.score==50&&hasreset7==false)
	    	 {
	    		 i=6;
	    		 hasreset7=true;
	    		 pumpexist=true;
	    		 speed=1;
	    	 }
	    	 if(ghostJump.score==60&&hasreset8==false)
	    	 {
	    		 i=7;
	    		 hasreset8=true;
	    		 pumpexist=true;
	    		 speed=1;
	    	 }
	    	
	     }
	     if(pumpexist==true)
	     {
	    	 if(index[i]==0)
	    	 {
	    		 Random ran=new Random();
	    		 int m=ran.nextInt(300);
	    		  base.x=Ballforghost.LBorder+100*ghostMainActivity.w+m*ghostMainActivity.w;
	    	 base.y=Ballforghost.DBorder+100*ghostMainActivity.h+m*ghostMainActivity.h;
	    	 index[i]=1;
	    	 }
	    	// hasPump=true;
	    	// if(gap>0)
	    	// {
	    	  gap=gap+speed;
	    	  if(gap<0)
					pumpexist=false;
	    	  pump.setSize(2*gap,2*gap);
	    	  pump.setPosition(base.x-gap,base.y-gap);
	    	// }
	    	
	     }
	     
	    stage.act();
	    stage.draw();
	   
	}
	public void help()
	 {
		 if(help1exist==false)
		 {
			 System.out.println("help1");
			 help1.setVisible(false);
			 help1.clear();
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
		//System.out.println("在pan中arg0="+arg0+"arg1="+arg1+"arg2="+arg2+"arg3="+arg3);
		return false;
	}

	@Override
	public boolean panStop(float arg0, float arg1, int arg2, int arg3) {
		// TODO Auto-generated method stub
		//System.out.println("在pan中arg0="+arg0+"arg1="+arg1+"arg2="+arg2+"arg3="+arg3);
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
		arg1=ghostMainActivity.screenheight-arg1;
		System.out.println("在tab中arg0="+arg0+"arg1="+arg1+"arg2="+arg2+"arg3="+arg3);
		if(pumpexist==true)
		{
			System.out.println("base.x="+base.x+"base.y="+base.y+"gap="+gap);
			
			if(arg0>base.x-gap-50&&arg0<base.x+gap+50&&arg1>base.y-gap-50&&arg1<base.y+gap+50)
			{
				System.out.println("打中了");
				speed=-1;
				
			}
		}
		return true;
	}

	@Override
	public boolean touchDown(float arg0, float arg1, int arg2, int arg3) //arg0代表x坐标，arg1代表y坐标
	{
		// TODO Auto-generated method stub
		//System.out.println("被按下了");
		if(help1exist==true)
		{
			help1exist=false;
		}
		System.out.println("在touchdown中arg0="+arg0+"arg1="+arg1+"arg2="+arg2+"arg3="+arg3);
		if(arg0<ghostMainActivity.screenwidth/2&&arg1>ghostMainActivity.screenheight/2)
			area=0;
		else if(arg0>ghostMainActivity.screenwidth/2&&arg1>ghostMainActivity.screenheight/2)
			area=1;
		else if(arg0>ghostMainActivity.screenwidth/2&&arg1<ghostMainActivity.screenheight/2)
			area=2;
		else if(arg0<ghostMainActivity.screenwidth/2&&arg1<ghostMainActivity.screenheight/2)
			area=3;
		return true;
	}
 public void changePosition()//滑动时改变方块位置
 {
	 //System.out.println("area="+area+"fling="+fling);
	 if(area==1||area==2)
	 {
		 if(fling==2)
		 {
			if(ifexist[3]==0&&ifexist[4]==1)
			{
				ifexist[3]=1;
				ifexist[4]=0;
				  MoveToAction moveto = Actions.moveTo(ghostJump.right[2].x+64*MainActivity.w, ghostJump.right[2].y-48*MainActivity.h, 0.1f);
				  Ballforghost.right2.addAction(moveto);
			}
			else if(ifexist[3]==1&&ifexist[4]==0&&ifexist[5]==1)
			{
				ifexist[4]=1;
				ifexist[5]=0;
				  MoveToAction moveto = Actions.moveTo(ghostJump.right[1].x+64*MainActivity.w, ghostJump.right[1].y-48*MainActivity.h, 0.1f);
				Ballforghost.right1.addAction(moveto);
			}
		 }
		 if(fling==3)
		 {
			 if(ifexist[3]==1&&ifexist[4]==0&&ifexist[5]==1)
			 {
				 ifexist[3]=0;
					ifexist[4]=1;
					  MoveToAction moveto = Actions.moveTo(ghostJump.right[1].x+64*MainActivity.w, ghostJump.right[1].y-48*MainActivity.h, 0.05f);
					Ballforghost.right2.addAction(moveto);
			 }
			 else if(ifexist[3]==1&&ifexist[4]==1&&ifexist[5]==0)
			 {
				 ifexist[4]=0;
					ifexist[5]=1;
					  MoveToAction moveto = Actions.moveTo(ghostJump.right[0].x+64*MainActivity.w, ghostJump.right[0].y-48*MainActivity.h, 0.05f);
					Ballforghost.right1.addAction(moveto);
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
				  MoveToAction moveto = Actions.moveTo(ghostJump.left[2].x-64*MainActivity.w, ghostJump.left[2].y-48*MainActivity.h, 0.05f);
				  Ballforghost.left2.addAction(moveto);
			}
			else if(ifexist[11]==1&&ifexist[10]==0&&ifexist[9]==1)
			{
				ifexist[10]=1;
				ifexist[9]=0;
				  MoveToAction moveto = Actions.moveTo(ghostJump.left[1].x-64*MainActivity.w, ghostJump.left[1].y-48*MainActivity.h, 0.05f);
				Ballforghost.left1.addAction(moveto);
			}
		 }
		 if(fling==3)
		 {
			 if(ifexist[11]==1&&ifexist[10]==0&&ifexist[9]==1)
			 {
				 ifexist[11]=0;
					ifexist[10]=1;
					  MoveToAction moveto = Actions.moveTo(ghostJump.left[1].x-64*MainActivity.w, ghostJump.left[1].y-48*MainActivity.h, 0.05f);
					Ballforghost.left2.addAction(moveto);
			 }
			 else if(ifexist[11]==1&&ifexist[10]==1&&ifexist[9]==0)
			 {
				 ifexist[10]=0;
					ifexist[9]=1;
					  MoveToAction moveto = Actions.moveTo(ghostJump.left[0].x-64*MainActivity.w, ghostJump.left[0].y-48*MainActivity.h, 0.05f);
					Ballforghost.left1.addAction(moveto);
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
				  MoveToAction moveto = Actions.moveTo(ghostJump.up[2].x-48*MainActivity.w, ghostJump.up[2].y+64*MainActivity.h, 0.05f);
				  Ballforghost.up2.addAction(moveto);
			}
			else if(ifexist[0]==1&&ifexist[1]==0&&ifexist[2]==1)
			{
				ifexist[1]=1;
				ifexist[0]=0;
				  MoveToAction moveto = Actions.moveTo(ghostJump.up[1].x-48*MainActivity.w, ghostJump.up[1].y+64*MainActivity.h, 0.05f);
				Ballforghost.up1.addAction(moveto);
			}
		 }
		 if(fling==1)
		 {
			 if(ifexist[0]==0&&ifexist[1]==1&&ifexist[2]==1)
			 {
				 ifexist[1]=0;
					ifexist[0]=1;
					  MoveToAction moveto = Actions.moveTo(ghostJump.up[0].x-48*MainActivity.w, ghostJump.up[0].y+64*MainActivity.h, 0.05f);
					Ballforghost.up1.addAction(moveto);
			 }
			 else if(ifexist[0]==1&&ifexist[1]==0&&ifexist[2]==1)
			 {
				 ifexist[1]=1;
					ifexist[2]=0;
					  MoveToAction moveto = Actions.moveTo(ghostJump.up[1].x-48*MainActivity.w, ghostJump.up[1].y+64*MainActivity.h, 0.05f);
					Ballforghost.up2.addAction(moveto);
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
				  MoveToAction moveto = Actions.moveTo(ghostJump.down[2].x-48*MainActivity.w, ghostJump.down[2].y-64*MainActivity.h,0.05f);
				  Ballforghost.down2.addAction(moveto);
			}
			else if(ifexist[8]==1&&ifexist[7]==0&&ifexist[6]==1)
			{
				ifexist[7]=1;
				ifexist[8]=0;
				  MoveToAction moveto = Actions.moveTo(ghostJump.down[1].x-48*MainActivity.w, ghostJump.down[1].y-64*MainActivity.h, 0.05f);
				Ballforghost.down1.addAction(moveto);
			}
		 }
		 if(fling==1)
		 {
			 if(ifexist[8]==0&&ifexist[7]==1&&ifexist[6]==1)
			 {
				 ifexist[7]=0;
					ifexist[8]=1;
					  MoveToAction moveto = Actions.moveTo(ghostJump.down[0].x-48*MainActivity.w,ghostJump.down[0].y-64*MainActivity.h, 0.05f);
					Ballforghost.down1.addAction(moveto);
			 }
			 else if(ifexist[8]==1&&ifexist[7]==0&&ifexist[6]==1)
			 {
				 ifexist[7]=1;
					ifexist[6]=0;
					  MoveToAction moveto = Actions.moveTo(ghostJump.down[1].x-48*MainActivity.w, ghostJump.down[1].y-64*MainActivity.h, 0.05f);
					Ballforghost.down2.addAction(moveto);
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
		System.out.println("在touchup中arg0="+arg0+"arg1="+arg1+"arg2="+arg2+"arg3="+arg3);
		return false;
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}
}
