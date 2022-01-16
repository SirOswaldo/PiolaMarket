package org.kayteam.piolamarket.inventories;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.kayteam.api.inventory.InventoryBuilder;
import org.kayteam.api.yaml.Yaml;
import org.kayteam.piolamarket.PiolaMarket;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class PiolaMarketInventory extends InventoryBuilder {

    public PiolaMarketInventory(PiolaMarket piolaMarket) {
        super(piolaMarket.getInventories().getString("piolaMarket.title"), 5);
        Yaml inventories = piolaMarket.getInventories();
        fillItem(() -> inventories.getItemStack("piolaMarket.items.panel"));

        addItem(10, () -> inventories.getItemStack("piolaMarket.items.close"));
        addLeftAction(10, (player, slot) -> player.closeInventory());
        addItem(19, () -> {
            ItemStack brandItem = new ItemStack(Material.PAPER);
            ItemMeta brandMeta = brandItem.getItemMeta();
            brandMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&f>>"));
            List<String> lore = new ArrayList<>();
            lore.add(ChatColor.translateAlternateColorCodes('&', "&f>> &6" + piolaMarket.getDescription().getName()));
            lore.add(ChatColor.translateAlternateColorCodes('&', "&f>> &6Version &f" + piolaMarket.getDescription().getVersion()));
            lore.add(ChatColor.translateAlternateColorCodes('&', "&f>>"));
            lore.add(ChatColor.translateAlternateColorCodes('&', "&f>> &6Developed by KayTeam © 2020 - " + Calendar.getInstance().get(Calendar.YEAR) + ". All rights reserved."));
            lore.add(ChatColor.translateAlternateColorCodes('&', "&f>> &f" + piolaMarket.getDescription().getWebsite()));
            lore.add(ChatColor.translateAlternateColorCodes('&', "&f>>"));
            brandMeta.setLore(lore);
            brandItem.setItemMeta(brandMeta);
            return brandItem;
        });
        addItem(28, () -> inventories.getItemStack("piolaMarket.items.reload"));
        addLeftAction(28, (player, slot) -> {
            player.closeInventory();
            piolaMarket.onReload();
            piolaMarket.getMessages().sendMessage(player, "reloaded");
        });

        addItem(12, () -> inventories.getItemStack("piolaMarket.items.separator"));
        addItem(21, () -> inventories.getItemStack("piolaMarket.items.separator"));
        addItem(30, () -> inventories.getItemStack("piolaMarket.items.separator"));

        addItem(23, () -> inventories.getItemStack("piolaMarket.items.categories"));
        addLeftAction(23, (player, slot) -> piolaMarket.getInventoryManager().openInventory(player, new CategoriesManagerInventory(piolaMarket, 1)));
        addItem(25, () -> inventories.getItemStack("piolaMarket.items.products"));
    }

}