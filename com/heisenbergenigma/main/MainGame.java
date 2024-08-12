package com.heisenbergenigma.main;

import com.heisenbergenigma.locations.DesertLab;
import com.heisenbergenigma.locations.SaulGoodmansOffice;
import com.heisenbergenigma.player.Player;
import com.heisenbergenigma.ui.UiManager;
import com.heisenbergenigma.locations.CarWash;

/**
 * MainGame is the central class of the game, responsible for initializing and
 * starting the game. It manages the primary game flow and interactions between
 * various game components such as locations, player, and UI.
 */

public class MainGame {
    private Player player;
    private UiManager uiManager;
    private CarWash carWash;
    private static MainGame instance;
    private SaulGoodmansOffice saulOffice;
    private DesertLab desertLab;


    /**
     * Constructs the MainGame object, initializing the UI manager and game locations.
     */
    public MainGame() {
        this.uiManager = new UiManager(this);
        this.carWash = new CarWash();
        this.saulOffice = new SaulGoodmansOffice();
        this.desertLab = new DesertLab();

    }

    /**
     * Starts a new game, creating a player profile and initiating the first level.
     * If player creation fails, it terminates the game.
     *
     * @throws InterruptedException if the game is interrupted during execution.
     */
    public void startNewGame() throws InterruptedException {
        this.player = uiManager.createPlayerProfile();
        if (this.player != null) {
            carWash.beginLevelOne(this.player, this.uiManager);
            saulOffice.BeginLevelTwo(this.player, this.uiManager);
            desertLab.BeginLevelThree(this.player, this.uiManager);

        } else {
            System.out.println("Player creation failed. Exiting game.");
        }
    }

    /**
     * Provides the singleton instance of the MainGame.
     *
     * @return the instance of MainGame.
     */
    public static MainGame getInstance() {
        return instance;
    }

    /**
     * The main method to start the game. It initializes the game and displays the main menu.
     *
     * @param args command line arguments .
     */
    public static void main(String[] args) {
        MainGame game = new MainGame();
        game.uiManager.displayMainMenu();
    }
}
