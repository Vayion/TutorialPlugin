package de.vayion.tutorial.commands;

import de.vayion.tutorial.TaskManager;
import de.vayion.tutorial.tasks.AbstractTask;
import de.vayion.tutorial.tasks.LabTask;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;
import org.jetbrains.annotations.NotNull;

public class SelectTask implements CommandExecutor
{

    private TaskManager taskManager;

    public SelectTask(TaskManager taskManager) {
        this.taskManager = taskManager;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(taskManager.isTaskActive()) {
            sender.sendMessage("Idiot!");

            return false;
        }
        if(sender instanceof Player && sender.isOp()) {
            Player player = (Player) sender;

            if(args.length >= 1) {
                String task = args[0];

                switch(task) {
                    case ("maze"):
                        AbstractTask mazeTask = new LabTask(new Vector(-3, 1, 2), taskManager.getMain().getArenaLoader().loadSchematic("maze"), new Vector(0,0,-4), taskManager.getMain().getArenaLoader(), taskManager.getMain().getArenaLoader().getPlayers());

                    break;

                    default:
                        AbstractTask defaultTask = new LabTask(new Vector(0, 0, -4), taskManager.getMain().getArenaLoader().loadSchematic("maze"), new Vector(0,0,-4), taskManager.getMain().getArenaLoader(), taskManager.getMain().getArenaLoader().getPlayers());
                        break;
                }
            }
        }
        return false;
    }
}
