package game.entity.aggressive;

import java.awt.Graphics;

import game.Game;
import game.entity.EntityLiving;
import game.entity.ai.AIFollowPlayer;
import game.gfx.animation.Animation;
import game.tile.tileentity.TileEntityFire;
import game.util.ImageLoader;

public class EntityDog extends EntityLiving {
	
	public EntityDog(int posX, int posY) {
		this.pos.set(posX, posY);
		this.AABB.setBounds(0.0625, 0.375, 0.875, 0.62);
		animation=new Animation(150, ImageLoader.loadAnimationSheet("/resource/assets/textures/creatures/dog.png", 3));
		speed=0.4;
		jumpPower = 2.5;
		
		//Tasks Dog
		tasks.addTask(new AIFollowPlayer(this));
	}
	
	@Override
	public void render(Graphics g) {
//		g.fillRect((int) ((pos.getX()+AABB.getX())*Game.TILESCALE-Game.getHandler().getCamera().getxOffset()), (int) ((pos.getY()+AABB.getY())*Game.TILESCALE-Game.getHandler().getCamera().getyOffset()), (int) (AABB.getWidth()*Game.TILESCALE), (int) (AABB.getHeight()*Game.TILESCALE)); //RENDER AABB
		g.drawImage(getFrame(), (int) (pos.getX()*Game.TILESCALE-Game.getHandler().getCamera().getxOffset()), (int) (pos.getY()*Game.TILESCALE-Game.getHandler().getCamera().getyOffset()), Game.TILESCALE, Game.TILESCALE, null);
	}
	
	@Override
	public void tick() {
		super.tick();
	}
	
	@Override
	public void onDeath() {
		TileEntityFire e = new TileEntityFire(getPosX(), getPosY());
		e.setLiveTime(3000);
		Game.getHandler().getWorld().addEntity(e);
	}
}