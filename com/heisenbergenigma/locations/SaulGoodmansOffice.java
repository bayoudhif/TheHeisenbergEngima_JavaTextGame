package com.heisenbergenigma.locations;

import com.heisenbergenigma.main.GameState;
import com.heisenbergenigma.player.Player;
import com.heisenbergenigma.puzzles.*;
import com.heisenbergenigma.ui.UiManager;
import com.heisenbergenigma.utils.GameUtils;
import com.heisenbergenigma.utils.Narrator;
import com.heisenbergenigma.npcs.NPC;

import java.io.InputStream;

/**
 * The SaulGoodmansOffice class extends the Location class and represents
 * Saul Goodman's Office in the game. This class includes the setup of NPCs,
 * puzzles, and descriptions that relate to Saul Goodman's legal practice.
 */
public class SaulGoodmansOffice extends Location {
    // Matrix defining the labels for various locations in the game environment.
    // Each array entry represents a row in the environment layout, and each string within
    // the row represents a specific location. This matrix is used to provide a descriptive
    // name for each location, enhancing the player's understanding and interaction with the game world.
    String[][] locationLabels = {
            {"Storage Closet", "File Room", "Saul's Personal Office"},
            {"Conference Room", "Lobby", "Legal Library"},
            {"Surveillance Room", "Reception", "Client Consultation Area"}
    };

    /**
     * Constructs Saul Goodman's Office with predefined NPCs, puzzles, and descriptions.
     * Initializes various elements like NPCs in specific locations and puzzles.
     */
    public SaulGoodmansOffice() {
        super();

        matrix[1][2] = new NPC("Junior Associate", GameUtils.ANSI_YELLOW + "\"There’s a rumor that Saul keeps track of more than just legal cases in the surveillance room.\n I heard there's a way to access hidden files on his computer if you know where to look.\"" + GameUtils.ANSI_RESET);
        matrix[2][1] = new NPC("Observant Receptionist", GameUtils.ANSI_YELLOW + "\"I've noticed Saul often steps into the conference room before meeting with certain clients. He always checks a specific folder there. Makes me wonder what's in it.\"" + GameUtils.ANSI_RESET);
        matrix[0][0] = new NPC("Discreet Cleaner", GameUtils.ANSI_YELLOW + "\"While cleaning the file room, I overheard some lawyers in the legal library talking about a secret drawer that no one ever notices.\"" + GameUtils.ANSI_RESET);

        matrix[2][0] = new JumbledWordsPuzzle("LWYARAE", "LAWYER", "The one who knocks in the courtroom.");
        matrix[1][0] = new DecryptionPuzzle();
        matrix[0][1] = new HiddenCodePuzzle();
    }

    /**
     * Initializes the descriptions for each location within Saul Goodman's Office.
     * The descriptions are specific to different areas in the office.
     */
    @Override
    protected void initializeLocationDescriptions() {
        locationDescriptions.put("2,1", GameUtils.ANSI_GREEN + "You step into the reception, greeted by a buzz of activity and walls adorned with legal accolades." + GameUtils.ANSI_RESET);
        locationDescriptions.put("0,1", GameUtils.ANSI_GREEN + "You enter the file room, lined with cabinets full of case files, legal documents, and client records." + GameUtils.ANSI_RESET);
        locationDescriptions.put("0,2", GameUtils.ANSI_GREEN + "You find yourself in Saul's personal office, an eclectic mix of luxury and kitsch, reflecting the man himself." + GameUtils.ANSI_RESET);

        locationDescriptions.put("1,0", GameUtils.ANSI_GREEN + "You are in the conference room, a space with a large table and chairs, hinting at many high-stakes meetings." + GameUtils.ANSI_RESET);
        locationDescriptions.put("1,1", GameUtils.ANSI_GREEN + "You stand in the lobby, an area designed to comfort the anxious minds of clients with plush seating and calming decor." + GameUtils.ANSI_RESET);
        locationDescriptions.put("1,2", GameUtils.ANSI_GREEN + "You're in the legal library, surrounded by shelves stocked with law books, journals, and legal precedents." + GameUtils.ANSI_RESET);

        locationDescriptions.put("2,0", GameUtils.ANSI_GREEN + "You discover a surveillance room, filled with monitors showing different areas of the office and beyond." + GameUtils.ANSI_RESET);
        locationDescriptions.put("0,0", GameUtils.ANSI_GREEN + "You enter a small storage closet, seemingly mundane but potentially hiding secrets amidst its shelves." + GameUtils.ANSI_RESET);
        locationDescriptions.put("2,2", GameUtils.ANSI_GREEN + "You're in the client consultation area, a private room where Saul Goodman discusses cases with his clients." + GameUtils.ANSI_RESET);


    }

