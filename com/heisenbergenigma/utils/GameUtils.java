package com.heisenbergenigma.utils;
// The next imports are for background music implementations

import java.io.InputStream;
import java.util.Scanner;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;

/**
 * The GameUtils class provides utility functions for the game, including
 * methods for playing background music, printing formatted text, and handling
 * user prompts.
 */
public class GameUtils {
    // ANSI escape code to reset text formatting to the default in the console.
    // I used this link  as a reference :https://www.geeksforgeeks.org/how-to-print-colored-text-in-java-console/
    public static final String ANSI_RESET = "\u001B[0m";
    // ANSI escape code for  text colors in the console.
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";

    /**
     * Prints text centered and decorated with a specific width.
     *
     * @param text  The text to be printed.
     * @param width The width for centering the text.
     */
    public static void printCenteredAndDecorated(String text, int width) {
        String decoratedText = "* " + text + " *";
        int padSize = width - decoratedText.length();
        int padStart = padSize / 2;

        decoratedText = String.format("%" + (padStart + decoratedText.length()) + "s", decoratedText);
        decoratedText = String.format("%-" + width + "s", decoratedText);

        System.out.println(decoratedText);
    }

    private static Clip clip; // Static member to hold the music clip

    /**
     * Plays background music from the provided InputStream.
     *
     * @param audioStream The InputStream of the audio file to be played.
     */
    // https://stackoverflow.com/questions/13573281/how-to-play-a-background-music-when-the-program-run-in-java
    public static void playBackgroundMusic(InputStream audioStream) {
        try {
            AudioInputStream audioInput = AudioSystem.getAudioInputStream(audioStream);
            clip = AudioSystem.getClip();
            clip.open(audioInput);
            clip.start();
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    /**
     * Plays background music from the specified file path.
     * This method attempts to load an audio file from the given path and play it as background music.
     * If the file cannot be found or loaded, it prints an error message.
     *
     * @param filePath The relative path to the audio file within the project resources.
     */
    public static void playBackgroundMusicFromPath(String filePath) {
        InputStream audioStream = GameUtils.class.getClassLoader().getResourceAsStream(filePath);
        if (audioStream != null) {
            playBackgroundMusic(audioStream);
        } else {
            System.out.println("Can't find file: " + filePath);
        }
    }

    /**
     * Stops the currently playing background music.
     */
    public static void stopBackgroundMusic() {
        if (clip != null) {
            clip.stop(); // Stop the music
            clip.close(); // Close the clip
        }
    }

    /**
     * Prompts the user to proceed with the game or exit.
     */
    public static void promptToProceed() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print("Do you want to proceed? (yes/no): ");
            String response = scanner.nextLine().trim().toLowerCase();

            if ("yes".equals(response)) {
                clearConsole();
                break;
            } else if ("no".equals(response)) {
                System.out.println("Exiting game...");
                System.exit(0);
            } else {
                System.out.println("Invalid input. Please type 'yes' or 'no'.");
            }
        }
    }

    /**
     * Clears the console output by printing new lines.
     */
    private static void clearConsole() {
        for (int i = 0; i < 50; i++) {
            System.out.println("\n");
        }
    }

    /**
     * Draws a map of the game world with the player's current position.
     *
     * @param matrix         The matrix representing the game world layout.
     * @param playerPosX     The X-coordinate of the player's position.
     * @param playerPosY     The Y-coordinate of the player's position.
     * @param locationLabels Labels for each location in the matrix.
     */
    public static void drawMap(Object[][] matrix, int playerPosX, int playerPosY, String[][] locationLabels) {
        GameUtils.clearConsole();
        System.out.println(ANSI_RED + "Hint: Your Current Position is determined by the following Symbol:\"<Here!>\"");
        System.out.println(ANSI_RED + "+-----------------+-----------------+-----------------+" + ANSI_RESET);
        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[row].length; col++) {
                System.out.print(ANSI_RED + "| ");
                if (row == playerPosY && col == playerPosX) {
                    System.out.printf(ANSI_YELLOW + "%-15s", "[" + locationLabels[row][col] + "<Here!>" + "] " + ANSI_RESET);
                } else {
                    System.out.printf(ANSI_RED + "%-15s", "[" + locationLabels[row][col] + "] " + ANSI_RESET);
                }
            }
            System.out.println(ANSI_RED + "|" + ANSI_RESET);
            System.out.println(ANSI_RED + "+-----------------+-----------------+-----------------+" + ANSI_RESET);
        }
    }

}





