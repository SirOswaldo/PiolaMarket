package org.kayteam.piolamarket.inputs;

import org.bukkit.entity.Player;
import org.kayteam.api.input.inputs.ChatInput;
import org.kayteam.api.yaml.Yaml;
import org.kayteam.piolamarket.PiolaMarket;
import org.kayteam.piolamarket.inventories.CategoriesManagerInventory;

public class CategoryNameInput implements ChatInput {

    private final PiolaMarket piolaMarket;

    public CategoryNameInput(PiolaMarket piolaMarket) {
        this.piolaMarket = piolaMarket;
    }

    @Override
    public boolean onChatInput(Player player, String input) {
        Yaml messages = piolaMarket.getMessages();
        if (!input.contains(" ")) {
            piolaMarket.getInputManager().addInput(player, new CategoryPreviewItemInput(piolaMarket, input));
            messages.sendMessage(player, "categoryDisplayItem.input");
            return true;
        } else {
            messages.sendMessage(player, "categoryName.containSpace");
        }
        return false;
    }

    @Override
    public void onPlayerSneak(Player player) {
        piolaMarket.getInventoryManager().openInventory(player, new CategoriesManagerInventory(piolaMarket, 1));
        piolaMarket.getMessages().sendMessage(player, "categoryCreationCanceled");
    }

}