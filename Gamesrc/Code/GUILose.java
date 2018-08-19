package Code;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Font;
import java.awt.Color;
import javax.swing.SwingConstants;

/**The GUI for the losing screen of the game 
 */
public class GUILose {

	/** The window for this GUI.
	 */
	private JFrame window;
	/** The gameEnvironment that manages the games variables and events.
	 */
	GameEnvironment gameEnvironment;


	/**Creates the application
	 * @param newGameEnvironment The Environment the Lose GUI is running in
	 */
	public GUILose(GameEnvironment newGameEnvironment) {
		gameEnvironment = newGameEnvironment;
		initialize();
		window.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		window = new JFrame();
		window.setTitle("Heroes vs Villains | Game Over");
		window.setResizable(false);
		window.setBounds(100, 100, 750, 460);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.getContentPane().setLayout(null);
		
		JLabel lblGameOver = new JLabel("Game Over");
		lblGameOver.setHorizontalAlignment(SwingConstants.CENTER);
		lblGameOver.setForeground(Color.WHITE);
		lblGameOver.setFont(new Font("Garamond", Font.PLAIN, 30));
		lblGameOver.setBounds(0, 103, 744, 53);
		window.getContentPane().add(lblGameOver);
		
		JLabel lblTeamname = new JLabel(gameEnvironment.getTeam().getname() + " has been defeated.");
		lblTeamname.setForeground(Color.WHITE);
		lblTeamname.setHorizontalAlignment(SwingConstants.CENTER);
		lblTeamname.setBounds(0, 156, 744, 35);
		window.getContentPane().add(lblTeamname);
		
		JLabel lblCitiesBet = new JLabel("Cities passed: " + gameEnvironment.getCityIndex());
		lblCitiesBet.setHorizontalAlignment(SwingConstants.CENTER);
		lblCitiesBet.setForeground(Color.WHITE);
		lblCitiesBet.setBounds(0, 188, 744, 28);
		window.getContentPane().add(lblCitiesBet);
		
		JLabel background = new JLabel("");
		background.setIcon(new ImageIcon(GUILose.class.getResource("/assets/lose_background.png")));
		background.setBounds(0, 0, 744, 431);
		window.getContentPane().add(background);
	}

}
