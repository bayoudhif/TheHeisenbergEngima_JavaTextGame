package com.heisenbergenigma.puzzles;

import com.heisenbergenigma.items.Item;
import com.heisenbergenigma.main.GameState;
import com.heisenbergenigma.main.MainGame;
import com.heisenbergenigma.player.Player;
import com.heisenbergenigma.ui.UiManager;
import com.heisenbergenigma.utils.GameUtils;
import com.heisenbergenigma.utils.Narrator;
import org.ietf.jgss.GSSManager;

/**
 * The HiddenCodePuzzle class extends the Puzzle class and represents a puzzle
 * where the player needs to find a hidden code to unlock a drawer. This puzzle
 * involves reading a text and deciphering a code from it.
 */
public class HiddenCodePuzzle extends Puzzle {
    private String paperText;
    private String correctCode;
    private boolean isDrawerUnlocked;


    /**
     * Constructs a new HiddenCodePuzzle with predefined paper text and the correct code
     * needed to unlock a secret drawer.
     */
    public HiddenCodePuzzle() {
        super("SecretDrawerPuzzle");
        this.paperText = "Upon careful examination of " + GameUtils.ANSI_BLUE + "Section 4" + GameUtils.ANSI_RESET + " of the legal statute, \nit becomes evident that there are several key points to consider. \nFirstly," + GameUtils.ANSI_BLUE + " Article 7" + GameUtils.ANSI_RESET + " outlines the primary obligations and rights. \nMoreover," + GameUtils.ANSI_BLUE + "the third paragraph" + GameUtils.ANSI_RESET + " elaborates on the specifics of compliance.\n Notably," + GameUtils.ANSI_BLUE + "in subsection 2" + GameUtils.ANSI_RESET + ", the details become particularly intricate, highlighting the complexities of this case.";
        this.correctCode = "4732";
        this.isDrawerUnlocked = false;
    }

    /**
     * Solves the hidden code puzzle. The player is presented with a paper text and
     * must choose to read it, attempt to unlock the drawer, or exit. Correctly
     * deciphering the code and unlocking the drawer grants a reward and advances
     * the game state.
     *
     * @return boolean indicating whether the puzzle was successfully solved.
     */
    @Override
    public boolean solvePuzzle() {
        // Check if the hint from the cleaner has been received, which is required to solve the puzzle.
        if (!GameState.isCleanerHintReceived()) {
            return false;
        }
        // Prompt the player to proceed with the puzzle.
        GameUtils.promptToProceed();
        // Narrate the discovery of a curious paper in the library and provide options to the player.
        Narrator.narrate("In the library, you find a curious paper. Do you want to 'read' the paper or 'try' to unlock the drawer? (Type 'read', 'try', or 'exit')");
        // Initialize UI manager for handling user input.
        UiManager uiManager = new UiManager(MainGame.getInstance());
        String userResponse;
        // Continue the loop until the drawer is unlocked.
        while (!isDrawerUnlocked) {
            // Get user input and process it in lowercase.
            userResponse = uiManager.getUserInput().toLowerCase();
            // Handle different user responses.
            switch (userResponse) {
                case "read":
                    // Narrate the contents of the paper and provide further options to the player.
                    Narrator.narrate(paperText);
                    Narrator.narrate(GameUtils.ANSI_GREEN + "Mmmm , There is some marked text" + GameUtils.ANSI_RESET);
                    Narrator.narrate("Do you want to 'try' to unlock the drawer or 'exit'?");
                    break;
                case "try":
                    // Prompt the player to enter the code to unlock the drawer.
                    Narrator.narrate("Enter the code to unlock the drawer:");
                    String codeAttempt = uiManager.getUserInput();
                    // Check if the entered code is correct.
                    if (codeAttempt.equals(correctCode)) {
                        // Narrate the success of unlocking the drawer and update the game state.
                        Narrator.narrate(GameUtils.ANSI_GREEN + "The code works! The drawer unlocks, revealing its contents." + GameUtils.ANSI_RESET);
                        isDrawerUnlocked = true;
                        GameState.setHiddenCodePuzzleSolved(true);
                        this.setSolved(true);
                        // Reward the player with items for solving the puzzle.
                        Player player = Player.currentPlayer();
                        Item rewardItem = new Item("GPS Device", "A sophisticated GPS device,");
                        Item rewardItem2 = new Item("Note", "34.0522° N, 106.2436° W");
                        player.addItemToInventory(rewardItem2);
                        player.addItemToInventory(rewardItem);
                        // Narrate the discovery inside the drawer.
                        Narrator.narrate(GameUtils.ANSI_YELLOW + "\"Inside the drawer, you find a note and a GPS device. \nThe note reads: 'Coordinates to the heart of the blue empire: 34.0522° N, 106.2436° W\"" + GameUtils.ANSI_RESET);
                        // Provide a hint for the player to check their inventory.
                        Narrator.narrate("Hint: Use the next command 'inventory' to view the inventory.");
                        return true;
                    } else {
                        // Inform the player if the entered code is incorrect.
                        Narrator.narrate("That code doesn't seem to work.");
                    }
                    break;
                case "exit":
                    // Handle the player's decision to exit the puzzle.
                    Narrator.narrate("You decide to step away from the paper and drawer.");
                    return false;
                default:
                    // Inform the player of an invalid option and provide valid options.
                    Narrator.narrate("Invalid option. Type 'read', 'try', or 'exit'.");
                    break;
            }
        }
        // Return false if the drawer remains locked after the loop.
        return false;
    }
}
