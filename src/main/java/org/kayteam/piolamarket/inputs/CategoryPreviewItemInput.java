package org.kayteam.piolamarket.inputs;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.kayteam.api.input.inputs.DropInput;
import org.kayteam.piolamarket.PiolaMarket;
import org.kayteam.piolamarket.inventories.CategoriesManagerInventory;

public class CategoryPreviewItemInput implements DropInput {

    private final PiolaMarket piolaMarket;
    private final String categoryName;

    public CategoryPreviewItemInput(PiolaMarket piolaMarket, String categoryName) {
        this.piolaMarket = piolaMarket;
        this.categoryName = categoryName;
    }

    @Override
    public void onPLayerDrop(Player player, ItemStack itemStack) {
        piolaMarket.getMarketManager().createCategory(categoryName, itemStack);
        piolaMarket.getMessages().sendMessage(player, "categoryCreated", new String[][]{
                {"%category_name%", categoryName}
        });
        piolaMarket.getInventoryManager().openInventory(player, new CategoriesManagerInventory(piolaMarket, 1));
    }

    @Override
    public void onPlayerSneak(Player player) {
        piolaMarket.getInventoryManager().openInventory(player, new CategoriesManagerInventory(piolaMarket, 1));
        piolaMarket.getMessages().sendMessage(player, "categoryCreationCanceled");
    }

}