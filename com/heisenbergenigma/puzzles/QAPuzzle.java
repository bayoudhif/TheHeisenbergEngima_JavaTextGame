package com.heisenbergenigma.puzzles;

import com.heisenbergenigma.items.Item;
import com.heisenbergenigma.main.GameState;
import com.heisenbergenigma.main.MainGame;
import com.heisenbergenigma.player.Player;
import com.heisenbergenigma.ui.UiManager;
import com.heisenbergenigma.utils.GameUtils;
import com.heisenbergenigma.utils.Narrator;
import org.ietf.jgss.GSSManager;

import java.util.List;
import java.util.ArrayList;

/**
 * The QAPuzzle class extends the Puzzle class and represents a trivia-style puzzle
 * where the player needs to answer a series of questions correctly. Each question
 * comes with multiple choices, and the player must select the correct answer.
 */
public class QAPuzzle extends Puzzle {
    private static class Question {
        String query;
        List<String> choices;
        String correctAnswer;

        Question(String query, List<String> choices, String correctAnswer) {
            this.query = query;
            this.choices = choices;
            this.correctAnswer = correctAnswer;
        }


    }

    private List<Question> questions;

    /**
     * Constructs a new QAPuzzle with an empty list of questions.
     */
    public QAPuzzle() {
        super("Q&A Puzzle");
        this.questions = new ArrayList<>();

    }

    /**
     * Adds a question with multiple choices and a correct answer to the puzzle.
     *
     * @param query         The question text.
     * @param choices       A list of answer choices.
     * @param correctAnswer The correct answer to the question.
     */
    public void addQuestion(String query, List<String> choices, String correctAnswer) {
        questions.add(new Question(query, choices, correctAnswer));
    }

    /**
     * Solves the Q&A puzzle. The player is presented with a series of questions and
     *
     * must answer each one correctly. Successfully answering all questions grants
     * a reward and advances the game state.
     *
     * @return boolean indicating whether the puzzle was successfully solved.
     */
    @Override
    public boolean solvePuzzle() {
        // Check if the customer's hint has been received and if there are questions to answer.
        if (!GameState.isCustomerHintReceived() || questions.isEmpty()) {
            return false;
        }
        // Initialize the UI manager for user interaction.
        UiManager uiManager = new UiManager(MainGame.getInstance());
        // Narrate an introduction to the challenge of the puzzle.
        Narrator.narrate("Gathering your resolve, you step up to the challenge. The answers to the 'Breaking Bad'-themed puzzle stand between you and the secrets behind the door. It's time to begin.");
        // Prompt the player to proceed with the puzzle.
        GameUtils.promptToProceed();
        // Iterate through each question in the puzzle.
        for (Question question : questions) {
            // Display the question to the player.
            System.out.println(question.query);
            int choiceIndex = 1;
            // Display each choice for the current question.
            for (String choice : question.choices) {
                System.out.println(choiceIndex++ + ": " + choice);
            }
            // Ask the player to input their answer.
            System.out.print("Your answer (number): ");
            int answerIndex = uiManager.getIntInput() - 1;
            // Check if the player's answer is incorrect.
            if (!question.choices.get(answerIndex).equals(question.correctAnswer)) {
                System.out.println("Incorrect answer. Puzzle failed.");
                return false;
            }
        }
        // Confirm that all answers are correct and the puzzle is solved.
        Narrator.narrate(GameUtils.ANSI_GREEN + "All answers correct! Puzzle solved." + GameUtils.ANSI_RESET);
        // Get the current player and update the game state to reflect the puzzle's completion.
        Player player = Player.currentPlayer();
        GameState.setQAPuzzleSolved(true);
        this.setSolved(true);
        // Determine and provide the appropriate reward based on whether another puzzle is solved.
        if (GameState.isLicensePuzzleSolved()) {
            // Reward the player with a missing page if the license puzzle is solved.
            Item rewardItem = new Item("Missing Page", "A tattered page that seems to fit perfectly in the 'Cryptic Notebook'. It's filled with intricate details and a distinct address.");
            player.addItemToInventory(rewardItem);
            Narrator.narrate(GameUtils.ANSI_YELLOW + "\"You find a worn page hidden among the clutter. Its edges are frayed, blending with the debris. Carefully, you flatten it out. The handwriting on the page matches the 'Cryptic Notebook.'\n As you read, a clear address emerges among the cryptic writings. It's unmistakable – Saul Goodman's office. Could this be the clue you've been searching for?\"" + GameUtils.ANSI_RESET);

        } else {
            // Reward the player with a missing page regardless.
            Item rewardItem = new Item("Missing Page", "A tattered page. It's filled with intricate details and a distinct address.");
            player.addItemToInventory(rewardItem);
            Narrator.narrate(GameUtils.ANSI_YELLOW + "\"You find a worn page hidden among the clutter. Its edges are frayed, blending with the debris. Carefully, you flatten it out.\n As you read, a clear address emerges among the cryptic writings. It's unmistakable – SG's office. Could this be the clue you've been searching for?\"" + GameUtils.ANSI_RESET);

        }
        // Return true indicating the puzzle was successfully solved.
        return true;

    }

}
