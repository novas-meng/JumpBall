package com.example.jumpball;

import java.util.HashMap;

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


public class blueFirstGame implements Screen,InputProcessor,GestureListener
{

	private int Lboder=(int)crowball.LBorder;
	private int Rboder=(int)crowball.RBorder;
	private int Uboder=(int)crowball.UBorder;
	private int Dboder=(int)crowball.DBorder;
	static PointF[] up=new PointF[3];
	static PointF[] down=new PointF[3];
	static PointF[] left=new PointF[3];
	static PointF[] right=new PointF[3];
	int count=0;
	crowball ball;
	Image image;
	 Image game_board;
	 Image score_board;
	Stage stage;
	static boolean replay=false;
	static int state=0;
	InputMultiplexer input;
	  FreeTypeFontGenerator Generator;
	   FreeTypeBitmapFontData fontData;
	static blueGameSwitch gs;
	BitmapFont bf;
	BitmapFont Font;
	SpriteBatch sb;
	LabelStyle ls;
	Label l;
	 LabelStyle labelStyle;
	 Label label;
	 Label readyLabel,goLabel;
	//m表示方向，0为上，1为右，2为下，3为左，n表示编号
	//j表示需要移动得滑块得方位，k表示标号
	static int j;
	static int k;
	static int m;
	static int n;
	static //j表示需要移动得滑块得方位，k表示标号
	int code;
	
	Activity main;
	TextureRegion texture;
	Texture readytur,gotur;
	Window dialog;
	static int start=0;
	BitmapFont windowbf;
	static int playMusic=0;
	//area代表将屏幕分为四块,fling代表滑动方向,0->  1<-  2 上 3 下 
	static int fling=0;
	static int area=0;
	static int[] ifexist=new int[12];
	boolean hasdialog=false;
	Sound ready,go;
	//存在方块值为1，不存在值为0
	public blueFirstGame(blueGameSwitch gs)
	{
		this.gs=gs;
	}
	
