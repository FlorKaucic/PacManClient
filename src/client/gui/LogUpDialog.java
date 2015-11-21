package client.gui;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import client.config.Config;
import client.conn.Connection;
import client.logic.Validator;

import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.JLabel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JPasswordField;

@SuppressWarnings("serial")
public class LogUpDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textFieldUser;
	private JPasswordField textFieldPassword;
	private JPasswordField textFieldConfirm;

	public void cerrarVentana() {
		int opc = JOptionPane.showConfirmDialog(null, "¿Realmente desea salir?", "Cerrar",
				JOptionPane.OK_CANCEL_OPTION);
		if (opc == JOptionPane.OK_OPTION) {
			LogUpDialog.this.dispose();
		}
	}

	/**
	 * Create the dialog.
	 */
	public LogUpDialog() {
		int x = Integer.parseInt(Config.get("screen_width"))/2-225;
		int y = Integer.parseInt(Config.get("screen_height"))/2-150;
		setBounds(x, y, 450, 300);
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

		textFieldPassword = new JPasswordField();
		textFieldPassword.setBounds(161, 66, 247, 20);
		contentPanel.add(textFieldPassword);

		textFieldConfirm = new JPasswordField();
		textFieldConfirm.setBounds(161, 97, 247, 20);
		contentPanel.add(textFieldConfirm);

		JButton btnVaciar = new JButton("Vaciar");
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
				String user = textFieldUser.getText();
				char[] pass = textFieldPassword.getPassword();
				char[] passConfirm = textFieldConfirm.getPassword();
				try {
					Validator.isUserValid(user, pass, passConfirm);

					Connection.getInstance().send("LOGUP " + user + " " + String.valueOf(pass));
					
					LogUpDialog.this.dispose();
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnEnviar.setBounds(27, 168, 120, 30);
		contentPanel.add(btnEnviar);
	}
}
