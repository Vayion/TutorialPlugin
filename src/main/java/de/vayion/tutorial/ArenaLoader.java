package de.vayion.tutorial;

import com.sk89q.worldedit.EditSession;
import com.sk89q.worldedit.WorldEdit;
import com.sk89q.worldedit.WorldEditException;
import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldedit.extent.clipboard.Clipboard;
import com.sk89q.worldedit.extent.clipboard.io.ClipboardFormat;
import com.sk89q.worldedit.extent.clipboard.io.ClipboardFormats;
import com.sk89q.worldedit.extent.clipboard.io.ClipboardReader;
import com.sk89q.worldedit.function.operation.Operation;
import com.sk89q.worldedit.function.operation.Operations;
import com.sk89q.worldedit.math.BlockVector3;
import com.sk89q.worldedit.session.ClipboardHolder;
import de.vayion.tutorial.commands.StartCmd;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.util.Vector;
import org.w3c.dom.stylesheets.LinkStyle;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

public class ArenaLoader implements Listener {
    private int loaded_arenas = 0;

    private List<Player> players;

    private Location anchor;
    private Tutorial main;

    private final int X_LENGTH = 23;
    private final int Y_LENGTH = 31;

    private int Z_MIN = 100;

    public ArenaLoader(Tutorial main) {
        this.main = main;
        main.getServer().getPluginManager().registerEvents(this, main);
        main.getCommand("start").setExecutor(new StartCmd(this));
    }

    public Location getOffset(int x, int y, int z, int index) {
        Location offset = anchor.clone();
        offset.add(x, y, z);
        offset.add((int)((X_LENGTH+1)/2)+index*X_LENGTH, 2, (int)((Y_LENGTH+1)/2));
        return offset;
    }

    public void pasteCentered(Clipboard clipboard, Location targetLocation) {
        BlockVector3 centerOffset = clipboard.getRegion().getCenter().toBlockPoint();
        clipboard.setOrigin(centerOffset);

        try (EditSession editSession = WorldEdit.getInstance().newEditSession(BukkitAdapter.adapt(targetLocation.getWorld()))) {
            Operation operation = new ClipboardHolder(clipboard)
                    .createPaste(editSession)
                    .to(BlockVector3.at(targetLocation.getX(), targetLocation.getY(), targetLocation.getZ()))
                    .ignoreAirBlocks(false)
                    .build();

            Operations.complete(operation);
        } catch (WorldEditException e) {
            e.printStackTrace();
        }
    }

    public void start(List<Player> _players, Location startLocation) {

        Z_MIN = startLocation.getBlockZ();
        anchor = startLocation;
        players = _players;
        Clipboard clipboard = loadSchematic("Arena");
        if(clipboard == null) {
            return;
        }



        for (int i = 0; i < players.size(); i++) {
            Player player = players.get(i);

            pasteCentered(clipboard, getOffset(0,3,0, i));
            player.teleport(getOffset(0, 0, 0, i));
        }
    }


    public Clipboard loadSchematic(String schem) {

        File file = new File("plugins/WorldEdit/schematics/"+schem+".schem");
        if (!file.exists()) {
            System.out.println("Error: Arena.schem not found in plugins/WorldEdit/schematics/");
            return null;
        }

        ClipboardFormat format = ClipboardFormats.findByFile(file);
        if (format == null) return null;

        try (ClipboardReader reader = format.getReader(new FileInputStream(file))) {
            Clipboard clipboard = reader.read();

            try (EditSession editSession = WorldEdit.getInstance().newEditSession(BukkitAdapter.adapt(anchor.getWorld()))) {

                return clipboard;

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public int getPlayerID(Player player) {
        for (int i = 0; i < players.size(); i++) {
            if (player.equals(players.get(i))) {
                return i;
            }
        }
        return -1;
    }


    public void reset(int index) {
        Clipboard clipboard = loadSchematic("Arena");
        if(clipboard == null) {
            return;
        }

        pasteCentered(clipboard, getOffset(0,3,0, index));
    }

    public boolean isInBounds(Location loc, int index) {
        Vector max = getOffset(6,15,10, index).toVector();
        Vector min = getOffset(-6,-2,-10, index).toVector();

        return loc.toVector().isInAABB(min, max);
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        if (event.getPlayer().isOp()) return;
        if (getPlayerID(event.getPlayer()) == -1) return;
        event.setCancelled(!isInBounds(event.getBlock().getLocation(), getPlayerID(event.getPlayer())));
    }

    @EventHandler
    public void onPlaceBreak(BlockPlaceEvent event) {
        if (event.getPlayer().isOp()) return;
        if (getPlayerID(event.getPlayer()) == -1) return;
        event.setCancelled(!isInBounds(event.getBlock().getLocation(), getPlayerID(event.getPlayer())));
    }

    public Location getAnchor() {
        return anchor;
    }

    public Tutorial getMain() {
        return main;
    }
}
