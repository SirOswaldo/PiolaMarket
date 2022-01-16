package org.kayteam.piolamarket.market;

public class Product {

    private final String name;

    private String category;

    private String displayName;
    private int currentAmount;
    private int maxAmount;
    private double minBuyPrice;
    private double maxBuyPrice;
    private double minSellPrice;
    private double maxSellPrice;

    public Product(String name, String displayName, int currentAmount, int maxAmount, double minBuyPrice, double maxBuyPrice, double minSellPrice, double maxSellPrice) {
        this.name = name;
        this.displayName = displayName;
        this.currentAmount = currentAmount;
        this.maxAmount = maxAmount;
        this.minBuyPrice = minBuyPrice;
        this.maxBuyPrice = maxBuyPrice;
        this.minSellPrice = minSellPrice;
        this.maxSellPrice = maxSellPrice;
    }

    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public int getCurrentAmount() {
        return currentAmount;
    }

    public void setCurrentAmount(int currentAmount) {
        this.currentAmount = currentAmount;
    }

    public int getMaxAmount() {
        return maxAmount;
    }

    public void setMaxAmount(int maxAmount) {
        this.maxAmount = maxAmount;
    }

    public double getMinBuyPrice() {
        return minBuyPrice;
    }

    public void setMinBuyPrice(double minBuyPrice) {
        this.minBuyPrice = minBuyPrice;
    }

    public double getMaxBuyPrice() {
        return maxBuyPrice;
    }

    public void setMaxBuyPrice(double maxBuyPrice) {
        this.maxBuyPrice = maxBuyPrice;
    }

    public double getMinSellPrice() {
        return minSellPrice;
    }

    public void setMinSellPrice(double minSellPrice) {
        this.minSellPrice = minSellPrice;
    }

    public double getMaxSellPrice() {
        return maxSellPrice;
    }

    public void setMaxSellPrice(double maxSellPrice) {
        this.maxSellPrice = maxSellPrice;
    }

}