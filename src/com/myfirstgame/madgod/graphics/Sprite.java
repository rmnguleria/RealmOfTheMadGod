package com.myfirstgame.madgod.graphics;

public class Sprite {
	public final int SIZE;
	private int x,y;
	public int[] pixels;
	private Spritesheet sheet;
	
	public static Sprite grass = new Sprite(16,0,0,Spritesheet.sheet);
	
	public Sprite(int size,int x,int y,Spritesheet sheet){
		SIZE = size;
		this.x = x;
		this.y = y;
		this.sheet = sheet;
		pixels = new int[SIZE*SIZE];
		load();
	}
	
	private void load(){
		for(int y=0;y<SIZE;y++){
			for(int x=0;x<SIZE;x++){
				pixels[x + y*SIZE] = sheet.pixels[(this.x +x) + (this.y + y)*sheet.SIZE];
			}
		}
	}
}