    /**
     * Initiates and orchestrates the gameplay for Level 2, set in Saul Goodman's Office.
     * This method begins with an introduction to Saul Goodman's world, setting the tone
     * with a narrative description. It then proceeds to handle the gameplay loop for this level.
     * <p>
     * The gameplay loop includes:
     * - Drawing the current map and player's position.
     * - Checking for completion of various puzzles like DecryptionPuzzle, HiddenCodePuzzle,
     * and JumbledWordPuzzle.
     * - Handling player movement and interactions within Saul Goodman's Office.
     * <p>
     * The level continues until the player has solved all the puzzles and moves to the specified
     * exit point, indicating progression to the next stage of the game.
     *
     * @param player    The player character, responsible for interacting with the game world.
     * @param uiManager The UI manager, used for input handling and displaying information to the player.
     */
    public void BeginLevelTwo(Player player, UiManager uiManager) {
        String PlayerName = player.getName();
        String introNarration = "You step into the world of Saul Goodman, the flamboyant lawyer with a penchant for bending the rules. \nHis office, a kaleidoscope of gaudy decor and legal paraphernalia, holds more than just confidential files and court documents. \nBehind the facade of legal services, this place is a nexus of Albuquerque's underground, a crossroad of questionable dealings and desperate clients. \nYour journey into the heart of legal grey areas begins here. Unravel the enigmas wrapped in legalese, and tread carefully, for the truths you uncover might be as twisted as the man himself. \nEvery document, every client record could be the key to understanding Saul Goodman's role in the saga of Heisenberg.";
        GameUtils.stopBackgroundMusic();
        final String BgMusicPath = "com/heisenbergenigma/assets/SaulGoodmansOffice.wav";
        GameUtils.playBackgroundMusicFromPath(BgMusicPath);
        Narrator.narrate("\nWelcome to Level 2: Saul Goodman's Office\n");
        Narrator.narrate("Hello, " + PlayerName + "," + introNarration);
        GameUtils.promptToProceed();
        while (!(GameState.isDecryptionPuzzleSolved() && GameState.isHiddenCodeSolved() && GameState.isJumbledWordPuzzleSolved()) || !(player.getPlayerPosX() == 2 && player.getPlayerPosY() == 1)) {
            GameUtils.drawMap(matrix, player.getPlayerPosY(), player.getPlayerPosX(), locationLabels);
            if (GameState.isDecryptionPuzzleSolved() && GameState.isHiddenCodeSolved() && GameState.isJumbledWordPuzzleSolved()) {
                Narrator.narrate("With all clues in hand and the GPS coordinates set, you know your next destination: \nHeisenberg's secret lab in the desert. It's time to leave Saul Goodman's office. Head straight through the reception to the outside, where your journey to the desert begins.");
                Narrator.narrate(GameUtils.ANSI_GREEN + "Objective: Exit Saul Goodman's office through the reception and head to the desert." + GameUtils.ANSI_RESET);

            }
            player.printCurrentPosition();
            interactWithCurrentCell(player.getPlayerPosX(), player.getPlayerPosY());
            String direction = uiManager.getMovementInput();
            player.movePlayer(direction);
        }

        player.clearInventory();
        player.resetPosition();
    }
}
