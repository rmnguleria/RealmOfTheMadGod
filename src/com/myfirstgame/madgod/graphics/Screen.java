package com.myfirstgame.madgod.graphics;
/*
 * You would struggle to put water out of a boot with instructions on the heel.
 */
import java.util.Random;

public class Screen {
	private int width , height;
	public int[] pixels;
	public int[] tiles;
	public final int TILES_SIZE = 2;
	public final int TILES_SIZE_MASK = TILES_SIZE - 1; 
	int counter = 0;
	int time = 0;
	
	private Random random;
	
	public Screen(int width,int height){
		this.width = width;
		this.height = height;
		pixels = new int[height*width];
		tiles = new int[TILES_SIZE*TILES_SIZE];
		random = new Random(0xffffff);
		for(int i=0;i<tiles.length;i++){
			tiles[i] = random.nextInt();
		}
	}
	
	public void clear(){
		for(int i=0;i<pixels.length;i++)
			pixels[i]=0;
	}
	
	/**
	 * main render function (this is the real deal.)
	 * @param xOffset UserInput in x direction
	 * @param yOffset UserInput in y direction
	 */
	public void render(int xOffset , int yOffset){
		int xp,yp;
		for(int y=0;y<height;y++){
			yp = y + yOffset;
			if(yp<0 || yp>=height ) continue;
			for(int x=0;x<width;x++){
				xp = x + xOffset;
				if(xp<0 || xp>= width ) continue;
				pixels[yp*width + xp] = Sprite.grass.pixels[(x&15) + (y&15)*Sprite.grass.SIZE];
			}
		}
	}
}
