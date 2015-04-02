package com.example.jumpball;

import java.util.HashMap;

import android.app.Activity;
import android.content.Context;
import android.hardware.SensorManager;
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
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.ui.Window.WindowStyle;
import com.badlogic.gdx.scenes.scene2d.utils.DragListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;


public class FirstGame implements Screen,InputProcessor,GestureListener
{
	Image ach1;
    SpriteBatch batch;
	static Ball ball;
	float crow_x,crow_y;
	Image help1,help2,help3;
	static boolean help1exist=true,help2exist=true,help3exist=true,begin=false;
	boolean temp1=false,temp2=false;
	int count=0;
	Image image;
	Image ball1;
	Image ball2;
	Sound crowfly1,crowfly2,crowfly3;
	static Actor[] actorGroup=new Actor[12];
	 Image game_board;
	 Image score_board;
	 TextureRegion currentFrame;
	 //判断是否播放小球撞到砖块上
     static int playAnimation_split=0;
    static  int block_count;
     //判断是否已经确定乌鸦掉落的开始点坐标
    static  boolean hasXY=false;
	   float stateTime;
	   int down_speed;
	static Stage stage;
	InputMultiplexer input;
	  FreeTypeFontGenerator Generator;
	   FreeTypeBitmapFontData fontData;
	GameSwitch gs;
	static BitmapFont bf;
	BitmapFont Font;
	Activity main;
	TextureRegion texture;
	static int start=0;
	BitmapFont windowbf;
	static int playMusic=0;
	//记录在左方右方相应位置上是哪个滑块
	static int left1=1,left2=2,left3,right1=1,right2=2,right3;
	//area代表将屏幕分为四块,fling代表滑动方向,0->  1<-  2 上 3 下 
	static int fling=0;
	static int area=0;
	static int[] ifexist=new int[12];
	boolean hasdialog=false;
	static Sound ready;
	static Sound go;
	Image crow,crow2;
	 LabelStyle labelStyle;
	 Label label;
	 Label readyLabel,goLabel,bounsLabel;
	//存在方块值为1，不存在值为0
	 SensorManager manager;
	 static boolean query=true;
	public FirstGame(GameSwitch gs)
	{
		this.gs=gs;
	}
	
