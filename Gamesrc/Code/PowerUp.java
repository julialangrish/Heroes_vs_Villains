package Code;

/**A PowerUp used to give a Hero extra abilities
 */
public class PowerUp implements Buyable{
	
	
	/*
	 * Types:
	 * 1: Shield
	 * 2: Steroids
	 * 3: Lucky Horseshoe
	 * 4: Time Machine
	 */
	int type;
	int cost = 50; //adjust later
	int count = 0;
	
	
	/**Creates a new PowerUp Buyable
	 * @param powerup An Integer specifying which type of PowerUp to create
	 */
	public PowerUp(int powerup) {
		type = powerup;
	}


	public int getCost() {
		return cost;
	}
	

	public int getTypeInt() {
		return type;
	}

	public String getName() {
		switch(type) {
		case 1:		return "Shield";
		case 2:		return "Steroids";
		case 3: 	return "Lucky Horseshoe";
		case 4:		return "Time Machine";
		default:	return "There is something wrong with the code...";
		}
	}

	public String describe() {
		switch(type) {
		case 1:		return "Provides a hero with extra defense for one battle";
		case 2:		return "Provides a hero with extra strength for one battle";
		case 3: 	return "Provides a hero with extra luck for one battle";
		case 4:		return "Provides a hero with the ability to predict which game a villain will pick (one time use)";
		default:	return "There is something wrong with the code...";
		}
	}
	
	public int getCount() {
		return count;
	}
	
	public void changeCount(int difference) {
		count += difference;
		if (count < 0) {
			count = 0;
		}
	}

	public String type() {
		return "PowerUp";
	}
}
