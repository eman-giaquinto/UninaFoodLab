package GUI;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class FinestraTemplate extends JFrame {

    // Colori
    protected final Color COLORE_SFONDO = new Color(245, 238, 223);
    protected final Color COLORE_PRINCIPALE = new Color(3, 49, 86);
    protected final Color COLORE_PRINCIPALE_SCURO = new Color(23, 65, 94);
    
    // Font
    protected final Font FONT_BOTTONE = new Font("SansSerif", Font.BOLD, 13);
    
	// Metodi
    protected void messaggioInfoPopUp(String testo) {
    	JOptionPane.showMessageDialog(this,testo,"Info",JOptionPane.INFORMATION_MESSAGE);
	}
    
    protected void messaggioWarningPopUp(String testo) {
    	JOptionPane.showMessageDialog(this,testo,"Attenzione",JOptionPane.WARNING_MESSAGE);
	}
    
    protected void messaggioErrorPopUp(String testo) {
    	JOptionPane.showMessageDialog(this,testo,"Errore",JOptionPane.ERROR_MESSAGE);
	}
    
	public FinestraTemplate() {}

}
