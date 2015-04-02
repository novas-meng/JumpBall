package com.example.jumpball;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;

public class crowGameSwitch extends Game
{
 private Screen sc;
 crowFirstGame fg;
 crowfail cf;
	@Override
	public void create() {
		// TODO Auto-generated method stub
		 cf=new crowfail(this);
	     fg=new crowFirstGame(this);
	   
	    	  setScreen(fg);
	    
	}
	

}
