package game.input;

import game.Game;
import game.entity.EntityLiving;
import game.entity.player.EntityPlayer;
import game.util.InputHandler;

public class InputGame {
	private static boolean jumped;
//	private static int cooldown, amountCooldown = (int) (Game.TPS*0.5); //Removed
	protected static final InputHandler input = Game.getHandler().getInput();
	private static EntityPlayer player;
	private static float a = 0.03F;
	
	public static void tick() {
		player = Game.getHandler().getWorld().getEntities().getPlayer();
		if(input.jump.isPressed() && !jumped && !player.isAirborn()) {
			player.jump();
			jumped = true;
		} else if (jumped && !input.jump.isPressed())
			jumped =false;
		
//		if(player.isAirborn()) //Removed
//			a=0.02F;
//		else a = 0.03F;
		
		if(input.left.isPressed() && !input.right.isPressed())
			player.accelerate(-a);
		else if(player.getCurrentMomentum()<0 && !input.right.isPressed()) player.slowDown(a/2);
		
		if(input.right.isPressed() && !input.left.isPressed())
			player.accelerate(a);
		else if(player.getCurrentMomentum()>0 && !input.left.isPressed()) player.slowDown(a/2);
		
		if(input.left.isPressed() && input.right.isPressed())
			player.slowDown();
		
		if(input.run.isPressed()) {
			player.setSpeed(2);
		} else if(!player.isAirborn() && player.getCurrentMomentum()<=EntityLiving.DEFAULT_SPEED) player.setSpeed(EntityLiving.DEFAULT_SPEED);
		
//		if(input.shoot.isPressed()) { //Removed
//			if(cooldown<=0) {
//				float offset = (player.getFacing() == Facing.EAST) ? -0.25F : 0.25F;
//				Game.getHandler().getWorld().addEntity(new EntityFlame(player.getPosX()+offset, player.getPosY(), player));
//				cooldown=amountCooldown;
//			}
//		}
//		
//		if(cooldown>0)
//			cooldown--;
	}
}
