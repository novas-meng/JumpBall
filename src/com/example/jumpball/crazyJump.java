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
import com.example.jumpball.hardJump.STATE;

public class crazyJump extends Actor
{
	float x,y;
	boolean hastwoball=false;
	static int score=0;
	private static int slow = 150;
	static int m,n,m2,n2;
	static int stay=0;//表示球在边界停留一段时间
	static int stay2=0;
	int[] flagcount=new int[12];
	//dire代表下一次碰撞点的方向，0表示左，1表示下，2表示右，3表示上num表示下表，
	static int num,dire;
	static int ifdraw=0;
	//当这个类中的ifdraw=1表示碰到了边界，和Ball中count=0,表示没有按下任何按键，此时变红
	public static float ballX,ballY;
	public static int left1,up1,down1,right1,left2,up2,down2,right2,left3,up3,down3,right3;
	public static int pleft1,pup1,pdown1,pright1,pleft2,pup2,pdown2,pright2,pleft3,pup3,pdown3,pright3;
	Texture jumpball;
	private int Lboder=(int)crazyball.LBorder;
	private int Rboder=(int)crazyball.RBorder;
	private int Uboder=(int)crazyball.UBorder;
	private int Dboder=(int)crazyball.DBorder;
	static int pre;
	static int pre2;
	static int ifcontinue=0;
	static int ifcontinue2 = 0;
	PointF point=null;
	PointF point2 = null;
	static Sound[] sound=new Sound[26];
	Random ran=new Random();
	static int start=0;
	static int start2 = 0;
	//private static  PointF up[],down[],left[],right[];
	static PointF[] up=new PointF[3];
	static PointF[] down=new PointF[3];
	static PointF[] left=new PointF[3];
	static PointF[] right=new PointF[3];
	static  enum STATE {
	       Left,Right,Up,Down
	   };
	
	
	 static  STATE state;
	 static  STATE state2;
	 static  STATE state3;
	 static  STATE state4;
	 static Image d;
	 static Image d2;
	 static Image d3;
	 static Image d4;
	 
	   //代表播放音乐，只有为0的时候才播放
	   static int playcount=0;
	   static int playcount2=0;
	   static int r=0;
	   //定义播放的音节次序
	   static int lyric=0;
	   static int lyriccount=0;
	   static int fail=0;
	   static int[] l;
 public crazyJump()
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
	 Texture.setEnforcePotImages(false);
	 Texture txtup=new Texture(Gdx.files.internal("ball_confus.png"));
		Texture txtdown=new Texture(Gdx.files.internal("ball_confus.png"));
		TextureRegionDrawable trd=new TextureRegionDrawable(new TextureRegion(txtdown));
		TextureRegionDrawable tru=new TextureRegionDrawable(new TextureRegion(txtup));
		//d=new ImageButton(tru,trd);
		d=new Image(new Texture(Gdx.files.internal("ball_confus.png")));
		d.setSize(64*MainActivity.w, 64*MainActivity.h);

		d4=new Image(new Texture(Gdx.files.internal("ball_confus.png")));
		d4.setSize(64*MainActivity.w, 64*MainActivity.h);
		d2=new Image(new Texture(Gdx.files.internal("ball_confus.png")));
		d2.setSize(64*MainActivity.w, 64*MainActivity.h);
		d3=new Image(new Texture(Gdx.files.internal("ball_confus.png")));
		d3.setSize(64*MainActivity.w, 64*MainActivity.h);
	
		initMusic();
	
