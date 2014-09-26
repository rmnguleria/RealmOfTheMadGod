package com.myfirstgame.madgod.level;

import com.myfirstgame.madgod.graphics.Screen;

public class Level {
	int width,height;
	int[] tiles;
	
	public Level(int width,int height){
		this.width = width;
		this.height = height;
		tiles = new int[width*height];
		generateLevel();
	}

	public Level(String path){
		loadLevel(path);
	}
	
	private void loadLevel(String path) {
	}

	void generateLevel() {
	}
	
	public void render(int xScroll , int yScroll , Screen screen){
	}
	
	public void update(){
	}
	
	private void time(){
	}
}
