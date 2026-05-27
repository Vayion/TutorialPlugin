package de.vayion.tutorial.tasks;

import com.sk89q.worldedit.extent.clipboard.Clipboard;
import de.vayion.tutorial.ArenaLoader;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.util.Vector;

import java.util.List;

public class LabTask extends AbstractTask implements Listener{

    public LabTask(Vector vector, Clipboard clipboard, Vector paste_offset, ArenaLoader arenaLoader, List<Player> players) {
        super(vector, clipboard, paste_offset, arenaLoader, players);
    }


    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        event.setCancelled(true);
    }


    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event) {
        event.setCancelled(true);
    }

    @EventHandler
    public void onPlayerMoveEvent(PlayerMoveEvent event) {
        int id = loader.getMain().getArenaLoader().getPlayerID(event.getPlayer());



    }
}
