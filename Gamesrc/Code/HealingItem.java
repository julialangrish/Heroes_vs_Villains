package Code;

/**
 * This class is for the healing items which can be bought at the shop and used at the hospital.
 * Each healing item has a name, price, time (time needed to apply), and health (health increase).
 */
public class HealingItem implements Buyable {
	
	/*
	 * NOTE:
	 * To apply a healing item, use function:
	 * hero.setActiveHealingItemTime(index, time);
	 * where index is the healing item you want to use (0 for small heal, 1 for medium heal, 2 for full heal) (int)
	 * and time is the time taken to use that healing item (int)
	 * 
	 * index can be found by calling HealingItem.getType()
	 */

	
	/**The name of the healing item.
	 */
	private String name;
	/**How much it will cost to buy the item at the shop.
	 */
	private int price;
	/**The time it will take to apply the item at the hospital.
	 */
	private int time;
	/**The amount of health that the item can give to the hero.
	 */
	private int health;
	/**Used for team inventory purposes. Keeps track of how many of the item the team owns.
	 */
	private int count = 0;
	/**An integer representing the type of the item (1 for small heal, 2 for medium heal, 3 for full heal).
	 */
	private int type;
	
	
	/**
	 * initializes the object, generates the desired properties.
	 * @param newType An integer representing the type of healing item. Used to generate item properties.
	 */
	public HealingItem(int newType){
		switch (newType) {
		case 1:	name = "Painkillers";
				price = 22;
				time = 20;
				health = 25;
				break;
		case 2:	name = "Bandage";
				price = 40;
				time = 20;
				health = 50;
				break;
		case 3:	name = "Body Reset";
				price = 60;
				time = 60;
				health = 100;
				break;
		}
		type = newType;
	}
	
	/**
	 * Sets the count of the healing item in inventory
	 * @param newCount the amount of the item
	 */
	public void setCount(int newCount) {
		count = newCount;
	}
	
	/**
	 * returns an integer describing the type
	 * 0: small heal
	 * 1: medium heal
	 * 2: full heal
	 * @return an integer describing the type
	 */
	public int getTypeInt() {
		return type;
	}
	
	/**
	 * Gets count of the healing item. Useful for keeping track of how many the team owns.
	 * @return An integer for the amount owned.
	 */
	public int getCount() {
		return count;
	}
	

	/**
	 * Changes the current count of the item.
	 * @param difference The amount the count is to change.
	 */
	public void changeCount(int difference) {
		count += difference;
	}
	
	
	/**
	 * Gets the cost of the item.
	 * @return The cost of the item.
	 */
	public int getCost() {
		return price;
	}
	
	
	/**
	 * Gets the name of the healing item.
	 * @return A string representing the items name.
	 */
	public String getName() {
		return name;
	}
	
	
	/**
	 * Gets the amount of time it takes to use the item.
	 * @return An integer of the amount of time it takes to use the item.
	 */
	public int getTime() {
		return time;
	}
	
	
	/**
	 * Gets the amount of health that will be restored.
	 * @return An integer for the amount of health that the item restores.
	 */
	public int getHealth(){
		return health;
	}
	
	/**
	 * Gets a description of the item. Useful for the shop display.
	 * @return A string describing the healing item.
	 */
	public String describe() {
		String description = name + " heals the hero when applied at the hospital." +
				"\nHealth increase: " + health + "%" +
				"\nApplication time: " + time + " seconds";
		return description;
	}

	/**
	 * Gets the item type (HealingItem) useful for casting back from buyable.
	 * @return A string "healingItem"
	 */
	public String type() {
		return "healingItem";
	}
	
}
