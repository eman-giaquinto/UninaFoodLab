package GUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableCellRenderer;

public class FinestraTemplate extends JFrame {

	/* Attributi */
	
    // Colori
    private final static Color COLORE_SFONDO = new Color(245, 238, 223);
    private final static Color COLORE_PRINCIPALE = new Color(3, 49, 86);
    private final static Color COLORE_SECONDARIO = new Color(23, 65, 94);
    private final static Color COLORE_BOTTONE_CHIARO = new Color(30, 129, 176);
    
    // Font
    private final static Font FONT_BOTTONE = new Font("SansSerif", Font.BOLD, 13);
    private final static Font FONT_BOTTONE_MENU = new Font("Tahoma", Font.BOLD, 19);
    private final static Font FONT_HEADER = new Font("SansSerif", Font.BOLD, 15); 
    private final static Font FONT_CELLE = new Font("SansSerif", Font.PLAIN, 15);
    private final static Font FONT_TITOLO_PRINCIPALE = new Font("Verdana", Font.BOLD | Font.ITALIC, 20);
    private final static Font FONT_TITOLO_SECONDARIO = new Font("Sans Serif Collection", Font.BOLD, 21);
    private final static Font FONT_INSERIMENTO = new Font("Tahoma", Font.PLAIN, 18);


    // font font_casella_username_and_password = new font new Font("Tahoma", Font.PLAIN, 20) | da vedere se implemetare o meno
    
    // Logo
    private ImageIcon logoIcon = new ImageIcon(FinestraTemplate.class.getResource("/img/logo_ritagliato.jpg"));
    private Image logoImage = logoIcon.getImage();
    private Image scaledLogo = logoImage.getScaledInstance(175, 175, Image.SCALE_SMOOTH);
    private ImageIcon logoVisualizzato = new ImageIcon(scaledLogo);
    
    // Utilità
    private DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
    
	/* Metodi */
    
    // Messaggi a schermo
    public void messaggioInfoPopUp(String testo) {
    	JOptionPane.showMessageDialog(this,testo,"Info",JOptionPane.INFORMATION_MESSAGE);
	}
    
    public void messaggioWarningPopUp(String testo) {
    	JOptionPane.showMessageDialog(this,testo,"Attenzione",JOptionPane.WARNING_MESSAGE);
	}
    
    public void messaggioErrorPopUp(String testo) {
    	JOptionPane.showMessageDialog(this,testo,"Errore",JOptionPane.ERROR_MESSAGE);
	}
    
    public int messaggioSceltaPopUp(String messaggioSchermo) {
        String[] opzioni = {"Sì", "No"};
    	 int scelta = JOptionPane.showOptionDialog(
                 this,                                     
                 messaggioSchermo, 
                 "Scegli un opzione",                         
                 JOptionPane.YES_NO_OPTION,                 
                 JOptionPane.QUESTION_MESSAGE,              
                 null,                                     
                 opzioni,                                  
                 null                                       
         );
    	return scelta; 
    }
    
    // Logo
    protected ImageIcon getLogoVisualizzato() {
        return logoVisualizzato;
    }
    
    // Colori
    protected Color getColoreSfondo() {
    	return COLORE_SFONDO;
    }
    
    protected Color getColorePrincipale() {
    	return COLORE_PRINCIPALE;
    }
    
    protected Color getColoreSecondario() {
    	return COLORE_SECONDARIO;
    }
    
    protected Color getColoreBottoneChiaro() {
    	return COLORE_BOTTONE_CHIARO;
    }
    
    // Font    
    protected Font getFontBottone() {
    	return FONT_BOTTONE;
    }
    
    protected Font getFontBottoneMenu() {
    	return FONT_BOTTONE_MENU;
    }  
    
	protected Font getFontHeader() {
		return FONT_HEADER;
	}

	protected Font getFontCelle() {
		return FONT_CELLE;
	}
	
	protected Font getFontTitoloPrincipale() {
		return FONT_TITOLO_PRINCIPALE;
	}

	protected Font getFontTitoloSecondario() {
		return FONT_TITOLO_SECONDARIO;
	}
	
	protected Font getFontInserimento() {
		return FONT_INSERIMENTO;
	}
	
	// Utilità
	protected DefaultTableCellRenderer getcenterRenderer() {
		return centerRenderer;
	}
	
	public FinestraTemplate() {
	    centerRenderer.setHorizontalAlignment( JLabel.CENTER );	
	}
}
