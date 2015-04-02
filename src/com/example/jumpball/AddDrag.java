package com.example.jumpball;

import java.util.ArrayList;
import java.util.Collections;

import android.graphics.Point;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.utils.DragListener;

public class AddDrag {
	
	static Point[] point=new Point[12];
	public static void initPoint()
	{
		//Point[] point=new Point[9];
		point[0]=new Point();
		point[1]=new Point();
		point[2]=new Point();
		point[3]=new Point();
		point[4]=new Point();
		point[5]=new Point();
		point[6]=new Point();
		point[7]=new Point();
		point[8]=new Point();
		point[9]=new Point();
		point[10]=new Point();
		point[11]=new Point();
		point[0].x=(int) Ball.LBorder;
		point[0].y=(int) (Ball.UBorder+64*MainActivity.h);
		point[1].x=(int) (Ball.LBorder+160*MainActivity.w);
		point[1].y=(int) (Ball.UBorder+64*MainActivity.h);
		point[2].x=(int) (Ball.LBorder+320*MainActivity.w);
		point[2].y=(int) (Ball.UBorder+64*MainActivity.h);
		
		
		point[5].x=(int) (Ball.RBorder+64*MainActivity.w);
		point[5].y=(int)(Ball.DBorder);
		point[4].x=(int) (Ball.RBorder+64*MainActivity.w);
		point[4].y=(int)(Ball.DBorder+160*MainActivity.h);
		point[3].x=(int) (Ball.RBorder+64*MainActivity.w);
		point[3].y=(int)(Ball. DBorder+160*2*MainActivity.h);
		
		point[8].x=(int) Ball.LBorder;
		point[8].y=(int) (Ball.DBorder-64*MainActivity.h);
		point[7].x=(int)(Ball.LBorder+160*MainActivity.w);
		point[7].y=(int) (Ball.DBorder-64*MainActivity.h);
		point[6].x=(int)(Ball.LBorder+160*2*MainActivity.w);
		point[6].y=(int) (Ball.DBorder-64*MainActivity.h);
		
		
		point[9].x=(int)(Ball.LBorder-64*MainActivity.w);
		point[9].y= (int) Ball.DBorder;
		point[10].x=(int)(Ball.LBorder-64*MainActivity.w);
		point[10].y= (int) (Ball.DBorder+160*MainActivity.h);
		point[11].x=(int)(Ball.LBorder-64*MainActivity.w);
		point[11].y= (int)(Ball.DBorder+160*2*MainActivity.h);
		
	}
	public static int  min(float x,float y)
	{
		Point[] point=new Point[12];
		point[0]=new Point();
		point[1]=new Point();
		point[2]=new Point();
		point[3]=new Point();
		point[4]=new Point();
		point[5]=new Point();
		point[6]=new Point();
		point[7]=new Point();
		point[8]=new Point();
		point[9]=new Point();
		point[10]=new Point();
		point[11]=new Point();
		point[0].x=(int) Ball.LBorder;
		point[0].y=(int) (Ball.UBorder+64*MainActivity.h);
		point[1].x=(int) (Ball.LBorder+160*MainActivity.w);
		point[1].y=(int) (Ball.UBorder+64*MainActivity.h);
		point[2].x=(int) (Ball.LBorder+320*MainActivity.w);
		point[2].y=(int) (Ball.UBorder+64*MainActivity.h);
		
		
		point[5].x=(int) (Ball.RBorder+64*MainActivity.w);
		point[5].y=(int)(Ball.DBorder);
		point[4].x=(int) (Ball.RBorder+64*MainActivity.w);
		point[4].y=(int)(Ball.DBorder+160*MainActivity.h);
		point[3].x=(int) (Ball.RBorder+64*MainActivity.w);
		point[3].y=(int)(Ball. DBorder+160*2*MainActivity.h);
		
		point[8].x=(int) Ball.LBorder;
		point[8].y=(int) (Ball.DBorder-64*MainActivity.h);
		point[7].x=(int)(Ball.LBorder+160*MainActivity.w);
		point[7].y=(int) (Ball.DBorder-64*MainActivity.h);
		point[6].x=(int)(Ball.LBorder+160*2*MainActivity.w);
		point[6].y=(int) (Ball.DBorder-64*MainActivity.h);
		
		
		point[9].x=(int)(Ball.LBorder-64*MainActivity.w);
		point[9].y= (int) Ball.DBorder;
		point[10].x=(int)(Ball.LBorder-64*MainActivity.w);
		point[10].y= (int) (Ball.DBorder+160*MainActivity.h);
		point[11].x=(int)(Ball.LBorder-64*MainActivity.w);
		point[11].y= (int)(Ball.DBorder+160*2*MainActivity.h);
		ArrayList<Float> l1=new ArrayList<Float>();
		ArrayList<Float> l2=new ArrayList<Float>();
		for(int i=0;i<=11;i++)
		{
			float path=(x-point[i].x)*(x-point[i].x)+(y-point[i].y)*(y-point[i].y);
			System.out.println("path="+path);
			l1.add(path);
			l2.add(path);
		}
		Collections.sort(l1);
		Float m=l1.get(0);
		return l2.indexOf(m);
	}
	public static int find(Actor[] a,Actor actor)
	{
		for(int i=0;i<a.length;i++)
		{
			if(a[i]==actor)
			{
				return i;
			}
		}
		return -1;
	}
	public static int ifRoll(int m,int n)
	{
		ArrayList<Integer> list1=new ArrayList<Integer>();
		ArrayList<Integer> list2=new ArrayList<Integer>();
		list1.add(3);
		list1.add(4);
		list1.add(5);
		list1.add(9);
		list1.add(10);
		list1.add(11);
		list2.add(0);
		list2.add(1);
		list2.add(2);
		list2.add(6);
		list2.add(7);
		list2.add(8);
		if((list2.contains(m)&&list2.contains(n))||(list1.contains(m)&&list1.contains(n)))
			return 0;
		else
			return 1;
	}
    public static void add()
    {
    	final ArrayList<Integer> list1=new ArrayList<Integer>();
    	final ArrayList<Integer> list2=new ArrayList<Integer>();
    	final ArrayList<Integer> list3=new ArrayList<Integer>();
    	final ArrayList<Integer> list4=new ArrayList<Integer>();
    	for(int i=0;i<3;i++)
    	{
    		list1.add(i);
    		list2.add(3+i);
    		list3.add(6+i);
    		list4.add(9+i);
    	}
    	FirstGame.ball.right1.addListener(new DragListener()
	       {

	    	   float m_x,m_y;
	    	     int m=0;
			@Override
			public void dragStart(InputEvent event, float x, float y,
					int pointer) {
				// TODO Auto-generated method stub
				m_x=FirstGame.ball.right1.getX();
				m_y=FirstGame.ball.right1.getY();
				
				//System.out.println("开始拖动时x="+x+"y="+y);
				//System.out.println("m_x="+m_x+"m_y="+m_y);
			}
            
			@Override
			public void dragStop(InputEvent event, float x, float y, int pointer) {
				// TODO Auto-generated method stub
				
				float current_x=FirstGame.ball.right1.getX();
				float current_y=FirstGame.ball.right1.getY();
				System.out.println("current_x"+current_x+"current_y="+current_y);
				int m=min(current_x,current_y);
				System.out.println("right1停止拖拽"+"m="+m+"FirstGame.ifexist[m]="+FirstGame.ifexist[m]);
				if(FirstGame.ifexist[m]==0)
				{
					
					int n=find(FirstGame.actorGroup,FirstGame.ball.right1);
					if((list1.contains(m)&&list1.contains(n))||(list2.contains(m)&&list2.contains(n))||(list3.contains(m)&&list3.contains(n))||(list4.contains(m)&&list4.contains(n)))
					{
						System.out.println("属于同一行");
						Actor temp=new Actor();
						temp=FirstGame.actorGroup[m];
						FirstGame.actorGroup[m]=FirstGame.actorGroup[n];
						FirstGame.actorGroup[n]=temp;
						FirstGame.actorGroup[m].setVisible(true);
						FirstGame.actorGroup[m].setPosition(point[m].x,point[m].y);
						FirstGame.actorGroup[n].setVisible(false);
					}
					else
					{
						FirstGame.actorGroup[m].setVisible(true);
						FirstGame.actorGroup[m].setPosition(point[m].x,point[m].y);
						FirstGame.ball.right1.setVisible(false);
						FirstGame.ball.right1.setPosition(point[n].x,point[n].y);
					}
					//if(ifRoll(m,n)==1)
					//{
						//System.out.println("应该进行旋转");
						//FirstGame.ball.right1.setOrigin(0,0);
						//FirstGame.ball.right1.addAction(Actions.rotateBy(-90, 0.5f));
						//FirstGame.ball.right1.setRotation(30);
						
					//}
					
					FirstGame.ifexist[m]=1;
					
					System.out.println("n="+n);
				    FirstGame.ifexist[n]=0;
					//FirstGame.actorGroup[n]=null;
					//FirstGame.actorGroup[m]=FirstGame.ball.right1;
					
					
				}
				else
				{
					FirstGame.ball.right1.setPosition(m_x, m_y);
					
				}
			}

			@Override
			public void drag(InputEvent event, float x, float y, int pointer) {
				// TODO Auto-generated method stub
				//super.drag(event, x, y, pointer);
				System.out.println("m="+m+"x="+x+"y="+y+"pointer="+pointer);
				
					//System.out.println("在拖动函数中，x="+x+"y="+y);
					float x0=FirstGame.ball.right1.getX();
					float y0=FirstGame.ball.right1.getY();
					 System.out.println("x0="+x0+"y0="+y0);
					  if (x != m_x)
		                {
		                    x0+= x;
		                }
		                if (y != m_y)
		                {
		                    y0+= y;
		                }
		                System.out.println("x0="+x0+"y0="+y0);
		              //  FirstGame.ifexist[5]=0;
		                FirstGame.ball.right1.setPosition(x0, y0);
			}   
	       });
    	
    	FirstGame.ball.right2.addListener(new DragListener()
	       {

	    	   float m_x,m_y;
	    	     int m=0;
			@Override
			public void dragStart(InputEvent event, float x, float y,
					int pointer) {
				// TODO Auto-generated method stub
				m_x=FirstGame.ball.right2.getX();
				m_y=FirstGame.ball.right2.getY();
				
				//System.out.println("开始拖动时x="+x+"y="+y);
				//System.out.println("m_x="+m_x+"m_y="+m_y);
			}
         
			@Override
			public void dragStop(InputEvent event, float x, float y, int pointer) {
				// TODO Auto-generated method stub
				
				float current_x=FirstGame.ball.right2.getX();
				float current_y=FirstGame.ball.right2.getY();
				System.out.println("current_x"+current_x+"current_y="+current_y);
				int m=min(current_x,current_y);
				System.out.println("right2停止拖拽"+"m="+m+"FirstGame.ifexist[m]="+FirstGame.ifexist[m]);
				if(FirstGame.ifexist[m]==0)
				{
					
					int n=find(FirstGame.actorGroup,FirstGame.ball.right2);
					if((list1.contains(m)&&list1.contains(n))||(list2.contains(m)&&list2.contains(n))||(list3.contains(m)&&list3.contains(n))||(list4.contains(m)&&list4.contains(n)))
					{
						System.out.println("属于同一行");
						Actor temp=new Actor();
						temp=FirstGame.actorGroup[m];
						FirstGame.actorGroup[m]=FirstGame.actorGroup[n];
						FirstGame.actorGroup[n]=temp;
						FirstGame.actorGroup[m].setVisible(true);
						FirstGame.actorGroup[m].setPosition(point[m].x,point[m].y);
						FirstGame.actorGroup[n].setVisible(false);
					}
					else
					{
						FirstGame.actorGroup[m].setVisible(true);
						FirstGame.actorGroup[m].setPosition(point[m].x,point[m].y);
						FirstGame.ball.right2.setVisible(false);
						FirstGame.ball.right2.setPosition(point[n].x,point[n].y);
					}
					//if(ifRoll(m,n)==1)
					//{
						//System.out.println("应该进行旋转");
						//FirstGame.ball.right1.setOrigin(0,0);
						//FirstGame.ball.right1.addAction(Actions.rotateBy(-90, 0.5f));
						//FirstGame.ball.right1.setRotation(30);
						//FirstGame.actorGroup[m].setVisible(true);
						//FirstGame.ball.right2.setVisible(false);
						//FirstGame.ball.right2.setPosition(point[n].x,point[n].y);
					//}
					
					FirstGame.ifexist[m]=1;
					
					System.out.println("n="+n);
				    FirstGame.ifexist[n]=0;
					//FirstGame.actorGroup[n]=null;
					//FirstGame.actorGroup[m]=FirstGame.ball.right2;
					
					
				}
				else
				{
					FirstGame.ball.right2.setPosition(m_x, m_y);
					
				}
			}

			@Override
			public void drag(InputEvent event, float x, float y, int pointer) {
				// TODO Auto-generated method stub
				//super.drag(event, x, y, pointer);
				System.out.println("m="+m+"x="+x+"y="+y+"pointer="+pointer);
				
					//System.out.println("在拖动函数中，x="+x+"y="+y);
					float x0=FirstGame.ball.right2.getX();
					float y0=FirstGame.ball.right2.getY();
					 System.out.println("x0="+x0+"y0="+y0);
					  if (x != m_x)
		                {
		                    x0+= x;
		                }
		                if (y != m_y)
		                {
		                    y0+= y;
		                }
		                System.out.println("x0="+x0+"y0="+y0);
		              //  FirstGame.ifexist[5]=0;
		                FirstGame.ball.right2.setPosition(x0, y0);
			}   
	       });
    	
    	
    	
    	
    	
    	
    	FirstGame.ball.right3.addListener(new DragListener()
	       {

	    	   float m_x,m_y;
	    	     int m=0;
			@Override
			public void dragStart(InputEvent event, float x, float y,
					int pointer) {
				// TODO Auto-generated method stub
				m_x=FirstGame.ball.right3.getX();
				m_y=FirstGame.ball.right3.getY();
				
				//System.out.println("开始拖动时x="+x+"y="+y);
				//System.out.println("m_x="+m_x+"m_y="+m_y);
			}
         
			@Override
			public void dragStop(InputEvent event, float x, float y, int pointer) {
				// TODO Auto-generated method stub
				
				float current_x=FirstGame.ball.right3.getX();
				float current_y=FirstGame.ball.right3.getY();
				System.out.println("current_x"+current_x+"current_y="+current_y);
				int m=min(current_x,current_y);
				System.out.println("right3停止拖拽"+"m="+m+"FirstGame.ifexist[m]="+FirstGame.ifexist[m]);
				if(FirstGame.ifexist[m]==0)
				{
					
					int n=find(FirstGame.actorGroup,FirstGame.ball.right3);
					if((list1.contains(m)&&list1.contains(n))||(list2.contains(m)&&list2.contains(n))||(list3.contains(m)&&list3.contains(n))||(list4.contains(m)&&list4.contains(n)))
					{
						System.out.println("属于同一行");
						Actor temp=new Actor();
						temp=FirstGame.actorGroup[m];
						FirstGame.actorGroup[m]=FirstGame.actorGroup[n];
						FirstGame.actorGroup[n]=temp;
						FirstGame.actorGroup[m].setVisible(true);
						FirstGame.actorGroup[m].setPosition(point[m].x,point[m].y);
						FirstGame.actorGroup[n].setVisible(false);
					}
					else
					{
						FirstGame.actorGroup[m].setVisible(true);
						FirstGame.actorGroup[m].setPosition(point[m].x,point[m].y);
						FirstGame.ball.right3.setVisible(false);
						FirstGame.ball.right3.setPosition(point[n].x,point[n].y);
					}
					//if(ifRoll(m,n)==1)
					//{
						//System.out.println("应该进行旋转");
						//FirstGame.ball.right1.setOrigin(0,0);
						//FirstGame.ball.right1.addAction(Actions.rotateBy(-90, 0.5f));
						//FirstGame.ball.right1.setRotation(30);
						//FirstGame.actorGroup[m].setVisible(true);
						//FirstGame.ball.right3.setVisible(false);
						//FirstGame.ball.right3.setPosition(point[n].x,point[n].y);
					//}
					
					FirstGame.ifexist[m]=1;
					
					System.out.println("n="+n);
				    FirstGame.ifexist[n]=0;
				//	FirstGame.actorGroup[n]=null;
				//	FirstGame.actorGroup[m]=FirstGame.ball.right3;
					
					
				}
				else
				{
					FirstGame.ball.right3.setPosition(m_x, m_y);
					
				}
			}

			@Override
			public void drag(InputEvent event, float x, float y, int pointer) {
				// TODO Auto-generated method stub
				//super.drag(event, x, y, pointer);
				System.out.println("m="+m+"x="+x+"y="+y+"pointer="+pointer);
				
					//System.out.println("在拖动函数中，x="+x+"y="+y);
					float x0=FirstGame.ball.right3.getX();
					float y0=FirstGame.ball.right3.getY();
					 System.out.println("x0="+x0+"y0="+y0);
					  if (x != m_x)
		                {
		                    x0+= x;
		                }
		                if (y != m_y)
		                {
		                    y0+= y;
		                }
		                System.out.println("x0="+x0+"y0="+y0);
		              //  FirstGame.ifexist[5]=0;
		                FirstGame.ball.right3.setPosition(x0, y0);
			}   
	       });
    	
    	
    	
    	
    	
    	FirstGame.ball.down1.addListener(new DragListener()
	       {

	    	   float m_x,m_y;
	    	     int m=0;
			@Override
			public void dragStart(InputEvent event, float x, float y,
					int pointer) {
				// TODO Auto-generated method stub
				m_x=FirstGame.ball.down1.getX();
				m_y=FirstGame.ball.down1.getY();
				
				//System.out.println("开始拖动时x="+x+"y="+y);
				//System.out.println("m_x="+m_x+"m_y="+m_y);
			}
         
			@Override
			public void dragStop(InputEvent event, float x, float y, int pointer) {
				// TODO Auto-generated method stub
				
				float current_x=FirstGame.ball.down1.getX();
				float current_y=FirstGame.ball.down1.getY();
				System.out.println("current_x"+current_x+"current_y="+current_y);
				int m=min(current_x,current_y);
				System.out.println("down1停止拖拽"+"m="+m+"FirstGame.ifexist[m]="+FirstGame.ifexist[m]);
				if(FirstGame.ifexist[m]==0)
				{
					
					int n=find(FirstGame.actorGroup,FirstGame.ball.down1);
					if((list1.contains(m)&&list1.contains(n))||(list2.contains(m)&&list2.contains(n))||(list3.contains(m)&&list3.contains(n))||(list4.contains(m)&&list4.contains(n)))
					{
						System.out.println("属于同一行");
						Actor temp=new Actor();
						temp=FirstGame.actorGroup[m];
						FirstGame.actorGroup[m]=FirstGame.actorGroup[n];
						FirstGame.actorGroup[n]=temp;
						FirstGame.actorGroup[m].setVisible(true);
						FirstGame.actorGroup[m].setPosition(point[m].x,point[m].y);
						FirstGame.actorGroup[n].setVisible(false);
					}
					else
					{
						FirstGame.actorGroup[m].setVisible(true);
						FirstGame.actorGroup[m].setPosition(point[m].x,point[m].y);
						FirstGame.ball.down1.setVisible(false);
						FirstGame.ball.down1.setPosition(point[n].x,point[n].y);
					}
					//if(ifRoll(m,n)==1)
					//{
						//System.out.println("应该进行旋转");
						//FirstGame.ball.right1.setOrigin(0,0);
						//FirstGame.ball.right1.addAction(Actions.rotateBy(-90, 0.5f));
						//FirstGame.ball.right1.setRotation(30);
						//FirstGame.actorGroup[m].setVisible(true);
						//FirstGame.ball.down1.setVisible(false);
						//FirstGame.ball.down1.setPosition(point[n].x,point[n].y);
					//}
					
					FirstGame.ifexist[m]=1;
					
					System.out.println("n="+n);
				    FirstGame.ifexist[n]=0;
				//	FirstGame.actorGroup[n]=null;
				//	FirstGame.actorGroup[m]=FirstGame.ball.down1;
					
					
				}
				else
				{
					FirstGame.ball.down1.setPosition(m_x, m_y);
					
				}
			}

			@Override
			public void drag(InputEvent event, float x, float y, int pointer) {
				// TODO Auto-generated method stub
				//super.drag(event, x, y, pointer);
				System.out.println("m="+m+"x="+x+"y="+y+"pointer="+pointer);
				
					//System.out.println("在拖动函数中，x="+x+"y="+y);
					float x0=FirstGame.ball.down1.getX();
					float y0=FirstGame.ball.down1.getY();
					 System.out.println("x0="+x0+"y0="+y0);
					  if (x != m_x)
		                {
		                    x0+= x;
		                }
		                if (y != m_y)
		                {
		                    y0+= y;
		                }
		                System.out.println("x0="+x0+"y0="+y0);
		              //  FirstGame.ifexist[5]=0;
		                FirstGame.ball.down1.setPosition(x0, y0);
			}   
	       });
    	
    	
    	
    	FirstGame.ball.down2.addListener(new DragListener()
	       {

	    	   float m_x,m_y;
	    	     int m=0;
			@Override
			public void dragStart(InputEvent event, float x, float y,
					int pointer) {
				// TODO Auto-generated method stub
				m_x=FirstGame.ball.down2.getX();
				m_y=FirstGame.ball.down2.getY();
				
				//System.out.println("开始拖动时x="+x+"y="+y);
				//System.out.println("m_x="+m_x+"m_y="+m_y);
			}
      
			@Override
			public void dragStop(InputEvent event, float x, float y, int pointer) {
				// TODO Auto-generated method stub
				
				float current_x=FirstGame.ball.down2.getX();
				float current_y=FirstGame.ball.down2.getY();
				System.out.println("current_x"+current_x+"current_y="+current_y);
				int m=min(current_x,current_y);
				System.out.println("down2停止拖拽"+"m="+m);
				if(FirstGame.ifexist[m]==0)
				{
					
					int n=find(FirstGame.actorGroup,FirstGame.ball.down2);
					if((list1.contains(m)&&list1.contains(n))||(list2.contains(m)&&list2.contains(n))||(list3.contains(m)&&list3.contains(n))||(list4.contains(m)&&list4.contains(n)))
					{
						System.out.println("属于同一行");
						Actor temp=new Actor();
						temp=FirstGame.actorGroup[m];
						FirstGame.actorGroup[m]=FirstGame.actorGroup[n];
						FirstGame.actorGroup[n]=temp;
						FirstGame.actorGroup[m].setVisible(true);
						FirstGame.actorGroup[m].setPosition(point[m].x,point[m].y);
						FirstGame.actorGroup[n].setVisible(false);
					}
					else
					{
						FirstGame.actorGroup[m].setVisible(true);
						FirstGame.actorGroup[m].setPosition(point[m].x,point[m].y);
						FirstGame.ball.down2.setVisible(false);
						FirstGame.ball.down2.setPosition(point[n].x,point[n].y);
					}
					//if(ifRoll(m,n)==1)
					//{
						//System.out.println("应该进行旋转");
						//FirstGame.ball.right1.setOrigin(0,0);
						//FirstGame.ball.right1.addAction(Actions.rotateBy(-90, 0.5f));
						//FirstGame.ball.right1.setRotation(30);
						//FirstGame.actorGroup[m].setVisible(true);
						//FirstGame.ball.down2.setVisible(false);
						//FirstGame.ball.down2.setPosition(point[n].x,point[n].y);
					//}
					
					FirstGame.ifexist[m]=1;
					
					System.out.println("n="+n);
				    FirstGame.ifexist[n]=0;
					//FirstGame.actorGroup[n]=null;
					//FirstGame.actorGroup[m]=FirstGame.ball.down2;
					
					
				}
				else
				{
					FirstGame.ball.down2.setPosition(m_x, m_y);
					
				}
			}

			@Override
			public void drag(InputEvent event, float x, float y, int pointer) {
				// TODO Auto-generated method stub
				//super.drag(event, x, y, pointer);
				System.out.println("m="+m+"x="+x+"y="+y+"pointer="+pointer);
				
					//System.out.println("在拖动函数中，x="+x+"y="+y);
					float x0=FirstGame.ball.down2.getX();
					float y0=FirstGame.ball.down2.getY();
					 System.out.println("x0="+x0+"y0="+y0);
					  if (x != m_x)
		                {
		                    x0+= x;
		                }
		                if (y != m_y)
		                {
		                    y0+= y;
		                }
		                System.out.println("x0="+x0+"y0="+y0);
		              //  FirstGame.ifexist[5]=0;
		                FirstGame.ball.down2.setPosition(x0, y0);
			}   
	       });
    	
    	
    	FirstGame.ball.down3.addListener(new DragListener()
	       {

	    	   float m_x,m_y;
	    	     int m=0;
			@Override
			public void dragStart(InputEvent event, float x, float y,
					int pointer) {
				// TODO Auto-generated method stub
				m_x=FirstGame.ball.down3.getX();
				m_y=FirstGame.ball.down3.getY();
				
				//System.out.println("开始拖动时x="+x+"y="+y);
				//System.out.println("m_x="+m_x+"m_y="+m_y);
			}
      
			@Override
			public void dragStop(InputEvent event, float x, float y, int pointer) {
				// TODO Auto-generated method stub
				
				float current_x=FirstGame.ball.down3.getX();
				float current_y=FirstGame.ball.down3.getY();
				System.out.println("current_x"+current_x+"current_y="+current_y);
				int m=min(current_x,current_y);
				System.out.println("down3停止拖拽"+"m="+m);
				if(FirstGame.ifexist[m]==0)
				{
					
					int n=find(FirstGame.actorGroup,FirstGame.ball.down3);
					if((list1.contains(m)&&list1.contains(n))||(list2.contains(m)&&list2.contains(n))||(list3.contains(m)&&list3.contains(n))||(list4.contains(m)&&list4.contains(n)))
					{
						System.out.println("属于同一行");
						Actor temp=new Actor();
						temp=FirstGame.actorGroup[m];
						FirstGame.actorGroup[m]=FirstGame.actorGroup[n];
						FirstGame.actorGroup[n]=temp;
						FirstGame.actorGroup[m].setVisible(true);
						FirstGame.actorGroup[m].setPosition(point[m].x,point[m].y);
						FirstGame.actorGroup[n].setVisible(false);
					}
					else
					{
						FirstGame.actorGroup[m].setVisible(true);
						FirstGame.actorGroup[m].setPosition(point[m].x,point[m].y);
						FirstGame.ball.down3.setVisible(false);
						FirstGame.ball.down3.setPosition(point[n].x,point[n].y);
					}
					//if(ifRoll(m,n)==1)
					//{
						//System.out.println("应该进行旋转");
						//FirstGame.ball.right1.setOrigin(0,0);
						//FirstGame.ball.right1.addAction(Actions.rotateBy(-90, 0.5f));
						//FirstGame.ball.right1.setRotation(30);
						//FirstGame.actorGroup[m].setVisible(true);
						//FirstGame.ball.down3.setVisible(false);
						//FirstGame.ball.down3.setPosition(point[n].x,point[n].y);
					//}
					
					FirstGame.ifexist[m]=1;
					
					System.out.println("n="+n);
				    FirstGame.ifexist[n]=0;
					//FirstGame.actorGroup[n]=null;
					///FirstGame.actorGroup[m]=FirstGame.ball.down3;
					
					
				}
				else
				{
					FirstGame.ball.down3.setPosition(m_x, m_y);
					
				}
			}

			@Override
			public void drag(InputEvent event, float x, float y, int pointer) {
				// TODO Auto-generated method stub
				//super.drag(event, x, y, pointer);
				System.out.println("m="+m+"x="+x+"y="+y+"pointer="+pointer);
				
					//System.out.println("在拖动函数中，x="+x+"y="+y);
					float x0=FirstGame.ball.down3.getX();
					float y0=FirstGame.ball.down3.getY();
					 System.out.println("x0="+x0+"y0="+y0);
					  if (x != m_x)
		                {
		                    x0+= x;
		                }
		                if (y != m_y)
		                {
		                    y0+= y;
		                }
		                System.out.println("x0="+x0+"y0="+y0);
		              //  FirstGame.ifexist[5]=0;
		                FirstGame.ball.down3.setPosition(x0, y0);
			}   
	       });
    	
    	
    	
    	FirstGame.ball.up1.addListener(new DragListener()
	       {

	    	   float m_x,m_y;
	    	     int m=0;
			@Override
			public void dragStart(InputEvent event, float x, float y,
					int pointer) {
				// TODO Auto-generated method stub
				m_x=FirstGame.ball.up1.getX();
				m_y=FirstGame.ball.up1.getY();
				
				//System.out.println("开始拖动时x="+x+"y="+y);
				//System.out.println("m_x="+m_x+"m_y="+m_y);
			}
         
			@Override
			public void dragStop(InputEvent event, float x, float y, int pointer) {
				// TODO Auto-generated method stub
				
				float current_x=FirstGame.ball.up1.getX();
				float current_y=FirstGame.ball.up1.getY();
				System.out.println("current_x"+current_x+"current_y="+current_y);
				int m=min(current_x,current_y);
				System.out.println("up1停止拖拽"+"m="+m);
				if(FirstGame.ifexist[m]==0)
				{
					
					int n=find(FirstGame.actorGroup,FirstGame.ball.up1);
					if((list1.contains(m)&&list1.contains(n))||(list2.contains(m)&&list2.contains(n))||(list3.contains(m)&&list3.contains(n))||(list4.contains(m)&&list4.contains(n)))
					{
						System.out.println("属于同一行");
						Actor temp=new Actor();
						temp=FirstGame.actorGroup[m];
						FirstGame.actorGroup[m]=FirstGame.actorGroup[n];
						FirstGame.actorGroup[n]=temp;
						FirstGame.actorGroup[m].setVisible(true);
						FirstGame.actorGroup[m].setPosition(point[m].x,point[m].y);
						FirstGame.actorGroup[n].setVisible(false);
					}
					else
					{
						FirstGame.actorGroup[m].setVisible(true);
						FirstGame.actorGroup[m].setPosition(point[m].x,point[m].y);
						FirstGame.ball.up1.setVisible(false);
						FirstGame.ball.up1.setPosition(point[n].x,point[n].y);
					}
					//if(ifRoll(m,n)==1)
					//{
						//System.out.println("应该进行旋转");
						//FirstGame.ball.right1.setOrigin(0,0);
						//FirstGame.ball.right1.addAction(Actions.rotateBy(-90, 0.5f));
						//FirstGame.ball.right1.setRotation(30);
						//FirstGame.actorGroup[m].setVisible(true);
						//FirstGame.ball.up1.setVisible(false);
						//FirstGame.ball.up1.setPosition(point[n].x,point[n].y);
					//}
					
					FirstGame.ifexist[m]=1;
					
					System.out.println("n="+n);
				    FirstGame.ifexist[n]=0;
					//FirstGame.actorGroup[n]=null;
					//FirstGame.actorGroup[m]=FirstGame.ball.up1;
					
					
				}
				else
				{
					FirstGame.ball.up1.setPosition(m_x, m_y);
					
				}
			}

			@Override
			public void drag(InputEvent event, float x, float y, int pointer) {
				// TODO Auto-generated method stub
				//super.drag(event, x, y, pointer);
				System.out.println("m="+m+"x="+x+"y="+y+"pointer="+pointer);
				
					//System.out.println("在拖动函数中，x="+x+"y="+y);
					float x0=FirstGame.ball.up1.getX();
					float y0=FirstGame.ball.up1.getY();
					 System.out.println("x0="+x0+"y0="+y0);
					  if (x != m_x)
		                {
		                    x0+= x;
		                }
		                if (y != m_y)
		                {
		                    y0+= y;
		                }
		                System.out.println("x0="+x0+"y0="+y0);
		              //  FirstGame.ifexist[5]=0;
		                FirstGame.ball.up1.setPosition(x0, y0);
			}   
	       });
    	
    	
    	FirstGame.ball.up2.addListener(new DragListener()
	       {

	    	   float m_x,m_y;
	    	     int m=0;
			@Override
			public void dragStart(InputEvent event, float x, float y,
					int pointer) {
				// TODO Auto-generated method stub
				m_x=FirstGame.ball.up2.getX();
				m_y=FirstGame.ball.up2.getY();
				
				//System.out.println("开始拖动时x="+x+"y="+y);
				//System.out.println("m_x="+m_x+"m_y="+m_y);
			}
      
			@Override
			public void dragStop(InputEvent event, float x, float y, int pointer) {
				// TODO Auto-generated method stub
				
				float current_x=FirstGame.ball.up2.getX();
				float current_y=FirstGame.ball.up2.getY();
				System.out.println("current_x"+current_x+"current_y="+current_y);
				int m=min(current_x,current_y);
				System.out.println("up2停止拖拽"+"m="+m);
				if(FirstGame.ifexist[m]==0)
				{
					
					int n=find(FirstGame.actorGroup,FirstGame.ball.up2);
					if((list1.contains(m)&&list1.contains(n))||(list2.contains(m)&&list2.contains(n))||(list3.contains(m)&&list3.contains(n))||(list4.contains(m)&&list4.contains(n)))
					{
						System.out.println("属于同一行");
						Actor temp=new Actor();
						temp=FirstGame.actorGroup[m];
						FirstGame.actorGroup[m]=FirstGame.actorGroup[n];
						FirstGame.actorGroup[n]=temp;
						FirstGame.actorGroup[m].setVisible(true);
						FirstGame.actorGroup[m].setPosition(point[m].x,point[m].y);
						FirstGame.actorGroup[n].setVisible(false);
					}
					else
					{
						FirstGame.actorGroup[m].setVisible(true);
						FirstGame.actorGroup[m].setPosition(point[m].x,point[m].y);
						FirstGame.ball.up2.setVisible(false);
						FirstGame.ball.up2.setPosition(point[n].x,point[n].y);
					}
					//if(ifRoll(m,n)==1)
					//{
						//System.out.println("应该进行旋转");
						//FirstGame.ball.right1.setOrigin(0,0);
						//FirstGame.ball.right1.addAction(Actions.rotateBy(-90, 0.5f));
						//FirstGame.ball.right1.setRotation(30);
					//	FirstGame.actorGroup[m].setVisible(true);
					//	FirstGame.ball.up2.setVisible(false);
						//FirstGame.ball.up2.setPosition(point[n].x,point[n].y);
					//}
					
					FirstGame.ifexist[m]=1;
					
					System.out.println("n="+n);
				    FirstGame.ifexist[n]=0;
					//FirstGame.actorGroup[n]=null;
					//FirstGame.actorGroup[m]=FirstGame.ball.up2;
					
					
				}
				else
				{
					FirstGame.ball.up2.setPosition(m_x, m_y);
					
				}
			}

			@Override
			public void drag(InputEvent event, float x, float y, int pointer) {
				// TODO Auto-generated method stub
				//super.drag(event, x, y, pointer);
				System.out.println("m="+m+"x="+x+"y="+y+"pointer="+pointer);
				
					//System.out.println("在拖动函数中，x="+x+"y="+y);
					float x0=FirstGame.ball.up2.getX();
					float y0=FirstGame.ball.up2.getY();
					 System.out.println("x0="+x0+"y0="+y0);
					  if (x != m_x)
		                {
		                    x0+= x;
		                }
		                if (y != m_y)
		                {
		                    y0+= y;
		                }
		                System.out.println("x0="+x0+"y0="+y0);
		              //  FirstGame.ifexist[5]=0;
		                FirstGame.ball.up2.setPosition(x0, y0);
			}   
	       });
    	
    	
    	FirstGame.ball.up3.addListener(new DragListener()
	       {

	    	   float m_x,m_y;
	    	     int m=0;
			@Override
			public void dragStart(InputEvent event, float x, float y,
					int pointer) {
				// TODO Auto-generated method stub
				m_x=FirstGame.ball.up3.getX();
				m_y=FirstGame.ball.up3.getY();
				
				//System.out.println("开始拖动时x="+x+"y="+y);
				//System.out.println("m_x="+m_x+"m_y="+m_y);
			}
      
			@Override
			public void dragStop(InputEvent event, float x, float y, int pointer) {
				// TODO Auto-generated method stub
				
				float current_x=FirstGame.ball.up3.getX();
				float current_y=FirstGame.ball.up3.getY();
				System.out.println("current_x"+current_x+"current_y="+current_y);
				int m=min(current_x,current_y);
				System.out.println("up3停止拖拽"+"m="+m);
				if(FirstGame.ifexist[m]==0)
				{
					
					int n=find(FirstGame.actorGroup,FirstGame.ball.up3);
					if((list1.contains(m)&&list1.contains(n))||(list2.contains(m)&&list2.contains(n))||(list3.contains(m)&&list3.contains(n))||(list4.contains(m)&&list4.contains(n)))
					{
						System.out.println("属于同一行");
						Actor temp=new Actor();
						temp=FirstGame.actorGroup[m];
						FirstGame.actorGroup[m]=FirstGame.actorGroup[n];
						FirstGame.actorGroup[n]=temp;
						FirstGame.actorGroup[m].setVisible(true);
						FirstGame.actorGroup[m].setPosition(point[m].x,point[m].y);
						FirstGame.actorGroup[n].setVisible(false);
					}
					else
					{
						FirstGame.actorGroup[m].setVisible(true);
						FirstGame.actorGroup[m].setPosition(point[m].x,point[m].y);
						FirstGame.ball.up3.setVisible(false);
						FirstGame.ball.up3.setPosition(point[n].x,point[n].y);
					}
					//if(ifRoll(m,n)==1)
					//{
						//System.out.println("应该进行旋转");
						//FirstGame.ball.right1.setOrigin(0,0);
						//FirstGame.ball.right1.addAction(Actions.rotateBy(-90, 0.5f));
						//FirstGame.ball.right1.setRotation(30);
						//FirstGame.actorGroup[m].setVisible(true);
						//FirstGame.ball.up3.setVisible(false);
						//FirstGame.ball.up3.setPosition(point[n].x,point[n].y);
					//}
					
					FirstGame.ifexist[m]=1;
					
					System.out.println("n="+n);
				    FirstGame.ifexist[n]=0;
					//FirstGame.actorGroup[n]=null;
					//FirstGame.actorGroup[m]=FirstGame.ball.up3;
					
					
				}
				else
				{
					FirstGame.ball.up3.setPosition(m_x, m_y);
					
				}
			}

			@Override
			public void drag(InputEvent event, float x, float y, int pointer) {
				// TODO Auto-generated method stub
				//super.drag(event, x, y, pointer);
				System.out.println("m="+m+"x="+x+"y="+y+"pointer="+pointer);
				
					//System.out.println("在拖动函数中，x="+x+"y="+y);
					float x0=FirstGame.ball.up3.getX();
					float y0=FirstGame.ball.up3.getY();
					 System.out.println("x0="+x0+"y0="+y0);
					  if (x != m_x)
		                {
		                    x0+= x;
		                }
		                if (y != m_y)
		                {
		                    y0+= y;
		                }
		                System.out.println("x0="+x0+"y0="+y0);
		              //  FirstGame.ifexist[5]=0;
		                FirstGame.ball.up3.setPosition(x0, y0);
			}   
	       });
    	
    	
    	FirstGame.ball.left1.addListener(new DragListener()
	       {

	    	   float m_x,m_y;
	    	     int m=0;
			@Override
			public void dragStart(InputEvent event, float x, float y,
					int pointer) {
				// TODO Auto-generated method stub
				m_x=FirstGame.ball.left1.getX();
				m_y=FirstGame.ball.left1.getY();
				
				//System.out.println("开始拖动时x="+x+"y="+y);
				//System.out.println("m_x="+m_x+"m_y="+m_y);
			}
         
			@Override
			public void dragStop(InputEvent event, float x, float y, int pointer) {
				// TODO Auto-generated method stub
				
				float current_x=FirstGame.ball.left1.getX();
				float current_y=FirstGame.ball.left1.getY();
				System.out.println("current_x"+current_x+"current_y="+current_y);
				int m=min(current_x,current_y);
				System.out.println("left1停止拖拽"+"m="+m+"FirstGame.ifexist[m]="+FirstGame.ifexist[m]);
				if(FirstGame.ifexist[m]==0)
				{
					
					int n=find(FirstGame.actorGroup,FirstGame.ball.left1);
					if((list1.contains(m)&&list1.contains(n))||(list2.contains(m)&&list2.contains(n))||(list3.contains(m)&&list3.contains(n))||(list4.contains(m)&&list4.contains(n)))
					{
						System.out.println("属于同一行");
						Actor temp=new Actor();
						temp=FirstGame.actorGroup[m];
						FirstGame.actorGroup[m]=FirstGame.actorGroup[n];
						FirstGame.actorGroup[n]=temp;
						FirstGame.actorGroup[m].setVisible(true);
						FirstGame.actorGroup[m].setPosition(point[m].x,point[m].y);
						FirstGame.actorGroup[n].setVisible(false);
					}
					else
					{
						FirstGame.actorGroup[m].setVisible(true);
						FirstGame.actorGroup[m].setPosition(point[m].x,point[m].y);
						FirstGame.ball.left1.setVisible(false);
						FirstGame.ball.left1.setPosition(point[n].x,point[n].y);
					}
					//if(ifRoll(m,n)==1)
					//{
						//System.out.println("应该进行旋转");
						//FirstGame.ball.right1.setOrigin(0,0);
						//FirstGame.ball.right1.addAction(Actions.rotateBy(-90, 0.5f));
						//FirstGame.ball.right1.setRotation(30);
						//FirstGame.actorGroup[m].setVisible(true);
						//FirstGame.ball.left1.setVisible(false);
					//	FirstGame.ball.left1.setPosition(point[n].x,point[n].y);
					//}
					
					FirstGame.ifexist[m]=1;
					
					System.out.println("n="+n);
				    FirstGame.ifexist[n]=0;
					//FirstGame.actorGroup[n]=null;
					//FirstGame.actorGroup[m]=FirstGame.ball.left1;
					
					
				}
				else
				{
					FirstGame.ball.left1.setPosition(m_x, m_y);
					
				}
			}

			@Override
			public void drag(InputEvent event, float x, float y, int pointer) {
				// TODO Auto-generated method stub
				//super.drag(event, x, y, pointer);
				System.out.println("m="+m+"x="+x+"y="+y+"pointer="+pointer);
				
					//System.out.println("在拖动函数中，x="+x+"y="+y);
					float x0=FirstGame.ball.left1.getX();
					float y0=FirstGame.ball.left1.getY();
					 System.out.println("x0="+x0+"y0="+y0);
					  if (x != m_x)
		                {
		                    x0+= x;
		                }
		                if (y != m_y)
		                {
		                    y0+= y;
		                }
		                System.out.println("x0="+x0+"y0="+y0);
		              //  FirstGame.ifexist[5]=0;
		                FirstGame.ball.left1.setPosition(x0, y0);
			}   
	       });
    	
    	
    	FirstGame.ball.left2.addListener(new DragListener()
	       {

	    	   float m_x,m_y;
	    	     int m=0;
			@Override
			public void dragStart(InputEvent event, float x, float y,
					int pointer) {
				// TODO Auto-generated method stub
				m_x=FirstGame.ball.left2.getX();
				m_y=FirstGame.ball.left2.getY();
				
				//System.out.println("开始拖动时x="+x+"y="+y);
				//System.out.println("m_x="+m_x+"m_y="+m_y);
			}
      
			@Override
			public void dragStop(InputEvent event, float x, float y, int pointer) {
				// TODO Auto-generated method stub
				
				float current_x=FirstGame.ball.left2.getX();
				float current_y=FirstGame.ball.left2.getY();
				System.out.println("current_x"+current_x+"current_y="+current_y);
				int m=min(current_x,current_y);
				System.out.println("left2停止拖拽"+"m="+m+"FirstGame.ifexist[m]="+FirstGame.ifexist[m]);
				if(FirstGame.ifexist[m]==0)
				{
					
					int n=find(FirstGame.actorGroup,FirstGame.ball.left2);
					if((list1.contains(m)&&list1.contains(n))||(list2.contains(m)&&list2.contains(n))||(list3.contains(m)&&list3.contains(n))||(list4.contains(m)&&list4.contains(n)))
					{
						System.out.println("属于同一行");
						Actor temp=new Actor();
						temp=FirstGame.actorGroup[m];
						FirstGame.actorGroup[m]=FirstGame.actorGroup[n];
						FirstGame.actorGroup[n]=temp;
						FirstGame.actorGroup[m].setVisible(true);
						FirstGame.actorGroup[m].setPosition(point[m].x,point[m].y);
						FirstGame.actorGroup[n].setVisible(false);
					}
					else
					{
						FirstGame.actorGroup[m].setVisible(true);
						FirstGame.actorGroup[m].setPosition(point[m].x,point[m].y);
						FirstGame.ball.left2.setVisible(false);
						FirstGame.ball.left2.setPosition(point[n].x,point[n].y);
					}
					//if(ifRoll(m,n)==1)
					//{
						//System.out.println("应该进行旋转");
						//FirstGame.ball.right1.setOrigin(0,0);
						//FirstGame.ball.right1.addAction(Actions.rotateBy(-90, 0.5f));
						//FirstGame.ball.right1.setRotation(30);
						//FirstGame.actorGroup[m].setVisible(true);
						//FirstGame.ball.left2.setVisible(false);
						//FirstGame.ball.left2.setPosition(point[n].x,point[n].y);
					//}
					
					FirstGame.ifexist[m]=1;
					
					System.out.println("n="+n);
				    FirstGame.ifexist[n]=0;
					//FirstGame.actorGroup[n]=null;
					//FirstGame.actorGroup[m]=FirstGame.ball.left2;
					
					
				}
				else
				{
					FirstGame.ball.left2.setPosition(m_x, m_y);
					
				}
			}

			@Override
			public void drag(InputEvent event, float x, float y, int pointer) {
				// TODO Auto-generated method stub
				//super.drag(event, x, y, pointer);
				System.out.println("m="+m+"x="+x+"y="+y+"pointer="+pointer);
				
					//System.out.println("在拖动函数中，x="+x+"y="+y);
					float x0=FirstGame.ball.left2.getX();
					float y0=FirstGame.ball.left2.getY();
					 System.out.println("x0="+x0+"y0="+y0);
					  if (x != m_x)
		                {
		                    x0+= x;
		                }
		                if (y != m_y)
		                {
		                    y0+= y;
		                }
		                System.out.println("x0="+x0+"y0="+y0);
		              //  FirstGame.ifexist[5]=0;
		                FirstGame.ball.left2.setPosition(x0, y0);
			}   
	       });
    	
    	
    	FirstGame.ball.left3.addListener(new DragListener()
	       {

	    	   float m_x,m_y;
	    	     int m=0;
			@Override
			public void dragStart(InputEvent event, float x, float y,
					int pointer) {
				// TODO Auto-generated method stub
				m_x=FirstGame.ball.left3.getX();
				m_y=FirstGame.ball.left3.getY();
				
				//System.out.println("开始拖动时x="+x+"y="+y);
				//System.out.println("m_x="+m_x+"m_y="+m_y);
			}
      
			@Override
			public void dragStop(InputEvent event, float x, float y, int pointer) {
				// TODO Auto-generated method stub
				
				float current_x=FirstGame.ball.left3.getX();
				float current_y=FirstGame.ball.left3.getY();
				System.out.println("current_x"+current_x+"current_y="+current_y);
				int m=min(current_x,current_y);
				System.out.println("left3停止拖拽"+"m="+m+"FirstGame.ifexist[m]="+FirstGame.ifexist[m]);
				if(FirstGame.ifexist[m]==0)
				{
					
					int n=find(FirstGame.actorGroup,FirstGame.ball.left3);
					if((list1.contains(m)&&list1.contains(n))||(list2.contains(m)&&list2.contains(n))||(list3.contains(m)&&list3.contains(n))||(list4.contains(m)&&list4.contains(n)))
					{
						System.out.println("属于同一行");
						Actor temp=new Actor();
						temp=FirstGame.actorGroup[m];
						FirstGame.actorGroup[m]=FirstGame.actorGroup[n];
						FirstGame.actorGroup[n]=temp;
						FirstGame.actorGroup[m].setVisible(true);
						FirstGame.actorGroup[m].setPosition(point[m].x,point[m].y);
						FirstGame.actorGroup[n].setVisible(false);
					}
					else
					{
						FirstGame.actorGroup[m].setVisible(true);
						FirstGame.actorGroup[m].setPosition(point[m].x,point[m].y);
						FirstGame.ball.left3.setVisible(false);
						FirstGame.ball.left3.setPosition(point[n].x,point[n].y);
					}
					//if(ifRoll(m,n)==1)
					//{
						//System.out.println("应该进行旋转");
						//FirstGame.ball.right1.setOrigin(0,0);
						//FirstGame.ball.right1.addAction(Actions.rotateBy(-90, 0.5f));
						//FirstGame.ball.right1.setRotation(30);
						//FirstGame.actorGroup[m].setVisible(true);
						//FirstGame.ball.left3.setVisible(false);
						//FirstGame.ball.left3.setPosition(point[n].x,point[n].y);
					//}
					
					FirstGame.ifexist[m]=1;
					
					System.out.println("n="+n);
				    FirstGame.ifexist[n]=0;
					//FirstGame.actorGroup[n]=null;
					//FirstGame.actorGroup[m]=FirstGame.ball.left3;
					
					
				}
				else
				{
					FirstGame.ball.left3.setPosition(m_x, m_y);
					
				}
			}

			@Override
			public void drag(InputEvent event, float x, float y, int pointer) {
				// TODO Auto-generated method stub
				//super.drag(event, x, y, pointer);
				System.out.println("m="+m+"x="+x+"y="+y+"pointer="+pointer);
				
					//System.out.println("在拖动函数中，x="+x+"y="+y);
					float x0=FirstGame.ball.left3.getX();
					float y0=FirstGame.ball.left3.getY();
					 System.out.println("x0="+x0+"y0="+y0);
					  if (x != m_x)
		                {
		                    x0+= x;
		                }
		                if (y != m_y)
		                {
		                    y0+= y;
		                }
		                System.out.println("x0="+x0+"y0="+y0);
		              //  FirstGame.ifexist[5]=0;
		                FirstGame.ball.left3.setPosition(x0, y0);
			}   
	       });
    	
    	
    	
    	
    	
    	   }
    
}
