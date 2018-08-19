package tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import Code.Team;
import Code.Hero;
import Code.Map;
import Code.PowerUp;
import Code.HealingItem;

public class TestTeam {

	private Team team1 = new Team("City Savers", 3);
	private Team team2 = new Team("Super Team", 6);

	private Hero y = new Hero("yy", 1);
	private Hero x = new Hero("xx", 5);
	private Hero z = new Hero("zz", 6);
	
	
	@Before
	public void setUpTeam(){
		team1.addHero(x);
		team1.addHero(y);
		Map map1 = new Map(0);
		team2.addToInventory(map1);
		team1.applyBonuses();
		team2.applyBonuses();
	}
	
	
	@Test
	public void testAddHeroes(){
		assertEquals(0, team2.getHeroes().size());
		assertEquals(2, team1.getHeroes().size());
	}
	
	
	@Test
	public void testMoney() {
		assertEquals(20, team1.getMoney());
		team1.changeMoney(25);
		assertEquals(45, team1.getMoney());
		team1.changeMoney(-10);
		assertEquals(35, team1.getMoney());
	}
	
	
	@Test
	public void testBuffs() {
		//discount, recovery, teamMap
		Team team = new Team("xyz", 6);
		team.applyBonuses();
		assertEquals(false, team.hasDiscount());
		assertEquals(false, team.hasBonusRecovery());
		assertEquals(false, team.hasNavigator());
		
		team.addHero(x);
		team.addHero(y);
		team.addHero(z);
		team.applyBonuses();
		assertEquals(true, team.hasDiscount());
		assertEquals(true, team.hasBonusRecovery());
		assertEquals(true, team.hasNavigator());
	}
	
	
	@Test
	public void testInventoryMaps() {
		assertEquals(0, team1.getMaps().get(0).getCount());
		Map map1 = new Map(0);
		team1.addToInventory(map1);
		assertEquals(1, team1.getMaps().get(0).getCount());
		team1.removeInventoryMaps(0);
		assertEquals(0, team1.getMaps().get(0).getCount());
	}
	
	@Test
	public void testInventoryHealingItems(){
		HealingItem item = new HealingItem(1);
		team1.addToInventory(item);
		assertEquals(1, team1.getInventoryHealingItems().get(0).getCount());
		team1.removeInventoryHealingItems(0);
		assertEquals(0, team1.getInventoryHealingItems().get(0).getCount());
	}
	
	@Test
	public void testInventoryPowerUps(){
		PowerUp item = new PowerUp(1);
		team1.addToInventory(item);
		assertEquals(1, team1.getPowerUps().get(0).getCount());
		team1.removeInventoryPowerUp(0);
		assertEquals(0, team1.getPowerUps().get(0).getCount());
	}
	
	
	@Test
	public void testOwnsMap() {
		assertEquals(false, team1.ownsMap(0));
		assertEquals(true, team2.ownsMap(0));
	}
	
	
	@Test
	public void testName(){
		assertEquals("City Savers", team1.getname());
	}
	
	
	@Test
	public void testStealItem(){
		assertEquals("none", team1.stealItem());
		
		team1.addToInventory(new PowerUp(1));
		assertEquals("powerup; shield", team1.stealItem());
		
		team1.addToInventory(new HealingItem(1));
		assertEquals("healing item; painkillers", team1.stealItem());
		
		team1.addToInventory(new Map(0));
		assertEquals("area 1 map.", team1.stealItem());
	}
	
	
	@Test
	public void testGetLivingHeroes(){
		Team team = new Team("heroes", 3);
		Hero a = new Hero("adam", 1);
		Hero b = new Hero("Brooke", 1);
		team.addHero(a);
		team.addHero(b);
		assertEquals(2, team.getLivingHeroes().size());
		a.changeHealth(-100);
		assertEquals(1, team.getLivingHeroes().size());
		b.changeHealth(-100);
		assertEquals(0, team.getLivingHeroes().size());
	}
	
}
