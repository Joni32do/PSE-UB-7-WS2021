package de.unistuttgart.iste.rss.oo.hamstersimulation.exercise7;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import de.unistuttgart.iste.rss.oo.hamstersimulator.datatypes.Direction;
import de.unistuttgart.iste.rss.oo.hamstersimulator.datatypes.Location;
import de.unistuttgart.iste.rss.oo.hamstersimulator.datatypes.Size;
import de.unistuttgart.iste.rss.oo.hamstersimulator.external.model.Hamster;
import de.unistuttgart.iste.rss.oo.hamstersimulator.external.model.HamsterGame;
import de.unistuttgart.iste.rss.oo.hamstersimulator.external.model.Territory;
import de.unistuttgart.iste.rss.oo.hamstersimulator.internal.model.territory.TerritoryBuilder;
import de.unistuttgart.iste.rss.oo.hamstersimulator.ui.javafx.JavaFXUI;

/**
 * Class demonstrating the intended use of the hamster simulator API.
 * @author Steffen Becker
 *
 */
public final class Main {
	
	
	
	public final static Location targetLocation = new Location(1,1);
	public final static int targetAmountGrain = 5;
	
	
	
	
	
	
	public static void studentTask(Hamster paule) {
		
		
		moveTillWall(paule);
		turnRight(paule);
		moveTillWall(paule);
		turnRight(paule);
		moveTillWall(paule);
		paule.turnLeft();
		paule.move();
		paule.move();
		paule.turnLeft();
		paule.move();
		pickAll(paule);
		turnRight(paule);
		moveTillWall(paule);
		paule.turnLeft();
		moveTillWall(paule);
		paule.turnLeft();
		moveTillWall(paule);
		pickAll(paule);
		turnAround(paule);
		
		
		//interestingly you could do this now backwards by doing everything in reverse query
		
		
		moveTillWall(paule);
		turnRight(paule);
		moveTillWall(paule);
		turnRight(paule);
		moveTillWall(paule);
		paule.turnLeft();
		paule.move();
		turnRight(paule);
		moveTillWall(paule);
		turnRight(paule);
		paule.move();
		paule.move();
		paule.move();
		paule.turnLeft();
		moveTillWall(paule);
		paule.turnLeft();
		moveTillWall(paule);
		
		
		
		
		
		
		
		
		//checks if the objective is achieved
		isFinished(paule);
	}
	
	
	
	
	



	//possible helpful commands to add
	
	private static void turnAround(Hamster paule) {
		paule.turnLeft();
		paule.turnLeft();
	}



	public static void moveTillWall(Hamster paule) {
		while(paule.frontIsClear()) {
			paule.move();
		}
	}
	
	public static void turnRight(Hamster paule) {
		paule.turnLeft();
		paule.turnLeft();
		paule.turnLeft();
	}
	

	private static void pickAll(Hamster paule) {
		while(paule.grainAvailable()) {
			paule.pickGrain();
		}
		
	}


	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

    private static void isFinished(Hamster paule) {

    	if(paule.readString("Sind sie wirklich fertig?").equals("Ja")){
    		if(paule.getLocation().equals(targetLocation)) {
    			for(int i = 0; i < targetAmountGrain; i++) {
    				if(!paule.mouthEmpty()) {
    					paule.putGrain();
    				} else {
    					paule.write("Oh nein ich habe zu wenig Körner, jetzt muss ich Seppuku betreiben, "
    							+ "um den Namen meiner Familie wieder rein zu waschen!");
    					break;
    				}
    			}
    		} else {
    			paule.write("Falsches Feld!");
    		}
    	} else {
    		paule.write("Dann nerv nicht!");
    	}
		
	}



















	/**
     * A constant containing the location of an example grain on the territory.
     */
    private static final Location GRAIN_LOCATION = Location.from(1, 3);

    /**
     * Row count of the territory.
     */
    private static final int MAX_ROW_COUNT = 8;

    /**
     * Column count of the territory.
     */
    private static final int MAX_COLUMN_COUNT = 8;

