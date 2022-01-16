package org.kayteam.piolamarket;

import net.milkbowl.vault.economy.Economy;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;
import org.kayteam.api.BrandSender;
import org.kayteam.api.input.InputManager;
import org.kayteam.api.inventory.InventoryManager;
import org.kayteam.api.yaml.Yaml;
import org.kayteam.piolamarket.commands.MarketCommand;
import org.kayteam.piolamarket.commands.PiolaMarketCommand;
import org.kayteam.piolamarket.market.MarketManager;

public final class PiolaMarket extends JavaPlugin {

    private final Yaml settings = new Yaml(this, "settings");
    private final Yaml messages = new Yaml(this, "messages");
    private final Yaml inventories = new Yaml(this, "inventories");

    private final InventoryManager inventoryManager = new InventoryManager(this);
    private final InputManager inputManager = new InputManager();

    private final MarketManager marketManager = new MarketManager(this);

    private static Economy economy = null;

    @Override
    public void onEnable() {
        if (!setupEconomy()) {
            getLogger().severe(String.format("[%s] - Disabled due to no Vault dependency found!", getDescription().getName()));
            getServer().getPluginManager().disablePlugin(this);
        } else {
            registerFiles();
            registerListeners();
            registerCommands();
            BrandSender.sendBrandMessage(this, "&aEnabled");
        }
    }

    @Override
    public void onDisable() {
        BrandSender.sendBrandMessage(this, "&cDisabled");
    }

    public void onReload() {
        settings.reloadFileConfiguration();
        messages.reloadFileConfiguration();
        inventories.reloadFileConfiguration();
        marketManager.saveCategories();
        marketManager.loadCategories();
    }

    private void registerFiles() {
        settings.registerFileConfiguration();
        messages.registerFileConfiguration();
        inventories.registerFileConfiguration();
    }

    private void registerCommands() {
        new PiolaMarketCommand(this).registerCommand(this);
        new MarketCommand(this).registerCommand(this);
    }

    private void registerListeners() {
        PluginManager pluginManager = getServer().getPluginManager();
        pluginManager.registerEvents(inventoryManager, this);
        pluginManager.registerEvents(inputManager, this);
    }

    public Yaml getSettings() {
        return settings;
    }

    public Yaml getMessages() {
        return messages;
    }

    public Yaml getInventories() {
        return inventories;
    }

    public InventoryManager getInventoryManager() {
        return inventoryManager;
    }

    public InputManager getInputManager() {
        return inputManager;
    }

    public MarketManager getMarketManager() {
        return marketManager;
    }

    private boolean setupEconomy() {
        if (getServer().getPluginManager().getPlugin("Vault") == null) {
            return false;
        }
        RegisteredServiceProvider<Economy> registeredServiceProvider = getServer().getServicesManager().getRegistration(Economy.class);
        if (registeredServiceProvider == null) {
            return false;
        }
        economy = registeredServiceProvider.getProvider();
        return economy != null;
    }

    public static Economy getEconomy() {
        return economy;
    }

}