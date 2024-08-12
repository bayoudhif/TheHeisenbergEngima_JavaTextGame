package com.heisenbergenigma.items;

/**
 * The Item class represents a general item with a name and a description.
 * It provides methods to retrieve the item's name and description.
 */
public class Item {
    // Name of the item

    private String name;
    // Description of the item

    private String description;

    /**
     * Constructs a new Item with the specified name and description.
     *
     * @param name        The name of the item.
     * @param description The description of the item.
     */
    public Item(String name, String description) {
        this.name = name;
        this.description = description;
    }

    /**
     * Returns the name of this item.
     *
     * @return The name of the item.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Returns the description of this item.
     *
     * @return The description of the item.
     */
    public String getDescription() {
        return this.description;
    }
}
