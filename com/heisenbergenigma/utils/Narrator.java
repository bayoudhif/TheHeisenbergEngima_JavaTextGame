package com.heisenbergenigma.utils;

/**
 * The Narrator class provides a static method for narrating messages within the game.
 * It is used to display text to the player, aiding in storytelling and providing
 * information about game events, instructions, and narrative elements.
 */
public class Narrator {
    /**
     * Prints a narrated message to the console.
     * This method is used to convey information, story elements, or instructions to the player.
     *
     * @param message The message to be narrated.
     */
    public static void narrate(String message) {
        System.out.println(GameUtils.ANSI_PURPLE + message + GameUtils.ANSI_RESET);
    }

}
