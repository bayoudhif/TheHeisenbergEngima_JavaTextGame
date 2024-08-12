package com.heisenbergenigma.puzzles;

import com.heisenbergenigma.items.Item;
import com.heisenbergenigma.main.GameState;
import com.heisenbergenigma.main.MainGame;
import com.heisenbergenigma.player.Player;
import com.heisenbergenigma.ui.UiManager;
import com.heisenbergenigma.utils.GameUtils;
import com.heisenbergenigma.utils.Narrator;
import org.ietf.jgss.GSSManager;

import java.util.Random;

/**
 * The PinCodePuzzle class extends the Puzzle class and represents a puzzle
 * where the player needs to guess a 3-digit secret PIN code. The puzzle
 * allows a maximum number of attempts for the player to guess the correct PIN.
 */
public class PinCodePuzzle extends Puzzle {
    private String secretPin;
    private final int maxAttempts = 8;

    /**
     * Constructs a new PinCodePuzzle with a randomly generated 3-digit secret PIN.
     */
    public PinCodePuzzle() {
        super("The Enigma Safe");
        this.secretPin = "123"; //generateRandomPin(); I am using 123 code  for easy testing, but you can use the method to generate a random number
    }

    /**
     * Generates a random 3-digit PIN code.
     *
     * @return A string representing the generated PIN code.
     */
    private String generateRandomPin() {
        Random random = new Random();
        int num = random.nextInt(900) + 100; // Generates a number in the range 100-999
        return String.valueOf(num);
    }

    /**
     * Validates if the provided guess is a valid 3-digit number.
     *
     * @param guess The guessed PIN code.
     * @return boolean indicating whether the guess is valid.
     */
    private boolean isValidGuess(String guess) {
        return guess.matches("\\d{3}"); // Regex to check if guess is exactly 3 digits
    }

    /**
     * Solves the PIN code puzzle. The player is presented with a digital keypad
     * and must correctly guess the 3-digit PIN code within a limited number of attempts.
     * Correctly guessing the code grants a reward and advances the game state.
     *
     * @return boolean indicating whether the puzzle was successfully solved.
     */
    @Override
    public boolean solvePuzzle() {
        // Check if the hint from the employee has been received before attempting the puzzle.
        if (!GameState.isEmployeeHintReceived()) {
            return false;
        }
        // Narrate the discovery of an old safe in the storage room.
        Narrator.narrate("Hidden behind dusty boxes in the storage room, an old safe with a digital keypad challenges you to unlock its secrets.");
        // Prompt the player to proceed with the puzzle.
        GameUtils.promptToProceed();
        // Initialize the UI manager for user input.
        UiManager uiManager = new UiManager(MainGame.getInstance());
        // Initialize a variable to track the number of attempts made by the player.
        int attempts = 0;
        // Narrate the scenario of facing a high-security digital lock on the safe.
        Narrator.narrate("\"In front of you is a high-security digital lock safeguarding the most confidential files.\n It requires a 3-digit code to unlock.\"");
        // Loop for the player to attempt to unlock the safe.
        while (attempts < maxAttempts) {
            // Prompt the player to enter their guess for the safe's code.
            Narrator.narrate("Enter your guess (3 digits): ");
            String guess = uiManager.getUserInput().trim();

            // Validate the player's input to ensure it's a 3-digit number.
            if (!isValidGuess(guess)) {
                System.out.println("Invalid input. Please enter exactly 3 digits.");
                continue;
            }
            // Check if the player's guess matches the secret PIN of the safe.
            if (guess.equals(secretPin)) {
                // Confirm the correct guess and unlock the safe.
                System.out.println(GameUtils.ANSI_GREEN + "Correct! You've unlocked the safe." + GameUtils.ANSI_RESET);
                Player player = Player.currentPlayer();
                // Update the game state to reflect the puzzle's completion.
                GameState.setPinPuzzleSolved(true);
                this.setSolved(true);
                // Reward the player with an item for solving the puzzle.
                Item rewardItem = new Item("Faded Business Card", "An old business card, its edges frayed and colors faded. The name 'Saul Goodman, \nAttorney at Law' is just legible, along with a partially smudged phone number.");
                player.addItemToInventory(rewardItem);
                // Narrate the discovery of the faded business card inside the safe.
                Narrator.narrate(GameUtils.ANSI_YELLOW + "\"You've uncovered a 'Faded Business Card' from within the safe. Though worn with time, the name 'Saul Goodman, Attorney at Law' stands out, \nalong with a phone number that's partially obscured. This could be a vital lead.\"" + GameUtils.ANSI_RESET);

                return true;
            } else {
                // Provide feedback to the player about their incorrect guess.
                provideFeedback(guess);
                // Increment the attempts counter.
                attempts++;
            }
        }
        // Inform the player that they have run out of attempts and the safe remains locked.
        Narrator.narrate(GameUtils.ANSI_RED + "You've run out of attempts! The safe remains locked." + GameUtils.ANSI_RESET);
        return false;
    }


    /**
     * Provides feedback on the player's guess, indicating how many digits are correctly
     * placed and how many are correct but in the wrong place.
     *
     * @param guess The guessed PIN code.
     */
    private void provideFeedback(String guess) {
        int correctPlace = 0;
        int correctNumber = 0;

        for (int i = 0; i < secretPin.length(); i++) {
            char guessChar = guess.charAt(i);
            if (guessChar == secretPin.charAt(i)) {
                correctPlace++;
            } else if (secretPin.contains(String.valueOf(guessChar))) {
                correctNumber++;
            }
        }

        System.out.println(GameUtils.ANSI_GREEN + "Correct number in correct place: " + correctPlace + GameUtils.ANSI_RESET);
        System.out.println(GameUtils.ANSI_RED + "Correct number in wrong place: " + correctNumber + GameUtils.ANSI_RESET);
    }

}
