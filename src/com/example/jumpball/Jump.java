package com.example.jumpball;

import java.util.Random;

import android.content.Intent;
import android.graphics.PointF;
import android.os.Vibrator;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.AlphaAction;
import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction;
import com.badlogic.gdx.scenes.scene2d.actions.ParallelAction;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class Jump extends Actor
{
	float x,y;
	int corwG;
	boolean ifup=false;
	static int time=0;
	public static boolean setscore1=false;
	public static boolean setscore2=false;
	public static boolean setscore3=false;
	public static boolean setscore4=false;
	public static  boolean setscore5=false;
	public static boolean setscore6=false;
	public static boolean setscore7=false;
	public boolean hasreset=false;
	public boolean hasreset1=false;
	public boolean hasreset2=false;
	public boolean hasreset3=false;
	public boolean hasreset4=false;
	public boolean hasreset5=false;
	public boolean hasreset6=false;
	TextureRegion[] split;
	static Animation splitblock;
	static boolean hasCorw=false;
	static float AnimationX;
	static float AnimationY;
	static int first=0;
	static boolean hasBreaken=false;
	//�ж��Ƿ�ײ����ǽ��
	static boolean playAnimation=false;
	ParticleEffect effect;
	int crowX=0;
	static int score=0;
	SpriteBatch sb;
	static Image crow;
	Sound crowfly1,crowfly2,crowfly3;
	//��ʾcrowҪײ���ĸ�ש�飬
	static int crowTo=-1;
	private static int flag=0;
	private static int count=1;
	static float current_x,current_y,goto_x,goto_y;
	static int m,n;
	int j;
	static int stay=0;//��ʾ���ڱ߽�ͣ��һ��ʱ��
	int[] flagcount=new int[12];
	static int gotoX,gotoY;
	//dire������һ����ײ��ķ���0��ʾ��1��ʾ�£�2��ʾ�ң�3��ʾ��num��ʾ�±�
	static int num,dire;
	static int ifdraw=0;
	//��������е�ifdraw=1��ʾ�����˱߽磬��Ball��count=0,��ʾû�а����κΰ�������ʱ���
	public static float ballX,ballY;
	public static int left1,up1,down1,right1,left2,up2,down2,right2,left3,up3,down3,right3;
	public static int pleft1,pup1,pdown1,pright1,pleft2,pup2,pdown2,pright2,pleft3,pup3,pdown3,pright3;
	Texture jumpball;
	private int Lboder=(int)Ball.LBorder;
	private int Rboder=(int)Ball.RBorder;
	private int Uboder=(int)Ball.UBorder;
	private int Dboder=(int)Ball.DBorder;
	static int pre;
	static int ifcontinue=0;
	PointF point=new PointF();
	static Sound[] sound=new Sound[26];
	Random ran=new Random();
	static int start=0;
	//private static  PointF up[],down[],left[],right[];
	static PointF[] up=new PointF[3];
	static PointF[] down=new PointF[3];
	static PointF[] left=new PointF[3];
	static PointF[] right=new PointF[3];
	Random sa=new Random();
	   int fa=sa.nextInt()%3;
	static  enum STATE {
	       Left,Right,Up,Down
	   };
	 static   int crowHasExist=0;
	  static  STATE state;
	   static Image d;
	   //���������֣�ֻ��Ϊ0��ʱ��Ų���
	   static int playcount=0;
	   static int r=0;
	   //���岥�ŵ����ڴ���
	   static int lyric=0;
	   static int lyriccount=0;
	   static int fail=0;
	   static int[] l;
	   TextureRegion[] CrowAnimation;
	   Animation CrowFly;
	   TextureRegion Frame;
	   SpriteBatch batch;
	   float stateTime=0f;
	   static Image crow2;
	   int hasUp=0;
	   static boolean  hasCrow=false;
	   static boolean ifCrowExist=true;
	   static int CrowHitBall=0;
	   static int hasGoToRight=0;
 public Jump()
 {
	 crowfly1=Gdx.audio.newSound(Gdx.files.internal("music/crow_fly1.wav"));
		crowfly2=Gdx.audio.newSound(Gdx.files.internal("music/crow_fly2.wav"));
		crowfly3=Gdx.audio.newSound(Gdx.files.internal("music/crow_fly3.wav"));
	 Texture.setEnforcePotImages(false);
	 split=new TextureRegion[5];
	 TextureRegion r1=new TextureRegion(new Texture(Gdx.files.internal("crow/brick1.png")));
	 TextureRegion r2=new TextureRegion(new Texture(Gdx.files.internal("crow/brick2.png")));
	 TextureRegion r3=new TextureRegion(new Texture(Gdx.files.internal("crow/brick3.png")));
	 TextureRegion r4=new TextureRegion(new Texture(Gdx.files.internal("crow/brick4.png")));
	 TextureRegion r5=new TextureRegion(new Texture(Gdx.files.internal("crow/brick5.png")));
     Texture t= new Texture(Gdx.files.internal("block1.png"));
     
	 split[0]=r1;
	 split[1]=r2;
	 split[2]=r3;
	 split[3]=r4;
	 split[4]=r5;
	 splitblock=new Animation(0.02f,split);
	 splitblock.setPlayMode(splitblock.LOOP_PINGPONG);
	 
	 CrowAnimation=new TextureRegion[2];
	 TextureRegion region1=new TextureRegion(new Texture(Gdx.files.internal("crow/crow_01.png")));
	 TextureRegion region2=new TextureRegion(new Texture(Gdx.files.internal("crow/crow_02.png")));
	 CrowAnimation[0]=region1;
	 CrowAnimation[1]=region2;
	 CrowFly=new Animation(0.02f,CrowAnimation);
	 CrowFly.setPlayMode(CrowFly.LOOP_PINGPONG);
	 batch = new SpriteBatch();
	 crow=new Image(new Texture(Gdx.files.internal("crow/crow_01.png")));
	 crow2=new Image(new Texture(Gdx.files.internal("crow/crow_02.png")));
	 crow.setSize(128*MainActivity.w, 64*MainActivity.h);
	 crow2.setSize(128*MainActivity.w, 64*MainActivity.h);
	 crow.setX(-129);
	 crow.setY(-10);
	 crow2.setX(-129);
	 crow2.setY(-10);
	 music.initStar();
	 music.initMusic();
	 sb=new SpriteBatch();
	 effect = new ParticleEffect();
		effect.load(Gdx.files.internal("test.p"),Gdx.files.internal(""));
	 Random r=new Random();
	 int n=r.nextInt(7);
	 String[] s1=music.s[n].split(" ");
     l=new int[s1.length];
     lyriccount=s1.length;
     for(int i=0;i<=s1.length-1;i++)
     {
     	l[i]=Integer.parseInt(s1[i]);
     }
	// jumpball=new Texture(Gdx.files.internal("ball_confus.png"));
	 //Texture txtup=new Texture(Gdx.files.internal("ball_confus.png"));
	//	Texture txtdown=new Texture(Gdx.files.internal("ball_confus.png"));
	//	TextureRegionDrawable trd=new TextureRegionDrawable(new TextureRegion(txtdown));
		//TextureRegionDrawable tru=new TextureRegionDrawable(new TextureRegion(txtup));
		//d=new ImageButton(tru,trd);
		d=new Image(new Texture(Gdx.files.internal("crow/crow_ball.png")));
		d.setSize(64*MainActivity.w, 64*MainActivity.h);
		initMusic();
	// this.x=Lboder;
	// this.y=(Uboder+Dboder)/2;
	 for(int i=0;i<3;i++)
	 {
		 int width=(int) (Lboder+(2*i+1)*80*MainActivity.w-32*MainActivity.w);
		 int height=Uboder;
		 PointF point=new PointF(width,height);
		 up[i]=point;
		 
		 width=Lboder;
		 height=(int) (Dboder+(2*i+1)*80*MainActivity.h-32*MainActivity.h);
		 point=new PointF(width,height);
		 left[i]=point;
		 
		 width=(int) (Lboder+(2*i+1)*80*MainActivity.w-32*MainActivity.w);
		 height=Dboder;
		 point=new PointF(width,height);
		 down[i]=point;
		 
		 width=Rboder;
		 height=(int) (Dboder+(2*i+1)*80*MainActivity.h-32*MainActivity.h);
		 point=new PointF(width,height);
		 right[i]=point;
	 }
	 this.x=left[1].x;
	 this.y=left[1].y;
	 this.state=STATE.Left;
	 d.setPosition(650*MainActivity.w, 390*MainActivity.h);
	
 }
 public void initMusic()
 {
	 sound[0]=Gdx.audio.newSound(Gdx.files.internal("soundA.mp3"));
		sound[1]=Gdx.audio.newSound(Gdx.files.internal("soundB.mp3"));
		sound[2]=Gdx.audio.newSound(Gdx.files.internal("soundC.mp3"));
		sound[3]=Gdx.audio.newSound(Gdx.files.internal("soundD.mp3"));
		sound[4]=Gdx.audio.newSound(Gdx.files.internal("soundE.mp3"));
		sound[5]=Gdx.audio.newSound(Gdx.files.internal("soundF.mp3"));
		sound[6]=Gdx.audio.newSound(Gdx.files.internal("soundG.mp3"));
		sound[7]=Gdx.audio.newSound(Gdx.files.internal("soundH.mp3"));
		sound[8]=Gdx.audio.newSound(Gdx.files.internal("soundI.mp3"));
		sound[9]=Gdx.audio.newSound(Gdx.files.internal("soundJ.mp3"));
		sound[10]=Gdx.audio.newSound(Gdx.files.internal("soundK.mp3"));
		sound[11]=Gdx.audio.newSound(Gdx.files.internal("soundL.mp3"));
		sound[12]=Gdx.audio.newSound(Gdx.files.internal("soundM.mp3"));
		sound[13]=Gdx.audio.newSound(Gdx.files.internal("soundN.mp3"));
		sound[14]=Gdx.audio.newSound(Gdx.files.internal("soundO.mp3"));
		sound[15]=Gdx.audio.newSound(Gdx.files.internal("soundP.mp3"));
		sound[16]=Gdx.audio.newSound(Gdx.files.internal("soundQ.mp3"));
		sound[17]=Gdx.audio.newSound(Gdx.files.internal("soundR.mp3"));
		sound[18]=Gdx.audio.newSound(Gdx.files.internal("soundS.mp3"));
		sound[19]=Gdx.audio.newSound(Gdx.files.internal("soundT.mp3"));
		sound[20]=Gdx.audio.newSound(Gdx.files.internal("soundU.mp3"));
		sound[21]=Gdx.audio.newSound(Gdx.files.internal("soundV.mp3"));
		sound[22]=Gdx.audio.newSound(Gdx.files.internal("soundW.mp3"));
		sound[23]=Gdx.audio.newSound(Gdx.files.internal("soundX.mp3"));
		sound[24]=Gdx.audio.newSound(Gdx.files.internal("soundY.mp3"));
		sound[25]=Gdx.audio.newSound(Gdx.files.internal("soundZ.mp3"));
 }
	@Override
	public void act(float arg0) {
		// TODO Auto-generated method stub
		super.act(arg0);
	}
   public void justflag()
   {
	   if(d.getY()>=Uboder)
		{
		   if(ifcontinue == 1){
		   ifdraw=1;
			if(d.getX()<=Lboder+160*MainActivity.w)
			{
				up1=1;
				
				if(FirstGame.ifexist[0]==1&&playcount==0)
				{
					score++;
					int n=ran.nextInt(25);
					//n=music.star[lyric%lyriccount];
					n=l[lyric%lyriccount];
					lyric++;
					sound[n].play();
					playcount++;
				}
				if(FirstGame.ifexist[0]==0)
					fail=1;
			}else if(d.getX()<=Lboder+320*MainActivity.w)
			{
				up2=1;
				 
				if(FirstGame.ifexist[1]==1&&playcount==0)
				{
					score++;
					int n=ran.nextInt(25);
					//n=music.star[lyric%lyriccount];
					n=l[lyric%lyriccount];
					lyric++;
					sound[n].play();
					playcount++;
				}
				if(FirstGame.ifexist[1]==0)
					fail=1;
			}
			else
			{
				up3=1;
				 
				if(FirstGame.ifexist[2]==1&&playcount==0)
				{
					score++;
					int n=ran.nextInt(25);
					//n=music.star[lyric%lyriccount];
					n=l[lyric%lyriccount];
					lyric++;
					sound[n].play();
					playcount++;
				}
				if(FirstGame.ifexist[2]==0)
					fail=1;
			}
			ifcontinue = 0;
		} 
		}
	    if(d.getX()<=Lboder)
	    {
	    	if(ifcontinue == 1){
	    	ifdraw=1;
	    	if(d.getY()<=Dboder+160*MainActivity.h)
			{
				left1=1;
				if(FirstGame.ifexist[9]==1&&playcount==0)
				{
					score++;
					int n=ran.nextInt(25);
					//n=music.star[lyric%lyriccount];
					n=l[lyric%lyriccount];
					lyric++;
					sound[n].play();
					playcount++;
				}
				if(FirstGame.ifexist[9]==0)
					fail=1;
				
			}else if(d.getY()<=Dboder+320*MainActivity.h)
			{
				left2=1;
				if(FirstGame.ifexist[10]==1&&playcount==0)
				{
					score++;
					int n=ran.nextInt(25);
					//n=music.star[lyric%lyriccount];
					n=l[lyric%lyriccount];
					lyric++;
					sound[n].play();
					playcount++;
				}
				if(FirstGame.ifexist[10]==0)
					fail=1;
			}
			else
			{
				left3=1;
				if(FirstGame.ifexist[11]==1&&playcount==0)
				{
					score++;
					int n=ran.nextInt(25);
					//n=music.star[lyric%lyriccount];
					n=l[lyric%lyriccount];
					lyric++;
					sound[n].play();
					playcount++;
				}
				if(FirstGame.ifexist[11]==0)
					fail=1;
			}
	    	ifcontinue = 0;
	    	}
	    }
	    if(d.getX()>=Rboder)
	    {
	    	ifdraw=1;
	    	
	    	if(ifcontinue == 1){
	    	if(d.getY()<=Dboder+160*MainActivity.h)
			{
				right1=1;
				if(FirstGame.ifexist[5]==1&&playcount==0)
				{
					score++;
					int n=ran.nextInt(25);
					//n=music.star[lyric%lyriccount];
					n=l[lyric%lyriccount];
					lyric++;
					sound[n].play();
					playcount++;
				}
				if(FirstGame.ifexist[5]==0)
					fail=1;
			}else if(d.getY()<=Dboder+320*MainActivity.h)
			{
				right2=1;
				if(FirstGame.ifexist[4]==1&&playcount==0)
				{
					score++;
					int n=ran.nextInt(25);
				//	n=music.star[lyric%lyriccount];
					n=l[lyric%lyriccount];
					lyric++;
					sound[n].play();
					playcount++;
				}
				if(FirstGame.ifexist[4]==0)
					fail=1;
			}
			else
			{
				right3=1;
				if(FirstGame.ifexist[3]==1&&playcount==0)
				{
					score++;
					int n=ran.nextInt(25);
					//n=music.star[lyric%lyriccount];
					n=l[lyric%lyriccount];
					lyric++;
					sound[n].play();
					playcount++;
				}
				if(FirstGame.ifexist[3]==0)
					fail=1;
			}
	    	ifcontinue = 0;
	    	}
	    }
	    
	    if(d.getY()<=Dboder)
	    {
	    	ifdraw=1;
	    	if(ifcontinue == 1){
	    	
	    	if(d.getX()<=Lboder+160*MainActivity.w)
			{
				down1=1;
				if(FirstGame.ifexist[8]==1&&playcount==0)
				{
					score++;
					int n=ran.nextInt(25);
					//n=music.star[lyric%lyriccount];
					n=l[lyric%lyriccount];
					lyric++;
					sound[n].play();
					playcount++;
				}
				if(FirstGame.ifexist[8]==0)
					fail=1;
			}else if(d.getX()<=Lboder+320*MainActivity.w)
			{
				down2=1;
				if(FirstGame.ifexist[7]==1&&playcount==0)
				{
					score++;
					int n=ran.nextInt(25);
					//n=music.star[lyric%lyriccount];
					n=l[lyric%lyriccount];
					lyric++;
					sound[n].play();
					playcount++;
				}
				if(FirstGame.ifexist[7]==0)
					fail=1;
			}
			else
			{
				down3=1;
				if(FirstGame.ifexist[6]==1&&playcount==0)
				{
					score++;
					int n=ran.nextInt(25);
					//n=music.star[lyric%lyriccount];
					n=l[lyric%lyriccount];
					lyric++;
					sound[n].play();
					playcount++;
				}
				if(FirstGame.ifexist[6]==0)
					fail=1;
			}
	    	ifcontinue = 0;
	    	}
	    }
   }
  
   public void drawLine()
   {
		   if(d.getX()<=Lboder)
	   {
		   state=STATE.Left;
		   justflag();
		   start++;
	   }
	   if(d.getX()>=Rboder)
	   {
		   state=STATE.Right;
		   justflag();
		   start++;
	   }
	   if(d.getY()>=Uboder)
	   {
		   state=STATE.Up;
		  justflag();
		   start++;
	   }
	   if(d.getY()<=Dboder)
	   {
		   state=STATE.Down;
		   justflag();
		   start++;
	   }
	   if(ifcontinue==0)
	   {
		   if(start==0||start==1)
		   {
			   Random ran=new Random();
		     m=ran.nextInt(3);
		     n=ran.nextInt(3);
		   }
		   //n��ʾ��λ��m��ʾ�ڼ���
		  // start++;
		  num=m;
		   if(state==STATE.Left)
		   {
			   if(n==0)
			   {
				   point=right[m];
				   dire=2;
			   }
			   if(n==1)
			   {
				   if(pre==2){
					   point=down[m];
					   dire = 1;
				   }
				    else
				    {
				    	 point=up[m];
				        dire=3;
				    }
				  
			   }
			   if(n==2)
			   {
				   if(pre==0){
					   point=up[m];
					   dire=3;
				   }
				   else
				   { 
					     point=down[m];
				        dire=1;
				   }
				 
			   }
			 if(stay>=10)
			   {	 
				 pre=m;
				 first=1;
				  playAnimation=false;
				   stay=0;
				   start=0;
				  
				   float length=(point.x-d.getX())*(point.x-d.getX())+(point.y-d.getY())*(point.y-d.getY());
				   length=(float) Math.pow(length, 0.5f);
				   MoveToAction moveto = Actions.moveTo(point.x, point.y, length/(300+score*4));
				   //SequenceAction sequenceAction = Actions.sequence(moveto1,moveto); 
				   d.addAction(moveto);
			     ifcontinue=1;
			   }
			   else
			   {
				   AnimationX=d.getX();
				   AnimationY=d.getY();
				   playAnimation=true;
				   stay++;
			   }
			   
		   }
		   else if(state==STATE.Right)
			   {
				   if(n==0)
				   {
					   point=left[m];
					   dire=0;
				   }
				   if(n==1)
				   {
					   if(pre==2){
						   point=down[m];
						   dire=1;
					   }
					   else
					   {
						    point=up[m];
					   dire=3;
					   }
					  
				   }
				   if(n==2)
				   {
					   if(pre==0){
						   point=up[m];
						   dire=3;
					   }
					   else
					   {
						    point=down[m];
						    dire=1;
					   }
				   }
				   if(stay>=10)
				   {
					   pre=m;
					   first=1;
					   playAnimation=false;
					   stay=0;
					   start=0;
					
					   float length=(point.x-d.getX())*(point.x-d.getX())+(point.y-d.getY())*(point.y-d.getY());
					   length=(float) Math.pow(length, 0.5f);
					   MoveToAction moveto = Actions.moveTo(point.x, point.y, length/(300+score*4));
				     d.addAction(moveto);
				     ifcontinue=1;
				   }
				   else
				   {
					   AnimationX=d.getX();
					   AnimationY=d.getY();
					   playAnimation=true;
					  stay++;
				   }
				  
			   }
			   else if(state==STATE.Down)
				   {
					   if(n==0)
					   {
						   point=up[m];
						  dire=3;
					   }
					   if(n==1)
					   {
						   if(pre==0){
							   point=right[m];
							   dire=2;
						   }
						   else
						   {
							      point=left[m];
						   dire=0;
						   }
						
					   }
					   if(n==2)
					   {
						   if(pre==2){
							   point=left[m];
							   dire=0;
						   }
						   else
						   {
							    point=right[m];
						       dire=2;
						   } 
					   }
					   if(stay>=10)
					   {
						   pre=m;
						   first=1;
						   playAnimation=false;
						   stay=0;
						   start=0;
						
						   float length=(point.x-d.getX())*(point.x-d.getX())+(point.y-d.getY())*(point.y-d.getY());
						   length=(float) Math.pow(length, 0.5f);
						   MoveToAction moveto = Actions.moveTo(point.x, point.y, length/(300+score*4));
					     d.addAction(moveto);
					     ifcontinue=1;
					   }
					   else
					   {
						   AnimationX=d.getX();
						   AnimationY=d.getY();
						   playAnimation=true;
						   stay++;
					   }
					  
				   }
				   else if(state==STATE.Up)
					   {
						   if(n==0)
						   {
							   point=down[m];
							  dire=1;
						   }
						   if(n==1)
						   {
							  
							   if(pre==0){
								
								   point=right[m];
								    dire=2;
							   }
							   else
							   {
								    point=left[m];
							   dire=0;
							   }
							  
						   }
						   if(n==2)
						   {
							  if(pre==2){
								  point=left[m];
								  dire=0;
							  }
							  else
							  {
								   point=right[m];
								   dire=2;
							  }
							  
						   }
						   if(stay>=10)
						   {
							   pre=m;
							   first=1;
							   playAnimation=false;
							   stay=0;
							   start=0;
							
							   float length=(point.x-d.getX())*(point.x-d.getX())+(point.y-d.getY())*(point.y-d.getY());
							   length=(float) Math.pow(length, 0.5f);
							   MoveToAction moveto = Actions.moveTo(point.x, point.y, length/(300+score*4));
						     d.addAction(moveto);
						     ifcontinue=1;
						   }
						   else
						   {
							   AnimationX=d.getX();
							   AnimationY=d.getY();
							   System.out.println("����0");
							   playAnimation=true;
							 stay++;
						   }
					   }
	   }
	  
   }
   public void crowAction(int i)
   {
	   System.out.println("i="+i);
	   System.out.println("crowTo="+crowTo);
	   //System.out.println("crowx="+crow.getX());
	   if(crow.getX()+128*MainActivity.w>=Ball.LBorder-64*MainActivity.w)
	   {
		   crowHasExist=1;
		   if(i==2)
		   {
			   if(crow.getX()+128*MainActivity.w>=Ball.RBorder+192*MainActivity.w)
			   {
				   hasGoToRight=1;
			   }
			   if(FirstGame.ifexist[11]==1&&crow.getX()<=Ball.LBorder)
			   {
				   //splitblock.
				   crowHasExist=0;
				 //  System.out.println("��ѻ���м�");
				    hasBreaken=true;
			        crowTo=11;
			        hasCorw=false;
			        hasCrow=false;
			   }
			   else if(crow.getX()+128*MainActivity.w>=Ball.RBorder+64*MainActivity.w&&FirstGame.ifexist[3]==1&&hasGoToRight==0&&hasBreaken==false)
			   {
				   crowHasExist=0;
				   System.out.println("��ѻ���м�");
				   hasGoToRight=1;
				   FirstGame.query=true;
				   crowTo=3;
				   hasCorw=false;
				   hasCrow=false;
			   }
			   else
			   {
				   
				   if(crowX%2==0)
				   {
					 //  System.out.println("��ʾ����1");
					   crow2.setVisible(false);
					   crow.setVisible(true);
					   crowX=crowX+3;
					    crow.setX(crowX);
		    	   crow.setY(Ball.DBorder+418*MainActivity.h);
		    	   
				   }
				   else
				   { 
					  // System.out.println("��ʾ����2");
					   crow.setVisible(false);
				      crow2.setVisible(true);
				      crowX=crowX+3;
					   crow2.setX(crowX);
			    	   crow2.setY(Ball.DBorder+418*MainActivity.h);
				   }
				   if(d.getX()>=crow.getX()-64*MainActivity.w&&d.getX()<=crow.getX()+128*MainActivity.w&&d.getY()>crow.getY()-64*MainActivity.h&&d.getY()<=crow.getY()+64*MainActivity.w&&ifCrowExist)
				   {
					   crowHasExist=0;
					   CrowHitBall=1;
					   hasCorw=false;
					   hasCrow=false;
				   }
			   }
			  
		   }
		   if(i==0)
		   {
			   if(crow.getX()+128*MainActivity.w>=Ball.RBorder+192*MainActivity.w)
			   {
				   hasGoToRight=1;
			   }
			   if(FirstGame.ifexist[9]==1&&crow.getX()<=Ball.LBorder)
			   {
				   crowHasExist=0;
				  // System.out.println("��ѻ���м�");
				    hasBreaken=true;
			        crowTo=9;
			        hasCorw=false;
			        hasCrow=false;
			   }
			   else if(crow.getX()+128*MainActivity.w>=Ball.RBorder+64*MainActivity.w&&FirstGame.ifexist[5]==1&&hasGoToRight==0&&hasBreaken==false)
			   {
				   crowHasExist=1;
				 //  System.out.println("FirstGame.ifexist[5]="+FirstGame.ifexist[5]);
				   System.out.println("��ѻ���м�");
				   hasGoToRight=1;
				   FirstGame.query=true;
				   crowTo=5;
				   hasCorw=false;
				   hasCrow=false;
			   }
			   else
			   {
				   
				   if(crowX%2==0)
				   {
					  // System.out.println("��ʾ����1");
					   crow2.setVisible(false);
					   crow.setVisible(true);
					   crowX=crowX+3;
					    crow.setX(crowX);
		    	   crow.setY(Ball.DBorder+58*MainActivity.h);
		    	   
				   }
				   else
				   { 
					  // System.out.println("��ʾ����2");
					   crow.setVisible(false);
				      crow2.setVisible(true);
				      crowX=crowX+3;
					   crow2.setX(crowX);
			    	   crow2.setY(Ball.DBorder+58*MainActivity.h);
				   }
				   if(d.getX()>=crow.getX()-64*MainActivity.w&&d.getX()<=crow.getX()+128*MainActivity.w&&d.getY()>crow.getY()-64*MainActivity.h&&d.getY()<=crow.getY()+64*MainActivity.w&&ifCrowExist)
				   {
					   crowHasExist=1;
					   CrowHitBall=1;
					   hasCorw=false;
					   hasCrow=false;
				   }
			   }
			  
		   }
		   if(i==1)
		   {
			   if(crow.getX()+128*MainActivity.w>=Ball.RBorder+192*MainActivity.w)
			   {
				   hasGoToRight=1;
			   }
			   if(FirstGame.ifexist[10]==1&&crow.getX()<=Ball.LBorder)
			   {
				   crowHasExist=0;
				  // System.out.println("��ѻ���м�");
				    hasBreaken=true;
			        crowTo=10;
			        hasCorw=false;
			        hasCrow=false;
			   }
			   else if(crow.getX()+128*MainActivity.w>=Ball.RBorder+64*MainActivity.w&&FirstGame.ifexist[4]==1&&hasGoToRight==0&&hasBreaken==false)
			   {
				   crowHasExist=0;
				   System.out.println("��ѻ���м�");
				   hasGoToRight=1;
				   FirstGame.query=true;
				   crowTo=4;
				   hasCorw=false;
				   hasCrow=false;
			   }
			   else
			   {
				   
				   if(crowX%2==0)
				   {
					  // System.out.println("��ʾ����1");
					   crow2.setVisible(false);
					   crow.setVisible(true);
					   crowX=crowX+3;
					    crow.setX(crowX);
		    	   crow.setY(Ball.DBorder+238*MainActivity.h);
		    	   
				   }
				   else
				   { 
					  // System.out.println("��ʾ����2");
					   crow.setVisible(false);
				      crow2.setVisible(true);
				      crowX=crowX+3;
					   crow2.setX(crowX);
			    	   crow2.setY(Ball.DBorder+238*MainActivity.h);
				   }
				   if(d.getX()>=crow.getX()-64*MainActivity.w&&d.getX()<=crow.getX()+128*MainActivity.w&&d.getY()>crow.getY()-64*MainActivity.h&&d.getY()<=crow.getY()+64*MainActivity.w&&ifCrowExist)
				   {
					   crowHasExist=0;
					   CrowHitBall=1;
					   hasCorw=false;
					   hasCrow=false;
				   }
			   }
			  
		   }
		  
	   }
	   
	   else
	   {
		  // System.out.println("��û������");
		   if(crowX%2==0)
		   {
			   //System.out.println("��ʾ����1");
			    crow2.setVisible(false);
			    crow.setVisible(true);
			    crowX=crowX+3;
			    crow.setX(crowX);
			    
    	        crow.setY(Ball.DBorder+(180*i+58)*MainActivity.h);
    	   
		   }
		   else
		   { 
			  // System.out.println("��ʾ����2");
			   crow.setVisible(false);
		      crow2.setVisible(true);
		      crowX=crowX+3;
			   crow2.setX(crowX);
	    	   crow2.setY(Ball.DBorder+(180*i+58)*MainActivity.h);
		   }
	   }   
   }
   
	@Override
	public void draw(SpriteBatch batch, float parentAlpha) {
		// TODO Auto-generated method stub
		// float duration = MathUtils.random(3, 10);
		
		 if(FirstGame.playMusic==-1)
		 {
			 if(hasCrow==false)
			 {
				// System.out.println("С�򲻿���ʵ����ͣ����");
				 drawLine();
				 if(ifup==true)
				 {
					  float length=(point.x-d.getX())*(point.x-d.getX())+(point.y-d.getY())*(point.y-d.getY());
				   length=(float) Math.pow(length, 0.5f);
				 MoveToAction moveto = Actions.moveTo(point.x, point.y, length/(300+score*4));
			     d.addAction(moveto);
			     ifup=false;
				 }
				
			 }
			 
			 if(hasCrow==true)
		 {
				 ifup=true;
				 time++;
				// System.out.println("С�����ʵ����ͣ����");
				 //�ⲿ��ʵ����ͣС��Ĺ���
				 if(time%40==0)
				 crowfly1.play();
				 if(MainActivity.direcation!=MainActivity.Direcation.Up)
				 {
					 if(hasUp==1&&point.x!=0)
					 {
						 float length=(point.x-d.getX())*(point.x-d.getX())+(point.y-d.getY())*(point.y-d.getY());
						   length=(float) Math.pow(length, 0.5f);
						 MoveToAction moveto = Actions.moveTo(point.x, point.y, length/(300+score*4));
					     d.addAction(moveto);
					     hasUp=0;
					 }
					 
					     drawLine();
				 }
				 else
				 {
					 hasUp=1;
					 //����С��ֹͣ
					 MoveToAction moveto = Actions.moveTo(d.getX(),d.getY(), 0.1f);
				     d.addAction(moveto);
				 }
	 }
			
			 
	       if(score>=5&&score<15)
	       { 
	    	   if(setscore1==false)
	    	   {
	    		    hasCrow=true;
	    		    setscore1=true;
	    	   }
	    	  
	    	  if(crow.getX()>=MainActivity.screenwidth)
	    	  {
	    		   hasCorw=false;
	    		   hasCrow=false;
	    	  }	 
	    	   if((score==5)&&hasCorw==false)
	    	   {
	    		   crow.setX(-129);
	    		   crow2.setX(-129);
	    		   crowX=0;
	    		  // System.out.println("����Ϊ20");
	    		   hasCorw=true;
	    		    Random ran=new Random();
	    	       int c=ran.nextInt(3);
	    	       corwG=c;
	    	       crowAction(c);
	    	   }
	    	   else
	    	   {
	    		   crowAction(corwG);
	    	   }
	    	  
	       }
	       
	       if(score==15	&&hasreset==false)																																																																																																																								
 		  {
	    	   hasCrow=false;
	    	   hasreset=true;
 			  FirstGame.stage.addActor(crow);
	    		   FirstGame.stage.addActor(crow2);
	    		   FirstGame.hasXY=false;
	    		   FirstGame.query=true;
	    		   FirstGame.block_count=0;
	    		   hasBreaken=false;
	    		   this.hasCorw=false;
	    		   this.hasGoToRight=0;
	    		   this.crowHasExist=-1;
	    		  crowTo=-2;
	    		   FirstGame.playAnimation_split=0;
	    		   crow.setX(-129);
	    		   crow2.setX(-129);
	    		   crowX=0;
 		  }
	       
	       
	       
	       if(score>=15&&score<25)
	       {
	    	   //hasCrow=true;
	    	   if(setscore2==false)
	    	   {
	    		    hasCrow=true;
	    		    setscore2=true;
	    	   }
	    	  if(crow.getX()>=MainActivity.screenwidth)
	    	  {
	    		   hasCorw=false;
	    		   hasCrow=false;
	    	  }  
	    	   if((score==15)&&hasCorw==false)
	    	   {
	    		   crow.setX(-129);
	    		   crow2.setX(-129);
	    		   crowX=0;
	    		   hasCorw=true;
	    		    Random ran=new Random();
	    	       int c=ran.nextInt(3);
	    	       corwG=c;
	    	       crowAction(c);
	    	  
	    	   }
	    	   else
	    	   {
	    		 //  System.out.println("corg="+corwG);
	    		   crowAction(corwG);
	    	   }
	    	  
	       }
	       if(score==25	&&hasreset1==false)																																																																																																																								
	 		  {
	    	   hasCrow=false;
		    	   hasreset1=true;
	 			  FirstGame.stage.addActor(crow);
		    		   FirstGame.stage.addActor(crow2);
		    		   FirstGame.hasXY=false;
		    		   FirstGame.query=true;
		    		   FirstGame.block_count=0;
		    		   hasBreaken=false;
		    		   this.hasCorw=false;
		    		   this.hasGoToRight=0;
		    		   this.crowHasExist=-1;
		    		  crowTo=-2;
		    		   FirstGame.playAnimation_split=0;
		    		   crow.setX(-129);
		    		   crow2.setX(-129);
		    		   crowX=0;
	 		  }
	       
	       
	       
	       if(score>=25&&score<35)
	       {
	    	   //hasCrow=true;
	    	   if(setscore3==false)
	    	   {
	    		    hasCrow=true;
	    		    setscore3=true;
	    	   }
	    	  if(crow.getX()>=MainActivity.screenwidth)
	    	  {
	    		   hasCorw=false;
	    		   hasCrow=false;
	    	  }  
	    	   if((score==25)&&hasCorw==false)
	    	   {
	    		   crow.setX(-129);
	    		   crow2.setX(-129);
	    		   crowX=0;
	    		   hasCorw=true;
	    		    Random ran=new Random();
	    	       int c=ran.nextInt(3);
	    	       corwG=c;
	    	       crowAction(c);
	    	  
	    	   }
	    	   else
	    	   {
	    		 //  System.out.println("corg="+corwG);
	    		   crowAction(corwG);
	    	   }
	    	  
	       }
	       if(score==35	&&hasreset2==false)																																																																																																																								
	 		  {
	    	   hasCrow=false;
		    	   hasreset2=true;
	 			  FirstGame.stage.addActor(crow);
		    		   FirstGame.stage.addActor(crow2);
		    		   FirstGame.hasXY=false;
		    		   FirstGame.query=true;
		    		   FirstGame.block_count=0;
		    		   hasBreaken=false;
		    		   this.hasCorw=false;
		    		   this.hasGoToRight=0;
		    		   this.crowHasExist=-1;
		    		  crowTo=-2;
		    		   FirstGame.playAnimation_split=0;
		    		   crow.setX(-129);
		    		   crow2.setX(-129);
		    		   crowX=0;
	 		  }
	       
	       
	       
	       
	       
	       if(score>=35&&score<45)
	       {
	    	  // hasCrow=true;
	    	   if(setscore4==false)
	    	   {
	    		    hasCrow=true;
	    		    setscore4=true;
	    	   }
	    	  if(crow.getX()>=MainActivity.screenwidth)
	    	  {
	    		   hasCorw=false;
	    		   hasCrow=false;
	    	  }  
	    	   if((score==35)&&hasCorw==false)
	    	   {
	    		   crow.setX(-129);
	    		   crow2.setX(-129);
	    		   crowX=0;
	    		   hasCorw=true;
	    		    Random ran=new Random();
	    	       int c=ran.nextInt(3);
	    	       corwG=c;
	    	       crowAction(c);
	    	  
	    	   }
	    	   else
	    	   {
	    		 //  System.out.println("corg="+corwG);
	    		   crowAction(corwG);
	    	   }
	    	  
	       }
	       if(score==45&&hasreset3==false)																																																																																																																								
	 		  {
	    	   hasCrow=false;
		    	   hasreset3=true;
	 			  FirstGame.stage.addActor(crow);
		    		   FirstGame.stage.addActor(crow2);
		    		   FirstGame.hasXY=false;
		    		   FirstGame.query=true;
		    		   FirstGame.block_count=0;
		    		   hasBreaken=false;
		    		   this.hasCorw=false;
		    		   this.hasGoToRight=0;
		    		   this.crowHasExist=-1;
		    		  crowTo=-2;
		    		   FirstGame.playAnimation_split=0;
		    		   crow.setX(-129);
		    		   crow2.setX(-129);
		    		   crowX=0;
	 		  }
	       
	       
	       
	       if(score>=45&&score<55)
	       {
	    	  // hasCrow=true;
	    	   if(setscore5==false)
	    	   {
	    		    hasCrow=true;
	    		    setscore5=true;
	    	   }
	    	  if(crow.getX()>=MainActivity.screenwidth)
	    	  {
	    		   hasCorw=false;
	    		   hasCrow=false;
	    	  }  
	    	   if((score==45)&&hasCorw==false)
	    	   {
	    		   crow.setX(-129);
	    		   crow2.setX(-129);
	    		   crowX=0;
	    		   hasCorw=true;
	    		    Random ran=new Random();
	    	       int c=ran.nextInt(3);
	    	       corwG=c;
	    	       crowAction(c);
	    	  
	    	   }
	    	   else
	    	   {
	    		 //  System.out.println("corg="+corwG);
	    		   crowAction(corwG);
	    	   }
	    	  
	       }
	       if(score==55	&&hasreset4==false)																																																																																																																								
	 		  {
	    	   hasCrow=false;
		    	   hasreset4=true;
	 			  FirstGame.stage.addActor(crow);
		    		   FirstGame.stage.addActor(crow2);
		    		   FirstGame.hasXY=false;
		    		   FirstGame.query=true;
		    		   FirstGame.block_count=0;
		    		   hasBreaken=false;
		    		   this.hasCorw=false;
		    		   this.hasGoToRight=0;
		    		   this.crowHasExist=-1;
		    		  crowTo=-2;
		    		   FirstGame.playAnimation_split=0;
		    		   crow.setX(-129);
		    		   crow2.setX(-129);
		    		   crowX=0;
	 		  }
	       
	       
	       
	       if(score>=55&&score<60)
	       {
	    	   //hasCrow=true;
	    	   if(setscore6==false)
	    	   {
	    		    hasCrow=true;
	    		    setscore6=true;
	    	   }
	    	  if(crow.getX()>=MainActivity.screenwidth)
	    	  {
	    		   hasCorw=false;
	    		   hasCrow=false;
	    	  }  
	    	   if((score==55)&&hasCorw==false)
	    	   {
	    		   crow.setX(-129);
	    		   crow2.setX(-129);
	    		   crowX=0;
	    		   hasCorw=true;
	    		    Random ran=new Random();
	    	       int c=ran.nextInt(3);
	    	       corwG=c;
	    	       crowAction(c);
	    	  
	    	   }
	    	   else
	    	   {
	    		 //  System.out.println("corg="+corwG);
	    		   crowAction(corwG);
	    	   }
	    	  
	       }
	       if(score==70	&&hasreset5==false)																																																																																																																								
	 		  {
	    	   hasCrow=false;
		    	   hasreset5=true;
	 			  FirstGame.stage.addActor(crow);
		    		   FirstGame.stage.addActor(crow2);
		    		   FirstGame.hasXY=false;
		    		   FirstGame.query=true;
		    		   FirstGame.block_count=0;
		    		   hasBreaken=false;
		    		   this.hasCorw=false;
		    		   this.hasGoToRight=0;
		    		   this.crowHasExist=-1;
		    		  crowTo=-2;
		    		   FirstGame.playAnimation_split=0;
		    		   crow.setX(-129);
		    		   crow2.setX(-129);
		    		   crowX=0;
	 		  }
	       
	       
	       
	       if(score>=70&&score<80)
	       {
	    	  // hasCrow=true;
	    	   if(setscore7==false)
	    	   {
	    		    hasCrow=true;
	    		    setscore7=true;
	    	   }
	    	  if(crow.getX()>=MainActivity.screenwidth)
	    	  {
	    		   hasCorw=false;
	    		   hasCrow=false;
	    	  }  
	    	   if((score==70)&&hasCorw==false)
	    	   {
	    		   crow.setX(-129);
	    		   crow2.setX(-129);
	    		   crowX=0;
	    		   hasCorw=true;
	    		    Random ran=new Random();
	    	       int c=ran.nextInt(3);
	    	       corwG=c;
	    	       crowAction(c);
	    	  
	    	   }
	    	   else
	    	   {
	    		 //  System.out.println("corg="+corwG);
	    		   crowAction(corwG);
	    	   }
	    	  
	       }
	       if(score==80	&&hasreset6==false)																																																																																																																								
	 		  {
	    	   hasCrow=false;
		    	   hasreset6=true;
	 			  FirstGame.stage.addActor(crow);
		    		   FirstGame.stage.addActor(crow2);
		    		   FirstGame.hasXY=false;
		    		   FirstGame.query=true;
		    		   FirstGame.block_count=0;
		    		   hasBreaken=false;
		    		   this.hasCorw=false;
		    		   this.hasGoToRight=0;
		    		   this.crowHasExist=-1;
		    		  crowTo=-2;
		    		   FirstGame.playAnimation_split=0;
		    		   crow.setX(-129);
		    		   crow2.setX(-129);
		    		   crowX=0;
	 		  }
	       current_x=d.getX();
	       current_y=d.getY();
	       if(fail==1)
	       {
	    	   float x=d.getX();
	    	   float y=d.getY();
	    	     // System.out.println("����");
	    	   MainActivity.vibrator.vibrate(50);   
	       }
	     r++;
	     if(r==40)
	     {
	    	 r=0;
	    	 playcount=0;
	     }
		
		 }
			 
		
	}

}
