import java.awt.*;
import javax.swing.*;
import javax.swing.border.Border;

import java.awt.event.*;
import java.io.*;
import java.util.*;
import static javax.swing.JOptionPane.showMessageDialog;

public class SearchByBranch extends JFrame implements ActionListener
{
    
    private JLabel branchL;
    private JComboBox<String> branchT; 
    private JTextArea output;
    private JButton search,back;
    
    Container con=null;
    String[] branches= {"CSE","IT","EEE","ECE","MEC","AIML"};
    
    SearchByBranch()
    {
        super("Search By Branch");
        con = getContentPane();
        con.setLayout(null);
        Color  lightBlue = new Color(0,255,255);
        con.setBackground(lightBlue);

        con.setSize(300,300);
        con.setLayout(null);
        con.setVisible(true);

        Font font = new Font("Verdana", Font.BOLD, 16);
        Color blue = new Color(30,144,255);
        branchL=new JLabel("Select Branch to search for records:");
        branchL.setBounds(300, 50, 700,150);
        branchL.setFont(font);
        branchL.setForeground(Color.BLACK);
        
        branchT=new JComboBox<>(branches);
		branchT.setBounds(725,100,250,50);
        branchT.setFont(font);
        branchT.setForeground(Color.BLACK);

        output=new JTextArea();
        output.setBounds(20,200,1300,350);
        output.setFont(font);
        output.setForeground(Color.BLACK);
        output.setEditable(false);
        
        search = new JButton("Search");
		search.setBounds(400,600,150,40);
		search.addActionListener(this);
        search.setFont(font);
        Color pul = new Color(0,0,255);
        Border bored = BorderFactory.createLineBorder(pul,5);
        search.setBorder(bored);
        search.setForeground(Color.WHITE);
        search.setBackground(blue);

        back = new JButton("Go Back");
		back.setBounds(600,600,150,40);
		back.addActionListener(this);
        back.setFont(font);
        back.setBorder(bored);
        back.setForeground(Color.WHITE);
        back.setBackground(blue);

        con.add(branchL);
		con.add(branchT);
        con.add(output);
        con.add(search);
        con.add(back);
    }

    public void actionPerformed(ActionEvent ae) 
    {
        if(ae.getSource()==search)
        {
            String branch = (String) branchT.getSelectedItem();
			try
            {
                searchRecordInCSV("student.csv", branch);
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
        }

        if(ae.getSource()==back)
        {
            try
            {
                this.dispose();
                Search1 h=new Search1();
                h.setSize(2300,790);
                h.setVisible(true);
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
        }
    }

    private void searchRecordInCSV(String filename, String branch) throws IOException {
        StringBuilder recordText = new StringBuilder();
        boolean recordFound = false;

        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 4 && parts[3].equalsIgnoreCase(branch)) {
                    recordText.append("Student By Branch Records:\n");
                    recordText.append("NAME\t|USN\t|SEM\t|BRANCH\t|CGPA\t|NOB\t|COMPANY\t|CTC\t|COMMENTS\n");
                    recordText.append(
                            "--------------------------------------------------------------------------------------------------------------------------------\n");
                    recordText.append(line.replace(",", "\t"));
                    recordText.append("\n");
                    recordFound = true;
                }
            }
        }

        if (!recordFound) {
            showMessageDialog(null, "No records found for the selected branch.");
        }

        output.setText(recordText.toString());
    }

    public static void main(String args[])
    {
        SearchByBranch ser=new SearchByBranch();
		ser.setSize(2300,790);
		ser.setVisible(true);
    }
}
