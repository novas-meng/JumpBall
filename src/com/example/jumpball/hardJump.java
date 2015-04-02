package com.example.jumpball;

import java.util.Random;

import android.content.Intent;
import android.graphics.PointF;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.example.jumpball.Jump.STATE;

public class hardJump extends Actor
{
	float x,y;
	static int score=0;
	static int hit=-1;
	static int temp=0;
	private static int flag=0;
	private static int count=1;
	 static int m,n;
	static int stay=0;//表示球在边界停留一段时间
	int[] flagcount=new int[12];
	//dire代表下一次碰撞点的方向，0表示左，1表示下，2表示右，3表示上num表示下表，
	static int num,dire;
	static int ifdraw=0;
	//当这个类中的ifdraw=1表示碰到了边界，和Ball中count=0,表示没有按下任何按键，此时变红
	public static float ballX,ballY;
	public static int left1,up1,down1,right1,left2,up2,down2,right2,left3,up3,down3,right3;
	public static int pleft1,pup1,pdown1,pright1,pleft2,pup2,pdown2,pright2,pleft3,pup3,pdown3,pright3;
	Texture jumpball;
	private int Lboder=(int) hardball.LBorder;
	private int Rboder=(int) hardball.RBorder;
	private int Uboder=(int) hardball.UBorder;
	private int Dboder=(int) hardball.DBorder;
	static int pre=0;
	static int ifcontinue=0;
	PointF point=null;
	static Sound[] sound=new Sound[26];
	Random ran=new Random();
	static int start=0;
	//private static  PointF up[],down[],left[],right[];
	static PointF[] up=new PointF[3];
	static PointF[] down=new PointF[3];
	static PointF[] left=new PointF[3];
	static PointF[] right=new PointF[3];
	static  enum STATE {
	       Left,Right,Up,Down
	   };
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
 public hardJump()
 {
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
	 jumpball=new Texture(Gdx.files.internal("ball_confus.png"));
	 Texture txtup=new Texture(Gdx.files.internal("ball_confus.png"));
		Texture txtdown=new Texture(Gdx.files.internal("ball_confus.png"));
		TextureRegionDrawable trd=new TextureRegionDrawable(new TextureRegion(txtdown));
		TextureRegionDrawable tru=new TextureRegionDrawable(new TextureRegion(txtup));
		//d=new ImageButton(tru,trd);
		d=new Image(new Texture(Gdx.files.internal("ball_confus.png")));
		d.setSize(64*MainActivity.w, 64*MainActivity.h);
		initMusic();
	// this.x=Lboder;
	// this.y=(Uboder+Dboder)/2;
	 for(int i=0;i<3;i++)
	 {
		 /*
		 int width=Lboder+(2*i+1)*90*MainActivity.w-32;
		 int height=Uboder;
		 PointF point=new PointF(width,height);
		 up[i]=point;
		 
		 width=Lboder;
		 height=Dboder+(2*i+1)*90-32;
		 point=new PointF(width,height);
		 left[i]=point;
		 
		 width=Lboder+(2*i+1)*90-32;
		 height=Dboder;
		 point=new PointF(width,height);
		 down[i]=point;
		 
		 width=Rboder;
		 height=Dboder+(2*i+1)*90-32;
		 point=new PointF(width,height);
		 right[i]=point;
		 */
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
	 d.setPosition(550*MainActivity.w, 320*MainActivity.h);
	
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
		   if(ifcontinue==1)
		   {
		    ifdraw=1;
			if(d.getX()<=Lboder+160*MainActivity.w)
			{
				up1=1;
				if(hardFirstGame.ifexist[0]==1&&playcount==0)
				{
					score++;
					int n=ran.nextInt(25);
					//n=music.star[lyric%lyriccount];
					n=l[lyric%lyriccount];
					lyric++;
					sound[n].play(0.05f);
					hit=1;
					temp=0;
					playcount++;
				}
				if(hardFirstGame.ifexist[0]==0)
					fail=1;
			}else if(d.getX()<=Lboder+320*MainActivity.w)
			{
				up2=1;
				if(hardFirstGame.ifexist[1]==1&&playcount==0)
				{
					score++;
					int n=ran.nextInt(25);
					//n=music.star[lyric%lyriccount];
					n=l[lyric%lyriccount];
					lyric++;
					sound[n].play(0.05f);
					hit=1;
					temp=0;
					playcount++;
				}
				if(hardFirstGame.ifexist[1]==0)
					fail=1;
			}
			else
			{
				up3=1;
				if(hardFirstGame.ifexist[2]==1&&playcount==0)
				{
					score++;
					int n=ran.nextInt(25);
					//n=music.star[lyric%lyriccount];
					n=l[lyric%lyriccount];
					lyric++;
					sound[n].play(0.05f);
					hit=1;
					temp=0;
					playcount++;
				}
				if(hardFirstGame.ifexist[2]==0)
					fail=1;
			}
			ifcontinue=0;
			}
		} 
	   
	    if(d.getX()<=Lboder)
	    {
	    	if(ifcontinue==1){
	    	ifdraw=1;
	    	if(d.getY()<=Dboder+160*MainActivity.h)
			{
				left1=1;
				if(hardFirstGame.ifexist[9]==1&&playcount==0)
				{
					score++;
					int n=ran.nextInt(25);
					//n=music.star[lyric%lyriccount];
					n=l[lyric%lyriccount];
					hit=1;
					lyric++;
					temp=0;
					sound[n].play(0.05f);
					playcount++;
				}
				if(hardFirstGame.ifexist[9]==0)
					fail=1;
				
			}else if(d.getY()<=Dboder+320*MainActivity.h)
			{
				left2=1;
				if(hardFirstGame.ifexist[10]==1&&playcount==0)
				{
					score++;
					int n=ran.nextInt(25);
					//n=music.star[lyric%lyriccount];
					n=l[lyric%lyriccount];
					lyric++;
					sound[n].play(0.05f);
					hit=1;
					temp=0;
					playcount++;
				}
				if(hardFirstGame.ifexist[10]==0)
					fail=1;
			}
			else
			{
				left3=1;
				if(hardFirstGame.ifexist[11]==1&&playcount==0)
				{
					score++;
					int n=ran.nextInt(25);
					//n=music.star[lyric%lyriccount];
					n=l[lyric%lyriccount];
					lyric++;
					sound[n].play(0.05f);
					hit=1;
					temp=0;
					playcount++;
				}
				if(hardFirstGame.ifexist[11]==0)
					fail=1;
			}
	    	ifcontinue=0;
			}
	    }
	    if(d.getX()>=Rboder)
	    {
	    	if(ifcontinue==1){
	    	ifdraw=1;
	    	
	    	
	    	if(d.getY()<=Dboder+160*MainActivity.h)
			{
				right1=1;
				if(hardFirstGame.ifexist[5]==1&&playcount==0)
				{
					score++;
					int n=ran.nextInt(25);
					//n=music.star[lyric%lyriccount];
					n=l[lyric%lyriccount];
					lyric++;
					sound[n].play(0.05f);
					hit=1;
					temp=0;
					playcount++;
				}
				if(hardFirstGame.ifexist[5]==0)
					fail=1;
			}else if(d.getY()<=Dboder+320*MainActivity.h)
			{
				right2=1;
				if(hardFirstGame.ifexist[4]==1&&playcount==0)
				{
					score++;
					int n=ran.nextInt(25);
				//	n=music.star[lyric%lyriccount];
					n=l[lyric%lyriccount];
					lyric++;
					sound[n].play(0.05f);
					hit=1;
					temp=0;
					playcount++;
				}
				if(hardFirstGame.ifexist[4]==0)
					fail=1;
			}
			else
			{
				right3=1;
				if(hardFirstGame.ifexist[3]==1&&playcount==0)
				{
					score++;
					int n=ran.nextInt(25);
					//n=music.star[lyric%lyriccount];
					n=l[lyric%lyriccount];
					lyric++;
					sound[n].play(0.05f);
					hit=1;
					temp=0;
					playcount++;
				}
				if(hardFirstGame.ifexist[3]==0)
					fail=1;
			}
	    	ifcontinue=0;
			}
	    }
	    
	    if(d.getY()<=Dboder)
	    {
	    	if(ifcontinue==1){
	    	ifdraw=1;
	    	
	    	
	    	if(d.getX()<=Lboder+160*MainActivity.w)
			{
				down1=1;
				if(hardFirstGame.ifexist[8]==1&&playcount==0)
				{
					score++;
					int n=ran.nextInt(25);
					//n=music.star[lyric%lyriccount];
					n=l[lyric%lyriccount];
					lyric++;
					sound[n].play(0.05f);
					hit=1;
					temp=0;
					playcount++;
				}
				if(hardFirstGame.ifexist[8]==0)
					fail=1;
			}else if(d.getX()<=Lboder+320*MainActivity.w)
			{
				down2=1;
				if(hardFirstGame.ifexist[7]==1&&playcount==0)
				{
					score++;
					int n=ran.nextInt(25);
					//n=music.star[lyric%lyriccount];
					n=l[lyric%lyriccount];
					lyric++;
					sound[n].play(0.05f);
					hit=1;
					temp=0;
					playcount++;
				}
				if(hardFirstGame.ifexist[7]==0)
					fail=1;
			}
			else
			{
				down3=1;
				if(hardFirstGame.ifexist[6]==1&&playcount==0)
				{
					score++;
					int n=ran.nextInt(25);
					//n=music.star[lyric%lyriccount];
					n=l[lyric%lyriccount];
					lyric++;
					sound[n].play(0.05f);
					hit=1;
					temp=0;
					playcount++;
				}
				if(hardFirstGame.ifexist[6]==0)
					fail=1;
			}
	    	ifcontinue=0;
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
				
				   stay=0;
				   start=0;
				  
				   float length=(point.x-d.getX())*(point.x-d.getX())+(point.y-d.getY())*(point.y-d.getY());
				   length=(float) Math.pow(length, 0.5f);
				   MoveToAction moveto = Actions.moveTo(point.x, point.y, length/(300+score*1));
				   //SequenceAction sequenceAction = Actions.sequence(moveto1,moveto); 
				   d.addAction(moveto);
			     ifcontinue=1;
			   }
			   else
			   {
				   
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
					  
					   stay=0;
					   start=0;
					 
					   float length=(point.x-d.getX())*(point.x-d.getX())+(point.y-d.getY())*(point.y-d.getY());
					   length=(float) Math.pow(length, 0.5f);
					   MoveToAction moveto = Actions.moveTo(point.x, point.y, length/(300+score*1));
				     d.addAction(moveto);
				     ifcontinue=1;
				   }
				   else
				   {
					  
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
						 
						   stay=0;
						   start=0;
						 
						   float length=(point.x-d.getX())*(point.x-d.getX())+(point.y-d.getY())*(point.y-d.getY());
						   length=(float) Math.pow(length, 0.5f);
						   MoveToAction moveto = Actions.moveTo(point.x, point.y, length/(300+score*1));
					     d.addAction(moveto);
					     ifcontinue=1;
					   }
					   else
					   {
						  
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
							  
							   stay=0;
							   start=0;
							
							   float length=(point.x-d.getX())*(point.x-d.getX())+(point.y-d.getY())*(point.y-d.getY());
							   length=(float) Math.pow(length, 0.5f);
							   MoveToAction moveto = Actions.moveTo(point.x, point.y, length/(300+score*1));
						     d.addAction(moveto);
						     ifcontinue=1;
						   }
						   else
						   {
							  
							 stay++;
						   }
					   }
	   }
	  
   }
	@Override
	public void draw(SpriteBatch batch, float parentAlpha) {
		// TODO Auto-generated method stu
		 if(hardFirstGame.playMusic==-1)
		 {
			 justflag();
	       drawLine();
	     r++;
	     temp++;
	     if(temp==100)
	     {
	    	 hit=-1;
	     }
	  //   if(r==10)
	    	// hit=-1;
	     if(r==40)
	     {
	    	 r=0;
	    	 playcount=0;
	     }
		 }

	}

}
