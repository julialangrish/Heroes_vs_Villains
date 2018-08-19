package Code;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.awt.event.ActionEvent;
import javax.swing.JOptionPane;
import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import java.awt.Font;

/**A GUI for battling the villain
 * @author Ben Slattery
 */
public class GUIBattle {

	private JFrame frame;
	private String gameType;
	private Hero hero;
	private Villain villain;
	private boolean luck;
	private GameEnvironment gameEnvironment;
	private JLabel Outcome = new JLabel("");
	private JButton nextArea = new JButton("");
	private JLabel lblImage = new JLabel("");

	private int numberGuessGuessesRemaining = 3;
	private int numberGuessVillainsNumber = (int)(Math.random() * 10) + 1;
	
	//INPUTS
	//DICE GAME
	JButton RollDice = new JButton("Roll Dice");
	//NUMBER GUESS
	JSlider slider = new JSlider();
	JButton btnGuess = new JButton("Guess");
	//PAPER SCISSORS ROCK
	JButton Paper = new JButton("");
	JButton Scissors = new JButton("");
	JButton Rock = new JButton("");
	
	

	private String[] PSRChoices = {"Paper", "Scissors", "Rock"};
	private String PSRVillainsChoice = PSRChoices[(int)(Math.random() * 3)];
	private JLabel PSRHero = new JLabel("");
	private JLabel PSRVillain = new JLabel("");
	private JLabel PSRLuckLabel = new JLabel("villain has a 50% chance of picking " + PSRVillainsChoice.toLowerCase());
	
	
	/**Closes the window
	 */
	public void closeWindow(){
		frame.dispose();
	}
	
	/**Closes the GUI.
	 */
	private void finishWindow() {
		gameEnvironment.closeGUIBattle(this);
	}
	
