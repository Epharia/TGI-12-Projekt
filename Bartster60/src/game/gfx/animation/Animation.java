package game.gfx.animation;

import java.awt.image.BufferedImage;

import game.util.ImageLoader;

public class Animation {
	protected int speed;
	protected int frameIndex;
	protected long lastTimer;
	protected long timer;
	protected BufferedImage[] animationSheet;
	
	public Animation(int speed_ms, BufferedImage[] animationSheet) {
		this.speed=speed_ms;
		this.animationSheet=animationSheet;
		
		this.frameIndex=0;
		this.timer=0;
		this.lastTimer=System.currentTimeMillis();
	}
	
	public void tick() {
		long now = System.currentTimeMillis();
		timer+=now-lastTimer;
		lastTimer=now;
		
		if (timer>=speed) {
			frameIndex++;
			timer=0;
			if (frameIndex>=animationSheet.length) {
				frameIndex=0;
			}
		}
	}
	
	public BufferedImage getFrame() {
		return animationSheet[frameIndex];
	}
	
	public BufferedImage getFrameFliped() {
		return ImageLoader.flipImageX(animationSheet[frameIndex]);
	}
	
	public BufferedImage getFrameAt(int i) {
		if (i<animationSheet.length) {
			return animationSheet[i];
		}
		return null;
	}
	
	public void setSheet(BufferedImage[] animationSheet) {
		this.animationSheet = animationSheet;
	}	
	
	public BufferedImage[] getSheet() {
		return animationSheet;
	}	
	
	public int getFrameIndex() {
		return frameIndex;
	}

	public void setFrameIndex(int i) {
		this.frameIndex=i;
	}
	
	public Animation flip() {
		Animation a = this;
		a.setSheet(ImageLoader.flipAnimationSheetX(animationSheet));
		return a;
	}
}
