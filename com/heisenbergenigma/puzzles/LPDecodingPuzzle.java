package com.heisenbergenigma.puzzles;

import com.heisenbergenigma.main.GameState;
import com.heisenbergenigma.main.MainGame;
import com.heisenbergenigma.player.Player;
import com.heisenbergenigma.ui.UiManager;
import com.heisenbergenigma.utils.GameUtils;
import com.heisenbergenigma.utils.Narrator;
import com.heisenbergenigma.items.Item;

/**
 * The LPDecodingPuzzle class extends the Puzzle class and represents a puzzle
 * where the player needs to decode a series of cryptic license plates. Each
 * license plate hides a secret message that must be decoded to solve the puzzle.
 */
public class LPDecodingPuzzle extends Puzzle {
    private final String[] licensePlates = {"SAU1L", "L4WY3R", "B3TTR"};
    private final String[] decodedWords = {"SAUL", "LAWYER", "BETTER"};

    /**
     * Constructs a new LPDecodingPuzzle with predefined license plates and
     * their decoded words.
     */
    public LPDecodingPuzzle() {
        super("License Plate Decoding Puzzle");
    }

    /**
     * Solves the license plate decoding puzzle. The player is presented with
     * a series of cryptic license plates and must decode the hidden messages
     * correctly. Successfully decoding the messages grants a reward and
     * advances the game state.
     *
     * @return boolean indicating whether the puzzle was successfully solved.
     */
    @Override
    public boolean solvePuzzle() {
        // Check if the hint from the janitor has been received, a prerequisite for solving the puzzle.
        if (!GameState.isJanitorHintReceived()) {
            return false;
        }
        // Narrate the scenario where the player encounters cryptic license plates in the manager's office.
        Narrator.narrate("As you enter the manager's office, a series of cryptic license plates catches your eye, each seeming to hide a secret message waiting to be decoded.");
        // Prompt the player to proceed with the puzzle.
        GameUtils.promptToProceed();
        // Display each license plate to the player.
        for (String plate : licensePlates) {
            Narrator.narrate(plate);
        }
        // Initialize the UI manager for handling user input.
        String userResponse;
        UiManager uiManager = new UiManager(MainGame.getInstance());
        // Infinite loop to keep asking the player for input until the correct answer is given or the player exits.
        while (true) {
            // Ask the player to decode the hidden message or exit.
            Narrator.narrate("\nDecode the hidden message (or type 'exit' to leave): ");
            userResponse = uiManager.getUserInput().toUpperCase();
            // Check if the player chooses to exit the puzzle.
            if ("EXIT".equals(userResponse)) {
                Narrator.narrate("Exiting the puzzle.");
                return false;
            }
            // Check if the player's response matches the correct combination of decoded words.
            if (userResponse.equals(String.join(" ", decodedWords))) {
                // Confirm the correct answer and update the game state to reflect the puzzle's completion.
                Narrator.narrate(GameUtils.ANSI_GREEN + "Correct! The plates decode to: 'SAUL LAWYER BETTER'." + GameUtils.ANSI_RESET);
                GameState.setLicensePuzzleSolved(true);
                this.setSolved(true);
                Narrator.narrate(GameUtils.ANSI_YELLOW + "Mm, Saul, lawyer... Who is that person?" + GameUtils.ANSI_RESET);
                // Reward the player based on the completion of another puzzle.
                Player player = Player.currentPlayer();
                if (GameState.isQAPuzzleSolved()) {
                    Item rewardItem = new Item("Cryptic Notebook", "A small notebook filled with obscure notes and coded messages. Some pages are missing.");
                    player.addItemToInventory(rewardItem);
                    Narrator.narrate(GameUtils.ANSI_RED + "\"You've found a 'Cryptic Notebook'. As you flip through it, you notice it's missing some pages.\n Then, it strikes you - the piece of paper you found earlier fits perfectly into this notebook. \nIt's a crucial missing page. As you carefully align it, an address becomes clear. \nIt's the office of someone named Saul Goodman. What secrets might this lead unravel?\"" + GameUtils.ANSI_RESET);
                } else {
                    Item rewardItem = new Item("Cryptic Notebook", "A small notebook filled with obscure notes and coded messages. Some pages are missing.");
                    player.addItemToInventory(rewardItem);
                    Narrator.narrate(GameUtils.ANSI_RED + "\"You've found a 'Cryptic Notebook'. It might hold important clues.Some pages are missing.\"" + GameUtils.ANSI_RESET);
                }
                // Provide a hint for the player to check their inventory.
                Narrator.narrate("Hint: Use The next command \"inventory\" to view the inventory");
                return true;
            } else {
                // Notify the player that their answer is incorrect and prompt them to try again.
                Narrator.narrate("That doesn't seem right. Try again...");
            }
        }
    }

}
