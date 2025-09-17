package GUI;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
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
import DatabaseException.DBExceptionCorsiNonTrovati;
import DatabaseException.DBExceptionRisultatoIndefinito;
import DatabaseException.DBExceptionSessioniPraticheNonTrovate;

public class FinestraSelezionaSessionePratica extends FinestraTemplate {

    private Controller controller;
    
	private JPanel contentPane;
    private JPanel panel;
    private JButton btnBackToSelezionaCorso;
    private JSeparator separator;
    private JLabel lblLogo;
    private JTable tableSessioniPratiche;
    private DefaultTableModel modelloTabellaSessionePratica;
    private JScrollPane scrollPane;
    private JTableHeader tableHeader;
    
    
    //oggetti utili
    String[] tipiDiCorso;
    int indiceFiltro;
    String filtroSelezionato;

	public FinestraSelezionaSessionePratica(Controller c) {
		controller = c;

		setTitle("UninaFoodLab - Seleziona una sessione pratica");
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(FinestraSelezionaSessionePratica.class.getResource("/img/logo_ritagliato.jpg")));		
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
        
        btnBackToSelezionaCorso = new JButton("Torna ai corsi");
       
        btnBackToSelezionaCorso.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
                controller.backToSelezionaCorso(FinestraSelezionaSessionePratica.this); 
        	}
        });
        
        btnBackToSelezionaCorso.setBackground(getColorePrincipale());
        btnBackToSelezionaCorso.setForeground(Color.WHITE);
        btnBackToSelezionaCorso.setFont(getFontBottone());
        btnBackToSelezionaCorso.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnBackToSelezionaCorso.setFocusPainted(false);
        btnBackToSelezionaCorso.setBorderPainted(false);
        btnBackToSelezionaCorso.setOpaque(true);
       
        btnBackToSelezionaCorso.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
            	btnBackToSelezionaCorso.setBackground(getColoreBottoneChiaro()); 	
            }
            @Override
            public void mouseExited(MouseEvent e) {
            	btnBackToSelezionaCorso.setBackground(getColorePrincipale()); 
            }
        });
        
        btnBackToSelezionaCorso.setBounds(89, 62, 145, 46);
        panel.add(btnBackToSelezionaCorso);
		
		separator = new JSeparator();
		separator.setBackground(getColorePrincipale());
		separator.setBounds(10, 183, 914, 15);
		panel.add(separator);
		
		lblLogo = new JLabel("");
		lblLogo.setHorizontalAlignment(SwingConstants.CENTER);
		lblLogo.setIcon(getLogoVisualizzato());
		lblLogo.setBounds(372, 0, 180, 180);
		panel.add(lblLogo);
		
        modelloTabellaSessionePratica = new DefaultTableModel(
                new Object[][] {},
                new String[] { "idSessionePratica", "Sessione pratica n°", "Numero adesioni", "Data sessione", "Orario inizio", "Orario fine",}
                ) {
                @Override
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            };
            
        tableSessioniPratiche = new JTable(modelloTabellaSessionePratica);
        tableSessioniPratiche.setToolTipText("Doppio click per selezionare la sessione pratica");

        tableSessioniPratiche.addMouseListener(new MouseAdapter() {
        	@Override
            public void mouseClicked(MouseEvent e) {
                // Controlla se l'utente ha fatto un doppio click
                if (e.getClickCount() == 2) {
                    
                    int rigaSelezionata = tableSessioniPratiche.getSelectedRow();
                    
                    if (rigaSelezionata >= 0) {
                        
                    	int idSessione = (int) tableSessioniPratiche.getModel().getValueAt(rigaSelezionata, 0);
                        
                        controller.impostaSessionePraticaSelezionata(idSessione);
                        controller.showFinestraAggiungiRicettaSessionePratica();

                    }
                }
            }
        });
        
        tableSessioniPratiche.getColumnModel().getColumn(0).setPreferredWidth(1);
        tableSessioniPratiche.getColumnModel().getColumn(1).setPreferredWidth(120);
        tableSessioniPratiche.getColumnModel().getColumn(2).setPreferredWidth(100);
        tableSessioniPratiche.getColumnModel().getColumn(3).setPreferredWidth(120);
        tableSessioniPratiche.getColumnModel().getColumn(4).setPreferredWidth(100);
        tableSessioniPratiche.getColumnModel().getColumn(5).setPreferredWidth(100);
        
        tableSessioniPratiche.removeColumn(tableSessioniPratiche.getColumnModel().getColumn(0)); // Nascondi colonna idSessionePratica
        
        tableSessioniPratiche.setFont(getFontCelle());
        tableSessioniPratiche.setRowHeight(30);

        for (int i = 0; i < tableSessioniPratiche.getColumnModel().getColumnCount(); i++) {
        	tableSessioniPratiche.getColumnModel().getColumn(i).setCellRenderer(getcenterRenderer());
        }
        
        // Evita il trascinamento del mouse e il selezionamento multiplo delle righe
        tableSessioniPratiche.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        
        // Blocca lo spostamento e il ridimensionamento delle colonne
        tableHeader = tableSessioniPratiche.getTableHeader();
        tableHeader.setReorderingAllowed(false); 
        tableHeader.setResizingAllowed(false);
        tableHeader.setFont(getFontHeader());

        
        // ScrollPane
        scrollPane = new JScrollPane();
        scrollPane.setBounds(20, 200, 890, 345);
        panel.add(scrollPane);
        scrollPane.setViewportView(tableSessioniPratiche);
        
		// Per centrare la finestra ogni volta al centro dello schermo
		setLocationRelativeTo(null);
	}
	
	public void aggiungiRigaTabella(Object[] riga) {
        modelloTabellaSessionePratica.addRow(riga);
    }

    public void svuotaTabella() {
        modelloTabellaSessionePratica.setRowCount(0);
    }
    
    public void aggiungiTupla(int idSessionePratica, int numeroSessionePratica, int numeroAdesioni, String dataSessione, String orarioInizio, String orarioFine) {
        modelloTabellaSessionePratica.addRow(new Object[] { idSessionePratica, numeroSessionePratica, numeroAdesioni, dataSessione, orarioInizio, orarioFine });
    }
    
    public void richiestaVisualizzaSessioniPratiche() {
    	try {
			controller.richiestaSelezionaSessionePraticaSchermo();
		} catch (DBExceptionSessioniPraticheNonTrovate e) {
			svuotaTabella();
			messaggioWarningPopUp(e.getMessaggioWarningSchermo());
            controller.backToSelezionaCorso(FinestraSelezionaSessionePratica.this); 
		} catch (DBExceptionRisultatoIndefinito e) {
			messaggioErrorPopUp(e.getMessaggioErrorSchermo());

		}
    }
	
}