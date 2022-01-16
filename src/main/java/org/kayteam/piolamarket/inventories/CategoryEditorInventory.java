package org.kayteam.piolamarket.inventories;

import org.kayteam.api.inventory.InventoryBuilder;
import org.kayteam.api.yaml.Yaml;
import org.kayteam.piolamarket.PiolaMarket;
import org.kayteam.piolamarket.market.Category;

public class CategoryEditorInventory extends InventoryBuilder {

    public CategoryEditorInventory(PiolaMarket piolaMarket, Category category) {
        super(piolaMarket.getInventories().getString("categoryEditor.title"), 5);
        Yaml inventories = piolaMarket.getInventories();

        fillItem(() -> inventories.getItemStack("categoryEditor.items.panel"));

        addItem(10, () -> inventories.getItemStack("categoryEditor.items.return"));
        addLeftAction(10, (player, slot) -> piolaMarket.getInventoryManager().openInventory(player, new CategoriesManagerInventory(piolaMarket, 1)));

        addLeftAction(19, (player, slot) -> player.closeInventory());
        addItem(19, () -> inventories.getItemStack("categoryEditor.items.close"));

        addItem(28, () -> inventories.getItemStack("categoryEditor.items.delete"));
        addLeftAction(28, (player, slot) -> piolaMarket.getInventoryManager().openInventory(player, new CategoryDeleteConfirmInventory(piolaMarket, category)));

        addItem(12, () -> inventories.getItemStack("categoryEditor.items.separator"));
        addItem(21, () -> inventories.getItemStack("categoryEditor.items.separator"));
        addItem(30, () -> inventories.getItemStack("categoryEditor.items.separator"));

        addItem(23, category::getDisplayItem);

        addItem(25, () -> Yaml.replace(inventories.getItemStack("categoryEditor.items.products"), new String[][]{
                {"%products_amount%", category.getProducts().size() + ""}
        }));
    }

}