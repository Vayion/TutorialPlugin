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

    protected ArenaLoader loader;

    AbstractTask(Vector vector, Clipboard clipboard, Vector paste_offset, ArenaLoader arenaLoader, List<Player> players) {

        loader = arenaLoader;
        arenaLoader.getMain().getServer().getPluginManager().registerEvents(this, loader.getMain());



        for (int i = 0; i < players.size(); i++) {
            arenaLoader.resetArena(i);
            arenaLoader.pasteCentered(clipboard, loader.getOffset(paste_offset.getBlockX(), paste_offset.getBlockY(), paste_offset.getBlockZ(), i));

            players.get(i).teleport(loader.getOffset(vector.getBlockX(), vector.getBlockY(), vector.getBlockZ(), i).add(new Vector(0.5, 0, 0.5)));
        }
    }
    public void end_challenge() {
        HandlerList.unregisterAll(this);
    }





}
