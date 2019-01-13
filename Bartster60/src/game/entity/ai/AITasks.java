package game.entity.ai;

import java.util.ArrayList;

/* 
 * |===============================================|
 * |this Class is used to handle tasks for Entities|
 * |===============================================|
 */


public class AITasks {
	
	private ArrayList<EntityAITaskEntry> tasks = new ArrayList<EntityAITaskEntry>(); //Stores the tasks
	
    public void addTask(int priority, AIBase task) { //adds a task to the ArrayList 'tasks'
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
    
    private boolean canContinue(AITasks.EntityAITaskEntry task) {
        return task.action.continueExecuting();
    }
	
    public class EntityAITaskEntry { //this subclass is used to create tasks
        public final AIBase action;
        public final int priority; //Unused for now
        public boolean using;

        public EntityAITaskEntry(int priorityIn, AIBase task) {
            this.priority = priorityIn;
            this.action = task;
        }
    }
}
