package GUI;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import Controller.Controller;
import DatabaseException.DBExceptionOperazioneQueryDML;
import DatabaseException.DBExceptionRicettaGiàAssociata;
import DatabaseException.DBExceptionRisultatoIndefinito;
import Exception.ExceptionCampoRicettaNonSelezionato;
import javax.swing.JComboBox;

public class FinestraAggiungiRicettaSessionePratica extends FinestraTemplate {
	
	private Controller controller;

	private JPanel contentPane;
	private JSeparator separator;
	private JComboBox<String> comboBoxRicette;
	private JButton btnAnnulla;
	private JButton btnAggiungi;
	
	private ArrayList<String> listaRicette;
    private DefaultComboBoxModel<String> model;

	public FinestraAggiungiRicettaSessionePratica(Controller c) {
		controller = c;
		
		setTitle("UninaFoodLab - Aggiungi ricetta alla sessione pratica");
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(FinestraAggiungiRicettaSessionePratica.class.getResource("/img/logo_ritagliato.jpg")));		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 500);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
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
		
        JLabel lblTitolo = new JLabel("Scegli la ricetta da aggiungere");
        lblTitolo.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitolo.setForeground(getColorePrincipale());
        lblTitolo.setFont(getFontTitoloPrincipale());
        lblTitolo.setBounds(105, 205, 400, 26);
        panel.add(lblTitolo);
		
        JLabel lblRicetta = new JLabel("RICETTA");
        lblRicetta.setHorizontalAlignment(SwingConstants.CENTER);
        lblRicetta.setFont(getFontTitoloSecondario());
        lblRicetta.setBounds(200, 261, 200, 34);
        panel.add(lblRicetta);
		
		comboBoxRicette = new JComboBox<String>();
		comboBoxRicette.addActionListener(new ActionListener() {
        	@Override
        	public void actionPerformed(ActionEvent e) {
                // Dopo che una selezione è stata fatta, ristabilisce il focus 
                panel.requestFocusInWindow();
            }
        });
		
		comboBoxRicette.setCursor(new Cursor(Cursor.HAND_CURSOR));
		comboBoxRicette.setOpaque(true);
		comboBoxRicette.setFont(getFontInserimento());
		comboBoxRicette.setBackground(Color.WHITE);
		comboBoxRicette.setBounds(190, 302, 220, 40);
		panel.add(comboBoxRicette);
		
		btnAggiungi = new JButton("AGGIUNGI");
		btnAggiungi.addActionListener(new ActionListener() {
        	@Override
        	public void actionPerformed(ActionEvent e) {
        		try {
        			controlloinput();
        			
            		String nomeRicettaSelezionata=recuperoNomeRicetta();
            		
                    controller.aggiungiRicettaSessionePratica(nomeRicettaSelezionata);
                    
                    messaggioInfoPopUp("Ricetta aggiunta con successo!");
                    
                    int scelta = sceltaAggiungiNuovaRicetta();
                    
                    if(scelta==JOptionPane.YES_OPTION) {
                    	setCampoRicetta();
                    }
                    else {
    		    		controller.backToFinestraSceltaAggiungi(FinestraAggiungiRicettaSessionePratica.this);
                    }
                    
                    
        		} catch (ExceptionCampoRicettaNonSelezionato err) {
        			messaggioErrorPopUp(err.getmessaggioWarningSchermo());
        		} catch (DBExceptionRicettaGiàAssociata err) {
        			messaggioErrorPopUp(err.getMessaggioErroreSchermo());
        		} catch (DBExceptionOperazioneQueryDML err) {
        			messaggioErrorPopUp(err.getMessaggioErroreSchermo());
        		}
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
		
		btnAggiungi.setBackground(getColorePrincipale());
		btnAggiungi.setForeground(Color.WHITE);
		btnAggiungi.setFont(getFontHeader());
		btnAggiungi.setFocusPainted(false);
		btnAggiungi.setBorderPainted(false);
		btnAggiungi.setOpaque(true);
				
		btnAggiungi.setCursor(new Cursor(Cursor.HAND_CURSOR));
		
		btnAggiungi.setBounds(320, 396, 130, 35);
		panel.add(btnAggiungi);
		
		btnAnnulla = new JButton("ANNULLA");
		btnAnnulla.addActionListener(new ActionListener() {
        	@Override
        	public void actionPerformed(ActionEvent e) {
                controller.backToFinestraSelezionaSessionePratica();
            }
        });
		btnAnnulla.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnAnnulla.setBackground(getColoreBottoneChiaro()); 	
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnAnnulla.setBackground(getColorePrincipale()); 
			}
		});
		
		btnAnnulla.setBackground(getColorePrincipale());
		btnAnnulla.setForeground(Color.WHITE);
		btnAnnulla.setFont(getFontHeader());
		btnAnnulla.setFocusPainted(false);
		btnAnnulla.setBorderPainted(false);
		btnAnnulla.setOpaque(true);
				
		btnAnnulla.setCursor(new Cursor(Cursor.HAND_CURSOR));
		
		btnAnnulla.setBounds(150, 396, 130, 35);
		panel.add(btnAnnulla);

		
		// Per centrare la finestra ogni volta al centro dello schermo
		setLocationRelativeTo(null);
		
	}
	
	public void setListaRicette() {
		try {
			listaRicette = controller.impostaElencoRicette();
		} catch (DBExceptionRisultatoIndefinito e) {
			messaggioErrorPopUp(e.getMessaggioErrorSchermo());
		}
		String[] vettoreRicette = listaRicette.toArray(new String[0]);
		model = new DefaultComboBoxModel<>(vettoreRicette);
		comboBoxRicette.setModel(model);
	}
	
	public void setCampoRicetta() {
		comboBoxRicette.setSelectedIndex(-1);
	}
	
	public String recuperoNomeRicetta() {
		return comboBoxRicette.getSelectedItem().toString();
	}
	
	public void controlloinput() throws ExceptionCampoRicettaNonSelezionato {
		if(comboBoxRicette.getSelectedIndex()==-1) {
			throw new ExceptionCampoRicettaNonSelezionato();
		}
	}
	
	private int sceltaAggiungiNuovaRicetta() {
		int sceltaEseguita;
		sceltaEseguita = messaggioSceltaPopUp("Vuoi aggiungere un' altra ricetta?");		
		return sceltaEseguita;
	}
}