	public blueFirstGame(Activity main) {
		// TODO Auto-generated constructor stub
		super();
		this.main=main;
	}
	@Override
	public void show() {
		// TODO Auto-generated method stub
		// bluetooth.trans("ready");
		for(int i=0;i<3;i++)
		 {
			 int width=(int) (Lboder+(2*i+1)*80*blueMainActivity.w-32*blueMainActivity.w);
			 int height=Uboder;
			 PointF point=new PointF(width,height);
			 up[i]=point;
			 
			 width=Lboder;
			 height=(int) (Dboder+(2*i+1)*80*blueMainActivity.h-32*blueMainActivity.h);
			 point=new PointF(width,height);
			 left[i]=point;
			 
			 width=(int) (Lboder+(2*i+1)*80*blueMainActivity.w-32*blueMainActivity.w);
			 height=Dboder;
			 point=new PointF(width,height);
			 down[i]=point;
			 
			 width=Rboder;
			 height=(int) (Dboder+(2*i+1)*80*blueMainActivity.w-32*blueMainActivity.w);
			 point=new PointF(width,height);
			 right[i]=point;
		 }
		 for(int i=0;i<12;i++)
		 {
				 ifexist[i]=1;
		 }
		 ifexist[2]=0;
		 ifexist[3]=0;
		 ifexist[6]=0;
		 ifexist[11]=0;
		 Texture.setEnforcePotImages(false);
		 Generator=new FreeTypeFontGenerator(Gdx.files.internal("girl.TTF"));
			fontData=Generator.generateData(75, Generator.DEFAULT_CHARS+"负责方左右上侧请稍等队友还没有准备好亲当你看到这个页时说明刚才疏忽了肯定不是实力按一下面的钮，再来次吧", false);
		    Font=new BitmapFont(fontData,fontData.getTextureRegion(),false);
		    Font.setColor(Color.BLUE);
		    labelStyle = new LabelStyle(Font, Color.BLACK); 
		 ready=Gdx.audio.newSound(Gdx.files.internal("ready.wav"));
		 go=Gdx.audio.newSound(Gdx.files.internal("go.wav"));
		 readytur=new Texture(Gdx.files.internal("ready.png"));
		 gotur=new Texture(Gdx.files.internal("go.png"));
		 windowbf=new BitmapFont();
		 image = new Image(new Texture(Gdx.files.internal("easy/easyback.png")));
		    //  image.setPosition(0, 0);
	       image.setSize(MainActivity.screenwidth, MainActivity.screenheight);
	       game_board=new Image(new Texture(Gdx.files.internal("game_board.png")));
	       game_board.setSize(700, 700);
	       game_board.setPosition(260, 20);
	       score_board=new Image(new Texture(Gdx.files.internal("score_board.png")));
	       score_board.setSize(200, 170);
	       score_board.setPosition(1000, 530);
	       stage = new Stage(MainActivity.screenwidth,MainActivity.screenheight,false);
	       //Gdx.input.setInputProcessor(stage);
           ball=new crowball();
           stage.addActor(image);
         //  stage.addActor(game_board);
         //  stage.addActor(score_board);
           bf=new BitmapFont();
           bf.setScale(1.5f);
           label = new Label("", labelStyle); 
           readyLabel=new Label("",labelStyle);
           goLabel=new Label("",labelStyle);
           sb=new SpriteBatch();
           ls=new LabelStyle(bf,bf.getColor());
           blueJump jump=new blueJump();
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
	       stage.addActor(blueJump.d);
	       stage.addActor(jump);
	       stage.addActor(label);
	       stage.addActor(readyLabel);
	       stage.addActor(goLabel);
	       //stage.addActor(new blockcolor());
	      //stage.addActor(l);
	       input=new InputMultiplexer();
	       input.addProcessor(this);
	       input.addProcessor(stage);
	       input.addProcessor(new GestureDetector(this));
	       Gdx.input.setInputProcessor(input);
	      // float duration = MathUtils.random(3, 10);
	       
	      // MoveToAction moveto = Actions.moveTo(240, 160, 20);
	       //jump.addAction(moveto);
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		sb.dispose();
		bf.dispose();
		ready.dispose();
		go.dispose();
		stage.dispose();
		readytur.dispose();
		gotur.dispose();
		for(int i=0;i<25;i++)
		blueJump.sound[i].dispose();
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
	}

