package game.entity.ai;

import java.util.ArrayList;

/* 
 * |===============================================|
 * |this Class is used to handle tasks for Entities|
 * |===============================================|
 */


public class AITasks {
	
	private ArrayList<EntityAITaskEntry> tasks = new ArrayList<EntityAITaskEntry>(); //Stores the tasks
	
    public void addTask(AIBase task) { //adds a task to the ArrayList 'tasks'
    	tasks.add(new EntityAITaskEntry(task));
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
    
    private boolean canContinue(AITasks.EntityAITaskEntry task) {
        return task.action.continueExecuting();
    }
	
    public class EntityAITaskEntry { //this subclass is used to create tasks
        public final AIBase action;
        public boolean using;

        public EntityAITaskEntry(AIBase task) {;
            this.action = task;
        }
    }
}
