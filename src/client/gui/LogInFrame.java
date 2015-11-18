package client.gui;

import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import client.conn.Connection;

import javax.swing.JLabel;

import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.Font;
import java.awt.Toolkit;
import javax.swing.JPasswordField;

@SuppressWarnings("serial")
public class LogInFrame extends JFrame {
	
	private JPanel contentPane;
	private JTextField textFieldUser;
	private JPasswordField textFieldPassword;

	public void cerrarVentana() {
		int opc = JOptionPane.showConfirmDialog(null, "�Realmente desea salir?", "Cerrar",
				JOptionPane.OK_CANCEL_OPTION);
		if (opc == JOptionPane.OK_OPTION) {
			System.exit(0);
		}
	}	

	/**
	 * Create the frame.
	 */
	public LogInFrame() { 
		setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				cerrarVentana();
			}
		});
		setTitle("Pacman");
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		setBounds(dim.width / 2 - 200, dim.height / 2 - 225, 400, 450);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		textFieldUser = new JTextField();
		textFieldUser.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textFieldUser.setBounds(139, 111, 180, 20);
		contentPane.add(textFieldUser);
		textFieldUser.setColumns(10);
		textFieldUser.requestFocus();

		JLabel lblUser = new JLabel("Usuario");
		lblUser.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblUser.setBounds(61, 114, 80, 14);
		contentPane.add(lblUser);

		JLabel lblPassword = new JLabel("Contrase\u00F1a");
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblPassword.setBounds(61, 150, 80, 14);
		contentPane.add(lblPassword);

	
		JButton btnLogIn = new JButton("Acceder");
		btnLogIn.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnLogIn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				String user = textFieldUser.getText(); 
				char [] pass= textFieldPassword.getPassword();
								
				Connection.getInstance().send("LOGIN " + user + " " + String.valueOf(pass));
				LogInFrame.this.dispose();
			}
		});
		btnLogIn.setBounds(15, 190, 160, 40);
		contentPane.add(btnLogIn);
		
		JButton btnAsViewer = new JButton("Solo ver");
		btnAsViewer.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnAsViewer.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				String user = textFieldUser.getText(); 
				char [] pass= textFieldPassword.getPassword();
								
				Connection.getInstance().send("LOGIN VIEWER" + user + " " + String.valueOf(pass));
				LogInFrame.this.dispose();
			}
		});
		btnAsViewer.setBounds(205, 190, 160, 40);
		contentPane.add(btnAsViewer);

		JButton btnRegister = new JButton("Registrarse");
		btnRegister.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnRegister.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				LogUpDialog reg = new LogUpDialog();
				reg.setVisible(true);	
			}
		});
		btnRegister.setBounds(105, 280, 180, 40);
		contentPane.add(btnRegister);

		JButton btnInstructions = new JButton("Instrucciones");
		btnInstructions.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnInstructions.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				HowToPlayDialog instr = new HowToPlayDialog();
				instr.setVisible(true);
			}
		});
		btnInstructions.setBounds(105, 350, 180, 40);
		contentPane.add(btnInstructions);
		
		JLabel lblPacman = new JLabel("PACMAN - Multijugador");
		lblPacman.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblPacman.setBounds(90, 11, 214, 40);
		contentPane.add(lblPacman);
		
		textFieldPassword = new JPasswordField();
		textFieldPassword.setBounds(139, 149, 180, 20);
		contentPane.add(textFieldPassword);
		
	}
	
	public void setUser(String user){
		this.textFieldUser.setText(user);
	}
}