	/**Creates the application
	 * @param newGameEnvironment The environment the battle is running in
	 * @param newHero The Hero that is entering the battle
	 * @param newVillain The Villain that is entering the battle
	 */
	public GUIBattle(GameEnvironment newGameEnvironment, Hero newHero, Villain newVillain) {
		gameEnvironment = newGameEnvironment;
		hero = newHero;
		villain = newVillain;
		gameType = villain.chooseGame();
		luck = hero.getBuff("luck");
		initialize();
		frame.setVisible(true);
	}

	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {

		frame = new JFrame();
		frame.getContentPane().setBackground(Color.DARK_GRAY);
		frame.setTitle("Heroes vs Villains | " + gameType);
		frame.setResizable(false);
		frame.setBounds(100, 100, 750, 450);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel PSRPanel = new JPanel();
		if (gameType.equals("Paper Scissors Rock")) {
			PSRPanel.setVisible(true);
		}	else {
			PSRPanel.setVisible(false);
		}
		
		PSRPanel.setBounds(77, 86, 361, 189);
		frame.getContentPane().add(PSRPanel);
		PSRPanel.setLayout(null);
		PSRLuckLabel.setFont(new Font("Dialog", Font.PLAIN, 12));
		PSRLuckLabel.setHorizontalAlignment(SwingConstants.CENTER);
		
		PSRLuckLabel.setVisible(luck);
		PSRLuckLabel.setBounds(10, 37, 341, 14);
		PSRPanel.add(PSRLuckLabel);
		Paper.setIcon(new ImageIcon(GUIBattle.class.getResource("/assets/paper.png")));
		
		Paper.setBounds(53, 53, 75, 75);
		PSRPanel.add(Paper);
		Scissors.setIcon(new ImageIcon(GUIBattle.class.getResource("/assets/scissors.png")));
		
		Scissors.setBounds(138, 53, 75, 75);
		PSRPanel.add(Scissors);
		Rock.setIcon(new ImageIcon(GUIBattle.class.getResource("/assets/rock.png")));
		
		Rock.setBounds(223, 53, 75, 75);
		PSRPanel.add(Rock);
		PSRHero.setFont(new Font("Dialog", Font.PLAIN, 12));


		PSRHero.setBounds(38, 139, 260, 14);
		PSRPanel.add(PSRHero);
		PSRVillain.setFont(new Font("Dialog", Font.PLAIN, 12));


		PSRVillain.setBounds(67, 158, 284, 20);
		PSRPanel.add(PSRVillain);
		
		JLabel lblYouAreChallanged = new JLabel("You are challanged to a game of Paper Scissors Rock!");
		lblYouAreChallanged.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblYouAreChallanged.setHorizontalAlignment(SwingConstants.CENTER);
		lblYouAreChallanged.setBounds(10, 11, 341, 14);
		PSRPanel.add(lblYouAreChallanged);
		
		JPanel NumberguessPanel = new JPanel();
		NumberguessPanel.setVisible(gameType.equals("Number Guess"));
		NumberguessPanel.setBounds(77, 86, 361, 189);
		frame.getContentPane().add(NumberguessPanel);
		NumberguessPanel.setLayout(null);
		
		JLabel GuessLuckyLabel = new JLabel("");
		GuessLuckyLabel.setFont(new Font("Dialog", Font.PLAIN, 12));
		GuessLuckyLabel.setText("Lucky numbers: " + genLuckyNums()); 
		GuessLuckyLabel.setVisible(luck);
		GuessLuckyLabel.setBounds(10, 42, 318, 14);
		NumberguessPanel.add(GuessLuckyLabel);
		
		JLabel lblGuess = new JLabel("Guess:");
		lblGuess.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblGuess.setBounds(10, 67, 54, 14);
		NumberguessPanel.add(lblGuess);
		slider.setFont(new Font("Dialog", Font.PLAIN, 12));
		
		
		slider.setValue(6);
		slider.setMinimum(1);
		slider.setMaximum(10);
		slider.setBounds(74, 65, 200, 26);
		NumberguessPanel.add(slider);
		
		JLabel GuessedNum = new JLabel("6");
		GuessedNum.setFont(new Font("Dialog", Font.PLAIN, 12));
		GuessedNum.setBounds(284, 67, 54, 14);
		NumberguessPanel.add(GuessedNum);
		
		JLabel lblGuessesRemaining = new JLabel("Guesses remaining: 3");
		lblGuessesRemaining.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblGuessesRemaining.setBounds(10, 103, 143, 14);
		NumberguessPanel.add(lblGuessesRemaining);
		btnGuess.setFont(new Font("Dialog", Font.PLAIN, 12));
		
		
		btnGuess.setBounds(10, 155, 89, 23);
		NumberguessPanel.add(btnGuess);
		
		JLabel lblVillainPicked = new JLabel("Villain picked " + numberGuessVillainsNumber);
		lblVillainPicked.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblVillainPicked.setVisible(false);
		lblVillainPicked.setBounds(143, 134, 169, 14);
		NumberguessPanel.add(lblVillainPicked);
		
		JLabel lblYouAreChallanged_1 = new JLabel("You are challanged to a game of Number Guess!");
		lblYouAreChallanged_1.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblYouAreChallanged_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblYouAreChallanged_1.setBounds(10, 11, 341, 20);
		NumberguessPanel.add(lblYouAreChallanged_1);
		
		JPanel DicePanel = new JPanel();
		DicePanel.setVisible(gameType.equals("Dice Game"));
		DicePanel.setBounds(77, 86, 361, 189);
		frame.getContentPane().add(DicePanel);
		DicePanel.setLayout(null);
		RollDice.setFont(new Font("Dialog", Font.PLAIN, 12));
		
		
		RollDice.setBounds(21, 102, 89, 53);
		DicePanel.add(RollDice);
		
		JLabel DiceLuckyLabel = new JLabel("You're lucky, villain rolls a 4 sided die");
		DiceLuckyLabel.setFont(new Font("Dialog", Font.PLAIN, 12));
		DiceLuckyLabel.setVisible(luck);
		DiceLuckyLabel.setBounds(21, 52, 258, 14);
		DicePanel.add(DiceLuckyLabel);
		
		JLabel VillainRoll = new JLabel("");
		VillainRoll.setFont(new Font("Dialog", Font.PLAIN, 12));
		VillainRoll.setBounds(199, 141, 133, 14);
		DicePanel.add(VillainRoll);
		
		JLabel heroRoll = new JLabel("");
		heroRoll.setFont(new Font("Dialog", Font.PLAIN, 12));
		heroRoll.setBounds(199, 77, 133, 14);
		DicePanel.add(heroRoll);
		
		JLabel lblYouAreChallanged_2 = new JLabel("You are challanged to a game of Dice Roll!");
		lblYouAreChallanged_2.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblYouAreChallanged_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblYouAreChallanged_2.setBounds(21, 11, 330, 24);
		DicePanel.add(lblYouAreChallanged_2);
		nextArea.setFont(new Font("Dialog", Font.PLAIN, 12));
		
		
		nextArea.setVisible(false);
		nextArea.setBounds(520, 377, 214, 23);
		frame.getContentPane().add(nextArea);
		
		
		Outcome.setForeground(Color.WHITE);
		Outcome.setBounds(520, 342, 214, 23);
		frame.getContentPane().add(Outcome);
		
		
		lblImage.setBounds(480, 91, 204, 240);
		frame.getContentPane().add(lblImage);
		
		switch(villain.getName()){
		case "Cold Dealer":
			lblImage.setIcon(new ImageIcon(GUIBattleMenu.class.getResource("/assets/villain_cd.png")));
			break;
		case "The Crimson Killer":
			lblImage.setIcon(new ImageIcon(GUIBattleMenu.class.getResource("/assets/villain_crimson.png")));
			break;
		case "Void":
			lblImage.setIcon(new ImageIcon(GUIBattleMenu.class.getResource("/assets/villain_void.png")));
			break;
		case "Guardian of Death":
			lblImage.setIcon(new ImageIcon(GUIBattleMenu.class.getResource("/assets/villain_gaurd_of_death.png")));
			break;
		case "The Shadow King":
			lblImage.setIcon(new ImageIcon(GUIBattleMenu.class.getResource("/assets/villain_sk.png")));
			break;
		case "Lord Destruction":
			lblImage.setIcon(new ImageIcon(GUIBattleMenu.class.getResource("/assets/villain_super.png")));
			break;
		}
	
		
		
		//END SETUP
		
		
		
		
		//DICE GAME
		RollDice.addActionListener(new ActionListener() {
			/**Rolls a die for the dice game
			 */
			public void actionPerformed(ActionEvent arg0) {
				int villainNumber = (int)(Math.random() * (luck?4:6)) + 1; //if lucky, chooses a random number from 1-4
				int playerNumber = (int)(Math.random() * 6) + 1; //chooses a random number from 1-6
				
				VillainRoll.setText("Villain rolled a " + villainNumber);	//change to images of dice?
				heroRoll.setText("Hero rolled a " + playerNumber);
				
				if (villainNumber == playerNumber) {
					Outcome.setText("You Drew! Roll again");
				} else {
					gameOver(playerNumber > villainNumber);
				}
				
			}
		});
		
		//NUMBER GUESS
		slider.addChangeListener(new ChangeListener() {
			/**Changes the selected number for the number guess game
			 */
			public void stateChanged(ChangeEvent arg0) {
				GuessedNum.setText(Integer.toString(slider.getValue()));	//displays current number guessed
			}
		});
		
		btnGuess.addActionListener(new ActionListener() {
			/**Guesses the villains number using current selected number 
			 */
			public void actionPerformed(ActionEvent e) {
				numberGuessGuessesRemaining -= 1;
				lblGuessesRemaining.setText("Guesses remaining: " + numberGuessGuessesRemaining);
				
				
				if (numberGuessGuessesRemaining == 0) {
					//last guess (better get it right)
					lblVillainPicked.setVisible(true);
					gameOver(slider.getValue() == numberGuessVillainsNumber);
				} else {
					if (slider.getValue() == numberGuessVillainsNumber) {
						gameOver(true);
						
					} else {
						Outcome.setText(Integer.toString(slider.getValue()) + " was incorrect, " + ((numberGuessVillainsNumber > slider.getValue())?"higher!":"lower!"));
					}
				}
			}
		});
		
		//PAPER SCISSORS ROCK
		Paper.addActionListener(new ActionListener() {
			/**Select 'Paper' for paper scissors rock game
			 */
			public void actionPerformed(ActionEvent arg0) {
				psrOutcome("Paper");
			}
		});
		

		Scissors.addActionListener(new ActionListener() {
			/**Select 'Scissors' for paper scissors rock game
			 */
			public void actionPerformed(ActionEvent e) {
				psrOutcome("Scissors");
			}
		});
		

		Rock.addActionListener(new ActionListener() {
			/**Select 'Rock' for paper scissors rock game
			 */
			public void actionPerformed(ActionEvent e) {
				psrOutcome("Rock");
			}
		});
		

		nextArea.addActionListener(new ActionListener() {
			/**Advances game to next window
			 */
			public void actionPerformed(ActionEvent arg0) {
				finishWindow();
			}
		});
		
		
	}//END OF INITIALIZER
	
