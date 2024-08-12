package com.heisenbergenigma.player;

import com.heisenbergenigma.items.Item;
import com.heisenbergenigma.main.MainGame;
import com.heisenbergenigma.utils.GameUtils;

import java.util.UUID;

/**
 * The Player class represents a player in the game. It includes player details,
 * inventory management, position, and methods to handle player movements and
 * interactions.
 */
public class Player {
    private static Player instance;

    private InventoryManager inventory;
    private String name;
    private UUID playerId; // Unique ID
    private int playerPosX, playerPosY;

    /**
     * Constructs a Player with a given name. Initializes the player's inventory
     * and sets their initial position in the game.
     *
     * @param name The name of the player.
     */
    public Player(String name) {
        this.name = name;
        this.playerId = UUID.randomUUID(); // Generate a unique ID
        this.inventory = new InventoryManager(this.playerId);
        instance = this;
        resetPosition();

    }

    /**
     * Resets the player's position to a default location.
     */
    public void resetPosition() {
        playerPosX = 2; // Set player's X-coordinate
        playerPosY = 1; // Set player's Y-coordinate
    }

    /**
     * Gets the InventoryManager for the player.
     *
     * @return The InventoryManager of the player.
     */
    public InventoryManager getInventoryManager() {
        return inventory;
    }

    /**
     * Adds an item to the player's inventory.
     *
     * @param item The item to add to the inventory.
     */
    public void addItemToInventory(Item item) {
        inventory.addItem(item);

    }

    /**
     * Clears all items from the player's inventory.
     */
    public void clearInventory() {
        inventory.clearInventory();
    }

    /**
     * Moves the player in a specified direction.
     *
     * @param direction The direction in which the player moves.
     */
    public void movePlayer(String direction) {
        switch (direction.toLowerCase()) {
            case "gn":
                playerPosX = Math.max(playerPosX - 1, 0);
                break; // Go North
            case "gs":
                playerPosX = Math.min(playerPosX + 1, 2);
                break; // Go South
            case "ge":
                playerPosY = Math.min(playerPosY + 1, 2);
                break; // Go East
            case "gw":
                playerPosY = Math.max(playerPosY - 1, 0);
                break; // Go West
            case "inventory":
                if (inventory != null) {
                    inventory.displayInventory();
                    GameUtils.promptToProceed();
                } else {
                    System.out.println("Inventory not available.");
                    GameUtils.promptToProceed();

                }
                break;

            default:
                System.out.println("Invalid direction.");

        }
    }

    /**
     * Prints the current position of the player.
     */
    public void printCurrentPosition() {
        System.out.println("You are in cell " + (playerPosX + 1) + "," + (playerPosY + 1));
    }

    /**
     * Gets the player's X-coordinate position.
     *
     * @return The X-coordinate of the player.
     */
    public int getPlayerPosX() {
        return playerPosX;
    }

    /**
     * Gets the player's Y-coordinate position.
     *
     * @return The Y-coordinate of the player.
     */
    public int getPlayerPosY() {
        return playerPosY;
    }

    /**
     * Gets the name of the player.
     *
     * @return The name of the player.
     */

    public String getName() {
        return name;
    }

    /**
     * Displays the contents of the player's inventory.
     */
    public void displayInventory() {
        inventory.displayInventory();
    }

    /**
     * Gets the unique identifier of the player.
     *
     * @return The UUID of the player.
     */
    public UUID getPlayerId() {
        return playerId;
    }

    /**
     * Returns the current instance of the Player.
     *
     * @return The current Player instance.
     */
    public static Player currentPlayer() {
        return instance;
    }
}
