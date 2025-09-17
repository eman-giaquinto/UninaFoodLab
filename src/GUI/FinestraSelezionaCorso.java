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

public class FinestraSelezionaCorso extends FinestraTemplate {

    private Controller controller;
    
	private JPanel contentPane;
    private JPanel panel;
    private JButton btnBackToMenuPrincipale;
    private JSeparator separator;
    private JLabel lblLogo;
    private JTable tableCorsi;
    private DefaultTableModel modelloTabella;
    private JScrollPane scrollPane;
    private JTableHeader tableHeader;
    
    private JComboBox<String> listaFiltri;
    private DefaultComboBoxModel<String> model;
    private JLabel lblFiltra;
    private JLabel lblFiltroImmagine;
    
    //oggetti utili
    String[] tipiDiCorso;
    int indiceFiltro;
    String filtroSelezionato;
    String modeAccesso;

	public FinestraSelezionaCorso(Controller c) {
		controller = c;

		setTitle("UninaFoodLab - Seleziona corso");
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(FinestraSelezionaCorso.class.getResource("/img/logo_ritagliato.jpg")));		
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
        
        btnBackToMenuPrincipale = new JButton("Torna indietro");
        btnBackToMenuPrincipale.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		controller.backToFinestraSceltaAggiungi(FinestraSelezionaCorso.this);
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
		
        modelloTabella = new DefaultTableModel(
                new Object[][] {},
                new String[] { "IdCorsoDB","Corso n°", "Tipo di corso", "Data di inizio", "Frequenza sessione", "Data di fine" }
            ) {
                @Override
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            };
            
        tableCorsi = new JTable(modelloTabella);
        tableCorsi.setToolTipText("Doppio click per visualizzare le sessioni del corso");

        tableCorsi.addMouseListener(new MouseAdapter() {
        	@Override
            public void mouseClicked(MouseEvent e) {
                // Controlla se l'utente ha fatto un doppio click
                if (e.getClickCount() == 2) {
                    
                    int rigaSelezionata = tableCorsi.getSelectedRow();
                    
                    if (rigaSelezionata >= 0) {
                        
                        int idCorso = (int) tableCorsi.getModel().getValueAt(rigaSelezionata, 0);
                        
                        controller.impostaCorsoSelezionato(idCorso);
                        
                        switch(modeAccesso){
	            			case "pratica":
	                        controller.showFinestraAggiungiSessionePratica();
	            			break;
	            			case "online":
		                    controller.showFinestraAggiungiSessioneOnline();
	            			break;
	            			case "ricetta":
			                controller.showFinestraSelezionaSessionePratica();
	            			break;
                        }
                       
                    }
                }
            }
        });
        
        // Nascondo la prima colonna che contiene l'id del corso
        tableCorsi.removeColumn(tableCorsi.getColumnModel().getColumn(0));

        tableCorsi.getColumnModel().getColumn(0).setPreferredWidth(75);
        tableCorsi.getColumnModel().getColumn(1).setPreferredWidth(130);
        tableCorsi.getColumnModel().getColumn(2).setPreferredWidth(100);
        tableCorsi.getColumnModel().getColumn(3).setPreferredWidth(130);
        tableCorsi.getColumnModel().getColumn(4).setPreferredWidth(100);
        
        tableCorsi.setFont(getFontCelle());
        tableCorsi.setRowHeight(30);

        for (int i = 0; i < tableCorsi.getColumnModel().getColumnCount(); i++) {
        	tableCorsi.getColumnModel().getColumn(i).setCellRenderer(getcenterRenderer());
        }
        
        // Evita il trascinamento del mouse e il selezionamento multiplo delle righe
        tableCorsi.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        
        // Blocca lo spostamento e il ridimensionamento delle colonne
        tableHeader = tableCorsi.getTableHeader();
        tableHeader.setReorderingAllowed(false); 
        tableHeader.setResizingAllowed(false);
        tableHeader.setFont(getFontHeader());

        
        // ScrollPane
        scrollPane = new JScrollPane();
        scrollPane.setBounds(20, 200, 890, 345);
        panel.add(scrollPane);
        scrollPane.setViewportView(tableCorsi);
        
        listaFiltri = new JComboBox();
        listaFiltri.addActionListener(new ActionListener() {
        	@Override
        	public void actionPerformed(ActionEvent e) {
                // Dopo che una selezione è stata fatta, ristabilisce il focus 
                panel.requestFocusInWindow();
            }
        });
    
        listaFiltri.setBackground(Color.WHITE); 
        listaFiltri.setForeground(Color.BLACK);
        listaFiltri.setCursor(new Cursor(Cursor.HAND_CURSOR));
        listaFiltri.setBounds(690, 101, 160, 22);
        panel.add(listaFiltri);
        
