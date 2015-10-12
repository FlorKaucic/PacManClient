package client.gui;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class GameInstructions extends JDialog {

	private final JPanel contentPanel = new JPanel();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			GameInstructions dialog = new GameInstructions();
			
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public GameInstructions() {
		setBounds(100, 100, 450, 300);
		this.setModal(true);
		this.setTitle("PacoMan - Instrucciones del Juego");
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JTextArea txtrMajik = new JTextArea();
			txtrMajik.setText("Majik");
			txtrMajik.setEditable(false);
			txtrMajik.setBounds(10, 10, 422, 218);
			contentPanel.add(txtrMajik);
		}
		
		JButton btnOk = new JButton("OK");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				GameInstructions.this.dispose();
			}
		});
		btnOk.setBounds(343, 232, 89, 23);
		contentPanel.add(btnOk);
	}
}
