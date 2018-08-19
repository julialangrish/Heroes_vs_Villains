package Code;

import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JTextPane;
import javax.swing.JRadioButton;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;

/**A GUI for selecting a Hero to fight the Villain
 * @author Julia Langrish
 */
public class GUIBattleMenu {

	private GameEnvironment gameEnvironment;
	private Villain villain;
	private Hero selectedHero;
	private JFrame window;
	private JTextPane txtTaunt;


	/**Create the application
	 * @param newGameEnvironment The environment the BattleMenu is running in
	 */
	public GUIBattleMenu(GameEnvironment newGameEnvironment) {
		gameEnvironment = newGameEnvironment;
		selectedHero = gameEnvironment.getTeam().getHeroes().get(0);
		villain = gameEnvironment.getCurrentMap().getVillain();
		initialize();
		window.setVisible(true);
	}
	
	
	/**Closes the window
	 */
	public void closeWindow(){
		window.dispose();
	}
	

	private void finishWindow(){
		gameEnvironment.closeGUIBattleMenu(this, selectedHero);
	}

	/** Initialize the contents of the frame.
	 */
	private void initialize() {
		window = new JFrame();
		window.getContentPane().setBackground(Color.DARK_GRAY);
		window.setTitle("Heroes vs Villains | Battle Select");
		window.setResizable(false);
		window.setBounds(100, 100, 750, 460);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.getContentPane().setLayout(null);
		
		JLabel triangle = new JLabel("");
		triangle.setIcon(new ImageIcon(GUIBattleMenu.class.getResource("/assets/speech_triangle.png")));
		triangle.setBounds(400, 56, 82, 93);
		window.getContentPane().add(triangle);
		
		JTextPane txtpnHeroDesc = new JTextPane();
		txtpnHeroDesc.setText(selectedHero.getHeroInfo());
		txtpnHeroDesc.setBounds(22, 137, 191, 267);
		window.getContentPane().add(txtpnHeroDesc);
		
		JRadioButton btnhero1 = new JRadioButton("Hero 1");
		btnhero1.setFont(new Font("Dialog", Font.PLAIN, 12));
		btnhero1.setSelected(true);
		btnhero1.setBackground(Color.DARK_GRAY);
		btnhero1.setForeground(Color.WHITE);
		btnhero1.setBounds(22, 55, 109, 23);
		window.getContentPane().add(btnhero1);
		
		JRadioButton btnhero2 = new JRadioButton("Hero 2");
		btnhero2.setFont(new Font("Dialog", Font.PLAIN, 12));
		btnhero2.setVisible(gameEnvironment.getTeam().getHeroes().size() > 1);
		btnhero2.setForeground(Color.WHITE);
		btnhero2.setBackground(Color.DARK_GRAY);
		btnhero2.setBounds(22, 81, 109, 23);
		window.getContentPane().add(btnhero2);
		
		JRadioButton btnhero3 = new JRadioButton("Hero 3");
		btnhero3.setFont(new Font("Dialog", Font.PLAIN, 12));
		btnhero3.setVisible(gameEnvironment.getTeam().getHeroes().size() > 2);
		btnhero3.setBackground(Color.DARK_GRAY);
		btnhero3.setForeground(Color.WHITE);
		btnhero3.setBounds(22, 107, 109, 23);
		window.getContentPane().add(btnhero3);
		
		JLabel lblBattleMenu = new JLabel("Battle Menu");
		lblBattleMenu.setFont(new Font("Impact", Font.PLAIN, 25));
		lblBattleMenu.setForeground(Color.WHITE);
		lblBattleMenu.setBounds(29, 11, 204, 37);
		window.getContentPane().add(lblBattleMenu);

		String status = "villain";
		if (villain.isSuperVillain()){
			status = "supervillain";
		}
		
		JLabel lblname = new JLabel("You meet the " + status + ", " + villain.getName());
		lblname.setHorizontalAlignment(SwingConstants.CENTER);
		lblname.setForeground(Color.WHITE);
		lblname.setBounds(256, 284, 478, 14);
		window.getContentPane().add(lblname);
		
		JLabel lblHp = new JLabel("HP: " + villain.getHealth() + "/" + villain.getMaxHealth());
		lblHp.setForeground(Color.WHITE);
		lblHp.setBounds(473, 309, 114, 14);
		window.getContentPane().add(lblHp);
		
		JButton btnFight = new JButton("Fight");
		btnFight.setEnabled(selectedHero.getAlive());
		btnFight.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				finishWindow();
			}
		});
		btnFight.setBounds(473, 357, 89, 23);
		window.getContentPane().add(btnFight);
		
		JLabel lblImage = new JLabel("");
		lblImage.setBounds(473, 23, 204, 244);
		window.getContentPane().add(lblImage);
		
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
		
		txtTaunt = new JTextPane();
		txtTaunt.setEditable(false);
		txtTaunt.setText(villain.getMainTaunt());
		txtTaunt.setBounds(271, 55, 132, 109);
		window.getContentPane().add(txtTaunt);
		
		
		btnhero1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				btnhero1.setSelected(true);
				btnhero2.setSelected(false);
				btnhero3.setSelected(false);
				selectedHero = gameEnvironment.getTeam().getHeroes().get(0);
				txtpnHeroDesc.setText(selectedHero.getHeroInfo());
				btnFight.setEnabled(selectedHero.getAlive());
				
			}
		});
		
		btnhero2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnhero1.setSelected(false);
				btnhero2.setSelected(true);
				btnhero3.setSelected(false);
				selectedHero = gameEnvironment.getTeam().getHeroes().get(1);
				txtpnHeroDesc.setText(selectedHero.getHeroInfo());
				btnFight.setEnabled(selectedHero.getAlive());
			}
		});
		
		btnhero3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnhero1.setSelected(false);
				btnhero2.setSelected(false);
				btnhero3.setSelected(true);
				selectedHero = gameEnvironment.getTeam().getHeroes().get(2);
				txtpnHeroDesc.setText(selectedHero.getHeroInfo());
				btnFight.setEnabled(selectedHero.getAlive());
			}
		});

		
	}
}
