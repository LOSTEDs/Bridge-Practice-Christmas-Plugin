package net.bridgepractice.christmasedition;

import net.bridgepractice.christmasedition.items.Commands;
import net.bridgepractice.christmasedition.items.ItemManager;
import org.bukkit.plugin.java.JavaPlugin;

@SuppressWarnings("unused")
public final class ChristmasEdition extends JavaPlugin {

    public static ChristmasEdition instance;

    @Override
    public void onEnable() {
        Commands commands = new Commands();
        getServer().getPluginManager().registerEvents(new Listeners(), this);
        getCommand("particleenable").setExecutor(new Commands());
        ItemManager.init();
        instance = this;
    }

    @Override
    public void onDisable() {
        
    }
}