        lblFiltra = new JLabel("FILTRA");
        lblFiltra.setHorizontalAlignment(SwingConstants.CENTER);
        lblFiltra.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 21));
        lblFiltra.setForeground(getColorePrincipale());
        lblFiltra.setBounds(699, 38, 105, 22);
        panel.add(lblFiltra);
        
        lblFiltroImmagine = new JLabel("");
        lblFiltroImmagine.setHorizontalAlignment(SwingConstants.CENTER);
        
        ImageIcon filtroIcon = new ImageIcon(FinestraTemplate.class.getResource("/img/filtra-imbuto.png"));
        Image filtroImage = filtroIcon.getImage();
        Image scaledFiltro = filtroImage.getScaledInstance(25, 25, Image.SCALE_SMOOTH);
        ImageIcon filtroVisualizzato = new ImageIcon(scaledFiltro);
        
        lblFiltroImmagine.setIcon(filtroVisualizzato);
        lblFiltroImmagine.setBounds(809, 38, 25, 25);
        panel.add(lblFiltroImmagine);
                
        JLabel lblTipoDiCorsoFiltro = new JLabel("Tipo di corso");
        lblTipoDiCorsoFiltro.setHorizontalAlignment(SwingConstants.CENTER);
        lblTipoDiCorsoFiltro.setFont(new Font("SansSerif", Font.BOLD, 15));
        lblTipoDiCorsoFiltro.setBounds(710, 73, 112, 22);
        panel.add(lblTipoDiCorsoFiltro);
        
        JButton btnFiltra = new JButton("Filtra");
        btnFiltra.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		indiceFiltro = listaFiltri.getSelectedIndex();
   	         	filtroSelezionato=tipiDiCorso[indiceFiltro];
   	            richiestaSelezionaCorso(filtroSelezionato);      
   	        }
        });
        
        btnFiltra.setBackground(getColorePrincipale());
        btnFiltra.setForeground(Color.WHITE);
        btnFiltra.setFont(getFontBottone());
        btnFiltra.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnFiltra.setFocusPainted(false);
        btnFiltra.setBorderPainted(false);
        btnFiltra.setOpaque(true);
        
        btnFiltra.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
            	btnFiltra.setBackground(getColoreBottoneChiaro()); 	
            }
            @Override
            public void mouseExited(MouseEvent e) {
            	btnFiltra.setBackground(getColorePrincipale()); 
            }
        });
        
        btnFiltra.setBounds(778, 135, 89, 23);
        panel.add(btnFiltra);

        
        JButton btnResetFiltro = new JButton("Reset");
        
        btnResetFiltro.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
	      		if (listaFiltri.getItemCount() > 0) {
	    	         listaFiltri.setSelectedIndex(0);
	    	        }     
   	        }
        });

        btnResetFiltro.setBackground(getColorePrincipale());
        btnResetFiltro.setForeground(Color.WHITE);
        btnResetFiltro.setFont(getFontBottone());
        btnResetFiltro.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnResetFiltro.setFocusPainted(false);
        btnResetFiltro.setBorderPainted(false);
        btnResetFiltro.setOpaque(true);
        
        btnResetFiltro.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
            	btnResetFiltro.setBackground(getColoreBottoneChiaro()); 	
            }
            @Override
            public void mouseExited(MouseEvent e) {
            	btnResetFiltro.setBackground(getColorePrincipale()); 
            }
        });
        
        btnResetFiltro.setBounds(672, 135, 89, 23);
        panel.add(btnResetFiltro);
        
		// Per centrare la finestra ogni volta al centro dello schermo
		setLocationRelativeTo(null);
	}
	
	public void aggiungiRigaTabella(Object[] riga) {
        modelloTabella.addRow(riga);
    }

    public void svuotaTabella() {
        modelloTabella.setRowCount(0);
    }
    
    public void aggiungiTupla(int idCorso, int numeroRiga, Object tipodiCorso, 	String dataInizio, Object frequenzaSessione, String dataFine) {
		modelloTabella.addRow (new Object [] { idCorso,numeroRiga,tipodiCorso,dataInizio,frequenzaSessione,dataFine});
	}
    
    public void richiestaSelezionaCorso(String filtro) {
    	try {
			controller.richiestaSelezionaCorsoAggiungi(filtro);
		} catch (DBExceptionCorsiNonTrovati e) {
			svuotaTabella();
			if(filtro.equals("Tutti")) {
				messaggioWarningPopUp(e.getMessaggioWarningSchermo());
        		controller.backToFinestraSceltaAggiungi(FinestraSelezionaCorso.this);
			}
			else
			{
				messaggioWarningPopUp(e.getMessaggioWarningSchermo()+" con il filtro selezionato");
			}
		} catch (DBExceptionRisultatoIndefinito e) {
			messaggioErrorPopUp(e.getMessaggioErrorSchermo());
		}
    }

	public void setFiltriTipiDiCorso() {
		 tipiDiCorso = controller.impostaDescrizioniTipiDiCorso();
		 model = new DefaultComboBoxModel<>(tipiDiCorso);
		 listaFiltri.setModel(model);
	}
	
	public void setModalitàAccesso(String opzioneAccesso) {
	    modeAccesso=opzioneAccesso;
	}

}
