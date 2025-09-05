package GUI;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import Controller.Controller;

public class FinestraSceltaAggiungi extends FinestraTemplate {

    private Controller controller;
    
	private JPanel contentPane;
    private JPanel panel;
    private JButton btnBackToMenuPrincipale;
    private JSeparator separator;
    private JLabel lblLogo;
    private JButton btnAggiungiCorso;
    private JButton btnAggiungiSessioniPraticheCorso;
    private JButton btnAggiungiRicetteSessionePratica;
    private JButton btnAggiungiSessioniOnlineCorso;


	public FinestraSceltaAggiungi(Controller c) {
		controller = c;

		setTitle("UninaFoodLab - Scelta aggiungi");
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(FinestraSceltaAggiungi.class.getResource("/img/logo_ritagliato.jpg")));		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 950, 600);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
        panel = new JPanel();
        panel.setBackground(getColoreSfondo());
        panel.setLayout(null);
        panel.setBounds(0, 0, 934, 561);
        contentPane.add(panel);
        
        btnBackToMenuPrincipale = new JButton("Torna al menu");
        
        btnBackToMenuPrincipale.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
                controller.gotoMenuPrincipale(FinestraSceltaAggiungi.this); 
        	}
        }); 
        
        btnBackToMenuPrincipale.setBackground(getColorePrincipale());
        btnBackToMenuPrincipale.setForeground(Color.WHITE);
        btnBackToMenuPrincipale.setFont(getFontBottone());
        btnBackToMenuPrincipale.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnBackToMenuPrincipale.setFocusPainted(false);
        btnBackToMenuPrincipale.setBorderPainted(false);
        btnBackToMenuPrincipale.setOpaque(true);
       
        btnBackToMenuPrincipale.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
            	btnBackToMenuPrincipale.setBackground(getColoreBottoneChiaro()); 	
            }
            @Override
            public void mouseExited(MouseEvent e) {
            	btnBackToMenuPrincipale.setBackground(getColorePrincipale()); 
            }
        });
        
        btnBackToMenuPrincipale.setBounds(89, 62, 145, 46);
        panel.add(btnBackToMenuPrincipale);
		
		separator = new JSeparator();
		separator.setBackground(getColorePrincipale());
		separator.setBounds(10, 183, 914, 15);
		panel.add(separator);
		
		lblLogo = new JLabel("");
		lblLogo.setHorizontalAlignment(SwingConstants.CENTER);
		lblLogo.setIcon(getLogoVisualizzato());
		lblLogo.setBounds(372, 0, 180, 180);
		panel.add(lblLogo);
		
		JLabel lblTitolo = new JLabel("Scegli un opzione disponibile");
		lblTitolo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitolo.setForeground(getColorePrincipale());
		lblTitolo.setFont(getFontTitoloPrincipale());
		lblTitolo.setBounds(151, 197, 622, 26);
		panel.add(lblTitolo);
        
		
		Component verticalStrut = Box.createVerticalStrut(20);
		verticalStrut.setBounds(300, 0, 24, 561);
		panel.add(verticalStrut);
		
		Component verticalStrut_1 = Box.createVerticalStrut(20);
		verticalStrut_1.setBounds(600, 0, 24, 561);
		panel.add(verticalStrut_1);
		
		btnAggiungiCorso = new JButton("<html><center>Aggiungi un<br>corso</center></html>");
		btnAggiungiCorso.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//		    	controller.showFinestraAggiungiCorso();
			}
		});
		
		btnAggiungiCorso.addMouseListener(new MouseAdapter() {
		    @Override
		    public void mouseEntered(MouseEvent e) {
		    	btnAggiungiCorso.setBackground(getColoreBottoneChiaro()); 	
		    }
		    @Override
		    public void mouseExited(MouseEvent e) {
		    	btnAggiungiCorso.setBackground(getColorePrincipale()); 
		    }
		});
		
		btnAggiungiCorso.setFont(getFontBottoneMenu());
		btnAggiungiCorso.setBackground(getColorePrincipale());
		btnAggiungiCorso.setForeground(Color.WHITE);
		btnAggiungiCorso.setFocusPainted(false);
		btnAggiungiCorso.setBorderPainted(false);
		btnAggiungiCorso.setOpaque(true);
		btnAggiungiCorso.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnAggiungiCorso.setBounds(73, 284, 180, 60);
		panel.add(btnAggiungiCorso);
		
		btnAggiungiSessioniPraticheCorso = new JButton("<html><center>Aggiungi sessioni<br>pratiche al corso</center></html>");
		btnAggiungiSessioniPraticheCorso.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//		    	controller.showFinestraSelezionaCorso("pratica");
			}
		});
		
		btnAggiungiSessioniPraticheCorso.addMouseListener(new MouseAdapter() {
		    @Override
		    public void mouseEntered(MouseEvent e) {
		    	btnAggiungiSessioniPraticheCorso.setBackground(getColoreBottoneChiaro()); 	
		    }
		    @Override
		    public void mouseExited(MouseEvent e) {
		    	btnAggiungiSessioniPraticheCorso.setBackground(getColorePrincipale()); 
		    }
		});
		
		btnAggiungiSessioniPraticheCorso.setFont(getFontBottoneMenu());
		btnAggiungiSessioniPraticheCorso.setBackground(getColorePrincipale());
		btnAggiungiSessioniPraticheCorso.setForeground(Color.WHITE);
		btnAggiungiSessioniPraticheCorso.setFocusPainted(false);
		btnAggiungiSessioniPraticheCorso.setBorderPainted(false);
		btnAggiungiSessioniPraticheCorso.setOpaque(true);
		btnAggiungiSessioniPraticheCorso.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnAggiungiSessioniPraticheCorso.setBounds(372, 284, 180, 60);
		panel.add(btnAggiungiSessioniPraticheCorso);
		
		btnAggiungiRicetteSessionePratica = new JButton("<html><center>Aggiungi ricette<br>alla sessione</center></html>");
		
		btnAggiungiRicetteSessionePratica.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//		    	controller.showFinestraSelezionaCorso("ricetta");
			}
		});
		
		btnAggiungiRicetteSessionePratica.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnAggiungiRicetteSessionePratica.setBackground(getColoreBottoneChiaro()); 	
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnAggiungiRicetteSessionePratica.setBackground(getColorePrincipale()); 
			}
		});
		
		btnAggiungiRicetteSessionePratica.setFont(getFontBottoneMenu());
		btnAggiungiRicetteSessionePratica.setBackground(getColorePrincipale());
		btnAggiungiRicetteSessionePratica.setForeground(Color.WHITE);
		btnAggiungiRicetteSessionePratica.setFocusPainted(false);
		btnAggiungiRicetteSessionePratica.setBorderPainted(false);
		btnAggiungiRicetteSessionePratica.setOpaque(true);
		btnAggiungiRicetteSessionePratica.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnAggiungiRicetteSessionePratica.setBounds(372, 399, 180, 60);
		panel.add(btnAggiungiRicetteSessionePratica);
		
		btnAggiungiSessioniOnlineCorso = new JButton("<html><center>Aggiungi sessioni<br>online al corso</center></html>");
		
		btnAggiungiSessioniOnlineCorso.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//		    	controller.showFinestraSelezionaCorso("online");
			}
		});
		
		btnAggiungiSessioniOnlineCorso.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnAggiungiSessioniOnlineCorso.setBackground(getColoreBottoneChiaro()); 	
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnAggiungiSessioniOnlineCorso.setBackground(getColorePrincipale()); 
			}
		});
	
		btnAggiungiSessioniOnlineCorso.setFont(getFontBottoneMenu());
		btnAggiungiSessioniOnlineCorso.setBackground(getColorePrincipale());
		btnAggiungiSessioniOnlineCorso.setForeground(Color.WHITE);
		btnAggiungiSessioniOnlineCorso.setFocusPainted(false);
		btnAggiungiSessioniOnlineCorso.setBorderPainted(false);
		btnAggiungiSessioniOnlineCorso.setOpaque(true);
		btnAggiungiSessioniOnlineCorso.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnAggiungiSessioniOnlineCorso.setBounds(685, 284, 180, 60);
		panel.add(btnAggiungiSessioniOnlineCorso);
		
		// Per centrare la finestra ogni volta al centro dello schermo
		setLocationRelativeTo(null);
	}

}
