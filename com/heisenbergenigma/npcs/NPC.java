package com.heisenbergenigma.npcs;

import com.heisenbergenigma.interfaces.Interactable;
import com.heisenbergenigma.main.GameState;

/**
 * The NPC (Non-Player Character) class represents characters in the game that
 * players can interact with. These characters can provide hints, instructions,
 * or contribute to the game's narrative.
 */
public class NPC implements Interactable {
    private String name;
    private String instruction;
    private boolean hasInteracted;

    /**
     * Constructs an NPC with a specified name and instruction.
     *
     * @param name        The name of the NPC.
     * @param instruction The instruction or dialogue the NPC provides.
     */
    public NPC(String name, String instruction) {
        this.name = name;
        this.instruction = instruction;
        this.hasInteracted = false;

    }

    /**
     * Returns the name of the NPC.
     *
     * @return The name of the NPC.
     */

    public String getName() {
        return this.name;
    }

    /**
     * Interacts with the NPC. On first interaction, it displays the instruction
     * and updates the game state based on the NPC's role. Subsequent interactions
     * do not repeat the instruction.
     */
    @Override
    public void interact() {
        if (!hasInteracted) {
            System.out.println(name + ":" + instruction);
            hasInteracted = true;
            switch (this.getName()) {
                case "Inquisitive Janitor":
                    GameState.setJanitorHintReceived(true);
                    break;
                case "Welcoming Clerk":
                    GameState.setClerkHintReceived(true);
                    break;
                case "Former Employee":
                    GameState.setEmployeeHintReceived(true);
                    break;
                case "Curious Customer":
                    GameState.setCustomerHintReceived(true);
                    break;
                case "Junior Associate":
                    GameState.setAssociateHintReceived(true);
                    break;
                case "Observant Receptionist":
                    GameState.setReceptionistHintReceived(true);
                    break;
                case "Discreet Cleaner":
                    GameState.setCleanerHintReceived(true);
                    break;
                default:
                    break;
            }
        }
    }
}
