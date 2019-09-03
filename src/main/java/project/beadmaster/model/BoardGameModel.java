package project.beadmaster.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


import project.beadmaster.utils.Utility;


/**
 * This class represents the game model binding together all the other classes representing the elements of the game.
 * In fact, its attributes define all the elements needed to play the game. Its methods, on the other hand, allow to play the game
 * from the setup to its end.
 * This class not only executes the game, but also carries out the task of ensuring the full respect of its rules and constraints,
 * validating each action performed by the players.
 */
public class BoardGameModel {
    /**
     * 	Attributes
     */
    /**
     * This attribute stores the number of players at the beginnning of the game.
     */
    public static int NUMBER_OF_PLAYERS = 4;
    /**
     * This attribute is an array of elements of type Bar, which represents the vertical bars of the game.
     * It has dimension seven, since the vertical bars in the game are seven.
     */
    private Bar[] verticals;
    /**
     * This attribute is an array of elements of type Bar, which represents the horizontal bars of the game.
     * It has dimension seven, since the horizontal bars in the game are seven.
     */
    private Bar[] horizontals;
    /**
     * This attribute is an array of elements of type Player, which represents all the players of the game.
     * When a player is removed from the game, the corresponding instance in the list is marked as out of the game, setting his
     * attribute outOfGame to true.
     */
    private Player[] players;
    /**
     * This attribute is an array of elements of type Bead, which represents all the beads of every player placed on the grid.
     * When a bead is removed from the game, the corresponding element in the list is removed accordingly.
     */
    private Bead[] beads;
    /**
     * This attribute is a matrix of elements of the enumeration type GridHole. The purpose of this attribute is to be a reference
     * for getting the information about the state of each slot in the grid.
     */
    private GridHole gridHoles[][];


    /**
     * This attribute stores the number of the current player who has an action to perform.
     */
    private int playerTurn;

    /**
     * Variables memorizing information about a player's move.
     */
    /**
     *  This attribute memorizes the type of the bar moved by the current player.
     */
    private String typeOfBar;
    /**
     * This attribute memorizes the number of the bar moved by the current player.
     */
    private int numberOfBar;
    /**
     * This attribute memorizes the direction of the bar moved by a player.
     * The direction can either be inwards or outwards, moving a bar of exactly one position.
     */
    private String directionOfMove;

    /**
     * This attribute is a list of all the moves made in the game prior to the current move.
     * Each move is a string which holds the player number, the type of the bar moved and its position.
     */
    private List<String> historyOfMoves;

    /**
     * This attribute designates if the game is ongoing or over. In fact, when the game is over by the rules, it is set to true.
     */
    private boolean gameOver;
    /**
     * This attribute holds the winner player of the game.
     */
    private Player winner;

    /**
     * This attribute stores the scores for each player.
     */
    private int[] scoreForPlayer;

    /**
     * This attribute stores the date on which this instance of game is created.
     */
    private Date date;

    /**
     * This attribute holds the number of rounds played in a game.
     */
    private int numberOfRounds;

    /**
     * This attribute holds the position in the array of beads regarding the bead to initialize.
     */
    private int beadCounter;


    /**
     * Constructor
     */
    public BoardGameModel() {
        verticals = new Bar[Utility.NUMBER_OF_BARS];
        horizontals = new Bar[Utility.NUMBER_OF_BARS];

        gridHoles = new GridHole[Utility.GRID_SIDE][Utility.GRID_SIDE];

		/* The first player is always player number 1. Here the attribute is initialized to a zero value since it is useful
		 * for accessing the first element of the array players.
		 */
        playerTurn = 0;
        historyOfMoves = new ArrayList<String>();
        gameOver = false;

        date = new Date();
        numberOfRounds = 0;
        beadCounter = 0;
    }



    /**
     * Methods
     */

    /**
     * Getter methods
     */
    public Bar[] getVerticals() {
        return verticals;
    }

    /* It must be assumed that the checks regarding the index parameter have already been performed before invoking this method.
     * The index is number starting from 1 upwards.
     */
    public Bar getVerticalAtIndex(int index) {
        return verticals[index - 1];
    }

    public Bar[] getHorizontals() {
        return horizontals;
    }
	/* It must be assumed that the checks regarding the index parameter have already been performed before invoking this method.
	 * The index is number starting from 1 upwards.
	 */

    public Bar getHorizontalAtIndex(int index) {
        return horizontals[index - 1];
    }

