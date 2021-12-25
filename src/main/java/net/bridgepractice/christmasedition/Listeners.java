package net.bridgepractice.christmasedition;

import net.bridgepractice.christmasedition.items.ItemManager;
import net.minecraft.server.v1_8_R3.IChatBaseComponent;
import net.minecraft.server.v1_8_R3.PacketPlayOutChat;
import org.bukkit.*;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowball;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class Listeners implements Listener {
    @EventHandler
    public void onPlayerJoinEvent(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        // It doesn't work. . . So I put it in the Move Event!
        player.getInventory().setItem(5, ItemManager.stick);
    }

    @EventHandler
    @SuppressWarnings("deprecation")
    public void onEntityDamageByEntityEvent(EntityDamageByEntityEvent e){
        if (e.getDamager() instanceof Snowball) {
            Player player = (Player) e.getEntity();
            CraftPlayer craftPlayer = (CraftPlayer) player;
            IChatBaseComponent iChatBaseComponent = IChatBaseComponent.ChatSerializer.a("{\"text\": \"§4Ouch! You got hit by a Snowball!\"}");
            PacketPlayOutChat packetPlayOutChat = new PacketPlayOutChat(iChatBaseComponent, (byte) 2);
            craftPlayer.getHandle().playerConnection.sendPacket(packetPlayOutChat);
            Location location = player.getLocation();
            player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 4 * 5, 255));
            player.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 4 * 8, 255));
            for (Player players : Bukkit.getOnlinePlayers()) {
                players.playSound(location, Sound.EXPLODE, 1, 1);
                players.playEffect(location, Effect.EXPLOSION_HUGE, 1);
            }
        }
    }

    @EventHandler
    public void onPlayerInteractEvent(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        Location location = player.getLocation();
        if (event.getAction().equals(Action.RIGHT_CLICK_AIR) || event.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
            ItemStack itemStack = event.getItem();
            if (itemStack.getType().equals(Material.STICK)) {
                Snowball snowball = player.getWorld().spawn(player.getEyeLocation(), Snowball.class);
                snowball.setShooter(player);
                snowball.setVelocity(player.getLocation().getDirection().multiply(1.5));
                player.playSound(location, Sound.PISTON_EXTEND, 1, 1);
            }
        }
    }

    @EventHandler
    @SuppressWarnings("deprecation")
    public void onPlayerMoveEvent(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        player.getInventory().setItem(5, ItemManager.stick);
        if (player.getLocation().getBlock().getLightFromSky() >= 14) {
            if (!player.getLocation().getBlock().getType().isSolid() && player.isOnGround() && Math.round(player.getVelocity().getY()) == 0 && player.getLocation().clone().subtract(0, 1.0, 0).getBlock().getType().isSolid()) {
                player.getLocation().getBlock().setType(Material.SNOW);
            }
        }
    }
}
