package Code;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;

/**The Intro Screen for the game
 */
public class GUIIntroScreen {


	private JFrame window;
	private GameEnvironment gameEnvironment;


	/**Creates the application
	 * @param newGameEnvironment The environment the IntroScreen is running in
	 */
	public GUIIntroScreen(GameEnvironment newGameEnvironment) {
		gameEnvironment = newGameEnvironment;
		initialize();
		window.setVisible(true);
	}
	
	/**
	 * Closes the window
	 */
	public void closeWindow(){
		window.dispose();
	}
	
	/**
	 * Closes the window and lets the game environment continue the game.
	 */
	private void finishWindow(){
		gameEnvironment.closeGUIIntroScreen(this);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		window = new JFrame();
		window.setTitle("Heroes vs Villains");
		window.setResizable(false);
		window.setBounds(100, 100, 750, 450);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.getContentPane().setLayout(null);
		
		
		JLabel lblHeroesVsVillains = new JLabel("Heroes vs Villains");
		lblHeroesVsVillains.setBounds(0, 125, 744, 155);
		lblHeroesVsVillains.setHorizontalAlignment(SwingConstants.CENTER);
		lblHeroesVsVillains.setFont(new Font("Elephant", Font.PLAIN, 24));
		window.getContentPane().add(lblHeroesVsVillains);
		
		JButton creditsButton = new JButton("Credits");
		creditsButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(window, gameEnvironment.getCredits());
			}
		});
		creditsButton.setBounds(97, 335, 166, 46);
		window.getContentPane().add(creditsButton);
		
		JButton startButton = new JButton("Start");
		startButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				finishWindow();	
			}
		});
		startButton.setBounds(273, 335, 166, 46);
		window.getContentPane().add(startButton);
		
		JButton exitButton = new JButton("Quit");
		exitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		exitButton.setBounds(449, 335, 166, 46);
		window.getContentPane().add(exitButton);
		
		JLabel backgound = new JLabel("");
		backgound.setIcon(new ImageIcon(GUIIntroScreen.class.getResource("/assets/IntroScreenBackground.png")));
		backgound.setBounds(0, 0, 744, 421);
		window.getContentPane().add(backgound);
	}

}
