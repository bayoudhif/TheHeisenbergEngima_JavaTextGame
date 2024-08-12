package com.heisenbergenigma.locations;

import com.heisenbergenigma.puzzles.Puzzle;
import com.heisenbergenigma.npcs.NPC;


import java.util.HashMap;
import java.util.Map;

/**
 * Abstract class Location serves as a base for different locations in the game.
 * It provides a structure for the layout of a location, interaction mechanisms,
 * and location-specific descriptions.
 */


public abstract class Location {
    // Matrix representing the layout of the location

    protected Object[][] matrix;
    // Map storing descriptions for different parts of the location

    protected Map<String, String> locationDescriptions;


    /**
     * Constructs a Location with an empty 3x3 matrix and initializes
     * location descriptions.
     */
    public Location() {
        this.matrix = new Object[3][3];
        this.locationDescriptions = new HashMap<>();
        initializeLocationDescriptions();
    }

    /**
     * Abstract method to initialize location-specific descriptions.
     * Implementing classes must provide the descriptions for their respective locations.
     */
    protected abstract void initializeLocationDescriptions();

    /**
     * Interacts with the current cell based on the player's position.
     * It displays the description of the location and triggers interaction
     * with any NPC or Puzzle present in the cell.
     *
     * @param posX The X coordinate of the player's position.
     * @param posY The Y coordinate of the player's position.
     */
    protected void interactWithCurrentCell(int posX, int posY) {
        String key = posX + "," + posY;
        String description = locationDescriptions.getOrDefault(key, "You are in an unmarked area.");
        System.out.println(description);

        Object cellContent = matrix[posX][posY];
        if (cellContent instanceof NPC) {
            ((NPC) cellContent).interact();
        } else if (cellContent instanceof Puzzle) {
            Puzzle puzzle = (Puzzle) cellContent;
            if (!puzzle.isSolved()) {
                ((Puzzle) cellContent).solvePuzzle();
            }
        }
    }
}




