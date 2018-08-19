package Code;
import java.util.ArrayList;

/**A collection of Hero's
 */
public class Team {
	/**An ArrayList of the heroes in the team.
	 */
	private ArrayList<Hero> heroes = new ArrayList<Hero>();
	/**The name of the team.
	 */
	private String teamName;
	
	//inventory stuff
	/**The teams HealingItem inventory. Keeps track of how much of each type
	 * of healing item the team owns.
	 */
	private ArrayList<HealingItem> inventoryHealingItems = new ArrayList<HealingItem>();
	/**The teams PowerUp inventory. Keeps track of how much of each type
	 * of Power up the team owns.
	 */
	private ArrayList<PowerUp> powerups = new ArrayList<PowerUp>();
	/**The teams Map inventory. Keeps track of how much of each type
	 * of map the team owns.
	 */
	private ArrayList<Map> maps = new ArrayList<Map>();
	/**A list of items the team owns. Keeps track of what items the team has.
	 */
	private ArrayList<Buyable> ownedItems = new ArrayList<Buyable>();
	/**The amount of money the team has. The team has 20g at the start of the game.
	 */
	private int money = 20;
	
	//team bonuses from special hero types
	private boolean discount;	//50% discount at store
	private boolean recovery;	//halves recovery times
	private boolean teamMap;	//no map needed
	
	

	
	/**Creates a new Team
	 * @param newTeamName A String representing the name of the Team
	 * @param gameSize An Integer representing how many cities are in the game
	 */
	public Team(String newTeamName, int gameSize) {
		teamName = newTeamName;
		setupMaps(gameSize);
		setupPowerUps();
		setupHealingItems();
		setupMaps(gameSize);
	}
	
	/**Removes all PowerUp buffs from all members of the team
	 */
	public void resetHeroBuffs() {
		for (int i = 0; i < heroes.size(); i++) {
			heroes.get(i).resetBuffs();
		}
	}
	
	private void setupMaps(int gameSize) {
		for (int i = 0; i < gameSize; i++) {
			maps.add(new Map(i));
		}
	}
	
	private void setupHealingItems() {
		for (int i = 0; i < 3; i++) {
			inventoryHealingItems.add(new HealingItem(i + 1));
		}
	}
	


	
	private void setupPowerUps() {
		for (int i = 0; i < 4; i++) {
			powerups.add(new PowerUp(i + 1));
		}
	}

	/**Adds a Hero to the Team
	 * @param newHero A Hero to be added to the Team
	 */
	public void addHero(Hero newHero) {
		heroes.add(newHero);
	}
	
	
	/**Resets all team bonuses then adds bonuses from all active heroes to the team 
	 * To be called when the team is created and every time a hero dies
	 */
	public void applyBonuses() {
		//reset all bonuses to false and turn them on for each active hero
		discount = false;
		recovery = false;
		teamMap = false;
		for(int i = 0; i < heroes.size(); i++) {
			if (heroes.get(i).getAlive()) {	//if the hero is currently alive
				applyBonus(heroes.get(i));
			}
			
		}
	}
	
	/**
	 * Applies the bonus from one hero to the team
	 * @param hero A Hero to apply bonuses to the Team from
	 */
	private void applyBonus(Hero hero) {
		switch(hero.getType()) {
		case 1: discount = true;
				break;
		case 5: recovery = true;
				break;
		case 6: teamMap = true;
		}
	}
	
	/**
	 * Returns a boolean of whether the team has the "50% shop discount" bonus
	 * @return A Boolean representing whether or not the team has a 50% shop discount
	 */
	public boolean hasDiscount() {
		return discount;
	}
	
	/**
	 * Returns a boolean of whether or not the team has an active Navigator
	 * @return A Boolean representing whether or not the team has an active navigator
	 */
	public boolean hasNavigator() {
		return teamMap;
	}
	
	/**Returns a Boolean stating whether or not the Team owns the Map for the specified city
	 * @param area An Integer specifying which city to check the Team for maps
	 * @return A Boolean representing whether or not the team owns the maps for the specified city
	 */
	public boolean ownsMap(int area) {
		return maps.get(area).getCount() > 0;
	}
	
	/**
	 * Returns a boolean of whether or not the team has the "halved recovery times" bonus
	 * @return A Boolean representing whether or not the team has halved recovery times
	 */
	public boolean hasBonusRecovery() {
		return recovery;
	}
	
	/**Gets a list of all Hero's on the Team
	 * @return A list of Hero's
	 */
	public ArrayList<Hero> getHeroes(){
		return heroes;
	}
	
	/**Gets the name of the Team
	 * @return A String representing the name of the Team
	 */
	public String getname() {
		return teamName;
	}
	
	/**Gets the amount of money the Team currently has
	 * @return An Integer specifying the amount of money the Team currently has
	 */
	public int getMoney() {
		return money;
	}
	
