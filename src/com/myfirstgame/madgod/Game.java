package com.myfirstgame.madgod;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

import javax.swing.JFrame;

import com.myfirstgame.madgod.graphics.Screen;
import com.myfirstgame.madgod.input.Keyboard;
/**
 * @author Raman
 * Main Game class.
 */
public class Game extends Canvas implements Runnable {
	private static final long serialVersionUID = 1L;
	
	public static int width = 300;
	public static int height = width / 16 * 9;
	public static int scale = 3;
	public boolean running = false;
	private Thread gameThread;
	public JFrame frame;
	private Keyboard keyboard;
	public Screen screen;
	
	public BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
	public int[] pixels = ((DataBufferInt)image.getRaster().getDataBuffer()).getData();
	
	public Game(){
		Dimension size = new Dimension(width*scale, height*scale);
		
		screen = new Screen(width, height);
		
		frame = new JFrame();
		setPreferredSize(size);
		frame.setResizable(false);
		frame.setTitle("Realm Of The Mad God");
		frame.add(this);
		frame.setLocationRelativeTo(null);
		frame.pack();
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		keyboard = new Keyboard();
		addKeyListener(keyboard);
	}
	
	/**
	 * contains our game loop.
	 */
	public void run(){
		long lastTime = System.nanoTime();
		long timer = System.currentTimeMillis();
		double delta = 0;
		double ns = 1000000000.0 / 60.0 ;
		long now = System.nanoTime();
		int updates = 0;
		int frames = 0;
		requestFocus();
		while(running){
			now = System.nanoTime();
			delta += (now-lastTime) / ns;
			//System.out.println("Delta :- " + delta);
			while(delta >= 1){
				update();
				updates++;
				delta--;
			}
			lastTime = now;
			render();
			frames++;
			if(System.currentTimeMillis() - timer > 1000){
				timer += 1000;
				//System.out.println(frames +"  fps,  " + updates + " ups");
				frame.setTitle("Realm Of The Mad God" + " | " + frames +"  fps,  " + updates + " ups");
				frames = 0;
				updates = 0;
			}
		}
		stop();
	}
	
	int x=0,y=0;
	
	/**
	 * Logic Of The Game.  runs constant no. of times per second
	 */
	public void update(){
		keyboard.update();
		if(keyboard.up) y--;
		if(keyboard.down) y++;
		if(keyboard.left) x++;
		if(keyboard.right) x--;
	}
	
	/**
	 * render the game. runs as fast as it can.
	 */
	public void render(){
		BufferStrategy bs = getBufferStrategy();
		if(bs == null){
			createBufferStrategy(3);
			return;
		}
		
		screen.clear();
		screen.render(x,y);
		
		for(int i=0;i<pixels.length;i++)
			pixels[i]=screen.pixels[i];
		
		// here comes the graphics
		
		Graphics g = bs.getDrawGraphics(); // returns a graphic context for the drawing buffer
		g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
		//g.setColor(Color.BLACK);
		//g.fillRect(0, 0, getWidth(), getHeight());
		g.dispose();
		bs.show();
	}
	
	/**
	 * starts the gameThread and calls the run method.
	 */
	public synchronized void start(){
		running = true;
		gameThread = new Thread(this, "Display");
		gameThread.start();
	}
	/**
	 * stops the run method and kills the thread.
	 */
	public synchronized void stop(){
		running = false;
		try {
			gameThread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args){
		Game game = new Game();
		game.start();
	}
}
