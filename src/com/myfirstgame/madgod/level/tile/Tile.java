package com.myfirstgame.madgod.level.tile;

import com.myfirstgame.madgod.graphics.Screen;
import com.myfirstgame.madgod.graphics.Sprite;

public class Tile {
	public int x,y;
	public Sprite sprite;
	
	public static Tile grass = new GrassTile(Sprite.grass);
	
	public Tile(Sprite sprite){
		this.sprite = sprite;
	}
	
	public void render(int x,int y,Screen screen){
	}
	
	public boolean solid(){
		return false;
	}
}
