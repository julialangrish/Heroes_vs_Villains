

package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import Code.Villain;

/**
 * JUnit test for villains.
 * @author Julia Langrish
 *
 */
public class TestVillain {
	
	private Villain superVillain = new Villain(0);
	private Villain diceVillain = new Villain(1);
	private Villain ngVillain = new Villain(2);
	private Villain psrVillain = new Villain(3);
	private Villain strongVillain = new Villain(4);
	private Villain healthVillain = new Villain(5);
	
	
	@Test
	public void testSuperVillain() {
		assertEquals(false, diceVillain.isSuperVillain());
		assertEquals(false, strongVillain.isSuperVillain());
		assertEquals(false, healthVillain.isSuperVillain());
		assertEquals(false, psrVillain.isSuperVillain());
		assertEquals(false, ngVillain.isSuperVillain());
		assertEquals(true, superVillain.isSuperVillain());
	}
	
	
	@Test
	public void testGameChoices(){
		assertEquals("Dice Game", diceVillain.chooseGame());
		assertEquals("Number Guess", ngVillain.chooseGame());
		assertEquals("Paper Scissors Rock", psrVillain.chooseGame());
		
		String game = strongVillain.chooseGame();
		
		assertEquals(true, game.equals("Paper Scissors Rock") || game.equals("Dice Game") || game.equals("Dice Game"));
		
		
	}
	
	@Test
	public void testHealth(){
		assertEquals(true, diceVillain.getHealth() == diceVillain.getMaxHealth());
		assertEquals(true, healthVillain.getHealth() == healthVillain.getMaxHealth());
		assertEquals(true, healthVillain.getHealth() > diceVillain.getHealth());
		assertEquals(true, superVillain.getHealth() > healthVillain.getHealth());

	}
	
	@Test
	public void testDamage(){
		Villain villain = new Villain(1);
		villain.changeHealth(-30);
		assertEquals(true, villain.getHealth() == (villain.getMaxHealth() - 30));
		assertEquals(true, villain.getAlive());
	}
	
	@Test
	public void testDeath(){
		Villain v = new Villain(2);
		assertEquals(100, v.getHealth());
		v.changeHealth(-50);
		assertEquals(true, v.getAlive());
		assertEquals(50, v.getHealth());
		v.changeHealth(-49);
		assertEquals(true, v.getAlive());
		assertEquals(1, v.getHealth());
		v.changeHealth(-1);
		assertEquals(false, v.getAlive());
		assertEquals(0, v.getHealth());
		v.changeHealth(-80);
		assertEquals(false, v.getAlive());
		assertEquals(0, v.getHealth());
		
	}
	
	@Test
	public void testAlive(){
		assertEquals(true, superVillain.getAlive());
	}
	
	@Test
	public void testStrength(){
		assertEquals(true, strongVillain.getStrengthModifier() == 1);
		assertEquals(true, superVillain.getStrengthModifier() == 1);
		assertEquals(true, psrVillain.getStrengthModifier() == 0);
	}
	
	

}
