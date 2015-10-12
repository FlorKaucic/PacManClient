package client.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

import java.awt.Color;

import javax.swing.SwingConstants;
import javax.swing.JTextField;

import java.awt.GridLayout;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class MainMenu extends JFrame {
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
					MainMenu frame = new MainMenu();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public void cerrarVentana(){
		int opc = JOptionPane.showConfirmDialog(null, "¿Realmente desea salir?", "Cerrar", JOptionPane.OK_CANCEL_OPTION);
		if(opc == JOptionPane.OK_OPTION){
			System.exit(0);
			}
	}
	/**
	 * Create the frame.
	 */
	public MainMenu() {
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				cerrarVentana();	
			}
		});
		setTitle("PacoMan - Menu Principal");
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textFieldUser = new JTextField();
		textFieldUser.setBounds(128, 86, 122, 20);
		contentPane.add(textFieldUser);
		textFieldUser.setColumns(10);
		textFieldUser.requestFocus();
		
		textFieldPassword = new JTextField();
		textFieldPassword.setBounds(128, 122, 122, 20);
		contentPane.add(textFieldPassword);
		textFieldPassword.setColumns(10);
		
		JLabel lblLogIn = new JLabel("Ingreso:");
		lblLogIn.setBounds(41, 35, 46, 14);
		contentPane.add(lblLogIn);
		
		JLabel lblUser = new JLabel("Usuario");
		lblUser.setBounds(41, 89, 77, 14);
		contentPane.add(lblUser);
		
		JLabel lblPassword = new JLabel("Contrase\u00F1a");
		lblPassword.setBounds(41, 125, 77, 14);
		contentPane.add(lblPassword);
		
		JButton btnLogIn = new JButton("Acceder");
		btnLogIn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
					textFieldUser.getText(); //aca lo que sea que quieran hacer con esos 2 fields.
					textFieldPassword.getText();
			}
		});
		btnLogIn.setBounds(140, 153, 89, 23);
		contentPane.add(btnLogIn);
		
		JButton btnRegister = new JButton("Registrarse");
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Register reg = new Register();
				reg.setVisible(true);
				textFieldUser.requestFocus();
			}
		});
		btnRegister.setBounds(275, 85, 128, 23);
		contentPane.add(btnRegister);
		
		JButton btnInstructions = new JButton("Instrucciones");
		btnInstructions.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GameInstructions instr = new GameInstructions();
				instr.setVisible(true);
				textFieldUser.requestFocus();
			}
		});
		btnInstructions.setBounds(275, 121, 128, 23);
		contentPane.add(btnInstructions);
	}
}