	//Disables all input methods for games
	//Is only called after the game is over
	private void disableInputs() {
		Paper.setEnabled(false);
		Scissors.setEnabled(false);
		Rock.setEnabled(false);
		btnGuess.setEnabled(false);
		slider.setEnabled(false);
		RollDice.setEnabled(false);
	}
	
	//Performs the logic for the paper scissors rock game
	private void psrOutcome(String heroChoice) {
		//If the hero is lucky, ensures that the Villain has a 50% chance of picking
		//the stated choice
		if(luck) {
			if (Math.random() > 0.25) {
				PSRVillainsChoice = PSRChoices[(int)(Math.random() * 3)];
			}
		}
		
		PSRHero.setText("Hero: " + heroChoice);
		PSRVillain.setText("Villain: " + PSRVillainsChoice);
		
		heroChoice = heroChoice.substring(0, 1).toUpperCase();
		String villainChoice = PSRVillainsChoice.substring(0, 1).toUpperCase();
		
		
		if (heroChoice.equals(villainChoice)){	
			//game drew
			Outcome.setText("It's a draw! Play again!");
			PSRVillainsChoice = PSRChoices[(int)(Math.random() * 3)];
			PSRLuckLabel.setText("villain has a 50% chance of picking " + PSRVillainsChoice.toLowerCase());
		} else {
			if ((heroChoice.equals("P") && villainChoice.equals("R")) || (heroChoice.equals("R") && villainChoice.equals("S")) || (heroChoice.equals("S") && villainChoice.equals("P"))) {
				//you won
				gameOver(true);		
			} else {
				//you lost
				gameOver(false);	
			}
		}
		
	}
	