	@Override
	public void render(float arg0) {
		// TODO Auto-generated method stub
		//l.clear();
		// l=new Label("score=:"+Jump.score,ls);
		//l.setPosition(1000, 700);
		//l.setSize(200, 300);
		// stage.addActor(l);
		//if(Gdx.input.isKeyPressed(Input.Keys.BACK)){
			//System.out.println("Back Pressed");
			//main.gs.setScreen(main.gs.fg);
			//}
		if(bluetooth.receive.equals("fail"))
		{
			 gs.setScreen(gs.bf);
		}
		 Gdx.gl.glClearColor(1, 1, 1, 1);
	       Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
	      // l.setText("score=:"+Jump.score);
	       if(bluetooth.state.equals("ready")&&replay==false)
    	   {
	    	   System.out.println("state改变");
    		   state=1;
    		   bluetooth.trans("ready");
    		   replay=true;
    	   }
	       if(state==0)
	       {
	    	   System.out.println("count="+count);
	    	   if(count==0)
	    	   {
	    		    System.out.println("先进来得一方");
	    		     bluetooth.trans("ready");
	    	   }
	    	 
	    	   count++;
	    	   System.out.println("对方状态"+bluetooth.state);
	    	   sb.begin();
				//	bf.setScale(5.0f);
					//bf.draw(sb,"score"+Jump.score, 700, 700);
			    	   Font.drawMultiLine(sb,"请稍等，\n" +
			    	 "队友还没有准备好",350*MainActivity.w,350*MainActivity.h);
					sb.end();
					if(bluetooth.state.equals("ready"))
			    	   {
			    		   state=1;
			    	   }
	    	  
	       }
	       if(state==1)
	       {
	    	   count=0;
	    	   if(playMusic==0)
		       {
		    	   ready.play();
		       }
		       if(playMusic>=0&&playMusic<60)
		       {
		    	   readyLabel.setText("ready");
		    	   readyLabel.setPosition(500*blueMainActivity.w, 500*blueMainActivity.h);
		    	   sb.begin();
		    	   sb.draw(readytur, 800*MainActivity.w, 550*MainActivity.h);
		    	   sb.end();
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
		    	   readyLabel.setPosition(550*blueMainActivity.w, 500*blueMainActivity.h);
		    	   sb.begin();
		    	   sb.draw(gotur, 800*MainActivity.w, 550*MainActivity.h);
		    	   sb.end();
		       }
		       if(playMusic==100)
		       {
		    	   playMusic=-1;
		    	   
		       }
		       //if(playMusic==-1)
			      // {
			    	   sb.begin();
				//	bf.setScale(5.0f);
					//bf.draw(sb,"score"+Jump.score, 700, 700);
			    	   if(bluetooth.serviceOrCilent==bluetooth.ServerOrCilent.CILENT)
			    	   {
			    		   label.setText(blueJump.score+"");
			    		      label.setPosition(1050*blueMainActivity.w, 400*blueMainActivity.h);
			    		      readyLabel.setText("");
			    		      goLabel.setText("亲，你负责上方和下方");
			    		      goLabel.setFontScale(0.7f);
			    		      goLabel.setPosition(380*blueMainActivity.w, 600*blueMainActivity.h);
			    		    Font.drawMultiLine(sb,"亲，你负责上方和下方"+"   "+"Score="+blueJump.score,250*MainActivity.w,700*MainActivity.h);
			    	   }
			    	  
			    	   else
			    	   {
			    		   label.setText(blueJump.score+"");
			    		      label.setPosition(1050*blueMainActivity.w, 400*blueMainActivity.h);
			    		      readyLabel.setText("");
			    		      goLabel.setFontScale(0.7f);
			    		      goLabel.setText("亲，你负责左侧和右侧");
			    		      goLabel.setPosition(380*blueMainActivity.w, 600*blueMainActivity.h);
			    		   Font.drawMultiLine(sb,"亲，你负责左侧和右侧"+"   "+"Score="+blueJump.score,250*MainActivity.w,700*MainActivity.h);
			    	   }
			    	   sb.end();
					 
			     if(blueJump.fail==1)
			     {
			    	// System.out.println("碰撞错误");
			    	// GameSwitch gs=new GameSwitch(new fail());
			    	 bluetooth.trans("fail");
			    	 gs.setScreen(gs.bf);
			     }
	       }
	      /*
			     if(bluetooth.serviceOrCilent==bluetooth.ServerOrCilent.CILENT)
			     {
			    	 if(bluetooth.receive.equals("fail"))
			    	 {
			    		 System.out.println("服务端挂了");
			    		 gs.setScreen(gs.bf);
			    	 }
			     }
			     */
			     if(state==1)
			       {
			    	  
			     stage.act();
			      stage.draw();
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
		/*
		if(arg0==Input.Keys.BACK){
			//this.dispose();
			gs.f.dispose();
			//gs.fg.dispose();
			main.finish();
			//Gdx.app.exit();
			start=1;
			if(!hasdialog){
			stage.addActor(dialog);
			hasdialog=true;
			}
			else{
			dialog.clear();
			start=0;
			hasdialog=false;
			}
		}
		*/
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
	///	System.out.println(arg0+" "+arg1+arg2+"");
	//	System.out.println(area);
		//System.out.println(MainActivity.screenwidth);
		//System.out.println("花瓶");
		if(arg0>0&&Math.abs(arg0)>1000)
		{
			//System.out.println("向右");
			fling=0;
		}
			
		if(arg0<0&&Math.abs(arg0)>1000)
		{
			//System.out.println("向左");
			fling=1;
		}
			
		if(arg1>0&&Math.abs(arg1)>1000)
		{
			//System.out.println("向下");
			fling=3;
		}
			
		if(arg1<0&&Math.abs(arg1)>1000)
		{
			//System.out.println("向上");
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
		return false;
	}

	@Override
	public boolean touchDown(float arg0, float arg1, int arg2, int arg3) //arg0代表x坐标，arg1代表y坐标
	{
		// TODO Auto-generated method stub
		//System.out.println("被按下了");
		//System.out.println("arg0="+arg0+"arg1="+arg1+"arg2="+arg2+"arg3="+arg3);
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
	public static void move(String info)
	{
		
		 code=Integer.parseInt(info)-10000;
		 j=code/1000;
		 code=code-1000*j;
		 k=code/100;
		 code=code-k*100;
		 m=code/10;
		 n=code%10;
		 System.out.println("j="+j);
		 System.out.println("k="+k);
		 System.out.println("m="+m);
		 System.out.println("n="+n);
		 if(bluetooth.serviceOrCilent==bluetooth.ServerOrCilent.SERVICE)
		 {
			 if(j==0&&m==0)
			 {
				 if(k==2&&n==2)
				 {
					 ifexist[2]=1;
						ifexist[1]=0;
						  MoveToAction moveto = Actions.moveTo(blueJump.up[2].x-48*blueMainActivity.w, blueJump.up[2].y+64*blueMainActivity.h, 0.05f);
						  crowball.up2.addAction(moveto);
				 }
				 if(k==1&&n==1)
				 {

						ifexist[1]=1;
						ifexist[0]=0;
						  MoveToAction moveto = Actions.moveTo(blueJump.up[1].x-48*blueMainActivity.w, blueJump.up[1].y+64*blueMainActivity.h, 0.05f);
						  crowball.up1.addAction(moveto);
				 }
				 if(k==2&&n==1)
				 {
					 ifexist[1]=1;
						ifexist[2]=0;
						  MoveToAction moveto = Actions.moveTo(blueJump.up[1].x-48*blueMainActivity.w, blueJump.up[1].y+64*blueMainActivity.h, 0.05f);
						  crowball.up2.addAction(moveto);
				 }
				 if(k==1&&n==0)
				 {
					 ifexist[1]=0;
						ifexist[0]=1;
						  MoveToAction moveto = Actions.moveTo(blueJump.up[0].x-48*blueMainActivity.w, blueJump.up[0].y+64*blueMainActivity.w, 0.01f);
						  crowball.up1.addAction(moveto);
				 }
			 }
			 if(j==2&&m==2)
			 {
				 if(k==2&&n==2)
				 {
					 ifexist[6]=1;
						ifexist[7]=0;
						  MoveToAction moveto = Actions.moveTo(blueJump.down[2].x-48*blueMainActivity.w, blueJump.down[2].y-64*blueMainActivity.w,0.05f);
						  crowball.down2.addAction(moveto);
				 }
				 if(k==1&&n==1)
				 {


						ifexist[7]=1;
						ifexist[8]=0;
						  MoveToAction moveto = Actions.moveTo(blueJump.down[1].x-48*blueMainActivity.w, blueJump.down[1].y-64*blueMainActivity.h, 0.05f);
						  crowball.down1.addAction(moveto);
				 }
				 if(k==2&&n==1)
				 {
					 ifexist[7]=1;
						ifexist[6]=0;
						  MoveToAction moveto = Actions.moveTo(blueJump.down[1].x-48*blueMainActivity.w, blueJump.down[1].y-64*blueMainActivity.h, 0.05f);
						  crowball.down2.addAction(moveto);
				 }
				 if(k==1&&n==0)
				 {
					 ifexist[7]=0;
						ifexist[8]=1;
						  MoveToAction moveto = Actions.moveTo(blueJump.down[0].x-48*blueMainActivity.w, blueJump.down[0].y-64*blueMainActivity.h, 0.05f);
						  crowball.down1.addAction(moveto);
				 } 
			 }
		 }
		 
		 
		 
		 
		 
		 
		 
		 if(bluetooth.serviceOrCilent==bluetooth.ServerOrCilent.CILENT)
		 {
			 if(j==1&&m==1)
			 {
				 if(k==2&&n==2)
				 {
					 ifexist[3]=1;
					ifexist[4]=0;
						  MoveToAction moveto = Actions.moveTo(blueJump.right[2].x+64*blueMainActivity.w, blueJump.right[2].y-48*blueMainActivity.h, 0.05f);
						  crowball.right2.addAction(moveto);
				 }
				 if(k==1&&n==1)
				 {
					 ifexist[4]=1;
						ifexist[5]=0;
						  MoveToAction moveto = Actions.moveTo(blueJump.right[1].x+64*blueMainActivity.w, blueJump.right[1].y-48*blueMainActivity.h, 0.05f);
						  crowball.right1.addAction(moveto);
				 }
				 if(k==2&&n==1)
				 {
					 ifexist[3]=0;
						ifexist[4]=1;
						  MoveToAction moveto = Actions.moveTo(blueJump.right[1].x+64*blueMainActivity.w, blueJump.right[1].y-48*blueMainActivity.h, 0.05f);
						  crowball.right2.addAction(moveto);
				 }
				 if(k==1&&n==0)
				 {
					 ifexist[4]=0;
						ifexist[5]=1;
						  MoveToAction moveto = Actions.moveTo(blueJump.right[0].x+64*blueMainActivity.w, blueJump.right[0].y-48*blueMainActivity.h, 0.05f);
						  crowball.right1.addAction(moveto);
				 }
			 }
			 if(j==3&&m==3)
			 {
				 if(k==2&&n==2)
				 {
					 ifexist[11]=1;
						ifexist[10]=0;
						  MoveToAction moveto = Actions.moveTo(blueJump.left[2].x-64*blueMainActivity.w, blueJump.left[2].y-48*blueMainActivity.h, 0.05f);
						  crowball.left2.addAction(moveto);
				 }
				 if(k==1&&n==1)
				 {
					 ifexist[10]=1;
						ifexist[9]=0;
						  MoveToAction moveto = Actions.moveTo(blueJump.left[1].x-64*blueMainActivity.w, blueJump.left[1].y-48*blueMainActivity.h, 0.05f);
						  crowball.left1.addAction(moveto);
				 }
				 if(k==2&&n==1)
				 {
					 ifexist[11]=0;
						ifexist[10]=1;
						  MoveToAction moveto = Actions.moveTo(blueJump.left[1].x-64*blueMainActivity.w, blueJump.left[1].y-48*blueMainActivity.h, 0.05f);
						  crowball.left2.addAction(moveto);
				 }
				 if(k==1&&n==0)
				 {
					 ifexist[10]=0;
						ifexist[9]=1;
						  MoveToAction moveto = Actions.moveTo(blueJump.left[0].x-64*blueMainActivity.w, blueJump.left[0].y-48*blueMainActivity.h, 0.05f);
						  crowball.left1.addAction(moveto);
				 }
			 }
		 }
		
	}
 public void changePosition()//滑动时改变方块位置
 {
	// System.out.println("area="+area+"fling="+fling);
	 if(area==1||area==2)
	 {
		 if(bluetooth.serviceOrCilent==bluetooth.ServerOrCilent.SERVICE)
		 {
			 if(fling==2)
			 {
				if(ifexist[3]==0&&ifexist[4]==1)
				{
					ifexist[3]=1;
					ifexist[4]=0;
					  MoveToAction moveto = Actions.moveTo(blueJump.right[2].x+64*blueMainActivity.w, blueJump.right[2].y-48*blueMainActivity.h, 0.05f);
					  crowball.right2.addAction(moveto);
					   j=1;
					   k=2;
					   m=1;
					   n=2;
					   code=1*10000+j*1000+k*100+m*10+n;
					   bluetooth.trans(String.valueOf(code));
				}
				else if(ifexist[3]==1&&ifexist[4]==0&&ifexist[5]==1)
				{
					ifexist[4]=1;
					ifexist[5]=0;
					  MoveToAction moveto = Actions.moveTo(blueJump.right[1].x+64*blueMainActivity.w, blueJump.right[1].y-48*blueMainActivity.h, 0.05f);
					  crowball.right1.addAction(moveto);
					 j=1;
					   k=1;
					   m=1;
					   n=1;
					   code=1*10000+j*1000+k*100+m*10+n;
					   bluetooth.trans(String.valueOf(code));
				}
			 }
			 
			 
			 if(fling==3)
			 {
				 if(ifexist[3]==1&&ifexist[4]==0&&ifexist[5]==1)
				 {
					 ifexist[3]=0;
						ifexist[4]=1;
						  MoveToAction moveto = Actions.moveTo(blueJump.right[1].x+64*blueMainActivity.w, blueJump.right[1].y-48*blueMainActivity.h, 0.05f);
						  crowball.right2.addAction(moveto);
						 j=1;
						   k=2;
						   m=1;
						   n=1;
						   code=1*10000+j*1000+k*100+m*10+n;
						   bluetooth.trans(String.valueOf(code));
				 }
				 else if(ifexist[3]==1&&ifexist[4]==1&&ifexist[5]==0)
				 {
					 ifexist[4]=0;
						ifexist[5]=1;
						  MoveToAction moveto = Actions.moveTo(blueJump.right[0].x+64*blueMainActivity.w, blueJump.right[0].y-48*blueMainActivity.h, 0.05f);
						  crowball.right1.addAction(moveto);
						 j=1;
						   k=1;
						   m=1;
						   n=0;
						   code=1*10000+j*1000+k*100+m*10+n;
						   bluetooth.trans(String.valueOf(code));
				 }
			 }
			 
		 }
		 /*
		 if(bluetooth.serviceOrCilent==bluetooth.ServerOrCilent.CILENT)
		 {
			 String c=bluetooth.blockreceice;
			 code=Integer.parseInt(c)-10000;
			 j=code/1000;
			 code=code-1000*j;
			 k=code/100;
			 code=code-k*100;
			 m=code/10;
			 n=code%10;
			 if(k==2&n==2)
			 {
				 MoveToAction moveto = Actions.moveTo(blueJump.right[2].x+64, blueJump.right[2].y-32, 0.05f);
				Ball.right2.addAction(moveto);
			 }
			 if(k==1&n==1)
			 {
				 MoveToAction moveto = Actions.moveTo(blueJump.right[1].x+64, blueJump.right[1].y-32, 0.05f);
				Ball.right1.addAction(moveto);
			 }
			 if(k==2&n==1)
			 {
				 MoveToAction moveto = Actions.moveTo(blueJump.right[1].x+64, blueJump.right[1].y-32, 0.05f);
				Ball.right2.addAction(moveto);
			 }
			 if(k==1&n==0)
			 {
				 MoveToAction moveto = Actions.moveTo(blueJump.right[0].x+64, blueJump.right[0].y-32, 0.05f);
				Ball.right1.addAction(moveto);
			 }
			 
		 }
		 */
	 }
	 
	 if((area==3||area==0)&&bluetooth.serviceOrCilent==bluetooth.ServerOrCilent.SERVICE)
	 {
		 if(bluetooth.serviceOrCilent==bluetooth.ServerOrCilent.SERVICE)
		 {
			 if(fling==2)
			 {
				if(ifexist[11]==0&&ifexist[10]==1)
				{
					ifexist[11]=1;
					ifexist[10]=0;
					  MoveToAction moveto = Actions.moveTo(blueJump.left[2].x-64*blueMainActivity.w, blueJump.left[2].y-48*blueMainActivity.h, 0.05f);
					  crowball.left2.addAction(moveto);
					  j=3;
					   k=2;
					   m=3;
					   n=2;
					   code=1*10000+j*1000+k*100+m*10+n;
					   bluetooth.trans(String.valueOf(code));
				}
				else if(ifexist[11]==1&&ifexist[10]==0&&ifexist[9]==1)
				{
					ifexist[10]=1;
					ifexist[9]=0;
					  MoveToAction moveto = Actions.moveTo(blueJump.left[1].x-64*blueMainActivity.w, blueJump.left[1].y-48*blueMainActivity.h, 0.05f);
					  crowball.left1.addAction(moveto);
					 j=3;
					   k=1;
					   m=3;
					   n=1;
					   code=1*10000+j*1000+k*100+m*10+n;
					   bluetooth.trans(String.valueOf(code));
				}
			 }
			 if(fling==3)
			 {
				 if(ifexist[11]==1&&ifexist[10]==0&&ifexist[9]==1)
				 {
					 ifexist[11]=0;
						ifexist[10]=1;
						  MoveToAction moveto = Actions.moveTo(blueJump.left[1].x-64*blueMainActivity.w, blueJump.left[1].y-48*blueMainActivity.h, 0.05f);
						  crowball.left2.addAction(moveto);
						 j=3;
						   k=2;
						   m=3;
						   n=1;
						   code=1*10000+j*1000+k*100+m*10+n;
						   bluetooth.trans(String.valueOf(code));
				 }
				 else if(ifexist[11]==1&&ifexist[10]==1&&ifexist[9]==0)
				 {
					 ifexist[10]=0;
						ifexist[9]=1;
						  MoveToAction moveto = Actions.moveTo(blueJump.left[0].x-64*blueMainActivity.w, blueJump.left[0].y-48*blueMainActivity.h, 0.05f);
						  crowball.left1.addAction(moveto);
						 j=3;
						   k=1;
						   m=3;
						   n=0;
						   code=1*10000+j*1000+k*100+m*10+n;
						   bluetooth.trans(String.valueOf(code));
				 }
			 }
		 }
		 /*
		 if(bluetooth.serviceOrCilent==bluetooth.ServerOrCilent.CILENT)
		 {
			 String c=bluetooth.blockreceice;
			 code=Integer.parseInt(c)-10000;
			 j=code/1000;
			 code=code-1000*j;
			 k=code/100;
			 code=code-k*100;
			 m=code/10;
			 n=code%10;
			 if(k==2&n==2)
			 {
				 MoveToAction moveto = Actions.moveTo(blueJump.left[2].x+64, blueJump.left[2].y-32, 0.05f);
				Ball.left2.addAction(moveto);
			 }
			 if(k==1&n==1)
			 {
				 MoveToAction moveto = Actions.moveTo(blueJump.left[1].x+64, blueJump.left[1].y-32, 0.05f);
				Ball.left1.addAction(moveto);
			 }
			 if(k==2&n==1)
			 {
				 MoveToAction moveto = Actions.moveTo(blueJump.left[1].x+64, blueJump.left[1].y-32, 0.05f);
				Ball.left2.addAction(moveto);
			 }
			 if(k==1&n==0)
			 {
				 MoveToAction moveto = Actions.moveTo(Jump.left[0].x+64, blueJump.left[0].y-32, 0.05f);
				Ball.left1.addAction(moveto);
			 }
			 
		 }
		*/
	 }
	 
	 if((area==3||area==2)&&bluetooth.serviceOrCilent==bluetooth.ServerOrCilent.CILENT)
	 {
		 if(fling==0)
		 {
			if(ifexist[2]==0&&ifexist[1]==1)
			{
				ifexist[2]=1;
				ifexist[1]=0;
				  MoveToAction moveto = Actions.moveTo(blueJump.up[2].x-48*blueMainActivity.w, blueJump.up[2].y+64*blueMainActivity.h, 0.05f);
				  crowball.up2.addAction(moveto);
				  j=0;
				   k=2;
				   m=0;
				   n=2;
				   code=1*10000+j*1000+k*100+m*10+n;
				   bluetooth.trans(String.valueOf(code));
			}
			else if(ifexist[0]==1&&ifexist[1]==0&&ifexist[2]==1)
			{
				ifexist[1]=1;
				ifexist[0]=0;
				  MoveToAction moveto = Actions.moveTo(blueJump.up[1].x-48*blueMainActivity.w, blueJump.up[1].y+64*blueMainActivity.h, 0.05f);
				  crowball.up1.addAction(moveto);
				 j=0;
				   k=1;
				   m=0;
				   n=1;
				   code=1*10000+j*1000+k*100+m*10+n;
				   bluetooth.trans(String.valueOf(code));
			}
		 }
		 if(fling==1)
		 {
			 if(ifexist[0]==0&&ifexist[1]==1&&ifexist[2]==1)
			 {
				 ifexist[1]=0;
					ifexist[0]=1;
					  MoveToAction moveto = Actions.moveTo(blueJump.up[0].x-48*blueMainActivity.w, blueJump.up[0].y+64*blueMainActivity.h, 0.05f);
					  crowball.up1.addAction(moveto);
					 j=0;
					   k=1;
					   m=0;
					   n=0;
					   code=1*10000+j*1000+k*100+m*10+n;
					   bluetooth.trans(String.valueOf(code));
			 }
			 else if(ifexist[0]==1&&ifexist[1]==0&&ifexist[2]==1)
			 {
				 ifexist[1]=1;
					ifexist[2]=0;
					  MoveToAction moveto = Actions.moveTo(blueJump.up[1].x-48*blueMainActivity.w, blueJump.up[1].y+64*blueMainActivity.h, 0.05f);
					  crowball.up2.addAction(moveto);
					 j=0;
					   k=2;
					   m=0;
					   n=1;
					   code=1*10000+j*1000+k*100+m*10+n;
					   bluetooth.trans(String.valueOf(code));
			 }
		 }
	 }
	 
	 if((area==0||area==1)&&bluetooth.serviceOrCilent==bluetooth.ServerOrCilent.CILENT)
	 {
		 if(fling==0)
		 {
			if(ifexist[6]==0&&ifexist[7]==1)
			{
				ifexist[6]=1;
				ifexist[7]=0;
				  MoveToAction moveto = Actions.moveTo(blueJump.down[2].x-48*blueMainActivity.w, blueJump.down[2].y-64*blueMainActivity.h,0.05f);
				  crowball.down2.addAction(moveto);
				  j=2;
				   k=2;
				   m=2;
				   n=2;
				   code=1*10000+j*1000+k*100+m*10+n;
				   bluetooth.trans(String.valueOf(code));
			}
			else if(ifexist[8]==1&&ifexist[7]==0&&ifexist[6]==1)
			{
				ifexist[7]=1;
				ifexist[8]=0;
				  MoveToAction moveto = Actions.moveTo(blueJump.down[1].x-48*blueMainActivity.w, blueJump.down[1].y-64*blueMainActivity.h, 0.05f);
				  crowball.down1.addAction(moveto);
				 j=2;
				   k=1;
				   m=2;
				   n=1;
				   code=1*10000+j*1000+k*100+m*10+n;
				   bluetooth.trans(String.valueOf(code));
			}
		 }
		 if(fling==1)
		 {
			 if(ifexist[8]==0&&ifexist[7]==1&&ifexist[6]==1)
			 {
				 ifexist[7]=0;
					ifexist[8]=1;
					  MoveToAction moveto = Actions.moveTo(blueJump.down[0].x-48*blueMainActivity.w, blueJump.down[0].y-64*blueMainActivity.h, 0.05f);
					  crowball.down1.addAction(moveto);
					 j=2;
					   k=1;
					   m=2;
					   n=0;
					   code=1*10000+j*1000+k*100+m*10+n;
					   bluetooth.trans(String.valueOf(code));
			 }
			 else if(ifexist[8]==1&&ifexist[7]==0&&ifexist[6]==1)
			 {
				 ifexist[7]=1;
					ifexist[6]=0;
					  MoveToAction moveto = Actions.moveTo(blueJump.down[1].x-48*blueMainActivity.w, blueJump.down[1].y-64*blueMainActivity.h, 0.05f);
					  crowball.down2.addAction(moveto);
					 j=2;
					   k=2;
					   m=2;
					   n=1;
					   code=1*10000+j*1000+k*100+m*10+n;
					   bluetooth.trans(String.valueOf(code));
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
