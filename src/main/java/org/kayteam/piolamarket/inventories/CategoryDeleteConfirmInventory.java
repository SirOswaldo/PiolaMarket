package org.kayteam.piolamarket.inventories;

import org.kayteam.api.inventory.InventoryBuilder;
import org.kayteam.api.yaml.Yaml;
import org.kayteam.piolamarket.PiolaMarket;
import org.kayteam.piolamarket.market.Category;

public class CategoryDeleteConfirmInventory extends InventoryBuilder {

    public CategoryDeleteConfirmInventory(PiolaMarket piolaMarket, Category category) {
        super(piolaMarket.getInventories().getString("categoryDeleteConfirm.title"), 3);

        Yaml inventories = piolaMarket.getInventories();

        fillItem(() -> inventories.getItemStack("categoryDeleteConfirm.items.panel"));

        addItem(10, () -> inventories.getItemStack("categoryDeleteConfirm.items.cancel"));
        addLeftAction(10, (player, slot) -> piolaMarket.getInventoryManager().openInventory(player, new CategoryEditorInventory(piolaMarket, category)));

        addItem(13, () -> Yaml.replace(inventories.getItemStack("categoryDeleteConfirm.items.information"), new String[][]{
                {"%category_name%", category.getName()},
                {"%category_products_amount%", category.getProducts().size() + ""}
        }));

        addItem(16, () -> inventories.getItemStack("categoryDeleteConfirm.items.accept"));
        addLeftAction(16, (player, slot) -> {
            piolaMarket.getMarketManager().deleteCategory(category);
            piolaMarket.getMessages().sendMessage(player, "categoryDeleted", new String[][]{
                    {"%category_name%", category.getName()}
            });
            piolaMarket.getInventoryManager().openInventory(player, new CategoriesManagerInventory(piolaMarket, 1));
        });
    }

}