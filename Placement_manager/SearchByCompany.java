import java.awt.*;
import javax.swing.*;
import javax.swing.border.Border;

import java.awt.event.*;
import java.io.*;
import java.util.*;
import static javax.swing.JOptionPane.showMessageDialog;

public class SearchByCompany extends JFrame implements ActionListener {
    
    private JLabel companyL;
    private JComboBox<String> companyT; 
    private JTextArea output;
    private JButton search, back;
    
    Container con=null;
    String[] companies= {"Google", "Amazon","Flipkart","Ebay","SAP_LABS","Capgemini","Cognizant","Infosys","Wipro"};
    
    SearchByCompany() {
        super("Search By Company");
        con = getContentPane();
        con.setLayout(null);
        Color lightBlue = new Color(0, 255, 255);
        con.setBackground(lightBlue);

        con.setSize(300, 300);
        con.setLayout(null);
        con.setVisible(true);

        Font font = new Font("Verdana", Font.BOLD, 16);
        Color blue = new Color(30, 144, 255);
        
        companyL = new JLabel("Select Company to search for records:");
        companyL.setBounds(300, 50, 700, 150);
        companyL.setFont(font);
        companyL.setForeground(Color.BLACK);
        
        companyT = new JComboBox<>(companies);
        companyT.setBounds(725, 100, 250, 50);
        companyT.setFont(font);
        companyT.setForeground(Color.BLACK);

        output = new JTextArea();
        output.setBounds(20, 200, 1300, 350);
        output.setFont(font);
        output.setForeground(Color.BLACK);
        output.setEditable(false);
       
        search = new JButton("Search");
        search.setBounds(400, 600, 150, 40);
        search.addActionListener(this);
        search.setFont(font);
        Color pul = new Color(0, 0, 255);
        Border bored = BorderFactory.createLineBorder(pul, 5);
        search.setBorder(bored);
        search.setForeground(Color.WHITE);
        search.setBackground(blue);

        back = new JButton("Go Back");
        back.setBounds(600, 600, 150, 40);
        back.addActionListener(this);
        back.setFont(font);
        back.setBorder(bored);
        back.setForeground(Color.WHITE);
        back.setBackground(blue);

        con.add(companyL);
        con.add(companyT);
        con.add(output);
        con.add(search);
        con.add(back);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == search) {
            String company = (String) companyT.getSelectedItem();
            try {
                searchRecordInCSV("student.csv", company);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if (ae.getSource() == back) {
            try {
                this.dispose();
                Search1 h = new Search1();
                h.setSize(2300, 790);
                h.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void searchRecordInCSV(String filename, String company) throws IOException {
        StringBuilder recordText = new StringBuilder();

        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            boolean recordFound = false;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 7 && parts[6].equalsIgnoreCase(company)) {
                    recordText.append("Company Results:\n");
                    recordText.append("NAME\t|USN\t|SEM\t|BRANCH\t|CGPA\t|NOB\t|COMPANY\t|CTC\t|COMMENTS\n");
                    recordText.append(
                            "--------------------------------------------------------------------------------------------------------------------------------\n");
                    recordText.append(line.replace(",", "\t"));
                    recordText.append("\n");
                    recordFound = true;
                }
            }

            if (!recordFound) {
                showMessageDialog(null, "No records found for the selected company.");
            }
        }

        output.setText(recordText.toString());
    }

    public static void main(String args[]) {
        SearchByCompany ser = new SearchByCompany();
        ser.setSize(2300, 790);
        ser.setVisible(true);
    }
}