    public int getNumberOfPlayers() {
        return NUMBER_OF_PLAYERS;
    }

    public Player getPlayerAtIndex(int index) {
        return players[index - 1];
    }

    public Bead getBeadAtIndex(int i) {
        return beads[i];
    }

    public int getNumberOfBeads() { return beads.length; }

    public GridHole[][] getGridHoles() {
        return gridHoles;
    }

    public List<String> getHistoryOfMoves() {
        return historyOfMoves;
    }

    public int getPlayerTurn() {
        return playerTurn;		// playerTurn attribute is a value starting from 0
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public String getWinnerName() {
        if (winner == null)
            return "";
        return winner.getName();
    }

    public int getNumberOfPlayersAlive() {
        int counter = 0;
        for (Player player : players)
            if (!player.isOutOfTheGame())
                counter++;
        return counter;
    }

    public int getStatisticsForPlayer(int playerNumber) {
        return scoreForPlayer[playerNumber - 1];
    }

    public Date getDate() {
        return date;
    }

    public int getNumberOfRounds() {
        return numberOfRounds;
    }

    public int getBeadCounter() {
        return beadCounter;
    }


    public int getNumberOfPlayerBeads(int player){
        int counter = 0;

        for ( int i = 0; i < getNumberOfBeads(); i++){
            if ( beads[i] != null && beads[i].getPlayerNumber() == player) {
                counter++;
            }
        }

        return counter;
    }


    /**
     * Setter methods. They provide a utility to perform the unit test and the method moveTest(String move) and are not relevant
     * to the correct execution of the game.
     * There are two things that deserved notice:
     * 		It is assumed that the values given as parameters have already undergone the validity checks in the methods of the invoking classes.
     * 		All the parameters passed to these methods are numbers from 1 upwards; for the model correctness though, they need to be decremented, since these numbers are used to access a position in the arrays or list in this class
     */

    /**	Sets the position of a specified horizontal bar.
     * @param position	the position to assign to the bar
     * @param number	the number of the horizontal bar starting from 1
     */
    public void setHorizontalBarPosition(BarPosition position, int number) {
        horizontals[number - 1].setPosition(position);
    }

    /**	Sets the position of a specified vertical bar.
     * @param position	the position to assign to the bar
     * @param number	the number of the vertical bar starting from 1
     */
    public void setVerticalBarPosition(BarPosition position, int number) {
        verticals[number - 1].setPosition(position);
    }

    /**
     * Sets the number of the player who should move in this turn.
     * @param playerNumber	the number of the player (starting from 0) for this turn
     */
    public void setPlayerTurn(int playerNumber) {
        playerTurn = playerNumber;
    }

    /**
     * Sets the number of players for playing the game. Remember to add as many instances of players as is the set number.
     * @param number	this number must be two, three or four
     */
    public void setNumberOfPlayers(int number) {
        NUMBER_OF_PLAYERS = number;
        players = new Player[number];
        beads = new Bead[number * Utility.NUMBER_OF_BEADS_PER_PLAYER];
        scoreForPlayer = new int[number];
    }



    /**
     * Initialize an instance of Player in the array containing all the players.
     * @param name	the name of the player to add
     * @param number	the number assigned to the player starting from 1
     */
    public void initializePlayer(String name, int number) {
        players[number - 1] = new Player(name, number);
    }

    /**
     * Sets the player out of the game.
     * @param number		the number of the player (starting from 1) to set out of the game
     */
    public void removePlayer(int number) {
        players[number - 1].setPlayerOutOfTheGame();
    }



    /**
     * This method is called to setup the board when the game starts. Its steps have been split in different methods.
     *
     */
    public void setupBoard() {
        initializeVerticalBars();
        initializeHorizontalBars();

        setVerticalBarsToRandomPosition();
        setHorizontalBarsToRandomPosition();

        for (int i = 0; i < Utility.NUMBER_OF_BARS; i++)
            setColumnInGrid(i);
        for (int i = 0; i < Utility.NUMBER_OF_BARS; i++)
            setRowInGrid(i);
    }


    /**
     * This method initializes the vertical bars, assigning a fixed configuration of slots to every bar.
     */
    public void initializeVerticalBars() {
        // create the vertical bars
        verticals[0] = new Bar(new boolean[] {true, false, false, false, false, true, false, true, true});
        verticals[1] = new Bar(new boolean[] {true, false, false, false, true, true, false, false, true});
        verticals[2] = new Bar(new boolean[] {true, false, true, false, false, true, false, true, true});
        verticals[3] = new Bar(new boolean[] {true, false, false, true, true, false, false, false, true});
        verticals[4] = new Bar(new boolean[] {true, true, false, false, false, true, false, true, true});
        verticals[5] = new Bar(new boolean[] {true, true, false, false, false, false, false, true, true});
        verticals[6] = new Bar(new boolean[] {true, false, false, true, false, false, true, false, true});
    }


    /**
     * This method initializes the horizontal bars, assigning a fixed configuration of slots to every bar.
     */
    public void initializeHorizontalBars() {
        // create the horizontal bars
        horizontals[0] = new Bar(new boolean[] {true, false, true, false, true, false, true, false, true});
        horizontals[1] = new Bar(new boolean[] {true, false, false, true, false, false, true, false, true});
        horizontals[2] = new Bar(new boolean[] {true, false, false, false, true, false, false, false, true});
        horizontals[3] = new Bar(new boolean[] {true, false, true, false, true, false, true, false, true});
        horizontals[4] = new Bar(new boolean[] {true, false, false, false, false, false, false, false, true});
        horizontals[5] = new Bar(new boolean[] {true, true, false, false, false, true, false, true, true});
        horizontals[6] = new Bar(new boolean[] {true, false, false, true, false, true, false, true, true});
    }


    /**
     * This method assigns a random position to each of the seven vertical bars using a random method generator from the class
     * java.class.Math.
     */
    public void setVerticalBarsToRandomPosition() {
        // assign a random position to the vertical bars
        for (int i = 0; i < Utility.NUMBER_OF_BARS; i++) {
            int randomPosition = (int) (Math.random() * 3);
            if (randomPosition == 0)
                verticals[i].setPosition(BarPosition.INNER);
            else if (randomPosition == 1)
                verticals[i].setPosition(BarPosition.CENTRAL);
            else /*if (randomPosition == 2)*/
                verticals[i].setPosition(BarPosition.OUTER);
        }
    }


    /**
     * This method assigns a random position to each of the seven horizontal bars using a random method generator from the class
     * java.class.Math.
     */
    public void setHorizontalBarsToRandomPosition() {
        // assign a random position to the horizontal bars
        for (int i = 0; i < Utility.NUMBER_OF_BARS; i++) {
            int randomPosition = (int) (Math.random() * 3);
            if (randomPosition == 0)
                horizontals[i].setPosition(BarPosition.INNER);
            else if (randomPosition == 1)
                horizontals[i].setPosition(BarPosition.CENTRAL);
            else /*if (randomPosition == 2)*/
                horizontals[i].setPosition(BarPosition.OUTER);
        }
    }


    /**
     * This method checks the slots for every vertical bar in the grid. If a slot is filled, then that cell, an instance of type
     * GridHole, is set to VERTICAL.
     * @param i		the number of the vertical bar to assess
     */
    public void setColumnInGrid(int i) {
        boolean[] barInGrid = verticals[i].getBarInGrid();
        for (int j = 0; j < Utility.GRID_SIDE; j++) {
            if (barInGrid[j]) {
                gridHoles[j][i] = GridHole.VERTICAL;
            }
        }
    }


    /**
     * This method checks every slot in the grid. If a slot was not marked as VERTICAL by the method setColumnInGrid(int i),
     * then it is necessary to assess if the slot of the horizontal bar in that position is filled or holed.
     * @param i		the number of the horizontal bar to assess
     */
    public void setRowInGrid(int i) {
        // check if the bar slots in the grid are filled or holed; if they are filled, set gridHoles[i][j] to horizontal
        boolean[] barInGrid = horizontals[i].getBarInGrid();
        for (int j = 0; j < Utility.GRID_SIDE; j++) {
            if (gridHoles[i][j] != GridHole.VERTICAL) {
                if (barInGrid[j]) {
                    gridHoles[i][j] = GridHole.HORIZONTAL;
                }
                else {
                    gridHoles[i][j] = GridHole.HOLE;
                }
            }
        }
    }



    /**
     * This method sets a bead in a given position on the grid, creating an instance of Bead and adding it to the list of beads.
     * It first makes some controls on the validity of the position given, in particular if the bead is placed in a hole, which is
     * illegal by the game rules, and if a grid cell has already another bead.
     * Each coordinate of the position of the bead is given in a number starting from 0 up to 6, while the number of the player
     * is a number from 1 to 4.
     * @param x	the abscissa of the position in which to place the bead
     * @param y	the ordinate of the position in which to place the bead
     * @param playerNumber		the number of the player who is positioning the bead
     * @return an instance of the class GridHole
     * @throws GameException        the exception thrown in case of illegal position of the bead
     */
    public GridHole placeBeadOnGrid(int x, int y, int playerNumber) throws GameException {
        // must check validity of the coordinate
        if ((x < 0 || x >= Utility.GRID_SIDE) || (y < 0 || y >= Utility.GRID_SIDE)) {
            throw new GameException("error: the cell on which you want to place your bead is outside the boundaries of the grid. Pick another position.");
        }

        if (gridHoles[x][y] == GridHole.HOLE) {
            throw new GameException("error: you can't put a bead in a hole. Put it either on a vertical bar or on a horizontal bar.");
        }
        else if (gridHoles[x][y] == GridHole.VERTICAL) {
            for (Bead bead : beads) {
                if (bead == null)
                    break;
                if (bead.getXCoordinate() == x && bead.getYCoordinate() == y)
                    throw new GameException("bead: " + bead + "; grid hole: " + gridHoles[x][y] + "; error: the cell on which you want to place your bead has already another bead. Pick another cell.");
            }
            Bead bead = new Bead(playerNumber, players[playerNumber].getName(), x, y);
            bead.setOnVertical(true);
            bead.setHasFallen(false);
            beads[beadCounter] = bead;
            beadCounter++;
        }
        else if (gridHoles[x][y] == GridHole.HORIZONTAL) {
            for (Bead bead : beads) {
                if (bead == null)
                    break;
                if (bead.getXCoordinate() == x && bead.getYCoordinate() == y)
                    throw new GameException("bead: " + bead + "; grid hole: " + gridHoles[x][y] + "; error: the cell on which you want to place your bead has already another bead. Pick another cell.");
            }
            Bead bead = new Bead(playerNumber, players[playerNumber].getName(), x, y);
            bead.setOnVertical(false);
            bead.setHasFallen(false);
            beads[beadCounter] = bead;
            beadCounter++;
        }

        return gridHoles[x][y];
    }



    /**
     * This method is called to perform a player's move. Its steps are split in different methods.
     * @param move
     * a string with this pre-established format:
     * 		the type of the bar
     * 		the number of the bar
     * 		the direction of the move
     */
    public void performAMove(String move) {
        checkValidityOfDirection(move);
        checkValidityOfConstraints();
        moveBar();
        checkBeads();
        checkPlayersStatus();
        calculateNextPlayer();
    }


    /**
     * This method checks the direction of the string representing the move in this format:
     *  	the string has length 3;
     *  	the type of the bar is either vertical or horizontal (v or h);
     *  	the number of the bar is between 1 and 7;
     *  	the direction of the move is either inwards or outwards (i or o).
     * There are two scenarios to check:
     * 		if the bar is in an inner position, it cannot be moved innerwards
     * 		if the bar is in an outer position, it cannot be moved outerwards
     * @param move		a string representing the player's move
     */
    public String checkValidityOfDirection(String move) {
        String message = "";
        typeOfBar = move.substring(0, 1);
        String stringNumberOfBar = move.substring(1, 2);
        numberOfBar = Integer.parseInt(stringNumberOfBar);
        directionOfMove = move.substring(2, 3);
        // for accessing the correct bar in the array bars must decrement
        numberOfBar--;

        BarPosition barPosition;
        if (typeOfBar.equals("v"))
            barPosition = verticals[numberOfBar].getPosition();
        else /*if (typeOfBar.equals("h"))*/
            barPosition = horizontals[numberOfBar].getPosition();

        if (directionOfMove.equals("i")) {
            if (barPosition == BarPosition.INNER) {
                message += "You cannot slide inwards a bar in an inner position.";
            }
        }

        else /*if (directionOfMove.equals("o"))*/ {
            if (barPosition == BarPosition.OUTER) {
                message += "You cannot slide outwards a bar in an outer position.";
            }
        }
        return message;
    }


    /**
     * This method checks the constraint regarding the legality of the move with respect to the previous moves performed.
     * There are two constraints to check:
     * 		It is prohibited to slide a bar that was slid in the previous turn by anyone of the current players' opponents
     * 		Only in the case of two players, a player cannot slide the same bar for more than two consecutive turns
     */
    public String checkValidityOfConstraints() {
        String message = "";
        int i = historyOfMoves.size() - 1;		// size() method returns the number of elements the list contains (value range from 1 onwards)
        String latestMove = "";
        String latestPlayerString = "";
        String latestBarType = "";
        String latestBarNumber = "";
        int latestPlayerNumber;
        System.out.println(i);
    	/*
    	 * This algorithm applies for every cases.It checks if the list contains the same move of the current player,
    	 *  and stops as soon as the previous move of that player is found.
    	 */
        while (i >= 0) {
            latestMove = historyOfMoves.get(i);
            latestPlayerString = latestMove.substring(0, 1);
            latestBarType = latestMove.substring(1, 2);
            latestBarNumber = latestMove.substring(2, 3);
            latestPlayerNumber = Integer.parseInt(latestPlayerString);
            System.out.println(latestPlayerNumber + latestBarType + latestBarNumber);
            if (latestPlayerNumber == playerTurn)
                break;
            else {
                if (latestBarType.equals(typeOfBar) && latestBarNumber.equals("" + numberOfBar)) {
                    message += "The bar slid by you is the same bar slid by another player in this turn.";
                }
            }

            i--;
        }
        System.out.println(historyOfMoves);
		/*
		 * This case only applies when there are two players left. It checks the two previous moves of the current player,
		 * assessing if they are different from the current move.
		 */
        if (this.getNumberOfPlayersAlive() == 2) {
            i = historyOfMoves.size() - 1;
            int counter = 0;
            int sameBar = 0;
            while (counter < 2 && i > 0) {
                latestMove = historyOfMoves.get(i);
                latestPlayerString = latestMove.substring(0, 1);
                latestBarType = latestMove.substring(1, 2);
                latestBarNumber = latestMove.substring(2, 3);
                latestPlayerNumber = Integer.parseInt(latestPlayerString);
                System.out.println(latestPlayerNumber + latestBarType + latestBarNumber);
                if (playerTurn == latestPlayerNumber) {
                    if (latestBarType.equals(typeOfBar) && latestBarNumber.equals("" + numberOfBar)) {
                        sameBar++;
                    }
                    counter++;
                }
                i--;
            }
            if (sameBar == 2)
                message += "You have already slid this bar twice in your previous turns.";
        }
        System.out.println(historyOfMoves);

    	/*
    	 * Add the move to the list. The first turn from the beginning of the game skips all controls and directly executes this instruction.
    	 */
        historyOfMoves.add(playerTurn + typeOfBar + numberOfBar);
        return message;
    }



    /**
     * This method is designated to move the bar, without the need to perform any validation since the string of the move has already
     * undergone all the necessary controls. Furthermore, it updates the grid representation as well.
     * This method is split in different methods, symmetric depending on the type of the bar.
     */
    public void moveBar() {
        if (typeOfBar.equals("v"))
            changeVerticalBarPosition();
        else /*if (typeOfBar.equals("h"))*/
            changeHorizontalBarPosition();

        if (typeOfBar.equals("v"))
            updateColumnInGrid();
        else /*if (typeOfBar.equals("h"))*/
            updateRowInGrid();
    }


    /**
     * This method changes the position of the vertical bar according to the direction of the move.
     */
    public void changeVerticalBarPosition() {
        BarPosition barPosition = verticals[numberOfBar].getPosition();
        if (directionOfMove.equals("i")) {
            if (barPosition == BarPosition.OUTER)
                verticals[numberOfBar].setPosition(BarPosition.CENTRAL);
            else //if (barPosition == BarPosition.CENTRAL)
                verticals[numberOfBar].setPosition(BarPosition.INNER);
        }
        else /*if (directionOfMove.equals("o"))*/ {
            if (barPosition == BarPosition.INNER)
                verticals[numberOfBar].setPosition(BarPosition.CENTRAL);
            else //if (barPosition == BarPosition.CENTRAL)
                verticals[numberOfBar].setPosition(BarPosition.OUTER);
        }
    }


    /**
     * This method changes the position of the horizontal bar according to the direction of the move.
     */
    public void changeHorizontalBarPosition() {
        BarPosition barPosition = horizontals[numberOfBar].getPosition();
        if (directionOfMove.equals("i")) {
            if (barPosition == BarPosition.OUTER)
                horizontals[numberOfBar].setPosition(BarPosition.CENTRAL);
            else //if (barPosition == BarPosition.CENTRAL)
                horizontals[numberOfBar].setPosition(BarPosition.INNER);
        }
        else /*if (directionOfMove.equals("o"))*/ {
            if (barPosition == BarPosition.INNER)
                horizontals[numberOfBar].setPosition(BarPosition.CENTRAL);
            else //if (barPosition == BarPosition.CENTRAL)
                horizontals[numberOfBar].setPosition(BarPosition.OUTER);
        }
    }


    /**
     * This method updates just the column in the grid related to the vertical bar moved.
     */
    public void updateColumnInGrid() {
        // Get the portion of the vertical bar in the grid
        boolean[] vBarInGrid = verticals[numberOfBar].getBarInGrid();
        // The number of the vertical bar
        int j = numberOfBar;
        for (int i = 0; i < Utility.NUMBER_OF_BARS; i++) {
            // If the j-th slot of the vertical bar is filled
            if (vBarInGrid[i]) {
                if (gridHoles[i][j] != GridHole.VERTICAL)
                    gridHoles[i][j] = GridHole.VERTICAL;
            }
            else {
                // Get the j-th slot of the i-th horizontal bars in the grid
                boolean[] hBarInGrid = horizontals[i].getBarInGrid();
                // If the j-th slot of the horizontal bar is filled
                if (hBarInGrid[j]) {
                    if (gridHoles[i][j] != GridHole.HORIZONTAL)
                        gridHoles[i][j] = GridHole.HORIZONTAL;
                }
                else {
                    if (gridHoles[i][j] != GridHole.HOLE)
                        gridHoles[i][j] = GridHole.HOLE;
                }
            }
        }
    }


    /**
     * This method updates just the column in the grid related to the horizontal bar moved.
     */
    public void updateRowInGrid() {
        // Get the portion of the horizontal bar in the grid
        boolean[] hBarInGrid = horizontals[numberOfBar].getBarInGrid();
        // The number of the horizontal bar
        int i = numberOfBar;
        for (int j = 0; j < Utility.NUMBER_OF_BARS; j++) {
            // Get the j-th vertical bar in the grid
            boolean[] vBarInGrid = verticals[j].getBarInGrid();
            // If the i-th slot of the vertical bar is filled
            if (vBarInGrid[i]) {
                if (gridHoles[i][j] != GridHole.VERTICAL)
                    gridHoles[i][j] = GridHole.VERTICAL;
            }
            else {
                // If the j-th slot of the horizontal bar is filled
                if (hBarInGrid[j]) {
                    if (gridHoles[i][j] != GridHole.HORIZONTAL)
                        gridHoles[i][j] = GridHole.HORIZONTAL;
                }
                else {
                    if (gridHoles[i][j] != GridHole.HOLE)
                        gridHoles[i][j] = GridHole.HOLE;
                }
            }
        }
    }


    /**
     * This method checks if the moved bar has caused some beads to fall down. Plus, it checks if any player has zero beads
     * and sets his attribute outOfGame to true accordingly.
     */
    public String checkBeads() {
        String message = "";
        for (int i = 0; i < beads.length; i++) {
            if (beads[i] == null)
                break;
            int xCoordinate = beads[i].getXCoordinate();
            int yCoordinate = beads[i].getYCoordinate();

            if (gridHoles[xCoordinate][yCoordinate] == GridHole.HOLE) {
                message += beads[i].getPlayerName() + " has lost a bead.";
                // if the bead that falls down belongs to the player moving the bar, then this player loses 10 points
                if (playerTurn == (beads[i].getPlayerNumber() - 1))
                    scoreForPlayer[playerTurn] -= 10;
                // if the bead that falls down belongs to an opponent of the player moving the bar, then this player gains 10 points
                else //if (playerTurn != (bead.getPlayerNumber() - 1))
                    scoreForPlayer[playerTurn] += 10;
                // In the Bead class the number of the player starts from 1 to 4, so must decrement here
                players[beads[i].getPlayerNumber() - 1].decrementNumberOfBeads();

                if (players[beads[i].getPlayerNumber() - 1].getNumberOfBeads() == 0)
                    players[beads[i].getPlayerNumber() - 1].setPlayerOutOfTheGame();
                // must remove the iterator not to launch the ConcurrentModificationException
                beads[i].setHasFallen(true);
            }
            // Checks if a bead positioned on a vertical bar with a hole in that slot, has a horizontal filled slot underneath
            else if (beads[i].getOnVertical() && gridHoles[xCoordinate][yCoordinate] == GridHole.HORIZONTAL) {
                beads[i].setOnVertical(false);
            }
        }

        return message;
    }


    /**
     * This method checks how many players are still in the game. In the checkBeads() method, a player who had lost all his beads
     * had been marked as out of the game. So this method just needs to perform a check on the status of the players.
     * 		If all the players are marked as out of the game, that means they all lost all their beads, and the winner
     * 		is the last who performed a move.
     * 		If only one player is not marked as out of the game, then he is the winner of the game.
     */
    public String checkPlayersStatus() {
        String message = "";
        int numberOfPlayersAlive = this.getNumberOfPlayersAlive();

        if (numberOfPlayersAlive == 0) {
            gameOver = true;
            // The winner is the one who moved the bar
            winner = players[playerTurn];
            message += "The winner is " + players[playerTurn];
            // the winner gets 100 points
            scoreForPlayer[playerTurn] = 100;
        }

        if (numberOfPlayersAlive == 1) {
            // The only player in the array not marked as being out of the game
            for (Player player : players)
                if (!player.isOutOfTheGame()) {
                    gameOver = true;
                    winner = player;
                    message += "The winner is " + players[playerTurn];

                    // the winner gets 100 points
                    scoreForPlayer[player.getPlayerNumber() - 1] = 100;
                    // plus, 20 points per bead are added to the winner
                    scoreForPlayer[player.getPlayerNumber() - 1] = player.getNumberOfBeads() * 20;
                }
        }

        return message;
    }


    /**
     * This method calculates the player for the next turn. The first action this method does is assessing if the game is over;
     * if that is the case, it will stop its execution, to avoid an infinite loop in search for the next player.
     *
     * @return the number representing the next player
     */
    public int calculateNextPlayer() {
        // increment number of rounds
        numberOfRounds++;

        if (gameOver)
            return -1;
        int i = 1;
        while (true) {
            if (!players[(playerTurn + i) % players.length].isOutOfTheGame())
                break;
            i++;
        }

        return playerTurn = (players[(playerTurn + i) % players.length].getPlayerNumber() - 1); 		// the number must be from 0 upwards
    }





    /**
     * This method converts the state of the game to a string of this format:
     * 		a character string representing the number of players;
     * 		a character representing the number of the first moving player;
     * 		seven characters representing the positions of the horizontal bars;
     * 		seven characters representing the positions of the vertical bars;
     * 		forty-nine characters representing the grid, with 0 characters representing a cell with no beads, and a number
     * 			among 1, 2, 3, 4 representing the bead of the player with that number.
     *
     * 	@return a string representing the current configuration of the game
     */
    public String toString() {
        StringBuffer buffer = new StringBuffer();

        // Convert the number of players to string
        buffer.append(NUMBER_OF_PLAYERS);

        // Convert the player turn to string
        buffer.append(playerTurn + 1);

        // Convert the horizontal position of the bars to string
        for (int i = 0; i < Utility.NUMBER_OF_BARS; i++) {
            if (horizontals[i].getPosition() == BarPosition.INNER)
                buffer.append("0");
            else if (horizontals[i].getPosition() == BarPosition.CENTRAL)
                buffer.append("1");
            else if (horizontals[i].getPosition() == BarPosition.OUTER)
                buffer.append("2");
        }

        // Convert the vertical position of the bars to string
        for (int i = 0; i < Utility.NUMBER_OF_BARS; i++) {
            if (verticals[i].getPosition() == BarPosition.INNER)
                buffer.append("0");
            else if (verticals[i].getPosition() == BarPosition.CENTRAL)
                buffer.append("1");
            else if (verticals[i].getPosition() == BarPosition.OUTER)
                buffer.append("2");
        }

        // Display the board
        for (int i = 0; i < Utility.GRID_SIDE; i++) {
            for (int j = 0; j < Utility.GRID_SIDE; j++) {
                int flag = 0;
                for (Bead bead : beads) {
                    // If a bead has the same position as the cell being examined append the player number and set the flag to 1
                    if (i == bead.getXCoordinate() && j == bead.getYCoordinate()) {
                        buffer.append(bead.getPlayerNumber());
                        flag = 1;
                    }
                }
                if (flag == 0)
                    buffer.append("0");
            }
        }

        return buffer.toString();
    }

}