	 for(int i=0;i<3;i++)
	 {
		 int width=(int) (Lboder+(2*i+1)*80*MainActivity.w-32*MainActivity.w);
		 int height=Uboder;
		 PointF point=new PointF(width,height);
		 up[i]=point;
		 
		 width=Lboder;
		 height=(int) (Dboder+(2*i+1)*80*MainActivity.h-32*MainActivity.w);
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
		   if(ifcontinue==1){
		   ifdraw=1;
			if(d.getX()<=Lboder+160*MainActivity.w)
			{
				up1=1;
				if(crazyFirstGame.ifexist[0]==1&&playcount==0)
				{
					score++;
					int n=ran.nextInt(25);
					//n=music.star[lyric%lyriccount];
					n=l[lyric%lyriccount];
					lyric++;
					sound[n].play();
					playcount++;
				}
				if(crazyFirstGame.ifexist[0]==0)
					fail=1;
			}else if(d.getX()<=Lboder+320*MainActivity.w)
			{
				up2=1;
				if(crazyFirstGame.ifexist[1]==1&&playcount==0)
				{
					score++;
					int n=ran.nextInt(25);
					//n=music.star[lyric%lyriccount];
					n=l[lyric%lyriccount];
					lyric++;
					sound[n].play();
					playcount++;
				}
				if(crazyFirstGame.ifexist[1]==0)
					fail=1;
			}
			else
			{
				up3=1;
				if(crazyFirstGame.ifexist[2]==1&&playcount==0)
				{
					score++;
					int n=ran.nextInt(25);
					//n=music.star[lyric%lyriccount];
					n=l[lyric%lyriccount];
					lyric++;
					sound[n].play();
					playcount++;
				}
				if(crazyFirstGame.ifexist[2]==0)
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
				if(crazyFirstGame.ifexist[9]==1&&playcount==0)
				{
					score++;
					int n=ran.nextInt(25);
					//n=music.star[lyric%lyriccount];
					n=l[lyric%lyriccount];
					lyric++;
					sound[n].play();
					playcount++;
				}
				if(crazyFirstGame.ifexist[9]==0)
					fail=1;
				
			}else if(d.getY()<=Dboder+320*MainActivity.h)
			{
				left2=1;
				if(crazyFirstGame.ifexist[10]==1&&playcount==0)
				{
					score++;
					int n=ran.nextInt(25);
					//n=music.star[lyric%lyriccount];
					n=l[lyric%lyriccount];
					lyric++;
					sound[n].play();
					playcount++;
				}
				if(crazyFirstGame.ifexist[10]==0)
					fail=1;
			}
			else
			{
				left3=1;
				if(crazyFirstGame.ifexist[11]==1&&playcount==0)
				{
					score++;
					int n=ran.nextInt(25);
					//n=music.star[lyric%lyriccount];
					n=l[lyric%lyriccount];
					lyric++;
					sound[n].play();
					playcount++;
				}
				if(crazyFirstGame.ifexist[11]==0)
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
				if(crazyFirstGame.ifexist[5]==1&&playcount==0)
				{
					score++;
					int n=ran.nextInt(25);
					//n=music.star[lyric%lyriccount];
					n=l[lyric%lyriccount];
					lyric++;
					sound[n].play();
					playcount++;
				}
				if(crazyFirstGame.ifexist[5]==0)
					fail=1;
			}else if(d.getY()<=Dboder+320*MainActivity.h)
			{
				right2=1;
				if(crazyFirstGame.ifexist[4]==1&&playcount==0)
				{
					score++;
					int n=ran.nextInt(25);
				//	n=music.star[lyric%lyriccount];
					n=l[lyric%lyriccount];
					lyric++;
					sound[n].play();
					playcount++;
				}
				if(crazyFirstGame.ifexist[4]==0)
					fail=1;
			}
			else
			{
				right3=1;
				if(crazyFirstGame.ifexist[3]==1&&playcount==0)
				{
					score++;
					int n=ran.nextInt(25);
					//n=music.star[lyric%lyriccount];
					n=l[lyric%lyriccount];
					lyric++;
					sound[n].play();
					playcount++;
				}
				if(crazyFirstGame.ifexist[3]==0)
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
				if(crazyFirstGame.ifexist[8]==1&&playcount==0)
				{
					score++;
					int n=ran.nextInt(25);
					//n=music.star[lyric%lyriccount];
					n=l[lyric%lyriccount];
					lyric++;
					sound[n].play();
					playcount++;
				}
				if(crazyFirstGame.ifexist[8]==0)
					fail=1;
			}else if(d.getX()<=Lboder+320*MainActivity.w)
			{
				down2=1;
				if(crazyFirstGame.ifexist[7]==1&&playcount==0)
				{
					score++;
					int n=ran.nextInt(25);
					//n=music.star[lyric%lyriccount];
					n=l[lyric%lyriccount];
					lyric++;
					sound[n].play();
					playcount++;
				}
				if(crazyFirstGame.ifexist[7]==0)
					fail=1;
			}
			else
			{
				down3=1;
				if(crazyFirstGame.ifexist[6]==1&&playcount==0)
				{
					score++;
					int n=ran.nextInt(25);
					//n=music.star[lyric%lyriccount];
					n=l[lyric%lyriccount];
					lyric++;
					sound[n].play();
					playcount++;
				}
				if(crazyFirstGame.ifexist[6]==0)
					fail=1;
			}
	    	ifcontinue=0;
	    }
	    }
	    
	    
	    
	    
	    
