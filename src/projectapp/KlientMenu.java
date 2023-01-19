package projectapp;

import java.awt.Container;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

/**
 *
 * @author Midura Patryk
 */

public class KlientMenu {
    
    Container Content;
    JFrame UserFrame;
    JMenuBar MenuBar;
    JMenu AccountMenu, TransactionMenu;
    JMenuItem LogoffItem, ExitItem, BuyItem;
    JLabel GamBar;
    Image img = new ImageIcon(this.getClass().getResource("/img/phone.jpg")).getImage();
    
    public void KlientMenu() {
	UserFrame = new JFrame(); 
	UserFrame.setSize(1200, 700);
	UserFrame.setLocationRelativeTo(null);
	UserFrame.setTitle("Menu Klienta");
	Content = UserFrame.getContentPane();
        
	//Menu
	MenuBar = new JMenuBar();
        
	//Account Menu
	AccountMenu = new JMenu("Konto");
	LogoffItem = new JMenuItem("Wyloguj się");
	ExitItem = new JMenuItem("Wyjdź"); 		
	AccountMenu.add(LogoffItem);
	AccountMenu.add(ExitItem);
        
	//Transaction Menu
	TransactionMenu = new JMenu("Transakcje");
	BuyItem = new JMenuItem("Kup produkt"); 
	TransactionMenu.add(BuyItem);

        //Add to Menu Bar
	MenuBar.add(AccountMenu);
	MenuBar.add(TransactionMenu);
	UserFrame.setJMenuBar(MenuBar);
	
	//Gam Bar
        GamBar = new JLabel();
        GamBar.setIcon(new ImageIcon(img));
	GamBar.setBounds(0, 0, 1200,700);
	UserFrame.getContentPane().add(GamBar);
	UserFrame.add(GamBar);
	UserFrame.setVisible(true);			
    }
    
}

