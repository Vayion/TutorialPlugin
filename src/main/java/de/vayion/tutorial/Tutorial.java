package de.vayion.tutorial;

import org.bukkit.plugin.java.JavaPlugin;

public final class Tutorial extends JavaPlugin {

    private ArenaLoader arenaLoader;
    private TaskManager taskManager;
    @Override
    public void onEnable() {
        arenaLoader = new ArenaLoader(this);
        taskManager = new TaskManager(this);

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public ArenaLoader getArenaLoader() {
        return arenaLoader;
    }

    public TaskManager getTaskManager() {
        return taskManager;
    }
}
