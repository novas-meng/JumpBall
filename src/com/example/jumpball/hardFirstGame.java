package com.example.jumpball;
import java.util.HashMap;
import java.util.Random;
import android.app.Activity;
import android.content.Context;
import android.graphics.PointF;
import android.media.AudioFormat;
import android.media.AudioRecord;
import android.media.MediaRecorder;
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

 class RecordThread extends Thread {  
     AudioRecord ar;  
    private int bs;  
    private static int SAMPLE_RATE_IN_HZ = 8000;  
    private boolean isRun = false;  
   
    public RecordThread() {  
        super();  
        bs = AudioRecord.getMinBufferSize(SAMPLE_RATE_IN_HZ,  
                AudioFormat.CHANNEL_CONFIGURATION_MONO,  
                AudioFormat.ENCODING_PCM_16BIT);  
        ar = new AudioRecord(MediaRecorder.AudioSource.MIC, SAMPLE_RATE_IN_HZ,  
                AudioFormat.CHANNEL_CONFIGURATION_MONO,  
                AudioFormat.ENCODING_PCM_16BIT, bs);  
    }  
   
    public void run() {  
      super.run(); 
      /*
       bs = AudioRecord.getMinBufferSize(SAMPLE_RATE_IN_HZ,  
               AudioFormat.CHANNEL_CONFIGURATION_MONO,  
               AudioFormat.ENCODING_PCM_16BIT);  
       ar = new AudioRecord(MediaRecorder.AudioSource.MIC, SAMPLE_RATE_IN_HZ,  
               AudioFormat.CHANNEL_CONFIGURATION_MONO,  
               AudioFormat.ENCODING_PCM_16BIT, bs); 
               */ 
        ar.startRecording();  
        
                // 用于读取的 buffer  
        byte[] buffer = new byte[bs];  
        isRun = true;  
        while (isRun) {  
            int r = ar.read(buffer, 0, bs);  
            int v = 0;  
                        // 将 buffer 内容取出，进行平方和运算  
            for (int i = 0; i < buffer.length; i++) {  
                // 这里没有做运算的优化，为了更加清晰的展示代码  
                v += buffer[i] * buffer[i]; 
              //  if(buffer[i]>50)
             //   System.out.println("buffer[i]"+buffer[i]);
            }  
            // 平方和除以数据总长度，得到音量大小。可以获取白噪声值，然后对实际采样进行标准化。  
            // 如果想利用这个数值进行操作，建议用 sendMessage 将其抛出，在 Handler 里进行处理。  
            Log.d("spl", String.valueOf(v / (float) r));  
          //  System.out.println("长度为"+buffer.length);
            System.out.println("v="+v);
           // if(v / (float) r>3300)																																																										
           // if(v>=25000000)
            	// if(v>=5500000)
            if(v>=2500000)
            {
            	System.out.println("v="+v);
            	//v=0;
            	if(hardFirstGame.fireexist==true)
        		{
        			
        				hardFirstGame.speed=-1;
        				
        		}
            	System.out.println("吹气"); 
            	System.out.println("分数"+hardJump.score); 
            	System.out.println("声音大小为"+String.valueOf(v / (float) r));
            	  System.out.println("v="+v);
            	
            	buffer=new byte[bs];
            }
        }  
        ar.stop();  
    }  
   
    public void pause() {  
                // 在调用本线程的 Activity 的 onPause 里调用，以便 Activity 暂停时释放麦克风  
        isRun = false;  
    }  
   
    public void start() {  
                // 在调用本线程的 Activity 的 onResume 里调用，以便 Activity 恢复后继续获取麦克风输入音量  
        if (!isRun) {  
            super.start();  
        }  
    }  
}  


public class hardFirstGame implements Screen,InputProcessor,GestureListener
{

	Image ach1,ach2;
	static boolean hasreset1=false;
	static boolean hasreset2=false;
	static boolean hasreset3=false;
	static boolean hasreset4=false;
	static boolean hasreset5=false;
	static boolean hasreset6=false;
	static boolean hasreset7=false;
	static boolean hasreset8=false;
	 static int speed=1;
	 int display=0;
	 Image helpR,helpL,help;
	 static Label helpLabel;
	 int i=0;
	 PointF base=new PointF();
		static  int gap=1;
		static boolean fireexist=false;
		 static int[] index=new int[10];
	 private AudioRecord ar;  
	    private int bs;  
	    private static int SAMPLE_RATE_IN_HZ = 8000;  
	hardball ball;
	Image image;
	Image fire;
	boolean begin=false;
	int count=0;
	 Image game_board;
	 Image score_board,tips;
	Stage stage;
	InputMultiplexer input;
	  FreeTypeFontGenerator Generator;
	   FreeTypeBitmapFontData fontData,helpData;
	hardGameSwitch gs;
	BitmapFont bf;
	BitmapFont Font,helpFont;
	//SpriteBatch sb;
	Activity main;
	TextureRegion texture;
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
	 LabelStyle labelStyle,helpStyle;
	 Label label;
	 Label readyLabel,goLabel,bounsLabel,tipsLabel;
	 int second=0;
	 RepeatAction r;
	//存在方块值为1，不存在值为0
	public hardFirstGame(hardGameSwitch gs)
	{
		this.gs=gs;
	}
	
