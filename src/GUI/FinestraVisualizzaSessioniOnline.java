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
import DatabaseException.DBExceptionRisultatoIndefinito;
import DatabaseException.DBExceptionSessioniOnlineNonTrovate;

public class FinestraVisualizzaSessioniOnline extends FinestraTemplate {

    private Controller controller;
    
	private JPanel contentPane;
    private JPanel panel;
    private JButton btnBackToCorsi;
    private JSeparator separator;
    private JLabel lblLogo;
    private JTable tableSessioniOnline;
    private DefaultTableModel modelloTabellaSessioneOnline;
    private JScrollPane scrollPane;
    private JTableHeader tableHeader;
  

	public FinestraVisualizzaSessioniOnline(Controller c) {
		controller = c;

		setTitle("UninaFoodLab - Visualizza sessioni online");
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(FinestraVisualizzaSessioniOnline.class.getResource("/img/logo_ritagliato.jpg")));		
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
        
        btnBackToCorsi = new JButton("Torna ai corsi");
        
        btnBackToCorsi.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
                controller.backToCorsiVisualizzati(FinestraVisualizzaSessioniOnline.this); 
        	}
        });        
        
        btnBackToCorsi.setBackground(getColorePrincipale());
        btnBackToCorsi.setForeground(Color.WHITE);
        btnBackToCorsi.setFont(getFontBottone());
        btnBackToCorsi.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnBackToCorsi.setFocusPainted(false);
        btnBackToCorsi.setBorderPainted(false);
        btnBackToCorsi.setOpaque(true);
       
        btnBackToCorsi.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
            	btnBackToCorsi.setBackground(getColoreBottoneChiaro()); 	
            }
            @Override
            public void mouseExited(MouseEvent e) {
            	btnBackToCorsi.setBackground(getColorePrincipale()); 
            }
        });
        
        btnBackToCorsi.setBounds(89, 62, 145, 46);
        panel.add(btnBackToCorsi);
		
		separator = new JSeparator();
		separator.setBackground(getColorePrincipale());
		separator.setBounds(10, 183, 914, 15);
		panel.add(separator);
		
		lblLogo = new JLabel("");
		lblLogo.setHorizontalAlignment(SwingConstants.CENTER);
		lblLogo.setIcon(getLogoVisualizzato());
		lblLogo.setBounds(372, 0, 180, 180);
		panel.add(lblLogo);
		
        modelloTabellaSessioneOnline = new DefaultTableModel(
                new Object[][] {},
                new String[] { "idSessioneOnline", "Sessione online n°", "Piattaforma", "Data sessione", "Orario inizio", "Orario fine", "Link", "fkCorso" }
            ) {
                @Override
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            };
            
        tableSessioniOnline = new JTable(modelloTabellaSessioneOnline) {
        	
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

            // Ci assicuriamo che il cursore sia sulla colonna del link
            if (riga != -1 && colonna != -1 && colonna == 5) { 
            	String testoOriginale = getValueAt(riga, colonna).toString();
                
                // Usa la nuova funzione per andare a capo circa ogni 20 caratteri
                return wrapTextByLength(testoOriginale, 20);
            }
            return null;
            
            }
        	
        };

        
        tableSessioniOnline.getColumnModel().getColumn(0).setPreferredWidth(1);
        tableSessioniOnline.getColumnModel().getColumn(1).setPreferredWidth(75);
        tableSessioniOnline.getColumnModel().getColumn(2).setPreferredWidth(50);
        tableSessioniOnline.getColumnModel().getColumn(3).setPreferredWidth(50);
        tableSessioniOnline.getColumnModel().getColumn(4).setPreferredWidth(25);
        tableSessioniOnline.getColumnModel().getColumn(5).setPreferredWidth(25);
        tableSessioniOnline.getColumnModel().getColumn(5).setPreferredWidth(25);
        tableSessioniOnline.getColumnModel().getColumn(6).setPreferredWidth(125);
        tableSessioniOnline.getColumnModel().getColumn(7).setPreferredWidth(1);
        
        tableSessioniOnline.removeColumn(tableSessioniOnline.getColumnModel().getColumn(7));
        tableSessioniOnline.removeColumn(tableSessioniOnline.getColumnModel().getColumn(0)); 
        
        tableSessioniOnline.setFont(getFontCelle());
        tableSessioniOnline.setRowHeight(30);

        // Centro il contenuto di ogni cella della tabella
        for (int i = 0; i < tableSessioniOnline.getColumnModel().getColumnCount(); i++) {
        	tableSessioniOnline.getColumnModel().getColumn(i).setCellRenderer(getcenterRenderer());
        }
        
        // Evita il trascinamento del mouse e il selezionamento multiplo delle righe
        tableSessioniOnline.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        
        // Blocca lo spostamento e il ridimensionamento delle colonne
        tableHeader = tableSessioniOnline.getTableHeader();
        tableHeader.setReorderingAllowed(false); 
        tableHeader.setResizingAllowed(false);
        tableHeader.setFont(getFontHeader());

        
        // ScrollPane
        scrollPane = new JScrollPane();
        scrollPane.setBounds(20, 200, 890, 345);
        panel.add(scrollPane);
        scrollPane.setViewportView(tableSessioniOnline);
        
        JButton btnGoToMenuPrincipale = new JButton("Menu principale");
        btnGoToMenuPrincipale.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
                controller.gotoMenuPrincipale(FinestraVisualizzaSessioniOnline.this); 
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
        modelloTabellaSessioneOnline.addRow(riga);
    }

    public void svuotaTabella() {
        modelloTabellaSessioneOnline.setRowCount(0);
    }
    
    public void aggiungiTupla(int idSessioneOnline, int numeroSessioneOnline, String piattaforma, String dataSessione, String orarioInizio, String orarioFine, String link, int fkCorso) {
    	modelloTabellaSessioneOnline.addRow(new Object[] { idSessioneOnline, numeroSessioneOnline, piattaforma, dataSessione, orarioInizio, orarioFine, link, fkCorso });
    }
    
    public void richiestaVisualizzaSessioniOnline() {
    	try {
			controller.richiestaConfermataVisualizzaSessioniOnline();
		} catch (DBExceptionSessioniOnlineNonTrovate e) {
			svuotaTabella();
			messaggioWarningPopUp(e.getMessaggioWarningSchermo());	
            controller.backToCorsiVisualizzati(FinestraVisualizzaSessioniOnline.this); 
		} catch (DBExceptionRisultatoIndefinito e) {
			messaggioErrorPopUp(e.getMessaggioErrorSchermo());

		}
    }
}
