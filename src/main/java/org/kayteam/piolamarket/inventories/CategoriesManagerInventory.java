package org.kayteam.piolamarket.inventories;

import org.kayteam.api.inventory.InventoryBuilder;
import org.kayteam.api.yaml.Yaml;
import org.kayteam.piolamarket.PiolaMarket;
import org.kayteam.piolamarket.inputs.CategoryNameInput;
import org.kayteam.piolamarket.market.Category;

import java.util.List;

public class CategoriesManagerInventory extends InventoryBuilder {

    public CategoriesManagerInventory(PiolaMarket piolaMarket, int page) {
        super(piolaMarket.getInventories().getString("categoriesManager.title"), piolaMarket.getInventories().getInt("categoriesManager.rows", 1) + 2);
        Yaml inventories = piolaMarket.getInventories();
        int rows = inventories.getInt("categoriesManager.rows", 1);

        fillItem(() -> inventories.getItemStack("categoriesManager.items.panel"), new int[]{1, rows + 2});

        List<Category> categories = piolaMarket.getMarketManager().getCategories();
        for (int i = 9; i < (rows + 2) * 9; i++) {
            int index = ((page * (rows * 9)) - (rows * 9)) + (i - 9);
            if (index < categories.size()) {
                Category category = categories.get(index);
                addItem(i, category::getDisplayItem);
                addLeftAction(i, (player, slot) -> piolaMarket.getInventoryManager().openInventory(player, new CategoryEditorInventory(piolaMarket, category)));
            }
        }

        // Previous page
        if (page > 1) {
            addItem(((rows + 2) * 9) - 9, () -> inventories.getItemStack("categoriesManager.items.previousPage"));
            addLeftAction(((rows + 2) * 9) - 9, (player, slot) -> piolaMarket.getInventoryManager().openInventory(player, new CategoriesManagerInventory(piolaMarket, page - 1)));
        }
        // Return
        addItem(((rows + 2) * 9) - 6, () -> inventories.getItemStack("categoriesManager.items.return"));
        addLeftAction(((rows + 2) * 9) - 6, (player, slot) -> piolaMarket.getInventoryManager().openInventory(player, new PiolaMarketInventory(piolaMarket)));
        // Close
        addItem(((rows + 2) * 9) - 5, () -> inventories.getItemStack("categoriesManager.items.close"));
        addLeftAction(((rows + 2) * 9) - 5, (player, slot) -> player.closeInventory());
        // Create
        addItem(((rows + 2) * 9) - 4, () -> inventories.getItemStack("categoriesManager.items.createCategory"));
        addLeftAction(((rows + 2) * 9) - 4, (player, slot) -> {
            player.closeInventory();
            piolaMarket.getInputManager().addInput(player, new CategoryNameInput(piolaMarket));
            piolaMarket.getMessages().sendMessage(player, "categoryName.input");
        });
        // Next page
        if (categories.size() > (page * (4 * 9))) {
            addItem(((rows + 2) * 9) - 1, () -> inventories.getItemStack("categoriesManager.items.nextPage"));
            addLeftAction(((rows + 2) * 9) - 9, (player, slot) -> piolaMarket.getInventoryManager().openInventory(player, new CategoriesManagerInventory(piolaMarket, page + 1)));
        }
    }

}