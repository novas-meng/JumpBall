package com.example.jumpball;

import java.util.Random;

import android.content.Intent;
import android.graphics.PointF;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;
import com.badlogic.gdx.scenes.scene2d.actions.SizeToAction;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.example.jumpball.crowJump.STATE;

public class ghostJump extends Actor
{
	Image mask,mask_up,mask_down,mask_left,mask_right;
	float x,y;
	float duration;
	boolean playMusic=true;
	static Music background;
	boolean hasplay=false;
	SizeToAction sta;
	SizeToAction sta1;
	static int score=0;
	static int m,n;
	static int stay=0;//表示球在边界停留一段时间
	int[] flagcount=new int[12];
	//dire代表下一次碰撞点的方向，0表示左，1表示下，2表示右，3表示上num表示下表，
	static int num,dire;
	static int ifdraw=0;
	//当这个类中的ifdraw=1表示碰到了边界，和Ballforghost中count=0,表示没有按下任何按键，此时变红
	public static float BallforghostX,BallforghostY;
	public static int left1,up1,down1,right1,left2,up2,down2,right2,left3,up3,down3,right3;
	public static int pleft1,pup1,pdown1,pright1,pleft2,pup2,pdown2,pright2,pleft3,pup3,pdown3,pright3;
	Texture jumpBallforghost;
	private int Lboder=(int)Ballforghost.LBorder;
	private int Rboder=(int)Ballforghost.RBorder;
	private int Uboder=(int)Ballforghost.UBorder;
	private int Dboder=(int)Ballforghost.DBorder;
	static int pre;
	static int ifcontinue=0;
	PointF point=null;
	static Sound[] sound=new Sound[26];
	static Sound ghost = Gdx.audio.newSound(Gdx.files.internal("music/ghost_hit.mp3"));
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
	 static  STATE state2;
	 static  STATE state3;
	 static  STATE state4;
	 static Image d;
	 
