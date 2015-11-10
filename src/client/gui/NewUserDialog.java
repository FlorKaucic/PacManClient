package client.gui;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import client.conn.Connection;
import client.logic.Validator;
import game.gui.GameFrame;

import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.JLabel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import javax.swing.JPasswordField;
import java.awt.Color;
import java.awt.Font;

@SuppressWarnings("serial")
public class NewUserDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textFieldUser;
	private JPasswordField textFieldPassword;
	private JPasswordField textFieldConfirm;

	/**
	 * Launch the application.
	 */
	public void main(String[] args) {
		try {
			NewUserDialog dialog = new NewUserDialog();
			dialog.setVisible(true);

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Cliente", JOptionPane.ERROR_MESSAGE);
		}

	}

	public void cerrarVentana() {
		int opc = JOptionPane.showConfirmDialog(null, "¿Realmente desea salir?", "Cerrar",
				JOptionPane.OK_CANCEL_OPTION);
		if (opc == JOptionPane.OK_OPTION) {
			NewUserDialog.this.dispose();
		}
	}

	/**
	 * Create the dialog.
	 */
	public NewUserDialog() {
		setBounds(100, 100, 450, 300);
		this.setTitle("Pacman - Solicitud de Registro");
		this.setModal(true);
		setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				cerrarVentana();

			}
		});

		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		textFieldUser = new JTextField();
		textFieldUser.setBounds(161, 35, 247, 20);
		contentPanel.add(textFieldUser);
		textFieldUser.setColumns(10);

		JLabel lblUsuario = new JLabel("Usuario:");
		lblUsuario.setBounds(27, 41, 115, 14);
		contentPanel.add(lblUsuario);

		JLabel lblClave = new JLabel("Clave:");
		lblClave.setBounds(27, 72, 115, 14);
		contentPanel.add(lblClave);

		JLabel lblConfirmacionDeClave = new JLabel("Confirmacion de Clave:");
		lblConfirmacionDeClave.setBounds(27, 103, 115, 14);
		contentPanel.add(lblConfirmacionDeClave);

		JLabel lblMsg = new JLabel("");
		lblMsg.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblMsg.setBounds(161, 131, 247, 14);
		contentPanel.add(lblMsg);

		textFieldPassword = new JPasswordField();
		textFieldPassword.setBounds(161, 66, 247, 20);
		contentPanel.add(textFieldPassword);

		textFieldConfirm = new JPasswordField();
		textFieldConfirm.setBounds(161, 97, 247, 20);
		contentPanel.add(textFieldConfirm);

		JButton btnVaciar = new JButton("Vaciar solicitud");
		btnVaciar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				textFieldUser.setText("");
				textFieldPassword.setText("");
				textFieldConfirm.setText("");
				textFieldUser.requestFocus();
			}
		});
		btnVaciar.setBounds(297, 168, 120, 30);
		contentPanel.add(btnVaciar);

		JButton btnEnviar = new JButton("Enviar");
		btnEnviar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//				int f = 1;
				String user = textFieldUser.getText();
				char[] pass = textFieldPassword.getPassword();
				char[] passConfirm = textFieldConfirm.getPassword();
				//				String msg = "";
				try {
					Validator.isUserValid(user, pass, passConfirm);

					lblMsg.setForeground(Color.blue);
					lblMsg.setText("Solicitud enviada");

					Connection.getInstance().send("LOGUP " + user + " " + String.valueOf(pass));

					BufferedReader in = Connection.getInstance().getBufferedReader();

					try {
						String inputLine;
						while ((inputLine = in.readLine()) != null) {
							if (inputLine.startsWith("LOGUPOK")) {
								System.out.println("ok "+inputLine);
								JOptionPane.showMessageDialog(null, "Usuario registrado", "Registro de usuario",
										JOptionPane.INFORMATION_MESSAGE);
//								MainFrame mf = (MainFrame) NewUserDialog.this.getOwner().;
//								mf.setUser(user);
								System.out.println(NewUserDialog.this.getOwner().getClass());
								System.out.println(NewUserDialog.this.getParent().getClass());
								System.out.println(NewUserDialog.this.getOwner().getOwner().getClass());
								NewUserDialog.this.dispose();
								break;
							}
							if (inputLine.startsWith("LOGUPFAILED")) {
								System.out.println("failed "+inputLine);
								JOptionPane.showMessageDialog(null, "No se puede registrar", "Registro de usuario",
										JOptionPane.ERROR_MESSAGE);
								break;
							}
						}
						in.close();
					} catch (Exception ex) {
						JOptionPane.showMessageDialog(null, "Fallo al recibir del servidor.", "Cliente",
								JOptionPane.ERROR_MESSAGE);
						ex.printStackTrace();
					}
				} catch (Exception ex) {
					lblMsg.setForeground(Color.red);
					lblMsg.setText(ex.getMessage());
				}
			}
		});
		btnEnviar.setBounds(27, 168, 120, 30);
		contentPanel.add(btnEnviar);
	}
}
