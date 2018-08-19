package Code;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Timer;
import java.util.TimerTask;

/**The main game environment for running the game
 */
public class GameEnvironment {
	
	private static int gameSize;
	private Team team;
	private ArrayList<WorldMap> maps = new ArrayList<WorldMap>();
	private int currentMapIndex;
	Timer timer = new Timer();
	int secondsPassed = 0;
	

	/**Gets credits for the game.
	 * @return A string representation of the game credits
	 */
	public String getCredits(){
		String credits = "Heroes vs Villains\n\n"
				+ "Created by\n"
				+ "   Ben Slattery\n"
				+ "   Julia Langrish";
		return credits;
	}
	
	/**Keeps track of time passed since the setup, and runs the healing command.
	 */
	TimerTask task = new TimerTask() {
		public void run(){
			secondsPassed++;
			healAllHeroes();
		}	
	};
	
	
	/**Gets the amount of time passed since starting the game
	 * @return Seconds passed since the start of the game
	 */
	public String getTime(){
		return Integer.toString(secondsPassed);
	}
	
	
	/**Iterates through all active heroes and heals them if they have used a healing item
	 */
	private void healAllHeroes() {
		Hero hero;
		for (int i = 0; i < team.getLivingHeroes().size(); i++) { 	//for each living hero (index i)
			hero = team.getLivingHeroes().get(i);
			for (int j = 0; j < 3; j++){							//for each healing item
				if (hero.getActiveHealingItemTime(j) > 0) {			//if the healing item isn't completely used
					if (hero.getHealth() < 100) {	//if hero isn't fully healed
						hero.changeHealth(healingItemHealthChange(j, hero));						//apply heal
						hero.setActiveHealingItemTime(j, hero.getActiveHealingItemTime(j) - 1);		//decrease time left on healingitem by 1 second
					} else {
						hero.setActiveHealingItemTime(j, 0);		//hero is healed, reset healingitem time to 0.
					}
				}
			}
		}
	}
	
	
	/**Finds the amount of health to heal a hero per second based on the healing item strength and hero's max health
	 * @param type Type of healing item used to heal the hero
	 * @param hero The hero currently being healed
	 * @return An amount of health to be healed every second
	 */
	private int healingItemHealthChange (int type, Hero hero) {
		HealingItem healingItem = team.getInventoryHealingItems().get(type);
		int HealthChange = healingItem.getHealth();
		HealthChange *= 100;	//set healthChange as a percentage of max health * 100
		HealthChange /= healingItem.getTime();  //divide health change into second increments
		HealthChange /= 100;					//healthChange as a percentage of max health, in second increments
		HealthChange += 1;						//deal with rounding errors
		return HealthChange;
	}

	
	/**Gets a list of all the items obtainable in the game
	 * @return an ArrayList of all obtainable items in the game
	 */
	public ArrayList<Buyable> getObtainableItems(){
		ArrayList<Buyable> items = new ArrayList<Buyable>();
		for (int i = 0; i < 3; i++) {
			items.add(team.getInventoryHealingItems().get(i));
		}
		for (int i = 0; i < 4; i++) {
			items.add(team.getPowerUps().get(i));
		}
		for (int i = currentMapIndex; i < maps.size(); i++) {
			if (team.getMaps().get(i).getCount() != 1) {
				items.add(team.getMaps().get(i));
			}
		}
		return items;
	}
	
	
	/** Gets the team currently playing
	 * @return A Team of Heroes
	 */
	public Team getTeam(){
		return team;
	}
	
	
	/** Gets how many cities are in the game
	 * @return An Integer representing the number of cities in the game
	 */
	public static int getGameSize() {
		return gameSize;
	}
	
	
	/**Gets the map of the current city
	 * @return A WorldMap of the current city
	 */
	public WorldMap getCurrentMap(){
		return maps.get(currentMapIndex);
	}
	
	
	/** Gets the city index the Team is currently in
	 * @return An Integer representing the index of the current city
	 */
	public int getCityIndex(){
		return currentMapIndex;
	}

	
	/**Generates maps for all cities in the game and creates a Team
	 * @param gameSize An Integer representing how many cities are in the game
	 * @param teamName A String representing the name of the Team
	 */
	public void setup(int gameSize, String teamName) {
		ArrayList<Villain> villains = new ArrayList<Villain>();
		for (int i = 0; i < 5; i++) {
			villains.add(new Villain(i + 1));
		}
		Collections.shuffle(villains);
		for (int i = 0; i < gameSize; i++) {
			if (i < gameSize - 1){
				Villain newVillain = villains.get(0);
				villains.remove(0);
				maps.add(new WorldMap(newVillain, i));
			}
			else{
				Villain superVillain = new Villain(0);
				maps.add(new WorldMap(superVillain, i));
			}
		}
		team = new Team(teamName, gameSize);
	}

	
	/**Starts the game.
	 */
	public void runGameGUI(){
		new GUIIntroScreen(this);
	}
	
