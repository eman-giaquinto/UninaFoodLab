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
import java.time.temporal.ChronoUnit;

import javax.swing.Box;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import com.github.lgooddatepicker.components.DatePicker;
import com.github.lgooddatepicker.components.DatePickerSettings;

import Controller.Controller;
import DatabaseException.DBExceptionDataInizioMaggioreDataFine;
import DatabaseException.DBExceptionOperazioneQueryDML;
import DatabaseException.DBExceptionRisultatoIndefinito;
import Exception.ExceptionCampoDataDiFineVuoto;
import Exception.ExceptionCampoDataDiInizioVuoto;
import Exception.ExceptionCampoFrequenzaSessioneVuoto;
import Exception.ExceptionCampoTipoCorsoVuoto;
import Exception.ExceptionDataInizioMaggioreDataFine;
import Exception.ExceptionFrequenzaSessioneMensile;
import Exception.ExceptionFrequenzaSessioneSettimanale;

public class FinestraAggiungiCorso extends FinestraTemplate {

    private Controller controller;
    
	private JPanel contentPane;
    private JPanel panel;
    private JButton btnBackToSceltaAggiungi;
    private JSeparator separator;
    private JLabel lblLogo;
    private JButton btnConferma;

    // Tipo di Corso
    private JLabel lblTipoDiCorso;
    private JComboBox<String> listaTipoDiCorso;
    private DefaultComboBoxModel<String> modelTipoDiCorso;
    
    // Data di inizio
    private JLabel lblDataInizio;
    private DatePicker datePickerDataInizio;

    // Data di fine
    private JLabel lblDataFine;
    private DatePicker datePickerDataFine;
    
    // Frequenza Sessione
    private JLabel lblFrequenzaSessione;
    private JComboBox<String> listaFrequenzaSessione;
    private DefaultComboBoxModel<String> modelFrequenzaSessione;
 
    /* Oggetti utili */
    
    // Tipo di Corso
    String[] tipiDiCorso;
    
    // Frequenza Sessione
    String[] frequenzeSessioni;

   
	public FinestraAggiungiCorso(Controller c) {
		controller = c;

		setTitle("UninaFoodLab - Aggiungi un corso");
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(FinestraAggiungiCorso.class.getResource("/img/logo_ritagliato.jpg")));		
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
        
        btnBackToSceltaAggiungi = new JButton("Torna indietro");
        
        btnBackToSceltaAggiungi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
            	controller.backToFinestraSceltaAggiungi(FinestraAggiungiCorso.this);;
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
		
		// SEZIONE TIPO DI CORSO
		
        lblTipoDiCorso = new JLabel("TIPO DI CORSO");
        lblTipoDiCorso.setHorizontalAlignment(SwingConstants.CENTER);
        lblTipoDiCorso.setFont(getFontTitoloSecondario());
        lblTipoDiCorso.setBounds(62, 252, 200, 34);
        panel.add(lblTipoDiCorso);
		
        listaTipoDiCorso = new JComboBox<String>();
        listaTipoDiCorso.setFont(getFontInserimento());
        listaTipoDiCorso.addActionListener(new ActionListener() {
        	@Override
        	public void actionPerformed(ActionEvent e) {
                // Dopo che una selezione è stata fatta, ristabilisce il focus 
                panel.requestFocusInWindow();
            }
        });
    
        listaTipoDiCorso.setBackground(Color.WHITE); 
        listaTipoDiCorso.setForeground(Color.BLACK);
        listaTipoDiCorso.setCursor(new Cursor(Cursor.HAND_CURSOR));
        listaTipoDiCorso.setBounds(62, 289, 200, 40);
        panel.add(listaTipoDiCorso);
        
        //SEZIONE DATA DI INIZIO E FINE
        
        lblDataInizio = new JLabel("DATA DI INIZIO");
        lblDataInizio.setHorizontalAlignment(SwingConstants.CENTER);
        lblDataInizio.setFont(getFontTitoloSecondario());
        lblDataInizio.setBounds(362, 252, 200, 34);
        panel.add(lblDataInizio);
        
        
        DatePickerSettings dateSettingsDataInizio = new DatePickerSettings();

        // FONT PER TUTTE LE DATE
		dateSettingsDataInizio.setFontValidDate(getFontInserimento());
        // FONT PER LE DATE NON VALIDE
		dateSettingsDataInizio.setFontInvalidDate(getFontInserimento());
		// FONT PER LE DATE VIOLATE
		dateSettingsDataInizio.setFontVetoedDate(getFontInserimento());
        
        datePickerDataInizio = new DatePicker(dateSettingsDataInizio);
        datePickerDataInizio.getComponentDateTextField().setEditable(false);
        datePickerDataInizio.getComponentToggleCalendarButton().setOpaque(false);
        datePickerDataInizio.getComponentToggleCalendarButton().setContentAreaFilled(false);
        
        datePickerDataInizio.setBounds(362, 289, 200, 40);
        panel.add(datePickerDataInizio);
        
