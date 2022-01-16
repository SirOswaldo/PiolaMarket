package org.kayteam.piolamarket.commands;

import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.kayteam.api.command.SimpleCommand;
import org.kayteam.api.inventory.InventoryManager;
import org.kayteam.api.yaml.Yaml;
import org.kayteam.piolamarket.PiolaMarket;
import org.kayteam.piolamarket.inventories.MarketInventory;

public class MarketCommand extends SimpleCommand {

    private final PiolaMarket piolaMarket;

    public MarketCommand(PiolaMarket piolaMarket) {
        super("Market");
        this.piolaMarket = piolaMarket;
    }

    @Override
    public void onPlayerExecute(Player sender, String[] args) {
        Yaml messages = piolaMarket.getMessages();
        if (sender.hasPermission("piolamarket.market")) {
            InventoryManager inventoryManager = piolaMarket.getInventoryManager();
            inventoryManager.openInventory(sender, new MarketInventory(piolaMarket));
        } else {
            messages.sendMessage(sender, "noPermission");
        }
    }

    @Override
    public void onConsoleExecute(ConsoleCommandSender sender, String[] args) {
        Yaml messages = piolaMarket.getMessages();
        messages.sendMessage(sender, "onlyPlayers");
    }

}