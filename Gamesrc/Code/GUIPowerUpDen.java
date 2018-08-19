package Code;

import javax.swing.JFrame;
import javax.swing.JRadioButton;
import javax.swing.JTextPane;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;

/**The GUI for the PowerUpDen area
 */
public class GUIPowerUpDen {

	private JFrame frame;
	private GameEnvironment gameEnvironment;
	private Hero selectedHero;
	private PowerUp selectedPowerUp;

	/**Creates the application
	 * @param newGameEnvironment The environment the PowerUpDen is running in
	 */
	public GUIPowerUpDen(GameEnvironment newGameEnvironment) {
		gameEnvironment = newGameEnvironment;
		initialize();
		frame.setVisible(true);
	}
	
	/**Closes the window
	 */
	public void closeWindow() {
		frame.dispose();
	}
	

	private void finishedWindow(){
		gameEnvironment.closeGUIPowerUpDen(this);
	}
	
	private void selectHero(Hero hero) {
		selectedHero = hero;
	}
	
	private void selectPowerUp(PowerUp powerUp) {
		selectedPowerUp = powerUp;
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setEnabled(false);
		frame.getContentPane().setLayout(null);
		
		
		
		JRadioButton Hero1Select = new JRadioButton("Hero 1");
		Hero1Select.setFont(new Font("Dialog", Font.PLAIN, 12));
		Hero1Select.setSelected(true);
		Hero1Select.setBounds(6, 7, 109, 23);
		frame.getContentPane().add(Hero1Select);
		
		JRadioButton Hero2Select = new JRadioButton("Hero 2");
		Hero2Select.setFont(new Font("Dialog", Font.PLAIN, 12));
		Hero2Select.setBounds(6, 33, 109, 23);
		frame.getContentPane().add(Hero2Select);
		if(gameEnvironment.getTeam().getHeroes().size() > 1) {
			Hero2Select.setVisible(true);
		} else {
			Hero2Select.setVisible(false);
		}
		
		JRadioButton Hero3Select = new JRadioButton("Hero 3");
		Hero3Select.setFont(new Font("Dialog", Font.PLAIN, 12));
		Hero3Select.setBounds(6, 59, 109, 23);
		frame.getContentPane().add(Hero3Select);
		if(gameEnvironment.getTeam().getHeroes().size() > 2) {
			Hero3Select.setVisible(true);
		} else {
			Hero3Select.setVisible(false);
		}
		
	
		JTextPane txtpnHeroInformation = new JTextPane();
		txtpnHeroInformation.setEditable(false);
		txtpnHeroInformation.setBounds(10, 99, 167, 278);
		frame.getContentPane().add(txtpnHeroInformation);
		
		JTextPane txtpnPowerupInformation = new JTextPane();
		txtpnPowerupInformation.setText("Shield: Halves damage taken during battle");
		txtpnPowerupInformation.setBounds(410, 7, 290, 127);
		frame.getContentPane().add(txtpnPowerupInformation);
		
		JRadioButton ShieldPowerUp = new JRadioButton("Shield");
		ShieldPowerUp.setFont(new Font("Dialog", Font.PLAIN, 12));
		ShieldPowerUp.setSelected(true);
		ShieldPowerUp.setBounds(198, 22, 172, 23);
		frame.getContentPane().add(ShieldPowerUp);
		
		JRadioButton SteroidsPowerUp = new JRadioButton("Steroids");
		SteroidsPowerUp.setFont(new Font("Dialog", Font.PLAIN, 12));
		SteroidsPowerUp.setBounds(198, 48, 172, 23);
		frame.getContentPane().add(SteroidsPowerUp);
		
		JRadioButton LuckyHorseshoePowerUp = new JRadioButton("Lucky Horseshoe");
		LuckyHorseshoePowerUp.setFont(new Font("Dialog", Font.PLAIN, 12));
		LuckyHorseshoePowerUp.setBounds(198, 74, 172, 23);
		frame.getContentPane().add(LuckyHorseshoePowerUp);
		
		JRadioButton TimeMachinePowerUp = new JRadioButton("Time Machine");
		TimeMachinePowerUp.setFont(new Font("Dialog", Font.PLAIN, 12));
		TimeMachinePowerUp.setBounds(198, 100, 172, 23);
		frame.getContentPane().add(TimeMachinePowerUp);
		
		JButton ApplyPowerUp = new JButton("Apply Powerup");
		ApplyPowerUp.setFont(new Font("Dialog", Font.PLAIN, 12));
		ApplyPowerUp.setBounds(410, 238, 202, 61);
		frame.getContentPane().add(ApplyPowerUp);
		
		JLabel ErrorMessage = new JLabel("");
		ErrorMessage.setFont(new Font("Dialog", Font.PLAIN, 12));
		ErrorMessage.setBounds(410, 313, 290, 14);
		frame.getContentPane().add(ErrorMessage);
		
		JLabel ShieldCount = new JLabel("x" + gameEnvironment.getTeam().getPowerUps().get(0).getCount()); //displays count of owned shields
		ShieldCount.setFont(new Font("Dialog", Font.PLAIN, 12));
		ShieldCount.setBounds(376, 22, 24, 14);
		frame.getContentPane().add(ShieldCount);
		
		JLabel SteroidsCount = new JLabel("x" + gameEnvironment.getTeam().getPowerUps().get(1).getCount());	//displays count of owned steroids
		SteroidsCount.setFont(new Font("Dialog", Font.PLAIN, 12));
		SteroidsCount.setBounds(376, 49, 24, 14);
		frame.getContentPane().add(SteroidsCount);
		
		JLabel HorseshoeCount = new JLabel("x" + gameEnvironment.getTeam().getPowerUps().get(2).getCount());	//displays count of owned horseshoes
		HorseshoeCount.setFont(new Font("Dialog", Font.PLAIN, 12));
		HorseshoeCount.setBounds(376, 75, 24, 14);
		frame.getContentPane().add(HorseshoeCount);
		
		JLabel TimeMachineCount = new JLabel("x" + gameEnvironment.getTeam().getPowerUps().get(3).getCount());	//displays count of owned time machines
		TimeMachineCount.setFont(new Font("Dialog", Font.PLAIN, 12));
		TimeMachineCount.setBounds(376, 101, 24, 14);
		frame.getContentPane().add(TimeMachineCount);
		frame.setTitle("Heroes vs Villains | PowerUp Den");
		frame.setBounds(100, 100, 750, 450);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		
		
		selectHero(gameEnvironment.getTeam().getHeroes().get(0));
		txtpnHeroInformation.setText(selectedHero.getHeroInfo());
		
		JButton BackButton = new JButton("Return to hero base");
		BackButton.setFont(new Font("Dialog", Font.PLAIN, 12));
		BackButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/**Returns to the Hero base
				 */
				finishedWindow();
			}
		});
		BackButton.setBounds(560, 387, 174, 23);
		frame.getContentPane().add(BackButton);

		Hero1Select.addActionListener(new ActionListener() {
			/**Selects Hero 1
			 */
			public void actionPerformed(ActionEvent arg0) {
				selectHero(gameEnvironment.getTeam().getHeroes().get(0));
				txtpnHeroInformation.setText(selectedHero.getHeroInfo());
				Hero1Select.setSelected(true);
				Hero2Select.setSelected(false);
				Hero3Select.setSelected(false);
			}
		});
		Hero2Select.addActionListener(new ActionListener() {
			/**Selects Hero 2
			 */
			public void actionPerformed(ActionEvent e) {
				selectHero(gameEnvironment.getTeam().getHeroes().get(1));
				txtpnHeroInformation.setText(selectedHero.getHeroInfo());
				Hero2Select.setSelected(true);
				Hero1Select.setSelected(false);
				Hero3Select.setSelected(false);
			}
		});
		Hero3Select.addActionListener(new ActionListener() {
			/**Selects Hero 3
			 */
			public void actionPerformed(ActionEvent e) {
				selectHero(selectedHero = gameEnvironment.getTeam().getHeroes().get(2));
				txtpnHeroInformation.setText(selectedHero.getHeroInfo());
				Hero3Select.setSelected(true);
				Hero1Select.setSelected(false);
				Hero2Select.setSelected(false);
			}
		});
		
		selectPowerUp(gameEnvironment.getTeam().getPowerUps().get(0));

		ShieldPowerUp.addActionListener(new ActionListener() {
			/**Selects the "Shield" PowerUp
			 */
			public void actionPerformed(ActionEvent e) {
				selectPowerUp(gameEnvironment.getTeam().getPowerUps().get(0));
				txtpnPowerupInformation.setText("Shield: Halves damage taken during battle");
				ShieldPowerUp.setSelected(true);
				SteroidsPowerUp.setSelected(false);
				LuckyHorseshoePowerUp.setSelected(false);
				TimeMachinePowerUp.setSelected(false);
			}
		});
		SteroidsPowerUp.addActionListener(new ActionListener() {
			/**Selects the "Steroids" PowerUp
			 */
			public void actionPerformed(ActionEvent e) {
				selectPowerUp(gameEnvironment.getTeam().getPowerUps().get(1));
				txtpnPowerupInformation.setText("Steroids: Doubles damage delt during battle");
				SteroidsPowerUp.setSelected(true);
				ShieldPowerUp.setSelected(false);
				LuckyHorseshoePowerUp.setSelected(false);
				TimeMachinePowerUp.setSelected(false);
			}
		});
		LuckyHorseshoePowerUp.addActionListener(new ActionListener() {
			/**Selects the "Lucky Horseshoe" PowerUp
			 */
			public void actionPerformed(ActionEvent e) {
				selectPowerUp(gameEnvironment.getTeam().getPowerUps().get(2));
				txtpnPowerupInformation.setText("Lucky Horseshoe: Increases luck during battle");
				LuckyHorseshoePowerUp.setSelected(true);
				SteroidsPowerUp.setSelected(false);
				ShieldPowerUp.setSelected(false);
				TimeMachinePowerUp.setSelected(false);
			}
		});
		TimeMachinePowerUp.addActionListener(new ActionListener() {
			/**Selects the "Time Machine" PowerUp
			 */
			public void actionPerformed(ActionEvent e) {
				selectPowerUp(gameEnvironment.getTeam().getPowerUps().get(3));
				txtpnPowerupInformation.setText("Time Machine: Provides a hero with the ability to see which game a villain will play");
				TimeMachinePowerUp.setSelected(true);
				SteroidsPowerUp.setSelected(false);
				LuckyHorseshoePowerUp.setSelected(false);
				ShieldPowerUp.setSelected(false);
			}
		});
		

		

		ApplyPowerUp.addActionListener(new ActionListener() {
			/**Applies the selected PowerUp
			 */
			public void actionPerformed(ActionEvent e) {
				if(!selectedHero.getAlive()) {								//if hero is dead
					ErrorMessage.setText("Selected hero must be alive!");
				} else if(selectedPowerUp.getCount() == 0) {				//if count of powerup == 0
					if (selectedPowerUp.getName().equals("Steroids")) {
						ErrorMessage.setText("You don't have any Steroids' left!");
					} else {
						ErrorMessage.setText("You don't have any " + selectedPowerUp.getName() + "'s left!");
					}
				} else if(hasBuff(selectedHero, selectedPowerUp)) {			//if hero already has buff activated
					ErrorMessage.setText(selectedHero.getName() + " already has this buff activated!");
				} else {
					ErrorMessage.setText("");
					
					gameEnvironment.getTeam().removeInventoryPowerUp(selectedPowerUp.getTypeInt() - 1); //remove 1 of the selected powerup from inventory

					ShieldCount.setText("x" + gameEnvironment.getTeam().getPowerUps().get(0).getCount());
					SteroidsCount.setText("x" + gameEnvironment.getTeam().getPowerUps().get(1).getCount());
					HorseshoeCount.setText("x" + gameEnvironment.getTeam().getPowerUps().get(2).getCount());
					TimeMachineCount.setText("x" + gameEnvironment.getTeam().getPowerUps().get(3).getCount());	//resets count of all powerups
					
					
					if (selectedPowerUp.getName().equals("Time Machine")) {
						
						String villainFavGame = gameEnvironment.getCurrentMap().getVillain().getFavoriteGame();
						String prediction = (selectedHero.getName() + "'s ESP " + (villainFavGame.equals("none")?"is a little cloudy right now":"tells you that the villain will play " + villainFavGame));
						JOptionPane.showMessageDialog(null, prediction);
					} else {
						selectedHero.applyPowerUp(selectedPowerUp.getName());
						txtpnHeroInformation.setText(selectedHero.getHeroInfo());
						JOptionPane.showMessageDialog(null, "Applied " + selectedPowerUp.getName() + " to " + selectedHero.getName() + " successfully.");
					}
					

				}
			}
		});
	}
	
	private boolean hasBuff(Hero hero, PowerUp powerup) {
		switch (powerup.getName()) {
		case "Shield":			return hero.getBuff("shield");
		case "Steroids":		return hero.getBuff("strength");
		case "Lucky Horseshoe":	return hero.getBuff("luck");
		default:				return false;
		}
	}
	
}
