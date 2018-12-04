package game.entity.ai;

import java.util.ArrayList;

public class AITasks {
	
	private ArrayList<EntityAITaskEntry> tasks = new ArrayList<EntityAITaskEntry>();
	
	public AITasks() {
	}
	
    public void addTask(int priority, AIBase task) {
    	tasks.add(new EntityAITaskEntry(priority, task));
    }
    
	public void tick() {
		for (EntityAITaskEntry task : tasks) {
			if(task.using && canContinue(task)) {
				task.action.tick();
				return;
			} else if (task.action.shouldExecute()) {
				task.action.startExecuting();
				task.using=true;
			} else {
				task.using = false;
				task.action.resetTask();
			}
		}
	}
    
    private boolean canContinue(AITasks.EntityAITaskEntry task)
    {
        return task.action.continueExecuting();
    }
	
    public class EntityAITaskEntry
    {
        public final AIBase action;
        public final int priority;
        public boolean using;

        public EntityAITaskEntry(int priorityIn, AIBase task)
        {
            this.priority = priorityIn;
            this.action = task;
        }
    }
}
