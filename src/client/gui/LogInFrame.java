package client.gui;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import client.config.Config;
import client.conn.Connection;
import client.logic.Parser;

import javax.swing.JLabel;

import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.imageio.ImageIO;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JPasswordField;
import java.awt.Color;
import javax.swing.SwingConstants;

@SuppressWarnings("serial")
public class LogInFrame extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldUser;
	private JPasswordField textFieldPassword;
	private Image imgPanel;

	public void cerrarVentana() {
		int opc = JOptionPane.showConfirmDialog(null, "¿Realmente desea salir?", "Cerrar",
				JOptionPane.OK_CANCEL_OPTION);
		if (opc == JOptionPane.OK_OPTION) {
			System.exit(0);
		}
	}

	/**
	 * Create the frame.
	 */
	public LogInFrame() {
		setResizable(false);
		setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				cerrarVentana();
			}
		});
		setTitle("Pacman");
		int x = Integer.parseInt(Config.get("screen_width")) / 2 - 200;
		int y = Integer.parseInt(Config.get("screen_height")) / 2 - 225;
		setBounds(x, y, 400, 570);
		contentPane = new JPanel();
		contentPane.setBackground(Color.BLACK);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		textFieldUser = new JTextField();
		textFieldUser.setFont(new Font("Kristen ITC", Font.PLAIN, 14));
		textFieldUser.setBounds(139, 111, 180, 20);
		contentPane.add(textFieldUser);
		textFieldUser.setColumns(10);
		textFieldUser.requestFocus();

		JLabel lblUser = new JLabel("Usuario:");
		lblUser.setForeground(Color.WHITE);
		lblUser.setFont(new Font("Kristen ITC", Font.PLAIN, 14));
		lblUser.setBounds(46, 114, 95, 14);
		contentPane.add(lblUser);

		JLabel lblPassword = new JLabel("Contrase\u00F1a:");
		lblPassword.setForeground(Color.WHITE);
		lblPassword.setFont(new Font("Kristen ITC", Font.PLAIN, 14));
		lblPassword.setBounds(46, 150, 95, 14);
		contentPane.add(lblPassword);

		JButton btnLogIn = new JButton("Acceder");
		btnLogIn.setFont(new Font("Kristen ITC", Font.PLAIN, 14));
		btnLogIn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				String user = textFieldUser.getText();
				char[] pass = textFieldPassword.getPassword();

				Connection.getInstance().send("LOGIN " + user + " " + String.valueOf(pass));
				LogInFrame.this.dispose();
			}
		});
		btnLogIn.setBounds(15, 190, 160, 40);
		contentPane.add(btnLogIn);

		JButton btnAsViewer = new JButton("Solo ver");
		btnAsViewer.setFont(new Font("Kristen ITC", Font.PLAIN, 14));
		btnAsViewer.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				String user = textFieldUser.getText();
				char[] pass = textFieldPassword.getPassword();

				Connection.getInstance().send("LOGIN VIEWER" + user + " " + String.valueOf(pass));
				LogInFrame.this.dispose();
			}
		});
		btnAsViewer.setBounds(205, 190, 160, 40);
		contentPane.add(btnAsViewer);

		JButton btnRegister = new JButton("Registrarse");
		btnRegister.setFont(new Font("Kristen ITC", Font.PLAIN, 14));
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
		btnInstructions.setFont(new Font("Kristen ITC", Font.PLAIN, 14));
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
		lblPacman.setHorizontalAlignment(SwingConstants.CENTER);
		lblPacman.setForeground(Color.WHITE);
		lblPacman.setFont(new Font("Kristen ITC", Font.PLAIN, 20));
		lblPacman.setBounds(15, 11, 350, 40);
		contentPane.add(lblPacman);

		textFieldPassword = new JPasswordField();
		textFieldPassword.setBounds(139, 149, 180, 20);
		contentPane.add(textFieldPassword);
		
		try {
			imgPanel = ImageIO.read(new File(Config.get("img_path")+"gamepanel.jpg"));
		} catch (IOException e1) {
			JLabel error = new JLabel("No se pudo cargar la imagen.");
			error.setBounds(90, 150, 200, 50);
			error.setForeground(Color.WHITE);
			error.setFont(new Font("Kristen ITC", Font.BOLD, 14));
			error.setVisible(true);
			contentPane.add(error);
		}

	}

	public void setUser(String user) {
		this.textFieldUser.setText(user);
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		if(imgPanel != null)
			g.drawImage(this.imgPanel, 20, 450, 380, 533, 0, 0, 360, 83, null);
	}
	
}
