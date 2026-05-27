package de.vayion.tutorial.commands;

import de.vayion.tutorial.Tutorial;
import de.vayion.tutorial.tools.Locker;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class Lock implements CommandExecutor {

    private Locker locker = null;

    private Tutorial main;
    private boolean locked = false;

    public Lock(Tutorial main) {
        this.main = main;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(!(sender instanceof Player) || ((Player) sender).isOp()) {
            if (locked) {
                locker.disable();
                locker = null;
                locked = false;
            }
            else {
                main.getServer().getPluginManager().registerEvents(locker = new Locker(), main);
                locked = true;
            }
        }

        return false;
    }
}
