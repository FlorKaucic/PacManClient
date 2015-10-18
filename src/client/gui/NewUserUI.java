package client.gui;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

@SuppressWarnings("serial")
public class NewUserUI extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textFieldUser;
	private JTextField textFieldPassword;
	private JTextField textFieldConfirm;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			NewUserUI dialog = new NewUserUI();
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void cerrarVentana(){
		int opc = JOptionPane.showConfirmDialog(null, "¿Realmente desea salir?", "Cerrar", JOptionPane.OK_CANCEL_OPTION);
		if(opc == JOptionPane.OK_OPTION){
			NewUserUI.this.dispose();
			}
    }
	/**
	 * Create the dialog.
	 */
	public NewUserUI() {
		setBounds(100, 100, 450, 300);
		this.setTitle("Pacman - Solicitud de Registro");
		this.setModal(true);
		setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
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
		
		textFieldPassword = new JTextField();
		textFieldPassword.setBounds(161, 66, 247, 20);
		contentPanel.add(textFieldPassword);
		textFieldPassword.setColumns(10);
		
		textFieldConfirm = new JTextField();
		textFieldConfirm.setBounds(161, 97, 247, 20);
		contentPanel.add(textFieldConfirm);
		textFieldConfirm.setColumns(10);
		
		JLabel lblUsuario = new JLabel("Usuario:");
		lblUsuario.setBounds(27, 41, 115, 14);
		contentPanel.add(lblUsuario);
		
		JLabel lblClave = new JLabel("Clave:");
		lblClave.setBounds(27, 72, 115, 14);
		contentPanel.add(lblClave);
		
		JLabel lblConfirmacionDeClave = new JLabel("Confirmacion de Clave:");
		lblConfirmacionDeClave.setBounds(27, 103, 115, 14);
		contentPanel.add(lblConfirmacionDeClave);
		
		JButton btnVaciar = new JButton("Vaciar solicitud");
		btnVaciar.addActionListener(new ActionListener() {
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
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnEnviar.setBounds(154, 137, 120, 30);
		contentPanel.add(btnEnviar);
		
//		JButton btnCancelar = new JButton("Cancelar");
//		btnCancelar.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				cerrarVentana();
//			}
//		});
//		btnCancelar.setBounds(287, 181, 120, 30);
//		contentPanel.add(btnCancelar);
	}
}