	    //d2
	    if(score > 4){
	    	if(d2.getY()>=Uboder)
			{
	    		if(ifcontinue2==1){
			   ifdraw=1;
				if(d2.getX()<=Lboder+160*MainActivity.w)
				{
					up1=1;
					if(crazyFirstGame.ifexist[0]==1)
					{
						score++;
						int n=ran.nextInt(25);
						//n=music.star[lyric%lyriccount];
						n=l[lyric%lyriccount];
						lyric++;
						sound[n].play();
						playcount2++;
					}
					if(crazyFirstGame.ifexist[0]==0)
						fail=1;
				}else if(d2.getX()<=Lboder+320*MainActivity.w)
				{
					up2=1;
					if(crazyFirstGame.ifexist[1]==1)
					{
						score++;
						int n=ran.nextInt(25);
						//n=music.star[lyric%lyriccount];
						n=l[lyric%lyriccount];
						lyric++;
						sound[n].play();
						playcount2++;
					}
					if(crazyFirstGame.ifexist[1]==0)
						fail=1;
				}
				else
				{
					up3=1;
					if(crazyFirstGame.ifexist[2]==1)
					{
						score++;
						int n=ran.nextInt(25);
						//n=music.star[lyric%lyriccount];
						n=l[lyric%lyriccount];
						lyric++;
						sound[n].play();
						playcount2++;
					}
					if(crazyFirstGame.ifexist[2]==0)
						fail=1;
				}
				ifcontinue2=0;
			} 
			}
		    if(d2.getX()<=Lboder)
		    {
		    	if(ifcontinue2==1){
		    	ifdraw=1;
		    	if(d2.getY()<=Dboder+160*MainActivity.h)
				{
					left1=1;
					if(crazyFirstGame.ifexist[9]==1)
					{
						score++;
						int n=ran.nextInt(25);
						//n=music.star[lyric%lyriccount];
						n=l[lyric%lyriccount];
						lyric++;
						sound[n].play();
						playcount2++;
					}
					if(crazyFirstGame.ifexist[9]==0)
						fail=1;
					
				}else if(d2.getY()<=Dboder+320*MainActivity.h)
				{
					left2=1;
					if(crazyFirstGame.ifexist[10]==1)
					{
						score++;
						int n=ran.nextInt(25);
						//n=music.star[lyric%lyriccount];
						n=l[lyric%lyriccount];
						lyric++;
						sound[n].play();
						playcount2++;
					}
					if(crazyFirstGame.ifexist[10]==0)
						fail=1;
				}
				else
				{
					left3=1;
					if(crazyFirstGame.ifexist[11]==1)
					{
						score++;
						int n=ran.nextInt(25);
						//n=music.star[lyric%lyriccount];
						n=l[lyric%lyriccount];
						lyric++;
						sound[n].play();
						playcount++;
					}
					if(crazyFirstGame.ifexist[11]==0)
						fail=1;
				}
		    	ifcontinue2=0;
		    }
		    }
		    if(d2.getX()>=Rboder)
		    {
		    	if(ifcontinue2==1){
		    	ifdraw=1;
		    	if(d2.getY()<=Dboder+160*MainActivity.h)
				{
					right1=1;
					if(crazyFirstGame.ifexist[5]==1)
					{
						score++;
						int n=ran.nextInt(25);
						//n=music.star[lyric%lyriccount];
						n=l[lyric%lyriccount];
						lyric++;
						sound[n].play();
						playcount2++;
					}
					if(crazyFirstGame.ifexist[5]==0)
						fail=1;
				}else if(d2.getY()<=Dboder+320*MainActivity.h)
				{
					right2=1;
					if(crazyFirstGame.ifexist[4]==1)
					{
						score++;
						int n=ran.nextInt(25);
					//	n=music.star[lyric%lyriccount];
						n=l[lyric%lyriccount];
						lyric++;
						sound[n].play();
						playcount2++;
					}
					if(crazyFirstGame.ifexist[4]==0)
						fail=1;
				}
				else
				{
					right3=1;
					if(crazyFirstGame.ifexist[3]==1)
					{
						score++;
						int n=ran.nextInt(25);
						//n=music.star[lyric%lyriccount];
						n=l[lyric%lyriccount];
						lyric++;
						sound[n].play();
						playcount2++;
					}
					if(crazyFirstGame.ifexist[3]==0)
						fail=1;
				}
		    	ifcontinue2=0;
		    }
		    }
		    if(d2.getY()<=Dboder)
		    {
		    	if(ifcontinue2==1){
		    	ifdraw=1;
		    	if(d2.getX()<=Lboder+160*MainActivity.w)
				{
					down1=1;
					if(crazyFirstGame.ifexist[8]==1)
					{
						score++;
						int n=ran.nextInt(25);
						//n=music.star[lyric%lyriccount];
						n=l[lyric%lyriccount];
						lyric++;
						sound[n].play();
						playcount2++;
					}
					if(crazyFirstGame.ifexist[8]==0)
						fail=1;
				}else if(d2.getX()<=Lboder+320*MainActivity.w)
				{
					down2=1;
					if(crazyFirstGame.ifexist[7]==1)
					{
						score++;
						int n=ran.nextInt(25);
						//n=music.star[lyric%lyriccount];
						n=l[lyric%lyriccount];
						lyric++;
						sound[n].play();
						playcount2++;
					}
					if(crazyFirstGame.ifexist[7]==0)
						fail=1;
				}
				else
				{
					down3=1;
					if(crazyFirstGame.ifexist[6]==1)
					{
						score++;
						int n=ran.nextInt(25);
						//n=music.star[lyric%lyriccount];
						n=l[lyric%lyriccount];
						lyric++;
						sound[n].play();
						playcount2++;
					}
					if(crazyFirstGame.ifexist[6]==0)
						fail=1;
				}
		    	ifcontinue2=0;
		    }
	    }
	    }
   }
   public void drawLine()
   {
	   //if(stay==0)
	  // {
	   if(score <= 4)
	   {
		   
		      if(d.getX()==Lboder)
		      {
			   state=STATE.Left;
			   ifcontinue=0;
			   justflag();
			 
			   start++;
		       }
		    if(d.getX()==Rboder)
		     {
			   state=STATE.Right;
			   ifcontinue=0;
			   justflag();
			 
			   start++;
		     }
		     if(d.getY()==Uboder)
		     {
			   state=STATE.Up;
			   ifcontinue=0;
			   justflag();
			
			   start++;
		      }
		     if(d.getY()==Dboder)
		     {
			   justflag();
			   state=STATE.Down;
			   ifcontinue=0;
			 
			   start++;
		     }
		   
		     if(score == 0)
		     {
			     for(m = 0;;m++)
			     {
				   if(crazyFirstGame.ifexist[m+3] == 1)
					   break;
			      }
			      point = right[m];
			     if(stay>=10)
			      {
				   stay=0;
				   start=0;
				   float length=(point.x-d.getX())*(point.x-d.getX())+(point.y-d.getY())*(point.y-d.getY());
				   length=(float) Math.pow(length, 0.5f);
				   MoveToAction moveto = Actions.moveTo(point.x, point.y, length/(300+score*2));
				   //SequenceAction sequenceAction = Actions.sequence(moveto1,moveto); 
				   d.addAction(moveto);
			     ifcontinue=1;
			       }
			      else
			      {
				   stay++;
			       }
		       }
		      if(score > 0)
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
						   MoveToAction moveto = Actions.moveTo(point.x, point.y, length/(300+score*2));
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
							   MoveToAction moveto = Actions.moveTo(point.x, point.y, length/(300+score*2));
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
								   MoveToAction moveto = Actions.moveTo(point.x, point.y, length/(300+score*2));
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
									   MoveToAction moveto = Actions.moveTo(point.x, point.y, length/(300+score*2));
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
	   
	   //前面这部分是判断分数小于9的时候，也就是一个小球的时候
	   else if(score >= 5)
	   {
		 //  System.out.println("分数大雨10");
		   if(score==5&&hastwoball==false)
		   {
			    d2.setPosition(d.getX(), d.getY());
		     crazyFirstGame.stage.addActor(d2);
		      state2 = state;
		     hastwoball=true;
		   }
		    
		   if(d.getX()==Lboder)
		   {
			   state=STATE.Left;
			 //  ifcontinue=0;
			   justflag();
			 
			   start++;
		   }
		   if(d.getX()==Rboder)
		   {
			   state=STATE.Right;
			 //  ifcontinue=0;
			   justflag();
			
			   start++;
		   }
		   if(d.getY()==Uboder)
		   {
			   state=STATE.Up;
			  // ifcontinue=0;
			  justflag();
			
			   start++;
		   }
		   if(d.getY()==Dboder)
		   {
			   state=STATE.Down;
			 //  ifcontinue=0;
			   justflag();
			 
			   start++;
		   }
		   if(d2.getX()==Lboder)
		   {
			   state2=STATE.Left;
			   //ifcontinue2=0;
			   justflag();
			  // blockcolor.play=0;
			  // stay=1;
			   start2++;
		   }
		   if(d2.getX()==Rboder)
		   {
			   state2=STATE.Right;
			   //ifcontinue2=0;
			   justflag();
			  // blockcolor.play=0;
			  // stay=1;
			   start2++;
		   }
		   if(d2.getY()==Uboder)
		   {
			   state2=STATE.Up;
			 //  ifcontinue2=0;
			  justflag();
			  // stay=1;
			   start2++;
		   }
		   if(d2.getY()==Dboder)
		   {
			   state2=STATE.Down;
			 //  ifcontinue2=0;
			   justflag();
			  // blockcolor.play=0;
			   //stay=1;
			   start2++;
		   }
		   
		  // state2 = state;
		 //出现两个小球时，第一个球运动状况
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
			 //第二个球的运动状况
			   if(ifcontinue2==0)
			   {
				   if(start2==0||start2==1)
				   {
					   Random ran=new Random();
				     m2=ran.nextInt(3);
				     n2=ran.nextInt(3);
				   }
				   //n表示方位，m表示第几个
				  // start++;
				  num=m2;
				   if(state2==STATE.Left)
				   {
					   if(n2==0)
					   {
						   point2=right[m2];
						   dire=2;
					   }
					   if(n2==1)
					   {
						   if(pre2==2){
							   point2=down[m2];
							   dire = 1;
						   }
						    else
						    {
						    	 point2=up[m2];
						        dire=3;
						    }
						  
					   }
					   if(n2==2)
					   {
						   if(pre2==0){
							   point2=up[m2];
							   dire=3;
						   }
						   else
						   { 
							     point2=down[m2];
						        dire=1;
						   }
						 
					   }
					 if(stay2>=10)
					   {	 
						 pre2=m2;
						
						   stay2=0;
						   start2=0;
						 
						   float length=(point2.x-d2.getX())*(point2.x-d2.getX())+(point2.y-d2.getY())*(point2.y-d2.getY());
						   length=(float) Math.pow(length, 0.5f);
						   MoveToAction moveto = Actions.moveTo(point2.x, point2.y, length/(300+score*1));
						   //SequenceAction sequenceAction = Actions.sequence(moveto1,moveto); 
						   d2.addAction(moveto);
					     ifcontinue2=1;
					   }
					   else
					   {
						  
						   stay2++;
					   }
					   
				   }
				   else if(state2==STATE.Right)
					   {
						   if(n2==0)
						   {
							   point2=left[m2];
							   dire=0;
						   }
						   if(n2==1)
						   {
							   if(pre2==2){
								   point2=down[m2];
								   dire=1;
							   }
							   else
							   {
								    point2=up[m2];
							   dire=3;
							   }
							  
						   }
						   if(n2==2)
						   {
							   if(pre2==0){
								   point2=up[m2];
								   dire=3;
							   }
							   else
							   {
								    point2=down[m2];
								    dire=1;
							   }
						   }
						   if(stay2>=10)
						   {
							   pre2=m2;
							  
							   stay2=0;
							   start2=0;
							 
							   float length=(point2.x-d2.getX())*(point2.x-d2.getX())+(point2.y-d2.getY())*(point2.y-d2.getY());
							   length=(float) Math.pow(length, 0.5f);
							   MoveToAction moveto = Actions.moveTo(point2.x, point2.y, length/(300+score*1));
						     d2.addAction(moveto);
						     ifcontinue2=1;
						   }
						   else
						   {
							  
							  stay2++;
						   }
						  
					   }
					   else if(state2==STATE.Down)
						   {
							   if(n2==0)
							   {
								   point2=up[m2];
								  dire=3;
							   }
							   if(n2==1)
							   {
								   if(pre2==0){
									   point2=right[m2];
									   dire=2;
								   }
								   else
								   {
									      point2=left[m2];
								   dire=0;
								   }
								
							   }
							   if(n2==2)
							   {
								   if(pre2==2){
									   point2=left[m2];
									   dire=0;
								   }
								   else
								   {
									    point2=right[m2];
								       dire=2;
								   } 
							   }
							   if(stay2>=10)
							   {
								   pre2=m2;
								
								   stay2=0;
								   start2=0;
								
								   float length=(point2.x-d2.getX())*(point2.x-d2.getX())+(point2.y-d2.getY())*(point2.y-d2.getY());
								   length=(float) Math.pow(length, 0.5f);
								   MoveToAction moveto = Actions.moveTo(point2.x, point2.y, length/(300+score*1));
							     d2.addAction(moveto);
							     ifcontinue2=1;
							   }
							   else
							   {
								 
								   stay2++;
							   }
							  
						   }
						   else if(state2==STATE.Up)
							   {
								   if(n2==0)
								   {
									   point2=down[m2];
									  dire=1;
								   }
								   if(n2==1)
								   {
									  
									   if(pre2==0){
										
										   point2=right[m2];
										    dire=2;
									   }
									   else
									   {
										    point2=left[m2];
									   dire=0;
									   }
									  
								   }
								   if(n2==2)
								   {
									  if(pre2==2){
										  point2=left[m2];
										  dire=0;
									  }
									  else
									  {
										   point2=right[m2];
										   dire=2;
									  }
									  
								   }
								   if(stay2>=10)
								   {
									   pre2=m2;
									 
									   stay2=0;
									   start2=0;
									
									   float length=(point2.x-d2.getX())*(point2.x-d2.getX())+(point2.y-d2.getY())*(point2.y-d2.getY());
									   length=(float) Math.pow(length, 0.5f);
									   MoveToAction moveto = Actions.moveTo(point2.x, point2.y, length/(300+score*1));
								     d2.addAction(moveto);
								     ifcontinue2=1;
								   }
								   else
								   {
									 
									 stay2++;
								   }
							   }
			   }
		   }
	   
	   /*
	   else
	   {
	
		   if(d.getX()==Lboder)
		   {
			   state=STATE.Left;
			   ifcontinue=0;
			   justflag();
			  // blockcolor.play=0;
			  // stay=1;
			   start++;
		   }
		   if(d.getX()==Rboder)
		   {
			   state=STATE.Right;
			   ifcontinue=0;
			   justflag();
			  // blockcolor.play=0;
			  // stay=1;
			   start++;
		   }
		   if(d.getY()==Uboder)
		   {
			   state=STATE.Up;
			   ifcontinue=0;
			  justflag();
			  // stay=1;
			   start++;
		   }
		   if(d.getY()==Dboder)
		   {
			   state=STATE.Down;
			   ifcontinue=0;
			   justflag();
			  // blockcolor.play=0;
			   //stay=1;
			   start++;
		   }
		   if(d2.getX()==Lboder)
		   {
			   state2=STATE.Left;
			   ifcontinue2=0;
			   justflag();
			  // blockcolor.play=0;
			  // stay=1;
			   start++;
		   }
		   if(d2.getX()==Rboder)
		   {
			   state2=STATE.Right;
			   ifcontinue2=0;
			   justflag();
			  // blockcolor.play=0;
			  // stay=1;
			   start++;
		   }
		   if(d2.getY()==Uboder)
		   {
			   state2=STATE.Up;
			   ifcontinue2=0;
			  justflag();
			  // stay=1;
			   start++;
		   }
		   if(d2.getY()==Dboder)
		   {
			   state2=STATE.Down;
			   ifcontinue2=0;
			   justflag();
			  // blockcolor.play=0;
			   //stay=1;
			   start++;
		   }
		   
		  // }
		   
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
					   blockcolor.play=0;
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
						   blockcolor.play=0;
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
							   blockcolor.play=0;   
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
								   blockcolor.play=0;
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
			   
			   
			   
			   
			   
			  
			   
			   //d2的

		   if(ifcontinue2==0)
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
			   if(state2==STATE.Left)
			   {
				   if(n==0)
				   {
					   point2=right[m];
					   dire=2;
				   }
				   if(n==1)
				   {
					   if(pre==2){
						   point2=down[m];
						   dire = 1;
					   }
					    else
					    {
					    	 point2=up[m];
					        dire=3;
					    }
					  
				   }
				   if(n==2)
				   {
					   if(pre==0){
						   point2=up[m];
						   dire=3;
					   }
					   else
					   { 
						     point2=down[m];
					        dire=1;
					   }
					 
				   }
				 if(stay>=10)
				   {	 
					 pre=m;
					   stay=0;
					   start=0;
					   blockcolor.play=0;
					   float length=(point2.x-d.getX())*(point2.x-d.getX())+(point2.y-d.getY())*(point2.y-d.getY());
					   length=(float) Math.pow(length, 0.5f);
					   MoveToAction moveto = Actions.moveTo(point2.x, point2.y, length/(300+score*1));
					   //SequenceAction sequenceAction = Actions.sequence(moveto1,moveto); 
					   d2.addAction(moveto);
				     ifcontinue2=1;
				   }
				   else
				   {
					   stay++;
				   }
				   
			   }
			   else if(state2==STATE.Right)
				   {
					   if(n==0)
					   {
						   point2=left[m];
						   dire=0;
					   }
					   if(n==1)
					   {
						   if(pre==2){
							   point2=down[m];
							   dire=1;
						   }
						   else
						   {
							    point2=up[m];
						   dire=3;
						   }
						  
					   }
					   if(n==2)
					   {
						   if(pre==0){
							   point2=up[m];
							   dire=3;
						   }
						   else
						   {
							    point2=down[m];
							    dire=1;
						   }
					   }
					   if(stay>=10)
					   {
						   pre=m;
						   stay=0;
						   start=0;
						   blockcolor.play=0;
						   float length=(point2.x-d.getX())*(point2.x-d.getX())+(point2.y-d.getY())*(point2.y-d.getY());
						   length=(float) Math.pow(length, 0.5f);
						   MoveToAction moveto = Actions.moveTo(point2.x, point2.y, length/(300+score*1));
					     d2.addAction(moveto);
					     ifcontinue2=1;
					   }
					   else
					   {
						  stay++;
					   }
					  
				   }
				   else if(state2==STATE.Down)
					   {
						   if(n==0)
						   {
							   point2=up[m];
							  dire=3;
						   }
						   if(n==1)
						   {
							   if(pre==0){
								   point2=right[m];
								   dire=2;
							   }
							   else
							   {
								      point2=left[m];
							   dire=0;
							   }
							
						   }
						   if(n==2)
						   {
							   if(pre==2){
								   point2=left[m];
								   dire=0;
							   }
							   else
							   {
								    point2=right[m];
							       dire=2;
							   } 
						   }
						   if(stay>=10)
						   {
							   pre=m;
							  
							   stay=0;
							   start=0;
							   blockcolor.play=0;   
							   float length=(point2.x-d.getX())*(point2.x-d.getX())+(point2.y-d.getY())*(point2.y-d.getY());
							   length=(float) Math.pow(length, 0.5f);
							   MoveToAction moveto = Actions.moveTo(point2.x, point2.y, length/(300+score*1));
						     d2.addAction(moveto);
						     ifcontinue2=1;
						   }
						   else
						   {
							  
							   stay++;
						   }
						  
					   }
					   else if(state2==STATE.Up)
						   {
							   if(n==0)
							   {
								   point2=down[m];
								  dire=1;
							   }
							   if(n==1)
							   {
								  
								   if(pre==0){
									
									   point2=right[m];
									    dire=2;
								   }
								   else
								   {
									    point2=left[m];
								   dire=0;
								   }
								  
							   }
							   if(n==2)
							   {
								  if(pre==2){
									  point2=left[m];
									  dire=0;
								  }
								  else
								  {
									   point2=right[m];
									   dire=2;
								  }
								  
							   }
							   if(stay>=10)
							   {
								   pre=m;
								 
								   stay=0;
								   start=0;
								   blockcolor.play=0;
								   float length=(point2.x-d.getX())*(point2.x-d.getX())+(point2.y-d.getY())*(point2.y-d.getY());
								   length=(float) Math.pow(length, 0.5f);
								   MoveToAction moveto = Actions.moveTo(point2.x, point2.y, length/(300+score*1));
							     d2.addAction(moveto);
							     ifcontinue2=1;
							   }
							   else
							   {
								 
								 stay++;
							   }
						   }
		   }
	   }
	  */ 
	   
	   
   }
	@Override
	public void draw(SpriteBatch batch, float parentAlpha) {
		// TODO Auto-generated method stub
		 if(crazyFirstGame.playMusic==-1)
		 {
			 justflag();
	       drawLine();
	     r++;
	     if(r==40)
	     {
	    	 r=0;
	    	 playcount=0;
	    	 playcount2=0;
	     }
		 }
			 
	}

}
