package org.kayteam.piolamarket.inventories;

import org.kayteam.api.inventory.InventoryBuilder;
import org.kayteam.api.yaml.Yaml;
import org.kayteam.piolamarket.PiolaMarket;
import org.kayteam.piolamarket.market.Category;

import java.util.List;

public class MarketInventory extends InventoryBuilder {

    public MarketInventory(PiolaMarket piolaMarket) {
        super(piolaMarket.getInventories().getString("market.title"), 6);
        Yaml inventories = piolaMarket.getInventories();
        // Categorías
        List<Category> categories = piolaMarket.getMarketManager().getCategories();
        for (Category category : categories) {
            int slot = category.getMarketSlot();
            if (slot > -1 && slot < 45) {
                addItem(slot, category::getDisplayItem);
                // Open Category
                //TODO abrir la categoria con todos los articulos
            }
        }

        fillItem(() -> inventories.getItemStack("market.items.panel"), new int[] {6});
        addItem(50, () -> inventories.getItemStack("market.items.statisticsSeller"));
        addItem(49, () -> inventories.getItemStack("market.items.close"));
        addLeftAction(49, (player, slot) -> player.closeInventory());
        addItem(50, () -> inventories.getItemStack("market.items.statisticsBuyer"));
    }

}