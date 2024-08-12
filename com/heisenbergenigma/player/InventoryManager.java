package com.heisenbergenigma.player;

import com.heisenbergenigma.items.Item;
import com.heisenbergenigma.utils.GameUtils;

import java.util.List;
import java.util.UUID;
import java.util.ArrayList;

/**
 * The InventoryManager class is responsible for managing the inventory of a player.
 * It allows for adding items to the inventory, displaying the inventory, and
 * clearing the inventory. Each inventory is associated with a unique player ID.
 */
public class InventoryManager {
    private List<Item> inventory;
    private UUID playerUniqueId;

    /**
     * Constructs an InventoryManager for a specific player.
     *
     * @param playerUniqueId The unique identifier for the player.
     */
    public InventoryManager(UUID playerUniqueId) {
        this.playerUniqueId = playerUniqueId;
        inventory = new ArrayList<>();
    }

    /**
     * Adds an item to the player's inventory.
     *
     * @param item The item to be added to the inventory.
     */
    public void addItem(Item item) {
        inventory.add(item);
    }

    /**
     * Displays the items in the player's inventory. If the inventory is empty,
     * it indicates so to the player.
     */
    public void displayInventory() {
        if (inventory.isEmpty()) {
            System.out.println("Your inventory is empty.");
            return;
        }
        System.out.println("Inventory Items:");
        for (Item item : inventory) {
            System.out.println(GameUtils.ANSI_PURPLE+"- " + item.getName() + ": " + item.getDescription()+GameUtils.ANSI_RESET);
        }
    }

    /**
     * Clears all items from the player's inventory.
     */
    public void clearInventory() {
        inventory.clear();
    }

    /**
     * Returns the unique identifier of the player associated with this inventory.
     *
     * @return The UUID of the player.
     */
    public UUID getPlayerUniqueId() {
        return playerUniqueId;
    }
}