package game.input;

import game.Game;
import game.entity.EntityLiving;
import game.entity.player.EntityPlayer;
import game.util.InputHandler;

public class InputGame {
	private static boolean jumped;
	protected static final InputHandler input = Game.getHandler().getInput();
	private static EntityPlayer player = Game.getHandler().getWorld().getEntities().getPlayer();
	
	public static void tick() { //TODO improve system
		if(input.jump.isPressed() && !jumped && !player.isAirborn()) {
			player.jump();
			jumped = true;
		} else if (jumped && !input.jump.isPressed())
			jumped =false;
		
		if(input.left.isPressed() && !input.right.isPressed())
			player.accelerate(-0.05);
		else if(player.getCurrentMomentum()<0) player.slowDown();
		
		if(input.right.isPressed() && !input.left.isPressed())
			player.accelerate(0.05);
		else if(player.getCurrentMomentum()>0) player.slowDown();
		
		if(input.run.isPressed()) {
			player.setSpeed(2);
		} else player.setSpeed(EntityLiving.DEFAULT_SPEED);
	}
}
