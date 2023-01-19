package projectapp;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author Midura Patryk
 */

public class Main {
    
    LoginPanel LP = new LoginPanel();
    ConnectDB CDB = new ConnectDB();
    KlientMenu KM = new KlientMenu();
    BuyProduct BP = new BuyProduct();
    AdminPanel AP = new AdminPanel();
    ManageUser MU = new ManageUser();
    ViewTransaction VT = new ViewTransaction();
    ManageProduct MP = new ManageProduct();
    Register RG = new Register();
    
    String email, passwd, confirmPasswd, address, phone, year, month, day, ID_Klienta, ID_Klienta_Login, TransactionID;
    
    public void main() {
        LP.LoginPanel();
        LP.LoginBtn.addActionListener((java.awt.event.ActionEvent e) -> {
            String username = LP.EmailTxtField.getText();
            String passwd1 = LP.PasswdField.toString();
            if (username.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Proszę wypełnić pole Email");
            } else if (passwd1.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Proszę wypełnić pole Hasło");
            } else {
                CDB.ConnectDB();
                String query = "SELECT Email, Hasło, Rola FROM klienci where Email = '" + LP.EmailTxtField.getText() + "' AND Hasło = '" + LP.PasswdField.getText() + "' AND Rola = 'Klient'";
                String getID = "SELECT ID_Klienta FROM klienci WHERE Email = '" + username + "' ";
                
                try {
                    ResultSet rs = CDB.stmt.executeQuery(query);
                    
                    if(rs.next()) {
                        ResultSet rsID = CDB.stmt.executeQuery(getID);
                        rsID.next();
                        ID_Klienta_Login = rsID.getString("ID_Klienta");
                        LP.LoginFrame.dispose();
                        KM.KlientMenu();
                        
                        //Client Menu System
                        KM.LogoffItem.addActionListener((java.awt.event.ActionEvent e1) -> {
                            KM.UserFrame.dispose();
                            main();
                        });
                        
                        KM.ExitItem.addActionListener((java.awt.event.ActionEvent e1) -> {
                            KM.UserFrame.dispose();
                        });
                        
                        KM.BuyItem.addActionListener((java.awt.event.ActionEvent e1) -> {
                            BP.BuyProduct();
                            BP.checkout.addActionListener((java.awt.event.ActionEvent e2) -> {
                                if (!(BP.Transaction.isEmpty())) {
                                    CDB.ConnectDB();
                                    try {
                                        String getID1 = "SELECT ID_Transakcji FROM transakcje ORDER BY ID_Transakcji DESC";
                                        ResultSet resultID = CDB.stmt.executeQuery(getID1);
                                        if(resultID.next()) {
                                            String lastID = resultID.getString("ID_Transakcji");
                                            Integer idNumber = Integer.parseInt(lastID.substring(2, 5));
                                            idNumber++;
                                            TransactionID = "TR" + String.format("%03d", idNumber);
                                        }
                                        else {
                                            TransactionID = "TR001";
                                        }
                                        String query1 = "INSERT INTO transakcje VALUES (?, ?, ?, CURRENT_TIME(), ?)";
                                        PreparedStatement pst = CDB.conn.prepareStatement(query1);
                                        pst.setString(1, TransactionID);
                                        pst.setString(2, BP.ID_Produktu.getText());
                                        pst.setString(3, ID_Klienta_Login);
                                        pst.setString(4, Integer.toString(BP.valQnty));
                                        pst.executeUpdate();
                                        String query2 = "UPDATE produkty set Stan_magazyn = ? WHERE ID_Produktu = ?";
                                        PreparedStatement pst2 = CDB.conn.prepareStatement(query2);
                                        pst2.setString(1, Integer.toString(BP.StockUpdate));
                                        pst2.setString(2, BP.productid);
                                        pst2.executeUpdate();
                                        JOptionPane.showMessageDialog(null, "Transakcja zakończona sukcesem");
                                    }
                                    catch (SQLException e3) {
                                    }
                                }
                                else {
                                    JOptionPane.showMessageDialog(null, "Twój koszyk jest pusty");
                                }
                            });
                        });
                    }
                    else {
                        CDB.ConnectDB();
                        String query1 = "SELECT Email, Hasło, Rola FROM klienci WHERE Email = '" + LP.EmailTxtField.getText() + "' AND Hasło = '" + LP.PasswdField.getText() + "' AND Rola = 'Admin'";
                        try {
                            ResultSet rs1 = CDB.stmt.executeQuery(query1);
                            
                            if(rs1.next()) {
                                LP.LoginFrame.dispose();
                                AP.AdminPanel();
                                
                                //Admin Menu System
                                AP.LogoffItem.addActionListener((java.awt.event.ActionEvent e1) -> {
                                    AP.AdminFrame.dispose();
                                    main();
                                });
                                
                                AP.ExitItem.addActionListener((java.awt.event.ActionEvent e1) -> {
                                    AP.AdminFrame.dispose();
                                });
                                
                                AP.UserItem.addActionListener((java.awt.event.ActionEvent e1) -> {
                                    MU.ManageUser();
                                });
                                
                                AP.ViewTransactionItem.addActionListener((java.awt.event.ActionEvent e1) -> {
                                    VT.ViewTransaction();
                                });
                                
                                AP.ProductItem.addActionListener((java.awt.event.ActionEvent e1) -> {
                                    MP.ManageProduct();
                                });
                            }
                            else {
                                JOptionPane.showMessageDialog(null, "Nieprawidłowy E-mail lub Hasło");
                            }
                        }
                        catch(SQLException e1) {
                        }
                    }
                }
                catch(SQLException e1) {
                }
            }
        });
        LP.RegisterBtn.addActionListener((java.awt.event.ActionEvent e) -> {
            LP.LoginFrame.dispose();
            RG.Register();
            
            RG.submit.addActionListener((java.awt.event.ActionEvent e1) -> {
                email = RG.EmailInput.getText();
                passwd = RG.PassInput.getText();
                address = RG.AddressInput.getText();
                phone = RG.Phone.getText();
                year = (String) RG.yearList.getSelectedItem();
                month = (String) RG.MonthList.getSelectedItem();
                day = (String) RG.DateList.getSelectedItem();
                
                //Validate
                if (email.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Proszę podaj swój adres E-mail");
                } else if (passwd.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Proszę wypełnij pole hasło");
                } else if (month.contains("Miesiąc") || year.contains("Rok") || day.contains("Dzień")) {
                    JOptionPane.showMessageDialog(null, "Wybierz prawidłową datę urodzin");
                } else if (phone.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Proszę wprowadź swój nr telefonu");
                } else if (address.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Proszę wprowadź swój adres");
                } else if (RG.genderGroup.isSelected(null)) {
                    JOptionPane.showMessageDialog(null, "Wybierz płeć");
                } else {
                    if ((!email.startsWith("@") && !email.startsWith(".")) && (!email.contains("@.")) && (email.contains("@")&& email.contains(".")) &&(validateEmail() == true)) {
                                int valYear = 2020 - Integer.parseInt(year);
                                if (valYear >= 13) {
                                    if (phoneNumeric() == true) {
                                        if (phone.length() == 9) {
                                            //Insert Data to database and Generate ID
                                            CDB.ConnectDB();
                                            try {
                                                String getID = "Select ID_Klienta FROM klienci ORDER BY ID_Klienta DESC";
                                                ResultSet resultID = CDB.stmt.executeQuery(getID);
                                                
                                                if(resultID.next()) {
                                                    String lastID = resultID.getString("ID_Klienta");
                                                    Integer idNumber = Integer.parseInt(lastID.substring(2,5));
                                                    idNumber++;
                                                    ID_Klienta = "KL" + String.format("%03d", idNumber);
                                                }
                                                else {
                                                    ID_Klienta = "KL001";
                                                }
                                                
                                                String dob = year + "-" + RG.month() + "-" + day;
                                                String query = "INSERT INTO klienci VALUES (?,?,?,?,?,?,?,?)";
                                                PreparedStatement pst = CDB.conn.prepareStatement(query);
                                                pst.setString(1, ID_Klienta);
                                                pst.setString(2, RG.EmailInput.getText());
                                                pst.setString(3, RG.PassInput.getText());
                                                pst.setString(4, RG.genderGet);
                                                pst.setString(5, dob);
                                                pst.setString(6, RG.Phone.getText());
                                                pst.setString(7, RG.AddressInput.getText());
                                                pst.setString(8, "Klient");
                                                pst.executeUpdate();
                                                
                                                JOptionPane.showMessageDialog(null, "Rejestracja zakończona sukcesem");
                                                RG.registerFrame.dispose();
                                                main();
                                            } catch (SQLException e2) {
                                            }
                                        } else {
                                            JOptionPane.showMessageDialog(null, "Telefon musi zawierać 9 cyfr");
                                        }
                                    } else {
                                        JOptionPane.showMessageDialog(null, "Telefon musi składać się wyłącznie z cyfr");
                                    }
                                } else {
                                    JOptionPane.showMessageDialog(null, "Musisz mieć conajmniej 13lat, aby się zarejestrować");
                                }
                    } else {
                        JOptionPane.showMessageDialog(null, "Nieprawidłowy format adresu e-mail");
                    }
                }
            });
            
            RG.cancel.addActionListener((java.awt.event.ActionEvent e1) -> {
                RG.registerFrame.dispose();
                main();
            });
        });
    }
    
    public Main() {
        main();
    }
    
    public static void main(String[] args) {
        new Main();
    }
    
    public boolean validateEmail(){
	int container1 = 0;
	int container2 = 0;
	for(int i = 0; i < email.length(); i++) {
            if(email.charAt(i) == '@') {
		container1 = container1 + 1;
            }
            else if(email.charAt(i) == '.') {
		container2 = container2 + 1;			
            }
	}
        return !(container1 > 1 || container2 > 1);
    }

    public boolean phoneNumeric(){
        return phone.matches("[0-9]+");
    }
    
}

