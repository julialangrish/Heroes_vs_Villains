package tests;

import static org.junit.Assert.*;
import org.junit.Test;

import Code.Hero;

public class TestHero {

	@Test
	public void testPowerUpShield() {
		Hero hero = new Hero("TestHero", 7);
		hero.applyPowerUp("Shield");
		assertEquals(true, hero.getBuff("shield"));
		hero.resetBuffs();
		assertEquals(false, hero.getBuff("shield"));
	}
	
	@Test
	public void testPowerUpSteroids() {
		Hero hero = new Hero("TestHero", 7);
		hero.applyPowerUp("Steroids");
		assertEquals(true, hero.getBuff("strength"));
		hero.resetBuffs();
		assertEquals(false, hero.getBuff("strength"));
	}
	
	@Test
	public void testPowerUpLuckyHorseShoe() {
		Hero hero = new Hero("TestHero", 7);
		hero.applyPowerUp("Lucky Horseshoe");
		assertEquals(true, hero.getBuff("luck"));
		hero.resetBuffs();
		assertEquals(false, hero.getBuff("luck"));
	}
	
	@Test
	public void testHealthChange() {
		Hero hero = new Hero("TestHero", 7);
		hero.changeHealth(-10);
		assertEquals(90, hero.getHealth());
	}
	
	@Test
	public void testHeroDeath() {
		Hero hero = new Hero("TestHero", 7);
		assertEquals(true, hero.getAlive());
		hero.changeHealth(-100);
		assertEquals(false, hero.getAlive());
	}
	
	@Test
	public void testHeroSetup1(){
		Hero hero = new Hero("Cheap", 1);
		assertEquals("Cheap", hero.getName());
		assertEquals(true, hero.getAlive());
		assertEquals(100, hero.getHealth());
		assertEquals(1, hero.getType());
		assertEquals(false, hero.getBuff("strength"));
		assertEquals(false, hero.getBuff("shield"));
		assertEquals(false, hero.getBuff("luck"));
		hero.resetBuffs();
		assertEquals(false, hero.getBuff("strength"));
		assertEquals(false, hero.getBuff("shield"));
		assertEquals(false, hero.getBuff("luck"));
	}
	
	@Test
	public void testHeroSetup2(){
		Hero hero = new Hero("Can", 2);
		assertEquals("Can", hero.getName());
		assertEquals(true, hero.getAlive());
		assertEquals(100, hero.getHealth());
		assertEquals(2, hero.getType());
		assertEquals(false, hero.getBuff("strength"));
		assertEquals(false, hero.getBuff("shield"));
		assertEquals(true, hero.getBuff("luck"));
		hero.resetBuffs();
		assertEquals(false, hero.getBuff("strength"));
		assertEquals(false, hero.getBuff("shield"));
		assertEquals(true, hero.getBuff("luck"));
	}
	
	@Test
	public void testHeroSetup3(){
		Hero hero = new Hero("Brute", 3);
		assertEquals("Brute", hero.getName());
		assertEquals(true, hero.getAlive());
		assertEquals(100, hero.getHealth());
		assertEquals(3, hero.getType());
		assertEquals(true, hero.getBuff("strength"));
		assertEquals(false, hero.getBuff("shield"));
		assertEquals(false, hero.getBuff("luck"));
		hero.resetBuffs();
		assertEquals(true, hero.getBuff("strength"));
		assertEquals(false, hero.getBuff("shield"));
		assertEquals(false, hero.getBuff("luck"));
	}
	
	@Test
	public void testHeroSetup4(){
		Hero hero = new Hero("Tank", 4);
		assertEquals("Tank", hero.getName());
		assertEquals(true, hero.getAlive());
		assertEquals(100, hero.getHealth());
		assertEquals(4, hero.getType());
		assertEquals(false, hero.getBuff("strength"));
		assertEquals(true, hero.getBuff("shield"));
		assertEquals(false, hero.getBuff("luck"));
		hero.resetBuffs();
		assertEquals(false, hero.getBuff("strength"));
		assertEquals(true, hero.getBuff("shield"));
		assertEquals(false, hero.getBuff("luck"));
	}
	
	@Test
	public void testHeroSetup5(){
		Hero hero = new Hero("Nurse", 5);
		assertEquals("Nurse", hero.getName());
		assertEquals(true, hero.getAlive());
		assertEquals(100, hero.getHealth());
		assertEquals(5, hero.getType());
		assertEquals(false, hero.getBuff("strength"));
		assertEquals(false, hero.getBuff("shield"));
		assertEquals(false, hero.getBuff("luck"));
		hero.resetBuffs();
		assertEquals(false, hero.getBuff("strength"));
		assertEquals(false, hero.getBuff("shield"));
		assertEquals(false, hero.getBuff("luck"));
	}
	
	@Test
	public void testHeroSetup6(){
		Hero hero = new Hero("Nav", 6);
		assertEquals("Nav", hero.getName());
		assertEquals(true, hero.getAlive());
		assertEquals(100, hero.getHealth());
		assertEquals(6, hero.getType());
		assertEquals(false, hero.getBuff("strength"));
		assertEquals(false, hero.getBuff("shield"));
		assertEquals(false, hero.getBuff("luck"));
		hero.resetBuffs();
		assertEquals(false, hero.getBuff("strength"));
		assertEquals(false, hero.getBuff("shield"));
		assertEquals(false, hero.getBuff("luck"));
	}
	
	@Test
	public void testHeroInfo() {
		Hero hero = new Hero("testHero", 7);
		String heroInfo = hero.getHeroInfo();
		
		assertEquals(false, heroInfo.equals(null));
		
	}
}
