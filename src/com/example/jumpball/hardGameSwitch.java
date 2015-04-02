package com.example.jumpball;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;

public class hardGameSwitch extends Game
{
 private Screen sc;
 hardFirstGame hfg;
 hardfail hf;
	@Override
	public void create() {
		// TODO Auto-generated method stub
	     hfg=new hardFirstGame(this);
	     hf=new hardfail(this);
	    
	    	 setScreen(hfg);
	    
	    	
		
	}
	

}
