package projectapp;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Vector;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Midura Patryk
 */

public class ManageProduct {
    
    ConnectDB CDB = new ConnectDB();	
    Vector<String> header = new Vector<>();
    Vector<Vector<Object>> data = new Vector<>();
    Vector<Object> Product;
    JFrame manageProduct;
    JTextField prID, prName;
    JButton insert, delete, update, submit, cancel;
    JPanel Content, ID, name, price, quantity, choosePanel, table2, bttn, insrt, dlt, updt, cncl, sbmt;
    JScrollPane scroll;
    JTable table;
    JLabel productID, productName, productPrice, productqty;
    JComboBox<String> yearList, MonthList, DateList, role;
    JPasswordField usrPass;
    JRadioButton Male, Female;
    String genderGet;
    ButtonGroup genderGroup;
    JSpinner qty, prPrice;
    String ProductID;
    ResultSetMetaData rsm;
    DefaultTableModel dtm;
    
    public void ManageProduct() {
        
        manageProduct = new JFrame();
	manageProduct.setSize(1000, 600);
	manageProduct.setLocationRelativeTo(null);
	manageProduct.setLayout(new BorderLayout());
        manageProduct.setTitle("Zarządzanie Produktem");
				
        //Table 1
	header = new Vector<>();
	header.add("ID Produktu");
	header.add("Nazwa");
	header.add("Cena (PLN)");
	header.add("Stan na magazynie");
	dtm = new DefaultTableModel(header, 0);
				
	//Table 2
	try {
            CDB.ConnectDB();
            String query = "SELECT ID_Produktu, Nazwa, Cena, Stan_magazyn FROM produkty";
            ResultSet rs = CDB.stmt.executeQuery(query);
            rsm = rs.getMetaData();
            
            while(rs.next()) {
                Product = new Vector<>();
                for(int i = 1; i <= rsm.getColumnCount(); i++) {
                    Product.add(rs.getObject(i));		
                }
		dtm.addRow(Product);
            }
	}
        catch (SQLException e) {
        }
				
	table = new JTable(dtm);
	scroll = new JScrollPane(table);
	scroll.setPreferredSize(new Dimension(0, 350));
        
	//Mouse Click Event
	table.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
						
                delete.setEnabled(true);
                update.setEnabled(true);

                int price = (int) table.getValueAt(table.getSelectedRow(), 2);
                int quantity = (int) table.getValueAt(table.getSelectedRow(), 3);

                int i = table.getSelectedRow();
                prID.setText(table.getValueAt(i, 0).toString());
                prName.setText(table.getValueAt(i, 1).toString());
                prPrice.setValue(price);
                qty.setValue(quantity);

                delete.addActionListener((java.awt.event.ActionEvent e1) -> {
                    try {
                        prID.setText(table.getValueAt(table.getSelectedRow(), 0).toString());
                        try {
                            String query = "DELETE FROM produkty WHERE ID_Produktu = ?";
                            PreparedStatement pst = CDB.conn.prepareStatement(query);
                            pst.setString(1, prID.getText());
                            pst.executeUpdate();
                        }
                        catch (SQLException e2) {
                        }
                        
                        JOptionPane.showMessageDialog(null, "Usunięto Pomyślnie");
                        dtm.setRowCount(0);
                        
                        try {
                            String query = "SELECT * FROM produkty";
                            ResultSet rs = CDB.stmt.executeQuery(query);
                            rsm = rs.getMetaData();
                            while (rs.next()) {
                                Product = new Vector<>();
                                for (int i1 = 1; i1 <= rsm.getColumnCount(); i1++) {
                                    Product.add(rs.getObject(i1));
                                }
                                dtm.addRow(Product);
                            }
                        }
                        catch (SQLException e3) {
                        }
                    }
                    catch (HeadlessException e4) {
                    }
                });
            }
	});

	Content = new JPanel(new GridLayout(1,3));

	choosePanel = new JPanel(new GridLayout(5,1));
			
	//ID
	ID = new JPanel(new FlowLayout());
	productID = new JLabel();
	productID.setText("ID Produktu :      ");
			
	prID = new JTextField();
	prID.setPreferredSize(new Dimension(200, 25));
	prID.setEnabled(false);
	
	ID.add(productID);
	ID.add(prID);

	//Name
	name = new JPanel(new FlowLayout());
	productName = new JLabel();
	productName.setText("Nazwa :               ");

	prName = new JTextField();
	prName.setPreferredSize(new Dimension(200, 25));
	prName.setEnabled(false);
	
	name.add(productName);
	name.add(prName);

	//Price
	price = new JPanel(new FlowLayout());
	productPrice = new JLabel();
	productPrice.setText("Cena (PLN) :      ");

	prPrice = new JSpinner();
	prPrice.setPreferredSize(new Dimension(200, 25));
	prPrice.setEnabled(false);
	
	price.add(productPrice);
	price.add(prPrice);

	//Quantity
	quantity = new JPanel(new FlowLayout());
	productqty = new JLabel();
	productqty.setText("Stan :                   ");

	qty = new JSpinner();
	qty.setPreferredSize(new Dimension(200, 25));
	qty.setEnabled(false);
	
	quantity.add(productqty);
	quantity.add(qty);

        //Add To Choose Panel
	choosePanel.add(ID);
	choosePanel.add(name);
	choosePanel.add(price);
	choosePanel.add(quantity);	

	//Button
	bttn = new JPanel(new GridLayout(5,1)); 
	insrt = new JPanel(new FlowLayout());
	dlt = new JPanel(new FlowLayout());
	updt = new JPanel(new FlowLayout());
	sbmt = new JPanel(new FlowLayout());
	cncl = new JPanel(new FlowLayout());
	
	insert = new JButton("Dodaj");
				
	delete = new JButton("Usuń");
	delete.setEnabled(false);
				
	update = new JButton("Zaktualizuj");
	update.setEnabled(false);
				
	submit = new JButton("Potwierdź");
	submit.setEnabled(false);
				
	cancel = new JButton("Wyjdź");
	cancel.setEnabled(false);
				
	insrt.add(insert);
	dlt.add(delete);
	updt.add(update);
	sbmt.add(submit);
	cncl.add(cancel);
				
	bttn.add(insrt);
	bttn.add(dlt);
	bttn.add(updt);
	bttn.add(sbmt);
	bttn.add(cncl);
				
        //Update Button
	update.addActionListener((java.awt.event.ActionEvent e) -> {
            submit.setEnabled(true);
            cancel.setEnabled(true);
            insert.setEnabled(false);
            delete.setEnabled(false);
            
            prName.setEnabled(true);
            prPrice.setEnabled(true);
            qty.setEnabled(true);
        });
        
	//Insert Button
	insert.addActionListener((java.awt.event.ActionEvent e) -> {
            prID.setText(null);
            prName.setText(null);
            prPrice.setValue(0);
            qty.setValue(0);
            
            prName.setEnabled(true);
            prPrice.setEnabled(true);
            qty.setEnabled(true);
            
            submit.setEnabled(true);
            cancel.setEnabled(true);
            update.setEnabled(false);
            delete.setEnabled(false);
            insert.setEnabled(false);
            
            CDB.ConnectDB();
            
            //Generate Product ID
            try {
                String getID = "SELECT ID_Produktu FROM produkty ORDER BY ID_Produktu DESC";
                ResultSet resultID = CDB.stmt.executeQuery(getID);
                
                if(resultID.next()) {
                    String lastID = resultID.getString("ID_Produktu");
                    Integer idNumber = Integer.parseInt(lastID.substring(2,5));
                    idNumber++;
                    ProductID = "PR" + String.format("%03d", idNumber);
                }
                else {
                    ProductID = "PR001";
                }
            }
            catch(SQLException e1) {
            }
        });
				
	//Cancel Button
	cancel.addActionListener((java.awt.event.ActionEvent e) -> {
            submit.setEnabled(false);
            cancel.setEnabled(false);
            update.setEnabled(false);
            delete.setEnabled(false);
            insert.setEnabled(true);
            
            prName.setEnabled(false);
            prPrice.setEnabled(false);
            qty.setEnabled(false);
        });
				
	//Submit Button
	submit.addActionListener((java.awt.event.ActionEvent e) -> {
            String getPrName = prName.getText();
            String getPrPrice = prPrice.getValue().toString();
            String getQty = qty.getValue().toString();
            int Qty = (int) qty.getValue();
            int priceProduct = (int) prPrice.getValue();
            
            if(getPrName.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Proszę podaj nazwę produktu");
            }
            else if(getPrPrice.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Proszę podaj cenę produktu");
            }
            else if(getQty.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Proszę podaj ilość");
            }
            
            if(Qty > 5) {
                if(priceProduct > 300) {
                    
                    //Insert Product
                    if(prID.getText().isEmpty()) {
                        try {
                            CDB.ConnectDB();
                            String query = "INSERT INTO produkty VALUES (?,?,?,?)";
                            PreparedStatement pst = CDB.conn.prepareStatement(query);
                            pst.setString(1, ProductID);
                            pst.setString(2, getPrName);
                            pst.setString(3, getPrPrice);
                            pst.setString(4, getQty);
                            pst.executeUpdate();
                            
                            JOptionPane.showMessageDialog(null, "Dodano Pomyślnie");
                        }
                        catch(SQLException e1) {
                        }
                    }
                    else {
                        //Update
                        try {
                            String query = "UPDATE produkty SET Nazwa = ?, Cena = ?, Stan_magazyn = ? WHERE ID_Produktu = ?";
                            PreparedStatement pst = CDB.conn.prepareStatement(query);
                            pst.setString(1, getPrName);
                            pst.setString(2, getPrPrice);
                            pst.setString(3, getQty);
                            pst.setString(4, prID.getText());
                            pst.executeUpdate();
                            
                            JOptionPane.showMessageDialog(null, "Zaktualizowano Pomyślnie");
                        }
                        catch(SQLException e1) {
                        }
                    }
                }
                else {
                    JOptionPane.showMessageDialog(null, "Cena musi być większa niż 300 PLN");
                }
            }
            else {
                JOptionPane.showMessageDialog(null, "Ilość musi być większa niż 5szt.");
            }
            
            submit.setEnabled(false);
            cancel.setEnabled(false);
            delete.setEnabled(false);
            update.setEnabled(false);
            insert.setEnabled(true);
            
            prID.setText(null);
            prName.setText(null);
            prPrice.setValue(0);
            qty.setValue(0);
            
            dtm.setRowCount(0);
            try {
                String query = "SELECT * FROM produkty";
                ResultSet rs = CDB.stmt.executeQuery(query);
                rsm = rs.getMetaData();
                
                while(rs.next()) {
                    Product = new Vector<>();
                    for(int i = 1; i <= rsm.getColumnCount(); i++) {
                        Product.add(rs.getObject(i));
                    }
                    dtm.addRow(Product);
                }
            }
            catch(SQLException e1) {
            }
        });

	Content.add(choosePanel);
	Content.add(bttn);
	
	manageProduct.add(scroll, "North");
	manageProduct.add(Content, "Center");
	manageProduct.setVisible(true);
        
    }
    
}

