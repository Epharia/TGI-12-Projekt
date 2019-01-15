package game.entity.aggressive;

import java.awt.Graphics;

import game.Game;
import game.entity.EntityLiving;
import game.entity.misc.EntityFlame;
import game.entity.player.EntityPlayer;
import game.gfx.animation.Animation;
import game.tile.tileentity.TileEntityFire;
import game.util.ImageLoader;

public class EntityFlameSpawner extends EntityLiving {
	
	private int cooldownShot;
	
	public EntityFlameSpawner(int posX, int posY) {
		this.AABB.setBounds(0.1875, 0.3125, 0.625, 0.6875);
		this.pos.set(posX, posY);
		animation=new Animation(150, ImageLoader.loadAnimationSheet("/resource/assets/textures/creatures/flame_spawner.png", 3));
		this.maxHealth=10;
		heal();
		facing = Facing.WEST;
	}
	
	@Override
	public void render(Graphics g) {
//		g.fillRect((int) ((pos.getX()+AABB.getX())*Game.TILESCALE-Game.getHandler().getCamera().getxOffset()), (int) ((pos.getY()+AABB.getY())*Game.TILESCALE-Game.getHandler().getCamera().getyOffset()), (int) (AABB.getWidth()*Game.TILESCALE), (int) (AABB.getHeight()*Game.TILESCALE)); //RENDER AABB
		g.drawImage(animation.getFrame(), (int) (pos.getX()*Game.TILESCALE-Game.getHandler().getCamera().getxOffset()), (int) (pos.getY()*Game.TILESCALE-Game.getHandler().getCamera().getyOffset()), Game.TILESCALE, Game.TILESCALE, null);
	}
	
	@Override
	public void tick() {
		if(cooldownShot>0)
			cooldownShot--;
		else {
			cooldownShot = Game.TPS*3;
			
			EntityPlayer player = Game.getHandler().getWorld().getEntities().getPlayer();
			
			if (getCollisionBounds().getX()+getCollisionBounds().getWidth()<player.getCollisionBounds().getX()) {
				facing=Facing.WEST;
			}
			
			if (getCollisionBounds().getX()>player.getCollisionBounds().getX()+player.getCollisionBounds().getWidth()) {
				facing=Facing.EAST;
			}
			
			float offset = facing==Facing.EAST?-0.25F:0.25F;
			Game.getHandler().getWorld().addEntity(new EntityFlame((double) (getPosX()+offset), getPosY()+0.125, this));
		}
			
		
		if(animation.getFrameIndex()==0 && cooldownShot > Game.TPS*0.5) {
			animation.setFrameIndex(1);
			animation.tick();
		} else if(cooldownShot > Game.TPS*0.5)
				animation.tick();
		else animation.setFrameIndex(0);;
	}
	
	@Override
	public void onDeath() {
		TileEntityFire e = new TileEntityFire(getPosX(), getPosY());
		e.setLiveTime(8000);
		Game.getHandler().getWorld().addEntity(e);
	}
}