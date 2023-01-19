package projectapp;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

/**
 *
 * @author Midura Patryk
 */

public class Register {
    
    JFrame registerFrame;
    JPanel regisSec, title, EmailSec, PassSec, ConfrimSec, dobSec, NumberSec, AddressSec, GenderSec, SubmitSec;
    JLabel registxt, Email, Pass, Confrim, dob, Number, Address, Gender;
    JTextField EmailInput, Phone, AddressInput;
    JPasswordField PassInput, ConfrimInput;
    JComboBox<String> yearList, MonthList, DateList;
    JRadioButton Male, Female;
    JButton submit, cancel;
    ButtonGroup genderGroup;
    String genderGet;
        
    public void Register() {
        
        registerFrame = new JFrame();
        
	//Register Panel
	regisSec = new JPanel(new GridLayout(9,1));
	title = new JPanel(new FlowLayout());
	registxt = new JLabel();
	registxt.setText("Zarejestruj się");
	registxt.setFont(new Font("Arial", Font.PLAIN ,20));
	title.add(registxt);
	registerFrame.add(title, "North");
					
	//Email Panel
	EmailSec = new JPanel(new FlowLayout());
	Email = new JLabel();
	Email.setText("E-mail :                    ");
	EmailInput = new JTextField();
	EmailInput.setPreferredSize(new Dimension(200,20));
	EmailSec.add(Email);
	EmailSec.add(EmailInput);

	//Password Panel
	PassSec = new JPanel(new FlowLayout());
	Pass = new JLabel();
	Pass.setText("Hasło :                     ");
	PassInput = new JPasswordField();
	PassInput.setPreferredSize(new Dimension(200,20));
	PassInput.setEchoChar('*');
	PassSec.add(Pass);
	PassSec.add(PassInput);
					
	//Date Of Birth
	dobSec = new JPanel(new FlowLayout());
	dob = new JLabel();
	dob.setText("Data Urodzenia :           ");
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
					
	//Phone Number
	NumberSec = new JPanel(new FlowLayout());
	Number = new JLabel();
	Number.setText("Nr telefonu :            ");
	Phone = new JTextField();
	Phone.setPreferredSize(new Dimension(200,20));
	NumberSec.add(Number);
	NumberSec.add(Phone);
					
	//Address
	AddressSec = new JPanel(new FlowLayout());
	Address = new JLabel();
	Address.setText("Adres :                      ");
	AddressInput = new JTextField();
	AddressInput.setPreferredSize(new Dimension(200,20));
	AddressSec.add(Address);
	AddressSec.add(AddressInput);
					
	//Gender Panel
	GenderSec = new JPanel(new FlowLayout());
	Gender = new JLabel();
	Gender.setText("Płeć :                    ");
	Male = new JRadioButton("Mężczyzna        ");
	Male.addActionListener((ActionEvent e) -> {
            genderGet = "Mężczyzna";
        });			
										
	Female = new JRadioButton("Kobieta        ");
	Female.addActionListener((ActionEvent e) -> {
            genderGet = "Kobieta";
        });
					
	genderGroup = new ButtonGroup();
	genderGroup.add(Male);
	genderGroup.add(Female);
					
	GenderSec.add(Gender);
	GenderSec.add(Male);
	GenderSec.add(Female);
					
	//Submit & Cancel Panel
	SubmitSec = new JPanel(new FlowLayout()); 
	submit = new JButton("Potwierdź");
	cancel = new JButton("Wyjdź");
					
	SubmitSec.add(submit);
	SubmitSec.add(cancel);
					
	regisSec.add(title);
	regisSec.add(EmailSec);
	regisSec.add(PassSec);
	regisSec.add(dobSec);
	regisSec.add(NumberSec);
	regisSec.add(AddressSec);
	regisSec.add(GenderSec);	
	regisSec.add(SubmitSec);
	registerFrame.add(regisSec, "Center");

	registerFrame.setSize(600,500);
	registerFrame.setLocationRelativeTo(null);
	registerFrame.setTitle("Rejestracja");
	registerFrame.setVisible(true);
        
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
    
}

