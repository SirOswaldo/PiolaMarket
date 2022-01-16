package org.kayteam.piolamarket.market;

import org.bukkit.inventory.ItemStack;
import org.kayteam.api.yaml.Yaml;
import org.kayteam.piolamarket.PiolaMarket;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.List;

public class MarketManager {

    private final PiolaMarket piolaMarket;
    private final List<Category> categories = new ArrayList<>();
    private final List<Product> products = new ArrayList<>();

    public MarketManager(PiolaMarket piolaMarket) {
        this.piolaMarket = piolaMarket;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void createCategory(String categoryName, ItemStack displayItem) {
        Category category = new Category(categoryName, displayItem, -1);
        categories.add(category);
        saveCategory(category);
    }

    public void saveCategories() {
        for (Category category : categories) {
            saveCategory(category);
        }
    }

    public void saveCategory(Category category) {
        Yaml yaml = new Yaml(piolaMarket, "categories", category.getName());
        yaml.registerFileConfiguration();
        yaml.set("marketSlot", category.getMarketSlot());
        yaml.set("displayItem", category.getDisplayItem());
        yaml.saveFileConfiguration();
    }

    public void loadCategories() {
        categories.clear();
        File directory = new File(piolaMarket.getDataFolder() + File.separator + "categories");
        if (directory.exists()) {
            if (directory.isDirectory()) {
                File[] categoriesFiles = directory.listFiles((dir, name) -> name.endsWith(".yml"));
                if (categoriesFiles != null) {
                    for (File categoryFile : categoriesFiles) {
                        loadCategory(categoryFile.getName().replaceAll(".yml", ""));
                    }
                }
            }
        }
    }

    public void loadCategory(String name) {
        Yaml yaml = new Yaml(piolaMarket, "categories", name);
        yaml.registerFileConfiguration();
        int marketSlot = yaml.getInt("marketSlot");
        ItemStack previewItem = yaml.getFileConfiguration().getItemStack("displayItem");
        Category category = new Category(name, previewItem, marketSlot);
        categories.add(category);
        //TODO Añadir los productos
    }

    public void deleteCategory(Category category) {
        for (Product product : products) {
            if (product.getCategory().contains(category.getName())) {
                product.setCategory("undefined");
            }
        }
        categories.remove(category);
        Yaml yaml = new Yaml(piolaMarket, "categories", category.getName());
        yaml.deleteFileConfiguration();
    }

}