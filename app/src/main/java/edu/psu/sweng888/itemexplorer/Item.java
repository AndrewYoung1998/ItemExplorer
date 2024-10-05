package edu.psu.sweng888.itemexplorer;

import java.io.Serializable;

public class Item implements Serializable {
    private final String name;
    private final String description;
    private final String imageUrl;

    public Item(String name, String description, String imageUrl) {
        this.name = name;
        this.description = description;
        this.imageUrl = imageUrl;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getImageUrl() {
        return imageUrl;
    }
}

