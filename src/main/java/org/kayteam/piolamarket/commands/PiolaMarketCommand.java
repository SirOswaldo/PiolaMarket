package org.kayteam.piolamarket.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.kayteam.api.command.SimpleCommand;
import org.kayteam.api.inventory.InventoryManager;
import org.kayteam.api.yaml.Yaml;
import org.kayteam.piolamarket.PiolaMarket;
import org.kayteam.piolamarket.inventories.PiolaMarketInventory;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class PiolaMarketCommand extends SimpleCommand {

    private final PiolaMarket piolaMarket;

    public PiolaMarketCommand(PiolaMarket piolaMarket) {
        super("PiolaMarket");
        this.piolaMarket = piolaMarket;
    }

    @Override
    public void onPlayerExecute(Player sender, String[] args) {
        Yaml messages = piolaMarket.getMessages();
        if (sender.hasPermission("piolamarket.admin")) {
            InventoryManager inventoryManager = piolaMarket.getInventoryManager();
            inventoryManager.openInventory(sender, new PiolaMarketInventory(piolaMarket));
        } else {
            messages.sendMessage(sender, "noPermission");
        }
    }

    @Override
    public void onConsoleExecute(ConsoleCommandSender sender, String[] args) {
        Yaml messages = piolaMarket.getMessages();
        if (args.length > 0) {
            switch (args[0].toLowerCase()) {
                case "reload": {
                    piolaMarket.onReload();
                    messages.sendMessage(sender, "reloaded");
                    break;
                }
                case "version": {
                    List<String> message = new ArrayList<>();
                    message.add("");
                    message.add("&f>>");
                    message.add("&f>> &6" + piolaMarket.getDescription().getName());
                    message.add("&f>> &6Version &f" + piolaMarket.getDescription().getVersion());
                    message.add("&f>>");
                    message.add("&f>> &6Developed by KayTeam © 2020 - " + Calendar.getInstance().get(Calendar.YEAR) + ". All rights reserved.");
                    message.add("&f>> &f" + piolaMarket.getDescription().getWebsite());
                    message.add("&f>>");
                    message.add("");
                    for(String line : message) sender.sendMessage(ChatColor.translateAlternateColorCodes('&', line));
                    break;
                }
                default: {
                    messages.sendMessage(sender, "invalidArgument", new String[][]{
                            {"%argument%", args[0]}
                    });
                }
            }
        } else {
            messages.sendMessage(sender, "emptyArgument");
        }
    }

}