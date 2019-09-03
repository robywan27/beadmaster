package project.beadmaster.model_test;



import java.util.ArrayList;
import java.util.List;


import project.beadmaster.model.*;
import project.beadmaster.model.GameException;
import project.beadmaster.utils.Utility;


/**
 * This class implements the method moveTest(String) which tests the correct execution of the BoardGameModel class.
 */
public class MoveTester {
    /**
     *  attributes
     */
    private String numberOfPlayersString = "";
    private String movingPlayerString = "";
    private String horizontalPositions = "";
    private String verticalPositions = "";
    private String beadsInGrid = "";
    private String move1 = "";

    private List<String> moves;

    private int numberOfPlayers;
    private int movingPlayer;

    private String configuration = "";
    private BoardGameModel boardGame;



    /**
     * Constructor
     */
    public MoveTester() {
        moves = new ArrayList<String>();
        boardGame = new BoardGameModel();

        // initialize the bars
        boardGame.initializeVerticalBars();
        boardGame.initializeHorizontalBars();
    }


    /**
     * This method receives a string representing a configuration of the game as a parameter. The length of this string must be
     * at least 68 characters.
     * This method must first set the state of the game model according to the string parameter performing the due checks,
     * then it must perform the move or moves, and finally return the state of the model in a string, or in case of error
     * return an error message.
     * @param inputConfiguration		a string representing a configuration of the game
     * @return		a string representing the configuration of the game after some moves have been performed
     * @throws GameException        an exception thrown if some rules in the game have been violated
     * @throws NumberFormatException		an exception thrown if the parseInt() method is given a non numeric string value
     */
    public String moveTest(String inputConfiguration) throws GameException, NumberFormatException {
        configuration = inputConfiguration;
        if (configuration.length() < 68)
            return "The input string representing the configuration has less than 68 characters.";

        parseConfigurationString();

        areThereOtherMoves();

        parseToInt();

        setPlayers();

        setHorizontalBars();

        setVerticalBars();

        for (int i = 0; i < Utility.NUMBER_OF_BARS; i++)
            boardGame.setColumnInGrid(i);
        for (int i = 0; i < Utility.NUMBER_OF_BARS; i++)
            boardGame.setRowInGrid(i);

        setBeadsOnGrid();

        // set the moving player
        boardGame.setPlayerTurn(movingPlayer);

        performAMove();

        performOtherMoves();

        // return the correct status of the game
        return boardGame.toString();
    }


    /**
     * This method extracts the substrings from the string configuration according to this convention:
     *  	 The first character represents the number of players participating in the game (from 1 to 4);
     *		 The second represents the moving player (from 1 to 4);
     *		 The next 7 characters represent the positions of the horizontal bars;
     *		 The next 7 characters represent the positions of the vertical bars;
     *		 The next 49 characters represent the beads in the grid;
     */
    public void parseConfigurationString() {
        numberOfPlayersString = configuration.substring(0, 1);
        movingPlayerString = configuration.substring(1, 2);
        horizontalPositions = configuration.substring(2, 9);
        verticalPositions = configuration.substring(9, 16);
        beadsInGrid = configuration.substring(16, 65);
        move1 = configuration.substring(65, 68);
    }


    /**
     * 	 This method checks if the there are more than one moves.
     */
    public void areThereOtherMoves() {
        if (configuration.length() > 68) {
            int numberOfMoves = (configuration.length() - 68) / 3;
            int shift = 0;
            int counter = 0;
            while (counter < numberOfMoves) {
                moves.add(configuration.substring(68 + shift, 68 + shift + 3));
                shift += 3;
                counter++;
            }
        }
    }


