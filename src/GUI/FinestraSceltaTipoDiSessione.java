package GUI;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import Controller.Controller;


public class FinestraSceltaTipoDiSessione extends FinestraTemplate {
	
	private Controller controller;
	
	private JPanel contentPane;
	private JPanel panel;
	private JSeparator separator;



	public FinestraSceltaTipoDiSessione(Controller c) {
		controller = c;
		
		setTitle("UninaFoodLab - Scelta del tipo di sessione");
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(FinestraSceltaTipoDiSessione.class.getResource("/img/logo_ritagliato.jpg")));		
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 600, 500);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		panel = new JPanel();
		panel.setBackground(getColoreSfondo());
		panel.setBounds(0, 0, 584, 461);
		panel.setLayout(null);
		contentPane.add(panel);
		
		JLabel lblLogo = new JLabel("");
		lblLogo.setHorizontalAlignment(SwingConstants.CENTER);
		lblLogo.setIcon(getLogoVisualizzato());
		lblLogo.setBounds(224, 11, 150, 150);
		panel.add(lblLogo);
		
		separator = new JSeparator();
		separator.setBackground(getColorePrincipale());
		separator.setBounds(10, 183, 564, 15);
		panel.add(separator);
		
        JLabel lblTitolo = new JLabel("Scegli il tipo di sessione da visualizzare");
        lblTitolo.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitolo.setForeground(getColorePrincipale());
        lblTitolo.setFont(getFontTitoloPrincipale());
        lblTitolo.setBounds(55, 205, 475, 26);
        panel.add(lblTitolo);
        
        JButton btnVisualizzaSessioniPratiche = new JButton("Sessioni pratiche");
        
		btnVisualizzaSessioniPratiche.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.showFinestraVisualizzaSessioniPratiche();
			}
		});
		
		btnVisualizzaSessioniPratiche.addMouseListener(new MouseAdapter() {
			 @Override
			 public void mouseEntered(MouseEvent e) {
				 btnVisualizzaSessioniPratiche.setBackground(getColoreBottoneChiaro()); 	
			 }
			 @Override
			 public void mouseExited(MouseEvent e) {
				 btnVisualizzaSessioniPratiche.setBackground(getColorePrincipale()); 
			 }
		});
		
		btnVisualizzaSessioniPratiche.setBackground(getColorePrincipale());
		btnVisualizzaSessioniPratiche.setForeground(Color.WHITE);
		btnVisualizzaSessioniPratiche.setFont(getFontBottoneMenu());
		btnVisualizzaSessioniPratiche.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnVisualizzaSessioniPratiche.setFocusPainted(false);
		btnVisualizzaSessioniPratiche.setBorderPainted(false);
		btnVisualizzaSessioniPratiche.setOpaque(true);
		
		
		btnVisualizzaSessioniPratiche.setBounds(200, 273, 200, 52);
		panel.add(btnVisualizzaSessioniPratiche);
		
		
		JButton btnVisualizzaSessioniOnline = new JButton("Sessioni online");
        
		btnVisualizzaSessioniOnline.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//controller.showFinestraVisualizzaSessioniOnline();
			}
		});
		
		btnVisualizzaSessioniOnline.addMouseListener(new MouseAdapter() {
			 @Override
			 public void mouseEntered(MouseEvent e) {
				 btnVisualizzaSessioniOnline.setBackground(getColoreBottoneChiaro()); 	
			 }
			 @Override
			 public void mouseExited(MouseEvent e) {
				 btnVisualizzaSessioniOnline.setBackground(getColorePrincipale()); 
			 }
		});
		
		btnVisualizzaSessioniOnline.setBackground(getColorePrincipale());
		btnVisualizzaSessioniOnline.setForeground(Color.WHITE);
		btnVisualizzaSessioniOnline.setFont(getFontBottoneMenu());
		btnVisualizzaSessioniOnline.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnVisualizzaSessioniOnline.setFocusPainted(false);
		btnVisualizzaSessioniOnline.setBorderPainted(false);
		btnVisualizzaSessioniOnline.setOpaque(true);
		
		
		btnVisualizzaSessioniOnline.setBounds(200, 355, 200, 52);
		panel.add(btnVisualizzaSessioniOnline);
		
		
		
		// Per centrare la finestra ogni volta al centro dello schermo
		setLocationRelativeTo(null);
		
	}
	
}
