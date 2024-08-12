package com.heisenbergenigma.locations;

import com.heisenbergenigma.main.GameState;
import com.heisenbergenigma.npcs.NPC;
import com.heisenbergenigma.puzzles.*;
import com.heisenbergenigma.utils.Narrator;
import com.heisenbergenigma.player.Player;
import com.heisenbergenigma.utils.GameUtils;
import com.heisenbergenigma.ui.UiManager;

import java.util.Arrays;

/**
 * The CarWash class extends the Location class and represents the Car Wash location
 * in the game. It includes a variety of NPCs, puzzles, and descriptions that
 * relate to the theme of the location.
 */
public class CarWash extends Location {

    // Matrix defining the labels for various locations in the game environment.
// Each array entry represents a row in the environment layout, and each string within
// the row represents a specific location. This matrix is used to provide a descriptive
// name for each location, enhancing the player's understanding and interaction with the game world.
    String[][] locationLabels = {
            {"Maintenance Room", "Break Room", "Storage Room"},
            {"Back Exit", "Main Wash Area", "Manager's Office"},
            {"Secret Area", "Entrance", "Waiting Area"}
    };

    /**
     * Constructs a CarWash location with predefined NPCs, puzzles, and descriptions.
     * Initializes various elements like NPCs in specific locations, puzzles, and
     * the layout of the car wash.
     */
    public CarWash() {
        super();
        //Adding NPCs in specific locations with their respective names and speeches
        matrix[2][1] = new NPC("Welcoming Clerk", GameUtils.ANSI_YELLOW + "\"Welcome to the A1A Car Wash! If you're here to uncover its secrets, \nyou might want to chat with the janitor in the maintenance room. He's always mumbling about something interesting.\"" + GameUtils.ANSI_RESET);
        matrix[0][0] = new NPC("Inquisitive Janitor", GameUtils.ANSI_YELLOW + "\"If walls could talk, oh the tales they'd tell... especially in the manager's office, where secrets dwell..." +
                "\"" + GameUtils.ANSI_RESET);
        matrix[0][1] = new NPC("Former Employee", GameUtils.ANSI_YELLOW + "\"The old manager was paranoid about security. He hid something \nimportant in the storage room, behind all those dusty boxes.\"" + GameUtils.ANSI_RESET);
        matrix[2][2] = new NPC("Curious Customer", GameUtils.ANSI_YELLOW + "\"There's always been rumors about a secret area in this car wash.\nMakes you wonder what might be hidden there, doesn't it?\"" + GameUtils.ANSI_RESET);
        //Adding Puzzles in specific locations
        matrix[1][2] = new LPDecodingPuzzle();
        matrix[0][2] = new PinCodePuzzle();
        matrix[2][0] = new QAPuzzle();
        //The addition of questions for the QAPuzzle is dynamic you can add more questions here with their multiple choices and correct answers
        ((QAPuzzle) matrix[2][0]).addQuestion("What is the main character's name in Breaking Bad?", Arrays.asList("Walter White", "Jesse Pinkman", "Hank Schrader"), "Walter White");
        ((QAPuzzle) matrix[2][0]).addQuestion("In 'Breaking Bad', what color is the crystal meth that Walter White manufactures?", Arrays.asList("Green", "Blue", "Red"), "Blue");
        ((QAPuzzle) matrix[2][0]).addQuestion("What is the name of the fast-food restaurant chain owned by Gus Fring?", Arrays.asList("Los Pollos Hermanos", "El Pollo Loco", "Gus's Fried Chicken"), "Los Pollos Hermanos");


    }

