package game.entity.player;

import java.awt.Graphics;

import game.entity.EntityLiving;
import game.util.ImageLoader;
import game.util.math.Pos2D;

public class EntityPlayer extends EntityLiving {
	
	public EntityPlayer() {
		isAirborn=true;
		this.pos = new Pos2D(3,5);
		this.texture = ImageLoader.load("/assets/textures/creatures/player.png");
	}
	
	@Override
	public void render(Graphics g) {
		g.drawImage(texture, (int) (pos.getX()*32), (int) (pos.getY()*32), 32, 32, null);
	}
}
