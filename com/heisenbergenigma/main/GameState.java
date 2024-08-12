package com.heisenbergenigma.main;

/**
 * The GameState class is a utility class that maintains the state of the game
 * across different levels. It tracks various flags for hints received and puzzles
 * solved at each level of the game.
 */
public class GameState {
    //.... State flags for Level 1
    private static boolean janitorHintReceived = false;
    private static boolean ClerkHintReceived = false;
    private static boolean EmployeeHintReceived = false;
    private static boolean CustomerHintReceived = false;
    private static boolean licensePuzzleSolved = false;
    private static boolean PinPuzzleSolved = false;
    private static boolean QAPuzzleSolved = false;

    //....  State flags for Level 2
    private static boolean AssociateHintReceived = false;
    private static boolean ReceptionistHintReceived = false;
    private static boolean CleanerisHintReceived = false;


    private static boolean JumbledWordPuzzleSolved = false;
    private static boolean DecryptionPuzzleSolved = false;
    private static boolean HiddenCodeSolved = false;


    // ... Getters & Setters ...

    /**
     * Sets the flag indicating whether the janitor hint has been received.
     *
     * @param received boolean indicating if the hint is received.
     */
    public static void setJanitorHintReceived(boolean received) {
        janitorHintReceived = received;
    }

    /**
     * Checks if the janitor hint has been received.
     *
     * @return boolean indicating if the janitor hint is received.
     */
    // ... other getter and setter methods with the same logic ...
    public static boolean isJanitorHintReceived() {
        return janitorHintReceived;
    }

    public static void setClerkHintReceived(boolean received) {
        ClerkHintReceived = received;
    }


    public static void setEmployeeHintReceived(boolean received) {
        EmployeeHintReceived = received;
    }

    public static boolean isEmployeeHintReceived() {
        return EmployeeHintReceived;
    }

    public static void setCustomerHintReceived(boolean received) {
        CustomerHintReceived = received;
    }

    public static boolean isCustomerHintReceived() {
        return CustomerHintReceived;
    }

    public static void setLicensePuzzleSolved(boolean solved) {
        licensePuzzleSolved = solved;
    }

    public static boolean isLicensePuzzleSolved() {
        return licensePuzzleSolved;
    }

    public static void setPinPuzzleSolved(boolean solved) {
        PinPuzzleSolved = solved;
    }

    public static boolean isPinPuzzleSolved() {
        return PinPuzzleSolved;
    }

    public static void setQAPuzzleSolved(boolean solved) {
        QAPuzzleSolved = solved;
    }

    public static boolean isQAPuzzleSolved() {
        return QAPuzzleSolved;
    }

    //....... Level Two .....
    //Associate points to the jumbled Word Puzzle at Surveillance room
    public static void setAssociateHintReceived(boolean received) {
        AssociateHintReceived = received;
    }

    public static boolean isAssociateHintReceived() {
        return AssociateHintReceived;
    }

    public static void setJumbledWordPuzzleSolved(boolean solved) {
        JumbledWordPuzzleSolved = solved;
    }

    public static boolean isJumbledWordPuzzleSolved() {
        return JumbledWordPuzzleSolved;
    }

    //Receptionist points to the DecryptionPuzzle  at Conference room
    public static void setReceptionistHintReceived(boolean received) {
        ReceptionistHintReceived = received;
    }

    public static boolean isReceptionistHintReceived() {
        return ReceptionistHintReceived;
    }

    public static void setDecryptionPuzzleSolved(boolean solved) {
        DecryptionPuzzleSolved = solved;
    }

    public static boolean isDecryptionPuzzleSolved() {
        return DecryptionPuzzleSolved;
    }

    public static void setCleanerHintReceived(boolean received) {
        CleanerisHintReceived = received;
    }

    public static boolean isCleanerHintReceived() {
        return CleanerisHintReceived;
    }

    public static void setHiddenCodePuzzleSolved(boolean solved) {
        HiddenCodeSolved = solved;
    }

    public static boolean isHiddenCodeSolved() {
        return HiddenCodeSolved;
    }
}


