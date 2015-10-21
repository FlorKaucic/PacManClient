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
import java.util.Arrays;

import javax.swing.JPasswordField;
import java.awt.Color;
import java.awt.Font;

@SuppressWarnings("serial")
public class NewUserUI extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textFieldUser;
	private JPasswordField textFieldPassword;
	private JPasswordField textFieldConfirm;
	/**
	 * Launch the application.
	 */
	public void main(String[] args) {
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
				int f=1;
				if(textFieldUser.getText().equals("") || Arrays.equals(textFieldPassword.getPassword(),null) || Arrays.equals(textFieldConfirm.getPassword(),null)){
					lblMsg.setForeground(Color.red);
					lblMsg.setText("No puede haber campos vacíos");
				}else{
				if(Arrays.equals(textFieldPassword.getPassword(),textFieldConfirm.getPassword())){
					lblMsg.setForeground(Color.blue);
					lblMsg.setText("Solicitud enviada");
					
					String user = textFieldUser.getText();
					char [] pass = textFieldPassword.getPassword();
					
					if(f==0){//aca la respuesta del servidor
						lblMsg.setForeground(Color.red);
						lblMsg.setText("El nombre de usuario ya está siendo utilizado");
						textFieldUser.requestFocus();
					}
					else{
						JOptionPane.showMessageDialog(null, "Usuario registrado", "Registro de usuario", JOptionPane.INFORMATION_MESSAGE);
						
						NewUserUI.this.dispose();
						MainUI.acceso(1);
					}
				}
				else{
					lblMsg.setForeground(Color.red);
					lblMsg.setText("Las contraseñas no coinciden.");
					textFieldPassword.requestFocus();
				}
				}
			}
		});
		btnEnviar.setBounds(27, 168, 120, 30);
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