        lblDataFine = new JLabel("DATA DI FINE");
        lblDataFine.setHorizontalAlignment(SwingConstants.CENTER);
        lblDataFine.setFont(getFontTitoloSecondario());
        lblDataFine.setBounds(357, 363, 200, 34);
        panel.add(lblDataFine);
        
        DatePickerSettings dateSettingsDataFine = new DatePickerSettings();

        // FONT PER TUTTE LE DATE
        dateSettingsDataFine.setFontValidDate(getFontInserimento());
        // FONT PER LE DATE NON VALIDE
        dateSettingsDataFine.setFontInvalidDate(getFontInserimento());
		// FONT PER LE DATE VIOLATE
        dateSettingsDataFine.setFontVetoedDate(getFontInserimento());
        
        datePickerDataFine = new DatePicker(dateSettingsDataFine);
        datePickerDataFine.getComponentDateTextField().setEditable(false);
        datePickerDataFine.getComponentToggleCalendarButton().setOpaque(false);
        datePickerDataFine.getComponentToggleCalendarButton().setContentAreaFilled(false);
        datePickerDataFine.setBounds(362, 401, 200, 40);
        panel.add(datePickerDataFine);
        
        // SEZIONE FREQUENZA SESSIONE
        
        lblFrequenzaSessione = new JLabel("FREQUENZA SESSIONE");
        lblFrequenzaSessione.setHorizontalAlignment(SwingConstants.CENTER);
        lblFrequenzaSessione.setFont(getFontTitoloSecondario());
        lblFrequenzaSessione.setBounds(657, 252, 250, 34);
        panel.add(lblFrequenzaSessione);
        
        listaFrequenzaSessione  = new JComboBox<String>();
        listaFrequenzaSessione.setFont(getFontInserimento());
        listaFrequenzaSessione.addActionListener(new ActionListener() {
        	@Override
        	public void actionPerformed(ActionEvent e) {
                // Dopo che una selezione è stata fatta, ristabilisce il focus 
                panel.requestFocusInWindow();
            }
        });
    
        listaFrequenzaSessione.setBackground(Color.WHITE); 
        listaFrequenzaSessione.setForeground(Color.BLACK);
        listaFrequenzaSessione.setCursor(new Cursor(Cursor.HAND_CURSOR));
        listaFrequenzaSessione.setBounds(681, 289, 200, 40);
        panel.add(listaFrequenzaSessione);
        
        
        btnConferma = new JButton("Conferma");
		btnConferma.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
			    	controlloInputCampi();
			    	
	        		controlloConsistenzaDateInserite();
			    	
	        		String valoreTipoDiCorso = recuperoTipoDiCorso(); 
	        		
	        		String valoreDataInizio = recuperoDataInizio(); 

	        		String valoreDataFine = recuperoDataFine();
	        		
	        		String valoreFrequenzaSessione = recuperoFrequenzaSessione();
	        		
	        		controlloConsistenzaDateFrequenzaSessione(valoreFrequenzaSessione);
	        		
			    	controller.richiestaCreaCorso(valoreTipoDiCorso,valoreDataInizio,valoreDataFine,valoreFrequenzaSessione);
			    	
		    		messaggioInfoPopUp("Corso creato con successo !");
		    		
		    		controller.backToFinestraSceltaAggiungi(FinestraAggiungiCorso.this);

