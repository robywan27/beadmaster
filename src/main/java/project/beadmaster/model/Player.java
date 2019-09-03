package project.beadmaster.model;


import project.beadmaster.utils.Utility;

/**
 * This class represents a player of the game. A player is identified by his number in every round and by a name.
 */
public class Player {
    /**
     * A player is assigned a name, which cannot be changed.
     */
    private final String name;
    /**
     * This number indicates the turn in a round in which this player should place a bead and make a move; it is a number starting from 1.
     * This number cannot be changed, even though other players are removed from the game.
     */
    private final int number;
    /**
     * This attribute stores the number of beads held by this player.
     * Every player is assigned five beads at the beginning of the game, and is removed from the game when he loses all his beads.
     */
    private int beadsHeld;
    /**
     * This attribute designates if a player is still in the game, i.e. if he still holds at least one bead, or if he has lost all his beads
     * and must be removed from the game.
     */
    private boolean outOfGame;


    /** Constructor */
    public Player(String name, int number) {
        this.name = name;
        this.number = number;
        this.beadsHeld = Utility.NUMBER_OF_BEADS_PER_PLAYER;
        this.outOfGame = false;
    }

    /** Getter and setter methods */
    public String getName() {
        return name;
    }
    public int getPlayerNumber() {
        return number;
    }
    public int getNumberOfBeads() {
        return beadsHeld;
    }
    public boolean isOutOfTheGame() {
        return outOfGame;
    }
    public void decrementNumberOfBeads() {
        beadsHeld--;
    }
    public void setPlayerOutOfTheGame() {
        outOfGame = true;
    }

    /* These methods should be used just for the moveTest(String) method */
    public void resetNumberOfBeads() {
        beadsHeld = 0;
    }
    public void incrementNumberOfBeads() {
        beadsHeld++;
    }
    /*********************************************************************/

    /**
     * This method provides a string representing the information about the player.
     */
    public String toString() {
        return "PLAYER:\nName: " + name + "; number: " + number + "; number of beads: " + beadsHeld + "; out of the game? " + outOfGame  + ".\n";
    }
}
