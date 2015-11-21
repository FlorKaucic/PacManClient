package client.gui.game.other;

import java.awt.Color;
import java.awt.Font;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JDialog;
import javax.swing.JPanel;
import client.config.Config;
import client.logic.Parser;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;

@SuppressWarnings("serial")
public class WaitingDialog extends JDialog {

	private JPanel contentPanel = new JPanel();
	private JLabel mensaje;
	private Image img = null;
	
	/**
	 * Create the dialog.
	 */
	public WaitingDialog(int profile) {
		int x = Integer.parseInt(Config.get("screen_width"))/2-200;
		int y = (Integer.parseInt(Config.get("screen_height"))-Integer.parseInt(Config.get("taskbar_height")))/2-200;
		setBounds(x, y, 400, 400);
		this.setModal(true);
		this.setUndecorated(true);
		
		getRootPane().setLayout(null);
		
		
		contentPanel.setBounds(0,0,400,400);
		contentPanel.setLayout(null);
		contentPanel.setBorder( BorderFactory.createLineBorder(new Color(5, 73, 122), 5, false));
		contentPanel.setBackground(Color.BLACK);

		getRootPane().add(contentPanel);
		
		if(profile != -1)
			try {
				this.img = ImageIO.read(new File(Config.get("img_path")+Parser.parseProfile(profile).toLowerCase()+".png"));
			} catch (IOException e) {
				JLabel error = new JLabel("No se pudo cargar la imagen.");
				error.setBounds(90, 150, 200, 50);
				error.setForeground(Color.WHITE);
				error.setFont(new Font("Tahoma", Font.BOLD, 14));
				error.setVisible(true);
				contentPanel.add(error);
			}
		JLabel pj = new JLabel("Tu perfil es "+Parser.parseProfile(profile));
		pj.setBounds(100,50,200,70);
		pj.setForeground(Color.WHITE);
		pj.setFont(new Font("Tahoma", Font.BOLD, 18));
		pj.setVisible(true);
		contentPanel.add(pj);
		
		mensaje = new JLabel("Esperando a los demas jugadores...");
		mensaje.setBounds(50,300,300,50);
		mensaje.setForeground(Color.WHITE);
		mensaje.setFont(new Font("Tahoma", Font.BOLD, 16));
		mensaje.setVisible(true);
		contentPanel.add(mensaje);
		
		contentPanel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int opc = JOptionPane.showConfirmDialog(null, "¿Realmente desea salir?", "Cerrar",
						JOptionPane.OK_CANCEL_OPTION);
				if (opc == JOptionPane.OK_OPTION) {
					System.exit(0);
				}
			}
		});
		contentPanel.setToolTipText("Click aca para opciones de cierre.");
	}
	
	public void update(String mensaje){
		this.mensaje.setText(mensaje);
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		if(img != null)
			g.drawImage(this.img, 120, 120, 280, 280, 0, 0, 30, 30, null);
	}
	
}
