package Code;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.awt.event.ActionEvent;
import javax.swing.JRadioButton;

/**The GUI for the Home Base area
 */
public class GUIHomeBase {

	private JFrame window;
	private GameEnvironment gameEnvironment;
	
	private JButton TravelNorthButton = new JButton("North");
	private JButton TravelSouthButton = new JButton("South");
	private JButton TravelWestButton = new JButton("West");
	private JButton TravelEastButton = new JButton("East");

	/**Creates the application
	 * @param newGameEnvironment The environment the HomeBase is running in
	 */
	public GUIHomeBase(GameEnvironment newGameEnvironment) {
		gameEnvironment = newGameEnvironment;
		initialize();
		window.setVisible(true);
		setDirectionsVisible();
		randomEvent(); //runs the random event
	}
	
	/**Closes the HomeBase GUI
	 */
	public void closeWindow() {
		window.dispose();
	}
	
	/**
	 * Has a 10% chance of giving the team a gift
	 * a 10% chance of having an item robbed
	 * a 80% chance of nothing happen.
	 * Displays a relevant message dialogue if something happens.
	 */
	private void randomEvent(){
		Random random = new Random();
		int chance = random.nextInt(10);
		//chance = 1; //use for testing
		switch(chance){
		case 1:
			lose_item();
			break;
		case 2:
			gift_item();
			break;
		}
	}
	
	
	/**
	 * Takes a random item from the player.
	 */
	private void lose_item(){
		String item = gameEnvironment.getTeam().stealItem();
		if (!item.equals("none")){
			JOptionPane.showMessageDialog(window, "You have been robbed! The theif took your: \n" + item);
		}
	}
	
	
	/**
	 * Gifts a random item to the team and alerts the player.
	 */
	private void gift_item(){
		ArrayList<Buyable> items = gameEnvironment.getObtainableItems();
		Collections.shuffle(items);
		Buyable item = items.get(0);
		gameEnvironment.getTeam().addToInventory(item);
		if (item.type().equals("Map")) {
			setDirectionsVisible();
		}
		JOptionPane.showMessageDialog(window, "You have been gifted an item: " + item.getName());
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		window = new JFrame();
		window.getContentPane().setEnabled(false);
		window.setTitle("Heroes vs Villains | Hero Base");
		window.setBounds(100, 100, 750, 450);
		window.setResizable(false);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.getContentPane().setLayout(null);
		
		JLabel HeroBaseLabel = new JLabel("Hero Base");
		HeroBaseLabel.setBounds(471, 201, 108, 14);
		HeroBaseLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		window.getContentPane().add(HeroBaseLabel);
		
		JTextPane HeroInfo = new JTextPane();
		HeroInfo.setText(gameEnvironment.getTeam().getHeroes().get(0).getHeroInfo());
		HeroInfo.setEditable(false);
		HeroInfo.setBounds(25, 139, 209, 254);
		window.getContentPane().add(HeroInfo);
		TravelNorthButton.setFont(new Font("Dialog", Font.PLAIN, 12));
		
		
		
		TravelNorthButton.setBounds(452, 93, 120, 23);
		window.getContentPane().add(TravelNorthButton);
		TravelSouthButton.setFont(new Font("Dialog", Font.PLAIN, 12));
		
		TravelSouthButton.setBounds(452, 303, 120, 23);
		window.getContentPane().add(TravelSouthButton);
		TravelWestButton.setFont(new Font("Dialog", Font.PLAIN, 12));
		
		TravelWestButton.setBounds(312, 200, 120, 23);
		window.getContentPane().add(TravelWestButton);
		TravelEastButton.setFont(new Font("Dialog", Font.PLAIN, 12));
		
		TravelEastButton.setBounds(599, 200, 120, 23);
		window.getContentPane().add(TravelEastButton);
		
		JLabel MapNumber = new JLabel("Welcome to City " + (gameEnvironment.getCityIndex() + 1));
		MapNumber.setFont(new Font("Dialog", Font.PLAIN, 12));
		MapNumber.setBounds(25, 11, 156, 14);
		window.getContentPane().add(MapNumber);
		
		JLabel lblSelectWhichHero = new JLabel("Select which hero you want to check:");
		lblSelectWhichHero.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblSelectWhichHero.setBounds(25, 36, 323, 14);
		window.getContentPane().add(lblSelectWhichHero);
		
		JRadioButton SelectHero1 = new JRadioButton("Hero 1");
		SelectHero1.setFont(new Font("Dialog", Font.PLAIN, 12));
		SelectHero1.setSelected(true);
		SelectHero1.setBounds(25, 57, 109, 23);
		window.getContentPane().add(SelectHero1);
		
		JRadioButton SelectHero2 = new JRadioButton("Hero 2");
		SelectHero2.setFont(new Font("Dialog", Font.PLAIN, 12));
		if (gameEnvironment.getTeam().getHeroes().size() >= 2) {
			SelectHero2.setVisible(true);
		} else {
			SelectHero2.setVisible(false);
		}
		SelectHero2.setBounds(25, 83, 109, 23);
		window.getContentPane().add(SelectHero2);
		
		JRadioButton SelectHero3 = new JRadioButton("Hero 3");
		SelectHero3.setFont(new Font("Dialog", Font.PLAIN, 12));
		if (gameEnvironment.getTeam().getHeroes().size() >= 3) {
			SelectHero3.setVisible(true);
		} else {
			SelectHero3.setVisible(false);
		}
		SelectHero3.setBounds(25, 109, 109, 23);
		window.getContentPane().add(SelectHero3);
		
		//travel to location if button is pushed
		TravelEastButton.addActionListener(new ActionListener() {
			/**Travels to the "East" direction
			 */
			public void actionPerformed(ActionEvent e) {
				travel(gameEnvironment.getCurrentMap().getLocation("East"));
			}
		});
		TravelSouthButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/**Travels to the "South" direction
				 */
				travel(gameEnvironment.getCurrentMap().getLocation("South"));
			}
		});
		TravelWestButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/**Travels to the "West" direction
				 */
				travel(gameEnvironment.getCurrentMap().getLocation("West"));
			}
		});
		TravelNorthButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				/**Travels to the "North" direction
				 */
				travel(gameEnvironment.getCurrentMap().getLocation("North"));
			}
		});
		
		//Change hero info panel on button pushed
		SelectHero1.addActionListener(new ActionListener() {
			/**Selects Hero 1 to check info
			 */
			public void actionPerformed(ActionEvent arg0) {
				SelectHero1.setSelected(true);
				SelectHero2.setSelected(false);
				SelectHero3.setSelected(false);
				HeroInfo.setText(gameEnvironment.getTeam().getHeroes().get(0).getHeroInfo());
			}
		});
		SelectHero2.addActionListener(new ActionListener() {
			/**Selects Hero 2 to check info
			 */
			public void actionPerformed(ActionEvent e) {
				SelectHero2.setSelected(true);
				SelectHero1.setSelected(false);
				SelectHero3.setSelected(false);
				HeroInfo.setText(gameEnvironment.getTeam().getHeroes().get(1).getHeroInfo());
			}
		});
		SelectHero3.addActionListener(new ActionListener() {
			/**Selects Hero 3 to check info
			 */
			public void actionPerformed(ActionEvent e) {
				SelectHero3.setSelected(true);
				SelectHero2.setSelected(false);
				SelectHero1.setSelected(false);
				HeroInfo.setText(gameEnvironment.getTeam().getHeroes().get(2).getHeroInfo());
			}
		});
	}

	
	private void travel(String location) {
		gameEnvironment.enterArea(this, location);
	}
	
	private void setDirectionsVisible() {
		boolean canReadMap = gameEnvironment.getTeam().hasNavigator();		//checks if team has a navigator
		canReadMap = canReadMap || gameEnvironment.getTeam().ownsMap(gameEnvironment.getCityIndex());	//checks if team owns map for current area
		if (canReadMap) {
			TravelNorthButton.setText(gameEnvironment.getCurrentMap().getLocation("North"));
			TravelSouthButton.setText(gameEnvironment.getCurrentMap().getLocation("South"));
			TravelWestButton.setText(gameEnvironment.getCurrentMap().getLocation("West"));
			TravelEastButton.setText(gameEnvironment.getCurrentMap().getLocation("East"));
		}
	}
}
