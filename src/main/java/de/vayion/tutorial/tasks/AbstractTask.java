package de.vayion.tutorial.tasks;

import com.sk89q.worldedit.extent.clipboard.Clipboard;
import de.vayion.tutorial.ArenaLoader;
import de.vayion.tutorial.Tutorial;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractTask implements Listener {

    int max;
    Tutorial main;

    private ArenaLoader loader;
    public abstract void setup();

    private AbstractTask(Vector vector, Clipboard clipboard, Vector paste_offset, ArenaLoader arenaLoader, List<Player> players) {
        loader = arenaLoader;
        arenaLoader.getMain().getServer().getPluginManager().registerEvents(this, main);
    }
    public void end_challenge() {
        HandlerList.unregisterAll(this);
    }





}
