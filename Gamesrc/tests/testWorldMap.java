package tests;

import static org.junit.Assert.*;
import org.junit.Test;

import Code.Villain;
import Code.WorldMap;

public class testWorldMap {
	
	@Test
	public void testVillain() {

		for (int i = 0; i < 6; i++) {
		Villain villain = new Villain(i);
		WorldMap worldmap = new WorldMap(villain, 1);
		assertEquals(villain.getName(), worldmap.getVillain().getName());
		}
		
	}
	
	@Test
	public void testLocations() {

		Villain villain = new Villain(1);
		WorldMap worldmap = new WorldMap(villain, 1);
		
		String northDirection = worldmap.getLocation("north");
		String eastDirection = worldmap.getLocation("east");
		String westDirection = worldmap.getLocation("west");
		String southDirection = worldmap.getLocation("south");
		
		
		boolean north = 			northDirection.equals("Shop");
				north = north || 	northDirection.equals("Powerup Den");
				north = north || 	northDirection.equals("Hospital");
				north = north || 	northDirection.equals("Villain's Lair");
		
		boolean east = 				eastDirection.equals("Shop");
				east = east || 		eastDirection.equals("Powerup Den");
				east = east || 		eastDirection.equals("Hospital");
				east = east || 		eastDirection.equals("Villain's Lair");
		
		boolean west = 				westDirection.equals("Shop");
				west = west || 		westDirection.equals("Powerup Den");
				west = west || 		westDirection.equals("Hospital");
				west = west || 		westDirection.equals("Villain's Lair");
				
		boolean south = 			southDirection.equals("Shop");
				south = south || 	southDirection.equals("Powerup Den");
				south = south || 	southDirection.equals("Hospital");
				south = south || 	southDirection.equals("Villain's Lair");
		
		//test that each direction leads to one of the four locations
		assertEquals(north, true);
		assertEquals(east, true);
		assertEquals(west, true);
		assertEquals(south, true);
		
		//assert that each location is unique
		assertEquals(false, (northDirection == eastDirection) || (northDirection == westDirection) || (northDirection == southDirection));
		assertEquals(false, (eastDirection == westDirection) || (eastDirection == southDirection));
		assertEquals(false, (westDirection == southDirection));
				
	}
	
	@Test
	public void testArea(){
		Villain villain = new Villain(1);
		
		for (int i = 1; i < 7; i++) {
			WorldMap worldmap = new WorldMap(villain, i);
			assertEquals(i, worldmap.getArea());
		}
	}

}