	/**Decides what to do after the fight with the villain.
	 * Either wins the game, loses the game, or continue on to the next level.
	 */
	public void processLevelComplete(){
				currentMapIndex += 1;
				team.resetHeroBuffs();
				if (team.getLivingHeroes().size() < 1){
					new GUILose(this);
				}
				else if(currentMapIndex >= maps.size()){
					new GUIWin(this);
				}
				else{
					launchGUIMap();
				}
	}
	
	//--------------GUI CODE--------------------------------------------------------
	
	/**Closes the intro screen GUI
	 * @param window A GUIIntroScreen to be closed
	 */
	public void closeGUIIntroScreen(GUIIntroScreen window){
		window.closeWindow();
		new GUISetup(this);
	}
	
	/**Closes the setup GUI and places the Team in the homebase
	 * @param window A GUISetup to be closed
	 */
	public void closeGUISetup(GUISetup window){
		window.closeWindow();
		gameSize = maps.size();
		timer.scheduleAtFixedRate(task, 0, 1000); //start timer
		launchGUIMap();
	}
	
	/**Opens a new GUIMap for the current city
	 */
	public void launchGUIMap() {
		new GUIHomeBase(this);
	}
	
	/**Leaves the shop and returns to the homebase
	 * @param window a GUIShop to be closed
	 */
	public void closeGUIShop(GUIShop window){
		window.closeWindow();
		launchGUIMap();
	}
	
	/**Leaves the hospital and returns to the homebase
	 * @param window a GUIHostpital to be closed
	 */
	public void closeGUIHospital(GUIHospital window){
		window.closeWindow();
		launchGUIMap();
	}
	
	/**Leaves the powerup den and returns to the homebase
	 * @param window A GUIPowerUpDen to be closed
	 */
	public void closeGUIPowerUpDen(GUIPowerUpDen window) {
		window.closeWindow();
		launchGUIMap();
	}
	
	/**Leaves the villain's lair and return to the homebase.
	 * @param window A GUIVillainsLair to be closed
	 */
	public void leaveVillainsLair(GUIVillainsLair window){
		window.closeWindow();
		launchGUIMap();
	}
	
	/**Enters the villain's lair
	 * @param window A GUIVillainsLair to be closed
	 */
	public void goIntoVillainsLair(GUIVillainsLair window){
		window.closeWindow();
		new GUIBattleMenu(this);
	}
	
	/**Leaves the battle and decides what to do next
	 * @param window A GUIBattle to be closed
	 */
	public void closeGUIBattle(GUIBattle window) {
		window.closeWindow();
		if ((getCurrentMap().getVillain().getAlive()) && team.getLivingHeroes().size() > 0){
			new GUIBattleMenu(this);
		}
		else{
			processLevelComplete();
		}
	}
	
	/**Closes the BattleMenu and enters the battle
	 * @param window The battle GUI
	 * @param hero Hero chosen for battle
	 */
	public void closeGUIBattleMenu(GUIBattleMenu window, Hero hero){
		window.closeWindow();
		new GUIBattle(this, hero, getCurrentMap().getVillain());
	}
	

	
	/**Opens a GUI of a new area
	 * @param map A GUIMap to be closed
	 * @param area A String representing the name of the area GUI to be opened
	 */
	public void enterArea(GUIHomeBase map, String area) {
		map.closeWindow();
		
		switch (area) {
		case "Shop":			new GUIShop(this);
								break;
		case "Powerup Den":		new GUIPowerUpDen(this);
								break;
		case "Hospital":		new GUIHospital(this);
								break;
		case "Villain's Lair":	new GUIVillainsLair(this);
		}
	}


	//--------------------------------------------------------
	
	public static void main(String[] args){
		GameEnvironment heroesvsvillains = new GameEnvironment();
		heroesvsvillains.runGameGUI();       //runs the game
	}
	
}
