package game.entity.ai;

public abstract class AIBase {
    
	public abstract boolean shouldExecute();

    public boolean continueExecuting() {
        return this.shouldExecute();
    }

    public boolean isInterruptible() {
        return true;
    }

    public void startExecuting() {
    }


    public void resetTask() {
    }

	public void tick() {

	}
}
