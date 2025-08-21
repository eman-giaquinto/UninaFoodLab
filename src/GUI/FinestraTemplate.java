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
    Color COLORE_SFONDO = new Color(245, 238, 223);
    Color COLORE_PRINCIPALE = new Color(3, 49, 86);
    Color COLORE_SECONDARIO = new Color(23, 65, 94);
    Color COLORE_BOTTONE_CHIARO = new Color(30, 129, 176);
    
    // Font
    Font FONT_BOTTONE = new Font("SansSerif", Font.BOLD, 13);
	Font FONT_BOTTONE_MENU = new Font("Tahoma", Font.BOLD, 19);
    Font FONT_HEADER = new Font("SansSerif", Font.BOLD, 15); 
    Font FONT_CELLE = new Font("SansSerif", Font.PLAIN, 15);

    // font font_casella_username_and_password = new font new Font("Tahoma", Font.PLAIN, 20) | da vedere se implemetare o meno
    
    // Logo
    ImageIcon logoIcon = new ImageIcon(FinestraTemplate.class.getResource("/img/logo_ritagliato.jpg"));
    Image logoImage = logoIcon.getImage();
    Image scaledLogo = logoImage.getScaledInstance(175, 175, Image.SCALE_SMOOTH);
    ImageIcon logoVisualizzato = new ImageIcon(scaledLogo);
    
    // Utilità
    DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
    
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

	// Utilità
	protected DefaultTableCellRenderer getcenterRenderer() {
		return centerRenderer;
	}
	
	public FinestraTemplate() {
	    centerRenderer.setHorizontalAlignment( JLabel.CENTER );	
	}
}
