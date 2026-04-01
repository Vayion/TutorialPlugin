package de.vayion.tutorial.commands;

import de.vayion.tutorial.ArenaLoader;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.stream.Collectors;

public class StartCmd implements CommandExecutor {

    private ArenaLoader arenaLoader;
    public StartCmd(ArenaLoader arenaLoader) {
        this.arenaLoader = arenaLoader;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        List<Player> players = Bukkit.getOnlinePlayers().stream().filter(player -> !player.isOp()).collect(Collectors.toUnmodifiableList());
        if (commandSender instanceof Player) {
            Location start = new Location(((Player) commandSender).getLocation().getWorld(), 0, 100, 0);
            arenaLoader.start(players, start);
        }


        return true;
    }
}
