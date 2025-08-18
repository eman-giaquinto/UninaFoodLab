package GUI;

import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Controller.Controller;
import javax.swing.JLabel;

public class FinestraMenuPrincipale extends FinestraTemplate {
	
	private Controller controller;

	private JPanel contentPane;
	
	private JLabel lblNewLabel;

	public FinestraMenuPrincipale(Controller c) {
		controller = c;

		setIconImage(Toolkit.getDefaultToolkit().getImage(FinestraLogin.class.getResource("/img/logo_ritagliato.jpg")));		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 950, 600);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(getColoreSfondo());
		panel.setBounds(0, 0, 934, 561);
		contentPane.add(panel);
		panel.setLayout(null);
		
		lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(423, 221, 117, 48);
		panel.add(lblNewLabel);
	}

	public void impostaChef(String nomecognomechefautenticato) {
		lblNewLabel.setText(nomecognomechefautenticato);
	}
}
