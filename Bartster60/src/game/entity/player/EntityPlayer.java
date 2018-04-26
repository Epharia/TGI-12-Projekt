package game.entity.player;

import java.awt.Graphics;

import game.Game;
import game.entity.EntityLiving;
import game.util.ImageLoader;

public class EntityPlayer extends EntityLiving {
	
	public EntityPlayer() {
		this.pos.set(0.5, 10);
		this.AABB.setBounds(0.125, 0, 0.745, 1.937);
		this.texture = ImageLoader.load("/assets/textures/creatures/player.png");
	}
	
	@Override
	public void render(Graphics g) {
//		g.fillRect((int) ((pos.getX()+AABB.getX())*Game.TILESCALE-Game.getHandler().getCamera().getxOffset()), (int) ((pos.getY()+AABB.getY())*Game.TILESCALE-Game.getHandler().getCamera().getyOffset()), (int) (AABB.getWidth()*Game.TILESCALE), (int) (AABB.getHeight()*Game.TILESCALE)); //RENDER AABB
		g.drawImage(texture, (int) (pos.getX()*Game.TILESCALE-Game.getHandler().getCamera().getxOffset()), (int) (pos.getY()*Game.TILESCALE-Game.getHandler().getCamera().getyOffset()), Game.TILESCALE, Game.TILESCALE*2, null);
	}
}