package de.vayion.tutorial;

import de.vayion.tutorial.commands.Lock;
import org.bukkit.plugin.java.JavaPlugin;

public final class Tutorial extends JavaPlugin {

    private ArenaLoader arenaLoader;
    private TaskManager taskManager;
    private Lock lock;

    @Override
    public void onEnable() {
        arenaLoader = new ArenaLoader(this);
        taskManager = new TaskManager(this);
        this.getCommand("lock").setExecutor(lock = new Lock(this));

        this.getServer().getPluginManager().registerEvents(new JoinListeners(), this);

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
