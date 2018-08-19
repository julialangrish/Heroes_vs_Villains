package Code;

/**
 * An object that keeps track of which maps the team owns. Can be bought at the shop.
 * If a map is owned the buttons will display the location names in the game environment.
 */
public class Map implements Buyable{
	
	/** An integer representing which area this map is for.
	 */
	private int area;
	/** How many of the item is owned. Used for team inventory purposes.
	 */
	private int count;
	
	
	/**
	 * Initializes the map.
	 * @param newArea An integer representing the area this map is for.
	 */
	public Map(int newArea) {
		area = newArea;		
	}
	
	
	/**
	 * Changes the amount owned.
	 * @param difference The amount to change the count by. Can be positive or negative.
	 */
	public void changeCount(int difference) {
		count += difference;
	}
	
	
	/**
	 * Gets the integer for the amount of this object owned
	 * @return The amounts of maps represented by this object owned.
	 */
	public int getCount() {
		return count;
	}
	
	
	/**
	 * Gets a number representing the type of this map object.
	 * @return An integer representing the type of map this object is. 0 for city 1 map, 1 for city 2 map, etc.
	 */
	public int getTypeInt() {
		return area;
	}

	
	/**
	 * Gets the cost of the item.
	 * @return An integer for the store price of the item.
	 */
	public int getCost() {
		return 16;
	}

	
	/**
	 * Gets the name of the item.
	 * @return The name of the item as a string with the format "Map (city number)"
	 */
	public String getName() {
		return "Map " + (area + 1);
	}

	
	/**
	 * Gets a description of the item.
	 * @return A string describing the item.
	 */
	public String describe() {
		return "Map for City " + (area + 1) + ". Shows location on direction button.";
	}

	
	/**
	 * Gets the type of the Buyable item (Map). Used for converting from the Buyable interface.
	 * @return A string representing the type of object this is, ("Map").
	 */
	public String type() {
		return "Map";
	}

}
