package com.example.jumpball;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;

public class blueGameSwitch extends Game
{
 private Screen sc;
 blueFirstGame bfg;
 bluefail bf;
	@Override
	public void create() {
		// TODO Auto-generated method stub
		System.out.println("进入蓝牙对战切换模式");
	     bfg=new blueFirstGame(this);
	    
	     bf=new bluefail(this);
	     
	    	 setScreen(bfg);
	    
		
	}
	

}
