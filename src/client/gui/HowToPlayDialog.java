package client.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.io.File;
import java.io.IOException;
import java.awt.SystemColor;
import javax.swing.JEditorPane;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Insets;

@SuppressWarnings("serial")
public class HowToPlayDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();

	/**
	 * Create the dialog.
	 */
	public HowToPlayDialog() {
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		setBounds(dim.width / 2 - 335, dim.height / 2 - 225, 670, 450);
		this.setModal(true);
		this.setTitle("Instrucciones");
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		

		JEditorPane instrucciones;
		try {
			File instr = new File("res/otro.html");
			instrucciones = new JEditorPane(instr.toURI().toURL());
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Cliente", JOptionPane.ERROR_MESSAGE);
			instrucciones = new JEditorPane("text/html",
					"<center>No se encontr&oacute; la p&aacute;gina de instrucciones.</center>");
		}
		instrucciones.setFont(new Font("Tahoma", Font.PLAIN, 14));
		instrucciones.setEditable(false);
		instrucciones.setBackground(SystemColor.control);
		instrucciones.setMargin(new Insets(0,0,500,450));
		instrucciones.setBounds(0, 0, 670, 450);
		contentPanel.add(instrucciones);
	}
}
