package client.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

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

public class Register extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textFieldNickname;
	private JTextField textFieldUser;
	private JTextField textFieldPassword;
	private JTextField textFieldConfirm;
	private JTextField txtEstadoSolicitud;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			Register dialog = new Register();
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void cerrarVentana(){
		int opc = JOptionPane.showConfirmDialog(null, "¿Realmente desea salir?", "Cerrar", JOptionPane.OK_CANCEL_OPTION);
		if(opc == JOptionPane.OK_OPTION){
			Register.this.dispose();
			}
    }
	/**
	 * Create the dialog.
	 */
	public Register() {
		setBounds(100, 100, 450, 300);
		this.setTitle("PacoMan - Solicitud de Registro");
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
		
		textFieldNickname = new JTextField();
		textFieldNickname.setBounds(316, 32, 86, 20);
		contentPanel.add(textFieldNickname);
		textFieldNickname.setColumns(10);
		
		textFieldUser = new JTextField();
		textFieldUser.setBounds(316, 63, 86, 20);
		contentPanel.add(textFieldUser);
		textFieldUser.setColumns(10);
		
		textFieldPassword = new JTextField();
		textFieldPassword.setBounds(316, 94, 86, 20);
		contentPanel.add(textFieldPassword);
		textFieldPassword.setColumns(10);
		
		textFieldConfirm = new JTextField();
		textFieldConfirm.setBounds(316, 125, 86, 20);
		contentPanel.add(textFieldConfirm);
		textFieldConfirm.setColumns(10);
		
		txtEstadoSolicitud = new JTextField();
		txtEstadoSolicitud.setText(". . .");
		txtEstadoSolicitud.setEditable(false);
		txtEstadoSolicitud.setBounds(100, 235, 302, 20);
		contentPanel.add(txtEstadoSolicitud);
		txtEstadoSolicitud.setColumns(10);
		
		JLabel lblEstado = new JLabel("Estado");
		lblEstado.setBounds(21, 238, 46, 14);
		contentPanel.add(lblEstado);
		
		JLabel lblNombreDeUsuario = new JLabel("Nombre de usuario:");
		lblNombreDeUsuario.setBounds(21, 38, 154, 14);
		contentPanel.add(lblNombreDeUsuario);
		
		JLabel lblUsuario = new JLabel("Usuario");
		lblUsuario.setBounds(21, 69, 154, 14);
		contentPanel.add(lblUsuario);
		
		JLabel lblClave = new JLabel("Clave");
		lblClave.setBounds(21, 100, 154, 14);
		contentPanel.add(lblClave);
		
		JLabel lblConfirmacionDeClave = new JLabel("Confirmacion de Clave");
		lblConfirmacionDeClave.setBounds(21, 131, 154, 14);
		contentPanel.add(lblConfirmacionDeClave);
		
		JButton btnVaciar = new JButton("Vaciar solicitud");
		btnVaciar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textFieldNickname.setText("");
				textFieldUser.setText("");
				textFieldPassword.setText("");
				textFieldConfirm.setText("");
				textFieldNickname.requestFocus();
			}
		});
		btnVaciar.setBounds(153, 181, 103, 23);
		contentPanel.add(btnVaciar);
		
		JButton btnEnviar = new JButton("Enviar");
		btnEnviar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnEnviar.setBounds(38, 181, 89, 23);
		contentPanel.add(btnEnviar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cerrarVentana();
			}
		});
		btnCancelar.setBounds(287, 181, 89, 23);
		contentPanel.add(btnCancelar);
	}
}
