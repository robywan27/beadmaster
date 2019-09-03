package beadmaster.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;

import project.beadmaster.classes.*;
import project.beadmaster.utils.Utility;

import org.junit.Test;



/**
 * This class has been designed to perform unit tests in order to assess the correct execution of the methods provided by the
 * BoardGameModel class. 
 * @author
 *
 */
public class BoardGameTest {
	/**
	 * Attributes
	 */
	private BoardGameModel boardGame;
	private Bar[] verticals;
	private Bar[] horizontals;		
	private Bead[] beads;		
	private GridHole gridHoles[][]; 			
	private List<String> historyOfMoves;
	private String verticalBarsConfiguration;
	private String horizontalBarsConfiguration;
	
	
	
	/** 
	 * Methods
	 */	
	
	/**
	 * This method has been marked with a @Before annotation, so that it is invoked and executed whenever a test method has to be
	 * executed. The purpose of this method is:
	 * 		to initialize the local attributes;
	 * 		to initialize the bars in the model class;
	 * 		to set the position of the vertical and horizontal bars in the model according to a pre-established configuration;
	 * 		to initialize the four players instances in the model;
	 * 		to assign to the local attributes the values of the bars, the grid holes and the player from the model.
	 */
	@Before
	public void setUp() {
		// initialize this class attributes
		boardGame = new BoardGameModel();
		verticals = new Bar[Utility.NUMBER_OF_BARS];
		horizontals = new Bar[Utility.NUMBER_OF_BARS];			
		beads = new Bead[Utility.NUMBER_OF_BEADS_PER_PLAYER * Utility.NUMBER_OF_PLAYERS];		
		gridHoles = new GridHole[Utility.GRID_SIDE][Utility.GRID_SIDE];
		historyOfMoves = new ArrayList<String>();
		
		verticalBarsConfiguration = "2221001";
		horizontalBarsConfiguration = "0021102";
		
		// Initialize the bars
		boardGame.initializeVerticalBars();
		boardGame.initializeHorizontalBars();	
						
		// set vertical bars in the model
    	for (int i = 0; i < 7; i++) {
    		String verticalPositions = verticalBarsConfiguration;
    		String position = verticalPositions.substring(i, i + 1);
    		BarPosition barPosition;
    		if (position.equals("0"))     			
    			barPosition = BarPosition.INNER;
    		else if (position.equals("1"))
    			barPosition = BarPosition.CENTRAL;
    		else //if (position.equals("2"))
    			barPosition = BarPosition.OUTER;    		
    		boardGame.setVerticalBarPosition(barPosition, i + 1);
    		boardGame.setColumnInGrid(i);
    	}
    	
		// set horizontal bars in the model
    	for (int i = 0; i < 7; i++) {
    		String horizontalPositions = horizontalBarsConfiguration;
    		String position = horizontalPositions.substring(i, i + 1);
    		BarPosition barPosition;
    		if (position.equals("0"))     			
    			barPosition = BarPosition.INNER;
    		else if (position.equals("1"))
    			barPosition = BarPosition.CENTRAL;
    		else //if (position.equals("2"))
    			barPosition = BarPosition.OUTER;    		    		
    		boardGame.setHorizontalBarPosition(barPosition, i + 1);
    		boardGame.setRowInGrid(i);
    	}    
    	
    	// in the settings the default number of players is four
		boardGame.initializePlayer("Roberto", 1);
		boardGame.initializePlayer("Francesco", 2);
		boardGame.initializePlayer("Roza", 3);
		boardGame.initializePlayer("Fiona", 4);		
    			
    	// assign the bars, players and grid holes 
    	horizontals = boardGame.getHorizontals();
    	verticals = boardGame.getVerticals();    	
    	gridHoles = boardGame.getGridHoles();
	}
	

	
	/**
	 * Tests the correctness of the setup of the model.
	 */
	@Test
	public void testBoardSetUp() {				    	
    	assertEquals(BarPosition.OUTER, verticals[0].getPosition());
    	assertEquals(BarPosition.CENTRAL, verticals[3].getPosition());
    	assertEquals(BarPosition.CENTRAL, horizontals[3].getPosition());
    	assertEquals(BarPosition.INNER, horizontals[5].getPosition());
    	assertArrayEquals(new boolean[] {false, false, true, false, false, false, true}, horizontals[2].getBarInGrid());
    	assertArrayEquals(new boolean[] {false, true, false, true, false, true, false}, horizontals[3].getBarInGrid());
    	assertArrayEquals(new boolean[] {false, false, true, true, false, false, true}, verticals[1].getBarInGrid());
    	assertArrayEquals(new boolean[] {false, false, true, false, false, true, false}, verticals[6].getBarInGrid());
    	    	
    	assertEquals(GridHole.HORIZONTAL, gridHoles[0][0]);
    	assertEquals(GridHole.HOLE, gridHoles[0][3]);
    	assertEquals(GridHole.VERTICAL, gridHoles[3][3]);
    	assertEquals(GridHole.HOLE, gridHoles[6][4]);
    	
    	
		// scan the grid slots per row
		for (int i = 0; i < Utility.GRID_SIDE; i++) {		
			// get i-th horizontal bar in the grid
			boolean[] horizontalInGrid = horizontals[i].getBarInGrid();
			for (int j = 0; j < Utility.GRID_SIDE; j++) {
				// get j-th vertical bar in the grid
				boolean[] verticalInGrid = verticals[j].getBarInGrid();
				if (verticalInGrid[i]) {
					assertEquals(GridHole.VERTICAL, gridHoles[i][j]);					
				}
				else if (horizontalInGrid[j]) {
					assertEquals(GridHole.HORIZONTAL, gridHoles[i][j]);					
				}
				else {
					assertEquals(GridHole.HOLE, gridHoles[i][j]);					
				}				
			}			
		}
	}
	

