package client.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;

public class JoinGameUI extends JFrame {

	private JPanel contentPane;
	private JTextField nickname;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JoinGameUI frame = new JoinGameUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public JoinGameUI() {
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setTitle("Pacman");
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		setBounds(dim.width / 2 - 200, dim.height / 2 - 225, 400, 450);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		JLabel lblNickname = new JLabel("Nickname:");
		lblNickname.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNickname.setBounds(10, 11, 80, 14);
		contentPane.add(lblNickname);
		
		nickname = new JTextField();
		nickname.setBounds(100, 10, 274, 20);
		contentPane.add(nickname);
		nickname.setColumns(30);
		
		JButton button = new JButton("Unirse a partida");
		button.setFont(new Font("Tahoma", Font.PLAIN, 14));
		button.setBounds(107, 90, 180, 40);
		contentPane.add(button);
	}
}
