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
 * 
 * @author Steffen Becker
 *
 */
public final class Main {

	public final static Location targetLocation = new Location(1, 1);

	public final static int targetAmountGrain = 5;

	public final static Direction targetDirection = Direction.EAST;

	public final static String targetString = "Ja";
	
	

	/**
	 * Method in which the students should find a way to move Paule around the Territory
	 * and gather five grains to finally return back in to his cave and fullfil all his requirements
	 * 
	 * @param paule
	 */
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

		// interestingly you could do this backwards by doing everything in reverse

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
		turnAround(paule);

		// checks if the objective is achieved
		isFinished(paule);
	}

	// possible helpful commands to add
	
	
	
	/**
	 * Turns paule around
	 * @ensures \old paule.getDirection() == Direction.NORTH -> \neww paule.getDirection() == Direction.SOUTH
	 * @param paule a Hamster object
	 */
	private static void turnAround(Hamster paule) {
		paule.turnLeft();
		paule.turnLeft();
	}

	/**
	 * Paule moves to the next Wall or the end of the territory according to the Direction
	 * he looks at
	 * @param paule
	 */
	public static void moveTillWall(Hamster paule) {
		/**
		 * @variant decreasing tiles till next wall
		 * @invariant paule.frontIsClear() -> paule moved one tile
		 */
		while (paule.frontIsClear()) {
			paule.move();
		}
	}

	/**
	 * Turns paule right
	 * @param paule
	 */
	public static void turnRight(Hamster paule) {
		paule.turnLeft();
		paule.turnLeft();
		paule.turnLeft();
	}

	/**
	 * Paule picks up all grains on a tile
	 * @param paule
	 */
	private static void pickAll(Hamster paule) {
		while (paule.grainAvailable()) {
			paule.pickGrain();
		}

	}
	
	
	
	

	/**
	 * Checks if all requirements are achieved
	 * @param paule
	 */
	private static void isFinished(Hamster paule) {

		checkMessage(paule);
		checkLocation(paule);
		checkDirection(paule);
		checkGrains(paule);

	}

	private static void checkMessage(Hamster paule) {
		if (paule.readString("Sind sie wirklich fertig?").equals(targetString)) {
		} else {
			paule.write("Dann nerv nicht!");
		}
	}

	private static void checkLocation(Hamster paule) {
		if (paule.getLocation().equals(targetLocation)) {
			
		} else {
			paule.write("Falsches Feld!");
		}
	}

	private static void checkDirection(Hamster paule) {
		if (paule.getDirection().equals(targetDirection)) {
			
		} else {
			paule.write("Falsche Richtung!");
		}
	}

	private static void checkGrains(Hamster paule) {
		for (int i = 0; i < targetAmountGrain; i++) {
			if (!paule.mouthEmpty()) {
				paule.putGrain();
			} else {
				paule.write("Oh nein ich habe zu wenig Körner");
				break;
			}
		}
	}
	
	
	

	/**
	 * Main routine of the example class.
	 * 
	 * @param args Command line arguments, not used here.
	 */
	public static void main(final String... args) {
		final HamsterGame hamsterGame = new HamsterGame();

//        hamsterGame.initialize(createTerritory(hamsterGame));

		File terFile = new File("territories/territory_cave.ter");
		try {
			InputStream targetStream = new FileInputStream(terFile);
			hamsterGame.initialize(targetStream);
		} catch (IOException ex) {
			throw new RuntimeException(ex);
		}
		initUI(hamsterGame);
		runGameCycle(hamsterGame);
	}

	/**
	 * Starts, runs and finally stops the hamster game.
	 * 
	 * @param hamsterGame The hamster game to run, must not be null.
	 */
	private static void runGameCycle(final HamsterGame hamsterGame) {
		assert hamsterGame != null;

		hamsterGame.startGame(false);
		hamsterGame.runGame(Main::runHamster);
		hamsterGame.stopGame();
	}

	/**
	 * Setup routine which configures the UI appropriately.
	 * 
	 * @param hamsterGame The hamster game to run, must not be null.
	 */
	private static void initUI(final HamsterGame hamsterGame) {
		assert hamsterGame != null;

		JavaFXUI.start();
		hamsterGame.displayInNewGameWindow();
		hamsterGame.setInputInterface(JavaFXUI.getJavaFXInputInterface());
	}

	/**
	 * This operation captures the algorithm executed by the hamster.
	 * 
	 * @param territory The territory of the example. It has to contain the default
	 *                  hamster. Must not be null.
	 */
	private static void runHamster(final Territory territory) {
		assert territory != null;

		final Hamster paule = territory.getDefaultHamster();

		studentTask(paule);


	}

	/**
	 * Private constructor preventing instances of this class.
	 */
	private Main() {
	}

}