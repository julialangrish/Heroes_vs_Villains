package Code;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.Timer;
import java.util.TimerTask;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JRadioButton;
import javax.swing.UIManager;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;

/**
 * The GUI for the hospital location. Allows the player to use healing items to recover a hero's health. The health increases over a period of time.
 * A clock displays how much time is left for the health to recover. Each type of item can be applied to each hero simultaneously, however only one
 * of the same kind of healing item can be applied to a particular hero at a time.
 */
public class GUIHospital {

	private JFrame window;
	private JButton apply = new JButton("Apply");
	private JTextPane heroDesc = new JTextPane();
	private JTextPane itemDesc = new JTextPane();
	private JLabel alert = new JLabel("No hero/item selected");
	private JLabel clockdisplay = new JLabel("0:00");
	private GameEnvironment gameEnvironment;
	private Hero selectedHero;
	private int selectedItem = -1;
	private Timer t = new Timer();
	
	/**
	 * Keeps the clock updated to let the user know how much time is left for the hero to heal.
	 */
	TimerTask task = new TimerTask() {
		public void run(){
			updateClock();
		}	
	};


	/**Creates the application
	 * @param newGameEnvironment The environment the Hospital is running in
	 */
	public GUIHospital(GameEnvironment newGameEnvironment) {
		gameEnvironment = newGameEnvironment;
		initialize();
		window.setVisible(true);
		t.scheduleAtFixedRate(task, 0, 900);
	}
	
	
	/**
	 * Updates the clock display as well as the hero description (as the health amount will change regularly if being healed).
	 */
	private void updateClock(){
		String end = "0";
		String middle = "";
		if (!(selectedItem == -1) && !(selectedHero == null)){
			int time = selectedHero.getActiveHealingItemTime(selectedItem);
			if (!(time % 60 == 0)){
				end = "";
			}
			if (time < 10 && time > 0){
				middle = "0";
			}
			clockdisplay.setText((time/60) + ":" + middle + (time % 60) + end);
			if(selectedHero != null){
				heroDesc.setText(selectedHero.getHeroInfo());
			}
			decideIfApply();
		}
	}

	/**
	 * Selects a hero for the item application.
	 * @param i the index of the hero chosen.
	 */
	private void selectHero(int i){
		selectedHero = gameEnvironment.getTeam().getHeroes().get(i);
		heroDesc.setText(selectedHero.getHeroInfo());
		updateClock();
		decideIfApply();
	}
	
	/**
	 * Select an item for the item application.
	 * @param i the index of the item chosen
	 */
	private void selectItem(int i){
		selectedItem = i;
		HealingItem item = gameEnvironment.getTeam().getInventoryHealingItems().get(i);
		itemDesc.setText(item.getName() + "\n\nAmount Owned: " + item.getCount() + "\nHealth Increase: " + item.getHealth() + "\nApplication Time: " + item.getTime());
		updateClock();
		decideIfApply();
	}
	
	
	/**
	 * Decides whether to activate the apply button.
	 * If not chooses an appropriate message to notify the player why the button is disabled.
	 */
	private void decideIfApply(){
		if(selectedHero == null){
			apply.setEnabled(false);
			alert.setText("No hero selected.");
		}
		else if(selectedItem == -1){
			apply.setEnabled(false);
			alert.setText("No item selected.");
		}
		else if (selectedHero.getActiveHealingItemTime(selectedItem) > 0){
			apply.setEnabled(false);
			alert.setText(gameEnvironment.getTeam().getInventoryHealingItems().get(selectedItem).getName() + " is already being applied to (hero)");
		}
		else if(gameEnvironment.getTeam().getInventoryHealingItems().get(selectedItem).getCount() < 1){
			apply.setEnabled(false);
			alert.setText("You do not own any " + gameEnvironment.getTeam().getInventoryHealingItems().get(selectedItem).getName());
		}
		else{
			alert.setText("");
			apply.setEnabled(true);
		}
	}
	
	
	/**
	 * Closes this GUI.
	 */
	public void closeWindow(){
		window.dispose();
	}
	
	
	/**
	 * Closes this GUI in the game environment.
	 * The GameEnvironment will deal with what needs to happen next.
	 */
	private void finishedWindow(){
		gameEnvironment.closeGUIHospital(this);
	}
	
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		window = new JFrame();
		window.getContentPane().setBackground(UIManager.getColor("Button.background"));
		window.setTitle("Heroes vs Villains | Hospital");
		window.setResizable(false);
		window.setBounds(100, 100, 750, 460);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.getContentPane().setLayout(null);
		
