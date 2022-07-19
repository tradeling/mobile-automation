package com.tradeling.data.buyerApp;

public enum appSection {
    HOME("Home"),
    CATEGORIES("Categories"),
    DEALS("Deals"),
    ACCOUNT("MyAccount"),
    Cart("MyCart");

    private final String text;


    appSection(final String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }
}