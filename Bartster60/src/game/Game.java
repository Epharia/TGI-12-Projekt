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
	
	public static final int TILESCALE = 16*3; //texture width * 3
	
	public static final String NAME = "Game";
	public static final String VERSION = "Alpha-0.0.4";
	
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
		State.setState(States.game);
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
		//Setting BufferStrategy
		BufferStrategy bs = handler.getScreen().getBufferStrategy();
		if (bs == null){
			handler.getScreen().createBufferStrategy(3);
			return;
		}
		
		//Render
		Graphics g = bs.getDrawGraphics();
		g.clearRect(0, 0, handler.getScreen().getWidth(), handler.getScreen().getHeight());
		
		State.getCurrentState().render(g);
		
		g.dispose();
		bs.show();
	}
	
	public void tick() {
		State.getCurrentState().tick();
	}
	
	//Thread
	@Override
	public void run() {
		
		//Timer Variables
		long lastTime = System.nanoTime();
		
		double nsPerFrame = 1000000000D/FPS;
		double nsPerTick = 1000000000D/TPS;
		
		double deltaF=0;
		double deltaT=0;
		
		//init
		System.out.print("Init...");
		preInit();
		init();
		postInit();
		System.out.println(" done!");
		
		while(running) {
			
			//Timer
			long now = System.nanoTime();
			deltaF += (now-lastTime) / nsPerFrame;
			deltaT += (now-lastTime) / nsPerTick;
			lastTime = now;
			
			while(deltaT >= 1) {
				tick();
				deltaT--;
			}
			
			while(deltaF >= 1) {
				render();
				deltaF--;
			}
		}
	}
	
	//GETTER
	public static Handler getHandler() {
		return handler;
	}
}
