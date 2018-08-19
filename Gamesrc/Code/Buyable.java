package Code;

/**
 * 
 * A interface for objects that can be bought.
 * To be used for dealing with buying items.
 *
 */
public interface Buyable {
	
	/**Gets the cost of the item
	 * @return The cost of an item. Costs should be even so they can be divided for discounts.
	 */
	public int getCost();
	
	/**Keeps track of how many of the item is owned.
	 * @return The amount of the item owned.
	 */
	public int getCount();
	
	/**Gets an Integer specifying which type the item is
	 * @return An integer specifying the type.
	 */
	public int getTypeInt();
	
	/**Changes the count of the item by a specified amount
	 * @param difference An Integer specifying the amount to change the count of the item by
	 */
	public void changeCount(int difference);
	
	/**Gets the name of the item
	 * @return the name of the item.
	 */
	public String getName();
	
	/**Gets a text description of the item
	 * @return A text description of the item.
	 */
	public String describe();
	
	/**Find out what type of item the buyable is. Useful for casting back.
	 * @return A string specifying the objects type.
	 */
	public String type(); //helps me cast it back
}
