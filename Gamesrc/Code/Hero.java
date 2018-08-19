package Code;
import java.util.ArrayList;

/**A Hero to be added to a Team
 */
public class Hero {
	private boolean alive = true;
	private int health = 100;
	private int maxHealth = 100;
	private String name;
	private int heroType;
	private boolean lucky; //determines whether or not this hero is lucky in battle
	private boolean strength; //determines whether or not this hero is stronger in battle
	private boolean shield; //determines whether or not this hero has higher defense in battle
	private boolean fortuneTeller; //can see villain's favorite game
	private ArrayList<Integer> activeHealingItems = new ArrayList<Integer>(); //arraylist of times left on active healing items (has 3 items, one for each different type)

	/**
	 * Constructs a hero with a known name and type.
	 * @param newName Name of the hero.
	 * @param newHeroType Specifies type of hero.
	 */
	public Hero(String newName, Integer newHeroType){
		name = newName;
		heroType = newHeroType;
		/*Hero types:
		 * 1:	Cheapskate			50% discount at stores
		 * 2:	Can of Flyspray		luckier in battle
		 * 3:	Brute				deals 50% extra damage in battle
		 * 4:	Tank				takes half damage in battle
		 * 5:	Nurse				halves recovery time for whole team
		 * 6:	Navigator			acts as a map for every city
		 * 7+:	None				no buff
		 */
		assignTypeTraits();
		for (int i = 0; i < 3; i++) {
			activeHealingItems.add(0);
		}
	}
	
	/**Returns the time left in seconds of a type of healing item
	 * @param type An Integer representing which type of healing item to check
	 * @return An Integer representing the time left in seconds to apply the healing item
	 */
	public int getActiveHealingItemTime(int type) {
		/*
		 * type 0: small heal (25% health)
		 * type 1: medium heal (50% health)
		 * type 2: full heal(100% health)
		 */
		
		return activeHealingItems.get(type);
	}
	
	
	/**Sets the time left to apply a healing item
	 * @param type An Integer representing the type of healing item used
	 * @param time An Integer representing the time left to apply the healing item
	 */
	public void setActiveHealingItemTime(int type, int time) {
		activeHealingItems.set(type, time);
	}
	
	
	
	/**Gives heroes traits relevant to their type.
	 */
	private void assignTypeTraits(){
		switch (heroType) {
		case 2: lucky = true;
				break;
		case 3: strength = true;
				break;
		case 4: shield = true;
		}
	}
	
	
	/**Applies a power up to the Hero
	 * @param type An Integer representing the type of PowerUp used
	 */
	public void applyPowerUp(String type) {
		switch (type) {
		case "Shield":			shield = true;
								break;
		case "Steroids":		strength = true;
								break;
		case "Lucky Horseshoe":	lucky = true;
								break;
		case "Time Machine":	fortuneTeller = true;
								break;
								// TODO Auto-generated method stub
								
		default:				System.out.println("call to Hero.java's applyPowerUp switch case has an error");
		}
	}
	
	/**Removes buffs and re-adds buffs from hero types (removes buffs from powerups)
	 */
	public void resetBuffs() {
		shield = false;
		strength = false;
		lucky = false;
		fortuneTeller = false;
		
		switch (heroType) {		//re-apply hero-specific buff
		case 2: lucky = true;
				break;
		case 3: strength = true;
				break;
		case 4: shield = true;
		}
		
	}
	
	/**Returns a Boolean stating whether the Hero has a specific buff 
	 * @param buff A String representing the buff to be checked
	 * @return A Boolean representing whether or not the Hero has the specified buff
	 */
	public boolean getBuff(String buff) {
		switch(buff){
		case "shield":			return shield;
		case "strength":		return strength;
		case "luck":			return lucky;
		case "fortuneTeller":	return fortuneTeller;
		default: 				System.out.println("call to Hero.java's getBuff switch case has an error");
								return false;
		}
	}
	
	/**Gets the hero type of this Hero
	 * @return An Integer specifying the type of hero
	 */
	public int getType() {
		return heroType;
	}
	
	
	/**Gets the "alive" status of the Hero
	 * @return A Boolean representing whether or not the Hero is alive
	 */
	public boolean getAlive(){
		return alive;
	}
	
	/**Gets the current health of the Hero
	 * @return An Integer representing the current health of the Hero
	 */
	public int getHealth() {
		return health;
	}
	
	/**Changes the health of the Hero by a specified amount
	 * @param healthChange An Integer representing the amount you would like to change the Hero's health by
	 */
	public void changeHealth(int healthChange) {
		health = health + healthChange;
		if (health > maxHealth){
			health = maxHealth;
		}
		else if(health <= 0){
			alive = false;
			health = 0;
		}
	}
	
	/**Gets the name of the Hero
	 * @return A String representing the name of the Hero
	 */
	public String getName() {
		return name;
	}
	
	/**Gets all relevant information about the hero in a readable format
	 * @return A String displaying to the user everything relevant about the Hero
	 */
	public String getHeroInfo() {
		String perks = "Perks:\n";
		switch (heroType) {
		case 1: perks += "50% discount at stores\n";
				break;
		case 5: perks += "Provides improved recovery times for the team\n";
				break;
		case 6:	perks += "Acts as a map\n";
				break;
		default: perks += "None\n";
		}
		perks += (shield?"Takes 50% less damage in battle\n":"");
		perks += (strength?"Deals 50% more damage in battle\n":"");
		perks += (lucky?"Is more lucky in battle\n":"");
		perks += (fortuneTeller?"Can see which game the villain will choose\n":"");
		
		String stringHealth = "Health: " + Integer.toString(health) + "/" + Integer.toString(maxHealth);
		
		return name + "\n\n" + perks + "\n" + stringHealth + (alive?"":"\n\nDeceased");
	}

}