	/**Changes the amount of money the Team has by a specified amount
	 * @param difference An Integer specifying how much to change the Team's money by
	 */
	public void changeMoney(int difference) {
		money += difference;
	}
	
	/**Gets a list of the maps in the Team's inventory
	 * @return An ArrayList of maps in the Team's inventory
	 */
	public ArrayList<Map> getMaps(){
		return maps;
	}
	

	/**Gets a list of all PowerUp's in the Team's inventory
	 * @return An ArrayList of all PowerUp's in the Team's inventory 
	 */
	public ArrayList<PowerUp> getPowerUps() {
		return powerups;
	}
	
	/**Gets a list of all HealingItems in the Team's inventory
	 * @return An ArrayList of all HealingItems in the Team's inventory
	 */
	public ArrayList<HealingItem> getInventoryHealingItems() {
		return inventoryHealingItems;
	}

	
	/**Removes 1 of a specified HealingItem from the Team's inventory
	 * @param index An Integer specifying the type of HealingItem to remove (0-2)
	 */
	public void removeInventoryHealingItems(int index){
		inventoryHealingItems.get(index).changeCount(-1);
		for (int i = 0; i < ownedItems.size(); i++) {
			if (ownedItems.get(i).type().equals("healingItem") && ownedItems.get(i).getTypeInt() == index + 1) { //if this item is a healing item of correct type
				ownedItems.remove(ownedItems.get(i));
				break;
			}
		}
		
	}
	
	/**Removes an item from the teams powerups of the given index
	 * @param index An Integer specifying the type of PowerUp to remove (0-3)
	 */
	public void removeInventoryPowerUp(int index) {
		powerups.get(index).changeCount(-1);
		for (int i = 0; i < ownedItems.size(); i++) {
			if (ownedItems.get(i).type().equals("PowerUp") && ownedItems.get(i).getTypeInt() == index + 1) {	//if this item is a powerup of the correct type
				ownedItems.remove(ownedItems.get(i));
				break;
			}
		}
	}
	
	/**
	 * Removes an item from the teams maps of the given index
	 * @param index An Integer specifying the type of HealingItem to remove (1-GameSize)
	 */
	public void removeInventoryMaps(int index) {
		maps.get(index).changeCount(-1);
		for (int i = 0; i < ownedItems.size(); i++) {
			if (ownedItems.get(i).type().equals("Map") && ownedItems.get(i).getTypeInt() == index) {
				ownedItems.remove(ownedItems.get(i));
				break;
			}
		}
	}
	
	
	/**Adds a Buyable item to the Team's inventory
	 * @param item A Buyable to be added to the Team's inventory
	 */
	public void addToInventory(Buyable item){
		ownedItems.add(item);
		switch (item.type()) {
			case "healingItem":	inventoryHealingItems.get(item.getTypeInt() - 1).changeCount(1);
								break;
			case "Map":			maps.get(item.getTypeInt()).changeCount(1);
								break;
			case "PowerUp":		powerups.get(item.getTypeInt() - 1).changeCount(1);
								break;
		}
		
	}
	
	
	
	/**
	 * Takes a random item out of the teams inventory returns a String stating what was taken.
	 * @return A string representing the item removed from the inventory.
	 */
	public String stealItem(){
		String item = "none";
		if (ownedItems.size() > 0) {
		int itemIndex = (int)(Math.random() * ownedItems.size());
		Buyable stolenItem = ownedItems.get(itemIndex);
		ownedItems.remove(stolenItem);
		switch(stolenItem.type()) {
		case "Map":				maps.get(stolenItem.getTypeInt()).changeCount(-1);
								item = "area " + (stolenItem.getTypeInt() + 1) + " map.";
								break;
		case "healingItem":		inventoryHealingItems.get(stolenItem.getTypeInt() - 1).changeCount(-1);
								switch(stolenItem.getTypeInt()){
								case 1: item = "healing item; painkillers";
										break;
								case 2:	item = "healing item; bandage";
										break;
								case 3:	item = "healing item; body reset";
										break;
								}
								break;
		case "PowerUp":			powerups.get(stolenItem.getTypeInt() - 1).changeCount(-1);
								switch(stolenItem.getTypeInt()) {
								case 1:		item =  "powerup; shield";
											break;
								case 2:		item =  "powerup; steroids";
											break;
								case 3: 	item =  "powerup; lucky horseshoe";
											break;
								case 4:		item =  "powerup; time machine";
											break;
								}
								break;
		}
		}
		return item;
	}
	
	/**Gets a list of all current living Hero's
	 * @return An ArrayList of all living Hero's on the Team
	 */
	public ArrayList<Hero> getLivingHeroes(){
		ArrayList<Hero> LivingHeroes = new ArrayList<Hero>();
		for (int i = 0; i < heroes.size(); i ++) {
			if (heroes.get(i).getAlive()) {
				LivingHeroes.add(heroes.get(i));
			}
		}
		return LivingHeroes;
	}

}
