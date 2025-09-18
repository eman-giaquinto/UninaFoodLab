package GUI;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.DefaultListSelectionModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import Controller.Controller;
import DTO.Ingrediente;
import DatabaseException.DBExceptionRisultatoIndefinito;

import javax.swing.JScrollPane;

public class FinestraVisualizzaIngredientiRicetta extends FinestraTemplate {
	
	private Controller controller;
	
	private JPanel contentPane;
	private JPanel panel;
	private JSeparator separator;
    private JScrollPane scrollPane;
    private JList<String> elencoIngredienti;
    private DefaultListModel<String> listModelElencoIngredienti;

	
	private ArrayList<String> listaIngredienti;



	public FinestraVisualizzaIngredientiRicetta(Controller c) {
		controller = c;
		
		setTitle("UninaFoodLab - Ingredienti della ricetta selezionata");
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(FinestraVisualizzaIngredientiRicetta.class.getResource("/img/logo_ritagliato.jpg")));		
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
		
        JLabel lblTitolo = new JLabel("Lista ingredienti");
        lblTitolo.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitolo.setForeground(getColorePrincipale());
        lblTitolo.setFont(getFontTitoloPrincipale());
        lblTitolo.setBounds(55, 205, 475, 26);
        panel.add(lblTitolo);
                
        elencoIngredienti = new JList<>();
        elencoIngredienti.setFont(getFontInserimento());
        
        elencoIngredienti.setFocusable(false);


        elencoIngredienti.setSelectionModel(new DefaultListSelectionModel() {
         @Override
         public void setSelectionInterval(int index0, int index1) {
             // Non fare nulla
         }

         @Override
         public void addSelectionInterval(int index0, int index1) {
             // Non fare nulla
         }
        });
 	   
        scrollPane = new JScrollPane(elencoIngredienti);
        
        scrollPane.setBounds(144, 254, 300, 175);
        panel.add(scrollPane);
        
        
		// Per centrare la finestra ogni volta al centro dello schermo
		setLocationRelativeTo(null);
		
	}
	
	public void richiestaVisualizzaIngredienti() {
		try {
			controller.richiestaVisualizzaIngredientiSchermo();
			listaIngredienti = controller.impostaIngredientiRicetta();
		} catch (DBExceptionRisultatoIndefinito e) {
			messaggioErrorPopUp(e.getMessaggioErrorSchermo());
		}
		
        listModelElencoIngredienti = new DefaultListModel<>();

		// Aggiungo gli elementi al model per la lista di ingredienti
		for (int i = 0; i < listaIngredienti.size(); i++) {
		    listModelElencoIngredienti.addElement((i+1)+". " + listaIngredienti.get(i));
		}
				
		elencoIngredienti.setModel(listModelElencoIngredienti);
				
		JScrollBar verticalScrollBar = scrollPane.getVerticalScrollBar();
		verticalScrollBar.setValue(verticalScrollBar.getMinimum());
	}
}
