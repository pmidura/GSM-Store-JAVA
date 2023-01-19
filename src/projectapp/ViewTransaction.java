package projectapp;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Vector;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Midura Patryk
 */

public class ViewTransaction {
    
    ConnectDB CDB = new ConnectDB();
    Vector<String> header = new Vector<>();
    Vector<Object> transaction;
    Vector<String> header2 = new Vector<>();
    Vector<Object> Product;
    JFrame viewtrans;
    JScrollPane scroll, scroll2;
    JTable table, table2;
    DefaultTableModel dtm, dtm2;
    ResultSetMetaData rsm, rsm2;
    JScrollPane scrollpane, scrollpane2;
    String TransactionID;

    public void ViewTransaction() {
	viewtrans = new JFrame();
	viewtrans.setSize(1000, 600);
	viewtrans.setLocationRelativeTo(null);
	viewtrans.setLayout(new GridLayout(2, 1));
	viewtrans.setTitle("Panel Transakcji");
		
        header = new Vector<>();	
        header.add("ID_Transakcji");
	header.add("ID_Klienta");
	header.add("E-mail");
	header.add("Data Transakcji");
	dtm = new DefaultTableModel(header, 0);
		
	//Table 1
	try {
            CDB.ConnectDB();
            String query = "SELECT transakcje.ID_Transakcji, klienci.ID_Klienta, klienci.Email, transakcje.Data_Transakcji FROM transakcje, klienci WHERE klienci.ID_Klienta = transakcje.ID_Klienta";
            ResultSet rs = CDB.stmt.executeQuery(query);
            rsm = rs.getMetaData();
	
            while(rs.next()) {
                transaction = new Vector<>();
                for(int i = 1; i <= rsm.getColumnCount(); i++) {
                    transaction.add(rs.getObject(i));			
                }
		dtm.addRow(transaction);
            }
	}
        catch (SQLException e) {
	}
		
	table = new JTable(dtm);
	scrollpane = new JScrollPane(table);
	scrollpane.setPreferredSize(new Dimension(0, 350));
		
	table.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
		TransactionID = table.getValueAt(table.getSelectedRow(), 0).toString();
                
		//Select All Data
		try {
                    dtm2.setRowCount(0);
                    CDB.ConnectDB();
                    String query = "SELECT produkty.ID_Produktu, produkty.Nazwa, transakcje.Ilość_szt FROM transakcje, produkty WHERE produkty.ID_Produktu = transakcje.ID_Produktu and ID_Transakcji = '" + TransactionID + "'";
                    ResultSet rs = CDB.stmt.executeQuery(query);
                    rsm2 = rs.getMetaData();
                    
                    while(rs.next()) {
			Product = new Vector<>();
			for (int i = 1; i <= rsm2.getColumnCount(); i++) {
                            Product.add(rs.getObject(i));				
			}
			dtm2.addRow(Product);
                    }
		}
                catch (SQLException e1) {
		}	
            }
	});
		
	//Table 2
	header2 = new Vector<>();
	header2.add("ID_Produktu");
	header2.add("Nazwa");
	header2.add("Ilość (szt)");
	dtm2 = new DefaultTableModel(header2, 0);

	table2 = new JTable(dtm2);
	scrollpane2 = new JScrollPane(table2);
	scrollpane2.setPreferredSize(new Dimension(0, 350));
		
	viewtrans.add(scrollpane);
	viewtrans.add(scrollpane2);			
	viewtrans.setVisible(true);	
    }
    
}

