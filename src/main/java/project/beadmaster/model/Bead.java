package project.beadmaster.model;

/**
 * This class represents a bead to be located at a fixed position in the grid, and belongs to one player only.
 */
public class Bead {
    /**
     * This attribute holds the number of the player possessing this bead, and it is a number starting from 1.
     * Since a player is given an unchangeable number, this filed is declared as final.
     */
    private final int playerNumber;
    /**
     * This attribute holds the name of the owner of this bead.
     */
    private String playerName;
    /**
     * This attribute holds the abscissa (horizontal coordinate) of the bead in the grid; it is a number starting from 0.
     */
    private final int x;
    /**
     * This attribute holds the ordinate (vertical coordinate) of the bead in the grid; it is a number starting from 0.
     */
    private final int y;
    /**
     *  This attribute indicates if this bead is placed on a vertical bar or on a horizontal bar, so its type is boolean.
     *  If the bead is on the vertical bar, then onVertical is true.
     *  If the bead is on the horizontal bar, then onVertical is false.
     */
    private boolean onVertical;
    /**
     * This attribute indicates if this bead has fallen in a hole (true value), or not (false value).
     */
    private boolean hasFallen;


    /** Constructor */
    public Bead(int playerNumber, String playerName, int x, int y) {
        this.playerNumber = playerNumber;
        this.playerName = playerName;
        this.x = x;
        this.y = y;
    }


    /** Getter and setter methods */
    public int getPlayerNumber() {
        return playerNumber;
    }
    public int getXCoordinate() {
        return x;
    }
    public int getYCoordinate() {
        return y;
    }
    public boolean getOnVertical() {
        return onVertical;
    }
    public void setOnVertical(boolean onVertical) {
        this.onVertical = onVertical;
    }
    public boolean isHasFallen() {
        return hasFallen;
    }
    public void setHasFallen(boolean hasFallen) {
        this.hasFallen = hasFallen;
    }
    public String getPlayerName() {
        return playerName;
    }
    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    /**
     * This method provides a string representing the information about the bead.
     */
    public String toString() {
        String beadRepresentation = "Bead of " + playerName + " at position: (" + this.getXCoordinate() + ", " + this.getYCoordinate() + ") on the board";
        return beadRepresentation;
    }
}
