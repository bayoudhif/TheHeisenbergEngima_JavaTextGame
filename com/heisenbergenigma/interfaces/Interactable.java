package com.heisenbergenigma.interfaces;

/**
 * The Interactable interface defines a contract for objects that can be interacted with
 * in the game. It is used to implement interaction capabilities for various game entities,
 * such as NPCs, items, or environmental objects.
 * <p>
 * Implementing this interface requires defining the interact method, which will contain
 * the specific interaction logic for the object.
 */
public interface Interactable {

    /**
     * Executes an interaction with this object.
     * The specific behavior of this interaction is defined in the implementing class,
     * and can vary widely depending on the type of entity (NPC, item, etc.).
     */
    void interact();

}
