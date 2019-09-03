package project.beadmaster.statistics;

/**
 * The purpose of this class is that of storing the information regarding each player needed to display the players with the best scores.
 * The setter methods are given the values retrieved from the database, while the getter methods are used by the BestScoresActivity
 * class in order to display the statistics of each player.
 */
public class PlayerStatistics {
    /**
     * This attribute stores the name of this player.
     */
    private String name;
    /**
     * This attribute stores the total points accumulated by the player in all the games played by him.
     */
    private long points;
    /**
     * This attribute stores the total number of games played by this player.
     */
    private int numberOfGamesPlayed;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getPoints() {
        return points;
    }

    public void setPoints(long points) {
        this.points = points;
    }

    public int getNumberOfGamesPlayed() {
        return numberOfGamesPlayed;
    }

    public void setNumberOfGamesPlayed(int numberOfGamesPlayed) {
        this.numberOfGamesPlayed = numberOfGamesPlayed;
    }
}
