package GUI;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import Controller.Controller;
import DatabaseException.DBExceptionRicetteNonTrovate;
import DatabaseException.DBExceptionRisultatoIndefinito;

public class FinestraVisualizzaRicetteSessionePratica extends FinestraTemplate {

    private Controller controller;
    
	private JPanel contentPane;
    private JPanel panel;
    private JButton btnBackToSessioniPratiche;
    private JSeparator separator;
    private JLabel lblLogo;
    private JTable tableRicette;
    private DefaultTableModel modelloTabellaRicette;
    private JScrollPane scrollPane;
    private JTableHeader tableHeader;
  

	public FinestraVisualizzaRicetteSessionePratica(Controller c) {
		controller = c;

		setTitle("UninaFoodLab - Visualizza ricette della sessione pratica");
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(FinestraVisualizzaRicetteSessionePratica.class.getResource("/img/logo_ritagliato.jpg")));		
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
        
        btnBackToSessioniPratiche = new JButton("Torna indietro");
        
        btnBackToSessioniPratiche.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
                controller.backToFinestraVisualizzaSessioniPratiche(); 
        	}
        });        
        
        btnBackToSessioniPratiche.setBackground(getColorePrincipale());
        btnBackToSessioniPratiche.setForeground(Color.WHITE);
        btnBackToSessioniPratiche.setFont(getFontBottone());
        btnBackToSessioniPratiche.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnBackToSessioniPratiche.setFocusPainted(false);
        btnBackToSessioniPratiche.setBorderPainted(false);
        btnBackToSessioniPratiche.setOpaque(true);
       
        btnBackToSessioniPratiche.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
            	btnBackToSessioniPratiche.setBackground(getColoreBottoneChiaro()); 	
            }
            @Override
            public void mouseExited(MouseEvent e) {
            	btnBackToSessioniPratiche.setBackground(getColorePrincipale()); 
            }
        });
        
        btnBackToSessioniPratiche.setBounds(89, 62, 145, 46);
        panel.add(btnBackToSessioniPratiche);
		
		separator = new JSeparator();
		separator.setBackground(getColorePrincipale());
		separator.setBounds(10, 183, 914, 15);
		panel.add(separator);
		
		lblLogo = new JLabel("");
		lblLogo.setHorizontalAlignment(SwingConstants.CENTER);
		lblLogo.setIcon(getLogoVisualizzato());
		lblLogo.setBounds(372, 0, 180, 180);
		panel.add(lblLogo);
		
        modelloTabellaRicette = new DefaultTableModel(
                new Object[][] {},
                new String[] { "Ricetta n°","Nome ricetta","Descrizione", "Grado di difficoltà" }
            ) {
                @Override
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            };
            
        tableRicette = new JTable(modelloTabellaRicette) {
        	
		private String wrapTextByLength(String testoDaFormattare, int numeroMassimoLunghezzaRiga) {
	    String[] words = testoDaFormattare.split(" ");
	    
	    // StringBuilder per costruire la nuova stringa formattata
	    StringBuilder sb = new StringBuilder();
	    
	    int contatoreCaratteriLinea = 0;

	    // Itera su ogni parola del testo passato in input
	    for (String word : words) {
	        // Controlla se l'aggiunta della nuova parola supererebbe la lunghezza massima della riga.
	        if (contatoreCaratteriLinea > 0 && contatoreCaratteriLinea + word.length() + 1 > numeroMassimoLunghezzaRiga) {
	            // Se sì, aggiungi un'interruzione di riga e resetta il contatore
	            sb.append("<br>");
	            contatoreCaratteriLinea = 0;
	        }
	        
	        // Aggiungi la parola e uno spazio per creare il testo formattato
	        sb.append(word).append(" ");
	        
	  
	        contatoreCaratteriLinea += word.length() + 1; 
	    }
	    
	    // Rimuovi lo spazio finale e aggiungi i tag HTML per leggere i tag <br>
	    return "<html>" + sb.toString().trim() + "</html>";
		}	
        
    	@Override
    	public String getToolTipText(java.awt.event.MouseEvent e) {
        java.awt.Point p = e.getPoint();
        // Individuo la posizione del cursore
        int riga = rowAtPoint(p);
        int colonna = columnAtPoint(p);

        // Ci assicuriamo che il cursore sia sulla colonna della descrizione della ricetta
        if (riga != -1 && colonna != -1 && colonna == 2) {
        	String testoOriginale = getValueAt(riga, colonna).toString();
            
            return wrapTextByLength(testoOriginale, 40);
        }
        return null;
        
        }
    	
        };

        
        tableRicette.getColumnModel().getColumn(0).setPreferredWidth(25);
        tableRicette.getColumnModel().getColumn(1).setPreferredWidth(150);
        tableRicette.getColumnModel().getColumn(2).setPreferredWidth(200);
        tableRicette.getColumnModel().getColumn(3).setPreferredWidth(50);
    
        
        tableRicette.setFont(getFontCelle());
        tableRicette.setRowHeight(30);

        // Centro il contenuto di ogni cella della tabella
        for (int i = 0; i < tableRicette.getColumnModel().getColumnCount(); i++) {
        	tableRicette.getColumnModel().getColumn(i).setCellRenderer(getcenterRenderer());
        }
        
        // Evita il trascinamento del mouse e il selezionamento multiplo delle righe
        tableRicette.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        
        // Blocca lo spostamento e il ridimensionamento delle colonne
        tableHeader = tableRicette.getTableHeader();
        tableHeader.setReorderingAllowed(false); 
        tableHeader.setResizingAllowed(false);
        tableHeader.setFont(getFontHeader());

        
        // ScrollPane
        scrollPane = new JScrollPane();
        scrollPane.setBounds(20, 200, 890, 345);
        panel.add(scrollPane);
        scrollPane.setViewportView(tableRicette);
        
        
        JButton btnGoToMenuPrincipale = new JButton("Menu principale");
        btnGoToMenuPrincipale.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
                controller.gotoMenuPrincipale(FinestraVisualizzaRicetteSessionePratica.this); 
        	}
        });
        
        btnGoToMenuPrincipale.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseEntered(MouseEvent e) {
        		btnGoToMenuPrincipale.setBackground(getColoreBottoneChiaro()); 	
        	}
        	@Override
        	public void mouseExited(MouseEvent e) {
        		btnGoToMenuPrincipale.setBackground(getColorePrincipale()); 
        	}
        });
                
        btnGoToMenuPrincipale.setBackground(getColorePrincipale());
        btnGoToMenuPrincipale.setForeground(Color.WHITE);
        btnGoToMenuPrincipale.setFont(getFontBottone());
        btnGoToMenuPrincipale.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnGoToMenuPrincipale.setFocusPainted(false);
        btnGoToMenuPrincipale.setBorderPainted(false);
        btnGoToMenuPrincipale.setOpaque(true);
        
        btnGoToMenuPrincipale.setBounds(700, 62, 145, 46);
        panel.add(btnGoToMenuPrincipale);
        
		// Per centrare la finestra ogni volta al centro dello schermo
		setLocationRelativeTo(null);
	}
	
	public void aggiungiRigaTabella(Object[] riga) {
        modelloTabellaRicette.addRow(riga);
    }

    public void svuotaTabella() {
        modelloTabellaRicette.setRowCount(0);
    }
    
    public void aggiungiTupla(int numeroRicetta, String nomeRicetta, String descrizioneRicetta, Object gradoDiDiffcioltà) {
    	modelloTabellaRicette.addRow (new Object [] { numeroRicetta,nomeRicetta,descrizioneRicetta,gradoDiDiffcioltà});
	}
    
    public void richiestaVisualizzaRicette() {
    	try {
			controller.richiestaConfermataVisualizzaRicette();
		} catch (DBExceptionRicetteNonTrovate e) {
			svuotaTabella();
			messaggioWarningPopUp(e.getMessaggioWarningSchermo());	
            controller.backToFinestraVisualizzaSessioniPratiche(); 
		} catch (DBExceptionRisultatoIndefinito e) {
			messaggioErrorPopUp(e.getMessaggioErrorSchermo());

		}
    }
}
