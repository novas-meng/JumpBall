package com.example.jumpball;

import java.util.Random;

import android.graphics.PointF;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class Jumpforroll extends Actor
{

	Image ball;
	static int score;
	static int sumscore=0;
	static boolean ignore=false;
	static int rollfail=-1;
	PointF[] point=new PointF[6];
	int ball_goto;
	int fail[]=new int[12];
	Random ran=new Random();
	static int degree[]=new int[6];
	Sound hit,fails;
	public Jumpforroll()
	{
		init();
	}
	public void init()
	{
		for(int i=0;i<12;i++)
		{
			fail[i]=0;
		}
		degree[0]=180;
		degree[1]=240;
		degree[2]=300;
		degree[3]=0;
		degree[4]=60;
		degree[5]=120;
		point[0]=new PointF(620*rollMainActivity.w,596*rollMainActivity.h);
		point[1]=new PointF(840*rollMainActivity.w,480*rollMainActivity.h);
		point[2]=new PointF(840*rollMainActivity.w,230*rollMainActivity.h);
		point[3]=new PointF(615*rollMainActivity.w,74*rollMainActivity.h);
		point[4]=new PointF(370*rollMainActivity.w,230*rollMainActivity.h);
		point[5]=new PointF(390*rollMainActivity.w,480*rollMainActivity.h);
		ball_goto=ran.nextInt(6);
		Texture.setEnforcePotImages(false);
	    hit=Gdx.audio.newSound(Gdx.files.internal("music/roll_hit.wav"));
	    fails=Gdx.audio.newSound(Gdx.files.internal("music/fail.wav"));
		ball=new Image(new Texture(Gdx.files.internal("roll_ball.png")));
		ball.setPosition(rollMainActivity.screenwidth/2, rollMainActivity.screenheight/2);
		MoveToAction action=Actions.moveTo(point[ball_goto].x, point[ball_goto].y,4f);
		ball.addAction(action);
	}
	public void move()
	{ 
		if(ball.getX()==point[ball_goto].x)
		{
			float m=FirstGameForRoll.degree-(int)(FirstGameForRoll.degree/360)*360;
			if(m<0)
			{
				m=m+360;
			}
			System.out.println("m="+m+"degree[ball_goto]"+degree[ball_goto]);
			if((degree[ball_goto]>=m&&degree[ball_goto]<=m+90)||(degree[ball_goto]+360>=m&&degree[ball_goto]+360<=m+90))
			{
				hit.play();
				score=3;
				sumscore=sumscore+score;
				System.out.println("3分");
			}
			if((degree[ball_goto]>=m+90&&degree[ball_goto]<=m+150)||(degree[ball_goto]+360>=m+90&&degree[ball_goto]+360<=m+150))
			{
				//score=4;
				//sumscore=sumscore+score;
				//System.out.println("4分");
				fails.play();
				rollfail=1;
			}
			if((degree[ball_goto]>=m+150&&degree[ball_goto]<=m+270)||(degree[ball_goto]+360>=m+150&&degree[ball_goto]+360<=m+270))
			{
				hit.play();
				score=2;
				sumscore=sumscore+score;
				System.out.println("2分");
			}
			if((degree[ball_goto]>=m+270&&degree[ball_goto]<=m+330)||(degree[ball_goto]+360>=m+270&&degree[ball_goto]+360<=m+330))
			{
				hit.play();
				score=4;
				sumscore=sumscore+score;
				System.out.println("4分");
				
			}
			if((degree[ball_goto]>=m+330&&degree[ball_goto]<=m+360)||(degree[ball_goto]+360>=m+330&&degree[ball_goto]+360<=m+360))
			{
				hit.play();
				score=5;
				sumscore=sumscore+score;
				System.out.println("5分");
			}
			if(FirstGameForRoll.task==2)
			{
				if(FirstGameForRoll.ignore==false)
				{
					if(score!=2)
				{
					if(fail[2]==0)
					FirstGameForRoll.fail++;
					else
						fail[2]=1;
				}
				}
				
			}
			if(FirstGameForRoll.task==3)
			{
				if(FirstGameForRoll.ignore==false)
				{
					if(score!=3)
				{
					if(fail[3]==0)
						FirstGameForRoll.fail++;
						else
							fail[3]=1;
				}
				}
				
			}
			if(FirstGameForRoll.task==4)
			{
				if(FirstGameForRoll.ignore==false)
				{
					if(score!=4)
				{
					if(fail[4]==0)
						FirstGameForRoll.fail++;
						else
							fail[4]=1;
				}
					
				}
				
			}
			if(FirstGameForRoll.task==5)
			{
				if(FirstGameForRoll.ignore==false)
				{
					if(score!=5)
				{
					if(fail[5]==0)
						FirstGameForRoll.fail++;
						else
							fail[5]=1;
				}
				}
				
			}
			if(FirstGameForRoll.task==6)
			{
				if(FirstGameForRoll.ignore==false)
				{
					if(score!=3)
				{
					if(fail[6]==0)
						FirstGameForRoll.fail++;
						else
							fail[6]=1;
				}
				}
				
			}
			if(FirstGameForRoll.task==7)
			{
				if(FirstGameForRoll.ignore==false)
				{
					if(score!=4)
				{
					if(fail[7]==0)
						FirstGameForRoll.fail++;
						else
							fail[7]=1;
				}
				}
				
			}
			if(FirstGameForRoll.task==8)
			{
				if(FirstGameForRoll.ignore==false)
				{
					if(score!=5)
				{
					if(fail[8]==0)
						FirstGameForRoll.fail++;
						else
							fail[8]=1;
				}
				}
				
			}
			if(FirstGameForRoll.task==9)
			{
				if(FirstGameForRoll.ignore==false)
				{
					if(score!=3)
				{
					if(fail[9]==0)
						FirstGameForRoll.fail++;
						else
							fail[9]=1;
				}
				}
				
			}
			if(FirstGameForRoll.task==10)
			{
				if(FirstGameForRoll.ignore==false)
				{
					if(score!=4)
				{
					if(fail[10]==0)
						FirstGameForRoll.fail++;
						else
							fail[10]=1;
				}
				}
				
			}
			if(FirstGameForRoll.task==11)
			{
				if(FirstGameForRoll.ignore==false)
				{
					if(score!=5)
				{
					if(fail[11]==0)
						FirstGameForRoll.fail++;
						else
							fail[11]=1;
				}
				}
				
			}
			int n=ran.nextInt(6);
			int sum=(int) ((point[n].x-point[ball_goto].x)*(point[n].x-point[ball_goto].x)+(point[n].y-point[ball_goto].y)*(point[n].y-point[ball_goto].y));
			while(sum<90000)
			{
				 n=ran.nextInt(6);
			      sum=(int) ((point[n].x-point[ball_goto].x)*(point[n].x-point[ball_goto].x)+(point[n].y-point[ball_goto].y)*(point[n].y-point[ball_goto].y));
			}
			ball_goto=n;
			FirstGameForRoll.ignore=false;
			MoveToAction action=Actions.moveTo(point[ball_goto].x, point[ball_goto].y,2f);
			ball.addAction(action);
		}
		
	}
	@Override
	public void draw(SpriteBatch batch, float parentAlpha) {
		// TODO Auto-generated method stub
		if(FirstGameForRoll.playMusic==-1&&FirstGameForRoll.stageOver==true)
		{
			move();
		}
		
	}
     
}
