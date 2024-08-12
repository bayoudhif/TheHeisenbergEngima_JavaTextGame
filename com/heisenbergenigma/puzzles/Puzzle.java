package com.heisenbergenigma.puzzles;

/**
 * The Puzzle class is an abstract class that serves as a template for various puzzles
 * in the game. It holds common properties and functionalities that all puzzles share,
 * such as a name and a solved status, along with an abstract method for solving the puzzle.
 */
public abstract class Puzzle {
    private final String name;
    private boolean isSolved = false;

    /**
     * Constructs a Puzzle with a specified name. The puzzle is initially unsolved.
     *
     * @param name The name of the puzzle.
     */
    public Puzzle(String name) {
        this.name = name;
        this.isSolved = false;
    }

    /**
     * Returns the name of the puzzle.
     *
     * @return The name of the puzzle.
     */
    public String getName() {
        return name;
    }

    /**
     * Checks if the puzzle has been solved.
     *
     * @return boolean indicating whether the puzzle is solved.
     */
    public boolean isSolved() {
        return isSolved;
    }

    /**
     * Sets the puzzle's solved status.
     *
     * @param solved boolean indicating the new solved status.
     */
    public void setSolved(boolean solved) {
        this.isSolved = solved;
    }

    /**
     * An abstract method that must be implemented by derived classes to define
     * the logic for solving the puzzle. Returns a boolean indicating the success
     * or failure of solving the puzzle.
     *
     * @return boolean indicating whether the puzzle was successfully solved.
     */
    public abstract boolean solvePuzzle();


}
