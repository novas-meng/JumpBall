package com.example.jumpball;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class Ballforbluetooth extends Actor
{
	// static ImageButton left1,up1,down1,right1,left2,up2,down2,right2,left3,up3,down3,right3;
	 static Image left1,up1,down1,right1,left2,up2,down2,right2,left3,up3,down3,right3;
	 public static float LBorder=340*MainActivity.w;
	public static float RBorder=(340*MainActivity.w+180*MainActivity.w*3-64*MainActivity.w);
	public static float DBorder=100*MainActivity.h;
	public static float UBorder=(100*MainActivity.h+180*MainActivity.h*3-64*MainActivity.h);
	//图片宽度
	public static float picWidth=180*MainActivity.w;
	 public static float picHeight=64*MainActivity.h;
	 //判断是否没有方块，方向是上下左右,1代表消除了
	static int[] ifexist=new int[12];
	TextureRegionDrawable trd,tru;
	Sound success,press;
	int statetime=0;
	static int ifdraw=0;
	static int c=0;
	//ifdraw=0,表示绘画红色砖块，即按错了
	TextureRegion currentframe;
	static int count=0;
	 public Ballforbluetooth()
	{
		 System.out.println("刷新");
	Texture txtup=new Texture(Gdx.files.internal("element_blue1.png"));
	Texture txtdown=new Texture(Gdx.files.internal("element_green.png"));
	 trd=new TextureRegionDrawable(new TextureRegion(txtdown));
	tru=new TextureRegionDrawable(new TextureRegion(txtup));
	down1=new Image(txtup);
	down2=new Image(txtup);
	down3=new Image(txtup);
	up1=new Image(txtup);
	up2=new Image(txtup);
	up3=new Image(txtup);
	//down1.setSize(360, 64);
	/*
	down2=new ImageButton(tru,trd);
	down3=new ImageButton(tru,trd);
	up1=new ImageButton(tru,trd);
	up2=new ImageButton(tru,trd);
	up3=new ImageButton(tru,trd);
	*/
 txtup=new Texture(Gdx.files.internal("element_blue2.png"));
 txtdown=new Texture(Gdx.files.internal("element_green1.png"));
	 trd=new TextureRegionDrawable(new TextureRegion(txtdown));
	 tru=new TextureRegionDrawable(new TextureRegion(txtup));
	 left1=new Image(txtup);
	 left2=new Image(txtup);
	 left3=new Image(txtup);
	 right1=new Image(txtup);
	 right2=new Image(txtup);
	 right3=new Image(txtup);
	 /*
	left1=new ImageButton(tru,trd);
	left1.setSize(64, 160);
	left2=new ImageButton(tru,trd);
	left2.setSize(64, 160);
	left3=new ImageButton(tru,trd);
	left3.setSize(64, 160);
	right1=new ImageButton(tru,trd);
	right1.setSize(64, 160);
	right2=new ImageButton(tru,trd);
	right2.setSize(64, 160);
	right3=new ImageButton(tru,trd);
	right3.setSize(64, 160);
	*/
	success=Gdx.audio.newSound(Gdx.files.internal("gold.wav"));
	press=Gdx.audio.newSound(Gdx.files.internal("up.wav"));
		//right1=new IamgeBUtton(new Text);
		//left1=new ImageButton((Drawable)txt);
		setPosition();
	}
	public void setPosition()
	{
		
		down1.setSize(180*MainActivity.w, 64*MainActivity.h);
		down1.setPosition(LBorder, DBorder-64*MainActivity.h);
		//down1.setSize(128*MainActivity.w, 64*MainActivity.h);
		down2.setSize(180*MainActivity.w, 64*MainActivity.h);
		down2.setPosition(LBorder+180*MainActivity.w, DBorder-64*MainActivity.h);
		//down2.setSize(128*MainActivity.w, 64*MainActivity.h);
		down3.setSize(180*MainActivity.w, 64*MainActivity.h);
		down3.setPosition(LBorder+180*2*MainActivity.w, DBorder-64*MainActivity.h);
		//down3.setSize(128*MainActivity.w, 64*MainActivity.h);
         
		
		
		
		left1.setSize(64*MainActivity.w, 180*MainActivity.h);
		left1.setPosition(LBorder-64*MainActivity.w, DBorder);
	//	left1.setSize(64*MainActivity.w, 128*MainActivity.h);
		  left2.setSize(64*MainActivity.w, 180*MainActivity.h);
		left2.setPosition(LBorder-64*MainActivity.w, DBorder+180*MainActivity.h);
		//left2.setSize(64*MainActivity.w, 128*MainActivity.h);
		  left3.setSize(64*MainActivity.w, 180*MainActivity.h);
		left3.setPosition(LBorder-64*MainActivity.w, DBorder+180*2*MainActivity.h);
		//left3.setSize(64*MainActivity.w, 128*MainActivity.h);
		
		
		up1.setSize(180*MainActivity.w, 64*MainActivity.h);
		up1.setPosition(LBorder, UBorder+64*MainActivity.h);
		//up1.setSize(128*MainActivity.w, 64*MainActivity.h);
		up2.setSize(180*MainActivity.w, 64*MainActivity.h);
		up2.setPosition(LBorder+180*MainActivity.w,UBorder+64*MainActivity.h);
		//up2.setSize(128*MainActivity.w, 64*MainActivity.h);
		up3.setSize(180*MainActivity.w, 64*MainActivity.h);
		up3.setPosition(LBorder+180*2*MainActivity.w, UBorder+64*MainActivity.h);
		//up3.setSize(64*MainActivity.w, 64*MainActivity.h);
		
		
		
		  right1.setSize(64*MainActivity.w, 180*MainActivity.h);
		right1.setPosition(RBorder+64*MainActivity.w, DBorder);
		//right1.setSize(64*MainActivity.w, 128*MainActivity.h);
		 right2.setSize(64*MainActivity.w, 180*MainActivity.h);
		right2.setPosition(RBorder+64*MainActivity.w, DBorder+180*MainActivity.h);
		//right2.setSize(64*MainActivity.w, 128*MainActivity.h);
		 right3.setSize(64*MainActivity.w, 180*MainActivity.h);
		right3.setPosition(RBorder+64*MainActivity.w, DBorder+180*2*MainActivity.h);
		
		//right3.setSize(64*MainActivity.w, 128*MainActivity.h);
		down1.addListener(new InputListener()
		{

			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				// TODO Auto-generated method stub
				//if(Jump.pdown1==1)
				//{
				//	ifdraw=1;
				//	System.out.println("被按下");
				//}
				//sound.play();
				//press.play();
				count++;
				if(Jump.dire==1&&Jump.num==0)
				{
					//success.play();
					ifdraw=1;
				}
				else
				{
					ifdraw=0;
				}
				return true;
			}

			@Override
			public void touchUp(InputEvent event, float x, float y,
					int pointer, int button) {
				// TODO Auto-generated method stub
				
				super.touchUp(event, x, y, pointer, button);
			}
			
		});
		down2.addListener(new InputListener()
		{

			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				// TODO Auto-generated method stub
				//if(Jump.pdown2==1)
				//{
				//	ifdraw=1;
				//	System.out.println("被按下");
				//}
				//sound.play();
				//press.play();
				count++;
				if(Jump.dire==1&&Jump.num==1)
				{
					//success.play();
					ifdraw=1;
				}
				else
				{
					ifdraw=0;
				}
				return true;
			}

			@Override
			public void touchUp(InputEvent event, float x, float y,
					int pointer, int button) {
				// TODO Auto-generated method stub
				
				super.touchUp(event, x, y, pointer, button);
			}
			
		});
		down3.addListener(new InputListener()
		{

			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				// TODO Auto-generated method stub
				count++;
				//sound.play();
				//press.play();
				if(Jump.dire==1&&Jump.num==2)
				{
					//success.play();
					ifdraw=1;
				}
				else
				{
					ifdraw=0;
				}
				return true;
			}

			@Override
			public void touchUp(InputEvent event, float x, float y,
					int pointer, int button) {
				// TODO Auto-generated method stub
				
				super.touchUp(event, x, y, pointer, button);
			}
			
		});
		up1.addListener(new InputListener()
		{

			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				// TODO Auto-generated method stub
				count++;
				//sound.play();
				//press.play();
				if(Jump.dire==3&&Jump.num==0)
				{
					//success.play();
					ifdraw=1;
				}
				else
				{
					ifdraw=0;
				}
				return true;
			}

			@Override
			public void touchUp(InputEvent event, float x, float y,
					int pointer, int button) {
				// TODO Auto-generated method stub
			
				super.touchUp(event, x, y, pointer, button);
			}
			
		});
		up2.addListener(new InputListener()
		{

			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				// TODO Auto-generated method stub
				count++;
				//sound.play();
				//press.play();
				if(Jump.dire==3&&Jump.num==1)
				{
					//success.play();
					ifdraw=1;
				}
				else
				{
					ifdraw=0;
				}
				return true;
			}

			@Override
			public void touchUp(InputEvent event, float x, float y,
					int pointer, int button) {
				// TODO Auto-generated method stub
			
				super.touchUp(event, x, y, pointer, button);
			}
			
		});
		up3.addListener(new InputListener()
		{

			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				// TODO Auto-generated method stub
				count++;
				//sound.play();
				//press.play();
				if(Jump.dire==3&&Jump.num==2)
				{
					//success.play();
					ifdraw=1;
				}
				else
				{
					ifdraw=0;
				}
				return true;
			}

			@Override
			public void touchUp(InputEvent event, float x, float y,
					int pointer, int button) {
				// TODO Auto-generated method stub
			
				super.touchUp(event, x, y, pointer, button);
			}
			
		});
		left1.addListener(new InputListener()
		{

			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				// TODO Auto-generated method stub
				count++;
				//sound.play();
				//press.play();
				if(Jump.dire==0&&Jump.num==0)
				{
					//success.play();
					ifdraw=1;
				}
				else
				{
					ifdraw=0;
				}
				return true;
			}

			@Override
			public void touchUp(InputEvent event, float x, float y,
					int pointer, int button) {
				// TODO Auto-generated method stub
				
				super.touchUp(event, x, y, pointer, button);
			}
			
		});
		left2.addListener(new InputListener()
		{

			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				// TODO Auto-generated method stub
				count++;
				//sound.play();
			//	press.play();
				if(Jump.dire==0&&Jump.num==1)
				{
					//success.play();
					ifdraw=1;
				}
				else
				{
					ifdraw=0;
				}
				return true;
			}

			@Override
			public void touchUp(InputEvent event, float x, float y,
					int pointer, int button) {
				// TODO Auto-generated method stub
				
				super.touchUp(event, x, y, pointer, button);
			}
			
		});
		left3.addListener(new InputListener()
		{

			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				// TODO Auto-generated method stub
				count++;
				//sound.play();
				//press.play();
				if(Jump.dire==0&&Jump.num==2)
				{
					//success.play();
					ifdraw=1;
				}
				else
				{
					ifdraw=0;
				}
				return true;
			}

			@Override
			public void touchUp(InputEvent event, float x, float y,
					int pointer, int button) {
				// TODO Auto-generated method stub
				
				super.touchUp(event, x, y, pointer, button);
			}
			
		});
		right1.addListener(new InputListener()
		{

			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				// TODO Auto-generated method stub
				count++;
				//sound.play();
				//press.play();
				if(Jump.dire==2&&Jump.num==0)
				{
					//success.play();
					ifdraw=1;
				}
				else
				{
					ifdraw=0;
				}
				return true;
			}

			@Override
			public void touchUp(InputEvent event, float x, float y,
					int pointer, int button) {
				// TODO Auto-generated method stub
			
				super.touchUp(event, x, y, pointer, button);
			}
			
		});
		right2.addListener(new InputListener()
		{

			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				// TODO Auto-generated method stub
				count++;
				//sound.play();
				//press.play();
				if(Jump.dire==2&&Jump.num==1)
				{
					//success.play();
					ifdraw=1;
				}
				else
				{
					ifdraw=0;
				}
				return true;
			}

			@Override
			public void touchUp(InputEvent event, float x, float y,
					int pointer, int button) {
				// TODO Auto-generated method stub
				
				super.touchUp(event, x, y, pointer, button);
			}
			
		});
		right3.addListener(new InputListener()
		{

			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				// TODO Auto-generated method stub
				count++;
				//sound.play();
				//press.play();
				if(Jump.dire==2&&Jump.num==2)
				{
					//success.play();
					ifdraw=1;
				}
				
				else
				{
					ifdraw=0;
				}
				return true;
			}

			@Override
			public void touchUp(InputEvent event, float x, float y,
					int pointer, int button) {
				// TODO Auto-generated method stub
				
				super.touchUp(event, x, y, pointer, button);
			}
			
		});
		
		//left1.setSize(MainActivity.screenwidth, MainActivity.screenheight);
	}
	@Override
	public void act(float arg0) {
		// TODO Auto-generated method stub
		super.act(arg0);
	}

	@Override
	public void draw(SpriteBatch batch, float parentAlpha) {
		// TODO Auto-generated method stub
		
	}
  
}
