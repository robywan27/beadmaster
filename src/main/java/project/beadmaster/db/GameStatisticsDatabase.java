package project.beadmaster.db;

import java.util.List;

import project.beadmaster.model.BoardGameModel;
import project.beadmaster.statistics.GameStatistics;
import project.beadmaster.statistics.PlayerStatistics;

/**
 * This interface has a bridge role between the domain classes and the relational database; in fact, this interface
 * demands to the classes implementing it to provide a body its methods, that either allow to convert objects in relational entities
 * or viceversa to convert relational entitities in objects.
 */
public interface GameStatisticsDatabase {
    /**
     * This method allows to store some informations about a player in the database.
     * @param playerName the name of the player
     * @param points the points assigned to the player in a game
     */
    void savePlayer(String playerName, int points);

    /**
     * This method allows to save some informations about a game in the database.
     * @param game an instance of the game
     */
    void saveGame(BoardGameModel game);

    /**
     * This method allows to retrieve from the database a list of players ordered by a decreasing number of points.
     * @return
     */
    List<PlayerStatistics> retrieveBestPlayersStatistics();

    /**
     * This method allows to retrieve from the database a list of the games ordered by a decreasing date, that is, from most recent to oldest.
     * @return
     */
    List<GameStatistics> retrieveLatestGames();
}
