package Code;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JToggleButton;
import javax.swing.JTextPane;
import java.awt.Color;
import java.awt.Font;
import javax.swing.SwingConstants;

/**
 * A GUI for the shop part of the game.
 * The player can look at and purchase items to help them in the game.
 * Items can only be bought if they have enough money.
 */
public class GUIShop {

	private JFrame window;
	private GameEnvironment gameEnvironment;
	private JTextPane infobox;
	private Buyable currentItem;
	private JButton btnBuy;
	private JLabel lblerror;
	private JLabel itembought;
	private int b0Index = -1;
	private int b1Index = -1;
	private int b2Index = -1;
	private int b3Index = -1;
	private int b4Index = -1;
	private int b5Index = -1;
	

	private JButton button = new JButton("");
	private JButton button_1 = new JButton("");
	private JButton button_2 = new JButton("");
	private JButton button_3 = new JButton("");
	private JButton button_4 = new JButton("");
	private JButton button_5 = new JButton("");
	
	
	/**
	 * Creates the GUI.
	 * @param newGameEnvironment The GameEnvironment which manages the games attributes and events.
	 */
	public GUIShop(GameEnvironment newGameEnvironment) {
		gameEnvironment = newGameEnvironment;
		initialize();
		window.setVisible(true);
	}
	
	
	/**
	 * Chooses the item to show description and allow buying
	 */
	private void updateItem(int index){
		currentItem = gameEnvironment.getObtainableItems().get(index);
		updateInfo();
	}
	
	/**
	 * Updates the information displayed including the info of the selected item, and
	 * whether or not the item can be bought and alerts the user why.
	 * Will disable the buy button if the item cannot be bought.
	 */
	private void updateInfo(){
		int cost = currentItem.getCost()/(gameEnvironment.getTeam().hasDiscount()?2:1);
		String numOwned = Integer.toString(currentItem.getCount());
		infobox.setText("ITEM STATS\r\n\r\nName:\r\n" + currentItem.getName() +"\r\n\r\nDescription:\r\n" + currentItem.describe() + "\r\n\r\nNumber Owned:\r\n" + numOwned + "\r\n\r\nCost:\r\n" + cost + "g\r");
		
		if (gameEnvironment.getTeam().getMoney() < cost){
			btnBuy.setEnabled(false);
			lblerror.setText("Insufficent funds.");
		}
		else{
			btnBuy.setEnabled(true);
			lblerror.setText("");
		}
		//already own map case:
		if (currentItem.type().equals("Map") && (currentItem.getCount() > 0)){
			enableMapButtons();
			btnBuy.setEnabled(false);
			//lblerror.setText("You can only own one map per city");
		}
		itembought.setText("");
	}

	
	/**
	 * Closes the window
	 */
	public void closeWindow(){
		window.dispose();
	}
	
	/**
	 * Closes the window to and continues on with the game.
	 */
	public void finishWindow(){
		gameEnvironment.closeGUIShop(this);
	}
	

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		window = new JFrame();
		window.setTitle("Heroes vs Villains | Shop");
		window.setResizable(false);
		window.setBounds(100, 100, 750, 460);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.getContentPane().setLayout(null);
		
