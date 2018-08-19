package Code;
import java.util.Collections;
import java.util.ArrayList;

/**A map of a city
 */
public class WorldMap {

	private ArrayList<String> places = new ArrayList<String>();
	private Villain villain;
	private int area;
	
	
	/**Creates a map for a city
	 * @param newVillain A Villain that lives in the city
	 * @param newArea An Integer specifying which city the WorldMap is for
	 */
	public WorldMap(Villain newVillain, int newArea) {
		area = newArea;
		villain = newVillain;
		places.add("Shop");
		places.add("Powerup Den");
		places.add("Hospital");
		places.add("Villain's Lair");
		
		Collections.shuffle(places);
	}
	
	/**Gets which area is in the specified direction
	 * @param direction A String specifying the cardinal direction you wish to check
	 * @return A String representing which area is in that direction
	 */
	public String getLocation(String direction) {
		switch(direction.toLowerCase()) {
		case "north":	return places.get(0);
		case "east":	return places.get(1);
		case "south":	return places.get(2);
		default:		return places.get(3);
		}
	}
	
	/**Gets which city the WorldMap is for
	 * @return An Integer specifying the city the WorldMap is for
	 */
	public int getArea() {
		return area;
	}
	
	/**Gets the Villain that lives in this city
	 * @return A Villain which lives in this city
	 */
	public Villain getVillain(){
		return villain;
	}

}