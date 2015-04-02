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

public class blueJump extends Actor
{
	float x,y;
	static int score=0;
	private static int flag=0;
	private static int count=1;
	static int m,n;
	
	static int stay=0;//表示球在边界停留一段时间
	int[] flagcount=new int[12];
	//dire代表下一次碰撞点的方向，0表示左，1表示下，2表示右，3表示上num表示下表，
	static int num,dire;
	static int ifdraw=0;
	static String commu;
	//当这个类中的ifdraw=1表示碰到了边界，和Ball中count=0,表示没有按下任何按键，此时变红
	public static float ballX,ballY;
	public static int left1,up1,down1,right1,left2,up2,down2,right2,left3,up3,down3,right3;
	public static int pleft1,pup1,pdown1,pright1,pleft2,pup2,pdown2,pright2,pleft3,pup3,pdown3,pright3;
	Texture jumpball;
	/*
	private int Lboder=(int) (340*blueMainActivity.w);
	private int Rboder=(int) (816*blueMainActivity.w);
	private int Uboder=(int) (576*blueMainActivity.h);
	private int Dboder=(int) (100*blueMainActivity.h);
	*/
	private int Lboder=(int)crowball.LBorder;
	private int Rboder=(int)crowball.RBorder;
	private int Uboder=(int)crowball.UBorder;
	private int Dboder=(int)crowball.DBorder;
	static int pre;
	static int ifcontinue=0;
	PointF point=null;
	static Sound[] sound=new Sound[26];
	Random ran=new Random();
	static int start=0;
	int nextX,nextY;
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
 public blueJump()
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
		d.setSize(64*blueMainActivity.w, 64*blueMainActivity.h);
		initMusic();
	// this.x=Lboder;
	// this.y=(Uboder+Dboder)/2;
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
		 height=(int) (Dboder+(2*i+1)*80*blueMainActivity.h-32*blueMainActivity.h);
		 point=new PointF(width,height);
		 right[i]=point;
	 }
	 this.x=left[1].x;
	 this.y=left[1].y;
	 this.state=STATE.Left;
	 d.setPosition(550*blueMainActivity.w, 320*blueMainActivity.h);
	
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
			if(d.getX()<=Lboder+160*blueMainActivity.w)
			{
				up1=1;
				if(blueFirstGame.ifexist[0]==1&&playcount==0)
				{
					score++;
					int n=ran.nextInt(25);
					//n=music.star[lyric%lyriccount];
					n=l[lyric%lyriccount];
					lyric++;
					sound[n].play();
					playcount++;
				}
				if(blueFirstGame.ifexist[0]==0)
					fail=1;
			}else if(d.getX()<=Lboder+320*blueMainActivity.w)
			{
				up2=1;
				if(blueFirstGame.ifexist[1]==1&&playcount==0)
				{
					score++;
					int n=ran.nextInt(25);
					//n=music.star[lyric%lyriccount];
					n=l[lyric%lyriccount];
					lyric++;
					sound[n].play();
					playcount++;
				}
				if(blueFirstGame.ifexist[1]==0)
					fail=1;
			}
			else
			{
				up3=1;
				if(blueFirstGame.ifexist[2]==1&&playcount==0)
				{
					score++;
					int n=ran.nextInt(25);
					//n=music.star[lyric%lyriccount];
					n=l[lyric%lyriccount];
					lyric++;
					sound[n].play();
					playcount++;
				}
				if(blueFirstGame.ifexist[2]==0)
					fail=1;
			}
			ifcontinue=0;
		} 
		}
	    if(d.getX()<=Lboder)
	    {
	    	if(ifcontinue==1){
	    	ifdraw=1;
	    	if(d.getY()<=Dboder+160*blueMainActivity.h)
			{
				left1=1;
				if(blueFirstGame.ifexist[9]==1&&playcount==0)
				{
					score++;
					int n=ran.nextInt(25);
					//n=music.star[lyric%lyriccount];
					n=l[lyric%lyriccount];
					lyric++;
					sound[n].play();
					playcount++;
				}
				if(blueFirstGame.ifexist[9]==0)
					fail=1;
				
			}else if(d.getY()<=Dboder+320*blueMainActivity.h)
			{
				left2=1;
				if(blueFirstGame.ifexist[10]==1&&playcount==0)
				{
					score++;
					int n=ran.nextInt(25);
					//n=music.star[lyric%lyriccount];
					n=l[lyric%lyriccount];
					lyric++;
					sound[n].play();
					playcount++;
				}
				if(blueFirstGame.ifexist[10]==0)
					fail=1;
			}
			else
			{
				left3=1;
				if(blueFirstGame.ifexist[11]==1&&playcount==0)
				{
					score++;
					int n=ran.nextInt(25);
					//n=music.star[lyric%lyriccount];
					n=l[lyric%lyriccount];
					lyric++;
					sound[n].play();
					playcount++;
				}
				if(blueFirstGame.ifexist[11]==0)
					fail=1;
			}
	    	ifcontinue=0;
	    }
	    }
	    if(d.getX()>=Rboder)
	    {
	    	if(ifcontinue==1){
	    	ifdraw=1;
	    	
	    	
	    	if(d.getY()<=Dboder+160*blueMainActivity.h)
			{
				right1=1;
				if(blueFirstGame.ifexist[5]==1&&playcount==0)
				{
					score++;
					int n=ran.nextInt(25);
					//n=music.star[lyric%lyriccount];
					n=l[lyric%lyriccount];
					lyric++;
					sound[n].play();
					playcount++;
				}
				if(blueFirstGame.ifexist[5]==0)
					fail=1;
			}else if(d.getY()<=Dboder+320*blueMainActivity.h)
			{
				right2=1;
				if(blueFirstGame.ifexist[4]==1&&playcount==0)
				{
					score++;
					int n=ran.nextInt(25);
				//	n=music.star[lyric%lyriccount];
					n=l[lyric%lyriccount];
					lyric++;
					sound[n].play();
					playcount++;
				}
				if(blueFirstGame.ifexist[4]==0)
					fail=1;
			}
			else
			{
				right3=1;
				if(blueFirstGame.ifexist[3]==1&&playcount==0)
				{
					score++;
					int n=ran.nextInt(25);
					//n=music.star[lyric%lyriccount];
					n=l[lyric%lyriccount];
					lyric++;
					sound[n].play();
					playcount++;
				}
				if(blueFirstGame.ifexist[3]==0)
					fail=1;
			}
	    	ifcontinue=0;
	    }
	    }
	    if(d.getY()<=Dboder)
	    {
	    	if(ifcontinue==1){
	    	ifdraw=1;
	    	
	    	
	    	if(d.getX()<=Lboder+160*blueMainActivity.w)
			{
				down1=1;
				if(blueFirstGame.ifexist[8]==1&&playcount==0)
				{
					score++;
					int n=ran.nextInt(25);
					//n=music.star[lyric%lyriccount];
					n=l[lyric%lyriccount];
					lyric++;
					sound[n].play();
					playcount++;
				}
				if(blueFirstGame.ifexist[8]==0)
					fail=1;
			}else if(d.getX()<=Lboder+320*blueMainActivity.w)
			{
				down2=1;
				if(blueFirstGame.ifexist[7]==1&&playcount==0)
				{
					score++;
					int n=ran.nextInt(25);
					//n=music.star[lyric%lyriccount];
					n=l[lyric%lyriccount];
					lyric++;
					sound[n].play();
					playcount++;
				}
				if(blueFirstGame.ifexist[7]==0)
					fail=1;
			}
			else
			{
				down3=1;
				if(blueFirstGame.ifexist[6]==1&&playcount==0)
				{
					score++;
					int n=ran.nextInt(25);
					//n=music.star[lyric%lyriccount];
					n=l[lyric%lyriccount];
					lyric++;
					sound[n].play();
					playcount++;
				}
				if(blueFirstGame.ifexist[6]==0)
					fail=1;
			}
	    	ifcontinue=0;
	    }
   }
   }
   public String trans(float x,float y)
   {
	   for(int i=0;i<3;i++)
	   {
		   if(up[i].x==x&&up[i].y==y)
	     {
		   return 0+" "+i;
	      }
		   if(down[i].x==x&&down[i].y==y)
		     {
			   return 2+" "+i;
		      }
		   if(left[i].x==x&&left[i].y==y)
		     {
			   return 3+" "+i;
		      }
		   if(right[i].x==x&&right[i].y==y)
		     {
			   return 1+" "+i;
		      }
	   }
	   
	return null;
   }
   public void transMes()
   {
	   if(bluetooth.serviceOrCilent==bluetooth.ServerOrCilent.SERVICE)
	   {
		   System.out.println("this is SERVICE");
		   //commu=point.x+" "+point.y;
		   commu=trans(point.x,point.y);
		   System.out.println("服务端传送数据为"+commu);
          bluetooth.trans(commu);
	   }
	   /*
	   else if(bluetooth.serviceOrCilent==bluetooth.ServerOrCilent.CILENT)
	   {
		   System.out.println("this is CLIENT");
		   String s=bluetooth.receive;
		   if(s!=null)
		   {
			   String a[]=s.split(" ");
			   System.out.println("数据分割为"+a[0]);
			   try
			   {
				   point.x=Integer.parseInt(a[0]);
			   point.y=Integer.parseInt(a[1]);
			   }
			   catch(Exception e)
			   {
				   System.out.println("小球运动轨迹交换数据出错");
			   }
		   }
		  
	   }
	   */
   }
   public void drawLine()
   {
	   //if(stay==0)
	  // {
	  
		   if(d.getX()<=Lboder)
	   {
		   state=STATE.Left;
		  // ifcontinue=0;
		   justflag();
		  // blockcolor.play=0;
		  // stay=1;
		   start++;
	   }
	   if(d.getX()>=Rboder)
	   {
		   state=STATE.Right;
		 //  ifcontinue=0;
		  // blockcolor.play=0;
		  // stay=1;
		   justflag();
		   start++;
	   }
	   if(d.getY()>=Uboder)
	   {
		   state=STATE.Up;
		   //ifcontinue=0;
		   justflag();
		  // stay=1;
		   start++;
	   }
	   if(d.getY()<=Dboder)
	   {
		   state=STATE.Down;
		   justflag();
		   //ifcontinue=0;
		  // blockcolor.play=0;
		   //stay=1;
		   start++;
	   }
	   
	  // }
	   
	   if(ifcontinue==0)
	   {
		   if(start==0||start==1)
		   {
			   System.out.println("进入小球运动阶段");
			  // if(bluetooth.serviceOrCilent==bluetooth.ServerOrCilent.SERVICE)
			 //  {
				   System.out.println("this is SERVICE");
				     Random ran=new Random();
		          m=ran.nextInt(3);
		          n=ran.nextInt(3);
		          int code=m*10+n;
		   }
		  // start++;
		  num=m;
		   if(state==STATE.Left)
		   {
			   /*
			   if(n==0)
			   {
				   if(blueFirstGame.ifexist[m+3]==1)
				   {
					   int j=m;
					    while(j==m)
					   m=new Random().nextInt(3);
					    m=2;
				   }
				   point=right[m];
				   dire=2;
				  // System.out.println("right+down"+m);
			   }
			   if(n==1)
			   {
				   if(blueFirstGame.ifexist[m]==1)
				   {
					   int j=m;
					    while(j==m)
					   m=new Random().nextInt(3);
				   }
				   point=up[m];
				   dire=3;
			   }
			   if(n==2)
			   {
				   if(blueFirstGame.ifexist[m+6]==1)
				   {
					   int j=m;
					    while(j==m)
					   m=new Random().nextInt(3);
				   }
				   point=down[m];
				   dire=1;
			   }
			   */if(n==0)
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
			// if(stay>=10)
			//   {
			       pre=m;
				   stay=0;
				   start=0;
				   transMes();
				  // System.out.println("nextX="+nextX);
				  // System.out.println("nextY="+nextY);
				   if(bluetooth.serviceOrCilent==bluetooth.ServerOrCilent.CILENT&&nextX!=0)
				   {
					   System.out.println("nextX="+nextX);
					   System.out.println("nextY="+nextY);
					   float length=(nextX-d.getX())*(nextX-d.getX())+(nextY-d.getY())*(nextY-d.getY());
					   length=(float) Math.pow(length, 0.5f);
					   MoveToAction moveto = Actions.moveTo(nextX, nextY,1f);
				     d.addAction(moveto);
				     ifcontinue=1;
				   }
				   if(bluetooth.serviceOrCilent==bluetooth.ServerOrCilent.SERVICE)
				   {
					    float length=(point.x-d.getX())*(point.x-d.getX())+(point.y-d.getY())*(point.y-d.getY());
				     length=(float) Math.pow(length, 0.5f);
				     MoveToAction moveto = Actions.moveTo(point.x, point.y, 1f);
			        d.addAction(moveto); 
			        ifcontinue=1;
				   }
			    
			//  }
			//   else
			//   {
			//	   stay++;
			//   }
			   
		   }
		   else if(state==STATE.Right)
			   {
				  // System.out.println("right");
			   /*
				   if(n==0)
				   {
					   if(blueFirstGame.ifexist[m+9]==1)
					   {
						   int j=m;
						    while(j==m)
						   m=new Random().nextInt(3);
					   }
					   point=left[m];
					   dire=0;
					   //System.out.println("right+left"+m);
				   }
				   if(n==1)
				   {
					   if(blueFirstGame.ifexist[m]==1)
					   {
						   int j=m;
						    while(j==m)
						   m=new Random().nextInt(3);
					   }
					   point=up[m];
					   dire=3;
					  // System.out.println("right+up"+m);
				   }
				   if(n==2)
				   {
					   if(blueFirstGame.ifexist[m+6]==1)
					   {
						   int j=m;
						    while(j==m)
						   m=new Random().nextInt(3);
					   }
					   point=down[m];
					   dire=1;
					  // System.out.println("right+down"+m);
				   }
				   */
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
				//   if(stay>=10)
				//  {
			   pre=m;
					   stay=0;
					   start=0;
					  
					   transMes();
					  // System.out.println("nextX="+nextX);
					 //  System.out.println("nextY="+nextY);
					   if(bluetooth.serviceOrCilent==bluetooth.ServerOrCilent.CILENT&&nextX!=0)
					   {
						   System.out.println("nextX="+nextX);
						   System.out.println("nextY="+nextY);
						   float length=(nextX-d.getX())*(nextX-d.getX())+(nextY-d.getY())*(nextY-d.getY());
						   length=(float) Math.pow(length, 0.5f);
						   //length/(300+score*2)
						   MoveToAction moveto = Actions.moveTo(nextX, nextY,1f);
					     d.addAction(moveto);
					     ifcontinue=1;
					   }
					   if(bluetooth.serviceOrCilent==bluetooth.ServerOrCilent.SERVICE)
					   {
						    float length=(point.x-d.getX())*(point.x-d.getX())+(point.y-d.getY())*(point.y-d.getY());
					     length=(float) Math.pow(length, 0.5f);
					     MoveToAction moveto = Actions.moveTo(point.x, point.y, 1f);
				        d.addAction(moveto);
				        ifcontinue=1;
					   }
				    
			//	  }
			//	   else
				//   {
				//	  stay++;
				//   }
				  
			   }
			   else if(state==STATE.Down)
				   {
				   /*
					   if(n==0)
					   {
						   if(blueFirstGame.ifexist[m]==1)
						   {
							   int j=m;
							    while(j==m)
							   m=new Random().nextInt(3);
						   }
						   point=up[m];
						  dire=3;
					   }
					   if(n==1)
					   {
						   if(blueFirstGame.ifexist[m+9]==1)
						   {
							   int j=m;
							    while(j==m)
							   m=new Random().nextInt(3);
						   }
						   point=left[m];
						   dire=0;
					   }
					   if(n==2)
					   {
						   if(blueFirstGame.ifexist[m+3]==1)
						   {
							   int j=m;
							    while(j==m)
							   m=new Random().nextInt(3);
						   }
						   point=right[m];
						  dire=2;
					   }
					   */
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
					 //  if(stay>=10)
					 //  {
				           pre=m;
						   stay=0;
						   start=0;
						
						   transMes();
						 //  System.out.println("nextX="+nextX);
						 //  System.out.println("nextY="+nextY);
						   if(bluetooth.serviceOrCilent==bluetooth.ServerOrCilent.CILENT&&nextX!=0)
						   {
							   System.out.println("nextX="+nextX);
							   System.out.println("nextY="+nextY);
							   float length=(nextX-d.getX())*(nextX-d.getX())+(nextY-d.getY())*(nextY-d.getY());
							   length=(float) Math.pow(length, 0.5f);
							   MoveToAction moveto = Actions.moveTo(nextX, nextY,1f);
						     d.addAction(moveto);
						     ifcontinue=1;
						   }
						   if(bluetooth.serviceOrCilent==bluetooth.ServerOrCilent.SERVICE)
						   {
							    float length=(point.x-d.getX())*(point.x-d.getX())+(point.y-d.getY())*(point.y-d.getY());
						     length=(float) Math.pow(length, 0.5f);
						     MoveToAction moveto = Actions.moveTo(point.x, point.y,1f);
					        d.addAction(moveto);
					        ifcontinue=1;
						   }
					    
					//   }
					//  else
					//   {
						 //  stay++;
					 // }
					  
				   }
				   else if(state==STATE.Up)
					   {
					   /*
						   if(n==0)
						   {
							   if(blueFirstGame.ifexist[m+6]==1)
							   {
								   int j=m;
								    while(j==m)
								   m=new Random().nextInt(3);
								    m=2;
							   }
							   point=down[m];
							  dire=1;
						   }
						   if(n==1)
						   {
							   if(blueFirstGame.ifexist[m+9]==1)
							   {
								   int j=m;
								    while(j==m)
								   m=new Random().nextInt(3);
							   }
							   point=left[m];
							   dire=0;
						   }
						   if(n==2)
						   {
							      
							   if(blueFirstGame.ifexist[m+3]==1)
							   {
								   int j=m;
								    while(j==m)
								   m=new Random().nextInt(3);
							   }
								  
							   point=right[m];
							  dire=2;
						   }
						   */
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
						//   if(stay>=10)
						//   {
					   pre=m;
							   stay=0;
							   start=0;
							  
							   transMes();
							  // System.out.println("nextX="+nextX);
							   //ystem.out.println("nextY="+nextY);
							   if(bluetooth.serviceOrCilent==bluetooth.ServerOrCilent.CILENT&&nextX!=0)
							   {
								   System.out.println("nextX="+nextX);
								   System.out.println("nextY="+nextY);
								   float length=(nextX-d.getX())*(nextX-d.getX())+(nextY-d.getY())*(nextY-d.getY());
								   length=(float) Math.pow(length, 0.5f);
								   MoveToAction moveto = Actions.moveTo(nextX, nextY,1f);
							     d.addAction(moveto);
							     ifcontinue=1;
							   }
							   if(bluetooth.serviceOrCilent==bluetooth.ServerOrCilent.SERVICE)
							   {
								    float length=(point.x-d.getX())*(point.x-d.getX())+(point.y-d.getY())*(point.y-d.getY());
							     length=(float) Math.pow(length, 0.5f);
							     MoveToAction moveto = Actions.moveTo(point.x, point.y,1f);
						        d.addAction(moveto);
						        ifcontinue=1;
							   }
							  
						    
						//  }
						 //  else
						 //  {
						//	 stay++;
						 //  }
					   }
	   }
	   
   }
   public String transToLocal(String s)
   {
	   if(s.equals(0+" "+0))
	   {
		   return up[0].x+" "+up[0].y;
	   }
	   if(s.equals(0+" "+1))
	   {
		   return up[1].x+" "+up[1].y;
	   }
	   if(s.equals(0+" "+2))
	   {
		   return up[2].x+" "+up[2].y;
	   }
	   if(s.equals(1+" "+0))
	   {
		   return right[0].x+" "+right[0].y;
	   }
	   if(s.equals(1+" "+1))
	   {
		   return right[1].x+" "+right[1].y;
	   }
	   if(s.equals(1+" "+2))
	   {
		   return right[2].x+" "+right[2].y;
	   }
	   if(s.equals(2+" "+0))
	   {
		   return down[0].x+" "+down[0].y;
	   }
	   if(s.equals(2+" "+1))
	   {
		   return down[1].x+" "+down[1].y;
	   }
	   if(s.equals(2+" "+2))
	   {
		   return down[2].x+" "+down[2].y;
	   }
	   if(s.equals(3+" "+0))
	   {
		   return left[0].x+" "+left[0].y;
	   }
	   if(s.equals(3+" "+1))
	   {
		   return left[1].x+" "+left[1].y;
	   }
	   if(s.equals(3+" "+2))
	   {
		   return left[2].x+" "+left[2].y;
	   }
	return s; 
   }
	@Override
	public void draw(SpriteBatch batch, float parentAlpha) {
		// TODO Auto-generated method stub
		 float duration = MathUtils.random(3, 10);
		 //if(FirstGame.start==0)
		// {
		//System.out.println("d");
		 if(blueFirstGame.playMusic==-1)
		{
		 
		//System.out.println("drawlinefdsfdsfdsfds");
		
		   
		 //if(bluetooth.serviceOrCilent==bluetooth.ServerOrCilent.SERVICE)
		// {
			 drawLine();
		// }
			// justflag();
			 if(bluetooth.serviceOrCilent==bluetooth.ServerOrCilent.CILENT)
			   {
				  // System.out.println("this is CLIENT");
				   String s=transToLocal(bluetooth.receive);
				   System.out.println("s="+s);
				   if(s!=null)
				   {
					   String a[]=s.split(" ");
					   System.out.println("数据分割为"+a[0]);
					   try
					   {
						  int  mm=(int)Float.parseFloat(a[0]);
						   if(mm<10000)
						   {
							    nextX=(int)Float.parseFloat(a[0]);
						        nextY=(int)Float.parseFloat(a[1]);
						   }
						  
					   }
					   catch(Exception e)
					   {
						   System.out.println("小球运动轨迹交换数据出错");
					   }
				   }
				  // clientLine();
				  
			   }
	     r++;
	     if(r==40)
	     {
	    	 r=0;
	    	 playcount=0;
	     }
	     if(fail==1)
	     {
	    	// System.out.println("碰撞错误");
	    	// GameSwitch gs=new GameSwitch(new fail());
	    	 bluetooth.trans("fail");
	    	 blueFirstGame.gs.setScreen(blueFirstGame.gs.bf);
	     }
	     if(bluetooth.serviceOrCilent==bluetooth.ServerOrCilent.CILENT)
	     {
	    	 if(bluetooth.receive.equals("fail"))
	    	 {
	    		 System.out.println("服务端挂了");
	    		 blueFirstGame.gs.setScreen(blueFirstGame.gs.bf);
	    	 }
	     }
		// }
			 
		// }
		}
	      //System.out.println("坐标"+d.getX()+" "+d.getY());
	}

}
