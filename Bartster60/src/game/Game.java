package game;

import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import game.init.States;
import game.init.Tiles;
import game.state.State;

public class Game implements Runnable {

	//Constants
	public static final int FPS = 144;
	public static final int TPS = 144;
	
	public static final int TILESCALE = 16*3; //texture width * 4 //ZOOM
	
	public static final String NAME = "Game";
	public static final String VERSION = "0.2.0";
	
	//Attributes
	private boolean running;
	
	private static Handler handler;
	
	//Init
	private void preInit() {
		handler = new Handler();
	}
	
	private void init() {
		Tiles.init();
		handler.init();
		States.init();
	}
	
	private void postInit() {
		State.setState(States.menu);
		handler.getScreen().setVisible(true);
	}
	
	//Thread (Start/Stop)
	public synchronized void start() {
		this.running = true;
		new Thread(this).start();
	}
	
	public synchronized void stop() {
		this.running = false;
	}
	
	public void render() {
		//Setting BufferStrategy to TrippleBuffered
		BufferStrategy bs = handler.getScreen().getBufferStrategy();
		if (bs == null){
			handler.getScreen().createBufferStrategy(3);
			return;
		}
		
		//Rendering
		Graphics g = bs.getDrawGraphics();
		g.clearRect(0, 0, handler.getScreen().getWidth(), handler.getScreen().getHeight()); //prevent a flickering Screen by clearing it
		
		State.getCurrentState().render(g);
		
		g.dispose(); //releasing Systems Resources
		bs.show(); //draws the next Buffer stored to the Screen
	}
	
	public void tick() {
		State.getCurrentState().tick();
	}
	
	//Thread (run)
	@Override
	public void run() {
		
		//Timer Variables
		long lastTime = System.nanoTime();
		double nsPerFrame = 1000000000D/FPS;
		double nsPerTick = 1000000000D/TPS;
		
//		long lastTimer = System.currentTimeMillis();
//		int ticks = 0;
//	    int frames = 0;
		
		double deltaF=0;
		double deltaT=0;
		
		//calling init methods
		System.out.print("Init...");
		preInit();
		init();
		postInit();
		System.out.println(" done!");
		
		//Game loop
		while(running) {
			
			//Timer (restricting FPS and Ticks)
			long now = System.nanoTime();
			deltaF += (now-lastTime) / nsPerFrame;
			deltaT += (now-lastTime) / nsPerTick;
			lastTime = now;
			
			while(deltaT >= 1) {
//				ticks++;
				tick();
				deltaT--;
			}
			
			while(deltaF >= 1) {
//				frames++;
				render();
				deltaF--;
			}
			
			//FPS/TPS Output
//			if (System.currentTimeMillis() - lastTimer >= 1000) {
//				lastTimer += 1000;
//				System.out.println(frames + "/" + ticks + " Frames/Ticks per Second");
//				frames = 0;
//				ticks = 0;
//			}
		}
	}
	
	//GETTER
	public static Handler getHandler() {
		return handler;
	}
}