		    		/* Blocco eccezioni campi vuoti */
		    	} catch(ExceptionCampoTipoCorsoVuoto err) {
		    		messaggioWarningPopUp(err.getmessaggioWarningSchermo());
		    	} catch(ExceptionCampoDataDiInizioVuoto err) {
		    		messaggioWarningPopUp(err.getmessaggioWarningSchermo());
		    	} catch(ExceptionCampoDataDiFineVuoto err) {
		    		messaggioWarningPopUp(err.getmessaggioWarningSchermo());
		    	} catch(ExceptionCampoFrequenzaSessioneVuoto err) {
		    		messaggioWarningPopUp(err.getmessaggioWarningSchermo());
		    	
		    		/* Blocco eccezioni logiche */
		    	} catch(ExceptionDataInizioMaggioreDataFine err) {
		    		messaggioWarningPopUp(err.getmessaggioWarningSchermo());
		    	} catch(ExceptionFrequenzaSessioneSettimanale err) {
		    		messaggioWarningPopUp(err.getmessaggioWarningSchermo());
		    	} catch(ExceptionFrequenzaSessioneMensile err) {
		    		messaggioWarningPopUp(err.getmessaggioWarningSchermo());
		    		
		    		/* Blocco eccezioni database*/
		    	} catch(DBExceptionDataInizioMaggioreDataFine err) {
		    		messaggioWarningPopUp(err.getmessaggioWarningSchermo());	
		    	} catch(DBExceptionOperazioneQueryDML err) {
		    		messaggioErrorPopUp(err.getMessaggioErroreSchermo());
		    	} catch(DBExceptionRisultatoIndefinito err) {
		    		messaggioErrorPopUp(err.getMessaggioErrorSchermo());
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
		
		JLabel lblTitolo = new JLabel("Inserisci i seguenti dati per aggiungere un corso");
		lblTitolo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitolo.setForeground(getColorePrincipale());
		lblTitolo.setFont(getFontTitoloPrincipale());
		lblTitolo.setBounds(151, 197, 622, 26);
		panel.add(lblTitolo);
        
		
        
		
		// Per centrare la finestra ogni volta al centro dello schermo
		setLocationRelativeTo(null);
	}
	
	private void setListaTipiDiCorso() {
		 tipiDiCorso = controller.getDescrizioniTipiDiCorso();
		 modelTipoDiCorso = new DefaultComboBoxModel<>(tipiDiCorso);
		 listaTipoDiCorso.setModel(modelTipoDiCorso);
		 listaTipoDiCorso.setSelectedIndex(-1); 
	}
	
	private void setCampoDataInizioAndFine() {
		datePickerDataInizio.setDate(null);
		datePickerDataFine.setDate(null);
	}
	
	private void setCampoFrequenzaSessione() {
		frequenzeSessioni = controller.getDescrizioniFrequenzeSessioni();
		modelFrequenzaSessione = new DefaultComboBoxModel<>(frequenzeSessioni);
		listaFrequenzaSessione.setModel(modelFrequenzaSessione);
		listaFrequenzaSessione.setSelectedIndex(-1); 
	}
	
	public void setCampiInserimento() {
		setListaTipiDiCorso();
		setCampoDataInizioAndFine();
		setCampoFrequenzaSessione();
	}
	
	private void controlloInputCampi() throws ExceptionCampoTipoCorsoVuoto,ExceptionCampoDataDiInizioVuoto,
					ExceptionCampoDataDiFineVuoto,ExceptionCampoFrequenzaSessioneVuoto{
		if(listaTipoDiCorso.getSelectedIndex()==-1) {
			throw new ExceptionCampoTipoCorsoVuoto();
		}
		
		if(datePickerDataInizio.getDate()==null) {
			throw new ExceptionCampoDataDiInizioVuoto();
		}
		
		if(datePickerDataFine.getDate()==null) {
			throw new ExceptionCampoDataDiFineVuoto();
		}
		
		if(listaFrequenzaSessione.getSelectedIndex()==-1) {
			throw new ExceptionCampoFrequenzaSessioneVuoto();
		}
		
		
	}
	
	private String recuperoTipoDiCorso() {
	    int indiceTipoDiCorso = listaTipoDiCorso.getSelectedIndex();;
	    String tipoDiCorsoSelezionato = tipiDiCorso[indiceTipoDiCorso];
	    return tipoDiCorsoSelezionato;
	}
	
	private String recuperoDataInizio() {
		String dataInizioInserita = datePickerDataInizio.getDateStringOrEmptyString();
		return dataInizioInserita; 
	}
	
	private String recuperoDataFine() {
		String dataFineInserita = datePickerDataFine.getDateStringOrEmptyString();
		return dataFineInserita; 
	}
	
	private String recuperoFrequenzaSessione() {
	    int indiceFrequenzaSessione = listaFrequenzaSessione.getSelectedIndex();;
	    String frequenzaSessioneSelezionato = frequenzeSessioni[indiceFrequenzaSessione];
	    return frequenzaSessioneSelezionato;
	}
	
	private void controlloConsistenzaDateInserite() throws ExceptionDataInizioMaggioreDataFine{
		LocalDate dataInizio = datePickerDataInizio.getDate();
		LocalDate dataFine = datePickerDataFine.getDate();
		
		if(dataInizio.isAfter(dataFine)) {
			throw new ExceptionDataInizioMaggioreDataFine();
		}

	}
	
	private void controlloConsistenzaDateFrequenzaSessione(String frequenzaSessione) 
			throws ExceptionFrequenzaSessioneSettimanale, ExceptionFrequenzaSessioneMensile{
		LocalDate dataInizio = datePickerDataInizio.getDate();
		LocalDate dataFine = datePickerDataFine.getDate();
		
        long differenzaGiorni = ChronoUnit.DAYS.between(dataInizio, dataFine);
        
        // Corso settimanale che inizia e finisce nello stesso giorno non consentito
		if(frequenzaSessione.equals("Settimanale")){
			if(differenzaGiorni%7!=0 || dataInizio.getDayOfYear() == dataFine.getDayOfYear()) {
				throw new ExceptionFrequenzaSessioneSettimanale();
			}
		}
		
        // Corso mensile che inizia e finisce nello stesso mese e quindi anche giorno non consentito
		if(frequenzaSessione.equals("Mensile")){
			if(dataInizio.getMonthValue()==dataFine.getMonthValue()) {
				throw new ExceptionFrequenzaSessioneMensile();
			}
		}
				
		
	} 

}