	//ends game and shows the 'next area' button
	private void gameOver(boolean won) {
		if (won) {
			Outcome.setText("You Won!");
		} else {
			Outcome.setText("You Lost!");
		}
		disableInputs();
		dealDamage(won);
		nextArea.setText(nextAreaString());
		nextArea.setVisible(true);
	}
	
	
	//deals damage to hero or villain, depending on who won
	private void dealDamage(boolean won) {
		if (won) {
			int damage = ((-40) * (hero.getBuff("strength")?3:2))/2;  //applies a 50% bonus if hero has a strength buff
			JOptionPane.showMessageDialog(frame, hero.getName() + " has dealt " + (damage * (-1)) + " damage to " + villain.getName());
			villain.changeHealth(damage);
		}
		else {
			int damage = -34; //base damage
			damage = damage - (villain.getStrengthModifier() * 10);//increase by 10 for each strength buff
			damage = damage / (hero.getBuff("shield")?2:1); //halves damage taken if hero has a shield buff
			
			hero.changeHealth(damage); 
			JOptionPane.showMessageDialog(frame, villain.getName() + " has dealt " + (damage * (-1)) + " damage to " + hero.getName() + ((hero.getAlive())?"":"\n" + hero.getName() + " has been defeated."));
			if (!hero.getAlive()) {
				gameEnvironment.getTeam().applyBonuses();
			}
		}
	}
	
	/**
	 * Decides what the 'next area' button should have written on it.
	 * Adds money to team inventory if area complete.
	 * @return The text for the next area button.
	 */
	private String nextAreaString() {
		if (gameEnvironment.getTeam().getLivingHeroes().size() < 1){
			return "Proceed";
		}
		else if(villain.getAlive()) {
			return "Return to villain's lair";
		}
		else if (gameEnvironment.getCityIndex() + 1 >= GameEnvironment.getGameSize()) { //if this was the final battle
			//Supervillain was killed, game over
			lblImage.setEnabled(false);
			gainMoney();
			return "Proceed to victory!";
		}
		gainMoney();
		lblImage.setEnabled(false);
		return "Continue to next city";
	}
	
	
	/**
	 * Gives the team money for defeating the villain.
	 */
	private void gainMoney(){
		int money = 60;
		money += gameEnvironment.getCurrentMap().getArea() * 8; //maybe add a random variable to keep it interesting
		gameEnvironment.getTeam().changeMoney(money);
		JOptionPane.showMessageDialog(frame, villain.getName() + " defeated.\nYou gained " + money + "g!");
	}



	/**
	 * Generates a list of 'lucky numbers' used in the number guess game if the hero is lucky.
	 * @return an ordered list of numbers which includes the actual number.
	 */
	private String genLuckyNums() {
		ArrayList<Integer> nums = new ArrayList<Integer>();
		nums.add(numberGuessVillainsNumber);
		while(nums.size()<6) {			//until there are 6 numbers
			int n = (int)(Math.random() * 10) + 1;
			if (!nums.contains(n)) {	//only add if this number is not already in the list
				nums.add(n);
			}
		}
		
		Collections.sort(nums);
		
		String luckyNums = "";
		for (int i = 0; i < 6; i++) {	//convert list into a string of space separated numbers
			luckyNums += " " + Integer.toString(nums.get(i));
		}
		return luckyNums;
	}
}
