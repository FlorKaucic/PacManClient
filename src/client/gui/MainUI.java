package client.gui;

import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import client.conn.Connection;

import javax.swing.JLabel;

import javax.swing.JTextField;

import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.util.Properties;
import java.awt.Font;
import java.awt.Toolkit;

@SuppressWarnings("serial")
public class MainUI extends JFrame {
	private JPanel contentPane;
	private JTextField textFieldUser;
	private JTextField textFieldPassword;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainUI frame = new MainUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public void cerrarVentana() {
		int opc = JOptionPane.showConfirmDialog(null, "¿Realmente desea salir?", "Cerrar",
				JOptionPane.OK_CANCEL_OPTION);
		if (opc == JOptionPane.OK_OPTION) {
			System.exit(0);
		}
	}
	
//	public static void acceso(int i){ //hay que cambiar las entradas por lo que sea que necesite
//		//accede y cierra la ventana actual
//		JOptionPane.showMessageDialog(null, "Se accede al juego", "", JOptionPane.INFORMATION_MESSAGE);
//	}
	

	/**
	 * Create the frame.
	 */
	public MainUI() {
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
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

		textFieldPassword = new JTextField();
		textFieldPassword.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textFieldPassword.setBounds(139, 147, 180, 20);
		contentPane.add(textFieldPassword);
		textFieldPassword.setColumns(10);

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
			public void actionPerformed(ActionEvent arg0) {
				textFieldUser.getText(); 
				textFieldPassword.getText();
//				acceso(1);
				Connection conn = Connection.getInstance();

			}
		});
		btnLogIn.setBounds(105, 190, 180, 40);
		contentPane.add(btnLogIn);

		JButton btnRegister = new JButton("Registrarse");
		btnRegister.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				NewUserUI reg = new NewUserUI();
				reg.setVisible(true);	
			}
		});
		btnRegister.setBounds(105, 280, 180, 40);
		contentPane.add(btnRegister);

		JButton btnInstructions = new JButton("Instrucciones");
		btnInstructions.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnInstructions.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				InstructionsUI instr = new InstructionsUI();
				instr.setVisible(true);
			}
		});
		btnInstructions.setBounds(105, 350, 180, 40);
		contentPane.add(btnInstructions);
		
		JLabel lblPacman = new JLabel("PACMAN - Multijugador");
		lblPacman.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblPacman.setBounds(90, 11, 214, 40);
		contentPane.add(lblPacman);
		
	}
}
