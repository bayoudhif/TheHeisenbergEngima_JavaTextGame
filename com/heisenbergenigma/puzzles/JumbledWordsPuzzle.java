package com.heisenbergenigma.puzzles;

import com.heisenbergenigma.items.Item;
import com.heisenbergenigma.main.GameState;
import com.heisenbergenigma.main.MainGame;
import com.heisenbergenigma.player.Player;
import com.heisenbergenigma.ui.UiManager;
import com.heisenbergenigma.utils.GameUtils;
import com.heisenbergenigma.utils.Narrator;

/**
 * The JumbledWordsPuzzle class extends the Puzzle class and represents a puzzle
 * where the player needs to unscramble a jumbled word. This puzzle provides a
 * hint and requires the correct unscrambling of letters to solve it.
 */
public class JumbledWordsPuzzle extends Puzzle {
    private String jumbledWord;
    private String correctAnswer;
    private String hint;

    /**
     * Constructs a new JumbledWordsPuzzle with a jumbled word, its correct answer,
     * and a hint to assist in solving the puzzle.
     *
     * @param jumbledWord   The jumbled word to be unscrambled.
     * @param correctAnswer The correct unscrambled word.
     * @param hint          A hint to aid in solving the puzzle.
     */
    public JumbledWordsPuzzle(String jumbledWord, String correctAnswer, String hint) {
        super("Jumbled Words Puzzle");
        this.jumbledWord = jumbledWord;
        this.correctAnswer = correctAnswer;
        this.hint = hint;
    }

    /**
     * Solves the jumbled words puzzle. The player is presented with a jumbled
     * word and a hint, and must unscramble the word correctly. Successfully
     * unscrambling the word grants a reward and advances the game state.
     *
     * @return boolean indicating whether the puzzle was successfully solved.
     */
    @Override
    public boolean solvePuzzle() {
        // Check if the hint from the associate has been received before allowing puzzle solving.
        if (!GameState.isAssociateHintReceived()) {
            return false;
        }
        // Prompt the player to proceed with the next part of the puzzle.
        GameUtils.promptToProceed();
        // Narrate the scenario where the player encounters a jumbled set of letters in a computer.
        Narrator.narrate("As you boot up the computer in Saul's office, you see a jumbled set of letters: " + jumbledWord);
        // Provide a hint to the player about the nature of the puzzle.
        Narrator.narrate("Hint: " + hint);
        // Initialize the UI manager for user input handling.
        UiManager uiManager = new UiManager(MainGame.getInstance());
        String userResponse;
        // Infinite loop to handle player responses until the correct answer is given or the player exits.
        while (true) {
            // Prompt the player to unscramble the letters or exit.
            Narrator.narrate("\nUnscramble the letters (or type 'exit' to leave): ");
            userResponse = uiManager.getUserInput().toUpperCase();
            // Check if the player chooses to exit the puzzle.
            if ("EXIT".equals(userResponse)) {
                Narrator.narrate("Exiting the puzzle.");
                return false;
            }
            // Check if the player's response matches the correct answer.
            if (userResponse.equals(correctAnswer)) {
                // Confirm the correct answer and update the game state to reflect the puzzle's completion.
                Narrator.narrate(GameUtils.ANSI_GREEN + "Correct! The jumbled letters unscramble to: '" + correctAnswer + "'." + GameUtils.ANSI_RESET);
                GameState.setJumbledWordPuzzleSolved(true);
                this.setSolved(true);
                // Reward the player with an item for solving the puzzle.
                Player player = Player.currentPlayer();
                Item rewardItem = new Item("Encrypted USB Drive", "A small USB drive containing encrypted files.");
                player.addItemToInventory(rewardItem);
                // Narrate the outcome of solving the puzzle.
                Narrator.narrate(GameUtils.ANSI_YELLOW + "\"One of the files, when opened, displays a series of numbers and letters that appear to be GPS coordinates.\"" + GameUtils.ANSI_RESET);
                // Provide a hint to the player to check their inventory.
                Narrator.narrate("Hint: Use the next command 'inventory' to view the inventory.");
                return true;
            } else {
                // Notify the player that their answer is incorrect and prompt them to try again.
                Narrator.narrate("That doesn't seem right. Try again...");
            }
        }
    }

}
