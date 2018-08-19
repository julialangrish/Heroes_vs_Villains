package Code;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Font;
import java.awt.Color;
import javax.swing.SwingConstants;

/**The GUI for the winning screen of the game
 */
public class GUIWin {

	/** The game environment that manages the games properties and events.
	 */
	GameEnvironment gameEnvironment;
	/**The window for the GUI.
	 */
	private JFrame window;

	/**Creates the application
	 * @param newGameEnvironment The Environment the Win GUI is running in
	 */
	public GUIWin(GameEnvironment newGameEnvironment) {
		gameEnvironment = newGameEnvironment;
		initialize();
		window.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		window = new JFrame();
		window.setTitle("Heroes vs Villains | Game Complete");
		window.setResizable(false);
		window.setBounds(100, 100, 750, 460);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.getContentPane().setLayout(null);
		
		JLabel lblWintext = new JLabel(gameEnvironment.getTeam().getname() + " has protected the world from evil!");
		lblWintext.setHorizontalAlignment(SwingConstants.CENTER);
		lblWintext.setForeground(Color.WHITE);
		lblWintext.setFont(new Font("Garamond", Font.PLAIN, 30));
		lblWintext.setBounds(0, 94, 734, 70);
		window.getContentPane().add(lblWintext);
		
		JLabel lblTime = new JLabel("Your time: " + gameEnvironment.getTime() + " seconds");
		lblTime.setHorizontalAlignment(SwingConstants.CENTER);
		lblTime.setForeground(Color.WHITE);
		lblTime.setBounds(10, 163, 714, 28);
		window.getContentPane().add(lblTime);
		
		JLabel background = new JLabel("");
		background.setIcon(new ImageIcon(GUIWin.class.getResource("/assets/win_background.png")));
		background.setBounds(0, 0, 744, 431);
		window.getContentPane().add(background);
	}

}
