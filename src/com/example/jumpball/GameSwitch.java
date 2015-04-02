package com.example.jumpball;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;

public class GameSwitch extends Game
{
 private Screen sc;
 fail f;
 FirstGame fg;

	@Override
	public void create() {
		// TODO Auto-generated method stub
		 f=new fail(this);
	     fg=new FirstGame(this);
	     
	    	  setScreen(fg);
	   
		
	}
	

}
