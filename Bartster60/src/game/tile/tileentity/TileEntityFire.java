package game.tile.tileentity;

import java.awt.Graphics;

import game.Game;
import game.entity.Entity;
import game.gfx.animation.Animation;
import game.util.ImageLoader;

public class TileEntityFire extends TileEntity {
	
	private int cooldown;
	private int lifetime;
	private boolean shouldDie;
	private Animation animation;

	public TileEntityFire(double x, double y) {
		super(x, y, "fire");
		animation = new Animation(350, ImageLoader.loadAnimationSheet("/resource/assets/textures/tiles/fire.png", 4));
		this.AABB.setBounds(0, 0.25, 1, 0.75);
	}

	@Override
	public void render(Graphics g) {
//		g.fillRect((int) ((pos.getX()+AABB.getX())*Game.TILESCALE-Game.getHandler().getCamera().getxOffset()), (int) ((pos.getY()+AABB.getY())*Game.TILESCALE-Game.getHandler().getCamera().getyOffset()), (int) (AABB.getWidth()*Game.TILESCALE), (int) (AABB.getHeight()*Game.TILESCALE)); //RENDER AABB
		g.drawImage(animation.getFrame(), (int) (pos.getX()*Game.TILESCALE-Game.getHandler().getCamera().getxOffset()), (int) (pos.getY()*Game.TILESCALE-Game.getHandler().getCamera().getyOffset()), Game.TILESCALE, Game.TILESCALE, null);
	}
	
	@Override
	public void tick() {
		super.tick();
		isEntityCollision();
		
		animation.tick();
		
		if(cooldown>0)
			cooldown--;
		
		if(shouldDie && --lifetime<=0)
			setDead(true);
	}
	
	@Override
	public void onEntityCollision(Entity e) {
		if(cooldown<=0) { 
			if (e.equals(Game.getHandler().getWorld().getEntities().getPlayer()))
			e.damage(2);
			cooldown =(int) (Game.TPS * 0.25); //quarter of a second cooldown before dealing damage again
		}
	}
	
	public void setLiveTime(int ms) {
		lifetime = Game.TPS*(ms/1000);
		shouldDie=true;
	}
}