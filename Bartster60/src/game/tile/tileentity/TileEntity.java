package game.tile.tileentity;

import java.awt.Graphics;

import game.Game;
import game.entity.Entity;
import game.util.ImageLoader;
import game.util.math.Pos2D;

public class TileEntity extends Entity {
	
	public TileEntity(double x, double y, String name) {
		super();
		this.setPos(new Pos2D(x, y));
		this.texture = ImageLoader.load("/resource/assets/textures/tiles/" + name + ".png");
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(getTexture(), (int) (pos.getX()*Game.TILESCALE-Game.getHandler().getCamera().getxOffset()), (int) (pos.getY()*Game.TILESCALE-Game.getHandler().getCamera().getyOffset()), Game.TILESCALE, Game.TILESCALE, null);
	}

	@Override
	public void tick() {
		currentHealth=100;
	}

	@Override
	public void onEntityCollision(Entity e) {
	}
}