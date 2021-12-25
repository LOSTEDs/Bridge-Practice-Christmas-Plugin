package net.bridgepractice.christmasedition.items;

import net.bridgepractice.christmasedition.ChristmasEdition;
import org.bukkit.Effect;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class Commands implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String string, String[] args) {
        Player player = (Player) sender;
        // Fake Snow
        if (cmd.getName().equalsIgnoreCase("particleenable")) {
            new BukkitRunnable() {
                public void run() {
                    player.getWorld().spigot().playEffect(player.getLocation(), Effect.SNOW_SHOVEL, 0, 0, (float) 20, (float) 20, (float) 20, (float) 1, 20, 30);
                    player.getWorld().spigot().playEffect(player.getLocation(), Effect.SNOWBALL_BREAK, 0, 0, (float) 20, (float) 20, (float) 20, (float) 1, 10, 30);
                }
            }.runTaskTimer(ChristmasEdition.instance, 0, 1);
        }
        return false;
    }
}