    /**
     * Initializes the descriptions for each location within the Car Wash.
     * The descriptions are specific to different areas in the Car Wash.
     */
    @Override
    protected void initializeLocationDescriptions() {
        locationDescriptions.put("0,2", GameUtils.ANSI_GREEN + "You find yourself in a storage room, filled with cleaning supplies and tools." + GameUtils.ANSI_RESET);
        locationDescriptions.put("1,2", GameUtils.ANSI_GREEN + "You enter the manager's office, cluttered with papers and old receipts." + GameUtils.ANSI_RESET);
        locationDescriptions.put("2,1", GameUtils.ANSI_GREEN + "You are at the entrance of the Car Wash. The familiar hum of the machinery echoes around." + GameUtils.ANSI_RESET);
        locationDescriptions.put("2,2", GameUtils.ANSI_GREEN + "You're in the waiting area, with worn-out seats and outdated magazines." + GameUtils.ANSI_RESET);
        locationDescriptions.put("1,1", GameUtils.ANSI_GREEN + "You stand in the main wash area, where cars come to get cleaned." + GameUtils.ANSI_RESET);
        locationDescriptions.put("0,1", GameUtils.ANSI_GREEN + "You enter the break room, a small space with a coffee machine and a microwave." + GameUtils.ANSI_RESET);
        locationDescriptions.put("0,0", GameUtils.ANSI_GREEN + "You're in the maintenance room, where various machinery parts are scattered around." + GameUtils.ANSI_RESET);
        locationDescriptions.put("1,0", GameUtils.ANSI_GREEN + "You reach the back exit, a secondary way out of the Car Wash." + GameUtils.ANSI_RESET);
        locationDescriptions.put("2,0", GameUtils.ANSI_GREEN + "You discover a secret area, cleverly hidden from plain sight." + GameUtils.ANSI_RESET);

    }

    /**
     * Initiates and manages the gameplay for Level 1, set in the A1A Car Wash.
     * This method sets the stage for the level with an introductory narrative,
     * starts the background music, and enters the main gameplay loop.
     * <p>
     * During the gameplay loop, it updates the game state based on player interactions
     * and checks for the completion of puzzles and objectives specific to this level.
     * It continues until all necessary puzzles are solved and the player reaches
     * the designated exit point.
     *
     * @param player    The player character, used to interact with the game world.
     * @param uiManager The UI manager, used for handling user inputs and displaying outputs.
     */
    public void beginLevelOne(Player player, UiManager uiManager) {
        //Getting the player name
        String playerName = player.getName();
        //storing the introductory speech
        String introNarration = "and welcome to the A1A Car Wash, an ordinary facade for the extraordinary deeds of Walter White, the infamous Heisenberg. \nThis unassuming place hides secrets of a past life, where clean cars were just a cover for dirty money. Your task is to peel back the layers of this mundane setting to reveal \nthe truths that lie beneath. \nWhat you uncover here will set you on a path through the shadowy legacy of Heisenberg. \nProceed with caution and curiosity, for every clue you find brings you closer to the heart of the mystery.";
        //Stopping the previous Background music
        GameUtils.stopBackgroundMusic();
        //Storing the path of the current level background music
        final String bgMusicPath = "com/heisenbergenigma/assets/CarWash.wav";
        //starting the background music
        GameUtils.playBackgroundMusicFromPath(bgMusicPath);
        //printing messages
        Narrator.narrate("\nWelcome to Level 1: The Car Wash\n");
        Narrator.narrate("Hello, " + playerName + " " + introNarration);
        //asking the user to enter yes or no to continue playing
        GameUtils.promptToProceed();
        //the while loop will run until the conditions of exiting level 1 are met
        while (!(GameState.isLicensePuzzleSolved() && GameState.isQAPuzzleSolved() && GameState.isPinPuzzleSolved()) || !(player.getPlayerPosX() == 1 && player.getPlayerPosY() == 0)) {
            //printing a virtual map of the locations and the current position of the player
            GameUtils.drawMap(matrix, player.getPlayerPosY(), player.getPlayerPosX(), locationLabels);
            //checking if the player finished all puzzles so give him hints to pass to the next level
            if (GameState.isLicensePuzzleSolved() && GameState.isQAPuzzleSolved() && GameState.isPinPuzzleSolved()) {
                Narrator.narrate("As you piece together the final clue leading to Saul Goodman's office, a new path unfolds.\n 'Head to the back exit and follow the steps down,' the instruction echoes, guiding you towards the next chapter of your quest.");
                Narrator.narrate(GameUtils.ANSI_GREEN + "Objective: Go to Back Exit" + GameUtils.ANSI_RESET);
            }
            //printing current position of the player
            player.printCurrentPosition();
            //check if there is an interaction with cell based on the player position
            interactWithCurrentCell(player.getPlayerPosX(), player.getPlayerPosY());
            //ask the player to enter his movement command
            String direction = uiManager.getMovementInput();
            //move the player
            player.movePlayer(direction);

        }
        //clear the current inventory for the next level
        player.clearInventory();
        //reset the player position to the default starting position
        player.resetPosition();

    }


}