	/**
	 * This method tests the correct bead placement.
	 */
	@Test
	public void testBeadPlacement() {
		/*System.out.println("\nTesting the beads placement:\n");
		for (int i = 0; i < Utility.NUMBER_OF_BARS; i++) {
			for (int j = 0; j < Utility.GRID_SIDE; j++) {
				System.out.print(gridHoles[i][j] + "\t");
			}
			System.out.println();
		}*/
		
		// valid positions
		try {
			boardGame.placeBeadOnGrid(0, 0, 1);
			boardGame.placeBeadOnGrid(6, 6, 2);
			boardGame.placeBeadOnGrid(5, 4, 1);	
			beads = boardGame.getBeads();
			assertEquals(1, beads[0].getPlayerNumber());
			assertEquals(0, beads[0].getXCoordinate());
			assertEquals(0, beads[0].getYCoordinate());
			assertEquals(5, beads[2].getXCoordinate());
			assertEquals(6, beads[1].getYCoordinate());
		}
		catch (GameException e) {
			System.out.println(e.toString());
		}	
		// put a bead out of the grid
		try {
			boardGame.placeBeadOnGrid(7, 7, 1);
		}
		catch (GameException e) {
			System.out.println(e.toString());
		}
		// put a bead in a hole
		try {
			boardGame.placeBeadOnGrid(4, 0, 2);			
		}
		catch (GameException e) {
			System.out.println(e.toString());
		}		
		// put a bead on an occupied cell
		try {
			boardGame.placeBeadOnGrid(0, 0, 2);
		}
		catch (GameException e) {
			System.out.println(e.toString());
		}		
		
		beads = boardGame.getBeads();		
		assertEquals(false, beads[0].getOnVertical());
		assertEquals(false, beads[1].getOnVertical());
		assertEquals(true, beads[2].getOnVertical());
		assertEquals(GridHole.HOLE, gridHoles[4][0]);
		assertEquals(1, beads[0].getPlayerNumber());
		assertEquals(2, beads[1].getPlayerNumber());
		assertEquals(1, beads[2].getPlayerNumber());		
	}
	
	
	
	
	/**
	 * This method assesses the checks regarding the validity of the direction of the moves.
	 */
	@Test
	public void testValidityOfDirection() {
		/*System.out.println("\n\nTesting the direction of of the bars to move:\n");
		System.out.println("Vertical bars:");
		for (int i = 0; i < Utility.NUMBER_OF_BARS; i++)
			System.out.println(verticals[i]);
		System.out.println("\nHorizontal bars:");
		for (int i = 0; i < Utility.NUMBER_OF_BARS; i++)
			System.out.println(horizontals[i]);*/
		
		// outward direction, outer position of bar
		
			boardGame.checkValidityOfDirection("v1o");			
		
		// inward direction, inner position of bar
		
			boardGame.checkValidityOfDirection("v5i");			
		
		// inward direction, inner position of bar
		
			boardGame.checkValidityOfDirection("h2i");			
				
	}
		
	
	