	public FirstGame(Activity main) {
		// TODO Auto-generated constructor stub
		super();
		this.main=main;
	}
	@Override
	public void show() {
		// TODO Auto-generated method stub
		stateTime=0f;
		batch=new SpriteBatch();
		 for(int i=0;i<12;i++)
		 {
				 ifexist[i]=1;
		 }
		 ifexist[2]=0;
		 ifexist[3]=0;
		 ifexist[6]=0;
		 ifexist[11]=0;
		 Texture.setEnforcePotImages(false);
		 ach1=new Image(new Texture(Gdx.files.internal("ach/ach_6.png")));
		
		   ach1.setVisible(false);
	     
	       ach1.setSize(300*MainActivity.w, 130*MainActivity.h);
	     
	       ach1.setPosition(925*MainActivity.w, 530*MainActivity.h);
	     
		 crow=new Image(new Texture(Gdx.files.internal("crow/crow_01.png")));
		 crow2=new Image(new Texture(Gdx.files.internal("crow/crow_02.png")));
		 help1=new Image(new Texture(Gdx.files.internal("crow/help1.png")));
		 help2=new Image(new Texture(Gdx.files.internal("crow/help2.png")));
		 help3=new Image(new Texture(Gdx.files.internal("crow/help3.png")));
		// help1.setSize(960*MainActivity.w,540*MainActivity.h);
		// help1.setPosition(160*MainActivity.w, 90*MainActivity.h);
		// help2.setSize(960*MainActivity.w,540*MainActivity.h);
		// help2.setPosition(160*MainActivity.w, 90*MainActivity.h);
		 help1.setSize(MainActivity.screenwidth,MainActivity.screenheight);
		 help2.setSize(MainActivity.screenwidth,MainActivity.screenheight);
		 help3.setSize(MainActivity.screenwidth,MainActivity.screenheight);
		 crow2.setPosition(-220, -200);
		
		 Generator=new FreeTypeFontGenerator(Gdx.files.internal("girl.TTF"));
			fontData=Generator.generateData(75, Generator.DEFAULT_CHARS+"亲当你看到这个页时说明刚才疏忽了肯定不是实力按一下面的钮，再来次吧", false);
		    Font=new BitmapFont(fontData,fontData.getTextureRegion(),false);
		    Font.setColor(Color.BLUE);
		     labelStyle = new LabelStyle(Font, Color.BLACK); 
		 ready=Gdx.audio.newSound(Gdx.files.internal("ready.wav"));
		 go=Gdx.audio.newSound(Gdx.files.internal("go.wav"));
		 windowbf=new BitmapFont();
		 image = new Image(new Texture(Gdx.files.internal("crow/crow_background.png")));
		 ball1 = new Image(new Texture(Gdx.files.internal("crow/crow_ball.png")));
		 ball1.setSize(64*MainActivity.w, 42*MainActivity.h);
		 ball2 = new Image(new Texture(Gdx.files.internal("crow/crow_ball.png")));
		 ball2.setSize(42*MainActivity.w, 64*MainActivity.h);
	    //  image.setPosition(0, 0);
       image.setSize(MainActivity.screenwidth, MainActivity.screenheight);
       game_board=new Image(new Texture(Gdx.files.internal("crow/crow_board.png")));
       game_board.setSize(490*MainActivity.w, 490*MainActivity.h);
       game_board.setPosition(425*MainActivity.w, 145*MainActivity.h);
       score_board=new Image(new Texture(Gdx.files.internal("crow/crow_Score.png")));
       score_board.setSize(250*MainActivity.w, 220*MainActivity.h);
       score_board.setPosition(970*MainActivity.w, 330*MainActivity.h);
	       stage = new Stage(MainActivity.screenwidth,MainActivity.screenheight,false);
	       //Gdx.input.setInputProcessor(stage);
           ball=new Ball();
           actorGroup[0]=ball.up1;
  		 actorGroup[1]=ball.up2;
  		 actorGroup[2]=ball.up3;
  		 actorGroup[3]=ball.right3;
  		 actorGroup[4]=ball.right2;
  		 actorGroup[5]=ball.right1;
  		 actorGroup[6]=ball.down3;
  		 actorGroup[7]=ball.down2;
  		 actorGroup[8]=ball.down1;
  		 actorGroup[9]=ball.left1;
  		 actorGroup[10]=ball.left2;
  		 actorGroup[11]=ball.left3;
           stage.addActor(image);
           stage.addActor(game_board);
           stage.addActor(score_board);
           bf=new BitmapFont();
           bf.setScale(1.5f);
            label = new Label("", labelStyle); 
            readyLabel=new Label("",labelStyle);
            goLabel=new Label("",labelStyle);
            bounsLabel=new Label("",labelStyle);
           Jump jump=new Jump();
	       stage.addActor(ball);
	       stage.addActor(ball.down1);
	       stage.addActor(ball.down2);
	      stage.addActor(ball.down3);  
	       stage.addActor(ball.left1);
	       stage.addActor(ball.left2);
	       stage.addActor(ball.left3);
	       stage.addActor(ball.up1);
	       stage.addActor(ball.up2);
	       stage.addActor(ball.up3);
		       stage.addActor(ball.right1);
		       stage.addActor(ball.right2);
		      stage.addActor(ball.right3);
		      ball.down3.setVisible(false);
		      ball.up3.setVisible(false);
		      ball.left3.setVisible(false);
		      ball.right3.setVisible(false);
	    // addDragListener();
	      AddDrag.add();
	      AddDrag.initPoint();
	       stage.addActor(jump.d);
	       stage.addActor(jump);
	       stage.addActor(label);
	       stage.addActor(readyLabel);
	       stage.addActor(goLabel);
	       stage.addActor(Jump.crow);
	       stage.addActor(Jump.crow2);
	       stage.addActor(crow2);
	       stage.addActor(ball1);
	      stage.addActor(ball2);
	      stage.addActor(help3);
	      stage.addActor(help2);
	      stage.addActor(help1);
	      stage.addActor(ach1);
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
		Jump.sound[i].dispose();
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
	       if(Jump.score==30&&MainActivity.bouns1==0)
		     {
		    	ach1.setVisible(true);
		     }
		     if(Jump.score>=31)
		    	 ach1.setVisible(false);
	      label.setText(Jump.score+"");
	      label.setPosition(1060*MainActivity.w, 370*MainActivity.h);
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
	       if(Jump.score>=30&&count<60&&MainActivity.bouns1==0)
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
	     if(Jump.fail==1)
	     {
	    	
	    	 System.out.println("碰撞错误");
	    	 gs.setScreen(gs.f);
	     }
	    
	     if(Jump.crowTo==10&&query==true)
		    {
	    	// crowfly1.play();
	    	  block_count=10;
		    	Jump.ifCrowExist=false;
		    	playAnimation_split=1;
		    	 stateTime += Gdx.graphics.getDeltaTime()*0.1;

		         currentFrame = Jump.splitblock.getKeyFrame(stateTime, true);

		    	batch.begin();
		    	 batch.draw(currentFrame, Gdx.graphics.getWidth() / 2,
		                 Gdx.graphics.getHeight() / 2);
		         batch.end();
		    	//System.out.println("clear");
		    	//if(left2==2)
		    	//ball.left2.remove();
		    	//else
		    		//ball.left1.remove();
		         actorGroup[10].setVisible(false);
		    	Jump.crow2.remove();
		    	Jump.crow.remove();
		    	ifexist[10]=0;
		    	query=false;
		    	//ball.left2.clear();
		    }
		    if(Jump.crowTo==4&&query==true)
		    {
		    	 block_count=4;
		    	playAnimation_split=1;
		    	 stateTime += Gdx.graphics.getDeltaTime()*0.1;

		         currentFrame = Jump.splitblock.getKeyFrame(stateTime, true);

		    	batch.begin();
		    	 batch.draw(currentFrame, Gdx.graphics.getWidth() / 2,
		                 Gdx.graphics.getHeight() / 2);
		         batch.end();
		    	Jump.ifCrowExist=false;
		    	//System.out.println("clear");
		    	//if(right2==2)
		    	//ball.right2.remove();
		    	//else
		    		//ball.right1.remove();
		    	  actorGroup[4].setVisible(false);
		    	Jump.crow2.remove();
		    	Jump.crow.remove();
		    	ifexist[4]=0;
		    	query=false;
		    	//ball.left2.clear();
		    }
		    
		    
		    if(Jump.crowTo==11&&query==true)
		    {
		    	 block_count=11;
		    	playAnimation_split=1;
		    	 stateTime += Gdx.graphics.getDeltaTime()*0.1;

		         currentFrame = Jump.splitblock.getKeyFrame(stateTime, true);

		    	batch.begin();
		    	 batch.draw(currentFrame, Gdx.graphics.getWidth() / 2,
		                 Gdx.graphics.getHeight() / 2);
		         batch.end();
		    	Jump.ifCrowExist=false;
		    	//System.out.println("clear");
		    	///if(left3==2)
		    	//ball.left2.remove();
		    	//else
		    		//ball.left1.remove();
		    	  actorGroup[11].setVisible(false);
		    	Jump.crow2.remove();
		    	Jump.crow.remove();
		    	ifexist[11]=0;
		    	query=false;
		    	//ball.left2.clear();
		    }
		    if(Jump.crowTo==3&&query==true)
		    {
		    	 block_count=3;
		    	playAnimation_split=1;
		    	 stateTime += Gdx.graphics.getDeltaTime()*0.1;

		         currentFrame = Jump.splitblock.getKeyFrame(stateTime, true);

		    	batch.begin();
		    	 batch.draw(currentFrame, Gdx.graphics.getWidth() / 2,
		                 Gdx.graphics.getHeight() / 2);
		         batch.end();
		    	Jump.ifCrowExist=false;
		    	//System.out.println("clear");
		    	//if(right3==2)
		    	//ball.right2.remove();
		    	//else
		    		//ball.right1.remove();
		    	  actorGroup[3].setVisible(false);
		    	Jump.crow2.remove();
		    	Jump.crow.remove();
		    	ifexist[3]=0;
		    	query=false;
		    	//ball.left2.clear();
		    }
		    if(Jump.crowTo==9&&query==true)
		    {
		    	 block_count=9;
		    	playAnimation_split=1;
		    	 stateTime += Gdx.graphics.getDeltaTime()*0.1;

		         currentFrame = Jump.splitblock.getKeyFrame(stateTime, true);

		    	batch.begin();
		    	 batch.draw(currentFrame, Gdx.graphics.getWidth() / 2,
		                 Gdx.graphics.getHeight() / 2);
		         batch.end();
		    	Jump.ifCrowExist=false;
		    	//System.out.println("clear");
		    	//if(left1==2)
		    	//ball.left2.remove();
		    	//else
		    		//ball.left1.remove();
		    	  actorGroup[9].setVisible(false);
		    	Jump.crow2.remove();
		    	Jump.crow.remove();
		    	ifexist[9]=0;
		    	query=false;
		    	//ball.left2.clear();
		    }
		    if(Jump.crowTo==5&&query==true)
		    {
		    	 block_count=5;
		    	playAnimation_split=1;
		    	
		    	Jump.ifCrowExist=false;
		    	//System.out.println("clear");
		    	//if(right1==2)
		    	//ball.right2.remove();
		    	//else
		    		//ball.right1.remove();
		    	  actorGroup[5].setVisible(false);
		    	Jump.crow2.remove();
		    	Jump.crow.remove();
		    	ifexist[5]=0;
		    	query=false;
		    	//ball.left2.clear();
		    }
	    //判断小球是否和乌鸦相撞
	    if(Jump.CrowHitBall==1)
	    {
	    	Jump.d.remove();
	    	Jump.crow2.remove();
	    	Jump.crow.remove();
	    	Jump.fail=1;
	    }
	    //处理小球撞击两侧时压缩的动画
	    if(Jump.playAnimation==true&&Jump.first==1)
	    {
	    	 //ball.setVisible(false);
	    	Jump.d.setVisible(false);
	    	
	    	if(Jump.AnimationY<=Ball.DBorder+20||Jump.AnimationY>=Ball.UBorder-20)
	    	{
	    		ball1.setVisible(true);
	    		if(Jump.AnimationY>MainActivity.screenheight/2)
	    		ball1.setPosition(Jump.AnimationX, Jump.AnimationY+28*MainActivity.h);
	    		else
	    			ball1.setPosition(Jump.AnimationX, Jump.AnimationY);
	    	}
	    		
	    	else
	    	{
	    		ball2.setVisible(true);
	    		if(Jump.AnimationX>MainActivity.screenwidth/2)
	    		ball2.setPosition(Jump.AnimationX+28*MainActivity.w, Jump.AnimationY);
	    		else
	    			ball2.setPosition(Jump.AnimationX, Jump.AnimationY);
	    	}
	    	
	    }
	    else
	    {
	    	Jump.d.setVisible(true);
	    	ball1.setVisible(false);
	    	ball2.setVisible(false);
	    }
	    //乌鸦掉下去的动画
	    if(playAnimation_split==1)
	    {	
	    	//Jump.crowTo=-1;
	    	if(block_count==9&&hasXY==false)
	    {
	    		hasXY=true;
	    	crow_x=Ball.LBorder-192*MainActivity.w;
	    	crow_y=Ball.DBorder+58*MainActivity.h;
	    	down_speed=3;
	    }
	    	if(block_count==10&&hasXY==false)
		    {
	    		hasXY=true;
		    	crow_x=Ball.LBorder-192*MainActivity.w;
		    	crow_y=Ball.DBorder+218*MainActivity.h;
		        down_speed=8;
		    }
	    	if(block_count==11&&hasXY==false)
		    {
	    		hasXY=true;
		    	crow_x=Ball.LBorder-192*MainActivity.w;
		    	crow_y=Ball.DBorder+418*MainActivity.h;
		    	down_speed=13;
		    }
	   //	crow_x=Jump.crow2.getX();
	   // 	crow_y=Jump.crow2.getY();
	    	Jump.crow2.remove();
	    	Jump.crow.remove();
	    	//Jump.crow.setVisible(false);
	    	//Jump.crow2.setVisible(false);
	    	crow_y=crow_y-down_speed;
	    	//System.out.println("crow_x="+crow_x);
	    	//System.out.println("crow_y="+crow_y);
	    	crow2.setX(crow_x);
	    	crow2.setY(crow_y);
	    }
	      stage.act();
	      stage.draw();
	     
	      
	      
	      
	      
	      
	      
	      
	      //System.out.println("playAnimation_split="+playAnimation_split);
	      //System.out.println("Jump.crowTo==10&&query==true"+Jump.crowTo+"  "+query);
		    if(playAnimation_split==1)
		    {
		    	
		    	 stateTime += Gdx.graphics.getDeltaTime()*0.08;

		         currentFrame = Jump.splitblock.getKeyFrame(stateTime, true);
              if(Jump.splitblock.isAnimationFinished(stateTime))
              {
            	  playAnimation_split=0;
            	  stateTime=0f;
              }
		    	batch.begin();
		    	if(block_count==9)
		    	 batch.draw(currentFrame, Ball.LBorder-64*MainActivity.w, Ball.DBorder,64*MainActivity.w, 160*MainActivity.h);
		    	if(block_count==10)
			    	 batch.draw(currentFrame, Ball.LBorder-64*MainActivity.w, Ball.DBorder+160*MainActivity.h,64*MainActivity.w, 160*MainActivity.h);
		    	if(block_count==11)
			    	 batch.draw(currentFrame, Ball.LBorder-64*MainActivity.w, Ball.DBorder+2*160*MainActivity.h,64*MainActivity.w, 160*MainActivity.h);
		    	if(block_count==5)
			    	 batch.draw(currentFrame, Ball.RBorder+64*MainActivity.w, Ball.DBorder,64*MainActivity.w, 160*MainActivity.h);
			    	if(block_count==4)
				    	 batch.draw(currentFrame, Ball.RBorder+64*MainActivity.w, Ball.DBorder+160*MainActivity.h,64*MainActivity.w, 160*MainActivity.h);
			    	if(block_count==3)
				    	 batch.draw(currentFrame, Ball.RBorder+64*MainActivity.w, Ball.DBorder+2*160*MainActivity.h,64*MainActivity.w, 160*MainActivity.h);
		    	
		    	batch.end();
		    }
	     
	}
 public void help()
 {
	 if(help1exist==false)
	 {
		 System.out.println("help1");
		 help1.setVisible(false);
		 help1.clear();
	 }
	 if(help2exist==false)
	 {
		 System.out.println("help2");
		 help2.setVisible(false);
		 help2.clear();
		// begin=true;
	 }
	 if(help3exist==false)
	 {
		 System.out.println("help2");
		 help3.setVisible(false);
		 help3.clear();
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
		Jump.sound[i].dispose();
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
		changeAllPosition();
		//if(Jump.crowTo==-1)
		//	changePosition();
		//else
			//changePositionForCrow();
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
		return false;
	}

	@Override
	public boolean touchDown(float arg0, float arg1, int arg2, int arg3) //arg0代表x坐标，arg1代表y坐标
	{
		// TODO Auto-generated method stub
		if(help1exist==true)
		{
			help1exist=false;
		}
		else
		{
			if(help2exist==true)
			help2exist=false;
			else
				help3exist=false;
		}
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
	public void changeAllPosition()//滑动时改变方块位置
	 {
		 //System.out.println("area="+area+"fling="+fling);
		 if(area==1||area==2)
		 {
			 if(fling==2)
			 {
				if(ifexist[3]==0&&ifexist[4]==1&&ifexist[5]==1)
				{
					ifexist[3]=1;
					ifexist[4]=0;
					  MoveToAction moveto = Actions.moveTo(Jump.right[2].x+64*MainActivity.w, Jump.right[2].y-48*MainActivity.h, 0.05f);
					  actorGroup[4].addAction(moveto);
					  Actor temp1=new Actor(); 
					  temp1=actorGroup[3];		
					  actorGroup[3]= actorGroup[4];
					  actorGroup[4]=temp1;
					  //actorGroup[4]=null;
					  right1=1;
					  right2=0;
					  right3=2;
				}
				else if(ifexist[3]==1&&ifexist[4]==0&&ifexist[5]==1)
				{
					ifexist[4]=1;
					ifexist[5]=0;
					  MoveToAction moveto = Actions.moveTo(Jump.right[1].x+64*MainActivity.w, Jump.right[1].y-48*MainActivity.h, 0.05f);
					  actorGroup[5].addAction(moveto);
					 // actorGroup[4]= actorGroup[5];
					 // actorGroup[5]=null;
					  Actor temp1=new Actor(); 
					  temp1=actorGroup[4];		
					  actorGroup[4]= actorGroup[5];
					  actorGroup[5]=temp1;
					right1=0;
					right2=1;
					right3=2;
				}
				else if(ifexist[3]==0&&ifexist[4]==1&&ifexist[5]==0)
				{
					ifexist[3]=1;
					ifexist[4]=0;
					 MoveToAction moveto = Actions.moveTo(Jump.right[2].x+64*MainActivity.w, Jump.right[2].y-48*MainActivity.h, 0.05f);
					  actorGroup[4].addAction(moveto);
					// actorGroup[3]= actorGroup[4];
					//  actorGroup[4]=null;
					  Actor temp1=new Actor(); 
					  temp1=actorGroup[3];		
					  actorGroup[3]= actorGroup[4];
					  actorGroup[4]=temp1;
				}
				else if(ifexist[3]==0&&ifexist[4]==0&&ifexist[5]==1)
				{
					ifexist[4]=1;
					ifexist[5]=0;
					 MoveToAction moveto = Actions.moveTo(Jump.right[1].x+64*MainActivity.w, Jump.right[1].y-48*MainActivity.h, 0.05f);
					  actorGroup[5].addAction(moveto);
					// actorGroup[4]= actorGroup[5];
					//  actorGroup[5]=null;
					  Actor temp1=new Actor(); 
					  temp1=actorGroup[4];		
					  actorGroup[4]= actorGroup[5];
					  actorGroup[5]=temp1;
				}
			 }
			 if(fling==3)
			 {
				 if(ifexist[3]==1&&ifexist[4]==0&&ifexist[5]==1)
				 {
					 ifexist[3]=0;
						ifexist[4]=1;
						  MoveToAction moveto = Actions.moveTo(Jump.right[1].x+64*MainActivity.w, Jump.right[1].y-48*MainActivity.h, 0.05f);
						actorGroup[3].addAction(moveto);
						// actorGroup[4]= actorGroup[3];
						 // actorGroup[3]=null;
						 Actor temp1=new Actor(); 
						  temp1=actorGroup[4];		
						  actorGroup[4]= actorGroup[3];
						  actorGroup[3]=temp1;
						right1=1;
						right2=2;
						right3=0;
				 }
				 else if(ifexist[3]==1&&ifexist[4]==1&&ifexist[5]==0)
				 {
					 ifexist[4]=0;
						ifexist[5]=1;
						  MoveToAction moveto = Actions.moveTo(Jump.right[0].x+64*MainActivity.w, Jump.right[0].y-48*MainActivity.h, 0.05f);
						actorGroup[4].addAction(moveto);
						 //actorGroup[5]= actorGroup[4];
						 // actorGroup[4]=null;
						 Actor temp1=new Actor(); 
						  temp1=actorGroup[5];		
						  actorGroup[5]= actorGroup[4];
						  actorGroup[4]=temp1;
						right1=1;
						right2=0;
						right3=2;
						
				 }
				 else if(ifexist[3]==1&&ifexist[4]==0&&ifexist[5]==0)
				 {
					 ifexist[4]=1;
						ifexist[3]=0;
						  MoveToAction moveto = Actions.moveTo(Jump.right[1].x+64*MainActivity.w, Jump.right[1].y-48*MainActivity.h, 0.05f);
						actorGroup[3].addAction(moveto);
						// actorGroup[4]= actorGroup[3];
						 // actorGroup[3]=null;
						 Actor temp1=new Actor(); 
						  temp1=actorGroup[4];		
						  actorGroup[4]= actorGroup[3];
						  actorGroup[3]=temp1;
						right1=1;
						right2=0;
						right3=2;
						
				 }
				 else if(ifexist[3]==0&&ifexist[4]==1&&ifexist[5]==0)
				 {
					 ifexist[4]=0;
						ifexist[5]=1;
						  MoveToAction moveto = Actions.moveTo(Jump.right[0].x+64*MainActivity.w, Jump.right[0].y-48*MainActivity.h, 0.05f);
						actorGroup[4].addAction(moveto);
						// actorGroup[5]= actorGroup[4];
						 // actorGroup[4]=null;
						 Actor temp1=new Actor(); 
						  temp1=actorGroup[5];		
						  actorGroup[5]= actorGroup[4];
						  actorGroup[4]=temp1;
						right1=1;
						right2=0;
						right3=2;
						
				 }
			 }
		 }
		 
		 if(area==3||area==0)
		 {
			 if(fling==2)
			 {
				if(ifexist[11]==0&&ifexist[10]==1&&ifexist[9]==1)
				{
					ifexist[11]=1;
					ifexist[10]=0;
					  MoveToAction moveto = Actions.moveTo(Jump.left[2].x-64*MainActivity.w, Jump.left[2].y-48*MainActivity.h, 0.05f);
					  actorGroup[10].addAction(moveto);
					 // actorGroup[11]= actorGroup[10];
					 // actorGroup[10]=null;
					  Actor temp1=new Actor(); 
					  temp1=actorGroup[11];		
					  actorGroup[11]= actorGroup[10];
					  actorGroup[10]=temp1;
					  left1=1;
					  left2=0;
					  left3=2;
					  
				}
				else if(ifexist[11]==1&&ifexist[10]==0&&ifexist[9]==1)
				{
					ifexist[10]=1;
					ifexist[9]=0;
					  MoveToAction moveto = Actions.moveTo(Jump.left[1].x-64*MainActivity.w, Jump.left[1].y-48*MainActivity.h, 0.05f);
					actorGroup[9].addAction(moveto);
					// actorGroup[10]= actorGroup[9];
					 // actorGroup[9]=null;
					 Actor temp1=new Actor(); 
					  temp1=actorGroup[10];		
					  actorGroup[10]= actorGroup[9];
					  actorGroup[9]=temp1;
					left1=0;
					left2=1;
					left3=2;
				}
				else if(ifexist[11]==0&&ifexist[10]==1&&ifexist[9]==0)
				{
					ifexist[11]=1;
					ifexist[10]=0;
					  MoveToAction moveto = Actions.moveTo(Jump.left[2].x-64*MainActivity.w, Jump.left[2].y-48*MainActivity.h, 0.05f);
					actorGroup[10].addAction(moveto);
					 //actorGroup[11]= actorGroup[10];
					 // actorGroup[10]=null;
					 Actor temp1=new Actor(); 
					  temp1=actorGroup[11];		
					  actorGroup[11]= actorGroup[10];
					  actorGroup[10]=temp1;
					left1=0;
					left2=1;
					left3=2;
				}
				else if(ifexist[11]==0&&ifexist[10]==0&&ifexist[9]==1)
				{
					ifexist[10]=1;
					ifexist[9]=0;
					  MoveToAction moveto = Actions.moveTo(Jump.left[1].x-64*MainActivity.w, Jump.left[1].y-48*MainActivity.h, 0.05f);
					actorGroup[9].addAction(moveto);
					// actorGroup[10]= actorGroup[9];
					  //actorGroup[9]=null;
					 Actor temp1=new Actor(); 
					  temp1=actorGroup[10];		
					  actorGroup[10]= actorGroup[9];
					  actorGroup[9]=temp1;
					left1=0;
					left2=1;
					left3=2;
				}
			 }
			 if(fling==3)
			 {
				 if(ifexist[11]==1&&ifexist[10]==0&&ifexist[9]==1)
				 {
					 ifexist[11]=0;
						ifexist[10]=1;
						  MoveToAction moveto = Actions.moveTo(Jump.left[1].x-64*MainActivity.w, Jump.left[1].y-48*MainActivity.h, 0.05f);
						actorGroup[11].addAction(moveto);
						 //actorGroup[10]= actorGroup[11];
						 // actorGroup[11]=null;
						 Actor temp1=new Actor(); 
						  temp1=actorGroup[10];		
						  actorGroup[10]= actorGroup[11];
						  actorGroup[11]=temp1;
						left1=1;
						left2=2;
						left3=0;
				 }
				 else if(ifexist[11]==1&&ifexist[10]==1&&ifexist[9]==0)
				 {
					 ifexist[10]=0;
						ifexist[9]=1;
					    MoveToAction moveto = Actions.moveTo(Jump.left[0].x-64*MainActivity.w, Jump.left[0].y-48*MainActivity.h, 0.05f);
						actorGroup[10].addAction(moveto);
						// actorGroup[9]= actorGroup[10];
						//  actorGroup[10]=null;
						 Actor temp1=new Actor(); 
						  temp1=actorGroup[9];		
						  actorGroup[9]= actorGroup[10];
						  actorGroup[10]=temp1;
						left1=1;
						left2=0;
						left3=2;
						
				 }
				 else if(ifexist[11]==1&&ifexist[10]==0&&ifexist[9]==0)
				 {
					 ifexist[11]=0;
						ifexist[10]=1;
					    MoveToAction moveto = Actions.moveTo(Jump.left[1].x-64*MainActivity.w, Jump.left[1].y-48*MainActivity.h, 0.05f);
						actorGroup[11].addAction(moveto);
						 //actorGroup[10]= actorGroup[11];
						 // actorGroup[11]=null;
						 Actor temp1=new Actor(); 
						  temp1=actorGroup[10];		
						  actorGroup[10]= actorGroup[11];
						  actorGroup[11]=temp1;
						left1=1;
						left2=0;
						left3=2;
						
				 }
				 else if(ifexist[11]==0&&ifexist[10]==1&&ifexist[9]==0)
				 {
					 ifexist[10]=0;
						ifexist[9]=1;
					    MoveToAction moveto = Actions.moveTo(Jump.left[0].x-64*MainActivity.w, Jump.left[0].y-48*MainActivity.h, 0.05f);
						actorGroup[10].addAction(moveto);
						// actorGroup[9]= actorGroup[10];
						//  actorGroup[10]=null;
						 Actor temp1=new Actor(); 
						  temp1=actorGroup[9];		
						  actorGroup[9]= actorGroup[10];
						  actorGroup[10]=temp1;
						left1=1;
						left2=0;
						left3=2;
						
				 }
			 }
		 }
		 
		 if(area==3||area==2)
		 {
			 if(fling==0)
			 {
				if(ifexist[2]==0&&ifexist[1]==1&&ifexist[0]==1)
				{
					ifexist[2]=1;
					ifexist[1]=0;
					  MoveToAction moveto = Actions.moveTo(Jump.up[2].x-48*MainActivity.w, Jump.up[2].y+64*MainActivity.h, 0.05f);
					  actorGroup[1].addAction(moveto);
					  //actorGroup[2]= actorGroup[1];
					 // actorGroup[1]=null;
					  Actor temp1=new Actor(); 
					  temp1=actorGroup[2];		
					  actorGroup[2]= actorGroup[1];
					  actorGroup[1]=temp1;
				}
				else if(ifexist[0]==1&&ifexist[1]==0&&ifexist[2]==1)
				{
					ifexist[1]=1;
					ifexist[0]=0;
					  MoveToAction moveto = Actions.moveTo(Jump.up[1].x-48*MainActivity.w, Jump.up[1].y+64*MainActivity.h, 0.05f);
					actorGroup[0].addAction(moveto);
					 //actorGroup[1]= actorGroup[0];
					 // actorGroup[0]=null;
					 Actor temp1=new Actor(); 
					  temp1=actorGroup[1];		
					  actorGroup[1]= actorGroup[0];
					  actorGroup[0]=temp1;
				}
				else if(ifexist[0]==1&&ifexist[1]==0&&ifexist[2]==0)
				{
					ifexist[1]=1;
					ifexist[0]=0;
					  MoveToAction moveto = Actions.moveTo(Jump.up[1].x-48*MainActivity.w, Jump.up[1].y+64*MainActivity.h, 0.05f);
					actorGroup[0].addAction(moveto);
					 //actorGroup[1]= actorGroup[0];
					 // actorGroup[0]=null;
					 Actor temp1=new Actor(); 
					  temp1=actorGroup[1];		
					  actorGroup[1]= actorGroup[0];
					  actorGroup[0]=temp1;
				}
				else if(ifexist[0]==0&&ifexist[1]==1&&ifexist[2]==0)
				{
					ifexist[2]=1;
					ifexist[1]=0;
					  MoveToAction moveto = Actions.moveTo(Jump.up[2].x-48*MainActivity.w, Jump.up[2].y+64*MainActivity.h, 0.05f);
					actorGroup[1].addAction(moveto);
					// actorGroup[2]= actorGroup[1];
					 // actorGroup[1]=null;
					 Actor temp1=new Actor(); 
					  temp1=actorGroup[2];		
					  actorGroup[2]= actorGroup[1];
					  actorGroup[1]=temp1;
				}
			 }
			 if(fling==1)
			 {
				 if(ifexist[0]==0&&ifexist[1]==1&&ifexist[2]==1)
				 {
					 ifexist[1]=0;
						ifexist[0]=1;
						  MoveToAction moveto = Actions.moveTo(Jump.up[0].x-48*MainActivity.w, Jump.up[0].y+64*MainActivity.h, 0.05f);
						actorGroup[1].addAction(moveto);
						 //actorGroup[0]= actorGroup[1];
						 // actorGroup[1]=null;
						 Actor temp1=new Actor(); 
						  temp1=actorGroup[0];		
						  actorGroup[0]= actorGroup[1];
						  actorGroup[1]=temp1;
				 }
				 else if(ifexist[0]==1&&ifexist[1]==0&&ifexist[2]==1)
				 {
					 ifexist[1]=1;
						ifexist[2]=0;
						  MoveToAction moveto = Actions.moveTo(Jump.up[1].x-48*MainActivity.w, Jump.up[1].y+64*MainActivity.h, 0.05f);
						actorGroup[2].addAction(moveto);
						// actorGroup[1]= actorGroup[2];
						//  actorGroup[2]=null;
						 Actor temp1=new Actor(); 
						  temp1=actorGroup[1];		
						  actorGroup[1]= actorGroup[2];
						  actorGroup[2]=temp1;
				 }
				 else if(ifexist[0]==0&&ifexist[1]==0&&ifexist[2]==1)
				 {
					 ifexist[1]=1;
						ifexist[2]=0;
						  MoveToAction moveto = Actions.moveTo(Jump.up[1].x-48*MainActivity.w, Jump.up[1].y+64*MainActivity.h, 0.05f);
						actorGroup[2].addAction(moveto);
						// actorGroup[1]= actorGroup[2];
						 // actorGroup[2]=null;
						 Actor temp1=new Actor(); 
						  temp1=actorGroup[1];		
						  actorGroup[1]= actorGroup[2];
						  actorGroup[2]=temp1;
				 }
				 else if(ifexist[0]==0&&ifexist[1]==1&&ifexist[2]==0)
				 {
					 ifexist[1]=0;
						ifexist[0]=1;
						  MoveToAction moveto = Actions.moveTo(Jump.up[0].x-48*MainActivity.w, Jump.up[0].y+64*MainActivity.h, 0.05f);
						actorGroup[1].addAction(moveto);
						// actorGroup[0]= actorGroup[1];
						 // actorGroup[1]=null;
						 Actor temp1=new Actor(); 
						  temp1=actorGroup[0];		
						  actorGroup[0]= actorGroup[1];
						  actorGroup[1]=temp1;
				 }
			 }
		 }
		 
		 if(area==0||area==1)
		 {
			 if(fling==0)
			 {
				if(ifexist[6]==0&&ifexist[7]==1&&ifexist[8]==1)
				{
					ifexist[6]=1;
					ifexist[7]=0;
					  MoveToAction moveto = Actions.moveTo(Jump.down[2].x-48*MainActivity.w, Jump.down[2].y-64*MainActivity.h,0.05f);
					  actorGroup[7].addAction(moveto);
					 // actorGroup[6]= actorGroup[7];
					  //actorGroup[7]=null;
					  Actor temp1=new Actor(); 
					  temp1=actorGroup[6];		
					  actorGroup[6]= actorGroup[7];
					  actorGroup[7]=temp1;
				}
				else if(ifexist[8]==1&&ifexist[7]==0&&ifexist[6]==1)
				{
					ifexist[7]=1;
					ifexist[8]=0;
					  MoveToAction moveto = Actions.moveTo(Jump.down[1].x-48*MainActivity.w, Jump.down[1].y-64*MainActivity.h, 0.05f);
					actorGroup[8].addAction(moveto);
					 //actorGroup[7]= actorGroup[8];
					 // actorGroup[8]=null;
					Actor temp1=new Actor(); 
					  temp1=actorGroup[7];		
					  actorGroup[7]= actorGroup[8];
					  actorGroup[8]=temp1;
				}
				else if(ifexist[8]==1&&ifexist[7]==0&&ifexist[6]==0)
				{
					ifexist[7]=1;
					ifexist[8]=0;
					  MoveToAction moveto = Actions.moveTo(Jump.down[1].x-48*MainActivity.w, Jump.down[1].y-64*MainActivity.h, 0.05f);
					actorGroup[8].addAction(moveto);
					// actorGroup[7]= actorGroup[8];
					 // actorGroup[8]=null;
					Actor temp1=new Actor(); 
					  temp1=actorGroup[7];		
					  actorGroup[7]= actorGroup[8];
					  actorGroup[8]=temp1;
				}
				else if(ifexist[8]==0&&ifexist[7]==1&&ifexist[6]==0)
				{
					ifexist[6]=1;
					ifexist[7]=0;
					  MoveToAction moveto = Actions.moveTo(Jump.down[2].x-48*MainActivity.w, Jump.down[2].y-64*MainActivity.h, 0.05f);
					actorGroup[7].addAction(moveto);
					// actorGroup[6]= actorGroup[7];
					//  actorGroup[7]=null;
					 Actor temp1=new Actor(); 
					  temp1=actorGroup[6];		
					  actorGroup[6]= actorGroup[7];
					  actorGroup[7]=temp1;
				}
			 }
			 if(fling==1)
			 {
				 if(ifexist[8]==0&&ifexist[7]==1&&ifexist[6]==1)
				 {
					 ifexist[7]=0;
						ifexist[8]=1;
						  MoveToAction moveto = Actions.moveTo(Jump.down[0].x-48*MainActivity.w, Jump.down[0].y-64*MainActivity.h, 0.05f);
						actorGroup[7].addAction(moveto);
						 //actorGroup[8]= actorGroup[7];
						//actorGroup[7]=null;
						 Actor temp1=new Actor(); 
						  temp1=actorGroup[8];		
						  actorGroup[8]= actorGroup[7];
						  actorGroup[7]=temp1;
				 }
				 else if(ifexist[8]==1&&ifexist[7]==0&&ifexist[6]==1)
				 {
					 ifexist[7]=1;
						ifexist[6]=0;
						MoveToAction moveto = Actions.moveTo(Jump.down[1].x-48*MainActivity.w, Jump.down[1].y-64*MainActivity.h, 0.05f);
					    actorGroup[6].addAction(moveto);
					    //actorGroup[7]= actorGroup[6];
						//  actorGroup[6]=null;
					    Actor temp1=new Actor(); 
						  temp1=actorGroup[7];		
						  actorGroup[7]= actorGroup[6];
						  actorGroup[6]=temp1;
				 }
				 else if(ifexist[8]==0&&ifexist[7]==1&&ifexist[6]==0)
				 {
					 ifexist[8]=1;
						ifexist[7]=0;
						MoveToAction moveto = Actions.moveTo(Jump.down[0].x-48*MainActivity.w, Jump.down[0].y-64*MainActivity.h, 0.05f);
					    actorGroup[7].addAction(moveto);
					    //actorGroup[8]= actorGroup[7];
						//  actorGroup[7]=null;
					    Actor temp1=new Actor(); 
						  temp1=actorGroup[8];		
						  actorGroup[8]= actorGroup[7];
						  actorGroup[7]=temp1;
				 }
				 else if(ifexist[8]==0&&ifexist[7]==0&&ifexist[6]==1)
				 {
					 ifexist[7]=1;
						ifexist[6]=0;
						MoveToAction moveto = Actions.moveTo(Jump.down[1].x-48*MainActivity.w, Jump.down[1].y-64*MainActivity.h, 0.05f);
					    actorGroup[6].addAction(moveto);
					   // actorGroup[7]= actorGroup[6];
						//  actorGroup[6]=null;
					    Actor temp1=new Actor(); 
						  temp1=actorGroup[7];		
						  actorGroup[7]= actorGroup[6];
						  actorGroup[6]=temp1;
				 }
			 }
		 }
	 }
	@Override
	public boolean touchDown(int arg0, int arg1, int arg2, int arg3) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDragged(int arg0, int arg1, int arg2) {
		// TODO Auto-generated method stub
		//System.out.println("拖动");
	//	System.out.println("arg0="+arg0+"arg1="+arg1+"arg2="+arg2);
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

	@Override
	public boolean zoom(float arg0, float arg1) {
		// TODO Auto-generated method stub
		return false;
	}
	
}
