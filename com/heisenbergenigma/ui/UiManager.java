package com.heisenbergenigma.ui;

import com.heisenbergenigma.main.MainGame;

import java.util.InputMismatchException;
import java.util.Scanner;

import com.heisenbergenigma.utils.GameUtils;
import com.heisenbergenigma.player.Player;

/**
 * The UiManager class is responsible for managing all user input interactions
 * within the game. It handles input and output operations, displays menus, and
 * facilitates player interactions.
 */
public class UiManager {

    private MainGame mainGame;
    int menuWidth = 200; // Width of the menu for centering text
    private Scanner scanner;

    /**
     * Constructs a UiManager for the specified MainGame instance.
     *
     * @param mainGame The MainGame instance associated with this UiManager.
     */
    public UiManager(MainGame mainGame) {
        this.mainGame = mainGame;
        this.scanner = new Scanner(System.in);

    }

    /**
     * Gets the player's movement input, validating for specific commands.
     *
     * @return A string representing the player's movement direction.
     */
    public String getMovementInput() {
        System.out.print("Enter your move (gn, gs, ge, gw), 'inventory' to check items or 'exit': ");
        String input = scanner.nextLine().trim().toLowerCase();

        while (!input.matches("gn|gs|ge|gw|inventory|exit")) {
            System.out.println("Invalid command. Please enter 'gn', 'gs', 'ge', 'gw', 'inventory' or 'exit'.");
            System.out.print("Enter your move: ");
            input = scanner.nextLine().trim().toLowerCase();
        }
        if ("exit".equals(input)) {
            System.out.println("Exiting game...");
            System.exit(0); // Exits the program
        }
        return input;
    }

    /**
     * Displays the main menu of the game with options for the player to choose from.
     */
    public void displayMainMenu() {
        final String BgMusicPath = "com/heisenbergenigma/assets/mainMenu.wav";
        GameUtils.playBackgroundMusicFromPath(BgMusicPath);

        boolean menuActive = true;

        while (menuActive) {
            GameUtils.printCenteredAndDecorated("The Heisenberg Enigma", menuWidth);
            GameUtils.printCenteredAndDecorated("Main Menu", menuWidth);
            GameUtils.printCenteredAndDecorated("1. New Game", menuWidth);
            GameUtils.printCenteredAndDecorated("2. Load Game", menuWidth);
            GameUtils.printCenteredAndDecorated("3. Exit", menuWidth);
            GameUtils.printCenteredAndDecorated("Enter your choice (1-3): ", menuWidth);
            try {
                int choice = Integer.parseInt(scanner.nextLine());

                switch (choice) {
                    case 1:
                        System.out.println("Game Starting");
                        mainGame.startNewGame();
                        menuActive = false;  // Assuming the game loop takes over
                        break;
                    case 2:
                        System.out.println("Game Loading");
                        //mainGame.loadGame(); //This feature is not developed yet
                        menuActive = false;  // Assuming the game continues from loaded state
                        break;
                    case 3:
                        System.out.println("Exiting game. Goodbye!");
                        menuActive = false;
                        break;
                    default:
                        System.out.println("Invalid choice. Please enter a number between 1 and 3.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    /**
     * Creates a new player profile by getting the player's name and other details.
     *
     * @return A new Player object with the entered details.
     * @throws InterruptedException if the thread is interrupted.
     */
    public Player createPlayerProfile() throws InterruptedException {
        boolean isBreakingBadFan = false;
        String playerName = "";
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter your name: ");
        while (playerName.isEmpty()) {
            playerName = scanner.nextLine();
            if (playerName.isEmpty()) {
                System.out.println("You must enter a name. Please try again:");
            }
        }


        while (!isBreakingBadFan) {


            System.out.println("Do you admit that Breaking Bad is better than Game of Thrones? (yes/no)");
            String response = scanner.nextLine();
            isBreakingBadFan = response.trim().equalsIgnoreCase("yes");


            if (!isBreakingBadFan) {
                System.out.println("You must be a fan of Breaking Bad to play this game!");
            } else {
                System.out.println("Well answered! Bravo!");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }

            }
        }
        return new Player(playerName);
    }

    /**
     * Gets an integer input from the user, ensuring that the input is a valid number.
     *
     * @return The integer input provided by the user.
     */
    public int getIntInput() {
        while (true) {
            try {
                return scanner.nextInt();
            } catch (InputMismatchException e) {
                scanner.nextLine(); // clear the invalid input
                System.out.print("Invalid input. Please enter a number: ");
            }
        }
    }

    /**
     * Gets a string input from the user.
     *
     * @return The string input provided by the user.
     */

    public String getUserInput() {
        return scanner.nextLine();
    }
}
