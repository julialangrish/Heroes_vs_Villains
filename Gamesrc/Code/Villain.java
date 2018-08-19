package Code;
import java.util.ArrayList;

/**
 * The villain object for the game. Each map has a unique villain.
 * Is fought in the battle part of the game.
 *
 */
public class Villain {

	private boolean alive = true;
	private String name;
	private String mainTaunt;
	private int strengthModifier = 0;
	private String favoriteGame = "none";
	private int maxHealth = 100;
	private int health;
	private boolean isSuperVillain = false;
	
	
	/**
	 * Creates the villain, gives it the appropriate attributes for its type.
	 * @param type An Integer representing which type of villain to create
	 */
	public Villain(int type){
		switch(type){
		case 1:
			name = "Cold Dealer";
			mainTaunt = "Fate will not be on your side";
			favoriteGame = "Dice Game";
			break;
		case 2:
			name = "The Crimson Killer";
			mainTaunt = "You can not defeat me!";
			favoriteGame = "Number Guess";
			break;
		case 3:
			name = "Void";
			mainTaunt = "This is the end.";
			favoriteGame = "Paper Scissors Rock";
			break;
		case 4:
			name = "Guardian of Death";
			mainTaunt = "Are you are hero, or merely a coward?";
			strengthModifier = 1;
			break;
		case 5:
			name = "The Shadow King";
			mainTaunt = "The shadows are waiting for you";
			maxHealth = 120;
			break;
		case 0: //super villain case
			name = "Lord Destruction";
			mainTaunt = "The World is Mine.";
			strengthModifier = 1;
			maxHealth = 160;
			isSuperVillain = true;
			break;
		}
		health = maxHealth;
	}
	
	
	/**
	 * Determines if a villain is alive.
	 * @return true is Villain is alive, otherwise false
	 */
	public boolean getAlive(){
		return alive;
	}
	
	/**Gets the name of the Villain
	 * @return The villain's name.
	 */
	public String getName(){
		return name;
	}
	
	/**Gets the taunt of the Villain
	 * @return The villain's taunt.
	 */
	public String getMainTaunt(){
		return mainTaunt;
	}
	
	/**Gets the strength modifier of this Villain 
	 * @return a modifier which increases the amount the villain hurts the hero. (0 for no increase).
	 */
	public int getStrengthModifier(){
		return strengthModifier;
	}
	
	/**Gets the favorite game of this Villain
	 * @return a string noting the villains favorite game.
	 */
	public String getFavoriteGame(){
		return favoriteGame;
	}
	
	/**
	 * Gets the villains max health
	 * @return The maximum health of the villain.
	 */
	public int getMaxHealth(){
		return maxHealth;
	}
	
	/**
	 * Gets the villains health.
	 * @return The amount of health the villain has.
	 */
	public int getHealth(){
		return health;
	}
	
	
	/**
	 * Changes the health value of the villain, if health drops below 0, marks villain as dead by setting alive to false.
	 * Positive values increase health, negative numbers decrease health.
	 * @param healthChange The amount to change the health by
	 */
	public void changeHealth(int healthChange){
		health = health + healthChange;
		if (health >= maxHealth){
			health = maxHealth;
		}
		else if (health <= 0){
			alive = false;
			health = 0;
		}
		
	}
	
	
	/**
	 * Determines if the villain is a SuperVillain
	 * @return A boolean value which is true only if the villain is a supervillain
	 */
	public boolean isSuperVillain(){
		return isSuperVillain;
	}
	
	
	/**
	 * Selects a game. If the hero has a favorite game it will always be that game.
	 * @return A string naming a game.
	 */
	public String chooseGame(){
		if (favoriteGame.equals("none")){
			ArrayList<String> games = new ArrayList<String>();
			games.add("Paper Scissors Rock");
			games.add("Dice Game");
			games.add("Number Guess");
			return games.get((int)(Math.random() * 3));
		}
		else{
			return favoriteGame;
		}
	}
}
