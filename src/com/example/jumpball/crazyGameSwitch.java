package com.example.jumpball;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;

public class crazyGameSwitch extends Game
{
 private Screen sc;
 crazyfail f;
 crazyFirstGame cfg;

	@Override
	public void create() {
		// TODO Auto-generated method stub
		 f=new crazyfail(this);
	     cfg=new crazyFirstGame(this);
	     
	    	  setScreen(cfg);
	   
		
	}
	

}
