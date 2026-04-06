package de.vayion.tutorial.commands;

import de.vayion.tutorial.ArenaLoader;
import de.vayion.tutorial.TaskManager;
import de.vayion.tutorial.tasks.AbstractTask;
import de.vayion.tutorial.tasks.LabTask;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;
import org.jetbrains.annotations.NotNull;

public class GetRelativePos implements CommandExecutor {
    public ArenaLoader main;

    public GetRelativePos(ArenaLoader main) {
        this.main = main;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if(sender instanceof Player && sender.isOp()) {
            Player player = (Player) sender;

            for(int i = 0; i < main.getPlayers().size(); i++) {
                if(main.isInBounds(player.getLocation(), i)) {
                    Vector res = main.getRelativePosition(player.getLocation().toBlockLocation(), i);

                    sender.sendMessage("Hi, your location offset is "+ res.toString());
                }
            }
        }
        return false;
    }
}