    /**
     * This method parses the numeric strings in integer values.
     * @throws GameException		an exception thrown if this method gets illegal input
     * @throws NumberFormatException		an exception thrown if the parseInt() method is given a non numeric string value
     */
    public void parseToInt() throws GameException, NumberFormatException {
        try {
            numberOfPlayers = Integer.parseInt(numberOfPlayersString);
        }
        catch (NumberFormatException e) {
            throw new GameException("error: You passed an invalid number of players.");
        }
        if (numberOfPlayers != 2 && numberOfPlayers != 4)
            throw new GameException("error: The number of players is not correct.");

        try {
            movingPlayer = Integer.parseInt(movingPlayerString);
        }
        catch (NumberFormatException e) {
            throw new GameException("error: You passed an invalid moving player.");
        }
        if (movingPlayer < 0 && movingPlayer > 4)
            throw new GameException("error: The current player is not one of the players in game.");
    }


    /**
     * This method initializes the specified number of players, resetting their number of beads.
     */
    public void setPlayers() {
        boardGame.setNumberOfPlayers(numberOfPlayers);

        for (int i = 1; i <= numberOfPlayers; i++) {
            boardGame.initializePlayer("Player " + i, i);
            boardGame.getPlayerAtIndex(i).resetNumberOfBeads();
        }
    }


    /**
     * This method initializes the positions of the horizontal bars according to the string configuration.
     * @throws GameException		an exception thrown if the string representing the configuration of the positions of the horizontal bars as an illegal format
     */
    public void setHorizontalBars() throws GameException {
        for (int i = 0; i < 7; i++) {
            String position = horizontalPositions.substring(i, i + 1);
            BarPosition barPosition;
            if (position.equals("0"))
                barPosition = BarPosition.INNER;
            else if (position.equals("1"))
                barPosition = BarPosition.CENTRAL;
            else if (position.equals("2"))
                barPosition = BarPosition.OUTER;
            else
                throw new GameException("error: You must pass a sequence of 0, 1, 2.");

            boardGame.setHorizontalBarPosition(barPosition, i + 1);
        }
    }


    /**
     * This method initializes the positions of the vertical bars according to the string configuration.
     * @throws GameException		an exception thrown if the string representing the configuration of the positions of the vertical bars as an illegal format
     */
    public void setVerticalBars() throws GameException {
        for (int i = 0; i < 7; i++) {
            String position = verticalPositions.substring(i, i + 1);
            BarPosition barPosition;
            if (position.equals("0"))
                barPosition = BarPosition.INNER;
            else if (position.equals("1"))
                barPosition = BarPosition.CENTRAL;
            else if (position.equals("2"))
                barPosition = BarPosition.OUTER;
            else
                throw new GameException("error: You must pass a sequence of 0, 1, 2.");

            boardGame.setVerticalBarPosition(barPosition, i + 1);
        }
    }


    /**
     * This method places the beads on the grid in the model, and increments the number of beads held by its owner.
     * @throws GameException		the exception thrown in case of illegal position of the bead
     */
    public void setBeadsOnGrid() throws GameException {
        for (int i = 0; i < 49; i++) {
            String cell = beadsInGrid.substring(i, i + 1);
            if (!cell.equals("0") && !cell.equals("1") && !cell.equals("2") && !cell.equals("3") && !cell.equals("4"))
                throw new GameException("error: The configuration of the board is not legal.");
            if (!cell.equals("0")) {
                boardGame.placeBeadOnGrid(i / 7, i % 7, Integer.parseInt(cell));
                boardGame.getPlayerAtIndex(Integer.parseInt(cell)).incrementNumberOfBeads();
            }
        }
    }


    /**
     * This method performs a move.
     * @throws GameException		an exception thrown if some rules in the game have been violated
     */
    public void performAMove() throws GameException {
        boardGame.performAMove(move1);
    }


    /**
     * This method performs the other moves if there are.
     * @throws GameException		an exception thrown if some rules in the game have been violated
     */
    public void performOtherMoves() throws GameException {
        int i = 0;
        while (!moves.isEmpty())
            boardGame.performAMove(moves.remove(i));
    }
}
