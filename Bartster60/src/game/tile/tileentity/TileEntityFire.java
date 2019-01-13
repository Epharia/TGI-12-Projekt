package game.tile.tileentity;

import java.awt.Graphics;

import game.Game;
import game.entity.Entity;

public class TileEntityFire extends TileEntity {
	
	public TileEntityFire(double x, double y, String name) {
		super(x, y, name);
		this.AABB.setBounds(0, 0.25, 1, 0.75);
	}

	@Override
	public void render(Graphics g) {
//		g.fillRect((int) ((pos.getX()+AABB.getX())*Game.TILESCALE-Game.getHandler().getCamera().getxOffset()), (int) ((pos.getY()+AABB.getY())*Game.TILESCALE-Game.getHandler().getCamera().getyOffset()), (int) (AABB.getWidth()*Game.TILESCALE), (int) (AABB.getHeight()*Game.TILESCALE)); //RENDER AABB
		g.drawImage(getTexture(), (int) (pos.getX()*Game.TILESCALE-Game.getHandler().getCamera().getxOffset()), (int) (pos.getY()*Game.TILESCALE-Game.getHandler().getCamera().getyOffset()), Game.TILESCALE, Game.TILESCALE, null);
	}
	
	@Override
	public void tick() {
		super.tick();
		isEntityCollision();
	}
	
	@Override
	public void onEntityCollision(Entity e) {
		if (e.equals(Game.getHandler().getWorld().getEntities().getPlayer()))
		e.setDead(true);
	}
}