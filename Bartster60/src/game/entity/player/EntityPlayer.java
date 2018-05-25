package game.entity.player;

import java.awt.Graphics;

import game.Game;
import game.entity.EntityLiving;
import game.gfx.animation.Animation;
import game.util.ImageLoader;

public class EntityPlayer extends EntityLiving {
	
	public EntityPlayer() {
		this.pos.set(20, 10);
		this.AABB.setBounds(0.25, 0.125, 0.5, 0.875);
		this.animation = new Animation(150, ImageLoader.loadAnimationSheet("/assets/textures/creatures/player.png", 1, 16, 16));
	}
	
	@Override
	public void render(Graphics g) {
//		g.fillRect((int) ((pos.getX()+AABB.getX())*Game.TILESCALE-Game.getHandler().getCamera().getxOffset()), (int) ((pos.getY()+AABB.getY())*Game.TILESCALE-Game.getHandler().getCamera().getyOffset()), (int) (AABB.getWidth()*Game.TILESCALE), (int) (AABB.getHeight()*Game.TILESCALE)); //RENDER AABB
		g.drawImage(getFrame(), (int) (pos.getX()*Game.TILESCALE-Game.getHandler().getCamera().getxOffset()), (int) (pos.getY()*Game.TILESCALE-Game.getHandler().getCamera().getyOffset()), Game.TILESCALE, Game.TILESCALE, null);
	}
}