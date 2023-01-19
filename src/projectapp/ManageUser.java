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
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Midura Patryk
 */

public class ManageUser {
    
    ConnectDB CDB = new ConnectDB();
    Vector<String> header = new Vector<>();
    Vector<Vector<Object>> data = new Vector<>();
    Vector<Object> User;
    JFrame user;
    JTextField usrID, usrEmail, usrPhone, usrAddress;
    JButton insert, delete, update, submit, cancel;
    JPanel Content, ID, email, psswrd, dobSec, choosePanel, phone, address, GenderSec, Role, bttn, insrt, dlt, updt, cncl, sbmt;
    JScrollPane scroll;
    JTable table;
    JLabel userID, userEmail, userPass, dob, userPhone, userAddress, Gender, userRole;
    JComboBox<String> yearList,MonthList,DateList,role;
    JPasswordField usrPass;
    JRadioButton Male, Female;
    String genderGet;
    ButtonGroup genderGroup;
    String UserID;
    String getEmail;
    String getPasswd;
    String getAddress;
    String getPhone;
    String getRole;
    String year;
    String Month;
    String day;
	
    DefaultTableModel dtm;
    ResultSetMetaData rsm;
    
    public void ManageUser() {
        
        user = new JFrame();
	user.setSize(1000, 600);
	user.setLocationRelativeTo(null);
	user.setLayout(new BorderLayout());
	user.setTitle("Zarządzaj Użytkownikiem");
			
	header = new Vector<>();
	header.add("ID_Klienta");
	header.add("E-mail");
	header.add("Hasło");
	header.add("Płeć");
	header.add("Data Urodzenia");
	header.add("Telefon");
	header.add("Adres");
	header.add("Rola");
	dtm = new DefaultTableModel(header, 0);
	
	try {
            CDB.ConnectDB();
            String query = "SELECT ID_Klienta, Email, Hasło, Płeć, Data_Urodzenia, Telefon, Adres, Rola FROM klienci";
            ResultSet rs = CDB.stmt.executeQuery(query);
            rsm = rs.getMetaData();
	
            while(rs.next()) {
		User = new Vector();
		for(int i = 1; i <= rsm.getColumnCount(); i++) {
                    User.add(rs.getObject(i));
		}
		dtm.addRow(User);
            }
	}
        catch(SQLException e){
	}

	table = new JTable(dtm);
	table.setAutoCreateRowSorter(true);
	scroll = new JScrollPane(table);
	scroll.setPreferredSize(new Dimension(0, 300));
				
	Content = new JPanel(new GridLayout(1,2));
        
	//Input Layout
	choosePanel = new JPanel(new GridLayout(8,1));
		
	//ID
	ID = new JPanel(new FlowLayout());
	userID = new JLabel();
	userID.setText("ID Klienta:                ");
	usrID = new JTextField();
	usrID.setPreferredSize(new Dimension(200, 20));
	usrID.setEnabled(false);
	ID.add(userID);
	ID.add(usrID);

	//Email
	email = new JPanel(new FlowLayout());
	userEmail = new JLabel();
	userEmail.setText("E-mail:                      ");
	usrEmail = new JTextField();
	usrEmail.setPreferredSize(new Dimension(200, 20));
	usrEmail.setEnabled(false);
	email.add(userEmail);
	email.add(usrEmail);

	//Price
	psswrd = new JPanel(new FlowLayout());
	userPass = new JLabel();
	userPass.setText("Hasło:                       ");
	usrPass = new JPasswordField();
	usrPass.setPreferredSize(new Dimension(200, 20));
	usrPass.setEnabled(false);
	psswrd.add(userPass);
	psswrd.add(usrPass);

	//Quantity
	dobSec = new JPanel(new FlowLayout());
	dob = new JLabel();
	dob.setText("Data urodzenia:            ");
	yearList = new JComboBox<>();
	yearList.addItem("Rok");
	for(int i = 0; i <= 80; i++) {
            yearList.addItem(Integer.toString(1940 + i));
	}
        
	MonthList = new JComboBox<>();
	MonthList.addItem("Miesiąc");
	MonthList.addItem("Styczeń");
	MonthList.addItem("Luty");
	MonthList.addItem("Marzec");
	MonthList.addItem("Kwiecień");
	MonthList.addItem("Maj");
	MonthList.addItem("Czerwiec");
	MonthList.addItem("Lipiec");
	MonthList.addItem("Sierpień");
	MonthList.addItem("Wrzesień");
	MonthList.addItem("Październik");
	MonthList.addItem("Listopad");
	MonthList.addItem("Grudzień");
	
	DateList = new JComboBox<>();
	DateList.addItem("Dzień");
	for(int i = 1; i < 32; i++) {
            DateList.addItem(Integer.toString(i));
	}
	
	dobSec.add(dob);
	dobSec.add(yearList);
	dobSec.add(MonthList);
	dobSec.add(DateList);

	//Add, Remove, Checkout
	phone = new JPanel(new FlowLayout());
	userPhone = new JLabel();
	userPhone.setText("Telefon:                    ");
	usrPhone = new JTextField();
	usrPhone.setPreferredSize(new Dimension(200, 20));
	usrPhone.setEnabled(false);
	phone.add(userPhone);
	phone.add(usrPhone);

	//Add, Remove, Checkout
	address = new JPanel(new FlowLayout());
	userAddress = new JLabel();
	userAddress.setText("Adres:                      ");
	usrAddress = new JTextField();
	usrAddress.setPreferredSize(new Dimension(200, 20));
	usrAddress.setEnabled(false);
	address.add(userAddress);
	address.add(usrAddress);

	//Gender Panel
	GenderSec = new JPanel(new FlowLayout());
	Gender = new JLabel();
	Gender.setText("Płeć:                          ");
	Male = new JRadioButton("Mężczyzna        ");
	Male.addActionListener((java.awt.event.ActionEvent e) -> {
            genderGet = "Mężczyzna";
        });			
										
	Female = new JRadioButton("Kobieta        ");
	Female.addActionListener((java.awt.event.ActionEvent e) -> {
            genderGet = "Kobieta";
        });
						
	genderGroup = new ButtonGroup();
	genderGroup.add(Male);
	genderGroup.add(Female);
	GenderSec.add(Gender);
	GenderSec.add(Male);
	GenderSec.add(Female);

	Role = new JPanel(new FlowLayout());
	userRole = new JLabel();
	userRole.setText("Rola:                         ");
	role = new JComboBox<>();
	role.addItem("Wybierz");
	role.addItem("Admin");
	role.addItem("Klient");
	role.setPreferredSize(new Dimension(200, 20));
	Role.add(userRole);
	Role.add(role);
	
        //Add To Choose Panel
	choosePanel.add(ID);
	choosePanel.add(email);
	choosePanel.add(psswrd);
	choosePanel.add(dobSec);	
	choosePanel.add(phone);
	choosePanel.add(address);
	choosePanel.add(GenderSec);
	choosePanel.add(Role);

	table.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
		int i = table.getSelectedRow();
		usrID.setText(table.getValueAt(i, 0).toString());
		update.addActionListener((java.awt.event.ActionEvent e1) -> {
                    submit.setEnabled(true);
                    cancel.setEnabled(true);
                    delete.setEnabled(false);
                    insert.setEnabled(false);
                    usrEmail.setEnabled(true);
                    usrPass.setEnabled(true);
                    usrPhone.setEnabled(true);
                    usrAddress.setEnabled(true);
                    int i1 = table.getSelectedRow();
                    usrID.setText(table.getValueAt(i1, 0).toString());
                    usrEmail.setText(table.getValueAt(i1, 1).toString());
                    usrPass.setText(table.getValueAt(i1, 2).toString());
                    usrPhone.setText(table.getValueAt(i1, 5).toString());
                    usrAddress.setText(table.getValueAt(i1, 6).toString());
                    role.setSelectedItem(table.getValueAt(i1, 7).toString());
                });

		delete.addActionListener((java.awt.event.ActionEvent e1) -> {
                    try {
                        usrID.setText(table.getValueAt(table.getSelectedRow(), 0).toString());
                        try {
                            String query = "DELETE FROM klienci WHERE ID_Klienta = ?";
                            PreparedStatement pst = CDB.conn.prepareStatement(query);
                            pst.setString(1, usrID.getText());
                            pst.executeUpdate();
                        } catch (SQLException e2) {
                        }
                        JOptionPane.showMessageDialog(null, "Usunięto Pomyślnie");
                        dtm.setRowCount(0);
                        try {
                            String query = "SELECT * FROM klienci";
                            ResultSet rs = CDB.stmt.executeQuery(query);
                            rsm = rs.getMetaData();
                            while (rs.next()) {
                                User = new Vector<>();
                                for (int i1 = 1; i1 <= rsm.getColumnCount(); i1++) {
                                    User.add(rs.getObject(i1));
                                }
                                dtm.addRow(User);
                            }
                        } catch (SQLException e3) {
                        }
                    } catch (HeadlessException e4) {
                    }
                });
            }
	});

	bttn = new JPanel(new GridLayout(5,1)); 
	insrt = new JPanel(new FlowLayout());
	dlt = new JPanel(new FlowLayout());
	updt = new JPanel(new FlowLayout());
	sbmt = new JPanel(new FlowLayout());
	cncl = new JPanel(new FlowLayout());
				
	insert = new JButton("Dodaj");
	delete = new JButton("Usuń");
	update = new JButton("Zaktualizuj");
	submit = new JButton("Zatwierdź");
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

	Content.add(choosePanel);
	Content.add(bttn);
			
	//Insert Button
	insert.addActionListener((java.awt.event.ActionEvent e) -> {
            usrID.setText(null);
            usrPass.setEnabled(true);
            usrEmail.setEnabled(true);
            usrPhone.setEnabled(true);
            usrAddress.setEnabled(true);
            
            submit.setEnabled(true);
            cancel.setEnabled(true);
            delete.setEnabled(false);
            update.setEnabled(false);
            
            CDB.ConnectDB();
            try {
                String getID = "SELECT ID_Klienta FROM Klienci ORDER BY ID_Klienta DESC";
                ResultSet resultID = CDB.stmt.executeQuery(getID);
                
                //Generate Client ID
                if(resultID.next()) {
                    String lastID = resultID.getString("ID_Klienta");
                    Integer idNumber = Integer.parseInt(lastID.substring(2,5));
                    idNumber++;
                    UserID = "KL" + String.format("%03d", idNumber);
                }
                else {
                    UserID = "KL001";
                }
            }
            catch(SQLException e1){
            }
        });
	
        //Submit Button
        submit.addActionListener((java.awt.event.ActionEvent e) -> {
            getEmail = usrEmail.getText();
            getPasswd = usrPass.getText();
            getAddress = usrAddress.getText();
            getPhone = usrPhone.getText();
            getRole = (String) role.getSelectedItem();
            year = (String) yearList.getSelectedItem();
            Month = (String) MonthList.getSelectedItem();
            day = (String) DateList.getSelectedItem();
            
            //Validate
            if (getEmail.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Proszę podaj swój adres E-mail");
            }
            else if (getPasswd.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Proszę wypełnij pole hasło");
            }
            else if (Month.contains("Miesiąc") || year.contains("Rok") || day.contains("Dzień")) {
                JOptionPane.showMessageDialog(null, "Wybierz prawidłową datę urodzin");
            }
            else if (getPhone.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Proszę wprowadź swój nr telefonu");
            }
            else if (getAddress.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Proszę wprowadź swój adres");
            }
            else if (genderGroup.isSelected(null)) {
                JOptionPane.showMessageDialog(null, "Wybierz płeć");
            }
            else if (getRole.contains("Wybierz")) {
                JOptionPane.showMessageDialog(null, "Wybierz rolę");
            }
            else {
                if ((!getEmail.startsWith("@") && !getEmail.startsWith(".")) && (!getEmail.contains("@.")) && (getEmail.contains("@") && getEmail.contains(".")) &&(validateEmail() == true)) {
                        int valYear = 2020 - Integer.parseInt(year);
                        if (valYear >= 13) {
                            if (phoneNumeric() == true) {
                                if (getPhone.length() == 9) {
                                    
                                    //Insert Data to Database
                                    if (usrID.getText().isEmpty()) {
                                        CDB.ConnectDB();
                                        try {
                                            String dob1 = year + "-" + month() + "-" + day;
                                            String query = "INSERT INTO klienci VALUES (?,?,?,?,?,?,?,?)";
                                            PreparedStatement pst = CDB.conn.prepareStatement(query);
                                            pst.setString(1, UserID);
                                            pst.setString(2, usrEmail.getText());
                                            pst.setString(3, usrPass.getText());
                                            pst.setString(4, genderGet);
                                            pst.setString(5, dob1);
                                            pst.setString(6, usrPhone.getText());
                                            pst.setString(7, usrAddress.getText());
                                            pst.setString(8, getRole);
                                            pst.executeUpdate();
                                            JOptionPane.showMessageDialog(null, "Dodano Pomyślnie");
                                        }
                                        catch(SQLException e1) {
                                        }
                                        
                                    //Update Data to Database
                                    }
                                    else {
                                        String dob2 = year + "-" + month() + "-" + day;
                                        try {
                                            String query = "UPDATE klienci set Email = ?, Hasło = ?, Płeć = ?, Data_Urodzenia = ?, Telefon = ?, Adres = ?, Rola = ? WHERE ID_Klienta = ?";
                                            PreparedStatement pst = CDB.conn.prepareStatement(query);
                                            pst.setString(1, usrEmail.getText());
                                            pst.setString(2, usrPass.getText());
                                            pst.setString(3, genderGet);
                                            pst.setString(4, dob2);
                                            pst.setString(5, usrPhone.getText());
                                            pst.setString(6, usrAddress.getText());
                                            pst.setString(7, getRole);
                                            pst.setString(8, usrID.getText());
                                            pst.executeUpdate();
                                            JOptionPane.showMessageDialog(null, "Zaktualizowano Pomyślnie");
                                        }
                                        catch (SQLException e1) {   
                                        }
                                    }
                                }
                                else {
                                    JOptionPane.showMessageDialog(null, "Telefon musi zawierać 9 cyfr");
                                }
                            }
                            else {
                                JOptionPane.showMessageDialog(null, "Telefon musi składać się wyłącznie z cyfr");
                            }
                        }
                        else {
                            JOptionPane.showMessageDialog(null, "Musisz mieć conajmniej 13lat, aby się zarejestrować");
                        }
                }
                else {
                    JOptionPane.showMessageDialog(null, "Nieprawidłowy format adresu e-mail");
                }
            }
            
            submit.setEnabled(false);
            cancel.setEnabled(false);
            delete.setEnabled(true);
            update.setEnabled(true);
            insert.setEnabled(true);
            usrID.setText(null);
            usrPass.setText(null);
            usrEmail.setText(null);
            usrPhone.setText(null);
            usrAddress.setText(null);
            yearList.setSelectedItem("Rok");
            MonthList.setSelectedItem("Miesiąc");
            DateList.setSelectedItem("Dzień");
            role.setSelectedItem("Wybierz");
            dtm.setRowCount(0);
            
            try {
                String query = "SELECT * FROM klienci";
                ResultSet rs = CDB.stmt.executeQuery(query);
                rsm = rs.getMetaData();
                
                while(rs.next()) {
                    User = new Vector<>();
                    for(int i = 1; i <= rsm.getColumnCount(); i++) {
                        User.add(rs.getObject(i));
                    }
                    dtm.addRow(User);
                }
            }
            catch (SQLException e1) {  
            }
        });
		
        //Cancel Button
	cancel.addActionListener((java.awt.event.ActionEvent e) -> {
            submit.setEnabled(false);
            cancel.setEnabled(false);
            delete.setEnabled(true);
            update.setEnabled(true);
            insert.setEnabled(true);
            
            usrID.setText(null);
            usrPass.setText(null);
            usrEmail.setText(null);
            usrPhone.setText(null);
            usrAddress.setText(null);
            
            usrPass.setEnabled(false);
            usrEmail.setEnabled(false);
            usrPhone.setEnabled(false);
            usrAddress.setEnabled(false);
        });
				
        user.add(scroll, "North");
	user.add(Content, "Center");	
	user.setVisible(true);
        
    }
    
    public int month() {
	String Month = (String) MonthList.getSelectedItem();
	if(Month.contains("Styczeń")) {
            return 1;
	}
        else if(Month.contains("Luty")) {
            return 2;
	}
        else if(Month.contains("Marzec")) {
            return 3;
	}
        else if(Month.contains("Kwiecień")) {
            return 4;
	}
        else if(Month.contains("Maj")) {
            return 5;
	}
        else if(Month.contains("Czerwiec")) {
            return 6;
	}
        else if(Month.contains("Lipiec")) {
            return 7;
	}
        else if(Month.contains("Sierpień")) {
            return 8;
	}
        else if(Month.contains("Wrzesień")) {
            return 9;
	}
        else if(Month.contains("Październik")) {
            return 10;
	}
        else if(Month.contains("Listopad")) {
            return 11;
	}
        else if(Month.contains("Grudzień")) {
            return 12;
	}
	return month();
    }
    
    public boolean validateEmail() {
	int container1 = 0;
	int container2 = 0;
	for(int i = 0; i < getEmail.length(); i++) {
            if(getEmail.charAt(i) == '@') {
		container1 = container1 + 1;
            }
            else if(getEmail.charAt(i) == '.') {
		container2 = container2 + 1;			
            }
        }
        return !(container1 > 1 || container2 > 1);
    }

    public boolean phoneNumeric(){
        return getPhone.matches("[0-9]+");
    }
    
}

