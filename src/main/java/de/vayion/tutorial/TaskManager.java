package de.vayion.tutorial;

import de.vayion.tutorial.commands.EndTask;
import de.vayion.tutorial.commands.SelectTask;
import de.vayion.tutorial.tasks.AbstractTask;

public class TaskManager {
    private Tutorial main;

    public AbstractTask currentTask;

    public TaskManager(Tutorial main) {
        this.main = main;
        main.getCommand("EndTask").setExecutor(new EndTask(this));
        main.getCommand("SelectTask").setExecutor(new SelectTask(this));
    }

    public Tutorial getMain() {
        return main;
    }

    public void stopTask() {
        if(currentTask != null) {
            currentTask.end_challenge();
        }
    }

    public boolean isTaskActive() {
        return currentTask != null;
    }

    public void setNewTask(AbstractTask newTask) {
        currentTask = newTask;
    }
}
