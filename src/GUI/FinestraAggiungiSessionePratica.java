package GUI;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import com.github.lgooddatepicker.components.DatePicker;
import com.github.lgooddatepicker.components.DatePickerSettings;
import com.github.lgooddatepicker.optionalusertools.DateVetoPolicy;

import Controller.Controller;
import DTO.Corso.FrequenzaSessione;
import DatabaseException.DBExceptionDataSessionePraticaDiversaMensile;
import DatabaseException.DBExceptionDataSessioneFuoriRange;
import DatabaseException.DBExceptionOperazioneQueryDML;
import DatabaseException.DBExceptionSessionePraticaDuplicata;

import Exception.ExceptionCampoDataSessioneVuoto;
import Exception.ExceptionCampoNumeroAdesioniNullo;
import Exception.ExceptionCampoOrarioFineVuoto;
import Exception.ExceptionCampoOrarioInizioVuoto;

import Exception.ExceptionOrarioInizioMaggioreOrarioFine;

import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

public class FinestraAggiungiSessionePratica extends FinestraTemplate {

    private Controller controller;
    
	private JPanel contentPane;
    private JPanel panel;
    private JButton btnBackToSceltaAggiungi;
    private JSeparator separator;
    private JLabel lblLogo;
    private JButton btnConferma;

    // Data sessione
    private JLabel lblDataSessione;
    private DatePickerSettings dateSettings;
    private DatePicker datePickerDataSessione;
    private DateVetoPolicy dateConsentite;
    
    // Orario inizio
    private JLabel lblOrarioInizio;
    private JComboBox<String> comboBoxOrarioInizio;
    
    // Orario fine
    private JLabel lblOrarioFine;
    private JComboBox<String> comboBoxOrarioFine;

    // Numero Adesioni
    private JLabel lblNumeroAdesioni;
    private JSpinner spinnerNumeroAdesioni; 

   
	public FinestraAggiungiSessionePratica(Controller c) {
		controller = c;

		setTitle("UninaFoodLab - Aggiungi una sessione pratica");
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(FinestraAggiungiSessionePratica.class.getResource("/img/logo_ritagliato.jpg")));		
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
        
        btnBackToSceltaAggiungi = new JButton("Torna ai corsi");
        