		JButton btnExitShop = new JButton("Exit Shop");
		btnExitShop.setFont(new Font("Dialog", Font.PLAIN, 12));
		btnExitShop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				finishWindow();
			}
		});
		
		infobox = new JTextPane();
		infobox.setForeground(Color.BLACK);
		infobox.setEditable(false);
		infobox.setBackground(Color.WHITE);
		infobox.setText("ITEM STATS\r\n\r\nName:\r\n\r\n\r\nDescription:\r\n\r\n\r\nNumber Owned:\r\n\r\n\r\nCost:\r\n\r\n");
		infobox.setBounds(385, 26, 305, 295);
		window.getContentPane().add(infobox);
		btnExitShop.setBounds(591, 386, 121, 23);
		window.getContentPane().add(btnExitShop);
		
		
		JLabel lblWelcomeToThe = new JLabel("Welcome to the shop.");
		lblWelcomeToThe.setFont(new Font("Garamond", Font.PLAIN, 19));
		lblWelcomeToThe.setBounds(67, 26, 288, 48);
		window.getContentPane().add(lblWelcomeToThe);
		
		JToggleButton tglbtnHealingItems = new JToggleButton("Healing Items");
		tglbtnHealingItems.setFont(new Font("Dialog", Font.PLAIN, 12));

		tglbtnHealingItems.setBounds(24, 112, 121, 54);
		window.getContentPane().add(tglbtnHealingItems);
		
		JToggleButton tglbtnPowerups = new JToggleButton("Powerups");
		tglbtnPowerups.setFont(new Font("Dialog", Font.PLAIN, 12));
		tglbtnPowerups.setBounds(24, 177, 121, 54);
		window.getContentPane().add(tglbtnPowerups);
		
		JToggleButton tglbtnMaps = new JToggleButton("Maps");
		tglbtnMaps.setFont(new Font("Dialog", Font.PLAIN, 12));

		tglbtnMaps.setBounds(24, 242, 121, 54);
		window.getContentPane().add(tglbtnMaps);
		
		JLabel lblmoney = new JLabel("Your Balance: " + Integer.toString(gameEnvironment.getTeam().getMoney()) +"g");
		lblmoney.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblmoney.setBounds(67, 371, 197, 14);
		window.getContentPane().add(lblmoney);
		
		btnBuy = new JButton("Buy");
		btnBuy.setFont(new Font("Dialog", Font.PLAIN, 12));
		btnBuy.setEnabled(false);
		btnBuy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int itemCost = currentItem.getCost()/(gameEnvironment.getTeam().hasDiscount()?2:1);
				gameEnvironment.getTeam().changeMoney((-1)*itemCost);
				gameEnvironment.getTeam().addToInventory(currentItem);
				lblmoney.setText("Your Balance: " + Integer.toString(gameEnvironment.getTeam().getMoney()) +"g");
				updateInfo();
				itembought.setText("Item purchased: " + currentItem.getName());
			}
		});
		btnBuy.setBounds(552, 332, 89, 23);
		window.getContentPane().add(btnBuy);
		button.setFont(new Font("Dialog", Font.PLAIN, 12));
		
		button.setEnabled(false);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (b0Index >= 0){
					updateItem(b0Index);
				}
			}
		});
		button.setBounds(178, 85, 168, 33);
		window.getContentPane().add(button);
		button_1.setFont(new Font("Dialog", Font.PLAIN, 12));
		
		button_1.setEnabled(false);
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (b1Index >= 0){
					updateItem(b1Index);
				}
			}
		});
		button_1.setBounds(178, 129, 168, 33);
		window.getContentPane().add(button_1);
		button_2.setFont(new Font("Dialog", Font.PLAIN, 12));
		
		button_2.setEnabled(false);
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (b2Index >= 0){
					updateItem(b2Index);
				}
			}
		});
		button_2.setBounds(178, 172, 168, 33);
		window.getContentPane().add(button_2);
		button_3.setFont(new Font("Dialog", Font.PLAIN, 12));
		
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (b3Index >= 0){
					updateItem(b3Index);
				}
				
			}
		});
		button_3.setEnabled(false);
		button_3.setBounds(178, 212, 168, 33);
		window.getContentPane().add(button_3);
		button_4.setFont(new Font("Dialog", Font.PLAIN, 12));
		
		button_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (b4Index >= 0){
					updateItem(b4Index);
				}
			}
		});
		button_4.setEnabled(false);
		button_4.setBounds(178, 250, 168, 33);
		window.getContentPane().add(button_4);
		button_5.setFont(new Font("Dialog", Font.PLAIN, 12));
		
		button_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (b5Index >= 0){
					updateItem(b5Index);
				}
			}
		});
		button_5.setEnabled(false);
		button_5.setBounds(178, 291, 168, 33);
		window.getContentPane().add(button_5);
		
		lblerror = new JLabel("");
		lblerror.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblerror.setHorizontalAlignment(SwingConstants.CENTER);
		lblerror.setBounds(429, 355, 261, 14);
		window.getContentPane().add(lblerror);
		
		
		JLabel lblDiscount = new JLabel("");
		lblDiscount.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblDiscount.setBounds(24, 395, 589, 14);
		window.getContentPane().add(lblDiscount);
		
		itembought = new JLabel("");
		itembought.setFont(new Font("Dialog", Font.PLAIN, 12));
		itembought.setBounds(439, 371, 202, 14);
		window.getContentPane().add(itembought);
				
		tglbtnHealingItems.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tglbtnHealingItems.setSelected(true);
				tglbtnPowerups.setSelected(false);
				tglbtnMaps.setSelected(false);
				b0Index = 0;
				b1Index = 1;
				b2Index = 2;
				b3Index = -1;
				b4Index = -1;
				b5Index = -1;
				button.setText(gameEnvironment.getObtainableItems().get(b0Index).getName());
				button_1.setText(gameEnvironment.getObtainableItems().get(b1Index).getName());
				button_2.setText(gameEnvironment.getObtainableItems().get(b2Index).getName());
				button_3.setText(null);
				button_4.setText(null);
				button_5.setText(null);
				
				button.setEnabled(true);
				button_1.setEnabled(true);
				button_2.setEnabled(true);
				button_3.setEnabled(false);
				button_4.setEnabled(false);
				button_5.setEnabled(false);
				
			}
		});
		tglbtnPowerups.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tglbtnHealingItems.setSelected(false);
				tglbtnPowerups.setSelected(true);
				tglbtnMaps.setSelected(false);
				
				b0Index = 3;
				b1Index = 4;
				b2Index = 5;
				b3Index = 6;
				b4Index = -1;
				b5Index = -1;
				button.setText(gameEnvironment.getObtainableItems().get(b0Index).getName());
				button_1.setText(gameEnvironment.getObtainableItems().get(b1Index).getName());
				button_2.setText(gameEnvironment.getObtainableItems().get(b2Index).getName());
				button_3.setText(gameEnvironment.getObtainableItems().get(b3Index).getName());
				button_4.setText(null);
				button_5.setText(null);
				
				button.setEnabled(true);
				button_1.setEnabled(true);
				button_2.setEnabled(true);
				button_3.setEnabled(true);
				button_4.setEnabled(false);
				button_5.setEnabled(false);
			}
		});
		tglbtnMaps.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tglbtnHealingItems.setSelected(false);
				tglbtnPowerups.setSelected(false);
				tglbtnMaps.setSelected(true);
				
				
				enableMapButtons();
				

			}
		});
		
		if (gameEnvironment.getTeam().hasDiscount()){
			lblDiscount.setText("You qualify for a 50% storewide discount. (Discounted prices shown)");
		}
	}
	
	private void enableMapButtons() {

		b0Index = -1;
		b1Index = -1;
		b2Index = -1;
		b3Index = -1;
		b4Index = -1;
		b5Index = -1;
		button.setText(null);
		button_1.setText(null);
		button_2.setText(null);
		button_3.setText(null);
		button_4.setText(null);
		button_5.setText(null);
		button.setEnabled(false);
		button_1.setEnabled(false);
		button_2.setEnabled(false);
		button_3.setEnabled(false);
		button_4.setEnabled(false);
		button_5.setEnabled(false);
		
		switch(gameEnvironment.getObtainableItems().size() - 1){
		case 12:
			b5Index = 12;
			button_5.setText(gameEnvironment.getObtainableItems().get(b5Index).getName());
			button_5.setEnabled(true);
		case 11:
			b4Index = 11;
			button_4.setText(gameEnvironment.getObtainableItems().get(b4Index).getName());
			button_4.setEnabled(true);
		case 10:
			b3Index = 10;
			button_3.setText(gameEnvironment.getObtainableItems().get(b3Index).getName());
			button_3.setEnabled(true);
		case 9:
			b2Index = 9;
			button_2.setText(gameEnvironment.getObtainableItems().get(b2Index).getName());
			button_2.setEnabled(true);
		case 8:
			b1Index = 8;
			button_1.setText(gameEnvironment.getObtainableItems().get(b1Index).getName());
			button_1.setEnabled(true);
		case 7:
			b0Index = 7;
			button.setText(gameEnvironment.getObtainableItems().get(b0Index).getName());
			button.setEnabled(true);
		}
	}
}