    /**
     * Size of the example's territory.
     */
    private static final Size EXAMPLE_TERRITORY_SIZE = new Size(MAX_ROW_COUNT, MAX_COLUMN_COUNT);

    /**
     * Main routine of the example class.
     * @param args Command line arguments, not used here.
     */
    public static void main(final String...args) {
        final HamsterGame hamsterGame = new HamsterGame();

//        hamsterGame.initialize(createTerritory(hamsterGame));
        
        File terFile = new File ("territories/territory_cave.ter");
        try {
        	InputStream targetStream = new FileInputStream(terFile);
            hamsterGame.initialize(targetStream);
        } catch (IOException ex){
            throw new RuntimeException(ex);
        }
        initUI(hamsterGame);
        runGameCycle(hamsterGame);
    }

    /**
     * Starts, runs and finally stops the hamster game.
     * @param hamsterGame The hamster game to run, must not be null.
     */
    private static void runGameCycle(final HamsterGame hamsterGame) {
        assert hamsterGame != null;

        hamsterGame.startGame(false);
        hamsterGame.runGame(Main::runHamster);
        hamsterGame.stopGame();
    }

    /** Setup routine which configures the UI appropriately.
     * @param hamsterGame The hamster game to run, must not be null.
     */
    private static void initUI(final HamsterGame hamsterGame) {
        assert hamsterGame != null;

        JavaFXUI.start();
        hamsterGame.displayInNewGameWindow();
        hamsterGame.setInputInterface(JavaFXUI.getJavaFXInputInterface());
    }

    /** This operation captures the algorithm executed by the hamster.
     * @param territory The territory of the example. It has to contain
     *                  the default hamster. Must not be null.
     */
    private static void runHamster(final Territory territory) {
        assert territory != null;

        final Hamster paule = territory.getDefaultHamster();
                
        studentTask(paule);
        
        
//        paule.readNumber("15");
//        paule.readString("Hallöchen Popöchen");
        
    }
//
//    
//    /**
//     * This operation configures a hamster territory via API. It is a demo
//     * of the territory builder class.
//     * @param hamsterGame The hamster game to run, must not be null.
//     * @return A {@see TerritoryBuilder} object containing the information how to
//     *         initialize this example's territory.
//     */
//    private static TerritoryBuilder createTerritory(final HamsterGame hamsterGame) {
//        assert hamsterGame != null;
//
//        final TerritoryBuilder builder = hamsterGame.getNewTerritoryBuilder();
//
//        builder.initializeTerritory(EXAMPLE_TERRITORY_SIZE);
//        builder.defaultHamsterAt(Location.from(1, 1), Direction.EAST, 0);
//        builder.grainAt(GRAIN_LOCATION);
//
//        createWall(builder, Location.ORIGIN, Location.from(0, MAX_COLUMN_COUNT - 1));
//        createWall(builder, Location.from(1, 0), Location.from(MAX_ROW_COUNT - 2, 0));
//        createWall(builder, Location.from(1, MAX_COLUMN_COUNT - 1),
//                        Location.from(MAX_ROW_COUNT - 2, MAX_COLUMN_COUNT - 1));
//        createWall(builder, Location.from(MAX_ROW_COUNT - 1, 0),
//                        Location.from(MAX_ROW_COUNT - 1, MAX_COLUMN_COUNT - 1));
//
//        return builder;
//    }
//
//    /**
//     * Helper operation to create a bunch of walls in a given rectangular area.
//     * @param builder The territory builder to use to place the wall.
//     * @param from Upper, left corner of the rectangle.
//     * @param to Lower, right corner of the rectangle.
//     */
//    private static void createWall(final TerritoryBuilder builder, final Location from, final Location to) {
//        assert builder != null;
//        assert from != null;
//        assert to != null;
//        Location.getAllLocationsFromTo(from, to).forEach(location -> builder.wallAt(location));
//    }

    /**
     * Private constructor preventing instances of this class.
     */
    private Main() { }

}