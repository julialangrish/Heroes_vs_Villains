package Code;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**The GUI for the VillainsLair area of the game
 */
public class GUIVillainsLair {

	/**The window for this GUI.
	 */
	private JFrame window;
	/**The game environment that manages the games variables and events.
	 */
	private GameEnvironment gameEnvironment;


	/**Creates the application
	 * @param newGameEnvironment The Environment the Villain's Lair is running in
	 */
	public GUIVillainsLair(GameEnvironment newGameEnvironment) {
		gameEnvironment = newGameEnvironment;
		initialize();
		window.setVisible(true);
	}
	
	/**
	 * Closes the window.
	 */
	public void closeWindow(){
		window.dispose();
	}
	
	
	/**
	 * Lets the game environment close the window and decide what to do next.
	 */
	private void finishedWindow(){
		gameEnvironment.leaveVillainsLair(this);
	}
	
	/** 
	 * Closes this window and starts the battle screen with the game environment.
	 */
	public void enterLair(){
		gameEnvironment.goIntoVillainsLair(this);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		window = new JFrame();
		window.getContentPane().setBackground(Color.DARK_GRAY);
		window.setBackground(Color.DARK_GRAY);
		window.setResizable(false);
		window.setTitle("Heroes vs Villains | Villains Lair");
		window.setBounds(100, 100, 750, 460);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.getContentPane().setLayout(null);
		
		JLabel lblYouveReachedThe = new JLabel("You've reached the villains lair");
		lblYouveReachedThe.setForeground(Color.WHITE);
		lblYouveReachedThe.setFont(new Font("Garamond", Font.PLAIN, 20));
		lblYouveReachedThe.setBounds(260, 111, 317, 79);
		window.getContentPane().add(lblYouveReachedThe);
		
		JButton btnLeave = new JButton("Return to Home Base");
		btnLeave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				finishedWindow();
			}
		});
		btnLeave.setBounds(272, 301, 249, 59);
		window.getContentPane().add(btnLeave);
		
		JButton btnEnter = new JButton("Enter");
		btnEnter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				enterLair();
			}
		});
		btnEnter.setBounds(306, 194, 176, 59);
		window.getContentPane().add(btnEnter);
	}
}
