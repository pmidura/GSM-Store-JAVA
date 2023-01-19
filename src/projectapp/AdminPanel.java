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

public class AdminPanel {
    
    JFrame AdminFrame;
    Container Content;
    JMenuBar MenuBar;
    JMenu AccountMenu, ManageMenu, TransactionMenu;
    JMenuItem LogoffItem, ExitItem, UserItem, ProductItem, ViewTransactionItem;
    JLabel GamBar;
    Image img = new ImageIcon(this.getClass().getResource("/img/phone.jpg")).getImage();
    
    public void AdminPanel() {
        
        AdminFrame = new JFrame(); 
	AdminFrame.setSize(1200, 700);
	AdminFrame.setLocationRelativeTo(null);
	AdminFrame.setTitle("Panel Administratora");
	Content = AdminFrame.getContentPane();
		
	//Menu Bar
	MenuBar = new JMenuBar();
			
	//Account Menu
	AccountMenu = new JMenu("Konto");
	LogoffItem = new JMenuItem("Wyloguj się");
	ExitItem = new JMenuItem("Wyjdź"); 			
	AccountMenu.add(LogoffItem);				
	AccountMenu.add(ExitItem);

	//Manage Menu
	ManageMenu = new JMenu("Zarządzaj");
	UserItem = new JMenuItem("Użytkownik");
	ProductItem = new JMenuItem("Produkt"); 	
	ManageMenu.add(UserItem);
	ManageMenu.add(ProductItem);
			
	//Transaction Menu
	TransactionMenu = new JMenu("Transakcje");
	ViewTransactionItem = new JMenuItem("Pokaż Transakcje"); 	
	TransactionMenu.add(ViewTransactionItem);
		
        //Add To Menu Bar
	MenuBar.add(AccountMenu);
	MenuBar.add(ManageMenu);
	MenuBar.add(TransactionMenu);
		
	//Gam Bar
	GamBar = new JLabel();
        GamBar.setIcon(new ImageIcon(img));
	GamBar.setBounds(0, 0, 1200,700);
	AdminFrame.getContentPane().add(GamBar);		
	AdminFrame.add(GamBar);
	AdminFrame.setJMenuBar(MenuBar);
	AdminFrame.setVisible(true);	
        
    }
    
}

