package project.beadmaster.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import project.beadmaster.db.GameStatisticsDatabase;
import project.beadmaster.model.BoardGameModel;
import project.beadmaster.statistics.GameStatistics;
import project.beadmaster.statistics.PlayerStatistics;


/**
 * This class provides an SQLite implementation to the interface GameStatisticsDatabase for an Android application.
 * It holds an instance of the database in which to store all the relevant information regarding the games and players.
 */
public class GameStatisticsDatabaseImplementation extends SQLiteOpenHelper implements GameStatisticsDatabase {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "BeadMaster";

    private static final String GAME_STATISTICS = "gameStatistics";
    private static final String ID = "id";
    private static final String DATE = "date";
    private static final String WINNER = "winner";
    private static final String NUMBER_OF_PLAYERS = "numberOfPlayers";
    private static final String NUMBER_OF_BEADS_LEFT = "numberOfBeadsLeft";
    private static final String NUMBER_OF_ROUNDS = "numberOfRounds";

    private static final String PLAYER_STATISTICS = "playerStatistics";
    private static final String NAME = "name";
    private static final String POINTS = "points";
    private static final String NUMBER_OF_GAMES_PLAYED = "numberOfGamesPlayed";

    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yy hh:mm:ss", Locale.ITALY);


    public GameStatisticsDatabaseImplementation(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String gameQuery = "CREATE TABLE " + GAME_STATISTICS + " (" + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + DATE + " DATE, " +
                WINNER + " TEXT, " + NUMBER_OF_PLAYERS + " INTEGER, " + NUMBER_OF_BEADS_LEFT + " INTEGER, "
                + NUMBER_OF_ROUNDS + " INTEGER)";

        db.execSQL(gameQuery);

        String playerQuery = "CREATE TABLE " + PLAYER_STATISTICS + " (" + NAME + " TEXT PRIMARY KEY, " +
                POINTS + " INTEGER, " + NUMBER_OF_GAMES_PLAYED + " INTEGER)";

        db.execSQL(playerQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + GAME_STATISTICS);
        db.execSQL("DROP TABLE IF EXISTS " + PLAYER_STATISTICS);

        onCreate(db);
    }

    /**
     * Since the key for player table is name, when saving a player's statistics some precautions need to be done.
     * If the name is not present in the database, then it is inserted a new entry.
     * Otherwise, the values of the player must be updated.
     */
    @Override
    public void savePlayer(String playerName, int points) {
        SQLiteDatabase database = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(NAME, playerName);

        int numberOfGames = 0;
        boolean isPlayerInDatabase = false;
        Cursor cursor = database.rawQuery("SELECT " + NUMBER_OF_GAMES_PLAYED + ", " + POINTS +" FROM  " + PLAYER_STATISTICS + " WHERE " + NAME + " = ?", new String[] {playerName});
        if (cursor.moveToFirst()) {
            do {
                numberOfGames = cursor.getInt(0) + 1;
                points = cursor.getInt(1) + points;
                isPlayerInDatabase = true;
            } while (cursor.moveToNext());
        }
        cursor.close();

        values.put(POINTS, points);
        values.put(NUMBER_OF_GAMES_PLAYED, numberOfGames);

        if (isPlayerInDatabase) {
            database.update(PLAYER_STATISTICS, values, NAME + " = ? ", new String[] {playerName});
        }
        else {
            database.insert(PLAYER_STATISTICS, null, values);
        }

        database.close();
    }

    @Override
    public void saveGame(BoardGameModel game) {
        SQLiteDatabase database = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(DATE, simpleDateFormat.format(game.getDate()));
        values.put(WINNER, game.getWinnerName());
        values.put(NUMBER_OF_PLAYERS, game.getNumberOfPlayers());
        values.put(NUMBER_OF_BEADS_LEFT, game.getNumberOfBeads());
        values.put(NUMBER_OF_ROUNDS, game.getNumberOfRounds());

        database.insert(GAME_STATISTICS, null, values);
        database.close();
    }

    @Override
    public List<PlayerStatistics> retrieveBestPlayersStatistics() {
        List<PlayerStatistics> playerStatistics = new ArrayList<>();

        String selectQuery = "SELECT  * FROM  " + PLAYER_STATISTICS + " ORDER BY " + POINTS + " DESC LIMIT 10";

        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cursor = database.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                PlayerStatistics ps = new PlayerStatistics();

                ps.setName(cursor.getString(0));
                ps.setPoints(cursor.getInt(1));
                ps.setNumberOfGamesPlayed(cursor.getInt(2));

                playerStatistics.add(ps);
            } while (cursor.moveToNext());
        }

        cursor.close();

        return playerStatistics;
    }

    @Override
    public List<GameStatistics> retrieveLatestGames() {
        List<GameStatistics> gameStatistics = new ArrayList<>();

        String selectQuery = "SELECT  * FROM  " + GAME_STATISTICS + " ORDER BY " + DATE + " DESC LIMIT 10";

        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cursor = database.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                GameStatistics gs = new GameStatistics();

                try {
                    gs.setDate(simpleDateFormat.parse(cursor.getString(1)));
                } catch (ParseException e) {
                    Log.e("error in date parsing", "wrong parsing of date in game statistics database.");
                }
                gs.setWinner(cursor.getString(2));
                gs.setNumberOfPlayers(cursor.getInt(3));
                gs.setNumberOfBeadsLeft(cursor.getInt(4));
                gs.setNumberOfRounds(cursor.getInt(5));

                gameStatistics.add(gs);
            } while (cursor.moveToNext());
        }

        cursor.close();

        return gameStatistics;
    }
}
