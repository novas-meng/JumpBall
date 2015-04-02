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

public class crowJump extends Actor
{
	float x,y;
	int corwG;
	int movecount=0;
	static int help1=0,help2=0,help3=0,help4=0;
	TextureRegion[] split;
	static Animation splitblock;
	static boolean hasCorw=false;
	static float AnimationX;
	static float AnimationY;
	static int first=0;
	static boolean hasBreaken=false;
	//判断是否撞击到墙上
	static boolean playAnimation=false;
	ParticleEffect effect;
	int crowX=0;
	static int score=0;
	SpriteBatch sb;
	static Image crow;
	//表示crow要撞向哪个砖块，
	static int crowTo=-1;
	private static int flag=0;
	private static int count=1;
	static float current_x,current_y,goto_x,goto_y;
	static int m,n;
	int j;
	static int stay=0;//表示球在边界停留一段时间
	int[] flagcount=new int[12];
	static int gotoX,gotoY;
	//dire代表下一次碰撞点的方向，0表示左，1表示下，2表示右，3表示上num表示下表，
	static int num,dire;
	static int ifdraw=0;
	//当这个类中的ifdraw=1表示碰到了边界，和Ball中count=0,表示没有按下任何按键，此时变红
	public static float ballX,ballY;
	public static int left1,up1,down1,right1,left2,up2,down2,right2,left3,up3,down3,right3;
	public static int pleft1,pup1,pdown1,pright1,pleft2,pup2,pdown2,pright2,pleft3,pup3,pdown3,pright3;
	Texture jumpball;
	private int Lboder=(int)crowball.LBorder;
	private int Rboder=(int)crowball.RBorder;
	private int Uboder=(int)crowball.UBorder;
	private int Dboder=(int)crowball.DBorder;
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
	   //代表播放音乐，只有为0的时候才播放
	   static int playcount=0;
	   static int r=0;
	   //定义播放的音节次序
	   static int lyric=0;
	   static int lyriccount=0;
	   static int fail=0;
	   static int[] l;
	   SpriteBatch batch;
	   float stateTime=0f;
	   static Image crow2;
	   int hasUp=0;
	   static boolean ifCrowExist=true;
	   static int CrowHitBall=0;
	   static int hasGoToRight=0;
 public crowJump()
 {
	 Texture.setEnforcePotImages(false);
	
	 
	 music.initStar();
	 music.initMusic();
	
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
	 Texture txtup=new Texture(Gdx.files.internal("ball_confus.png"));
		Texture txtdown=new Texture(Gdx.files.internal("ball_confus.png"));
		TextureRegionDrawable trd=new TextureRegionDrawable(new TextureRegion(txtdown));
		TextureRegionDrawable tru=new TextureRegionDrawable(new TextureRegion(txtup));
		//d=new ImageButton(tru,trd);
		d=new Image(new Texture(Gdx.files.internal("ball_confus.png")));
		d.setSize(64*crowMainActivity.w, 64*crowMainActivity.h);
		initMusic();
	
	 for(int i=0;i<3;i++)
	 {
		 int width=(int) (Lboder+(2*i+1)*80*crowMainActivity.w-32*crowMainActivity.w);
		 int height=Uboder;
		 PointF point=new PointF(width,height);
		 up[i]=point;
		 
		 width=Lboder;
		 height=(int) (Dboder+(2*i+1)*80*crowMainActivity.h-32*crowMainActivity.h);
		 point=new PointF(width,height);
		 left[i]=point;
		 
		 width=(int) (Lboder+(2*i+1)*80*crowMainActivity.w-32*crowMainActivity.w);
		 height=Dboder;
		 point=new PointF(width,height);
		 down[i]=point;
		 
		 width=Rboder;
		 height=(int) (Dboder+(2*i+1)*80*crowMainActivity.h-32*crowMainActivity.h);
		 point=new PointF(width,height);
		 right[i]=point;
	 }
	 this.x=left[1].x;
	 this.y=left[1].y;
	 this.state=STATE.Left;
	 d.setPosition(550*crowMainActivity.w, 320*crowMainActivity.h);
	
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
			if(d.getX()<=Lboder+160*crowMainActivity.w)
			{
				up1=1;
				
				if(crowFirstGame.ifexist[0]==1&&playcount==0)
				{
					score++;
					int n=ran.nextInt(25);
					//n=music.star[lyric%lyriccount];
					n=l[lyric%lyriccount];
					lyric++;
					sound[n].play();
					playcount++;
				}
				if(crowFirstGame.ifexist[0]==0)
					fail=1;
			}else if(d.getX()<=Lboder+320*crowMainActivity.w)
			{
				up2=1;
				 
				if(crowFirstGame.ifexist[1]==1&&playcount==0)
				{
					score++;
					int n=ran.nextInt(25);
					//n=music.star[lyric%lyriccount];
					n=l[lyric%lyriccount];
					lyric++;
					sound[n].play();
					playcount++;
				}
				if(crowFirstGame.ifexist[1]==0)
					fail=1;
			}
			else
			{
				up3=1;
				 
				if(crowFirstGame.ifexist[2]==1&&playcount==0)
				{
					score++;
					int n=ran.nextInt(25);
					//n=music.star[lyric%lyriccount];
					n=l[lyric%lyriccount];
					lyric++;
					sound[n].play();
					playcount++;
				}
				if(crowFirstGame.ifexist[2]==0)
					fail=1;
			}
			ifcontinue = 0;
		} 
		}
	    if(d.getX()<=Lboder)
	    {
	    	if(ifcontinue == 1){
	    	ifdraw=1;
	    	if(d.getY()<=Dboder+160*crowMainActivity.h)
			{
				left1=1;
				if(crowFirstGame.ifexist[9]==1&&playcount==0)
				{
					score++;
					int n=ran.nextInt(25);
					//n=music.star[lyric%lyriccount];
					n=l[lyric%lyriccount];
					lyric++;
					sound[n].play();
					playcount++;
				}
				if(crowFirstGame.ifexist[9]==0)
					fail=1;
				
			}else if(d.getY()<=Dboder+320*crowMainActivity.h)
			{
				left2=1;
				if(crowFirstGame.ifexist[10]==1&&playcount==0)
				{
					score++;
					int n=ran.nextInt(25);
					//n=music.star[lyric%lyriccount];
					n=l[lyric%lyriccount];
					lyric++;
					sound[n].play();
					playcount++;
				}
				if(crowFirstGame.ifexist[10]==0)
					fail=1;
			}
			else
			{
				left3=1;
				if(crowFirstGame.ifexist[11]==1&&playcount==0)
				{
					score++;
					int n=ran.nextInt(25);
					//n=music.star[lyric%lyriccount];
					n=l[lyric%lyriccount];
					lyric++;
					sound[n].play();
					playcount++;
				}
				if(crowFirstGame.ifexist[11]==0)
					fail=1;
			}
	    	ifcontinue = 0;
	    	}
	    }
	    if(d.getX()>=Rboder)
	    {
	    	
	    	
	    	if(ifcontinue == 1){
	    		ifdraw=1;
	    	if(d.getY()<=Dboder+160*crowMainActivity.h)
			{
				right1=1;
				if(crowFirstGame.ifexist[5]==1&&playcount==0)
				{
					score++;
					int n=ran.nextInt(25);
					//n=music.star[lyric%lyriccount];
					n=l[lyric%lyriccount];
					lyric++;
					sound[n].play();
					playcount++;
				}
				if(crowFirstGame.ifexist[5]==0)
					fail=1;
			}else if(d.getY()<=Dboder+320*crowMainActivity.h)
			{
				right2=1;
				if(crowFirstGame.ifexist[4]==1&&playcount==0)
				{
					score++;
					int n=ran.nextInt(25);
				//	n=music.star[lyric%lyriccount];
					n=l[lyric%lyriccount];
					lyric++;
					sound[n].play();
					playcount++;
				}
				if(crowFirstGame.ifexist[4]==0)
					fail=1;
			}
			else
			{
				right3=1;
				if(crowFirstGame.ifexist[3]==1&&playcount==0)
				{
					score++;
					int n=ran.nextInt(25);
					//n=music.star[lyric%lyriccount];
					n=l[lyric%lyriccount];
					lyric++;
					sound[n].play();
					playcount++;
				}
				if(crowFirstGame.ifexist[3]==0)
					fail=1;
			}
	    	ifcontinue = 0;
	    	}
	    }
	    
	    if(d.getY()<=Dboder)
	    {
	    	
	    	if(ifcontinue == 1){
	    	ifdraw=1;
	    	if(d.getX()<=Lboder+160*crowMainActivity.w)
			{
				down1=1;
				if(crowFirstGame.ifexist[8]==1&&playcount==0)
				{
					score++;
					int n=ran.nextInt(25);
					//n=music.star[lyric%lyriccount];
					n=l[lyric%lyriccount];
					lyric++;
					sound[n].play();
					playcount++;
				}
				if(crowFirstGame.ifexist[8]==0)
					fail=1;
			}else if(d.getX()<=Lboder+320*crowMainActivity.w)
			{
				down2=1;
				if(crowFirstGame.ifexist[7]==1&&playcount==0)
				{
					score++;
					int n=ran.nextInt(25);
					//n=music.star[lyric%lyriccount];
					n=l[lyric%lyriccount];
					lyric++;
					sound[n].play();
					playcount++;
				}
				if(crowFirstGame.ifexist[7]==0)
					fail=1;
			}
			else
			{
				down3=1;
				if(crowFirstGame.ifexist[6]==1&&playcount==0)
				{
					score++;
					int n=ran.nextInt(25);
					//n=music.star[lyric%lyriccount];
					n=l[lyric%lyriccount];
					lyric++;
					sound[n].play();
					playcount++;
				}
				if(crowFirstGame.ifexist[6]==0)
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
		   //n表示方位，m表示第几个
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
				   MoveToAction moveto = Actions.moveTo(point.x, point.y, length/(300+score*2));
				   //SequenceAction sequenceAction = Actions.sequence(moveto1,moveto); 
				   d.addAction(moveto);
			     ifcontinue=1;
			     movecount++;
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
					   MoveToAction moveto = Actions.moveTo(point.x, point.y, length/(300+score*2));
				     d.addAction(moveto);
				     ifcontinue=1;
				     movecount++;
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
						   MoveToAction moveto = Actions.moveTo(point.x, point.y, length/(300+score*2));
					     d.addAction(moveto);
					     ifcontinue=1;
					     movecount++;
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
							   MoveToAction moveto = Actions.moveTo(point.x, point.y, length/(300+score*2));
						     d.addAction(moveto);
						     ifcontinue=1;
						     movecount++;
						   }
						   else
						   {
							   AnimationX=d.getX();
							   AnimationY=d.getY();
							   System.out.println("大于0");
							   playAnimation=true;
							 stay++;
						   }
					   }
	   }
	  
   }
   
   @Override
	public void draw(SpriteBatch batch, float parentAlpha) {
		// TODO Auto-generated method stub
		// float duration = MathUtils.random(3, 10);
		
	          
		 if(crowFirstGame.playMusic==-1)
		 {
			
			// justflag();
			
	       drawLine();
	      
	       
	      
	       current_x=d.getX();
	       current_y=d.getY();
	       if(fail==1)
	       {
	    	   float x=d.getX();
	    	   float y=d.getY();
	    	      System.out.println("整栋");
	    	 crowMainActivity.vibrator.vibrate(50);  
	    	  
	    	 
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