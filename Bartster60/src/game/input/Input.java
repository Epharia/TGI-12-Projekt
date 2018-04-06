package game.input;

import game.Game;
import game.entity.player.EntityPlayer;
import game.util.InputHandler;

public class Input {
	protected static final InputHandler input = Game.getHandler().getInput();
	private static EntityPlayer player = Game.getHandler().getWorld().getEntities().getPlayer();
	
	public static void tick() {
		if(input.jump.isPressed() && !player.isAirborn())
			player.jump();
		
		if(input.left.isPressed() && !input.right.isPressed())//TODO change
			player.accelerate(-0.05);
		else player.slowDown();
		
		if(input.right.isPressed() && !input.left.isPressed())
			player.accelerate(0.05);
		else player.slowDown();
	}
}
