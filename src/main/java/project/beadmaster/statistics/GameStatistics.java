package project.beadmaster.statistics;

import java.util.Date;

/**
 * The purpose of this class is that of storing the information regarding each game needed to display the history of the games.
 * The setter methods are given the values retrieved from the database, while the getter methods are used by the GameHistoryActivity
 * class in order to display the statistics of each game.
 */

public class GameStatistics {
    /**
     * This attribute holds the date of creation of this game.
     */
    private Date date;
    /**
     * This attribute stores the number of players in this game.
     */
    private int numberOfPlayers;
    /**
     * This attribute stores the name of the winner of this game.
     */
    private String winner;
    /**
     * This attribute stores the number of beads at the end of this game.
     */
    private int numberOfBeadsLeft;
    /**
     * This attribute stores the number of rounds played in this game.
     */
    private int numberOfRounds;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getNumberOfPlayers() {
        return numberOfPlayers;
    }

    public void setNumberOfPlayers(int numberOfPlayers) {
        this.numberOfPlayers = numberOfPlayers;
    }

    public String getWinner() {
        return winner;
    }

    public void setWinner(String winner) {
        this.winner = winner;
    }

    public int getNumberOfBeadsLeft() {
        return numberOfBeadsLeft;
    }

    public void setNumberOfBeadsLeft(int numberOfBeadsLeft) {
        this.numberOfBeadsLeft = numberOfBeadsLeft;
    }

    public int getNumberOfRounds() {
        return numberOfRounds;
    }

    public void setNumberOfRounds(int numberOfRounds) {
        this.numberOfRounds = numberOfRounds;
    }
}
