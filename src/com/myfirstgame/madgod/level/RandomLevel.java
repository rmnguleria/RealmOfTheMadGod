package com.myfirstgame.madgod.level;

import java.util.Random;

public class RandomLevel extends Level{

	private Random random = new Random();
	
	public RandomLevel(int width, int height) {
		super(width, height);
	}
	
	@Override
	void generateLevel(){
		for(int y=0;y<width;y++){
			for(int x=0;x<height;x++){
				tiles[x + y*width] = random.nextInt(4);
			}
		}
	}
	
}
