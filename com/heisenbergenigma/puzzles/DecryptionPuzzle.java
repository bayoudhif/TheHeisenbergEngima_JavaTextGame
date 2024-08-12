package com.heisenbergenigma.puzzles;

import com.heisenbergenigma.items.Item;
import com.heisenbergenigma.main.GameState;
import com.heisenbergenigma.main.MainGame;
import com.heisenbergenigma.player.Player;
import com.heisenbergenigma.ui.UiManager;
import com.heisenbergenigma.utils.GameUtils;
import com.heisenbergenigma.utils.Narrator;

/**
 * The DecryptionPuzzle class extends the Puzzle class and represents a puzzle
 * where the player needs to decrypt an encrypted message. This puzzle supports
 * decryption using various cipher techniques like Cesar cipher, Vigenere cipher,
 * and ROT13.
 */
public class DecryptionPuzzle extends Puzzle {

    private String encryptedMessage;
    private String decryptedWithCesar;
    private String decryptedWithVigenere;
    private String decryptedWithROT13;

    /**
     * Constructs a new DecryptionPuzzle with predefined encrypted messages
     * and their decrypted forms using different ciphers.
     */
    public DecryptionPuzzle() {
        super("DecryptionPuzzle");
        this.encryptedMessage = GameUtils.ANSI_BLUE + "Isbl'z vypnpu splz dolyl aol lhyao'z jhuchz zaylajolz dpkl huk aptl zahukz zapss." + GameUtils.ANSI_RESET; // Example encrypted message with Cesar shifted by 7
        this.decryptedWithCesar = GameUtils.ANSI_BLUE + "Blue's origin lies where the earth's canvas stretches wide and time stands still." + GameUtils.ANSI_RESET; // The actual decrypted message with Cesar cipher
        this.decryptedWithVigenere = GameUtils.ANSI_BLUE + "Gbdw'g nqnwrf zhdx mqwfd smu nsfsg'x sjfjzr xjawhbgji fard zst caad ryqwvg rsnbu." + GameUtils.ANSI_RESET; // Placeholder for Vigenere cipher
        this.decryptedWithROT13 = GameUtils.ANSI_BLUE + "Vfoy'm ilcach fcym qbyly nby yulnb'm wuhpum mnlynwbym qcxy uhx ncgy mnuhxm mncff." + GameUtils.ANSI_RESET; // Placeholder for ROT13 cipher
    }

    /**
     * Solves the decryption puzzle. The player is presented with an encrypted message
     * and has to choose the correct decryption method. Correct decryption grants a reward
     * and advances the game state.
     *
     * @return boolean indicating whether the puzzle was successfully solved.
     */
    @Override
    public boolean solvePuzzle() {
        // Check if the hint from the receptionist has been received, which is a prerequisite for solving this puzzle.
        if (!GameState.isReceptionistHintReceived()) {
            return false;
        }
        // Narrate to the player that an encrypted file has been found and display its contents.
        Narrator.narrate("You find an encrypted file. It reads: '" + encryptedMessage + "'");
        // Prompt the player to proceed further.
        GameUtils.promptToProceed();
        // Provide a hint to the player about possible encryption methods used in the file.
        Narrator.narrate("Hint: The file might be encrypted with either Cesar cipher, Vigenere cipher, or ROT13.");
        // Create an instance of UiManager for handling user input.
        UiManager uiManager = new UiManager(MainGame.getInstance());
        String userResponse;
        // Loop to keep asking the player for input until the puzzle is solved or the player decides to exit.
        while (true) {
            // Ask the player to choose a decryption method or to exit.
            Narrator.narrate("\nChoose the decryption method (Vigenere/Cesar/ROT13) or type 'exit' to leave: ");
            userResponse = uiManager.getUserInput().toUpperCase();
            // Check if the player decides to exit the puzzle.
            if ("EXIT".equals(userResponse)) {
                Narrator.narrate("Exiting the puzzle.");
                return false;
            }
            // Handle the player's choice of decryption method.
            switch (userResponse) {
                case "CESAR":
                    // Narrate the result of the Cesar decryption and mark the puzzle as solved.
                    Narrator.narrate("The file decrypts to reveal: '" + GameUtils.ANSI_RED + decryptedWithCesar + GameUtils.ANSI_RESET + "'.");
                    GameState.setDecryptionPuzzleSolved(true);
                    this.setSolved(true);
                    // Add a reward item to the player's inventory for solving the puzzle.
                    Player player = Player.currentPlayer();
                    Item rewardItem = new Item("Mysterious Note", "A cryptic note found after decrypting the file.\n It reads: 'Amidst the sands of silence, the shadow of the lone cedar guards the key to blue alchemy.'");
                    player.addItemToInventory(rewardItem);
                    // Provide a hint about the clue's significance.
                    Narrator.narrate(GameUtils.ANSI_YELLOW + "\"This clue seems to hint at the location of Heisenberg's secret lab.\"" + GameUtils.ANSI_RESET);
                    // Remind the player to check their inventory.
                    Narrator.narrate("Hint: Use the next command 'inventory' to view the inventory.");
                    return true;
                case "VIGENERE":
                    // Inform the player that the Vigenere cipher is not the right choice.
                    Narrator.narrate("Using Vigenere cipher, the text reads: '" + decryptedWithVigenere + "'. That doesn't seem right. Try a different one...");
                    break;
                case "ROT13":
                    // Inform the player that the ROT13 cipher is not the right choice.
                    Narrator.narrate("Using ROT13 cipher, the text reads: '" + decryptedWithROT13 + "'. That doesn't seem right. Try a different one...");
                    break;
                default:
                    // Inform the player that the input was invalid and prompt to choose a valid decryption method.
                    Narrator.narrate("Invalid method. Please choose Cesar, Vigenere, or ROT13.");
                    break;
            }
        }
    }
}

