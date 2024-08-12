package com.heisenbergenigma.locations;

import com.heisenbergenigma.main.GameState;
import com.heisenbergenigma.player.Player;
import com.heisenbergenigma.ui.UiManager;
import com.heisenbergenigma.utils.GameUtils;
import com.heisenbergenigma.utils.Narrator;

/**
 * Represents the Desert Lab location in the game.
 * This class extends the Location class and encapsulates all functionality specific
 * to the Desert Lab, including initializing the area, setting up the level, and
 * managing interactions within this location.
 */
public class DesertLab extends Location {
    // Matrix defining the labels for various locations in the game environment.
    // Each array entry represents a row in the environment layout, and each string within
    // the row represents a specific location. This matrix is used to provide a descriptive
    // name for each location, enhancing the player's understanding and interaction with the game world.
    String[][] locationLabels = {
            {"Meth Cooking Area", "Storage Room", "Quality Control Lab"},
            {"Power and Ventilation Control", "Security Room", "Emergency Escape Tunnel"},
            {"Rest Area", "Main Entrance", "Supply Room"}
    };

    /**
     * Constructs the DesertLab with default settings.
     */
    public DesertLab() {
        super();
        //this lvl isn't developed yet
    }


    /**
     * Initializes the descriptions for each area within the Desert Lab.
     */
    @Override
    protected void initializeLocationDescriptions() {
        locationDescriptions.put("0,0", GameUtils.ANSI_GREEN + "You're in the Meth Cooking Area, the heart of the lab, with complex apparatus and a distinct chemical odor." + GameUtils.ANSI_RESET);
        locationDescriptions.put("0,1", GameUtils.ANSI_GREEN + "You find yourself in the Storage Room, filled with barrels of chemicals and stacks of equipment." + GameUtils.ANSI_RESET);
        locationDescriptions.put("0,2", GameUtils.ANSI_GREEN + "You enter the Quality Control Lab, a pristine area with advanced testing equipment for meth purity." + GameUtils.ANSI_RESET);
        locationDescriptions.put("1,0", GameUtils.ANSI_GREEN + "You are in the Power and Ventilation Control Room, a network of cables and ventilation ducts ensuring the lab's operation." + GameUtils.ANSI_RESET);
        locationDescriptions.put("1,1", GameUtils.ANSI_GREEN + "You step into the Security Room, filled with monitors displaying different areas of the lab." + GameUtils.ANSI_RESET);
        locationDescriptions.put("1,2", GameUtils.ANSI_GREEN + "You discover the Emergency Escape Tunnel, a hidden pathway for quick and secretive evacuation." + GameUtils.ANSI_RESET);
        locationDescriptions.put("2,0", GameUtils.ANSI_GREEN + "You relax in the Rest Area, a small space with basic amenities for the lab workers." + GameUtils.ANSI_RESET);
        locationDescriptions.put("2,1", GameUtils.ANSI_GREEN + "You're at the Main Entrance, a nondescript door in the desert, deceptively ordinary for such a high-stakes place." + GameUtils.ANSI_RESET);
        locationDescriptions.put("2,2", GameUtils.ANSI_GREEN + "You enter the Supply Room, stocked with non-chemical supplies essential for long stints in the lab." + GameUtils.ANSI_RESET);


    }

    /**
     * Begins the third level of the game, set in the Desert Lab.
     * This method sets up the level, including narrative introduction, background music,
     * and the main gameplay loop for the level.
     *
     * @param player    The player character.
     * @param uiManager The UI manager to handle user input and display.
     */
    public void BeginLevelThree(Player player, UiManager uiManager) {
        String PlayerName = player.getName();
        String introNarration = """
                Welcome to the clandestine Desert Lab of Heisenberg, a hidden fortress of chemistry and crime in the barren expanses of the desert. \n
                This secret laboratory is the epicenter of Walter White's meth empire, a place where science and ambition fuse to create something both dangerous and brilliant. \n
                Beneath the scorching sun and shifting sands lies a world of precision and peril, a testament to Heisenberg's genius and ruthlessness. \n
                Your mission is to delve into the depths of this hidden lab, unraveling the complex operations and dark secrets that fueled the rise of the infamous Heisenberg. \n
                Tread carefully and observe keenly, for every piece of evidence you gather sheds light on the intricate web of this methamphetamine kingdom. \n
                This journey will take you through the shadowy corners of morality and ambition, where every discovery could be as volatile as the chemicals within these walls.""";
        GameUtils.stopBackgroundMusic();
        final String BgMusicPath = "com/heisenbergenigma/assets/DesertLab.wav";
        GameUtils.playBackgroundMusicFromPath(BgMusicPath);
        Narrator.narrate("\nWelcome to Level 3: The Desert Lab\n");
        Narrator.narrate("Hello, " + PlayerName + " " + introNarration);
        GameUtils.promptToProceed();
        //for the moment the condition of the while loop is always true because the logic of level 3 is not complete, it will be changed later
        while (true) {
            GameUtils.drawMap(matrix, player.getPlayerPosY(), player.getPlayerPosX(), locationLabels);
            player.printCurrentPosition();
            interactWithCurrentCell(player.getPlayerPosX(), player.getPlayerPosY());
            String direction = uiManager.getMovementInput();
            player.movePlayer(direction);


        }


    }

}
