package net.bridgepractice.christmasedition.items;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class ItemManager {

    public static ItemStack stick;

    public static void init() {
        createStick();
    }

    public static void createStick() {
        ItemStack itemStack = new ItemStack(Material.STICK, 1);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName("§6Magic Stick");
        List<String> lore = new ArrayList<>();
        lore.add("'What... What is this?'");
        lore.add("'A - a magic stick?' - LOSTED");
        lore.add("§6§lHINT: Right Click!");
        itemMeta.setLore(lore);
        itemMeta.addEnchant(Enchantment.LUCK, 1, false);
        itemMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        itemStack.setItemMeta(itemMeta);
        stick = itemStack;
    }

}