	   //代表播放音乐，只有为0的时候才播放
	   static int playcount=0;
	   static int r=0;
	   //定义播放的音节次序
	   static int lyric=0;
	   static int lyriccount=0;
	   static int fail=0;
	   static int[] l;
 public ghostJump()
 {
	 Texture.setEnforcePotImages(false);
	 background=Gdx.audio.newMusic(Gdx.files.internal("music/ghost_back.mp3"));
	 background.play();
	 background.setLooping(true);
	 mask=new Image(new Texture(Gdx.files.internal("pic/Mask.png")));
		mask.setSize(2000*ghostMainActivity.w, 1400*ghostMainActivity.h);
		mask.setPosition(-360*ghostMainActivity.w,-340*ghostMainActivity.h);
		/*
		mask_up=new Image(new Texture(Gdx.files.internal("pic/mask_board.png")));
		mask_up.setSize(1280,120);
		mask_up.setPosition(0, 600);
		mask_down=new Image(new Texture(Gdx.files.internal("pic/mask_board.png")));
		mask_down.setSize(1280,40);
		mask_down.setPosition(0, 0);
		mask_left=new Image(new Texture(Gdx.files.internal("pic/mask_board.png")));
		mask_left.setSize(70,720);
		mask_left.setPosition(0, 0);
		mask_right=new Image(new Texture(Gdx.files.internal("pic/mask_board.png")));
		mask_right.setSize(250,720);
		mask_right.setPosition(1030,0);
		*/
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
//	 jumpBallforghost=new Texture(Gdx.files.internal("ghost_ball.png"));
	 Texture txtup=new Texture(Gdx.files.internal("ghost/ghost_ball.png"));
//		Texture txtdown=new Texture(Gdx.files.internal("ghost_ball.png"));
//		TextureRegionDrawable trd=new TextureRegionDrawable(new TextureRegion(txtdown));
//		TextureRegionDrawable tru=new TextureRegionDrawable(new TextureRegion(txtup));
		d=new Image(txtup);
		
/*		 txtup=new Texture(Gdx.files.internal("Ballforghost_confus2.png"));
		 txtdown=new Texture(Gdx.files.internal("Ballforghost_confus2.png"));
		 trd=new TextureRegionDrawable(new TextureRegion(txtdown));
		 tru=new TextureRegionDrawable(new TextureRegion(txtup));*/
		initMusic();
	// this.x=Lboder;
	// this.y=(Uboder+Dboder)/2;
	 for(int i=0;i<3;i++)
	 {
		 int width=(int) (Lboder+(2*i+1)*80*ghostMainActivity.w-32*ghostMainActivity.w);
		 int height=Uboder;
		 PointF point=new PointF(width,height);
		 up[i]=point;
		 
		 width=Lboder;
		 height=(int) (Dboder+(2*i+1)*80*ghostMainActivity.h-32*ghostMainActivity.h);
		 point=new PointF(width,height);
		 left[i]=point;
		 
		 width=(int) (Lboder+(2*i+1)*80*ghostMainActivity.w-32*ghostMainActivity.w);
		 height=Dboder;
		 point=new PointF(width,height);
		 down[i]=point;
		 
		 width=Rboder;
		 height=(int) (Dboder+(2*i+1)*80*ghostMainActivity.h-32*ghostMainActivity.h);
  		 point=new PointF(width,height);
		 right[i]=point;
	 }
	 this.x=left[1].x;
	 this.y=left[1].y;
	 this.state=STATE.Left;
	 d.setPosition(550*ghostMainActivity.w, 320*ghostMainActivity.h);
//	 d2.setPosition(550*MainActivity.w+100, 320*MainActivity.h);
//	 d2.setPosition(d.getX(),d.getY());
	
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
	/*
   public void justflag()
   {
	   if(ifcontinue == 1){
	   if(d.getY()>=Uboder)
		{
		   ifdraw=1;
			if(d.getX()<=Lboder+160)
			{
				up1=1;
				if(ghostFirstGame.ifexist[0]==1&&playcount==0)
				{
					score++;
					int n=ran.nextInt(25);
					//n=music.star[lyric%lyriccount];
					n=l[lyric%lyriccount];
					lyric++;
				//	ghost.play();
					playcount++;
				}
				if(ghostFirstGame.ifexist[0]==0)
					fail=1;
			}else if(d.getX()<=Lboder+320)
			{
				up2=1;
				if(ghostFirstGame.ifexist[1]==1&&playcount==0)
				{
					score++;
					int n=ran.nextInt(25);
					//n=music.star[lyric%lyriccount];
					n=l[lyric%lyriccount];
					lyric++;
					//ghost.play();
					playcount++;
				}
				if(ghostFirstGame.ifexist[1]==0)
					fail=1;
			}
			else
			{
				up3=1;
				if(ghostFirstGame.ifexist[2]==1&&playcount==0)
				{
					score++;
					int n=ran.nextInt(25);
					//n=music.star[lyric%lyriccount];
					n=l[lyric%lyriccount];
					lyric++;
					//ghost.play();
					playcount++;
				}
				if(ghostFirstGame.ifexist[2]==0)
					fail=1;
			}
		} 
	   else if(d.getX()<=Lboder)
	    {
	    	ifdraw=1;
	    	if(d.getY()<=Dboder+160)
			{
				left1=1;
				if(ghostFirstGame.ifexist[9]==1&&playcount==0)
				{
					score++;
					int n=ran.nextInt(25);
					//n=music.star[lyric%lyriccount];
					n=l[lyric%lyriccount];
					lyric++;
					//ghost.play();
					playcount++;
				}
				if(ghostFirstGame.ifexist[9]==0)
					fail=1;
				
			}else if(d.getY()<=Dboder+320)
			{
				left2=1;
				if(ghostFirstGame.ifexist[10]==1&&playcount==0)
				{
					score++;
					int n=ran.nextInt(25);
					//n=music.star[lyric%lyriccount];
					n=l[lyric%lyriccount];
					lyric++;
					//ghost.play();
					playcount++;
				}
				if(ghostFirstGame.ifexist[10]==0)
					fail=1;
			}
			else
			{
				left3=1;
				if(ghostFirstGame.ifexist[11]==1&&playcount==0)
				{
					score++;
					int n=ran.nextInt(25);
					//n=music.star[lyric%lyriccount];
					n=l[lyric%lyriccount];
					lyric++;
					//ghost.play();
					playcount++;
				}
				if(ghostFirstGame.ifexist[11]==0)
					fail=1;
			}
	    }
	   else if(d.getX()>=Rboder)
	    {
	    	ifdraw=1;
	    	
	    	
	    	if(d.getY()<=Dboder+160)
			{
				right1=1;
				if(ghostFirstGame.ifexist[5]==1&&playcount==0)
				{
					score++;
					int n=ran.nextInt(25);
					//n=music.star[lyric%lyriccount];
					n=l[lyric%lyriccount];
					lyric++;
					//ghost.play();
					playcount++;
				}
				if(ghostFirstGame.ifexist[5]==0)
					fail=1;
			}else if(d.getY()<=Dboder+320)
			{
				right2=1;
				if(ghostFirstGame.ifexist[4]==1&&playcount==0)
				{
					score++;
					int n=ran.nextInt(25);
				//	n=music.star[lyric%lyriccount];
					n=l[lyric%lyriccount];
					lyric++;
					//ghost.play();
					playcount++;
				}
				if(ghostFirstGame.ifexist[4]==0)
					fail=1;
			}
			else
			{
				right3=1;
				if(ghostFirstGame.ifexist[3]==1&&playcount==0)
				{
					score++;
					int n=ran.nextInt(25);
					//n=music.star[lyric%lyriccount];
					n=l[lyric%lyriccount];
					lyric++;
					//ghost.play();
					playcount++;
				}
				if(ghostFirstGame.ifexist[3]==0)
					fail=1;
			}
	    }
	    
	   else if(d.getY()<=Dboder)
	    {
	    	ifdraw=1;
	    	
	    	
	    	if(d.getX()<=Lboder+160)
			{
				down1=1;
				if(ghostFirstGame.ifexist[8]==1&&playcount==0)
				{
					score++;
					int n=ran.nextInt(25);
					//n=music.star[lyric%lyriccount];
					n=l[lyric%lyriccount];
					lyric++;
					//ghost.play();
					playcount++;
				}
				if(ghostFirstGame.ifexist[8]==0)
					fail=1;
			}else if(d.getX()<=Lboder+320)
			{
				down2=1;
				if(ghostFirstGame.ifexist[7]==1&&playcount==0)
				{
					score++;
					int n=ran.nextInt(25);
					//n=music.star[lyric%lyriccount];
					n=l[lyric%lyriccount];
					lyric++;
					//ghost.play();
					playcount++;
				}
				if(ghostFirstGame.ifexist[7]==0)
					fail=1;
			}
			else
			{
				down3=1;
				if(ghostFirstGame.ifexist[6]==1&&playcount==0)
				{
					score++;
					int n=ran.nextInt(25);
					//n=music.star[lyric%lyriccount];
					n=l[lyric%lyriccount];
					lyric++;
					//ghost.play();
					playcount++;
				}
				if(ghostFirstGame.ifexist[6]==0)
					fail=1;
			}
	    }
	   ifcontinue = 0;
	   }
   }
   /*
   public void drawLine()
   {
	   
		 if(d.getX()==Lboder)
		   {
			   state=STATE.Left;
			   justflag();
			  // blockcolor.play=0;
			  // stay=1;
			   start++;
		   }
		   if(d.getX()==Rboder)
		   {
			   state=STATE.Right;
			   justflag();
			  // blockcolor.play=0;
			  // stay=1;
			   start++;
		   }
		   if(d.getY()==Uboder)
		   {
			   state=STATE.Up;
			  justflag();
			  // stay=1;
			   start++;
		   }
		   if(d.getY()==Dboder)
		   {
			   state=STATE.Down;
			   justflag();
			  // blockcolor.play=0;
			   //stay=1;
			   start++;
		   }
		   
		  // }
		   if(score == 0)
		   {
			   for(m = 0;;m++)
			   {
				   if(ghostFirstGame.ifexist[m+3] == 1)
					   break;
			   }
			   point = right[m];
			   if(stay>=10)
			   {
				   stay=0;
				   start=0;
				   playMusic=false;
				   float length=(point.x-d.getX())*(point.x-d.getX())+(point.y-d.getY())*(point.y-d.getY());
				   length=(float) Math.pow(length, 0.5f);
				   duration = length/(300+score*2);
				   MoveToAction moveto = Actions.moveTo(point.x, point.y, length/(300+score*2));
				   
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
			  // start++;
			  num=m;
			   if(state==STATE.Left)
			   {
				   if(n==0)
				   {
					   if(ghostFirstGame.ifexist[m+3]==1)
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
					   if(ghostFirstGame.ifexist[m]==1)
					   {
						   int j=m;
						    while(j==m||m==0)
						   m=new Random().nextInt(3);
					   }
					   point=up[m];
					   dire=3;
				   }
				   if(n==2)
				   {
					   if(ghostFirstGame.ifexist[m+6]==1)
					   {
						   int j=m;
						    while(j==m||m==0)
						   m=new Random().nextInt(3);
					   }
					   point=down[m];
					   dire=1;
				   }
				 if(stay>=10)
				   {
					   stay=0;
					   start=0;
				 //  MoveToAction moveto1 = Actions.moveTo(d.getX(),d.getY() ,1.2f);
				  //d.addAction(moveto1);
//					   blockcolor.play=0;
					   playMusic=false;
					   float length=(point.x-d.getX())*(point.x-d.getX())+(point.y-d.getY())*(point.y-d.getY());
					   length=(float) Math.pow(length, 0.5f);
					   duration = length/(300+score*2);
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
					  // System.out.println("right");
					   if(n==0)
					   {
						   if(ghostFirstGame.ifexist[m+9]==1)
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
						   if(ghostFirstGame.ifexist[m]==1)
						   {
							   int j=m;
							    while(j==m||m==2)
							   m=new Random().nextInt(3);
						   }
						   point=up[m];
						   dire=3;
						  // System.out.println("right+up"+m);
					   }
					   if(n==2)
					   {
						   if(ghostFirstGame.ifexist[m+6]==1)
						   {
							   int j=m;
							    while(j==m||m==2)
							   m=new Random().nextInt(3);
						   }
						   point=down[m];
						   dire=1;
						  // System.out.println("right+down"+m);
					   }
					   if(stay>=10)
					   {
						   stay=0;
						   start=0;
//						   blockcolor.play=0;
						   playMusic=false;
						   float length=(point.x-d.getX())*(point.x-d.getX())+(point.y-d.getY())*(point.y-d.getY());
						   length=(float) Math.pow(length, 0.5f);
						   duration = length/(300+score*2);
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
							   if(ghostFirstGame.ifexist[m]==1)
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
							   if(ghostFirstGame.ifexist[m+9]==1)
							   {
								   int j=m;
								    while(j==m||m==0)
								   m=new Random().nextInt(3);
							   }
							   point=left[m];
							   dire=0;
						   }
						   if(n==2)
						   {
							   if(ghostFirstGame.ifexist[m+3]==1)
							   {
								   int j=m;
								    while(j==m||m==0)
								   m=new Random().nextInt(3);
							   }
							   point=right[m];
							  dire=2;
						   }
						   if(stay>=10)
						   {
							   stay=0;
							   start=0;
//							   blockcolor.play=0;
							   playMusic=false;
							   float length=(point.x-d.getX())*(point.x-d.getX())+(point.y-d.getY())*(point.y-d.getY());
							   length=(float) Math.pow(length, 0.5f);
							   duration = length/(300+score*2);
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
								   if(ghostFirstGame.ifexist[m+6]==1)
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
								   if(ghostFirstGame.ifexist[m+9]==1)
								   {
									   int j=m;
									    while(j==m||m==2)
									   m=new Random().nextInt(3);
								   }
								   point=left[m];
								   dire=0;
							   }
							   if(n==2)
							   {
								      
								   if(ghostFirstGame.ifexist[m+3]==1)
								   {
									   int j=m;
									    while(j==m||m==2)
									   m=new Random().nextInt(3);
								   }
									  
								   point=right[m];
								  dire=2;
							   }
							   if(stay>=10)
							   {
								   stay=0;
								   start=0;
//								   blockcolor.play=0;
								   playMusic=false;
								   float length=(point.x-d.getX())*(point.x-d.getX())+(point.y-d.getY())*(point.y-d.getY());
								   length=(float) Math.pow(length, 0.5f);
								   duration = length/(300+score*2);
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
   */
	 public void justflag()
	   {
		   if(d.getY()>=Uboder)
			{
			   if(ifcontinue == 1){
			   ifdraw=1;
				if(d.getX()<=Lboder+160*ghostMainActivity.w)
				{
					up1=1;
					
					if(ghostFirstGame.ifexist[0]==1&&playcount==0)
					{
						score++;
						playcount++;
					}
					if(ghostFirstGame.ifexist[0]==0)
						fail=1;
				}else if(d.getX()<=Lboder+320*ghostMainActivity.w)
				{
					up2=1;
					 
					if(ghostFirstGame.ifexist[1]==1&&playcount==0)
					{
						score++;
						
						playcount++;
					}
					if(ghostFirstGame.ifexist[1]==0)
						fail=1;
				}
				else
				{
					up3=1;
					 
					if(ghostFirstGame.ifexist[2]==1&&playcount==0)
					{
						score++;
						
						playcount++;
					}
					if(ghostFirstGame.ifexist[2]==0)
						fail=1;
				}
				ifcontinue = 0;
			} 
			}
		    if(d.getX()<=Lboder)
		    {
		    	if(ifcontinue == 1){
		    	ifdraw=1;
		    	if(d.getY()<=Dboder+160*ghostMainActivity.h)
				{
					left1=1;
					if(ghostFirstGame.ifexist[9]==1&&playcount==0)
					{
						score++;
						
						playcount++;
					}
					if(ghostFirstGame.ifexist[9]==0)
						fail=1;
					
				}else if(d.getY()<=Dboder+320*ghostMainActivity.h)
				{
					left2=1;
					if(ghostFirstGame.ifexist[10]==1&&playcount==0)
					{
						score++;
						
						playcount++;
					}
					if(ghostFirstGame.ifexist[10]==0)
						fail=1;
				}
				else
				{
					left3=1;
					if(ghostFirstGame.ifexist[11]==1&&playcount==0)
					{
						score++;
					
						playcount++;
					}
					if(ghostFirstGame.ifexist[11]==0)
						fail=1;
				}
		    	ifcontinue = 0;
		    	}
		    }
		    if(d.getX()>=Rboder)
		    {
		    	ifdraw=1;
		    	
		    	if(ifcontinue == 1){
		    	if(d.getY()<=Dboder+160*ghostMainActivity.h)
				{
					right1=1;
					if(ghostFirstGame.ifexist[5]==1&&playcount==0)
					{
						score++;
					
						playcount++;
					}
					if(ghostFirstGame.ifexist[5]==0)
						fail=1;
				}else if(d.getY()<=Dboder+320*ghostMainActivity.h)
				{
					right2=1;
					if(ghostFirstGame.ifexist[4]==1&&playcount==0)
					{
						score++;
						
						playcount++;
					}
					if(ghostFirstGame.ifexist[4]==0)
						fail=1;
				}
				else
				{
					right3=1;
					if(ghostFirstGame.ifexist[3]==1&&playcount==0)
					{
						score++;
						
						playcount++;
					}
					if(ghostFirstGame.ifexist[3]==0)
						fail=1;
				}
		    	ifcontinue = 0;
		    	}
		    }
		    
		    if(d.getY()<=Dboder)
		    {
		    	ifdraw=1;
		    	if(ifcontinue == 1){
		    	
		    	if(d.getX()<=Lboder+160*ghostMainActivity.w)
				{
					down1=1;
					if(ghostFirstGame.ifexist[8]==1&&playcount==0)
					{
						score++;
						playcount++;
					}
					if(ghostFirstGame.ifexist[8]==0)
						fail=1;
				}else if(d.getX()<=Lboder+320*ghostMainActivity.w)
				{
					down2=1;
					if(ghostFirstGame.ifexist[7]==1&&playcount==0)
					{
						score++;
						
						playcount++;
					}
					if(ghostFirstGame.ifexist[7]==0)
						fail=1;
				}
				else
				{
					down3=1;
					if(ghostFirstGame.ifexist[6]==1&&playcount==0)
					{
						score++;
						
						playcount++;
					}
					if(ghostFirstGame.ifexist[6]==0)
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
				   stay=0;
				   start=0;
				
				  
				   float length=(point.x-d.getX())*(point.x-d.getX())+(point.y-d.getY())*(point.y-d.getY());
				   length=(float) Math.pow(length, 0.5f);
				   duration = length/(300+score*2);
				   playMusic=false;
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
					   duration = length/(300+score*2);
					   playMusic=false;
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
						   duration = length/(300+score*2);
						   playMusic=false;
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
							   duration = length/(300+score*2);
							   playMusic=false;
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
   public void getmask()
   {
	   float x=d.getX();
	   float y=d.getY();
	   mask.setPosition((x-1000)*ghostMainActivity.w, (y-700)*ghostMainActivity.h);
	   /*
	   if(x-500>0)
	   {
		   mask_left.setSize(x-500, 720);
		   mask_left.setPosition(0, 0);
	   }
	   else
	   {
		   mask_left.setSize(0, 0);
	   }
	   if(y-300>0)
	   {
		   mask_down.setSize(1280,y-300);
		   mask_down.setPosition(0, 0);
	   }
	   else
	   {
		   mask_down.setSize(0, 0);
	   }
	   if(x+500<1280)
	   {
		   mask_right.setSize(780-x,720);
		   mask_right.setPosition(x+500, 0);
	   }
	   else
	   {
		   mask_right.setSize(0, 0);
	   }
	   if(y+300<720)
	   {
		   mask_up.setSize(1280, 420-y);
		   mask_up.setPosition(0, y+300);
	   }
	   else
	   {
		    mask_up.setSize(0, 0);
	   }
		  */
   }
	@Override
	public void draw(SpriteBatch batch, float parentAlpha) {
		// TODO Auto-generated method stub
		 if(ghostFirstGame.playMusic==-1)
		 {
			 getmask();
			    Ballforghost.down1.setVisible(false);
				Ballforghost.down2.setVisible(false);
				Ballforghost.down3.setVisible(false);
				Ballforghost.up1.setVisible(false);
				Ballforghost.up2.setVisible(false);
				Ballforghost.up3.setVisible(false);
				Ballforghost.left1.setVisible(false);
				Ballforghost.left2.setVisible(false);
				Ballforghost.left3.setVisible(false);
				Ballforghost.right1.setVisible(false);
				Ballforghost.right2.setVisible(false);
				Ballforghost.right3.setVisible(false);
				
				if(point == right[0]||point == right[1]||point == right[2]){
					if(d.getX() >= (Lboder + Rboder+64)/2){
						if(playMusic==false)
						{
							
							ghost.play();
							System.out.println("播放声音");
							playMusic=true;
						}
						
						Ballforghost.right1.setVisible(true);
						Ballforghost.right2.setVisible(true);
						Ballforghost.right3.setVisible(true);
						Ballforghost.right1.setSize(1,1);
						   Ballforghost.right2.setSize(1,1);
						   sta = Actions.sizeTo(64*ghostMainActivity.w, 160*ghostMainActivity.h,0.1f);
						   sta1 = Actions.sizeTo(64*ghostMainActivity.w, 160*ghostMainActivity.h,0.1f);
						   Ballforghost.right1.addAction(sta);
						   Ballforghost.right2.addAction(sta1);
					}
				}
				else if(point == left[0]||point == left[1]||point == left[2]){
					if(d.getX() <= (Lboder + Rboder+64)/2){
						if(playMusic==false)
						{
							ghost.play();
							System.out.println("播放声音");
							playMusic=true;
						}
						Ballforghost.left1.setVisible(true);
						Ballforghost.left2.setVisible(true);
						Ballforghost.left3.setVisible(true);
						Ballforghost.left2.setSize(1,1);
						   Ballforghost.left1.setSize(1,1);
						   sta = Actions.sizeTo(64*ghostMainActivity.w, 160*ghostMainActivity.h,0.1f);
						   sta1 = Actions.sizeTo(64*ghostMainActivity.w, 160*ghostMainActivity.h,0.1f);
						   Ballforghost.left1.addAction(sta);
						   Ballforghost.left2.addAction(sta1);
					}
				}
				else if(point == up[0]||point == up[1]||point == up[2]){
					if(d.getY() >= (Uboder + Dboder+64)/2){
						if(playMusic==false)
						{
							ghost.play();
							System.out.println("播放声音");
							playMusic=true;
						}
						Ballforghost.up1.setVisible(true);
						Ballforghost.up2.setVisible(true);
						Ballforghost.up3.setVisible(true);
						Ballforghost.up1.setSize(1,1);
						   Ballforghost.up2.setSize(1,1);
						   sta = Actions.sizeTo(160*ghostMainActivity.w, 64*ghostMainActivity.h, 0.1f);
						   sta1 = Actions.sizeTo(160*ghostMainActivity.w, 64*ghostMainActivity.h, 0.1f);
						   Ballforghost.up1.addAction(sta);
						   Ballforghost.up2.addAction(sta1);
					}
				}
				else if(point == down[0]||point == down[1]||point == down[2]){
					if(d.getY() <= (Uboder + Dboder+64)/2){
						if(playMusic==false)
						{
							ghost.play();
							System.out.println("播放声音");
							playMusic=true;
						}
						Ballforghost.down1.setVisible(true);
						Ballforghost.down2.setVisible(true);
						Ballforghost.down3.setVisible(true);
						Ballforghost.down1.setSize(1,1);
						Ballforghost.down2.setSize(1,1);
						   sta = Actions.sizeTo(160*ghostMainActivity.w, 64*ghostMainActivity.h,0.1f);
						   sta1 = Actions.sizeTo(160*ghostMainActivity.w, 64*ghostMainActivity.h,0.1f);
						   Ballforghost.down1.addAction(sta);
						   Ballforghost.down2.addAction(sta1);
					}
				}
			
	       drawLine();
	     r++;
	     if(r==40)
	     {
	    	 r=0;
	    	 playcount=0;
	     }
		 }
			 
		// }
		 
	      //System.out.println("坐标"+d.getX()+" "+d.getY());
	}

}
