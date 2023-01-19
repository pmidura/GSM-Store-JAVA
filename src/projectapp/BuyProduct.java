package projectapp;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.JTable;

/**
 *
 * @author Midura Patryk
 */

public class BuyProduct {
    
    String productName;
    String productQnty;
    ConnectDB CDB = new ConnectDB();
    ResultSet rs;
    ResultSetMetaData rsm;

    //Form
    JTextField ID_Produktu, Product_Nazwa, ProduktCena;
    JPanel Content, ID, Name, Price, Quantity, choosePanel, Button;
    JScrollPane scroll1, scroll2;
    JSpinner Qnty;
    JLabel productID, productNazwa, productPrice, productQuantity, blank1, blank2, blank3;
    JButton add, remove, checkout;
    int valQnty;
    int Stock;
    int StockUpdate;
    String productid;
    JFrame FrameProduct;
    
    //Product Table
    Vector<Object> Table_Row;
	
    //Table Transaction
    Vector<Object> Transaction;
    DefaultTableModel dtm, dtm2;
	
    public void BuyProduct() {
	FrameProduct = new JFrame();
	FrameProduct.setSize(1000, 600);
	FrameProduct.setLocationRelativeTo(null);
	FrameProduct.setLayout(new BorderLayout());
	FrameProduct.setTitle("Kup produkt");
        
	//Header Table
	Vector<String> columnNames = new Vector<>();
	columnNames.add("ID_Produktu");
	columnNames.add("Nazwa");
	columnNames.add("Cena (PLN)");
	columnNames.add("Stan na magazynie");
	dtm = new DefaultTableModel(columnNames, 0);
		
	//Display Table
	try {
            CDB.ConnectDB();
            String query = "SELECT ID_Produktu, Nazwa, Cena, Stan_magazyn FROM produkty";
            ResultSet rs = CDB.stmt.executeQuery(query);
            rsm = rs.getMetaData();
            
            while(rs.next()){
		Table_Row = new Vector<>();
		for (int i = 1; i <= rsm.getColumnCount(); i++) {
                    Table_Row.add(rs.getObject(i));	
		}
		dtm.addRow(Table_Row);
            }
	}
        catch(SQLException e) {
	}
        
	JTable table = new JTable(dtm);
	JScrollPane scrollpane = new JScrollPane(table);
	scrollpane.setPreferredSize(new Dimension(0, 350));
	Content = new JPanel(new GridLayout(1,3));

        //Form Layout
	choosePanel = new JPanel(new GridLayout(5,1));
		
	//ID
	ID = new JPanel(new FlowLayout());
	productID = new JLabel();
	productID.setText("ID Produktu: ");
	ID_Produktu = new JTextField();
	ID_Produktu.setPreferredSize(new Dimension(200, 25));
	ID_Produktu.setEnabled(false);
	ID.add(productID);
	ID.add(ID_Produktu);

        //Name
	Name = new JPanel(new FlowLayout());
	productNazwa = new JLabel();
	productNazwa.setText("Nazwa: ");
        blank1 = new JLabel();
        blank1.setText("        ");
	Product_Nazwa = new JTextField();
	Product_Nazwa.setPreferredSize(new Dimension(200, 25));
	Product_Nazwa.setEnabled(false);
	Name.add(productNazwa);
        Name.add(blank1);
	Name.add(Product_Nazwa);

	//Price
	Price = new JPanel(new FlowLayout());
	productPrice = new JLabel();
	productPrice.setText("Cena:  ");
        blank2 = new JLabel();
        blank2.setText("          ");
	ProduktCena = new JTextField();
	ProduktCena.setPreferredSize(new Dimension(200, 25));
	ProduktCena.setEnabled(false);
	Price.add(productPrice);
        Price.add(blank2);
	Price.add(ProduktCena);

	//Quantity
	Quantity = new JPanel(new FlowLayout());
	productQuantity = new JLabel();
	productQuantity.setText("Ilość: ");
        blank3 = new JLabel();
        blank3.setText("           ");
	Qnty = new JSpinner();
	Qnty.setPreferredSize(new Dimension(200, 25));
	Quantity.add(productQuantity);
        Quantity.add(blank3);
	Quantity.add(Qnty);

	//Add, Remove, Checkout
	Button = new JPanel(new FlowLayout()); 
	add = new JButton("Dodaj do koszyka");
	remove = new JButton("Usuń");
	remove.setEnabled(false);
	checkout = new JButton("Zamów");
	Button.add(add);
	Button.add(remove);
	Button.add(checkout);
	
        //Add to choose panel
        choosePanel.add(ID);
        choosePanel.add(Name);
        choosePanel.add(Price);
        choosePanel.add(Quantity);	
        choosePanel.add(Button);

        //Mouse Event
        table.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
		int i = table.getSelectedRow();
		ID_Produktu.setText(table.getValueAt(i, 0).toString());
		Product_Nazwa.setText(table.getValueAt(i, 1).toString());
		ProduktCena.setText(table.getValueAt(i, 2).toString());
            }
        });


        //Header Table
	Vector<String> headertable = new Vector<>();
	headertable.add("Nazwa");
	headertable.add("Ilość (szt)");
	dtm2 = new DefaultTableModel(headertable, 0);

	//Add To Cart
	add.addActionListener((java.awt.event.ActionEvent e) -> {
            try {
                Transaction = new Vector<>();
                productid = table.getValueAt(table.getSelectedRow(), 0).toString();
                valQnty = (int) Qnty.getValue();
                int i = table.getSelectedRow();
                Stock = (int) table.getValueAt(table.getSelectedRow(), 3);
                StockUpdate = Stock - valQnty;
                
                if(table.isRowSelected(i)) {
                    if(valQnty >= 1) {
                        if(valQnty <= Stock) {
                            productName = Product_Nazwa.getText();
                            productQnty = Qnty.getValue().toString();
                            Transaction.add(productName);
                            Transaction.add(productQnty);
                            dtm2.addRow(Transaction);
                        }
                        else {
                            JOptionPane.showMessageDialog(null, "Ilość przekracza dostępne zapasy");
                        }
                    }
                    else {
                        JOptionPane.showMessageDialog(null, "Minimalna ilość sztuk wynosi 1");
                    }
                }
                else {
                    JOptionPane.showMessageDialog(null, "Wybierz produkt");
                }
            }
            catch(HeadlessException e1) {
            }
        });
        
	JTable tableproduct = new JTable(dtm2);
	JScrollPane scrollpane2 = new JScrollPane(tableproduct);
	scrollpane2.setPreferredSize(new Dimension(0, 350));
	
	tableproduct.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
		remove.setEnabled(true);
            }
	});
		
	//Remove Cart
	remove.addActionListener((java.awt.event.ActionEvent e) -> {
            try {
                dtm2.removeRow(tableproduct.getSelectedRow());
                remove.setEnabled(false);
            }
            catch(Exception e1) {
            }
        });
	
	//Checkout
	checkout.addActionListener((java.awt.event.ActionEvent e) -> {
            CDB.ConnectDB();
            dtm.setRowCount(0);
            try {
                String query = "SELECT ID_Produktu, Nazwa, Cena, Stan_magazyn FROM produkty";
                ResultSet rs1 = CDB.stmt.executeQuery(query);
                rsm = rs1.getMetaData();
                while (rs1.next()) {
                    Table_Row = new Vector<>();
                    for (int i = 1; i <= rsm.getColumnCount(); i++) {
                        Table_Row.add(rs1.getObject(i));
                    }
                    dtm.addRow(Table_Row);
                }
            }
            catch(SQLException e1) {
            }
        });
        
	Content.add(choosePanel);
	Content.add(scrollpane2);
        FrameProduct.add(scrollpane, "North");
        FrameProduct.add(Content, "Center");
	FrameProduct.setVisible(true);
		
    }

}

