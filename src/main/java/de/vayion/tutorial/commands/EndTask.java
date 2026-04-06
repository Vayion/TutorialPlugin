package de.vayion.tutorial.commands;

import de.vayion.tutorial.TaskManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class EndTask implements CommandExecutor
{

    private TaskManager taskManager;

    public EndTask(TaskManager taskManager) {
        this.taskManager = taskManager;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if (sender instanceof Player && ((Player) sender).isOp()) {
            taskManager.stopTask();
        }
        return false;
    }
}
