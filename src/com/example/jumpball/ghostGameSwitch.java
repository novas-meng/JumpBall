package com.example.jumpball;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;

public class ghostGameSwitch extends Game
{
 private Screen sc;
 ghostFirstGame fg;
 ghostfail cf;
	@Override
	public void create() {
		// TODO Auto-generated method stub
		 cf=new ghostfail(this);
	     fg=new ghostFirstGame(this);
	   
	    	  setScreen(fg);
	    
	}
	

}
