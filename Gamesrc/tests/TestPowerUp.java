package tests;

import static org.junit.Assert.*;
import org.junit.Test;

import Code.PowerUp;

public class TestPowerUp {

	@Test
	public void testCreate() {
		PowerUp shield = new PowerUp(1);
		PowerUp steroids = new PowerUp(2);
		PowerUp lucky = new PowerUp(3);
		PowerUp time = new PowerUp(4);
		
		assertEquals(0, shield.getCount());
		assertEquals(0, steroids.getCount());
		assertEquals(0, lucky.getCount());
		assertEquals(0, time.getCount());
		
		assertEquals("Shield", shield.getName());
		assertEquals("Steroids", steroids.getName());
		assertEquals("Lucky Horseshoe", lucky.getName());
		assertEquals("Time Machine", time.getName());
		
		assertEquals(1, shield.getTypeInt());
		assertEquals(2, steroids.getTypeInt());
		assertEquals(3, lucky.getTypeInt());
		assertEquals(4, time.getTypeInt());
		
		assertEquals("PowerUp", shield.type());
		assertEquals("PowerUp", steroids.type());
		assertEquals("PowerUp", lucky.type());
		assertEquals("PowerUp", time.type());
	}
	
	@Test
	public void testChangeCount() {
		PowerUp shield = new PowerUp(1);
		
		assertEquals(0, shield.getCount());
		shield.changeCount(2);
		assertEquals(2, shield.getCount());
		shield.changeCount(-1);
		assertEquals(1, shield.getCount());
		shield.changeCount(-2);
		assertEquals(0, shield.getCount());
	}

}
