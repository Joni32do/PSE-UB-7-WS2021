package de.unistuttgart.iste.rss.oo.hamstersimulation.exercise7;

import de.unistuttgart.iste.rss.oo.hamstersimulator.datatypes.Direction;
import de.unistuttgart.iste.rss.oo.hamstersimulator.datatypes.Location;
import de.unistuttgart.iste.rss.oo.hamstersimulator.external.model.Hamster;
import de.unistuttgart.iste.rss.oo.hamstersimulator.external.model.Territory;

public class AdvancedHamster extends Hamster {
	
	private final short age;
	private final String clanName;
	private final char favoriteSign;
	private final double money;
	
	
	public AdvancedHamster(final short age,final String clanName,final char favoriteSign,
			final double money) {
		super();
		this.age = age;
		this.clanName = clanName;
		this.favoriteSign = favoriteSign;
		this.money = money;
	}
	
	
	
	public AdvancedHamster(Territory territory, Location location, Direction direction, 
			int newGrainCount,final short age,final String clanName,final char favoriteSign,
			final double money) {
		
		super(territory, location, direction, newGrainCount);
		this.age = age;
		this.clanName = clanName;
		this.favoriteSign = favoriteSign;
		this.money = money;
	}
	
	



	public static void main(String args[]) {
		AdvancedHamster andi = new AdvancedHamster((short) 4, "Horst", '+', 352.21);
		System.out.println(andi.getDirection().toString());
	}
	

}
