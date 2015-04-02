package com.example.jumpball;

import com.badlogic.gdx.Game;

public class rollGameSwitch extends Game
{

	FirstGameForRoll fg;
	rollFail rf;
	@Override
	public void create() {
		// TODO Auto-generated method stub
		fg=new FirstGameForRoll(this);
		rf=new rollFail(this);
		setScreen(fg);
	}

}
