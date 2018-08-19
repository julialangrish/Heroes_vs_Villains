package Code;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import java.awt.Font;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextPane;

/**The GUI for the setup screen
 */
public class GUISetup {

	private JFrame frmHeroesVsVillains;
	private GameEnvironment gameEnvironment;
	private JTextField textField_Teamname;
	private JTextField txtHero1Name;
	private JTextField txtHero2Name;
	private JTextField txtHero3Name;

	/**Creates the application
	 * @param newGameEnvironment The Environment the Setup is running in
	 */
	public GUISetup(GameEnvironment newGameEnvironment) {
		gameEnvironment = newGameEnvironment;
		initialize();
		frmHeroesVsVillains.setVisible(true);
	}
	
	/**Closes the Setup GUI
	 */
	public void closeWindow(){
		frmHeroesVsVillains.dispose();
	}
	

	private void finishedWindow(){
		gameEnvironment.closeGUISetup(this);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmHeroesVsVillains = new JFrame();
		frmHeroesVsVillains.setTitle("Heroes vs Villains | Setup");
		frmHeroesVsVillains.setResizable(false);
		frmHeroesVsVillains.setBounds(100, 100, 750, 450);
		frmHeroesVsVillains.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmHeroesVsVillains.getContentPane().setLayout(null);
		
		JButton btnAccept = new JButton("Accept");
		btnAccept.setFont(new Font("Dialog", Font.PLAIN, 12));
		btnAccept.setBounds(599, 358, 89, 23);
		frmHeroesVsVillains.getContentPane().add(btnAccept);
		

		
		JLabel lblHeroCount = new JLabel("Team name:");
		lblHeroCount.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblHeroCount.setBounds(40, 42, 99, 14);
		frmHeroesVsVillains.getContentPane().add(lblHeroCount);
		
		JLabel lblNumberOfCities = new JLabel("Number of cities:");
		lblNumberOfCities.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblNumberOfCities.setBounds(40, 90, 119, 14);
		frmHeroesVsVillains.getContentPane().add(lblNumberOfCities);
		
		JSpinner spinner_No_OfCities = new JSpinner();
		spinner_No_OfCities.setModel(new SpinnerNumberModel(3, 3, 6, 1));
		spinner_No_OfCities.setBounds(149, 87, 51, 20);
		frmHeroesVsVillains.getContentPane().add(spinner_No_OfCities);
		
		JLabel lblbetweenAnd = new JLabel("Between 3 and 6");
		lblbetweenAnd.setFont(new Font("Tahoma", Font.ITALIC, 11));
		lblbetweenAnd.setBounds(40, 105, 119, 14);
		frmHeroesVsVillains.getContentPane().add(lblbetweenAnd);
		
		textField_Teamname = new JTextField();
		textField_Teamname.setBounds(149, 39, 86, 20);
		frmHeroesVsVillains.getContentPane().add(textField_Teamname);
		textField_Teamname.setColumns(10);
		
		JLabel lblTeamnameErrorMessage = new JLabel("");
		lblTeamnameErrorMessage.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblTeamnameErrorMessage.setBounds(245, 42, 344, 14);
		frmHeroesVsVillains.getContentPane().add(lblTeamnameErrorMessage);
		
		txtHero1Name = new JTextField();
		txtHero1Name.setBounds(582, 68, 108, 20);
		frmHeroesVsVillains.getContentPane().add(txtHero1Name);
		txtHero1Name.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Hero 1 name:");
		lblNewLabel.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblNewLabel.setBounds(444, 74, 106, 14);
		frmHeroesVsVillains.getContentPane().add(lblNewLabel);
		
		JComboBox<String> comboBox_Hero1Type = new JComboBox<String>();
		comboBox_Hero1Type.setFont(new Font("Dialog", Font.PLAIN, 12));
		comboBox_Hero1Type.setModel(new DefaultComboBoxModel<String>(new String[] {"Cheapskate", "Can of flyspray", "Brute", "Tank", "Nurse", "Navigator"}));
		comboBox_Hero1Type.setBounds(582, 114, 123, 20);
		frmHeroesVsVillains.getContentPane().add(comboBox_Hero1Type);
		
		JLabel lblType = new JLabel("Type:");
		lblType.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblType.setBounds(444, 120, 46, 14);
		frmHeroesVsVillains.getContentPane().add(lblType);
		
		JCheckBox chckbxSecondHero = new JCheckBox("Second hero");
		chckbxSecondHero.setFont(new Font("Dialog", Font.PLAIN, 12));
		chckbxSecondHero.setBounds(444, 155, 124, 23);
		frmHeroesVsVillains.getContentPane().add(chckbxSecondHero);
		
		JLabel lblHero2Name = new JLabel("Hero 2 name:");
		lblHero2Name.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblHero2Name.setEnabled(false);
		lblHero2Name.setBounds(444, 185, 108, 14);
		frmHeroesVsVillains.getContentPane().add(lblHero2Name);
		
		JLabel lblHero2Type = new JLabel("Type:");
		lblHero2Type.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblHero2Type.setEnabled(false);
		lblHero2Type.setBounds(444, 229, 46, 14);
		frmHeroesVsVillains.getContentPane().add(lblHero2Type);
		
		txtHero2Name = new JTextField();
		txtHero2Name.setEnabled(false);
		txtHero2Name.setColumns(10);
		txtHero2Name.setBounds(582, 179, 108, 20);
		frmHeroesVsVillains.getContentPane().add(txtHero2Name);
		
		JComboBox<String> comboBox_Hero2Type = new JComboBox<String>();
		comboBox_Hero2Type.setFont(new Font("Dialog", Font.PLAIN, 12));
		comboBox_Hero2Type.setEnabled(false);
		comboBox_Hero2Type.setModel(new DefaultComboBoxModel<String>(new String[] {"Cheapskate", "Can of flyspray", "Brute", "Tank", "Nurse", "Navigator"}));
		comboBox_Hero2Type.setBounds(582, 223, 123, 20);
		frmHeroesVsVillains.getContentPane().add(comboBox_Hero2Type);
		
		JCheckBox chckbxThirdHero = new JCheckBox("Third hero");
		chckbxThirdHero.setFont(new Font("Dialog", Font.PLAIN, 12));
		chckbxThirdHero.setEnabled(false);
		chckbxThirdHero.setBounds(444, 250, 97, 23);
		frmHeroesVsVillains.getContentPane().add(chckbxThirdHero);
		
		JLabel lblHero3Name = new JLabel("Hero 3 name:");
		lblHero3Name.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblHero3Name.setEnabled(false);
		lblHero3Name.setBounds(444, 280, 106, 14);
		frmHeroesVsVillains.getContentPane().add(lblHero3Name);
		
		JLabel lblHero3Type = new JLabel("Type:");
		lblHero3Type.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblHero3Type.setEnabled(false);
		lblHero3Type.setBounds(444, 326, 46, 14);
		frmHeroesVsVillains.getContentPane().add(lblHero3Type);
		
		txtHero3Name = new JTextField();
		txtHero3Name.setEnabled(false);
		txtHero3Name.setColumns(10);
		txtHero3Name.setBounds(582, 274, 108, 20);
		frmHeroesVsVillains.getContentPane().add(txtHero3Name);
		
		JComboBox<String> comboBox_Hero3Type = new JComboBox<String>();
		comboBox_Hero3Type.setFont(new Font("Dialog", Font.PLAIN, 12));
		comboBox_Hero3Type.setEnabled(false);
		comboBox_Hero3Type.setModel(new DefaultComboBoxModel<String>(new String[] {"Cheapskate", "Can of flyspray", "Brute", "Tank", "Nurse", "Navigator"}));
		comboBox_Hero3Type.setBounds(582, 320, 123, 20);
		frmHeroesVsVillains.getContentPane().add(comboBox_Hero3Type);
		
		JLabel lbl_Hero1NameError = new JLabel("");
		lbl_Hero1NameError.setFont(new Font("Dialog", Font.PLAIN, 12));
		lbl_Hero1NameError.setBounds(444, 95, 281, 14);
		frmHeroesVsVillains.getContentPane().add(lbl_Hero1NameError);
		
		JLabel lbl_Hero2NameError = new JLabel("");
		lbl_Hero2NameError.setFont(new Font("Dialog", Font.PLAIN, 12));
		lbl_Hero2NameError.setBounds(444, 204, 281, 14);
		frmHeroesVsVillains.getContentPane().add(lbl_Hero2NameError);
		
		JLabel lbl_Hero3NameError = new JLabel("");
		lbl_Hero3NameError.setFont(new Font("Dialog", Font.PLAIN, 12));
		lbl_Hero3NameError.setBounds(444, 301, 281, 14);
		frmHeroesVsVillains.getContentPane().add(lbl_Hero3NameError);	
		
		JTextPane txtpnDfgh = new JTextPane();
		txtpnDfgh.setText("Hero types:\n\nCheapskate: 50% discount at stores\n\nCan of flyspray: Have better luck during battles\n\nBrute: Deal more damage during battle\n\nTank: Take less damage during battles\n\nNurse: Improved recovery times for the whole team\n\nNavigator: Know locations without a map");
		txtpnDfgh.setBounds(10, 180, 302, 230);
		frmHeroesVsVillains.getContentPane().add(txtpnDfgh);
		
		
		
		chckbxSecondHero.addItemListener(new ItemListener() {	//do second hero checkbox things
		    /**Enables use of a second Hero
		     */
		    public void itemStateChanged(ItemEvent e) {
		    	boolean status = e.getStateChange() == ItemEvent.SELECTED;
		    	
	        	lblHero2Name.setEnabled(status);		//enable second hero boxes if second hero checkbox is activated
	        	txtHero2Name.setEnabled(status);
	        	lblHero2Type.setEnabled(status);
	        	comboBox_Hero2Type.setEnabled(status);
	        	chckbxThirdHero.setEnabled(status);
	        	
	        	status = status && chckbxThirdHero.isSelected();
	        	
	        	lblHero3Name.setEnabled(status);		//enable third hero boxes if second & third hero checkboxes are activated
	        	txtHero3Name.setEnabled(status);
	        	lblHero3Type.setEnabled(status);
	        	comboBox_Hero3Type.setEnabled(status);
		    }
		});
		
		chckbxThirdHero.addItemListener(new ItemListener() {	//do third hero checkbox things
		    /**Enables use of a third Hero
		     */
		    public void itemStateChanged(ItemEvent e) {
		    	boolean status = e.getStateChange() == ItemEvent.SELECTED && chckbxSecondHero.isSelected();
		    	lblHero3Name.setEnabled(status);		//enable third hero boxes if second & third hero checkboxes are activated
	        	txtHero3Name.setEnabled(status);
	        	lblHero3Type.setEnabled(status);
	        	comboBox_Hero3Type.setEnabled(status);
		    }
		});
		
		btnAccept.addActionListener(new ActionListener() {			//accept button is pushed
			public void actionPerformed(ActionEvent e) {
				 boolean status = true;
				 
				 if(textField_Teamname.getText().length() < 2) {	//checks team name is valid
					 lblTeamnameErrorMessage.setText("Team name must be at least 2 characters long!");
					 status = false;
				 } else if(textField_Teamname.getText().length() > 10) {
					 lblTeamnameErrorMessage.setText("Team name must be less than 10 characters long!");
					 status = false;
				 }	else {
					 lblTeamnameErrorMessage.setText("");
				 }
				 
				 if(txtHero1Name.getText().equals("")) {			//checks hero 1 name is valid
					 lbl_Hero1NameError.setText("Name cannot be empty!");
					 status = false;
				 } else if(txtHero1Name.getText().length() < 2) {
					 lbl_Hero1NameError.setText("Name must be at least 2 characters long!");
					 status = false;
				 } else if(txtHero1Name.getText().length() >= 16) {
					 lbl_Hero1NameError.setText("Name must be less than 16 characters long!");
					 status = false;
				 } else {
					 lbl_Hero1NameError.setText("");
				 }
				 
				 
				 
				 if(chckbxSecondHero.isSelected()) {				//checks hero 2 name is valid
					 if(txtHero2Name.getText().equals("")) {
						 lbl_Hero2NameError.setText("Name cannot be empty!");
						 status = false;
					 } else if(txtHero2Name.getText().equals(txtHero1Name.getText())) {
						 lbl_Hero2NameError.setText("Must be a unique name!");
						 status = false;
					 } else if(txtHero2Name.getText().length() < 2) {
						 lbl_Hero2NameError.setText("Name must be at least 2 characters long!");
						 status = false;
					 } else if(txtHero2Name.getText().length() >= 16) {
						 lbl_Hero2NameError.setText("Name must be less than 16 characters long!");
						 status = false;
					 } else {
						 lbl_Hero2NameError.setText("");
					 }
				 }	else {
					 lbl_Hero2NameError.setText("");
				 }
				 
				 
				 if(chckbxThirdHero.isSelected() && chckbxSecondHero.isSelected()) {	//checks hero 3 name is valid
					 if(txtHero3Name.getText().equals("")) {
						 lbl_Hero3NameError.setText("Name cannot be empty!");
						 status = false;
					 } else if(txtHero3Name.getText().equals(txtHero1Name.getText()) || txtHero3Name.getText().equals(txtHero2Name.getText())) {
						 lbl_Hero3NameError.setText("Must be a unique name!");
						 status = false;
					 } else if(txtHero3Name.getText().length() < 2) {
						 lbl_Hero3NameError.setText("Name must be at least 2 characters long!");
						 status = false;
					 } else if(txtHero3Name.getText().length() >= 16) {
						 lbl_Hero3NameError.setText("Name must be less than 16 characters long!");
						 status = false;
					 } else {
						 lbl_Hero3NameError.setText("");
					 }
				 } else {
					 lbl_Hero3NameError.setText("");
				 }
				
				//add values to gameenviro
				//asuming all is well:
				if(status) {
					Object gameSize = spinner_No_OfCities.getValue();
					gameEnvironment.setup((Integer)gameSize, textField_Teamname.getText());
					gameEnvironment.getTeam().addHero(new Hero(txtHero1Name.getText(), comboBox_Hero1Type.getSelectedIndex() + 1));
					
					if (chckbxSecondHero.isSelected()) {
						gameEnvironment.getTeam().addHero(new Hero(txtHero2Name.getText(), comboBox_Hero2Type.getSelectedIndex() + 1));
						if (chckbxThirdHero.isSelected()) {
							gameEnvironment.getTeam().addHero(new Hero(txtHero3Name.getText(), comboBox_Hero3Type.getSelectedIndex() + 1));
						}
					}
					
					gameEnvironment.getTeam().applyBonuses();
					
					finishedWindow();
				}
			}
		});
		
	}
}
