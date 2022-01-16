package org.kayteam.piolamarket.market;

import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class Category {

    private final String name;
    private ItemStack displayItem;
    private int marketSlot;

    private final List<Product> products = new ArrayList<>();

    public Category(String name) {
        this.name = name;
    }

    public Category(String name, ItemStack displayItem, int marketSlot) {
        this.name = name;
        this.displayItem = displayItem;
        this.marketSlot = marketSlot;
    }

    public String getName() {
        return name;
    }

    public ItemStack getDisplayItem() {
        return displayItem;
    }

    public void setDisplayItem(ItemStack displayItem) {
        this.displayItem = displayItem;
    }

    public int getMarketSlot() {
        return marketSlot;
    }

    public void setMarketSlot(int marketSlot) {
        this.marketSlot = marketSlot;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void loadProducts(List<Product> products) {
        this.products.clear();
        for (Product product : products) addProduct(product);
    }

    public void addProduct(Product product) {
        if (product.getCategory().equals(name)) products.add(product);
    }

}