	public hardFirstGame(Activity main) {
		// TODO Auto-generated constructor stub
		super();
		this.main=main;
	}
	@Override
	public void show() {
		// TODO Auto-generated method stub
		RecordThread thread=new RecordThread();
		thread.start();
		 for(int i=0;i<12;i++)
		 {
				 ifexist[i]=0;
		 }
		 ifexist[1]=1;
		 ifexist[4]=1;
		 ifexist[7]=1;
		 ifexist[10]=1;
		 Texture.setEnforcePotImages(false);
		 ach1=new Image(new Texture(Gdx.files.internal("ach/ach_3.png")));
		 ach2=new Image(new Texture(Gdx.files.internal("ach/ach_4.png")));
		   ach1.setVisible(false);
	       ach2.setVisible(false);
	       ach1.setSize(300*MainActivity.w, 130*MainActivity.h);
	       ach2.setSize(300*MainActivity.w, 130*MainActivity.h);
	       ach1.setPosition(925*MainActivity.w, 530*MainActivity.h);
	       ach2.setPosition(925*MainActivity.w, 530*MainActivity.h);
		 Generator=new FreeTypeFontGenerator(Gdx.files.internal("girl.TTF"));
			fontData=Generator.generateData(75, Generator.DEFAULT_CHARS+"亲当你看到这个页时说明刚才疏忽了肯定不是实力按一下面的钮，再来次吧", false);
		    Font=new BitmapFont(fontData,fontData.getTextureRegion(),false);
		    Font.setColor(Color.BLUE);
		    helpData=Generator.generateData(45, Generator.DEFAULT_CHARS+"游戏规则在屏幕上方滑动手指控制侧块下左右对着麦克风吹灭火苗吧", false);
		    helpFont=new BitmapFont(helpData,helpData.getTextureRegion(),false);
		    helpFont.setColor(Color.BLUE);
		     helpStyle = new LabelStyle(helpFont, Color.BLACK); 
		     help=new Image(new Texture(Gdx.files.internal("finger.png")));
		       helpL=new Image(new Texture(Gdx.files.internal("Lfinger.png")));
		       helpR=new Image(new Texture(Gdx.files.internal("Rfinger.png")));
		       tips=new Image(new Texture(Gdx.files.internal("tips.png")));
		       tips.setSize(240*hardMainActivity.w, 120*hardMainActivity.h);
		    labelStyle = new LabelStyle(Font, Color.BLACK); 
		 ready=Gdx.audio.newSound(Gdx.files.internal("ready.wav"));
		 go=Gdx.audio.newSound(Gdx.files.internal("go.wav"));
		// windowbf=new BitmapFont();
		 Texture.setEnforcePotImages(false);
		 fire=new Image(new Texture(Gdx.files.internal("inter/fire.png")));
		 fire.setSize(0,0);
		 image = new Image(new Texture(Gdx.files.internal("hard/hardback.png")));
		    //  image.setPosition(0, 0);
	       image.setSize(MainActivity.screenwidth, MainActivity.screenheight);
	       game_board=new Image(new Texture(Gdx.files.internal("game_board.png")));
	       game_board.setSize(700*MainActivity.w, 700*MainActivity.h);
	       game_board.setPosition(260*MainActivity.w, 20*MainActivity.h);
	       score_board=new Image(new Texture(Gdx.files.internal("score_board.png")));
	       score_board.setSize(200*MainActivity.w, 170*MainActivity.h);
	       score_board.setPosition(1000*MainActivity.w, 530*MainActivity.h);
	       stage = new Stage(MainActivity.screenwidth,MainActivity.screenheight,false);
	       //Gdx.input.setInputProcessor(stage);
           ball=new hardball();
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
           hardJump jump=new hardJump();
	     //  stage.addActor(image);
	       stage.addActor(ball);
	      // stage.addActor(ball.down1);
	       stage.addActor(ball.down2);
	      // stage.addActor(ball.down3);
	      // stage.addActor(ball.left1);
	       stage.addActor(ball.left2);
	       //stage.addActor(ball.left3);
	       //stage.addActor(ball.up1);
	       stage.addActor(ball.up2);
	      // stage.addActor(ball.up3);
	      
		      // stage.addActor(ball.right1);
		       stage.addActor(ball.right2);
		      // stage.addActor(ball.right3);
	       stage.addActor(hardJump.d);
	       stage.addActor(jump);
	       stage.addActor(label);
	       stage.addActor(readyLabel);
	       stage.addActor(goLabel);
	       stage.addActor(bounsLabel);
	       stage.addActor(helpLabel);
	       stage.addActor(tipsLabel);
	       stage.addActor(fire);
	       stage.addActor(ach1);
	       stage.addActor(ach2);
	    //   stage.addActor(new blockcolor());
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
		hardJump.sound[i].dispose();
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
		      label.setText(hardJump.score+"");
		      label.setPosition(1070*MainActivity.w, 400*MainActivity.h);
		      readyLabel.setText("");
		      tipsLabel.setText("对着麦克风\n吹灭火苗吧");
		      tipsLabel.setFontScale(0.7f);
		      tipsLabel.setPosition(30*hardMainActivity.w, 600*hardMainActivity.h);
		      tips.setPosition(30*hardMainActivity.w, 545*hardMainActivity.h);
		      if(hardMainActivity.first==1)
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
	     if(hardJump.fail==1)
	     {
	    	 System.out.println("碰撞错误");
	    	 gs.setScreen(gs.hf);
	     }
	     /*
	     if(hardJump.score>=30&&count<60&&hardMainActivity.bouns1==1)
	     {
	    	 count++;
	    	 bounsLabel.setText("反应敏捷");
	    	 bounsLabel.setPosition(1000, 600);
	     }
	     if(count==60)
	     {
	    	 bounsLabel.setText("");
	    	 count=0;
	     }
	     if(hardJump.score>=80&&count<60&&hardMainActivity.bouns2==1)
	     {
	    	 count++;
	    	 bounsLabel.setText("超神状态");
	    	 bounsLabel.setPosition(1000, 600);
	     }
	     */
	     if(hardJump.score==30&&hardMainActivity.bouns1==0)
	     {
	    	ach1.setVisible(true);
	     }
	     if(hardJump.score>=31)
	    	 ach1.setVisible(false);
	     if(hardJump.score==50&&hardMainActivity.bouns2==0)
	     {
	    	ach2.setVisible(true);
	     }
	     if(hardJump.score>=81)
	    	 ach2.setVisible(false);
	     if(hardJump.score==5||hardJump.score==10||hardJump.score==15||hardJump.score==20||hardJump.score==30||hardJump.score==40||hardJump.score==50||hardJump.score==60)
	     {
	    	 if(hardJump.score==5&&hasreset1==false)
	    	 {
	    		 i=0;
	    		 hasreset1=true;
	    		 fireexist=true;
	    		 speed=1;
	    	 }
	    	 if(hardJump.score==10&&hasreset2==false)
	    	 {
	    		 i=1;
	    		 hasreset2=true;
	    		 fireexist=true;
	    		 speed=1;
	    	 }
	    	 if(hardJump.score==15&&hasreset3==false)
	    	 {
	    		 i=2;
	    		 hasreset3=true;
	    		 fireexist=true;
	    		 speed=1;
	    	 }
	    	 if(hardJump.score==20&&hasreset4==false)
	    	 {
	    		 i=3;
	    		 hasreset4=true;
	    		 fireexist=true;
	    		 speed=1;
	    	 }
	    	 if(hardJump.score==30&&hasreset5==false)
	    	 {
	    		 i=4;
	    		 hasreset5=true;
	    		 fireexist=true;
	    		 speed=1;
	    	 }
	    	 if(hardJump.score==40&&hasreset6==false)
	    	 {
	    		 i=5;
	    		 hasreset6=true;
	    		 fireexist=true;
	    		 speed=1;
	    	 }
	    	 if(hardJump.score==50&&hasreset7==false)
	    	 {
	    		 i=6;
	    		 hasreset7=true;
	    		 fireexist=true;
	    		 speed=1;
	    	 }
	    	 if(hardJump.score==60&&hasreset8==false)
	    	 {
	    		 i=7;
	    		 hasreset8=true;
	    		 fireexist=true;
	    		 speed=1;
	    	 }
	    	
	     }
	     if(fireexist==true)
	     {
	    	 if(index[i]==0)
	    	 {
	    		 Random ran=new Random();
	    		 int m=ran.nextInt(300);
	    		  base.x=crowball.LBorder+100+m;
	    	 base.y=crowball.DBorder+100+m;
	    	 index[i]=1;
	    	 }
	    	// hasPump=true;
	    	// if(gap>0)
	    	// {
	    	  gap=gap+speed;
	    	  if(gap<0)
					fireexist=false;
	    	  if(gap>400)
	    		  hardJump.fail=1;
	    	  fire.setSize(2*gap,2*gap);
	    	  fire.setPosition(base.x-gap,base.y-gap);
	    	// }
	    	
	     }
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
	    	helpLabel.setPosition(400*hardMainActivity.w, 400*hardMainActivity.h);
	    	if(second==100)
	    	{
	    	    display=1;	
	    		helpR.setSize(128*hardMainActivity.w,64*hardMainActivity.h);
	    		helpR.setPosition(crowball.RBorder+300*hardMainActivity.w, 100*hardMainActivity.h);
	    		 MoveToAction moveto = Actions.moveTo(crowball.RBorder+300*hardMainActivity.w, 600*hardMainActivity.h,2f);
	    		 MoveToAction moveto1=Actions.moveTo(crowball.RBorder+300*hardMainActivity.w, 100*hardMainActivity.h);
	    		 SequenceAction alpha = Actions.sequence(moveto,moveto1);
	    		// RepeatAction r=Actions.repeat(5,alpha);
	    		 r=Actions.repeat(5,alpha);
	    		 helpR.addAction(r);
	    	}
	    	if(second==300)
	    	{
	    		helpR.setSize(0, 0);
	    		 display=2;	
	    		helpL.setSize(128*hardMainActivity.w,64*hardMainActivity.h);
	    		helpL.setPosition(50*hardMainActivity.w, 100*hardMainActivity.h);
	    		 MoveToAction moveto = Actions.moveTo(50*hardMainActivity.w, 600*hardMainActivity.h,2f);
	    		 MoveToAction moveto1=Actions.moveTo(50*hardMainActivity.w, 100*hardMainActivity.h);
	    		 SequenceAction alpha = Actions.sequence(moveto,moveto1);
	    		// RepeatAction r=Actions.repeat(5,alpha);
	    		 r=Actions.repeat(5,alpha);
	    		 helpL.addAction(r);
	    	}
	    	if(second==500)
	    	{
	    		helpL.setSize(0, 0);
	    		 display=3;	
	    		help.setSize(64*hardMainActivity.w,128*hardMainActivity.h);
	    		help.setPosition(1000*hardMainActivity.w, 500*hardMainActivity.h);
	    		 MoveToAction moveto = Actions.moveTo(1200*hardMainActivity.w, 500*hardMainActivity.h,1.5f);
	    		 MoveToAction moveto1=Actions.moveTo(1000*hardMainActivity.w, 500*hardMainActivity.h);
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
	    		help.setSize(64*hardMainActivity.w,128*hardMainActivity.h);
	    		help.setPosition(1000*hardMainActivity.w, 100*hardMainActivity.h);
	    		 MoveToAction moveto = Actions.moveTo(1200*hardMainActivity.w, 100*hardMainActivity.h,1.5f);
	    		 MoveToAction moveto1=Actions.moveTo(1000*hardMainActivity.w, 100*hardMainActivity.h);
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
				  MoveToAction moveto = Actions.moveTo(hardJump.right[2].x+64*MainActivity.w, hardJump.right[2].y-48*MainActivity.h, 0.1f);
				  hardball.right2.addAction(moveto);
			}
			else if(ifexist[3]==0&&ifexist[4]==0&&ifexist[5]==1)
			{
				ifexist[4]=1;
				ifexist[5]=0;
				  MoveToAction moveto = Actions.moveTo(hardJump.right[1].x+64*MainActivity.w, hardJump.right[1].y-48*MainActivity.h, 0.1f);
				  hardball.right2.addAction(moveto);
			}
		 }
		 if(fling==3)
		 {
			 if(ifexist[3]==1&&ifexist[4]==0&&ifexist[5]==0)
			 {
				 ifexist[3]=0;
					ifexist[4]=1;
					  MoveToAction moveto = Actions.moveTo(hardJump.right[1].x+64*MainActivity.w, hardJump.right[1].y-48*MainActivity.h, 0.05f);
					  hardball.right2.addAction(moveto);
			 }
			 else if(ifexist[3]==0&&ifexist[4]==1&&ifexist[5]==0)
			 {
				 ifexist[4]=0;
					ifexist[5]=1;
					  MoveToAction moveto = Actions.moveTo(hardJump.right[0].x+64*MainActivity.w, hardJump.right[0].y-48*MainActivity.h, 0.05f);
					  hardball.right2.addAction(moveto);
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
				  MoveToAction moveto = Actions.moveTo(hardJump.left[2].x-64*MainActivity.w, hardJump.left[2].y-48*MainActivity.h, 0.05f);
				  hardball.left2.addAction(moveto);
			}
			else if(ifexist[11]==0&&ifexist[10]==0&&ifexist[9]==1)
			{
				ifexist[10]=1;
				ifexist[9]=0;
				  MoveToAction moveto = Actions.moveTo(hardJump.left[1].x-64*MainActivity.w, hardJump.left[1].y-48*MainActivity.h, 0.05f);
				  hardball.left2.addAction(moveto);
			}
		 }
		 if(fling==3)
		 {
			 if(ifexist[11]==1&&ifexist[10]==0&&ifexist[9]==0)
			 {
				 ifexist[11]=0;
					ifexist[10]=1;
					  MoveToAction moveto = Actions.moveTo(hardJump.left[1].x-64*MainActivity.w, hardJump.left[1].y-48*MainActivity.h, 0.05f);
					  hardball.left2.addAction(moveto);
			 }
			 else if(ifexist[11]==0&&ifexist[10]==1&&ifexist[9]==0)
			 {
				 ifexist[10]=0;
					ifexist[9]=1;
					  MoveToAction moveto = Actions.moveTo(hardJump.left[0].x-64*MainActivity.w, hardJump.left[0].y-48*MainActivity.h, 0.05f);
					  hardball.left2.addAction(moveto);
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
				  MoveToAction moveto = Actions.moveTo(hardJump.up[2].x-48*MainActivity.w, hardJump.up[2].y+64*MainActivity.h, 0.05f);
				  hardball.up2.addAction(moveto);
			}
			else if(ifexist[0]==1&&ifexist[1]==0&&ifexist[2]==0)
			{
				ifexist[1]=1;
				ifexist[0]=0;
				  MoveToAction moveto = Actions.moveTo(hardJump.up[1].x-48*MainActivity.w, hardJump.up[1].y+64*MainActivity.h, 0.05f);
				  hardball.up2.addAction(moveto);
			}
		 }
		 if(fling==1)
		 {
			 if(ifexist[0]==0&&ifexist[1]==1&&ifexist[2]==0)
			 {
				 ifexist[1]=0;
					ifexist[0]=1;
					  MoveToAction moveto = Actions.moveTo(hardJump.up[0].x-48*MainActivity.w, hardJump.up[0].y+64*MainActivity.h, 0.05f);
					  hardball.up2.addAction(moveto);
			 }
			 else if(ifexist[0]==0&&ifexist[1]==0&&ifexist[2]==1)
			 {
				 ifexist[1]=1;
					ifexist[2]=0;
					  MoveToAction moveto = Actions.moveTo(hardJump.up[1].x-48*MainActivity.w, hardJump.up[1].y+64*MainActivity.h, 0.05f);
					  hardball.up2.addAction(moveto);
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
				  MoveToAction moveto = Actions.moveTo(hardJump.down[2].x-48*MainActivity.w, hardJump.down[2].y-64*MainActivity.h,0.05f);
				  hardball.down2.addAction(moveto);
			}
			else if(ifexist[8]==1&&ifexist[7]==0&&ifexist[6]==0)
			{
				ifexist[7]=1;
				ifexist[8]=0;
				  MoveToAction moveto = Actions.moveTo(hardJump.down[1].x-48*MainActivity.w, hardJump.down[1].y-64*MainActivity.h, 0.05f);
				  hardball.down2.addAction(moveto);
			}
		 }
		 if(fling==1)
		 {
			 if(ifexist[8]==0&&ifexist[7]==1&&ifexist[6]==0)
			 {
				 ifexist[7]=0;
					ifexist[8]=1;
					  MoveToAction moveto = Actions.moveTo(hardJump.down[0].x-48*MainActivity.w, hardJump.down[0].y-64*MainActivity.h, 0.05f);
					  hardball.down2.addAction(moveto);
			 }
			 else if(ifexist[8]==0&&ifexist[7]==0&&ifexist[6]==1)
			 {
				 ifexist[7]=1;
					ifexist[6]=0;
					  MoveToAction moveto = Actions.moveTo(hardJump.down[1].x-48*MainActivity.w, hardJump.down[1].y-64*MainActivity.h, 0.05f);
					  hardball.down2.addAction(moveto);
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