        btnBackToSceltaAggiungi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
                controller.backToSelezionaCorso(FinestraAggiungiSessionePratica.this); 
			}
		});
        
        btnBackToSceltaAggiungi.setBackground(getColorePrincipale());
        btnBackToSceltaAggiungi.setForeground(Color.WHITE);
        btnBackToSceltaAggiungi.setFont(getFontBottone());
        btnBackToSceltaAggiungi.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnBackToSceltaAggiungi.setFocusPainted(false);
        btnBackToSceltaAggiungi.setBorderPainted(false);
        btnBackToSceltaAggiungi.setOpaque(true);
       
        btnBackToSceltaAggiungi.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
            	btnBackToSceltaAggiungi.setBackground(getColoreBottoneChiaro()); 	
            }
            @Override
            public void mouseExited(MouseEvent e) {
            	btnBackToSceltaAggiungi.setBackground(getColorePrincipale()); 
            }
        });
        
        btnBackToSceltaAggiungi.setBounds(89, 62, 145, 46);
        panel.add(btnBackToSceltaAggiungi);
		
		separator = new JSeparator();
		separator.setBackground(getColorePrincipale());
		separator.setBounds(10, 183, 914, 15);
		panel.add(separator);
		
		lblLogo = new JLabel("");
		lblLogo.setHorizontalAlignment(SwingConstants.CENTER);
		lblLogo.setIcon(getLogoVisualizzato());
		lblLogo.setBounds(372, 0, 180, 180);
		panel.add(lblLogo);
		
		Component verticalStrut = Box.createVerticalStrut(20);
		verticalStrut.setBounds(300, 0, 24, 561);
		panel.add(verticalStrut);
		
		Component verticalStrut_1 = Box.createVerticalStrut(20);
		verticalStrut_1.setBounds(600, 0, 24, 561);
		panel.add(verticalStrut_1);
        
        JLabel lblTitolo = new JLabel("Inserisci i seguenti dati per aggiungere una sessione pratica");
        lblTitolo.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitolo.setForeground(getColorePrincipale());
        lblTitolo.setFont(getFontTitoloPrincipale());
        lblTitolo.setBounds(120, 197, 700, 26);
        panel.add(lblTitolo);
        
		
		// SEZIONE DATA SESSIONE
        
        lblDataSessione = new JLabel("DATA SESSIONE");
        lblDataSessione.setHorizontalAlignment(SwingConstants.CENTER);
        lblDataSessione.setFont(getFontTitoloSecondario());
        lblDataSessione.setBounds(62, 252, 200, 34);
        panel.add(lblDataSessione);
        
        
        dateSettings = new DatePickerSettings();

        // FONT PER TUTTE LE DATE
        dateSettings.setFontValidDate(getFontInserimento());
        // FONT PER LE DATE NON VALIDE
		dateSettings.setFontInvalidDate(getFontInserimento());
		// FONT PER LE DATE VIOLATE
		dateSettings.setFontVetoedDate(getFontInserimento());
        
        datePickerDataSessione = new DatePicker(dateSettings);
        datePickerDataSessione.getComponentDateTextField().setEditable(false);
        datePickerDataSessione.getComponentToggleCalendarButton().setOpaque(false);
        datePickerDataSessione.getComponentToggleCalendarButton().setContentAreaFilled(false);
        
        datePickerDataSessione.setBounds(62, 289, 200, 40);
        panel.add(datePickerDataSessione);
        
        
		// SEZIONE ORARIO INIZIO
        
        lblOrarioInizio = new JLabel("ORARIO INIZIO");
        lblOrarioInizio.setHorizontalAlignment(SwingConstants.CENTER);
        lblOrarioInizio.setFont(getFontTitoloSecondario());
        lblOrarioInizio.setBounds(362, 252, 200, 34);
        panel.add(lblOrarioInizio);
        
        
		comboBoxOrarioInizio = new JComboBox<>();
		
		comboBoxOrarioInizio.addActionListener(new ActionListener() {
        	@Override
        	public void actionPerformed(ActionEvent e) {
                // Dopo che una selezione è stata fatta, ristabilisce il focus 
                panel.requestFocusInWindow();
            }
        });
        
		for (int ora = 0; ora < 24; ora++) {
		    for (int minuto = 0; minuto < 60; minuto=minuto+30) {
		        comboBoxOrarioInizio.addItem(String.format("%02d:%02d", ora, minuto));
		    }
		}

		comboBoxOrarioInizio.setCursor(new Cursor(Cursor.HAND_CURSOR));
		comboBoxOrarioInizio.setOpaque(true);
		comboBoxOrarioInizio.setFont(getFontInserimento());
		comboBoxOrarioInizio.setBackground(Color.WHITE);
		
		comboBoxOrarioInizio.setBounds(362, 289, 200, 40);
		panel.add(comboBoxOrarioInizio);
		
		
		// SEZIONE ORARIO FINE
		
		lblOrarioFine = new JLabel("ORARIO FINE");
		lblOrarioFine.setHorizontalAlignment(SwingConstants.CENTER);
		lblOrarioFine.setFont(getFontTitoloSecondario());
		lblOrarioFine.setBounds(357, 363, 200, 34);
        panel.add(lblOrarioFine);
        
        
		comboBoxOrarioFine = new JComboBox<>();
		
		comboBoxOrarioFine.addActionListener(new ActionListener() {
        	@Override
        	public void actionPerformed(ActionEvent e) {
                // Dopo che una selezione è stata fatta, ristabilisce il focus 
                panel.requestFocusInWindow();
            }
        });
        
		for (int ora = 0; ora < 24; ora++) {
		    for (int minuto = 0; minuto < 60; minuto=minuto+30) {
		    	comboBoxOrarioFine.addItem(String.format("%02d:%02d", ora, minuto));
		    }
		}

		comboBoxOrarioFine.setCursor(new Cursor(Cursor.HAND_CURSOR));
		comboBoxOrarioFine.setOpaque(true);
		comboBoxOrarioFine.setFont(getFontInserimento());
		comboBoxOrarioFine.setBackground(Color.WHITE);
		
		comboBoxOrarioFine.setBounds(362, 401, 200, 40);
		panel.add(comboBoxOrarioFine);
       
        
		// SEZIONE NUMERO ADESIONI
		
		lblNumeroAdesioni = new JLabel("NUMERO ADESIONI");
		lblNumeroAdesioni.setHorizontalAlignment(SwingConstants.CENTER);
		lblNumeroAdesioni.setFont(getFontTitoloSecondario());
		lblNumeroAdesioni.setBounds(657, 252, 250, 34);
        panel.add(lblNumeroAdesioni);
		
		
        spinnerNumeroAdesioni = new JSpinner();
        spinnerNumeroAdesioni.setModel(new SpinnerNumberModel(Integer.valueOf(0), 0, null, Integer.valueOf(1)));
        spinnerNumeroAdesioni.setFont(getFontInserimento());
        
        
        JFormattedTextField textField = ((JSpinner.DefaultEditor) spinnerNumeroAdesioni.getEditor()).getTextField();
        textField.setEditable(false);
        textField.setBackground(Color.WHITE);
        
        spinnerNumeroAdesioni.setBounds(685, 289, 200, 40);
        panel.add(spinnerNumeroAdesioni);
        
        
        btnConferma = new JButton("Conferma");
		btnConferma.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
			    	controlloInputCampi();
			    	
	        		controlloConsistenzaOrari();
			    	
	        		LocalDate valoreDataSessione = recuperoDataSessione();
	        		
	        		LocalTime valoreOrarioInizio = recuperoOrarioInizio();
	        		
	        		LocalTime valoreOrarioFine =  recuperoOrarioFine();
	        		
	        		int valoreIumeroAdesioni = recuperoNumeroAdesioni();
	        			
			    	controller.richiestaCreaSessionePratica(valoreDataSessione,valoreOrarioInizio,valoreOrarioFine,valoreIumeroAdesioni);
			    	
		    		messaggioInfoPopUp("Sessione pratica aggiunta con successo !");
		    		
	    		    int scelta = sceltaAggiungiNuovaSessionePratica();
                    
                    if(scelta==JOptionPane.YES_OPTION) {
                    	setCampiInserimento();
                    }
                    else {
    		    		controller.backToFinestraSceltaAggiungi(FinestraAggiungiSessionePratica.this);
                    }
	                    
		    		/* Blocco eccezioni campi vuoti */
		    	} catch(ExceptionCampoDataSessioneVuoto err) {
		    		messaggioWarningPopUp(err.getmessaggioWarningSchermo());
		    	} catch(ExceptionCampoOrarioInizioVuoto err) {
		    		messaggioWarningPopUp(err.getmessaggioWarningSchermo());
		    	} catch(ExceptionCampoOrarioFineVuoto err) {
		    		messaggioWarningPopUp(err.getmessaggioWarningSchermo());
		    	} catch(ExceptionCampoNumeroAdesioniNullo err) {
		    		messaggioWarningPopUp(err.getmessaggioWarningSchermo());
		    	
		    	
		    		/* Blocco eccezioni logiche */
		    	} catch(ExceptionOrarioInizioMaggioreOrarioFine err) {
		    		messaggioWarningPopUp(err.getmessaggioWarningSchermo());
		    		
		    		/* Blocco eccezioni database*/
		    	} catch(DBExceptionDataSessionePraticaDiversaMensile err) {
		    		messaggioWarningPopUp(err.getMessaggioErroreSchermo());
		    	} catch(DBExceptionSessionePraticaDuplicata err) {
		    		messaggioWarningPopUp(err.getMessaggioErroreSchermo());
		       	} catch(DBExceptionDataSessioneFuoriRange err) {
		    		messaggioWarningPopUp(err.getMessaggioErroreSchermo());	
		    	} catch(DBExceptionOperazioneQueryDML err) {
		    		messaggioErrorPopUp(err.getMessaggioErroreSchermo());
		    	}	
			}
		});
		
		btnConferma.addMouseListener(new MouseAdapter() {
		    @Override
		    public void mouseEntered(MouseEvent e) {
		    	btnConferma.setBackground(getColoreBottoneChiaro()); 	
		    }
		    @Override
		    public void mouseExited(MouseEvent e) {
		    	btnConferma.setBackground(getColorePrincipale()); 
		    }
		});
		
		btnConferma.setFont(getFontBottoneMenu());
		btnConferma.setBackground(getColorePrincipale());
		btnConferma.setForeground(Color.WHITE);
		btnConferma.setFocusPainted(false);
		btnConferma.setBorderPainted(false);
		btnConferma.setOpaque(true);
		btnConferma.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnConferma.setBounds(692, 475, 180, 60);
		panel.add(btnConferma);
        
		
        
		
		// Per centrare la finestra ogni volta al centro dello schermo
		setLocationRelativeTo(null);
	}
	
	
	public void setCampiInserimento() {
		datePickerDataSessione.setDate(null);
		comboBoxOrarioInizio.setSelectedIndex(-1); 
		comboBoxOrarioFine.setSelectedIndex(-1);
		spinnerNumeroAdesioni.setValue(0);
	}

	
	private void controlloInputCampi() throws ExceptionCampoDataSessioneVuoto,ExceptionCampoOrarioInizioVuoto,
					ExceptionCampoOrarioFineVuoto,ExceptionCampoNumeroAdesioniNullo{
		
		if(datePickerDataSessione.getDate()==null) {
			throw new ExceptionCampoDataSessioneVuoto();
		}
		
		if(comboBoxOrarioInizio.getSelectedIndex()==-1) {
			throw new ExceptionCampoOrarioInizioVuoto();
		}
		
		if(comboBoxOrarioFine.getSelectedIndex()==-1) {
			throw new ExceptionCampoOrarioFineVuoto();
		}
		
		if(spinnerNumeroAdesioni.getValue()==Integer.valueOf(0)) {
			throw new ExceptionCampoNumeroAdesioniNullo();
		}
		
	}
	
	private void controlloConsistenzaOrari() throws ExceptionOrarioInizioMaggioreOrarioFine{
		int orarioInizioInserito = comboBoxOrarioInizio.getSelectedIndex();
		int orarioFineInserito = comboBoxOrarioFine.getSelectedIndex();

		if(orarioInizioInserito>=orarioFineInserito) {
			throw new ExceptionOrarioInizioMaggioreOrarioFine();
		}

	}
	
	
	public void setRangeDataSessionePratica(LocalDate dataInizioCorso,LocalDate dataFineCorso,String frequenzaSessioneCorsoSelezionato) {

		if(frequenzaSessioneCorsoSelezionato.equals("Giornaliera")) {
			dateConsentite = new DateVetoPolicy() {
	            @Override
	            public boolean isDateAllowed(LocalDate date) {
	                boolean rangeDate = !date.isBefore(dataInizioCorso) && !date.isAfter(dataFineCorso);
	             
	                return rangeDate ;
	            }
	        };
		}
		
		if(frequenzaSessioneCorsoSelezionato.equals("Settimanale")) {
			dateConsentite = new DateVetoPolicy() {
	            @Override
	            public boolean isDateAllowed(LocalDate date) {
	                boolean rangeDate = !date.isBefore(dataInizioCorso) && !date.isAfter(dataFineCorso);
	                
	                boolean giornoSettimanaleConsentito = date.getDayOfWeek() == dataInizioCorso.getDayOfWeek();
	                
	                return rangeDate && giornoSettimanaleConsentito;
	            }
	        };
		}
		
		if(frequenzaSessioneCorsoSelezionato.equals("Mensile")) {
			dateConsentite = new DateVetoPolicy() {
	            @Override
	            public boolean isDateAllowed(LocalDate date) {
	                boolean rangeDate = !date.isBefore(dataInizioCorso) && !date.isAfter(dataFineCorso);
	                
	                boolean primaSessioneConsentita = date.getYear() == dataInizioCorso.getYear() &&
	                						 date.getMonth() == dataInizioCorso.getMonth() &&
	    	                				 date.getDayOfMonth() != dataInizioCorso.getDayOfMonth();
	                
	                boolean ultimaSessioneConsentita = date.getYear() == dataFineCorso.getYear() &&
   						 date.getMonth() == dataFineCorso.getMonth() &&
           				 date.getDayOfMonth() != dataFineCorso.getDayOfMonth();
	                
	                return rangeDate && !primaSessioneConsentita && !ultimaSessioneConsentita;
	            }
	        };
		}
       
        dateSettings.setVetoPolicy(dateConsentite);
        
	}
	
	
	private LocalDate recuperoDataSessione() {
		return datePickerDataSessione.getDate();
	}
	
	private LocalTime recuperoOrarioInizio() {
		String orarioInizio = comboBoxOrarioInizio.getSelectedItem().toString();
		
		LocalTime orarioInizioFormattato = LocalTime.parse(orarioInizio);
        
        return orarioInizioFormattato;
		
	} 
	
	private LocalTime recuperoOrarioFine() {
		String orarioFine = comboBoxOrarioFine.getSelectedItem().toString();
		
		LocalTime orarioFineFormattato = LocalTime.parse(orarioFine);
        
        return orarioFineFormattato;
    } 
	
	private int recuperoNumeroAdesioni() {
		return (int)spinnerNumeroAdesioni.getValue();
	}  
	
	private int sceltaAggiungiNuovaSessionePratica() {
		int sceltaEseguita;
		sceltaEseguita = messaggioSceltaPopUp("Vuoi aggiungere un' altra sessione pratica?");		
		return sceltaEseguita;
	}
	
}