		JButton btnLeave = new JButton("Return to Hero Base");
		btnLeave.setFont(new Font("Dialog", Font.PLAIN, 12));
		btnLeave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				finishedWindow();
			}
		});
		btnLeave.setBounds(546, 385, 171, 23);
		window.getContentPane().add(btnLeave);
		
		JLabel intro = new JLabel("Welcome to the Hospital");
		intro.setFont(new Font("Garamond", Font.PLAIN, 20));
		intro.setBounds(40, 22, 288, 23);
		window.getContentPane().add(intro);
		
		JLabel lblSelectHero = new JLabel("Select Hero");
		lblSelectHero.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblSelectHero.setBounds(30, 61, 99, 14);
		window.getContentPane().add(lblSelectHero);
		
		JRadioButton btnhero1 = new JRadioButton("Hero 1");
		btnhero1.setFont(new Font("Dialog", Font.PLAIN, 12));
		btnhero1.setBounds(30, 82, 109, 23);
		window.getContentPane().add(btnhero1);
		
		JRadioButton btnhero2 = new JRadioButton("Hero 2");
		btnhero2.setFont(new Font("Dialog", Font.PLAIN, 12));
		btnhero2.setBounds(30, 108, 109, 23);
		window.getContentPane().add(btnhero2);
		
		JRadioButton btnhero3 = new JRadioButton("Hero 3");
		btnhero3.setFont(new Font("Dialog", Font.PLAIN, 12));
		btnhero3.setBounds(30, 133, 109, 23);
		window.getContentPane().add(btnhero3);
		
		JRadioButton btnitem3 = new JRadioButton(gameEnvironment.getTeam().getInventoryHealingItems().get(2).getName());
		btnitem3.setFont(new Font("Dialog", Font.PLAIN, 12));
		btnitem3.setBounds(206, 133, 109, 23);
		window.getContentPane().add(btnitem3);
		
		JRadioButton btnitem2 = new JRadioButton(gameEnvironment.getTeam().getInventoryHealingItems().get(1).getName());
		btnitem2.setFont(new Font("Dialog", Font.PLAIN, 12));
		btnitem2.setBounds(206, 108, 109, 23);
		window.getContentPane().add(btnitem2);
		
		JRadioButton btnitem1 = new JRadioButton(gameEnvironment.getTeam().getInventoryHealingItems().get(0).getName());
		btnitem1.setFont(new Font("Dialog", Font.PLAIN, 12));
		btnitem1.setBounds(206, 82, 109, 23);
		window.getContentPane().add(btnitem1);
		
		JLabel lblYourTeamHas = new JLabel("");
		lblYourTeamHas.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblYourTeamHas.setBounds(25, 400, 390, 23);
		window.getContentPane().add(lblYourTeamHas);
		if (gameEnvironment.getTeam().hasBonusRecovery()){
			lblYourTeamHas.setText("Your team has a nurse. Item Aplication time is reduced.");
		}
		
		JLabel lblSelectItem = new JLabel("Select Item");
		lblSelectItem.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblSelectItem.setBounds(206, 61, 109, 14);
		window.getContentPane().add(lblSelectItem);
		apply.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//remove item from inventory
				gameEnvironment.getTeam().removeInventoryHealingItems(selectedItem);
				
				//apply to hero
				int time = gameEnvironment.getTeam().getInventoryHealingItems().get(selectedItem).getTime();
				if (gameEnvironment.getTeam().hasBonusRecovery()){
					time = time / 2;
				}
				selectedHero.setActiveHealingItemTime(selectedItem, time);
				decideIfApply();
			}
		});
		apply.setEnabled(false);
		
		apply.setBounds(216, 294, 99, 54);
		window.getContentPane().add(apply);
		
		clockdisplay.setForeground(new Color(34, 139, 34));
		clockdisplay.setBackground(new Color(105, 105, 105));
		clockdisplay.setHorizontalAlignment(SwingConstants.RIGHT);
		clockdisplay.setFont(new Font("Consolas", Font.PLAIN, 36));
		clockdisplay.setBounds(457, 51, 144, 54);
		window.getContentPane().add(clockdisplay);
		
		JLabel lblHospitalClock = new JLabel("Clock");
		lblHospitalClock.setBounds(479, 110, 154, 19);
		window.getContentPane().add(lblHospitalClock);
		
		heroDesc.setEditable(false);
		heroDesc.setBounds(30, 162, 154, 227);
		window.getContentPane().add(heroDesc);
		
		itemDesc.setEditable(false);
		itemDesc.setBounds(206, 162, 154, 125);
		window.getContentPane().add(itemDesc);
		
		JTextPane txtpnclockBackground = new JTextPane();
		txtpnclockBackground.setBackground(Color.DARK_GRAY);
		txtpnclockBackground.setEditable(false);
		txtpnclockBackground.setToolTipText("");
		txtpnclockBackground.setBounds(481, 51, 144, 54);
		window.getContentPane().add(txtpnclockBackground);
		alert.setFont(new Font("Dialog", Font.PLAIN, 12));
		
		alert.setBounds(194, 346, 419, 23);
		window.getContentPane().add(alert);
		
		JTextPane txtpnYouAreAt = new JTextPane();
		txtpnYouAreAt.setEditable(false);
		txtpnYouAreAt.setText("You are at the hospital.\n\n"
				+ "To apply an item, select a hero and an item and click apply\n\n"
				+ "To see how much time is remaining for a particular item, select the item and the hero you applied it to "
				+ "and it will display on the clock above.");
		txtpnYouAreAt.setBackground(UIManager.getColor("Button.shadow"));
		txtpnYouAreAt.setBounds(409, 150, 288, 196);
		window.getContentPane().add(txtpnYouAreAt);
		
		
		
		
		
		
		btnhero1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				btnhero1.setSelected(true);
				btnhero2.setSelected(false);
				btnhero3.setSelected(false);
				selectHero(0);
			}
		});
		
		btnhero2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnhero1.setSelected(false);
				btnhero2.setSelected(true);
				btnhero3.setSelected(false);
				selectHero(1);
			}
		});
		
		btnhero3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnhero1.setSelected(false);
				btnhero2.setSelected(false);
				btnhero3.setSelected(true);
				selectHero(2);
			}
		});
		
		btnitem1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnitem1.setSelected(true);
				btnitem2.setSelected(false);
				btnitem3.setSelected(false);
				selectItem(0);
			}
		});
		
		btnitem2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnitem1.setSelected(false);
				btnitem2.setSelected(true);
				btnitem3.setSelected(false);
				selectItem(1);
			}
		});
		
		btnitem3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnitem1.setSelected(false);
				btnitem2.setSelected(false);
				btnitem3.setSelected(true);
				selectItem(2);
			}
		});
		

		
		switch(gameEnvironment.getTeam().getHeroes().size()){ //decides what heroes to set as visible/dead etc -test this
			case 3:
				if (!(gameEnvironment.getTeam().getHeroes().get(0).getAlive())){
					btnhero1.setEnabled(false);;
				}
				if (!(gameEnvironment.getTeam().getHeroes().get(1).getAlive())){
					btnhero2.setEnabled(false);;
				}
				if (!(gameEnvironment.getTeam().getHeroes().get(2).getAlive())){
					btnhero3.setEnabled(false);;
				}
				btnhero1.setText(gameEnvironment.getTeam().getHeroes().get(0).getName());
				btnhero2.setText(gameEnvironment.getTeam().getHeroes().get(1).getName());
				btnhero3.setText(gameEnvironment.getTeam().getHeroes().get(2).getName());
				break;
			case 2:
				btnhero1.setText(gameEnvironment.getTeam().getHeroes().get(0).getName());
				btnhero2.setText(gameEnvironment.getTeam().getHeroes().get(1).getName());
				btnhero3.setVisible(false);
				if (!(gameEnvironment.getTeam().getHeroes().get(0).getAlive())){
					btnhero1.setEnabled(false);;
				}
				if (!(gameEnvironment.getTeam().getHeroes().get(1).getAlive())){
					btnhero2.setEnabled(false);;
				}
				break;
			case 1:
				btnhero1.setText(gameEnvironment.getTeam().getHeroes().get(0).getName());
				btnhero2.setVisible(false);
				btnhero3.setVisible(false);
				if (!(gameEnvironment.getTeam().getHeroes().get(0).getAlive())){
					btnhero1.setEnabled(false);;
				}
				break;
		}
		
	}
}
