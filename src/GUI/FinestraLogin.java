package GUI;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Controller.Controller;
import DatabaseException.DBExceptionPasswordErrata;
import DatabaseException.DBExceptionRisultatoIndefinito;
import DatabaseException.DBExceptionUsernameNonTrovato;
import Exception.ExcpetionCampoPasswordVuoto;
import Exception.ExcpetionCampoUsernameVuoto;

import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;

import java.awt.Image;
import java.awt.Toolkit;
import java.awt.Color;
import javax.swing.JTextField;
import java.awt.Cursor;
import java.awt.Font;

import javax.swing.JPasswordField;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class FinestraLogin extends FinestraTemplate {
	
	private Controller controller;

	private JPanel contentPane;
	private JTextField textFieldUsername;
	private JPasswordField passwordFieldPassword;
	private boolean passwordVisible = false;



	public FinestraLogin(Controller c) {
		controller = c;
		
		setTitle("UninaFoodLab - Login");
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(FinestraLogin.class.getResource("/img/logo_ritagliato.jpg")));		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 500);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(getColoreSfondo());
		panel.setBounds(0, 0, 584, 461);
		panel.setLayout(null);
		contentPane.add(panel);
		
		JLabel lblLogo = new JLabel("");
		lblLogo.setHorizontalAlignment(SwingConstants.CENTER);
		lblLogo.setIcon(getLogoVisualizzato());
		lblLogo.setBounds(194, 9, 200, 200);
		panel.add(lblLogo);
		
		JLabel lblUser = new JLabel("");
		lblUser.setIcon(new ImageIcon(FinestraLogin.class.getResource("/img/user.png")));
		lblUser.setHorizontalAlignment(SwingConstants.CENTER);
		lblUser.setBounds(120, 220, 50, 50);
		panel.add(lblUser);
		
		JLabel lblPassword = new JLabel("");
		lblPassword.setIcon(new ImageIcon(FinestraLogin.class.getResource("/img/locked.png")));
		lblPassword.setHorizontalAlignment(SwingConstants.CENTER);
		lblPassword.setBounds(120, 296, 50, 50);
		panel.add(lblPassword);
		
		textFieldUsername = new JTextField();
		textFieldUsername.setForeground(Color.GRAY);
		textFieldUsername.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if (textFieldUsername.getText().equals("Username")) {
					textFieldUsername.setText("");
					textFieldUsername.setForeground(Color.BLACK);
				}
			}
			@Override
			public void focusLost(FocusEvent e) {
				if (textFieldUsername.getText().isEmpty()) {
					textFieldUsername.setText("Username");
					textFieldUsername.setForeground(Color.GRAY);
				}
			}
		});
		textFieldUsername.setFont(new Font("Tahoma", Font.PLAIN, 20));
		textFieldUsername.setBounds(184, 220, 250, 50);
		textFieldUsername.setColumns(10);
		textFieldUsername.setText("Username");
		panel.add(textFieldUsername);
		
		passwordFieldPassword = new JPasswordField();
		passwordFieldPassword.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				String passwordInserita = String.valueOf(passwordFieldPassword.getPassword());
				if(passwordInserita.equals("Password")) 
				{
					passwordFieldPassword.setText("");
					passwordFieldPassword.setForeground(Color.BLACK);
					if (!passwordVisible) {
						passwordFieldPassword.setEchoChar('•');
					}	
				}
			}
			@Override
			public void focusLost(FocusEvent e) {
				String passwordInserita = String.valueOf(passwordFieldPassword.getPassword());
				if(passwordInserita.isEmpty()) 
				{
					passwordFieldPassword.setText("Password");
					passwordFieldPassword.setForeground(Color.GRAY);
					passwordFieldPassword.setEchoChar((char)0);
					
				}
			}
		});
		passwordFieldPassword.setForeground(Color.GRAY);
		passwordFieldPassword.setText("Password");
		passwordFieldPassword.setEchoChar((char)0);
		passwordFieldPassword.setFont(new Font("Tahoma", Font.PLAIN, 20));
		passwordFieldPassword.setBounds(184, 296, 250, 50);
		panel.add(passwordFieldPassword);
		
		JLabel lblShowPass = new JLabel("");
		lblShowPass.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String passwordInserita = String.valueOf(passwordFieldPassword.getPassword());

				if (passwordInserita.equals("Password")) {
					return;
				}
				
				passwordVisible = !passwordVisible;
			
				String imagePath = passwordVisible ? "/img/show_pass.png" : "/Img/not_show_pass.png";
				ImageIcon icon = new ImageIcon(FinestraLogin.class.getResource(imagePath));
				Image image = icon.getImage();
				Image scaledImage = image.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
				lblShowPass.setIcon(new ImageIcon(scaledImage));
				
				if(passwordVisible) {
					passwordFieldPassword.setEchoChar('\0');
				} else {
					passwordFieldPassword.setEchoChar('•');
				}	
			}
		});
		lblShowPass.setHorizontalAlignment(SwingConstants.CENTER);
		
		ImageIcon passIcon = new ImageIcon(FinestraLogin.class.getResource("/img/not_show_pass.png")); 
		Image passImage = passIcon.getImage();
		Image scaledpassIcon = passImage.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
		
		lblShowPass.setIcon(new ImageIcon(scaledpassIcon));
		lblShowPass.setBounds(444, 296, 50, 50);
		panel.add(lblShowPass);
		
		JButton btnAccedi = new JButton("ACCEDI");
		btnAccedi.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnAccedi.setBackground(getColoreBottoneChiaro()); 	
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnAccedi.setBackground(getColorePrincipale()); 
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				String usernameInserito = textFieldUsername.getText();
				String passwordInserita = String.valueOf(passwordFieldPassword.getPassword());
				
				try {
					controlloCampiVuoti(usernameInserito,passwordInserita);
					controller.accesso(usernameInserito,passwordInserita);

				} catch (ExcpetionCampoUsernameVuoto eUsernameVuoto) {
					messaggioWarningPopUp(eUsernameVuoto.getmessaggioWarningSchermo());
				} catch (ExcpetionCampoPasswordVuoto ePasswordVuota) {
					messaggioWarningPopUp(ePasswordVuota.getmessaggioWarningSchermo());
				} catch (DBExceptionPasswordErrata err) {
					messaggioWarningPopUp(err.getmessaggioWarningSchermo());
				} catch (DBExceptionUsernameNonTrovato err) {
					messaggioErrorPopUp(err.getmessaggioErrorSchermo());
				} catch (DBExceptionRisultatoIndefinito err) {
					messaggioErrorPopUp(err.getMessaggioErrorSchermo());
				}
				
			}
		});
		
		btnAccedi.setBackground(getColorePrincipale());
		btnAccedi.setForeground(Color.WHITE);
		btnAccedi.setFont(new Font("SansSerif", Font.BOLD, 15));
		btnAccedi.setFocusPainted(false);
		btnAccedi.setBorderPainted(false);
		btnAccedi.setOpaque(true);
				
		btnAccedi.setCursor(new Cursor(Cursor.HAND_CURSOR));
		
		btnAccedi.setBounds(246, 389, 106, 35);
		panel.add(btnAccedi);
		
	    
		// Per non far passare il controllo diretto al textfield username
		panel.setFocusable(true);
		panel.requestFocusInWindow();
		
		// Per centrare la finestra ogni volta al centro dello schermo
		setLocationRelativeTo(null);
		
	}
	
	private void controlloCampiVuoti(String username,String password) throws ExcpetionCampoUsernameVuoto, ExcpetionCampoPasswordVuoto {
		if(username.isBlank() || username.equals("Username")) 
			throw new ExcpetionCampoUsernameVuoto();
			
		if(password.isBlank() || password.equals("Password"))
			throw new ExcpetionCampoPasswordVuoto();	
	}
}
