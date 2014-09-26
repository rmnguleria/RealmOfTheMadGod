package com.myfirstgame.madgod.graphics;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Spritesheet {
	private String path;
	final int SIZE;
	public int[] pixels;
	
	public static Spritesheet sheet = new Spritesheet("/textures/spritesheet.png", 256);
	
	public Spritesheet(String path , int size){
		this.path = path;
		this.SIZE = size;
		pixels = new int[SIZE*SIZE];
		load();
	}
	
	/**
	 * loading the image in the path specified and putting its RGB value in array pixels.
	 */
	private void load(){
		try {
			BufferedImage image = ImageIO.read(Spritesheet.class.getResource(path));
			int w = image.getWidth();
			int h = image.getHeight();
			image.getRGB(0, 0, w, h, pixels, 0, w);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
}
