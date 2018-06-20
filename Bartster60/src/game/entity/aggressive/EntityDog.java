package game.entity.aggressive;

import java.awt.Graphics;

import game.Game;
import game.entity.EntityLiving;
import game.gfx.animation.Animation;
import game.util.ImageLoader;

public class EntityDog extends EntityLiving {
	
	public EntityDog() {
		this.pos.set(29, 11);
		this.AABB.setBounds(0.0625, 0.375, 0.9370, 0.6245);
		animation=new Animation(150, ImageLoader.loadAnimationSheet("/resource/assets/textures/creatures/dog.png", 3));
		speed=0.5;
	}
	
	@Override
	public void render(Graphics g) {
//		g.fillRect((int) ((pos.getX()+AABB.getX())*Game.TILESCALE-Game.getHandler().getCamera().getxOffset()), (int) ((pos.getY()+AABB.getY())*Game.TILESCALE-Game.getHandler().getCamera().getyOffset()), (int) (AABB.getWidth()*Game.TILESCALE), (int) (AABB.getHeight()*Game.TILESCALE)); //RENDER AABB
		g.drawImage(getFrame(), (int) (pos.getX()*Game.TILESCALE-Game.getHandler().getCamera().getxOffset()), (int) (pos.getY()*Game.TILESCALE-Game.getHandler().getCamera().getyOffset()), Game.TILESCALE, Game.TILESCALE, null);
	}
	
	@Override
	public void tick() {
		super.tick();
		animation.tick();
		accelerate(-0.25);
	}
}