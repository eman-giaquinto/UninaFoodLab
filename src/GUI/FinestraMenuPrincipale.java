package GUI;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Controller.Controller;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Image;

import javax.swing.ImageIcon;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;

import javax.swing.Box;
import javax.swing.JSeparator;
import javax.swing.JButton;

public class FinestraMenuPrincipale extends FinestraTemplate {
	
	private Controller controller;

	private JPanel contentPane;
	
	private JLabel lblNomeCognomeChef;

	public FinestraMenuPrincipale(Controller c) {
		setTitle("UninaFoodLab - Menu Principale");
		setResizable(false);
		controller = c;

		setIconImage(Toolkit.getDefaultToolkit().getImage(FinestraMenuPrincipale.class.getResource("/img/logo_ritagliato.jpg")));		
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
		
		lblNomeCognomeChef = new JLabel("");
		
		ImageIcon logoIcon = new ImageIcon(FinestraTemplate.class.getResource("/img/user-square.png"));
	    Image logoImage = logoIcon.getImage();
	    Image scaledLogo = logoImage.getScaledInstance(60, 60, Image.SCALE_SMOOTH);
	    lblNomeCognomeChef.setIcon(new ImageIcon(scaledLogo));
	    
	    lblNomeCognomeChef.setHorizontalTextPosition(SwingConstants.CENTER);
	    lblNomeCognomeChef.setVerticalTextPosition(SwingConstants.BOTTOM);   

		lblNomeCognomeChef.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNomeCognomeChef.setHorizontalAlignment(SwingConstants.CENTER);
		lblNomeCognomeChef.setBounds(647, 75, 250, 90);
		
		panel.add(lblNomeCognomeChef);
		
		Component verticalStrut = Box.createVerticalStrut(20);
		verticalStrut.setBounds(300, 0, 24, 561);
		panel.add(verticalStrut);
		
		Component verticalStrut_1 = Box.createVerticalStrut(20);
		verticalStrut_1.setBounds(600, 0, 24, 561);
		panel.add(verticalStrut_1);
		
		JLabel lblLogo = new JLabel("");
		lblLogo.setHorizontalAlignment(SwingConstants.CENTER);
		lblLogo.setIcon(getLogoVisualizzato());
		lblLogo.setBounds(372, 0, 180, 180);
		panel.add(lblLogo);
		
		JSeparator separator = new JSeparator();
		separator.setBackground(getColorePrincipale());
		separator.setBounds(0, 183, 934, 15);
		panel.add(separator);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBackground(getColorePrincipale());
		separator_1.setBounds(0, 234, 934, 15);
		panel.add(separator_1);
		
		JLabel lblTitolo = new JLabel("Menu Principale");
		lblTitolo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitolo.setForeground(getColorePrincipale());
		lblTitolo.setFont(getFontTitoloPrincipale());
		lblTitolo.setBounds(334, 197, 256, 26);
		panel.add(lblTitolo);
		
		JButton btnVisualizza = new JButton("Visualizza");
		
		btnVisualizza.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
		    	controller.showFinestraVisualizzaCorsi();
        	}
        });   
		
		btnVisualizza.addMouseListener(new MouseAdapter() {
		    @Override
		    public void mouseEntered(MouseEvent e) {
		    	btnVisualizza.setBackground(getColoreBottoneChiaro()); 	
		    }
		    @Override
		    public void mouseExited(MouseEvent e) {
		    	btnVisualizza.setBackground(getColorePrincipale()); 
		    }
		});
		
		btnVisualizza.setFont(getFontBottoneMenu());
		btnVisualizza.setBackground(getColorePrincipale());
		btnVisualizza.setForeground(Color.WHITE);
		btnVisualizza.setFocusPainted(false);
		btnVisualizza.setBorderPainted(false);
		btnVisualizza.setOpaque(true);
		btnVisualizza.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnVisualizza.setBounds(372, 283, 180, 50);
		panel.add(btnVisualizza);
		
		JButton btnAggiungi = new JButton("Aggiungi");
		
		btnAggiungi.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
		    	controller.showFinestraSceltaAggiungi();
        	}
        }); 
		
		btnAggiungi.addMouseListener(new MouseAdapter() {
		    @Override
		    public void mouseEntered(MouseEvent e) {
		    	btnAggiungi.setBackground(getColoreBottoneChiaro()); 	
		    }
		    @Override
		    public void mouseExited(MouseEvent e) {
		    	btnAggiungi.setBackground(getColorePrincipale()); 
		    }
		});
		
		btnAggiungi.setFont(getFontBottoneMenu());
		btnAggiungi.setBackground(getColorePrincipale());
		btnAggiungi.setForeground(Color.WHITE);
		btnAggiungi.setFocusPainted(false);
		btnAggiungi.setBorderPainted(false);
		btnAggiungi.setOpaque(true);
		btnAggiungi.setCursor(new Cursor(Cursor.HAND_CURSOR));
		
		btnAggiungi.setBounds(372, 375, 180, 50);
		panel.add(btnAggiungi);
		
		JButton btnReportMensile = new JButton("Report Mensile");
		
		btnAggiungi.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
		        // Inserire qui la logica per visualizzare il report mensile
        	}
        }); 
		
		btnReportMensile.addMouseListener(new MouseAdapter() {
		    @Override
		    public void mouseEntered(MouseEvent e) {
		    	btnReportMensile.setBackground(getColoreBottoneChiaro()); 	
		    }
		    @Override
		    public void mouseExited(MouseEvent e) {
		    	btnReportMensile.setBackground(getColorePrincipale()); 
		    }
		});
		
		btnReportMensile.setFont(getFontBottoneMenu());
		btnReportMensile.setBackground(getColorePrincipale());
		btnReportMensile.setForeground(Color.WHITE);
		btnReportMensile.setFocusPainted(false);
		btnReportMensile.setBorderPainted(false);
		btnReportMensile.setOpaque(true);
		btnReportMensile.setCursor(new Cursor(Cursor.HAND_CURSOR));
		
		btnReportMensile.setBounds(372, 468, 180, 50);
		panel.add(btnReportMensile);
		
		// Per centrare la finestra ogni volta al centro dello schermo
		setLocationRelativeTo(null);
		
	}

	public void impostaChef(String nomecognomechefautenticato) {
		lblNomeCognomeChef.setText(nomecognomechefautenticato);
	}
}
