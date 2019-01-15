package game.entity.misc;

import java.awt.Graphics;

import game.Game;
import game.entity.EntityLiving;
import game.entity.EntityProjectile;
import game.gfx.animation.Animation;
import game.util.ImageLoader;

public class EntityFlame extends EntityProjectile {
	
	public EntityFlame(double posX, double posY, EntityLiving origin) {
		super(origin);
		this.pos.set(posX, posY);
		this.AABB.setBounds(0, 0.375, 1, 0.25);
		animation=new Animation(150, ImageLoader.loadAnimationSheet("/resource/assets/textures/misc/flame.png", 3));
	}
	
	@Override
	public void render(Graphics g) {
//		g.fillRect((int) ((pos.getX()+AABB.getX())*Game.TILESCALE-Game.getHandler().getCamera().getxOffset()), (int) ((pos.getY()+AABB.getY())*Game.TILESCALE-Game.getHandler().getCamera().getyOffset()), (int) (AABB.getWidth()*Game.TILESCALE), (int) (AABB.getHeight()*Game.TILESCALE)); //RENDER AABB
		g.drawImage(getFrame(), (int) (pos.getX()*Game.TILESCALE-Game.getHandler().getCamera().getxOffset()), (int) (pos.getY()*Game.TILESCALE-Game.getHandler().getCamera().getyOffset()), Game.TILESCALE, Game.TILESCALE, null);
	}
}