	/**
	 * This method assesses the checks regarding the validity of the constraints starting with two players.
	 */
	@Test
	public void testValidityOfConstraintsWithTwoPlayers() {
		boardGame.setNumberOfPlayers(2);
		boardGame.initializePlayer("Roberto", 1);
		boardGame.initializePlayer("Francesco", 2);		
		
		
			boardGame.setPlayerTurn(1);
			boardGame.checkValidityOfDirection("v3i");
			boardGame.checkValidityOfConstraints();
			boardGame.setPlayerTurn(2);
			boardGame.checkValidityOfDirection("v3i");	
			boardGame.checkValidityOfConstraints();
		
		
		
			boardGame.setPlayerTurn(2);
			boardGame.checkValidityOfDirection("h1o");	
			boardGame.checkValidityOfConstraints();
			boardGame.setPlayerTurn(1);
			boardGame.checkValidityOfDirection("h1o");
			boardGame.checkValidityOfConstraints();
		
		
		
			boardGame.setPlayerTurn(1);
			boardGame.checkValidityOfDirection("h4o");	
			boardGame.checkValidityOfConstraints();
			boardGame.setPlayerTurn(2);
			boardGame.checkValidityOfDirection("h4o");
			boardGame.checkValidityOfConstraints();
		
		
					
			boardGame.setPlayerTurn(2);
			boardGame.checkValidityOfDirection("v2i");
			boardGame.checkValidityOfConstraints();
			historyOfMoves = boardGame.getHistoryOfMoves();
			//System.out.println(historyOfMoves);		
		
	}
	
	
	/**
	 * 
	 */
	@Test
	public void testMoveBar() {			
			
			/*System.out.println();
			for (int i = 0; i < Utility.NUMBER_OF_BARS; i++) {
				for (int j = 0; j < Utility.GRID_SIDE; j++) {
					System.out.print(gridHoles[i][j] + "\t");
				}
				System.out.println();
			}
			}*/
			boardGame.setPlayerTurn(1);
			boardGame.checkValidityOfDirection("v1i");
			boardGame.moveBar();			
			assertEquals(BarPosition.CENTRAL, boardGame.getVerticalAtIndex(1).getPosition());
			gridHoles = boardGame.getGridHoles();	
			assertEquals(GridHole.HOLE, gridHoles[3][0]);
			assertEquals(GridHole.VERTICAL, gridHoles[4][0]);
			assertEquals(GridHole.HORIZONTAL, gridHoles[5][0]);
			/*System.out.println();
			for (int i = 0; i < Utility.NUMBER_OF_BARS; i++) {
				for (int j = 0; j < Utility.GRID_SIDE; j++) {
					System.out.print(gridHoles[i][j] + "\t");
				}
				System.out.println();
			}
			}*/
			
			// another move
			boardGame.setPlayerTurn(2);
			boardGame.checkValidityOfDirection("h4o");
			boardGame.moveBar();						
			assertEquals(BarPosition.OUTER, boardGame.getHorizontalAtIndex(4).getPosition());
			gridHoles = boardGame.getGridHoles();			
			assertEquals(GridHole.HOLE, gridHoles[3][5]);
			assertEquals(GridHole.HORIZONTAL, gridHoles[3][6]);
			
	}
	
	
	/**
	 * This method checks that if a bead falls in a hole, it is removed from the game and the owner of that bead possesses one bead less.
	 */
	@Test
	public void testCheckBeads() {		
		try {			
			// from vertical bar to hole
			boardGame.placeBeadOnGrid(3, 0, 1);
			// from vertical to horizontal
			boardGame.placeBeadOnGrid(5, 0, 1);	
			boardGame.setPlayerTurn(1);
			boardGame.checkValidityOfDirection("v1i");
			boardGame.moveBar();
			boardGame.checkBeads();
			gridHoles = boardGame.getGridHoles();			
			assertEquals(4, boardGame.getPlayerAtIndex(1).getNumberOfBeads());				
			assertEquals(GridHole.HOLE, gridHoles[3][0]);
			beads = boardGame.getBeads();
			for (Bead bead : beads) 
				if (bead.getXCoordinate() == 5 && bead.getYCoordinate() == 0)
					assertEquals(false, bead.getOnVertical());				
		} catch (GameException e) {			 
			System.out.println(e.toString());
		}		
		
		try {			
			boardGame.placeBeadOnGrid(3, 0, 2);					
			boardGame.setPlayerTurn(2);
			boardGame.checkValidityOfDirection("h4o");
			boardGame.moveBar();
			boardGame.checkBeads();			
			gridHoles = boardGame.getGridHoles();			
			assertEquals(4, boardGame.getPlayerAtIndex(2).getNumberOfBeads());						
			assertEquals(GridHole.HOLE, gridHoles[3][5]);			
		} catch (GameException e) {				
			System.out.println(e.toString());
		}		
	}
	
	
	/**
	 * This method checks if a player with one bead only loses his last bead and is set out of the game.
	 */
	@Test
	public void testCheckBeadsRemovePlayer() {			
		try {			
			for (int i = 0; i < 4; i ++)
				boardGame.getPlayerAtIndex(1).decrementNumberOfBeads();
			boardGame.placeBeadOnGrid(3, 0, 1);				
			boardGame.setPlayerTurn(1);
			boardGame.checkValidityOfDirection("v1i");
			boardGame.moveBar();
			boardGame.checkBeads();		
			gridHoles = boardGame.getGridHoles();
			assertEquals(true, boardGame.getPlayerAtIndex(1).isOutOfTheGame());
		} catch (GameException e) {			 
			System.out.println(e.toString());
		}
	}
	
	
	/**
	 * This method tests the winner of the game
	 */
	@Test
	public void testCheckPlayersStatus() {
		// there are only two players, one is set out of the game
		boardGame.setNumberOfPlayers(2);
		boardGame.initializePlayer("Roberto", 1);		
		boardGame.initializePlayer("Francesco", 2);
		
		boardGame.getPlayerAtIndex(2).setPlayerOutOfTheGame();		
		
		boardGame.checkPlayersStatus();
		assertEquals(boardGame.getPlayerAtIndex(1).getPlayerNumber(), boardGame.getWinnerName());
		
		// there's only one player. He is removed too
		boardGame.getPlayerAtIndex(1).setPlayerOutOfTheGame();
		assertEquals(boardGame.getPlayerAtIndex(1).getPlayerNumber(), boardGame.getWinnerName());
	}
	
	
	/**
	 * This method checks if the model calculates the next player correctly.
	 */
	@Test
	public void testNextPlayer() {	
		// the first player is by default the player 1
		assertEquals(1, boardGame.getPlayerTurn());
		
		// remove 2 players
		boardGame.removePlayer(2);
		boardGame.removePlayer(4);	
		
		// calculate two turns
		boardGame.calculateNextPlayer();
		assertEquals(3, boardGame.getPlayerTurn());
		boardGame.calculateNextPlayer();
		assertEquals(1, boardGame.getPlayerTurn());
		
		// remove 1 player
		boardGame.removePlayer(1);		
		
		boardGame.calculateNextPlayer();
		assertEquals(3, boardGame.getPlayerTurn());		
		
		// remove the last player
		boardGame.removePlayer(3);
		boardGame.setGameOver();
				
		boardGame.calculateNextPlayer();
		assertEquals(3, boardGame.getPlayerTurn());
	}
}
